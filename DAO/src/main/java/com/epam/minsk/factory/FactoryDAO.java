package com.epam.minsk.factory;

import com.epam.minsk.dao.IComponentProductDAO;
import com.epam.minsk.dao.IRecipeDAO;


/** Contains methods which allow to get main DAO entities  */
public interface FactoryDAO {

	/** Return Recipe DAO */
	IRecipeDAO getRecipeDAO();
	/** Retrun Ingredient DAO */	
	IComponentProductDAO getIngedientDAO();
	
}
