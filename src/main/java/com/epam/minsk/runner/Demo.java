package com.epam.minsk.runner;

import java.util.List;

import com.epam.minsk.bean.ComponentEntity;
import com.epam.minsk.bean.IComponentProduct;
import com.epam.minsk.bean.Recipe;

public class Demo {
	
	public String toCook(Recipe recipe, List<IComponentProduct> ingredients) {
		List<IComponentProduct> ingrNecessaryList = recipe.getIngredients();
		String s = "";
		int n = 0;
		for (IComponentProduct ingrNes : ingrNecessaryList) {
			for (IComponentProduct ingr : ingredients) {
				if (((ComponentEntity)ingrNes).getId() == ((ComponentEntity)ingr).getId()) {
					n++;
				}
			} 
		}
		if (ingrNecessaryList.size() == n) {
			s = "enough";
		} else 
		{
			s = "not enough";
		}
		return s;
	}

}
