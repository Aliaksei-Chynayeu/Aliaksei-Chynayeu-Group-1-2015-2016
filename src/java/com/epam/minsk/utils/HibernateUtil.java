package com.epam.minsk.utils;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;

import com.epam.minsk.bean.ComponentEntity;
import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.Recipe;

public class HibernateUtil {
	private static final Logger LOG = Logger.getLogger(HibernateUtil.class);
	private static final SessionFactory sessionFactory;
	private static final StandardServiceRegistry serviceRegistry;
	private static Session session;

	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			configuration.addAnnotatedClass(ComponentEntity.class);
			configuration.addAnnotatedClass(Ingredient.class);
			configuration.addAnnotatedClass(Recipe.class);
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			configuration.setSessionFactoryObserver(new SessionFactoryObserver() {
				private static final long serialVersionUID = 8057245926822427262L;

				@Override
				public void sessionFactoryCreated(SessionFactory factory) {
				}

				@Override
				public void sessionFactoryClosed(SessionFactory factory) {
					((StandardServiceRegistryImpl) serviceRegistry).destroy();
				}
			});

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception ex) {
			LOG.error("Error. Initial SessionFactory creation failed!", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session openSession() {
		session = sessionFactory.openSession();
		return session;
	}

	public static void closeFactory() {
		session.getSessionFactory().close();
	}
}
