package com.epam.minsk.runner;

import java.util.ArrayList;
import java.util.List;

import com.epam.minsk.bean.IComponentProduct;
import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.Recipe;
import com.epam.minsk.dao.IComponentProductDAO;
import com.epam.minsk.dao.IRecipeDAO;
import com.epam.minsk.factory.FactoryDAO;
import com.epam.minsk.factory.FactoryDAOXML;


public class ThreadForRun extends Thread {

	FactoryDAO factory = new FactoryDAOXML();
	IRecipeDAO recipeDAO = factory.getRecipeDAO();
	IComponentProductDAO ingredientDAO = factory.getIngedientDAO();
	private Object sync;
	
	public ThreadForRun (Object sync) {
		this.sync = sync;
	}
	
	@Override
	public void run() {
		synchronized (sync) {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(1l);
		ingredient.setName("bla");
		
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		List<IComponentProduct> list = new ArrayList<IComponentProduct>();
		list.add(ingredient);
		recipe.setComponents(list);
		recipeDAO.add(recipe);
		}
	}
}
