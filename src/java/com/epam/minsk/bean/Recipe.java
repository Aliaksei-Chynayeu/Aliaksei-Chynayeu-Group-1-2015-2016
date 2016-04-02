package com.epam.minsk.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Dzina_Andreyeva An entity which describes Recipe
 */
@Entity
@Table(name = "Recipes")
public class Recipe extends ComponentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "recipeId", unique = true, nullable = false)
	private int recipeId;
	private String description;
	/** List of categories which recipe belongs to */
	@ElementCollection(targetClass = Category.class)
	@Enumerated(EnumType.ORDINAL)
	@CollectionTable(name = "Recipes_Categories")
	@Column(name = "Category")
	private Collection<Category> categoryList = new ArrayList<Category>();
	/** List of components which need for preparing */
	@OneToMany
	private Set<Ingredient> ingredients = new HashSet<Ingredient>();

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getDescription() {
		return description;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Collection<Category> categoryList) {
		this.categoryList = categoryList;
	}
}
