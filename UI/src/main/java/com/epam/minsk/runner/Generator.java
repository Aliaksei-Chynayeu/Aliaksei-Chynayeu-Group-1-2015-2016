package com.epam.minsk.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.epam.minsk.bean.IComponentProduct;
import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.MeasureUnit;
import com.epam.minsk.bean.Recipe;
import com.epam.minsk.dao.IComponentProductDAO;
import com.epam.minsk.dao.IRecipeDAO;
import com.epam.minsk.exception.MongoDBException;
import com.epam.minsk.factory.FactoryDAO;
import com.epam.minsk.factory.FactoryDAOMongo;

/**
 *  @author Dzina_Andreyeva
 * Generates random objects for MongoDB
 */
public class Generator {
	
	private static FactoryDAO factory = new FactoryDAOMongo();
	/** Log4j */
	private static final Logger LOG = Logger.getLogger(Generator.class);
	
	public static void generateIngredientList(int ingredientNubmer) {
		IComponentProductDAO ingredientDAO = factory.getIngedientDAO();
		MeasureUnit mesures[] = {MeasureUnit.GRAMM, MeasureUnit.ML, MeasureUnit.PIECE};
		Random random = new Random();
		for (int i = 0; i < ingredientNubmer; i++) {
			Ingredient ingredient = new Ingredient();
			ingredient.setName("name" + i);
			ingredient.setMesureUnit(mesures[random.nextInt(mesures.length)]);
			ingredient.setQuantity(Double.valueOf(random.nextInt(i + 1) + 100));
			try {
				ingredientDAO.create(ingredient);
			} catch (MongoDBException e) {
				LOG.error("Error occured during insert Ingredient in DB" + e);
			}
		}
	}
	
	public static void generateRecipeList(int recipeNumber) {
		IRecipeDAO recipeDAO = factory.getRecipeDAO();
		for (int i = 0; i < recipeNumber; i++) {
			Recipe recipe = new Recipe();
			recipe.setName("name" + i);
			recipe.setIngredients(generateIngredientsForRecipe());
			recipe.setComment("comment" + i);
			recipe.setDescription("description" + i);
			try {
				recipeDAO.create(recipe);
			} catch (MongoDBException e) {
				LOG.error("Error occured during insert Recipe in DB" + e);
			}
		}
	}
	
	private static List<IComponentProduct> generateIngredientsForRecipe() {
		List <IComponentProduct> list = new ArrayList<IComponentProduct>();
		IComponentProductDAO ingredientDAO = factory.getIngedientDAO();
		Random random = new Random();
		for (int i = 1; i < random.nextInt(20) + 2; i++) {
			list.add(ingredientDAO.findByName("name" + i));
		}
		return list;
	}

}
