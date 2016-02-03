package com.epam.minsk.dao;

import java.util.List;

import com.epam.minsk.bean.IComponentProduct;

public interface IComponentProductDAO {
	
	List<IComponentProduct> findAll();
	boolean add(IComponentProduct compProduct);
	boolean update(IComponentProduct component);
	boolean delete(Long id);
	IComponentProduct findById(Long id);
	List<IComponentProduct> findByName(String name);
}
