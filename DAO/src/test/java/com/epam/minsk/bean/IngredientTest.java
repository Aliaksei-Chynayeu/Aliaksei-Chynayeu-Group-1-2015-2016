package com.epam.minsk.bean;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IngredientTest {

private Ingredient ingredient;
	
	@Before
	public void setUp() {
		ingredient = new Ingredient();
	}
	
	@Test
	public void test_GetSetId() {
		String id = "id";
		ingredient.setId(id);
		
		assertEquals(id, ingredient.getId());
	}
	
	@Test
	public void test_GetSetName() {
		String name = "name";
		ingredient.setName(name);
		
		assertEquals(name, ingredient.getName());
	}
	
	@Test
	public void test_GetSetComment() {
		String comment = "comment";
		ingredient.setComment(comment);
		
		assertEquals(comment, ingredient.getComment());
	}
	
	@Test
	public void test_GetSetRating() {
		int rating = 3;
		ingredient.setRating(rating);
		
		assertEquals(rating, ingredient.getRating());
	}
	
	@Test
	public void test_GetSetMeasure() {
		ingredient.setMesureUnit(MeasureUnit.GRAMM);
		
		assertEquals(MeasureUnit.GRAMM, ingredient.getMesureUnit());
	}

	@Test
	public void test_GetSetQuantity() {
		Double quantity = new Double(200);
		ingredient.setQuantity(quantity);
		
		assertEquals(quantity, ingredient.getQuantity());
	}
}
