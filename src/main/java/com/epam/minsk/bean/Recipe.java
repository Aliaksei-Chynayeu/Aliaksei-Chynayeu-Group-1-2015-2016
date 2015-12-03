package com.epam.minsk.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dzina_Andreyeva
 * An entity which describes Recipe
 */
public class Recipe extends ComponentEntity {
	
	/** List of components which need for preparing */
	private List<IComponentProduct> componentList = new ArrayList<IComponentProduct>();
	/** The description of cooking */
	private String description;
	/** List of categories which recipe belongs to */
	private List<Category> categoryList = new ArrayList<Category>();
		
	public Recipe(Long id) {
		super(id);
	}
		
	public List<IComponentProduct> getComponentList() {
		return componentList;
	}

	public void setComponentList(List<IComponentProduct> componentList) {
		this.componentList = componentList;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
}
