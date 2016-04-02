package com.epam.misnk.dao;

import java.util.List;

import com.epam.minsk.bean.Recipe;
import com.epam.minsk.exception.DAOException;

public interface IRecipeDAO {

	public List<Recipe> findAll() throws DAOException;

	public Recipe findById(int id) throws DAOException;

	public boolean create(Recipe recipe) throws DAOException;

	public boolean update(Recipe recipe) throws DAOException;

	public boolean deleteById(int id) throws DAOException;
}
