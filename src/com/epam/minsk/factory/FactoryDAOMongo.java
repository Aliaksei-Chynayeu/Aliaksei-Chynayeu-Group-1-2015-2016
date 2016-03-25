package com.epam.minsk.factory;

import com.epam.minsk.dao.IComponentProductDAO;
import com.epam.minsk.dao.IRecipeDAO;
import com.epam.minsk.dao.mongo.IngredientDAOMongo;
import com.epam.minsk.dao.mongo.RecipeDAOMongo;

public class FactoryDAOMongo implements FactoryDAO {

	@Override
	public IRecipeDAO getRecipeDAO() {
		return new RecipeDAOMongo();
	}

	@Override
	public IComponentProductDAO getIngedientDAO() {
		return new IngredientDAOMongo();
	}

}
