package com.epam.minsk.dao.mongo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.MeasureUnit;
import com.epam.minsk.dao.IComponentProductDAO;
import com.epam.minsk.exception.MongoDBException;
import com.epam.minsk.factory.FactoryDAO;
import com.epam.minsk.factory.FactoryDAOMongo;

@RunWith(JUnit4.class)
public class IngredientDAOMongoTest {
	
	private Ingredient ingredient;
	private String name = "milk158";
	private static FactoryDAO factory = new FactoryDAOMongo();
	private IComponentProductDAO ingredientDAO;

	@Before
	public void setUp() {
		ingredient = new Ingredient();
		ingredient.setName(name);
		ingredient.setMesureUnit(MeasureUnit.GRAMM);
		ingredient.setQuantity(new Double(100));
		ingredientDAO = factory.getIngedientDAO();
	}
	
	@Test
	public void test_CRUD() throws MongoDBException {
		assertNull(ingredientDAO.findByName(name));
		
		ingredientDAO.create(ingredient);
		
		assertNotNull(ingredientDAO.findByName(name));
			
		ingredient.setQuantity(new Double(354));
		ingredientDAO.update(ingredient);
		assertTrue(((Ingredient)ingredientDAO.findByName(name)).getQuantity() == 354);
		
		ingredientDAO.deleteByName(name);
		assertNull(ingredientDAO.findByName(name));
	}
}
