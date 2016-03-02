package com.epam.minsk.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.epam.minsk.bean.IComponentProduct;
import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.Recipe;

@RunWith(JUnit4.class)
public class CookUtilTest {

	@Test
	public void test_toCook() {
		Recipe recipe = new Recipe();
		List<IComponentProduct> ingredientNessList = new ArrayList<IComponentProduct>();
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId("id1");
		ingredient1.setQuantity(new Double(200));
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId("id2");
		ingredient2.setQuantity(new Double(100));
		Ingredient ingredient3 = new Ingredient();
		ingredient3.setId("id3");
		ingredient3.setQuantity(new Double(400));
		
		ingredientNessList.add(ingredient1);
		ingredientNessList.add(ingredient2);
		ingredientNessList.add(ingredient3);
		recipe.setIngredients(ingredientNessList);		
		
		List<IComponentProduct> ingredientExistList = new ArrayList<IComponentProduct>();
		ingredientExistList.add(ingredient1);
		ingredientExistList.add(ingredient2);
		
		assertFalse(CookUtil.toCook(recipe, ingredientExistList));
		
		ingredientExistList.add(ingredient3);
		
		assertTrue(CookUtil.toCook(recipe, ingredientExistList));
	}

}
