package com.epam.minsk.xml.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.epam.minsk.bean.ComponentEntity;
import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.Recipe;
import com.epam.minsk.bean.Category;

public class RecipeAnalyzerSAX implements ContentHandler {
	
	private List<ComponentEntity> recipeList;
	private Recipe recipe;
	private char tagName;
	
	public void startDocument() throws SAXException {
		recipeList = new ArrayList<ComponentEntity>();
	}
	
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if (qName.equals("tns:recipes")){}
		else{
		switch (TagName.valueOf(qName.toUpperCase())){
		case RECIPE: {
			String s = "";
			for (int i = 0; i < atts.getLength(); i++) {
				s += atts.getValue(i);
			}
			recipe = new Recipe(new Long(s));
		} break;
		case NAME:{
			tagName = 'n';
		} break;
		case INGREDIENT:{
			tagName = 'c';
		} break;
		case RATING:{
			tagName = 'r';
		} break;
		case COMMENT:{
			tagName = 'k';
		} break;
		case DESCRIPTION:{
			tagName = 'd';
		} break;
		case CATEGORY:{
			tagName = 'K';
		} break;
		}
	}
		
	}
	
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		switch (tagName){
		case 'n':{
			recipe.setName(new String(ch,start,length));
			tagName='0';
			}break;
		case 'c':{
			String s = new String(ch,start,length);
			recipe.getComponentList().add(new Ingredient(Long.valueOf((s))));
			tagName='0';
			}break;
		case 'r':{
			recipe.setRating(Integer.valueOf(new String(ch,start,length)));
			tagName='0';
			}break;
		case 'k':{
			recipe.setComment(new String(ch,start,length));
			tagName='0';
			}break;
		case 'd':{
			recipe.setDescription(new String(ch,start,length));
			tagName='0';
			}break;
		case 'K':{
			recipe.getCategoryList().add((Category.valueOf(new String(ch,start,length).toUpperCase())));
			tagName='0';
			}break;
		}	
	}
	
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("recipe".equals(qName)){
			recipeList.add(recipe);
		}
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}
	
	public List<ComponentEntity> getRecipeList() {
		return recipeList;
	}

}
