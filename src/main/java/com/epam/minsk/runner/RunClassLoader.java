package com.epam.minsk.runner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.log4j.Logger;

import com.epam.minsk.classloader.RecipeChooserClassLoader;

/**@author Dzina_Andreyeva
 * Run app using custom ClassLoader */
public class RunClassLoader {
	
	/** Log4j */
	private static final Logger LOG = Logger.getLogger(RunClassLoader.class);

	public static void main(String[] args) {
		RecipeChooserClassLoader recipeChooserClassLoader = new RecipeChooserClassLoader(RunClassLoader.class.getClassLoader(),
				RunClassLoader.class.getClassLoader().getResource("RecipeChooser.jar").getPath());
		
		try {
			Class<?> clazz = recipeChooserClassLoader.loadClass("com.epam.minsk.runner.Runner");
			Method main = clazz.getMethod("main", new Class[] { String[].class });
			
			int modifiers = main.getModifiers();
	        if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
	             main.invoke(null, new Object[] {new String[] {}});
	        }
		} catch (ClassNotFoundException e) {
			LOG.error("ClassNotFoundException" + e);
		} catch (IllegalAccessException e) {
			LOG.error("IllegalAccessException" + e);
		} catch (IllegalArgumentException e) {
			LOG.error("IllegalArgumentException" + e);
		} catch (SecurityException e) {
			LOG.error("SecurityException" + e);
		} catch (InvocationTargetException e) {
			LOG.error("InvocationTargetException" + e);
		} catch (NoSuchMethodException e) {
			LOG.error("NoSuchMethodException" + e);
		}
		
		
	}

}
