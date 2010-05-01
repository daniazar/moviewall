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
					<li><a href = "movies">Movies</a></li>
					<li><span class = "selected">Users</span></li>
					<li><a href = "../logout">Logout</a></li>
				</ul>
			</div>
			<div id = "content">
				<div class  = "search">
					<form>
						<input id = "searchField" type = "text" value = "${query}" name = "q"/><input type = "submit" value  = "Search User"/>
					</form>
				</div>
				<div class  = "searchResult">
					<table>
						
						<tr class = "first">
							<td class = "col">Username</td>
							<td class = "col">Name</td>
							<td class = "col">Surname</td>
							<td class = "col">E-mail</td>
							<td class = "col">Comments</td>
							<td class = "col">VIP</td>
							<td class = "col">Admin</td>
						</tr>
						
						<c:forEach items="${users}" var="user">
        				<tr class="elem">
          					<td class = "col">${user.username}</td>
          					<td class = "col">${user.name}</td>
          					<td class = "col">${user.surname}</td>
          					<td class = "col">${user.email}</td>
          					<td class = "col"> ${user.cantComments}</td>
							
			
          					<td class= "col"> <a href="<c:url value="userupgrade"><c:param name="code" value="${user.username}"/>
																<c:param name="admin" value="${user.isAdmin}"/>
																<c:param name="vip" value="${not user.isVip}"/>
							</c:url>">
								<c:out value="${user.isVip}" />
							</a> </td>
							
							          					<td class = "col"> 
							<a href="<c:url value="userupgrade"><c:param name="code" value="${user.username}"/>
																<c:param name="admin" value="${not user.isAdmin}"/>
																<c:param name="vip" value="${user.isVip}"/>
							</c:url>">
								<c:out value="${user.isAdmin}" />
							</a>
							</td>
							
							
          				</tr>
      					</c:forEach>
					
					
						</table>
				</div>
			</div>
		</div>
	</body>
</html>