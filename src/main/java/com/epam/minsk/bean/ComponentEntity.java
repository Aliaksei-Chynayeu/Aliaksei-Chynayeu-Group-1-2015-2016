package com.epam.minsk.bean;

/** A Basic class for describing entities */
public abstract class ComponentEntity {
	
	/** id for component */
	private Long id;
	/** name of component */
	private String name;
	/** additional information about component */
	private String comment;
	/** how popular or necessary component from 0 (unpopular/unnecessary) to 5 (very popular/necessary) */
	private int rating;
	
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
	
	
}
