package com.epam.minsk.runner;

import java.sql.SQLException;
import com.epam.minsk.connection.MongoConnection;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class Runner {

	public static void main(String[] args) throws SQLException {
		Generator.generateIngredientList();
		Generator.generateRecipeList();
		
		DBCollection collection = MongoConnection.getRecipeCollection();
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		
		DBCollection collection2 = MongoConnection.getIngredientCollection();
		DBCursor cursor2 = collection2.find();
		while (cursor2.hasNext()) {
			System.out.println(cursor2.next());
		}
		
	}
}
