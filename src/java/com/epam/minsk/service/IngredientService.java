package com.epam.minsk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.exception.DAOException;
import com.epam.misnk.dao.hibernate.IngredientDAOHibernate;

public class IngredientService {
	
	@Autowired
	private IngredientDAOHibernate ingDAO;
	
	public void setIngDAO(IngredientDAOHibernate ingDAO) {
		this.ingDAO = ingDAO;
	}
	
	public Ingredient getIngredient(int id) throws DAOException {
		return ingDAO.findById(id);
	}
	
	public List<Ingredient> getIngList() throws DAOException {
		return ingDAO.findAll();
	}
	
	public void createIng(Ingredient ing) throws DAOException {
		ingDAO.create(ing);
	}
	
	public void deleteIngById(int id) throws DAOException {
		ingDAO.deleteById(id);
	}
	
	public void updateIng(Ingredient newIng) throws DAOException {
		ingDAO.update(newIng);
	}

}
