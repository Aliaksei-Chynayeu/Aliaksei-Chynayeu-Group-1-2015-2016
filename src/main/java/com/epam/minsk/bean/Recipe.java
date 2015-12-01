package com.epam.minsk.bean;

import java.util.ArrayList;
import java.util.List;

public class Recipe extends ComponentEntity {
	
	private List<Ingredient> componentList = new ArrayList<Ingredient>();
	private String description;
		
	public Recipe(Long id) {
		super(id);
	}
		
	public List<Ingredient> getComponentList() {
		return componentList;
	}

	public void setComponentList(List<Ingredient> componentList) {
		this.componentList = componentList;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
