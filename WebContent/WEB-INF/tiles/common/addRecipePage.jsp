<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="center">

	<form method="post" action="/RecipeChooserWebProject/addRecipe">
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
		<b>Description</b>
		<br />
		<input type="text" name="description" size="30" maxlength="50" 
		required="required" value="">
		<br />
		<b>Categories</b>
		<br />
		<select name="categoriesNames" multiple="multiple" size="6">
   			<option value="DINNER">
    			DINNER
    		</option>
    		<option value="LUNCH">
    			LUNCH
    		</option>
    		<option value="BREAKFAST">
    			BREAKFAST
    		</option>
    		<option value="DRINK">
    			DRINK
    		</option>
    		<option value="SWEETS">
    			SWEETS
    		</option>
    		<option value="BAKE">
    			BAKE
    		</option>
		</select>
		<br />
		<br />
		<input type="submit" id="view" value="Add">
	</form>
</div>

