package com.epam.misnk.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.minsk.exception.DAOException;

public abstract class AbstractDAO<T> {
	@Autowired
	private SessionFactory sessionFactory;
	private volatile Session session;
	
	/**
	 * Returns a current Hibernate session.
	 * 
	 * @return org.hibernate.Session
	 */
	protected Session getSession() throws HibernateException {
		try {
			session = sessionFactory.openSession();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		return session;
	}
	
	/**
	 * Closes current Hibernate session.
	 */
	protected void closeSession() {
		if (session != null) {
			session.close();
		}
	}

	public abstract List<T> findAll() throws DAOException;

	public abstract T findById(int id) throws DAOException;

	public abstract boolean create(T component) throws DAOException;

	public abstract boolean update(T component) throws DAOException;

	public abstract boolean deleteById(int id) throws DAOException;
}
