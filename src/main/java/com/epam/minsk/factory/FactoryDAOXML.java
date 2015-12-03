package com.epam.minsk.factory;

import com.epam.minsk.dao.IComponentEntityDAO;
import com.epam.minsk.dao.IngredientDAOXML;
import com.epam.minsk.dao.RecipeDAOXML;

/** Contains methods which allow to get main DAO entities for XML data  */
public class FactoryDAOXML implements FactoryDAO {

	@Override
	public IComponentEntityDAO getRecipeDAO() {
		return new RecipeDAOXML();
	}

	@Override
	public IComponentEntityDAO getIngedientDAO() {
		return new IngredientDAOXML();
	}

	
}
