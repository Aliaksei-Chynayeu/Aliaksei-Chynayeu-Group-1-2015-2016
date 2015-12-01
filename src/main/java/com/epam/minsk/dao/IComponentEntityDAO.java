package com.epam.minsk.dao;

import java.util.List;

import com.epam.minsk.bean.ComponentEntity;

public interface IComponentEntityDAO {
	
	List<ComponentEntity> findAll();
	boolean create();
	boolean update();
	boolean delete();
	ComponentEntity find();

}
