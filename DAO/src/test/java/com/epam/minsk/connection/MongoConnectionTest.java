package com.epam.minsk.connection;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mongodb.DBCollection;

@RunWith(JUnit4.class)
public class MongoConnectionTest {

	@Test
	public void test_getIngredientCollection() {
		assertTrue(MongoConnection.getIngredientCollection() instanceof DBCollection);
		assertNotNull(MongoConnection.getIngredientCollection());
	}
	
	@Test
	public void test_getRecipeCollection() {
		assertTrue(MongoConnection.getRecipeCollection() instanceof DBCollection);
		assertNotNull(MongoConnection.getRecipeCollection());
	}

}
