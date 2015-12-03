package com.epam.minsk.factory;

import com.epam.minsk.dao.IComponentEntityDAO;

/** Contains methods which allow to get main DAO entities  */
public interface FactoryDAO {

	/** Return Recipe DAO */
	IComponentEntityDAO getRecipeDAO();
	/** Retrun Ingredient DAO */	
	IComponentEntityDAO getIngedientDAO();
	
}
