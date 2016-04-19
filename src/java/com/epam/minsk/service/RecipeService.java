package com.epam.minsk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.minsk.bean.Recipe;
import com.epam.minsk.exception.DAOException;
import com.epam.misnk.dao.hibernate.RecipeDAOHibernate;

public class RecipeService {
	
	@Autowired
	private RecipeDAOHibernate recipeDAO;

	public void setRecipeDAO(RecipeDAOHibernate recipeDAO) {
		this.recipeDAO = recipeDAO;
	}

	public Recipe getRecipe(int id) throws DAOException {
		return recipeDAO.findById(id);
	}
	
	public List<Recipe> getRecipeList() throws DAOException {
		return recipeDAO.findAll();
	}
	
	public void createRecipe(Recipe recipe) throws DAOException {
		recipeDAO.create(recipe);
	}
	
	public void deleteRecipeById(int id) throws DAOException {
		recipeDAO.deleteById(id);
	}
	
	public void updateRecipe(Recipe newRecipe) throws DAOException {
		recipeDAO.update(newRecipe);
	}
}
