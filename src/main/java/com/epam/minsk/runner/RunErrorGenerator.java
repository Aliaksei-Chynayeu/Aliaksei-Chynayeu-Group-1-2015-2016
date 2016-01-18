package com.epam.minsk.runner;

import com.epam.minsk.errorgenerator.OutOfMemoryErrorGenerator;
import com.epam.minsk.errorgenerator.StackOverflowErrorGenerator;

/**
 * @author Dzina_Andreyeva
 * Runs errors generators
 */
public class RunErrorGenerator {
	 
	public static void main(String[] args) {
		StackOverflowErrorGenerator.generateStackOverflowError();
		OutOfMemoryErrorGenerator.generateOutOfMemoryError();
			
	}
	
	
}
