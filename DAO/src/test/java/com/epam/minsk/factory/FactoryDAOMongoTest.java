package com.epam.minsk.factory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.epam.minsk.dao.IComponentProductDAO;
import com.epam.minsk.dao.IRecipeDAO;
import com.epam.minsk.dao.mongo.IngredientDAOMongo;
import com.epam.minsk.dao.mongo.RecipeDAOMongo;

@RunWith(JUnit4.class)
public class FactoryDAOMongoTest {

	private FactoryDAO factory;
	
	@Before
	public void setUp() {
		factory = new FactoryDAOMongo();
	}
	
	@Test
	public void test_getRecipeDAO() {
		IRecipeDAO recipeDAO = factory.getRecipeDAO();
		
		assertTrue(recipeDAO instanceof RecipeDAOMongo);
	}
	
	@Test
	public void test_getIngredientDAO() {
		IComponentProductDAO ingredientDAO = factory.getIngedientDAO();
		
		assertTrue(ingredientDAO instanceof IngredientDAOMongo);
	}

}
