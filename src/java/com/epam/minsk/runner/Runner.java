package com.epam.minsk.runner;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.MeasureUnit;
import com.epam.minsk.exception.DAOException;
import com.epam.misnk.dao.hibernate.IngredientDAOHibernate; 

public class Runner {
	private static final Logger LOG = Logger.getLogger(Runner.class);
	private static ClassPathXmlApplicationContext ctx;
	
	static {
		ctx = new ClassPathXmlApplicationContext("spring.xml");
	}

	public static void main(String[] args) {
		
		IngredientDAOHibernate ingDAO = ctx.getBean(IngredientDAOHibernate.class);
		Ingredient newIng = ctx.getBean(Ingredient.class);
		newIng.setName("Butter");
		newIng.setComment("Bitter butter is better than biter");
		newIng.setRating(4);
		newIng.setQuantity(2.8);
		newIng.setMesureUnit(MeasureUnit.GRAMM);
		
		try {
			List<Ingredient> ingList = ingDAO.findAll();
		    /** Print what we got */
			LOG.warn("Getting the list of all ingredients");
			for (Ingredient ingrid : ingList) {
				printIngredient(ingrid);
			}
			
			Ingredient ing = ingDAO.findById(1);
			/** Print what we got */
			LOG.warn("Getting ingredient by id 1");
			printIngredient(ing);
			
			/** create new */
			ingDAO.create(newIng);
			/** update existing */
			ing.setName("HUGE BREAD");
			ingDAO.update(ing);
			/** Print what we got */
			ingList = ingDAO.findAll();
			LOG.warn("Adding new ingredient and updating the old one");
			for (Ingredient ingrid : ingList) {
				printIngredient(ingrid);
			}
			
			/** delete newly created */
			ingList = ingDAO.findAll();
			int id = 0;
			for (Ingredient ingrid : ingList) {
				if (ingrid.getName().equals("Butter")) {
					id = ingrid.getIngredientId();
				}
			}
			ingDAO.deleteById(id);
			/** Print what we got */
			ingList = ingDAO.findAll();
			LOG.warn("Deleting the newly added ingredient with ID = " + id);
			for (Ingredient ingrid : ingList) {
				printIngredient(ingrid);
			}
			/** returning back to initial state */
			ing.setName("Bread");
			ingDAO.update(ing);
		} catch (DAOException e) {
			e.printStackTrace();
		} finally {
			ctx.close();
		}
	}
	
	private static void printIngredient(Ingredient ingrid) {
		LOG.info("-----------------------");
		LOG.info("Id: " + ingrid.getIngredientId());
		LOG.info("Name: " + ingrid.getName());
		LOG.info("Comment: " + ingrid.getComment());
		LOG.info("Rating: " + ingrid.getRating());
		LOG.info("Quantity: " + ingrid.getQuantity());
		LOG.info("Meas. unit: " + ingrid.getMesureUnit());
		LOG.info("-----------------------");
	}
}
