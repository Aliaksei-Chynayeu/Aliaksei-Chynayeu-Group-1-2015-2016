package com.epam.minsk.dao.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.bson.types.ObjectId;

import com.epam.minsk.bean.IComponentProduct;
import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.MeasureUnit;
import com.epam.minsk.bean.Recipe;
import com.epam.minsk.connection.MongoConnection;
import com.epam.minsk.dao.IRecipeDAO;
import com.epam.minsk.exception.MongoDBException;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/** Contains implementation for Recipe DAO */
public class RecipeDAOMongo implements IRecipeDAO {

	@Override
	public List<Recipe> findAll() {
		List<Recipe> recipeList = new ArrayList<Recipe>();
		DBCollection recipeCollection = MongoConnection.getRecipeCollection();
		DBCursor cursor = recipeCollection.find();
		while (cursor.hasNext()) {
			DBObject recipeObject = cursor.next();
			Recipe recipe = new Recipe();
			recipe.setId(((ObjectId)recipeObject.get( "_id" )).toString());
			recipe.setName(recipeObject.get("name").toString());
			recipeList.add(recipe);
		}
		return recipeList;
	}

	@Override
	public boolean create(Recipe recipe) throws MongoDBException {
		DBCollection recipeCollection = MongoConnection.getRecipeCollection();
		BasicDBObject query = createQueryForRecipe(recipe);
		WriteResult rightResult = recipeCollection.insert(query);
		int n = rightResult.getN();
		if (n == 1) {
			return true;
		} else {
			throw new MongoDBException();
		}
		
	}

	@Override
	public synchronized boolean update(Recipe recipe) throws MongoDBException {
		DBCollection recipeCollection = MongoConnection.getRecipeCollection();
		BasicDBObject query = createQueryForRecipe(recipe);
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.put("name", recipe.getName());
		WriteResult rightResult = recipeCollection.update(query, updateQuery);
		int n = rightResult.getN();
		if (n == 1) {
			return true;
		} else {
			throw new MongoDBException();
		}
	}

	@Override
	public synchronized boolean deleteById(String id) throws MongoDBException {
		DBCollection recipeCollection = MongoConnection.getRecipeCollection();
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		WriteResult rightResult = recipeCollection.remove(query);
		int n = rightResult.getN();
		if (n == 1) {
			return true;
		} else {
			throw new MongoDBException();
		}
	}
	
	@Override
	public synchronized boolean deleteByName(String name) throws MongoDBException {
		DBCollection recipeCollection = MongoConnection.getRecipeCollection();
		BasicDBObject query = new BasicDBObject();
		query.put("name", name);
		WriteResult rightResult = recipeCollection.remove(query);
		int n = rightResult.getN();
		if (n == 1) {
			return true;
		} else {
			throw new MongoDBException();
		}
	}

	@Override
	public Recipe findById(String id) {
		Recipe recipe = new Recipe();
		DBCollection recipeCollection = MongoConnection.getRecipeCollection();
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject recipeObject = recipeCollection.findOne(query);
		recipe.setId(((ObjectId)recipeObject.get( "_id" )).toString());
		recipe.setName(recipeObject.get("name").toString());
		recipe.setIngredients(getIngredientListById(id));
		return recipe;
	}

	@Override
	public Recipe findByName(String name) {
		Recipe recipe = new Recipe();
		DBCollection recipeCollection = MongoConnection.getRecipeCollection();
		BasicDBObject query = new BasicDBObject();
		query.put("name", name);
		DBObject ingredientObject = recipeCollection.findOne(query);
		recipe.setId(((ObjectId)ingredientObject.get( "_id" )).toString());
		recipe.setName(ingredientObject.get("name").toString());
		return recipe;
	}
	
	private List<IComponentProduct> getIngredientListById(String id) {
		List<IComponentProduct> ingredientList = new ArrayList<IComponentProduct>();
		DBCollection recipeCollection = MongoConnection.getRecipeCollection();
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		ListIterator<Object> ingredients = ((BasicDBList) recipeCollection.findOne(query).get("ingredients")).listIterator();
		while(ingredients.hasNext()){
            String nextItem = (String )ingredients.next();
            Ingredient ingredient = getIngredientById(nextItem);
            ingredientList.add(ingredient);            
        }
		return ingredientList;
	}
	
	
	private Ingredient getIngredientById(String id) {
		DBCollection ingredientCollection = MongoConnection.getIngredientCollection();
		Ingredient ingredient = new Ingredient();
		ingredientCollection = MongoConnection.getIngredientCollection();
		BasicDBObject queryIng = new BasicDBObject();
		queryIng.put("_id", new ObjectId(id));
		DBObject ingredientObject = ingredientCollection.findOne(queryIng);
		ingredient.setId(((ObjectId)ingredientObject.get( "_id" )).toString());
		ingredient.setName(ingredientObject.get("name").toString());
		ingredient.setMesureUnit(MeasureUnit.valueOf(ingredientObject.get("measure").toString()));
		ingredient.setQuantity(Double.valueOf(ingredientObject.get("quantity").toString()));
		return ingredient;
	}
	
	private BasicDBObject createQueryForRecipe(Recipe recipe) {
		BasicDBObject query = new BasicDBObject();
		query.put("name", recipe.getName());
		query.put("ingredients", recipe.getIngredientsId());
		query.put("categories", recipe.getCategoryList());
		query.put("description", recipe.getDescription());
		query.put("comment", recipe.getComment());
		query.put("rating", recipe.getRating());
		return query;
	}
	

}
