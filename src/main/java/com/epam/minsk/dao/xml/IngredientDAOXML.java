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
	private static Marshaller jaxbMarshaller;
	private static URL url;
	private Class clazz = Ingredient.class;
	/** Log4j */
	private static final Logger LOG = Logger.getLogger(IngredientDAOXML.class);
	
	static {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Ingredient.class, WrapperXML.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			url = IngredientDAOXML.class.getClassLoader().getResource(PATH_TO_FILE);
			} catch (JAXBException e) {
				LOG.error("JAXBException" + e);
			}		
	}

	@Override
	public List<IComponentProduct> findAll() {
		try {
			ingredientList = MarshalUtil.unmarshal(jaxbUnmarshaller, IComponentProduct.class, url.getPath());
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		return ingredientList;
	}

	@Override
	public boolean add(IComponentProduct ingredient) {
		List<IComponentProduct> list = findAll();
		list.add(ingredient);
		try {
			MarshalUtil.marshal(jaxbMarshaller,list, url.getPath(), ingredient.getClass());
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		
		return false;
	}

	@Override
	public boolean update(IComponentProduct component) {
		Ingredient ingredientNew = (Ingredient) component;
		delete(ingredientNew.getId());
		List<IComponentProduct> list = findAll();
		list.add(ingredientNew);
		try {
			MarshalUtil.marshal(jaxbMarshaller, list, url.getPath(), ingredientNew.getClass());
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		List<IComponentProduct> list = findAll();
		for(Iterator<IComponentProduct> it = list.iterator(); it.hasNext(); ) {
		IComponentProduct it1 = it.next();
		if(((ComponentEntity)it1).getId() == id) {
				it.remove();
				break;
			}
		}
		try {
			MarshalUtil.marshal(jaxbMarshaller, list, url.getPath(), clazz);
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		} 
		return false;
	}

	@Override
	public IComponentProduct findById(Long id) {
		try {
			ingredientList = MarshalUtil.unmarshal(jaxbUnmarshaller, IComponentProduct.class, url.getPath());
			for (IComponentProduct ingredientEntity : ingredientList) {
				if (((ComponentEntity)ingredientEntity).getId() == id) {
					return ingredientEntity;
				}
			}
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		return null;
	}

	@Override
	public List<IComponentProduct> findByName(String name) {
		List<IComponentProduct> list = new ArrayList<IComponentProduct>();
		try {
			ingredientList = MarshalUtil.unmarshal(jaxbUnmarshaller, IComponentProduct.class, url.getPath());
			for (IComponentProduct ingredientEntity : ingredientList) {
				if (((ComponentEntity)ingredientEntity).getName() == name) {
					list.add(ingredientEntity);
				}
			}
		} catch (JAXBException e) {
			LOG.error("JAXBException" + e);
		}
		return list;
	}

}
