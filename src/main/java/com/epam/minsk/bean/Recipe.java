package com.epam.minsk.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlAccessType;

/**
 * @author Dzina_Andreyeva
 * An entity which describes Recipe
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(ComponentEntity.class)
public class Recipe extends ComponentEntity {
	
	/** The description of cooking */
	private String description;
	/** List of categories which recipe belongs to */
	@XmlElementWrapper(name="categories")
    @XmlElement(name="category")
	private List<Category> categoryList = new ArrayList<Category>();
	/** List of components which need for preparing */
	@XmlElementWrapper(name="ingredients")
	@XmlElementRefs({@XmlElementRef(type=Ingredient.class, name="ingredient")})
	private List<IComponentProduct> ingredients = new ArrayList<IComponentProduct>();
		
	public String getDescription() {
		return description;
	}
	
	public List<IComponentProduct> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IComponentProduct> ingredients) {
		this.ingredients = ingredients;
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
	
	public List<String> getIngredientsId() {
		List<String> idList = new ArrayList<String>();
		for (IComponentProduct ingredient : ingredients) {
			idList.add(((Ingredient)ingredient).getId());
		}
		return idList;
	}
	
}
