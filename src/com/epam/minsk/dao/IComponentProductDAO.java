package com.epam.minsk.dao;

import java.util.List;

import com.epam.minsk.bean.IComponentProduct;
import com.epam.minsk.exception.MongoDBException;

public interface IComponentProductDAO {
	
	List<IComponentProduct> findAll();
	boolean create(IComponentProduct componentProduct) throws MongoDBException;
	boolean update(IComponentProduct componentProduct) throws MongoDBException;
	boolean deleteById(String id) throws MongoDBException;
	boolean deleteByName(String name) throws MongoDBException;
	IComponentProduct findById(String id);
	IComponentProduct findByName(String name);
}
