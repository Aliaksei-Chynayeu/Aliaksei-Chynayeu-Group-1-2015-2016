package com.epam.minsk.bean;

/**
 * @author Dzina_Andreyeva
 * Fake class for helping to generate StackOverFlowError
 */
public class IngredientExtention extends Ingredient {

	private RecipeExtention recipeExt;
	
	public IngredientExtention (){
		recipeExt  = new RecipeExtention(new IngredientExtention());
	}
	
	public RecipeExtention getRecipe() {
		return recipeExt;
	}
}
