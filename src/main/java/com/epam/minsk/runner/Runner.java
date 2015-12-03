package com.epam.minsk.runner;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.epam.minsk.xml.analyzer.IngredientAnalyzerSAX;
import com.epam.minsk.xml.analyzer.RecipeAnalyzerSAX;

public class Runner {

	public static void main(String[] args) {
		try {
			XMLReader readerRecipe = org.xml.sax.helpers.XMLReaderFactory.createXMLReader();
			XMLReader readerComponent = org.xml.sax.helpers.XMLReaderFactory.createXMLReader();
			RecipeAnalyzerSAX recipeSax = new RecipeAnalyzerSAX();
			IngredientAnalyzerSAX componentSAX = new IngredientAnalyzerSAX();
			readerRecipe.setContentHandler(recipeSax);
			readerRecipe.parse("recipe.xml");
			readerComponent.setContentHandler(componentSAX);
			readerComponent.parse("ingredient.xml");
		} catch (SAXException e) {
			e.printStackTrace();
			System.out.print("error of SAX");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("I/О exception");
		}
		System.out.print("success");
	}

}
