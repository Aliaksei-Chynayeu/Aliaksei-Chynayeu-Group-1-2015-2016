package com.epam.minsk.rest.service;

import java.util.ArrayList;
import java.util.List;

import com.epam.minsk.bean.IComponentProduct;
import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.dao.IComponentProductDAO;
import com.epam.minsk.exception.MongoDBException;
import com.epam.minsk.factory.FactoryDAO;
import com.epam.minsk.factory.FactoryDAOMongo;

/**
 * Service class which allows to manipulate Ingredients
 * @author Dzina_Andreyeva
 *
 */
public class IngredientService {
	
private static IComponentProductDAO ingredientDAO;
	
	static {
		FactoryDAO factory = new FactoryDAOMongo();
		ingredientDAO = factory.getIngedientDAO();
	}
	
	public Ingredient getIngredient(String id) {
		return (Ingredient)ingredientDAO.findById(id);
	}
	
	public List<Ingredient> getIngredientList() {
		List<IComponentProduct> ingList = ingredientDAO.findAll();
		List<Ingredient> ingModifyList = new ArrayList<Ingredient>();
		List<Ingredient> ingModifyList1 = new ArrayList<Ingredient>();
		for (IComponentProduct ing : ingList) {
			ingModifyList.add((Ingredient) ing);
		}
		for (int i = 0; i < 100; i++) {
			ingModifyList1.add(ingModifyList.get(i));
		}
		return ingModifyList1;
	}
	
	public void createIngredient(Ingredient ing) throws MongoDBException {
		ingredientDAO.create(ing);
	}
	
	public void deleteIngredientById(String id) throws MongoDBException {
		ingredientDAO.deleteById(id);
	}
	
	public void deleteIngredientByName(String name) throws MongoDBException {
		ingredientDAO.deleteByName(name);
	}

	public void updateIngredient(Ingredient newIng) throws MongoDBException {
		ingredientDAO.update(newIng);
	}
}
