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

import com.epam.minsk.bean.Ingredient;
import com.epam.minsk.exception.MongoDBException;
import com.epam.minsk.rest.service.IngredientService;
/**
 * @author Dzina_Andreyeva
 * REST resource maps to the path given in annotation “/ings”.
 */
@Path("/ings")
public class IngredientsResource {
	@Context
	private UriInfo uriInfo;
	@Context
	private Request request;
	
	private IngredientService ingService;
	
	public IngredientsResource() {
		ingService = new IngredientService();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Ingredient> getIngList() {
		return ingService.getIngredientList();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Ingredient> getIngredientsAsHtml() {
		return ingService.getIngredientList();
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void createIngredient(@FormParam("ingname") String name,
			@Context HttpServletResponse servletResponse) throws IOException, MongoDBException {
		Ingredient ing = new Ingredient();
		ing.setName(name);
		ingService.createIngredient(ing);
		servletResponse.sendRedirect("./ings/");
	}

	@Path("{ingredient}")
	public IngredientResource getIngredient(@PathParam("ingredient") String id) {
		return new IngredientResource(uriInfo, request, id);
	}
}
