package com.epam.minsk.util;

import java.util.List;

import com.epam.minsk.bean.ComponentEntity;
import com.epam.minsk.bean.IComponentProduct;
import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.Recipe;

public class CookUtil {
	
	public static boolean toCook(Recipe recipe, List<IComponentProduct> componentList) {
		List<IComponentProduct> compNecessaryList = recipe.getIngredients();
		boolean isEnough = true;
		for (IComponentProduct compNes : compNecessaryList) {
			IComponentProduct existComponent = getComponentIfExist(compNes, componentList);
			if (existComponent != null) {
				isEnough = isEnough && isIngredientEnough ((Ingredient) compNes, (Ingredient)existComponent); 
			} else {
				isEnough = false;
			}
		}
		return isEnough;
	}
	
	private static IComponentProduct getComponentIfExist(IComponentProduct compNes, List<IComponentProduct> components) {
		for (IComponentProduct component : components) {
			if (((ComponentEntity)compNes).getId() == ((ComponentEntity)component).getId()) {
				return component;
			}
		}
		return null;
	}
	
	private static boolean isIngredientEnough(IComponentProduct ingredientNes, IComponentProduct ingredient) {
		if (ingredientNes instanceof Ingredient 
				&& ((Ingredient)ingredientNes).getQuantity() <= ((Ingredient)ingredient).getQuantity()) {
			return true;
		} 
		else {
			return false;
		}
	}
	
}
