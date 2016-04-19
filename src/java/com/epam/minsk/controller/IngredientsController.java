package com.epam.minsk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.exception.DAOException;
import com.epam.minsk.service.IngredientService;
import com.epam.minsk.service.RecipeService;
import com.epam.minsk.util.RequestParameters;

@Controller
public class IngredientsController {
	
	private static final String ADD_ING = "ing/addIng";
	private static final String EDIT_ING = "ing/editIng";
	private static final String HOME = "home";
	
	@Autowired
	private IngredientService ingService;
	@Autowired
	private RecipeService recipeService;
	
	
	@RequestMapping(value = "/addIng")
	public String addIngredient(HttpServletRequest req, Model model, Ingredient ing) throws DAOException {
		String url = ADD_ING;
		HttpSession session = req.getSession(false);
		
		if (ing.getName() != null) {
			ingService.createIng(ing);
			url = HOME;
		}
		setAttrsToModel(model);
		session.setAttribute(RequestParameters.ACTION, "addIng");
		return url;
	}
	
	@RequestMapping(value = "/editIng")
	public String editIng(HttpServletRequest req, Model model, Ingredient ing) throws DAOException {
		String url = HOME;
			
		if (ing.getName() != null) {
			ingService.updateIng(ing);
		}
		setAttrsToModel(model);
		return url;
	}
	
	@RequestMapping(value = "/editIng/{id}")
	public String showDetailsIng(HttpServletRequest req, Model model, @PathVariable Integer id) throws DAOException {
		String url = EDIT_ING;
		Ingredient ing = ingService.getIngredient(id);
		model.addAttribute(RequestParameters.INGREDIENT, ing);
		setAttrsToModel(model);
		return url;
	}
	
	@RequestMapping(value = "/deleteIng")
	public String deleteIng(HttpServletRequest req, Model model) throws DAOException {
		String url = HOME;
		String id = req.getParameter(RequestParameters.INGREDIENT_ID);
		Ingredient ing = ingService.getIngredient(Integer.valueOf(id));
		if (ing != null) {
			ingService.deleteIngById(Integer.valueOf(id));
		}
		setAttrsToModel(model);
		return url;
	}
	
	private void setAttrsToModel(Model model) throws DAOException {
		model.addAttribute(RequestParameters.INGREDIENT_LIST, ingService.getIngList());
		model.addAttribute(RequestParameters.RECIPE_LIST, recipeService.getRecipeList());
	}

}
