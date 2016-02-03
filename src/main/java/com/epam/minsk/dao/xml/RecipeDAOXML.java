package com.epam.minsk.dao.xml;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.Recipe;
import com.epam.minsk.dao.IRecipeDAO;
import com.epam.minsk.xml.MarshalUtil;
import com.epam.minsk.xml.WrapperXML;

/** Contains implementation for Recipe DAO */
public class RecipeDAOXML implements IRecipeDAO {
	
	private List<Recipe> recipeList;
	/** path to XML file where data are saved */
	private static final String PATH_TO_FILE = "recipes.xml";
	private static Unmarshaller jaxbUnmarshaller;
	private static Marshaller jaxbMarshaller;
	private static URL url;
	/** Log4j */
	private static final Logger LOG = Logger.getLogger(RecipeDAOXML.class);
	
	static {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Recipe.class, Ingredient.class, WrapperXML.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			url = RecipeDAOXML.class.getClassLoader().getResource(PATH_TO_FILE);
			} catch (JAXBException e) {
				LOG.error("JAXBException" + e);
			}		
	}
	
	@Override
	public List<Recipe> findAll() {
		try {
			recipeList = MarshalUtil.unmarshal(jaxbUnmarshaller, Recipe.class, url.getPath());
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		return recipeList;
	}

	@Override
	public boolean add(Recipe recipe) {
		List<Recipe> list = findAll();
		list.add(recipe);
		try {
			MarshalUtil.marshal(jaxbMarshaller, list, url.getPath(), recipe.getClass());
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		
		return false;
	}

	@Override
	public boolean update(Recipe recipe) {
		delete(recipe.getId());
		List<Recipe> list = findAll();
		list.add(recipe);
		try {
			MarshalUtil.marshal(jaxbMarshaller, list, url.getPath(), recipe.getClass());
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		List<Recipe> list = findAll();
		for(Iterator<Recipe> it = list.iterator(); it.hasNext(); ) {
		if(it.next().getId() == id) {
				it.remove();
				break;
			}
		}
		try {
			MarshalUtil.marshal(jaxbMarshaller, list, url.getPath(), Recipe.class);
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		} 
		return false;		
	}

	@Override
	public Recipe findById(Long id) {
		try {
			URL url = this.getClass().getClassLoader().getResource(PATH_TO_FILE);
			recipeList = MarshalUtil.unmarshal(jaxbUnmarshaller, Recipe.class, url.getPath());
			for (Recipe recipeEntity : recipeList) {
				if (recipeEntity.getId() == id) {
					return recipeEntity;
				}
			}
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		return null;
	}

	@Override
	public List<Recipe> findByName(String name) {
		List<Recipe> list = new ArrayList<Recipe>();
		try {
			URL url = this.getClass().getClassLoader().getResource(PATH_TO_FILE);
			recipeList = MarshalUtil.unmarshal(jaxbUnmarshaller, Recipe.class, url.getPath());
			for (Recipe recipe : recipeList) {
				if (recipe.getName() == name) {
					list.add(recipe);
				}
			}
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		return list;
	}

}
