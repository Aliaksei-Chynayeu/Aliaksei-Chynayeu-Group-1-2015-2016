package com.epam.minsk.dao.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.epam.minsk.bean.ComponentEntity;
import com.epam.minsk.bean.IComponentProduct;
import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.dao.IComponentProductDAO;
import com.epam.minsk.xml.MarshalUtil;
import com.epam.minsk.xml.WrapperXML;

/** Contains implementation for Ingredient DAO */
public class IngredientDAOXML  implements IComponentProductDAO {
	
	/** path to XML file where data are saved */
	private static final String PATH_TO_FILE = "ingredients.xml";
	private List<IComponentProduct> ingredientList;
	private static Unmarshaller jaxbUnmarshaller;
	/** Log4j */
	private static final Logger LOG = Logger.getLogger(IngredientDAOXML.class);
	
	static {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Ingredient.class, WrapperXML.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			} catch (JAXBException e) {
				LOG.error("JAXBException");
			}		
	}

	@Override
	public List<IComponentProduct> findAll() {
		try {
			ingredientList = MarshalUtil.unmarshal(jaxbUnmarshaller, IComponentProduct.class, PATH_TO_FILE);
		} catch (JAXBException e) {
			LOG.error("JAXBException");
		}
		return ingredientList;
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
	public IComponentProduct findById(Long id) {
		try {
			ingredientList = MarshalUtil.unmarshal(jaxbUnmarshaller, IComponentProduct.class, PATH_TO_FILE);
			for (IComponentProduct ingredientEntity : ingredientList) {
				if (((ComponentEntity)ingredientEntity).getId() == id) {
					return ingredientEntity;
				}
			}
		} catch (JAXBException e) {
			LOG.error("JAXBException");
		}
		return null;
	}

	@Override
	public List<IComponentProduct> findByName(String name) {
		List<IComponentProduct> list = new ArrayList<IComponentProduct>();
		try {
			ingredientList = MarshalUtil.unmarshal(jaxbUnmarshaller, IComponentProduct.class, PATH_TO_FILE);
			for (IComponentProduct ingredientEntity : ingredientList) {
				if (((ComponentEntity)ingredientEntity).getName() == name) {
					list.add(ingredientEntity);
				}
			}
		} catch (JAXBException e) {
			LOG.error("JAXBException");
		}
		return list;
	}

}
