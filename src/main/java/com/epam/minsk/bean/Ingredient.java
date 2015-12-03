package com.epam.minsk.bean;

/**
 * @author Dzina_Andreyeva
 * An entity which describes Ingredient for Recipe
 */
public class Ingredient extends ComponentEntity implements IComponentProduct {

	/** Measure of unit for ingredient. Ex: GRAMM, ML, etc. */
	private MeasureUnit measureUnit;
	/** The quantity of ingredient for current moment */
	private double quantity;
	
	public Ingredient(Long id) {
		super(id);
	}
	
	public MeasureUnit getMesureUnit() {
		return measureUnit;
	}

	public void setMesureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
}
