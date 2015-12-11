package com.epam.minsk.dao.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
	/** Log4j */
	private static final Logger LOG = Logger.getLogger(RecipeDAOXML.class);
	
	static {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Recipe.class, Ingredient.class, WrapperXML.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			} catch (JAXBException e) {
				LOG.error("JAXBException" + e);
			}		
	}
	
	@Override
	public List<Recipe> findAll() {
		try {
			recipeList = MarshalUtil.unmarshal(jaxbUnmarshaller, Recipe.class, PATH_TO_FILE);
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		return recipeList;
	}

	@Override
	public boolean create() {
		return false;
	}

	@Override
	public boolean update() {
		return false;
	}

	@Override
	public boolean delete() {
		return false;
	}

	@Override
	public Recipe findById(Long id) {
		try {
			recipeList = MarshalUtil.unmarshal(jaxbUnmarshaller, Recipe.class, PATH_TO_FILE);
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
			recipeList = MarshalUtil.unmarshal(jaxbUnmarshaller, Recipe.class, PATH_TO_FILE);
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
