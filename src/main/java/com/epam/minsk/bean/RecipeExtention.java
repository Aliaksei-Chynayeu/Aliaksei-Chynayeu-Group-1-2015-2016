package com.epam.minsk.bean;

/**
 * @author Dzina_Andreyeva
 * Fake class for helping to generate OutOfMemoryerror and StackOverFlowError
 */
public class RecipeExtention extends Recipe {
	
	private IngredientExtention ingredientExt;
	private RecipeExtention recipeExt;
	
	public RecipeExtention() {
		super();
	}
	
	public RecipeExtention (IngredientExtention ingredientExt){
		this();
		this.ingredientExt  = ingredientExt;
	}
	
	public RecipeExtention (RecipeExtention recipeExt){
		this();
		this.recipeExt = recipeExt;
	}
	
	public IngredientExtention getIngredientExtenstion() {
		return ingredientExt;
	}
	
	public RecipeExtention getRecipeExtenstion() {
		return recipeExt;
	}

}
