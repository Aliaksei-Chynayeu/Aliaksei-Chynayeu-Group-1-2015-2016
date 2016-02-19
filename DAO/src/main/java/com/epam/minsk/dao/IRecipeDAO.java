package com.epam.minsk.dao;

import java.util.List;

import com.epam.minsk.bean.Recipe;
import com.epam.minsk.exception.MongoDBException;


public interface IRecipeDAO {
	
	List<Recipe> findAll();
	boolean create(Recipe recipe) throws MongoDBException;
	boolean update(Recipe recipe) throws MongoDBException;
	boolean deleteById(String id) throws MongoDBException;
	boolean deleteByName(String name) throws MongoDBException;
	Recipe findById(String id);
	Recipe findByName(String name);
}
