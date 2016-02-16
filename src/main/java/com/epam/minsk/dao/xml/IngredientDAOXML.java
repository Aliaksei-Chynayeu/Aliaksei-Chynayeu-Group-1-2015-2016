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
				LOG.error("JAXBException" + e);
			}		
	}

	@Override
	public List<IComponentProduct> findAll() {
		try {
			ingredientList = MarshalUtil.unmarshal(jaxbUnmarshaller, IComponentProduct.class, PATH_TO_FILE);
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		return ingredientList;
	}

	@Override
	public boolean create(IComponentProduct ingredient) {
		return false;
	}

	@Override
	public boolean update(IComponentProduct ingredient) {
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		return false;
	}

	@Override
	public IComponentProduct findById(String id) {
		try {
			ingredientList = MarshalUtil.unmarshal(jaxbUnmarshaller, IComponentProduct.class, PATH_TO_FILE);
			for (IComponentProduct ingredientEntity : ingredientList) {
				if (((ComponentEntity)ingredientEntity).getId().equals(id)) {
					return ingredientEntity;
				}
			}
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		return null;
	}

	@Override
	public IComponentProduct findByName(String name) {
		try {
			ingredientList = MarshalUtil.unmarshal(jaxbUnmarshaller, IComponentProduct.class, PATH_TO_FILE);
			for (IComponentProduct ingredientEntity : ingredientList) {
				if (((ComponentEntity)ingredientEntity).getName().equals(name)) {
					return ingredientEntity;
				}
			}
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		return null;
	}

	@Override
	public boolean deleteByName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

}
