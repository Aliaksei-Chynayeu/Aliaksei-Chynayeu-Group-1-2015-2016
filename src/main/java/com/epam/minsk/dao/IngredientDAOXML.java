package com.epam.minsk.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.epam.minsk.bean.ComponentEntity;
import com.epam.minsk.dao.IComponentEntityDAO;
import com.epam.minsk.xml.analyzer.IngredientAnalyzerSAX;

public class IngredientDAOXML  implements IComponentEntityDAO {

	@Override
	public List<ComponentEntity> findAll() {
		XMLReader readerIngredient;
		List<ComponentEntity> ingredientList = new ArrayList<ComponentEntity>();
		try {
			readerIngredient = org.xml.sax.helpers.XMLReaderFactory.createXMLReader();
			IngredientAnalyzerSAX ingredientSax = new IngredientAnalyzerSAX();
			readerIngredient.setContentHandler(ingredientSax);
			readerIngredient.parse("ingredient.xml");
			ingredientList = ingredientSax.getIngredientList();
		} catch (SAXException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
		}
		return ingredientList;
	}

	@Override
	public boolean create() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ComponentEntity find() {
		// TODO Auto-generated method stub
		return null;
	}

}
