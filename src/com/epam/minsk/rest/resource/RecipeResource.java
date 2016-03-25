package com.epam.minsk.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.epam.minsk.bean.Recipe;
import com.epam.minsk.exception.MongoDBException;
import com.epam.minsk.rest.service.RecipeService;

/**
 * @author Dzina_Andreyeva
 * RESTful web service resource file for Ingredient with GET, PUT and DELETE operations. 
 */
public class RecipeResource {
	@Context
	private UriInfo uriInfo;
	@Context
    private Request request;
	private String id;
	
	private RecipeService recipeService;
	
	public RecipeResource(UriInfo uriInfo, Request request, String id) {
	    this.uriInfo = uriInfo;
	    this.request = request;
	    this.id = id;
	    recipeService = new RecipeService();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Recipe getRecipe() {
		Recipe recipe = recipeService.getRecipe(id);
	    if(recipe==null)
	    	throw new RuntimeException("Get: Recipe with " + id +  " not found");
	    return recipe;
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Recipe getRecipeHTML() {
		Recipe recipe = recipeService.getRecipe(id);
	    if(recipe==null)
	    	throw new RuntimeException("Get: Recipe with " + id +  " not found");
	    return recipe;
	}
	
	@DELETE
	public void deleteRecipe() {
		try {
			recipeService.deleteRecipeById(id);
		} catch (MongoDBException e) {
			throw new RuntimeException("Delete: Recipe with " + id +  " not found");
		}
	  }
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	  public Response updateRecipe(JAXBElement<Recipe> newRecipe) {
	    Recipe recipe = newRecipe.getValue();
	    return buildResponse(recipe);
	}
	
	private Response buildResponse(Recipe newRecipe) {
	    Response res;
	    if(recipeService.getRecipe(newRecipe.getId()) == null) {
	      res = Response.noContent().build();
	    } else {
	      res = Response.created(uriInfo.getAbsolutePath()).build();
	    }
	    try {
	    	recipeService.updateRecipe(newRecipe);
		} catch (MongoDBException e) {
			throw new RuntimeException("Update: Ingredient with " + id +  " not found");
		}
	    return res;
	  }
}
