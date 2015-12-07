package com.epam.minsk.dao;

import java.util.List;

import com.epam.minsk.bean.IComponentProduct;

public interface IComponentProductDAO {
	
	List<IComponentProduct> findAll();
	boolean create();
	boolean update();
	boolean delete();
	IComponentProduct findById(Long id);
	List<IComponentProduct> findByName(String name);
}
