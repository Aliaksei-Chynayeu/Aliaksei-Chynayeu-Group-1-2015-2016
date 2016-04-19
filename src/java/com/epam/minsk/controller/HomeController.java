package com.epam.minsk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.bean.Recipe;
import com.epam.minsk.exception.DAOException;
import com.epam.minsk.service.IngredientService;
import com.epam.minsk.service.RecipeService;
import com.epam.minsk.util.RequestParameters;

@Controller
public class HomeController {
	private static final String HOME = "home";
	
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private IngredientService ingService;
	
	@RequestMapping(value = "/home")
	public String home(HttpServletRequest req, Model model) throws DAOException {
		String url = HOME;
		setAttrsToModel(model);
		return url;
	}
	
	private void setAttrsToModel(Model model) throws DAOException {
		List<Recipe> recipeList = recipeService.getRecipeList();
		model.addAttribute(RequestParameters.RECIPE_LIST, recipeList);
		List<Ingredient> ingList = ingService.getIngList();
		model.addAttribute(RequestParameters.INGREDIENT_LIST, ingList);
	}
}
