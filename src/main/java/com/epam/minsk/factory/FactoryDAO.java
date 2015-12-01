package com.epam.minsk.factory;

import com.epam.minsk.dao.IComponentEntityDAO;


public interface FactoryDAO {

	IComponentEntityDAO getComponentEntityDAO();
	
}
