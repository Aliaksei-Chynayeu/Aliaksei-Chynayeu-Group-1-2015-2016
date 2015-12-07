package com.epam.minsk.factory;

import com.epam.minsk.dao.IComponentProductDAO;
import com.epam.minsk.dao.IRecipeDAO;
import com.epam.minsk.dao.xml.IngredientDAOXML;
import com.epam.minsk.dao.xml.RecipeDAOXML;

/** Contains methods which allow to get main DAO entities for XML data  */
public class FactoryDAOXML implements FactoryDAO {

	@Override
	public IRecipeDAO getRecipeDAO() {
		return new RecipeDAOXML();
	}

	@Override
	public IComponentProductDAO getIngedientDAO() {
		return new IngredientDAOXML();
	}

	
}
