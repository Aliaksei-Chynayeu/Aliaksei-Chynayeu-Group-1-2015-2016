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
import com.mongodb.BulkWriteOperation;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.BulkWriteResult;

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
		BulkWriteOperation bulk = ingredientCollection.initializeOrderedBulkOperation();
		BasicDBObject query = new BasicDBObject();
		query.put("name", newIngredient.getName());
		query.put("measure", newIngredient.getMesureUnit().toString());
		query.put("quantity", newIngredient.getQuantity().toString());
		bulk.insert(query);
		BulkWriteResult rightResult = bulk.execute();
		int n = rightResult.getInsertedCount();
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
		BulkWriteOperation bulk = ingredientCollection.initializeOrderedBulkOperation();
		BasicDBObject query = new BasicDBObject();
		query.put("name", updateIngredient.getName());
		query.put("measure", updateIngredient.getMesureUnit().toString());
		query.put("quantity", updateIngredient.getQuantity().toString());
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.put("name", updateIngredient.getName());
		bulk.find(updateQuery).upsert().updateOne(new BasicDBObject("$set", query));
		BulkWriteResult rightResult = bulk.execute();
		int n = rightResult.getMatchedCount();
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
		DBCursor cursor = ingredientCollection.find(query);
		if (cursor.hasNext()) {
			DBObject ingredientObject = cursor.next();
			ingredient.setId(((ObjectId)ingredientObject.get( "_id" )).toString());
			ingredient.setName(ingredientObject.get("name").toString());
			ingredient.setMesureUnit(MeasureUnit.valueOf(ingredientObject.get("measure").toString()));
			ingredient.setQuantity(Double.valueOf(ingredientObject.get("quantity").toString()));
			return ingredient;
		}
		else return null;
	}

	@Override
	public IComponentProduct findByName(String name) {
		Ingredient ingredient = new Ingredient();
		DBCollection ingredientCollection = MongoConnection.getIngredientCollection();
		BasicDBObject query = new BasicDBObject();
		query.put("name", name);
		DBCursor cursor = ingredientCollection.find(query);
		if (cursor.hasNext()) {
			DBObject ingredientObject = cursor.next();
			ingredient.setId(((ObjectId)ingredientObject.get( "_id" )).toString());
			ingredient.setName(ingredientObject.get("name").toString());
			ingredient.setMesureUnit(MeasureUnit.valueOf(ingredientObject.get("measure").toString()));
			ingredient.setQuantity(Double.valueOf(ingredientObject.get("quantity").toString()));
			return ingredient;
		}
		else return null;
	}
}
