package com.epam.minsk.runner;

import com.epam.minsk.dao.IComponentProductDAO;
import com.epam.minsk.dao.IRecipeDAO;
import com.epam.minsk.factory.FactoryDAO;
import com.epam.minsk.factory.FactoryDAOXML;


public class Runner {

	public static void main(String[] args) {
		FactoryDAO factory = new FactoryDAOXML();
		IRecipeDAO recipeDAO = factory.getRecipeDAO();
		IComponentProductDAO ingredientDAO = factory.getIngedientDAO();
		
		Demo demo = new Demo();
		System.out.println(demo.toCook(recipeDAO.findById(1L), ingredientDAO.findAll()));
	}
}
