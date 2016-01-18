package com.epam.minsk.errorgenerator;

import com.epam.minsk.bean.RecipeExtention;

/**
 * @author Dzina_Andreyeva
 * Generates OutOfMemoryError
 */
public class OutOfMemoryErrorGenerator {
	
	public static void generateOutOfMemoryError() {
		 RecipeExtention recipe = new RecipeExtention();
	     while (true)
	     {
	    	 recipe = new RecipeExtention(recipe);
	     }
	}
}
