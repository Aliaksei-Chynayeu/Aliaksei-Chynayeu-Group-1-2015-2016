package com.epam.minsk.rest.service;

import java.util.List;

import com.epam.minsk.bean.Recipe;
import com.epam.minsk.dao.IRecipeDAO;
import com.epam.minsk.exception.MongoDBException;
import com.epam.minsk.factory.FactoryDAO;
import com.epam.minsk.factory.FactoryDAOMongo;

/**
 * Service class which allows to manipulate Recipes
 * @author Dzina_Andreyeva
 *
 */
public class RecipeService {
	
	private static IRecipeDAO recipeDAO;
	
	static {
		FactoryDAO factory = new FactoryDAOMongo();
		recipeDAO = factory.getRecipeDAO();
	}
	
	public Recipe getRecipe(String id) {
		return recipeDAO.findById(id);
	}
	
	public List<Recipe> getRecipeList() {
		return recipeDAO.findAll();
	}
	
	public void createRecipe(Recipe recipe) throws MongoDBException {
		recipeDAO.create(recipe);
	}
	
	public void deleteRecipeById(String id) throws MongoDBException {
		recipeDAO.deleteById(id);
	}
	
	public void deleteRecipeByName(String name) throws MongoDBException {
		recipeDAO.deleteByName(name);
	}

	public void updateRecipe(Recipe newRecipe) throws MongoDBException {
		recipeDAO.update(newRecipe);
	}
		
}
