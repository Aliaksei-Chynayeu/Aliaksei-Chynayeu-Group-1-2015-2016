package com.epam.minsk.dao;

import java.util.List;

import com.epam.minsk.bean.Recipe;


public interface IRecipeDAO {
	
	List<Recipe> findAll();
	boolean create();
	boolean update();
	boolean delete();
	Recipe findById(Long id);
	List<Recipe> findByName(String name);
}
