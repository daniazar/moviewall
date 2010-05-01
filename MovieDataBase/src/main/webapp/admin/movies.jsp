<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>

<html>
	<head>
		<link href="../css/adminStyle.css" rel="stylesheet" type="text/css" />
		<script type = "text/javascript" src = "../js/jquery.js"></script>
		<script>
			$(document).ready(function(){
				$('#searchField').select();
			});
		</script>
	</head>
	<body>
	
		<div id = "wrapper">
			<div id = "header" class = "blue">
				Admin Section
			</div>
			<div id = "leftmenu" class = "blue">
				<ul>
					<li><a href = "home">Admin Home</a></li>
					<li><a href = "../main">Main Home</a></li>
					<li><span class = "selected">Movies</span></li>
					<li><a href = "users">Users</a></li>
					<li><a href = "../logout">Logout</a></li>
				</ul>
			</div>
			<div id = "content">
				<div class  = "search">
					<form>
						<input id = "searchField" type = "text" value = "${query}" name = "q"/><input type = "submit" value  = "Search Movie"/>
					</form>
				</div>
				<div class  = "searchResult">
					<table>
						<tr class = "first">
							<td class = "col">Title</td>
							<td class = "col">Director</td>
							<td class = "col">Release&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td class = "col">Rating</td>
							<td class = "col">Comments</td>
							<td class = "col">Action</td>
						</tr>
						
						 <c:forEach items="${movies}" var="m">
        				<tr class="elem">
          					<td class = "col">${m.title}</td>			
          					<td class = "col">${m.director}</td>
          					<td class = "col">${m.release}</td>
							<td class = "col">${m.rating}</td>
							<td class= "col"> ${m.cantComments}</td>
							<td class = "col"> <a href = "deletemovie?id=${m.id}&retURL=movies" onclick = "return confirm('Are you sure? This cannot be undone');" >Delete</a>&nbsp;<a href = "movie?id=${m.id}">Edit</a> </td>
          				
          				</tr>
      					</c:forEach>  
						
						
					</table>
				</div>
				
				<dd>&nbsp;</dd>
				<form method="POST" action="newmovie">
					<input type = "submit" value = "Add New Movie"/> 
				</form>
			</div>
		</div>
	</body>
</html>