package com.epam.misnk.dao;

import java.util.List;

import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.exception.DAOException;

public interface IIngredientDAO {
	
	public List<Ingredient> findAll() throws DAOException;
	
	public Ingredient findById(int id) throws DAOException;
	
	public boolean create(Ingredient ing) throws DAOException;
	
	public boolean update(Ingredient ing) throws DAOException;
	
	public boolean deleteById(int id) throws DAOException;
}
