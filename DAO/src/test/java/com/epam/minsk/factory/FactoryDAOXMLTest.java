package com.epam.minsk.factory;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.epam.minsk.dao.IComponentProductDAO;
import com.epam.minsk.dao.IRecipeDAO;
import com.epam.minsk.dao.xml.IngredientDAOXML;
import com.epam.minsk.dao.xml.RecipeDAOXML;

@RunWith(JUnit4.class)
public class FactoryDAOXMLTest {
	
private FactoryDAO factory;
	
	@Before
	public void setUp() {
		factory = new FactoryDAOXML();
	}
	
	@Test
	public void test_getRecipeDAO() {
		IRecipeDAO recipeDAO = factory.getRecipeDAO();
		
		assertTrue(recipeDAO instanceof RecipeDAOXML);
	}
	
	@Test
	public void test_getIngredientDAO() {
		IComponentProductDAO ingredientDAO = factory.getIngedientDAO();
		
		assertTrue(ingredientDAO instanceof IngredientDAOXML);
	}

}
