<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="center">

	<form method="post" action="/RecipeChooserWebProject/editIng">
		<input type="HIDDEN" name="ingredientId" value="${ing.ingredientId}">
		<b></b>
		<br />
		<input type="text" name="id" size="20" disabled="disabled" 
		value="${ing.ingredientId}">
		<br />
		<br />
		<b>Name</b>
		<br />
		<input type="text" name="name" size="30" maxlength="50" 
		required="required" value="${ing.name}">
		<br />
		<b>Comment</b>
		<br />
		<input type="text" name="comment" size="30" maxlength="100" 
		required="required" value="${ing.comment}">
		<br />
		<b>Rating</b>
		<br />
		<input type="text" name="rating" size="30" maxlength="50" 
		required="required" value="${ing.rating}">
		<br />
		<b>Quantity</b>
		<br />
		<input type="text" name="quantity" size="30" maxlength="50" 
		required="required" value="${ing.quantity}">
		<br />
		<br />
		<input type="submit" id="view" value="Update">
		<br />
	</form>
	<form method="post" action="/RecipeChooserWebProject/deleteIng">
		<input type="HIDDEN" name="ingredientId" value="${ing.ingredientId}">
		<input type="submit" id="view" value="Delete">
		<br />
	</form>
</div>