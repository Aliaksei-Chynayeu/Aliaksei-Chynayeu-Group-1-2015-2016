package com.epam.minsk.dao.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.epam.minsk.bean.IComponentProduct;
import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.MeasureUnit;
import com.epam.minsk.connection.MongoConnection;
import com.epam.minsk.dao.IComponentProductDAO;
import com.epam.minsk.exception.MongoDBException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/** Contains implementation for Ingredient DAO */
public class IngredientDAOMongo implements IComponentProductDAO {

	@Override
	public List<IComponentProduct> findAll() {
		List<IComponentProduct> ingredientList = new ArrayList<IComponentProduct>();
		DBCollection ingredientCollection = MongoConnection.getIngredientCollection();
		DBCursor cursor = ingredientCollection.find();
		while (cursor.hasNext()) {
			DBObject ingredientObject = cursor.next();
			Ingredient ingredient = new Ingredient();
			ingredient.setId(((ObjectId)ingredientObject.get( "_id" )).toString());
			ingredient.setName(ingredientObject.get("name").toString());
			ingredient.setMesureUnit(MeasureUnit.valueOf(ingredientObject.get("measure").toString()));
			ingredient.setQuantity(Double.valueOf(ingredientObject.get("quantity").toString()));
			ingredientList.add(ingredient);
		}
		return ingredientList;
	}

	@Override
	public boolean create(IComponentProduct ingredient) throws MongoDBException {
		Ingredient newIngredient = (Ingredient) ingredient;
		DBCollection ingredientCollection = MongoConnection.getIngredientCollection();
		BasicDBObject query = new BasicDBObject();
		query.put("name", newIngredient.getName());
		query.put("measure", newIngredient.getMesureUnit().toString());
		query.put("quantity", newIngredient.getQuantity().toString());
		WriteResult rightResult = ingredientCollection.insert(query);
		int n = rightResult.getN();
		if (n == 1) {
			return true;
		} else {
			throw new MongoDBException();
		}
	}

	@Override
	public synchronized boolean update(IComponentProduct ingredient) throws MongoDBException {
		Ingredient updateIngredient = (Ingredient) ingredient;
		DBCollection ingredientCollection = MongoConnection.getIngredientCollection();
		BasicDBObject query = new BasicDBObject();
		query.put("name", updateIngredient.getName());
		query.put("measure", updateIngredient.getMesureUnit().toString());
		query.put("quantity", updateIngredient.getQuantity().toString());
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.put("name", updateIngredient.getName());
		WriteResult rightResult = ingredientCollection.update(query, updateQuery);
		int n = rightResult.getN();
		if (n == 1) {
			return true;
		} else {
			throw new MongoDBException();
		}
	}

	@Override
	public synchronized boolean deleteById(String id) throws MongoDBException {
		DBCollection ingredientCollection = MongoConnection.getIngredientCollection();
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		WriteResult rightResult = ingredientCollection.remove(query);
		int n = rightResult.getN();
		if (n == 1) {
			return true;
		} else {
			throw new MongoDBException();
		}
	}
	
	@Override
	public synchronized boolean deleteByName(String name) throws MongoDBException {
		DBCollection ingredientCollection = MongoConnection.getIngredientCollection();
		BasicDBObject query = new BasicDBObject();
		query.put("name", name);
		WriteResult rightResult = ingredientCollection.remove(query);
		int n = rightResult.getN();
		if (n == 1) {
			return true;
		} else {
			throw new MongoDBException();
		}
	}

	@Override
	public IComponentProduct findById(String id) {
		Ingredient ingredient = new Ingredient();
		DBCollection ingredientCollection = MongoConnection.getIngredientCollection();
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBObject ingredientObject = ingredientCollection.findOne(query);
		ingredient.setId(((ObjectId)ingredientObject.get( "_id" )).toString());
		ingredient.setName(ingredientObject.get("name").toString());
		ingredient.setMesureUnit(MeasureUnit.valueOf(ingredientObject.get("measure").toString()));
		ingredient.setQuantity(Double.valueOf(ingredientObject.get("quantity").toString()));
		return ingredient;
	}

	@Override
	public IComponentProduct findByName(String name) {
		Ingredient ingredient = new Ingredient();
		DBCollection ingredientCollection = MongoConnection.getIngredientCollection();
		BasicDBObject query = new BasicDBObject();
		query.put("name", name);
		DBCursor cursor = ingredientCollection.find(query);
		DBObject ingredientObject = cursor.next();
		ingredient.setId(((ObjectId)ingredientObject.get( "_id" )).toString());
		ingredient.setName(ingredientObject.get("name").toString());
		ingredient.setMesureUnit(MeasureUnit.valueOf(ingredientObject.get("measure").toString()));
		ingredient.setQuantity(Double.valueOf(ingredientObject.get("quantity").toString()));
		return ingredient;
	}
}
