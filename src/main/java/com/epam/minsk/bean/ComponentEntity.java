package com.epam.minsk.bean;

import java.util.ArrayList;
import java.util.List;

public abstract class ComponentEntity {
	
	private Long id;
	private String name;
	private String comment;
	private int rating;
	private List<Category> categoryList = new ArrayList<Category>();

	public ComponentEntity(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getRating() {
		return rating;
		
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
}
