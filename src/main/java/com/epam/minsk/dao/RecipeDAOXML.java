package com.epam.minsk.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.epam.minsk.bean.ComponentEntity;
import com.epam.minsk.xml.analyzer.RecipeAnalyzerSAX;

public class RecipeDAOXML implements IComponentEntityDAO {
	
	List<IComponentEntityDAO> recipeList = new ArrayList<IComponentEntityDAO>();
	/** path to XML file where data are saved */
	private static final String PATH_TO_FILE = "recipe.xml";
	
	@Override
	public List<ComponentEntity> findAll() {
		XMLReader readerRecipe;
		List<ComponentEntity> recipeList = new ArrayList<ComponentEntity>();
		try {
			readerRecipe = org.xml.sax.helpers.XMLReaderFactory.createXMLReader();
			RecipeAnalyzerSAX recipeSax = new RecipeAnalyzerSAX();
			readerRecipe.setContentHandler(recipeSax);
			readerRecipe.parse(PATH_TO_FILE);
			recipeList = recipeSax.getRecipeList();
		} catch (SAXException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
		}
		return recipeList;
	}

	@Override
	public boolean create() {
		return false;
	}

	@Override
	public boolean update() {
		return false;
	}

	@Override
	public boolean delete() {
		return false;
	}

	@Override
	public ComponentEntity find() {
		return null;
	}

}
