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

import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.exception.MongoDBException;
import com.epam.minsk.rest.service.IngredientService;

/**
 * @author Dzina_Andreyeva
 * RESTful web service resource file for Ingredient with GET, PUT and DELETE operations. 
 */
public class IngredientResource {
	@Context
	private UriInfo uriInfo;
	@Context
    private Request request;
	private String id;
	
	private IngredientService ingService;
	
	public IngredientResource(UriInfo uriInfo, Request request, String id) {
	    this.uriInfo = uriInfo;
	    this.request = request;
	    this.id = id;
	    ingService = new IngredientService();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Ingredient getIngredient() {
		Ingredient ing = ingService.getIngredient(id);
	    if(ing==null)
	    	throw new RuntimeException("Get: Ingredient with " + id +  " not found");
	    return ing;
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Ingredient getIngredientHTML() {
		Ingredient ing = ingService.getIngredient(id);
		 if(ing==null)
		    	throw new RuntimeException("Get: Ingredient with " + id +  " not found");
		    return ing;
	}
	
	@DELETE
	public void deleteIngredient() {
		try {
			ingService.deleteIngredientById(id);
		} catch (MongoDBException e) {
			throw new RuntimeException("Delete: Ingredient with " + id +  " not found");
		}
	  }
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	  public Response updateIngredient(JAXBElement<Ingredient> ing) {
	    Ingredient ingredient = ing.getValue();
	    return buildResponse(ingredient);
	}
	
	private Response buildResponse(Ingredient newIng) {
	    Response res;
	    if(ingService.getIngredient(newIng.getId()) == null) {
	      res = Response.noContent().build();
	    } else {
	      res = Response.created(uriInfo.getAbsolutePath()).build();
	    }
	    try {
			ingService.updateIngredient(newIng);
		} catch (MongoDBException e) {
			throw new RuntimeException("Update: Ingredient with " + id +  " not found");
		}
	    return res;
	  }
}

