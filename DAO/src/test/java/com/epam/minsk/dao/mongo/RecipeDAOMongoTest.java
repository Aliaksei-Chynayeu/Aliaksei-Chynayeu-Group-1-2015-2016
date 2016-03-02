package com.epam.minsk.dao.mongo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.epam.minsk.bean.Recipe;
import com.epam.minsk.dao.IRecipeDAO;
import com.epam.minsk.exception.MongoDBException;
import com.epam.minsk.factory.FactoryDAO;
import com.epam.minsk.factory.FactoryDAOMongo;

@RunWith(JUnit4.class)
public class RecipeDAOMongoTest {
	
	private Recipe recipe;
	private String name = "recipe123";
	private static FactoryDAO factory = new FactoryDAOMongo();
	private IRecipeDAO recipeDAO;

	@Before
	public void setUp() {
		recipe = new Recipe();
		recipe.setName(name);
		recipe.setComment("myComment");
		recipeDAO = factory.getRecipeDAO();
	}
	
	@Test
	public void test_CRUD() throws MongoDBException {
		assertNull(recipeDAO.findByName(name));
		
		recipeDAO.create(recipe);
		
		assertNotNull(recipeDAO.findByName(name));
			
		recipe.setComment("myNewComment");
		recipeDAO.update(recipe);
		assertEquals("myNewComment", recipeDAO.findByName(name).getComment());
		
		recipeDAO.deleteByName(name);
		assertNull(recipeDAO.findByName(name));
	}

}
