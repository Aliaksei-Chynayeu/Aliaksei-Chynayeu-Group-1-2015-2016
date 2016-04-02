package com.epam.minsk.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Dzina_Andreyeva An entity which describes Ingredient
 */
@Entity
@Table(name = "Ingredients")
public class Ingredient extends ComponentEntity {

	/** id for component */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ingredientId", unique = true, nullable = false)
	private int ingredientId;
	/** Measure of unit for ingredient. Ex: GRAMM, ML, etc. */
	@Enumerated(EnumType.ORDINAL)
	private MeasureUnit measureUnit;
	/** The quantity of ingredient for current moment */
	private Double quantity;

	public MeasureUnit getMesureUnit() {
		return measureUnit;
	}

	public void setMesureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}
}
