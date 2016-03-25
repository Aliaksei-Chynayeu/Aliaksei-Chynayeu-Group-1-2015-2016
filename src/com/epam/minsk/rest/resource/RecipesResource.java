package com.epam.minsk.rest.resource;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.epam.minsk.bean.Recipe;
import com.epam.minsk.exception.MongoDBException;
import com.epam.minsk.rest.service.RecipeService;

/**
 * @author Dzina_Andreyeva
 * REST resource maps to the path given in annotation “/recipes”.
 */
@Path("/recipes")
public class RecipesResource {

	@Context
	private UriInfo uriInfo;
	@Context
	private Request request;
	
	private RecipeService recipeService;
	
	public RecipesResource() {
		recipeService = new RecipeService();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Recipe> getRecipeList() {
		return recipeService.getRecipeList();
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Recipe> getRecipeListAsHtml() {
		return recipeService.getRecipeList();
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void createRecipe(@FormParam("recipename") String name,
			@Context HttpServletResponse servletResponse) throws IOException, MongoDBException {
		Recipe recipe = new Recipe();
		recipe.setName(name);
		recipeService.createRecipe(recipe);
		servletResponse.sendRedirect("./recipes/");
	}

	@Path("{recipe}")
	public RecipeResource getRecipe(@PathParam("recipe") String id) {
		return new RecipeResource(uriInfo, request, id);
	}
	
	
}
