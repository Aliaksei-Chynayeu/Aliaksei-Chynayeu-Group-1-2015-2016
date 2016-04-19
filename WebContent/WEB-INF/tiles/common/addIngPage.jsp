<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="center">

	<form method="post" action="/RecipeChooserWebProject/addIng">
		<br />
		<b>Name</b>
		<br />
		<input type="text" name="name" size="30" maxlength="50" 
		required="required" value="">
		<br />
		<b>Comment</b>
		<br />
		<input type="text" name="comment" size="30" maxlength="100" 
		required="required" value="">
		<br />
		<b>Rating</b>
		<br />
		<input type="text" name="rating" size="30" maxlength="50" 
		required="required" value="">
		<br />
		<b>MeasureUnit</b>
		<br />
		<input type="text" name="measureUnit" size="30" maxlength="50" 
		required="required" value="">
		<br />
		<b>Quantity</b>
		<br />
		<input type="text" name="quantity" size="30" maxlength="50" 
		required="required" value="">
		<br />
		<br />
		<input type="submit" id="view" value="Add">
	</form>
</div>