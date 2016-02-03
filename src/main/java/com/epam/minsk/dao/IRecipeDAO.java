package com.epam.minsk.dao;

import java.util.List;

import com.epam.minsk.bean.Recipe;


public interface IRecipeDAO {
	
	List<Recipe> findAll();
	boolean add(Recipe recipe);
	boolean update(Recipe recipe);
	boolean delete(Long id);
	Recipe findById(Long id);
	List<Recipe> findByName(String name);
}
