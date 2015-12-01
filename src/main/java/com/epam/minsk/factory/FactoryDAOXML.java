package com.epam.minsk.factory;

import com.epam.minsk.dao.IngredientDAOXML;
import com.epam.minsk.dao.IComponentEntityDAO;


public class FactoryDAOXML implements FactoryDAO {

	@Override
	public IComponentEntityDAO getComponentEntityDAO() {
		return new IngredientDAOXML();
	}

}
