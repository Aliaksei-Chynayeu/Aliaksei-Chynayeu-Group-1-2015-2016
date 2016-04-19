<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<link rel='stylesheet' href='/RecipeChooserWebProject/resources/css/style.css' type='text/css' />
  	<title><tiles:getAsString name="title"/></title>
  </head>
 	<body>
		<table>
  			<tr>
    			<td>
     				<tiles:insertAttribute name="header" />
    			</td>
  			</tr>
  			<tr>
    			<td>
      				<tiles:insertAttribute name="body" />
    			</td>
  			</tr>
  			<tr>
    			<td>
      				<tiles:insertAttribute name="footer" />
   				 </td>
  			</tr>
		</table>
  </body>
</html>