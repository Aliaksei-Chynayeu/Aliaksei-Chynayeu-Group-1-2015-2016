package com.epam.misnk.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.Recipe;
import com.epam.minsk.exception.DAOException;
import com.epam.misnk.dao.AbstractDAO;

public class RecipeDAOHibernate extends AbstractDAO<Recipe> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Recipe> findAll() throws DAOException {
		List<Recipe> recipes = new ArrayList<Recipe>();

		try {
			Session session = getSession();
			Criteria cr = session.createCriteria(Recipe.class);
			if (cr.list() != null) {
				recipes.addAll(cr.list());
			}
		} catch (HibernateException ex) {
			throw new DAOException("Database error", ex);
		} finally {
			closeSession();
		}
		return recipes;
	}

	@Override
	public Recipe findById(int id) throws DAOException {
		Recipe recipe;

		try {
			Session session = getSession();
			recipe = session.get(Recipe.class, id);
		} catch (HibernateException ex) {
			throw new DAOException("Database error", ex);
		} finally {
			closeSession();
		}
		return recipe;
	}

	@Override
	public boolean create(Recipe recipe) throws DAOException {
		boolean isCreated = false;
		Transaction tx = null;

		if (recipe == null) {
			return isCreated;
		}

		try {
			Session session = getSession();
			tx = session.beginTransaction();
			session.save(recipe);
			tx.commit();
			isCreated = true;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new DAOException("Database error", ex);
		} finally {
			closeSession();
		}
		return isCreated;
	}

	@Override
	public boolean update(Recipe recipe) throws DAOException {
		boolean isUpdated = false;
		Transaction tx = null;

		if (recipe == null) {
			return isUpdated;
		}

		try {
			Session session = getSession();
			tx = session.beginTransaction();
			Recipe oldRecipe = session.get(Recipe.class, recipe.getRecipeId());
			if (oldRecipe != null) {
				updateRecipe(oldRecipe, recipe);
			}
			session.update(oldRecipe);
			tx.commit();
			isUpdated = true;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new DAOException("Database error", ex);
		} finally {
			closeSession();
		}
		return isUpdated;
	}

	@Override
	public boolean deleteById(int id) throws DAOException {
		boolean isDeleted = false;
		Transaction tx = null;

		try {
			Session session = getSession();
			tx = session.beginTransaction();
			Recipe recipe = session.get(Recipe.class, id);
			if (recipe != null) {
				deleteRecipe(session, recipe);
			}
			tx.commit();
			isDeleted = true;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new DAOException("Database error", ex);
		} finally {
			closeSession();
		}
		return isDeleted;
	}

	private void updateRecipe(Recipe oldRecipe, Recipe newRecipe) {
		oldRecipe.setName(newRecipe.getName());
		oldRecipe.setComment(newRecipe.getComment());
		oldRecipe.setRating(newRecipe.getRating());
		oldRecipe.setDescription(newRecipe.getDescription());
		oldRecipe.setCategoryList(newRecipe.getCategoryList());
		oldRecipe.setIngredients(newRecipe.getIngredients());
	}

	private void deleteRecipe(Session session, Recipe recipe) {
		for (Ingredient ing : recipe.getIngredients()) {
			session.delete(ing);
		}
		session.delete(recipe);
	}
}
