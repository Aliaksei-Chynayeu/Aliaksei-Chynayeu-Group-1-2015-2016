package com.epam.minsk.bean;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RecipeTest {
	
	private Recipe recipe;
	
	@Before
	public void setUp() {
		recipe = new Recipe();
	}
	
	@Test
	public void test_GetSetId() {
		String id = "id";
		recipe.setId(id);
		
		assertEquals(id, recipe.getId());
	}
	
	@Test
	public void test_GetSetName() {
		String name = "name";
		recipe.setName(name);
		
		assertEquals(name, recipe.getName());
	}
	
	@Test
	public void test_GetSetComment() {
		String comment = "comment";
		recipe.setComment(comment);
		
		assertEquals(comment, recipe.getComment());
	}
	
	@Test
	public void test_GetSetRating() {
		int rating = 3;
		recipe.setRating(rating);
		
		assertEquals(rating, recipe.getRating());
	}
	
	@Test
	public void test_GetSetDescription() {
		String description = "testDescription";
		recipe.setDescription(description);
		
		assertEquals(description, recipe.getDescription());
	}
	
	@Test
	public void test_GetSetIngredients() {
		List<IComponentProduct> ingredients = new ArrayList<IComponentProduct>();
		recipe.setIngredients(ingredients);
		
		assertEquals(ingredients, recipe.getIngredients());
	}
	
	@Test
	public void test_GetSetCategoryList() {
		List<Category> categoryList = new ArrayList<Category>();
		recipe.setCategoryList(categoryList);
		
		assertEquals(categoryList, recipe.getCategoryList());
	}
	
	@Test
	public void test_GetIngredientsId() {
		Ingredient ingredient = new Ingredient();
		ingredient.setId("id");
		recipe.getIngredients().add(ingredient);
			
		assertEquals("id", recipe.getIngredientsId().get(0));
	}
	
	

}
