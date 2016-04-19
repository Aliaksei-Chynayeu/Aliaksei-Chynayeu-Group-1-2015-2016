package com.epam.minsk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.minsk.bean.Category;
import com.epam.minsk.bean.Recipe;
import com.epam.minsk.exception.DAOException;
import com.epam.minsk.service.IngredientService;
import com.epam.minsk.service.RecipeService;
import com.epam.minsk.util.RequestParameters;

@Controller
public class RecipesController {
	
	private static final String ADD_RECIPE = "recipe/addRecipe";
	private static final String EDIT_RECIPE = "recipe/editRecipe";
	private static final String HOME = "home";
	
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private IngredientService ingService;
	
	@RequestMapping(value = "/addRecipe")
	public String addRecipe(HttpServletRequest req, Model model, Recipe recipe) throws DAOException {
		String url = ADD_RECIPE;
		HttpSession session = req.getSession(false);
		
		if (recipe.getName() != null) {
			recipe.setCategoryList(getCategoriesList(req));
			recipeService.createRecipe(recipe);
			url = HOME;
		}
		setAttrsToModel(model);
		session.setAttribute(RequestParameters.ACTION, "addRecipe");
		return url;
	}
	
	@RequestMapping(value = "/editRecipe")
	public String editRecipe(HttpServletRequest req, Model model, Recipe recipe) throws DAOException {
		String url = HOME;
			
		if (recipe.getName() != null) {
			recipeService.updateRecipe(recipe);
		}
		setAttrsToModel(model);
		return url;
	}
	
	@RequestMapping(value = "/editRecipe/{id}")
	public String showDetailsRecipe(HttpServletRequest req, Model model, @PathVariable Integer id) throws DAOException {
		String url = EDIT_RECIPE;
		Recipe recipe = recipeService.getRecipe(id);
		model.addAttribute(RequestParameters.RECIPE, recipe);
		setAttrsToModel(model);
		return url;
	}
	
	@RequestMapping(value = "/deleteRecipe")
	public String deleteRecipe(HttpServletRequest req, Model model) throws DAOException {
		String url = HOME;
		String id = req.getParameter(RequestParameters.RECIPE_ID);
		Recipe recipe = recipeService.getRecipe(Integer.valueOf(id));
		if (recipe != null) {
			recipeService.deleteRecipeById(Integer.valueOf(id));
		}
		setAttrsToModel(model);
		return url;
	}

	private void setAttrsToModel(Model model) throws DAOException {
		model.addAttribute(RequestParameters.INGREDIENT_LIST, ingService.getIngList());
		model.addAttribute(RequestParameters.RECIPE_LIST, recipeService.getRecipeList());
	}
	
	private List<Category> getCategoriesList(HttpServletRequest req) {
		List<Category> list = new ArrayList<Category>();
		String[] names = req.getParameterValues(RequestParameters.CATEGORIES_NAMES);
		for (String name : names) {
			list.add(Category.valueOf(name));
		}
		return list;
	}
}
