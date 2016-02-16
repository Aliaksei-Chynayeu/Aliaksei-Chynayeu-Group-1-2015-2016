package com.epam.minsk.connection;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class MongoConnection {
	
	private static MongoClient mongo = null;
	private static DB mongoDB = null;
	private static final String DB_NAME = "RecipeDB";
	private static final String INGREDIENTS = "ingredients";
	private static final String RECIPES = "recipes";
	
	static {
		if (mongo == null) {
			mongo = new MongoClient("localhost", 27017);
			mongo.setWriteConcern(WriteConcern.SAFE);
		}
		mongoDB = mongo.getDB(DB_NAME);
	}
	
	public static DBCollection getIngredientCollection() {
		return mongoDB.getCollection(INGREDIENTS);
	}
	
	public static DBCollection getRecipeCollection() {
		return mongoDB.getCollection(RECIPES);
	}
}
