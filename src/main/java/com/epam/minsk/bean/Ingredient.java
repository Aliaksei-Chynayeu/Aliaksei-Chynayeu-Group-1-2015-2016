package com.epam.minsk.bean;

public class Ingredient extends ComponentEntity implements IProductComponent {

	
	private MeasureUnit measureUnit;
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
