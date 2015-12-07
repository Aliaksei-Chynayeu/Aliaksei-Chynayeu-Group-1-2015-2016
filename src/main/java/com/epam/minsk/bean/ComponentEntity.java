package com.epam.minsk.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/** A Basic class for describing entities */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class ComponentEntity {
	
	/** id for component */
	@XmlAttribute
	private Long id;
	/** name of component */
	private String name;
	/** additional information about component */
	private String comment;
	/** how popular or necessary component from 0 (unpopular/unnecessary) to 5 (very popular/necessary) */
	private int rating;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
