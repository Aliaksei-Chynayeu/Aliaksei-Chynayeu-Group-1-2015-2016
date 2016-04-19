<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id='list'>
	<c:forEach var="ing" items="${ingList}">
		<tr>
			<td class="list">
				<a href="/RecipeChooserWebProject/editIng/${ing.ingredientId}">${ing.name}</a>
			</td>
		</tr>
	</c:forEach>
		<tr>
			<td class="list"></td>
			<td class="list"><a href="/RecipeChooserWebProject/addIng">Add ingredient</a></td>
		</tr>	
</table>
				
<table class="right" id='list'>
	<c:forEach var="recipe" items="${recipeList}">
		<tr>
			<td class="list">
				<a href="/RecipeChooserWebProject/editRecipe/${recipe.recipeId}">${recipe.name}</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td class="list"></td>
		<td class="list"><a href="/RecipeChooserWebProject/addRecipe">Add recipe</a></td>
	</tr>
</table>