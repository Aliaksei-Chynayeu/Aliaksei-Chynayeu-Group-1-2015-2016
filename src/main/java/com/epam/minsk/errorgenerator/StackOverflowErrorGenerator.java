package com.epam.minsk.errorgenerator;

import com.epam.minsk.bean.IngredientExtention;
import com.epam.minsk.bean.RecipeExtention;

/**
 * @author Dzina_Andreyeva
 * Generates StackOverflowError
 */
public class StackOverflowErrorGenerator {
	
	public static void generateStackOverflowError() {
		new RecipeExtention(new IngredientExtention());
	}

}
