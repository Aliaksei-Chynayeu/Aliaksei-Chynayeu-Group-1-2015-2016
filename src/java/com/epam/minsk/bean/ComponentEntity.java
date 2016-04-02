package com.epam.minsk.bean;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Dzina_Andreyeva An entity which describes some abstract component
 */
@MappedSuperclass
public abstract class ComponentEntity {

	/** name of component */
	@Column(name = "Name", unique = true, nullable = false)
	private String name;
	/** additional information about component */
	@Column(name = "Comment")
	private String comment;
	/**
	 * how popular or necessary component from 0 (unpopular/unnecessary) to 5
	 * (very popular/necessary)
	 */
	@Column(name = "Rating")
	private int rating;

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
