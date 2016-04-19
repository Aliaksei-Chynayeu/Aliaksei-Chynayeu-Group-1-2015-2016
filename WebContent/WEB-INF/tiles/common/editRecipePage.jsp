<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="center">

	<form method="post" action="/RecipeChooserWebProject/editRecipe">
		<input type="HIDDEN" name="recipeId" value="${recipe.recipeId}">
		<b></b>
		<br />
		<input type="text" name="id" size="20" disabled="disabled" 
		value="${recipe.recipeId}">
		<br />
		<br />
		<b>Name</b>
		<br />
		<input type="text" name="name" size="30" maxlength="50" 
		required="required" value="${recipe.name}">
		<br />
		<b>Comment</b>
		<br />
		<input type="text" name="comment" size="30" maxlength="100" 
		required="required" value="${recipe.comment}">
		<br />
		<b>Rating</b>
		<br />
		<input type="text" name="rating" size="30" maxlength="50" 
		required="required" value="${recipe.rating}">
		<br />
		<b>Description</b>
		<br />
		<input type="text" name="description" size="30" maxlength="50" 
		required="required" value="${recipe.description}">
		<br />
		<b>Categories</b>
		<br />
		<select name="categoriesNames" multiple="multiple">
			<c:forEach var="category" items="${recipe.categoryList}">
    			<option selected="selected" value="${category}">
    				<c:out value="${category}" />
    			</option>
			</c:forEach>
		</select>
		<br />
		<br />
		<input type="submit" id="view" value="Update">
	</form>
	
	<form method="post" action="/RecipeChooserWebProject/deleteRecipe">
		<input type="HIDDEN" name="recipeId" value="${recipe.recipeId}">
		<input type="submit" id="view" value="Delete">
		<br />
	</form>
</div>