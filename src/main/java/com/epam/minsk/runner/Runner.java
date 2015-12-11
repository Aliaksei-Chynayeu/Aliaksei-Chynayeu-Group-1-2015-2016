package com.epam.minsk.runner;

import com.epam.minsk.dao.IComponentProductDAO;
import com.epam.minsk.dao.IRecipeDAO;
import com.epam.minsk.factory.FactoryDAO;
import com.epam.minsk.factory.FactoryDAOXML;
import com.epam.minsk.util.CookUtil;


public class Runner {

	public static void main(String[] args) {
		FactoryDAO factory = new FactoryDAOXML();
		IRecipeDAO recipeDAO = factory.getRecipeDAO();
		IComponentProductDAO ingredientDAO = factory.getIngedientDAO();
		
		System.out.println(CookUtil.toCook(recipeDAO.findById(1L), ingredientDAO.findAll()));
	}
}
