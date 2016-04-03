package com.epam.misnk.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.exception.DAOException;
import com.epam.misnk.dao.AbstractDAO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class IngredientDAOHibernate extends AbstractDAO<Ingredient> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingredient> findAll() throws DAOException {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();

		try {
			Session session = getSession();
			Criteria cr = session.createCriteria(Ingredient.class);
			if (cr.list() != null) {
				ingredients.addAll(cr.list());
			}
		} catch (HibernateException ex) {
			throw new DAOException("Database error", ex);
		} finally {
			closeSession();
		}
		return ingredients;
	}

	@Override
	public Ingredient findById(int id) throws DAOException {
		Ingredient ing;

		try {
			Session session = getSession();
			ing = session.get(Ingredient.class, id);
		} catch (HibernateException ex) {
			throw new DAOException("Database error", ex);
		} finally {
			closeSession();
		}
		return ing;
	}

	@Override
	public boolean create(Ingredient ing) throws DAOException {
		boolean isCreated = false;
		Transaction tx = null;
		
		if (ing == null) {
			return isCreated;
		}

		try {
			Session session = getSession();
			tx = session.beginTransaction();
			session.save(ing);
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
	public boolean update(Ingredient ing) throws DAOException {
		boolean isUpdated = false;
		Transaction tx = null;
		
		if (ing == null) {
			return isUpdated;
		}

		try {
			Session session = getSession();
			tx = session.beginTransaction();
			Ingredient ingredient = session.get(Ingredient.class, ing.getIngredientId());
			if (ingredient != null) {
				updateIngredient(ingredient, ing);
			}
			session.update(ingredient);
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
			Ingredient ing = session.get(Ingredient.class, id);
			session.delete(ing);
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

	private void updateIngredient(Ingredient oldIng, Ingredient newIng) {
		oldIng.setName(newIng.getName());
		oldIng.setQuantity(newIng.getQuantity());
		oldIng.setRating(newIng.getRating());
		oldIng.setComment(newIng.getComment());
		oldIng.setMesureUnit(newIng.getMesureUnit());
	}
}
