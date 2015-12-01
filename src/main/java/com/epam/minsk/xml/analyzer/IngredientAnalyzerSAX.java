package com.epam.minsk.xml.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.epam.minsk.bean.ComponentEntity;
import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.MeasureUnit;;

public class IngredientAnalyzerSAX implements ContentHandler {
	
	private List<ComponentEntity> ingredienttList;
	private Ingredient ingredient;
	private char tagName;

	@Override
	public void startDocument() throws SAXException {
		ingredienttList = new ArrayList<ComponentEntity>();		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if (qName.equals("tns:ingredients")){}
		else{
		switch (TagName.valueOf(qName.toUpperCase())){
		case INGREDIENT: {
			String s = "";
			for (int i = 0; i < atts.getLength(); i++) {
				s += atts.getValue(i);
			}
			ingredient = new Ingredient(Long.valueOf((s)));
		} break;
		case NAME:{
			tagName = 'n';
		} break;
		case MEASURE:{
			tagName = 'm';
		} break;
		case QUANTITY:{
			tagName = 'q';
		} break;
		}
	}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		switch (tagName){
		case 'n':{
			ingredient.setName(new String(ch,start,length));
			tagName='0';
			}break;
		case 'm':{
			ingredient.setMesureUnit(MeasureUnit.valueOf(new String(ch,start,length)));
			tagName='0';
			}break;
		case 'q':{
			ingredient.setQuantity(Double.valueOf(new String(ch,start,length)));
			tagName='0';
			}break;
		}	
		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("component".equals(qName)){
			ingredienttList.add(ingredient);
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
	
	public List<ComponentEntity> getIngredientList() {
		return ingredienttList;
	}

}
