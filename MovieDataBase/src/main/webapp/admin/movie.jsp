<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>

<html>
	<head>
		<link href="../css/adminStyle.css" rel="stylesheet" type="text/css" />
		<link href="../css/jquery.multiSelect.css" rel="stylesheet" type="text/css" />
		<script type = "text/javascript" src = "../js/jquery.js"></script>
		<script src="../js/jquery.bgiframe.min.js" type="text/javascript"></script>
		<script src="../js/jquery.multiSelect.js" type="text/javascript"></script>
	<link href="../js/calendar/calendar-blue.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../js/calendar/calendar.js"></script>
	<script type="text/javascript" src="../js/calendar/lang/calendar-en.js"></script>
	<script type="text/javascript" src="../js/calendar/calendar-setup.js"></script>
	<script type = "text/javascript" src = "../js/jquery.validate.js"></script>
		

		
		<script type = "text/javascript">
			$(document).ready(function(){
				$('#genres').multiSelect({ oneOrMoreSelected: '*' });

				$('.delete').each(function(){
					$(this).click(function(){ return confirm('Are you sure? This cannot be undone');});
				});
				
					$('#movieForm').validate({
				rules: {
						title:{
								 required: true,
								 minlength: 1
							 	},
						director :{
								required:true,
								minlength: 1
							 	},
						duration :{
									required: true,
							 	},
						release: {
									required: true,
							 	},
						imgurl: {
									required: true
							 	}
						
				},
				messages: {
					title: {
							required: "Provide a title for the movie.",
							minlength: "Title too short. Min 1 character."
							},
					director: {
								required: "Provide a director",
								minlength: "Director too short. Min 1 character."
							},
					duration: {
								required: "Provide a duration."
						},
					release: {
								required: "Provide a date of release."
						},	
					imgurl: {
								required: "Provide a url for the image."
					}
						
				}
				});
				
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
					<li><a href="movies">Movies</a></li>
					<li><a href="users">Users</a></li>
					<li><a href = "../logout">Logout</a></li>
				</ul>
			</div>
			<div id = "content">
				
				<div class = "box">

					<form method="POST" action="savemovie?id=${movie.id}" id = "movieForm">
						<dl>
							<dd><span class = "left"> id: </span> <i> ${movie.id}</i></dd>
							<dd><span class = "left"> Title: (*) </span> <input class = "fixedSize" type = "text" name = "title" value="${movie.title}"/></dd>
							<dd><span class = "left"> Director: (*) </span><input class = "fixedSize" type = "text" name = "director" value="${movie.director}"/></dd>
							<dd><span class = "left"> Duration(minutes): (*)</span><input class = "fixedSize" type = "text" name = "duration" value="${movie.duration}" /></dd>
							<br/>
							<dd><span class = "left"> Release: (*)</span><input class = "fixedSize" type = "text" name = "release"  value="${movie.release}" id="date" />

							<img src="../js/calendar/img.gif" id="calendar" style="cursor: pointer; border: 1px solid red; height: 14px; width: 20px;" title="Date selector"
      							onmouseover="this.style.background='red';" onmouseout="this.style.background=''" /></dd>
							<script type="text/javascript">
  							Calendar.setup(
  							{
 							    inputField :  "date",      // ID of the input field
      							ifFormat   : "%Y-%m-%d",   // the date format
      							button     : "calendar"    // ID of the button
    						}
							  );
							</script>
							


						
							<dd><span class = "left">Genres:</span>
							<select id = "genres" class = "fixedSize" size = "4" multiple = "multiple" name = "genres">
								<c:forEach items="${genres}" var="gen">
									<c:if test="${gen.movieGenreTag == true}">
										<option selected = "selected" value = "${gen.name}"> ${gen.name} </option>
									</c:if>
									<c:if test="${gen.movieGenreTag == false}">
										<option value = "${gen.name}"> ${gen.name} </option>
									</c:if>
       							</c:forEach>   					
							</select>		
							</dd>
							<dd>&nbsp;</dd>
							<dd>&nbsp;</dd>
							<dd><span class = "left">Synopsis:</span>
								<textarea name = "sinopsys" cols = "40" rows = "20"> ${movie.synopsis} </textarea>
							</dd>
							<dd>&nbsp;</dd>
							<dd>&nbsp;</dd>
							<dd><span class = "left"> Image URL: (*) </span><input class = "fixedSize" type = "text" name = "imgurl"  value="${movie.imgUrl}" /></dd>
							<dd>&nbsp;</dd>
							<dd><span class = "left"> (*) Required fields </span> </dd>
							<dd>&nbsp;</dd>
							<dd>&nbsp;</dd>
							<dd><span class = "right"><input type = "submit" value = "Save" onclick = "return confirm('Are you sure? This cannot be undone');"/> <input type = "button" value = "Cancel"/> </span></dd>
							
						</dl>
					</form>
					<br/>

				</div>
				
				
				<div class = "box searchResult">
					Comments: ${movie.cantComments}
					
					<c:if test="${movie.cantComments > 0}">
			
					<table class = "searchResult">
						<tr class = "first">
							<td class = "col">User</td>
							<td class = "col">Rating</td>
							<td class = "col">Date</td>
							<td class = "col">Content</td>
							<td class = "col">Action</td>
						</tr>
						
						<c:forEach items="${movie.comments}" var="c">
							<tr>
								<td>${c.userId}</td>
								<td>${c.raiting} </td>
								<td>${c.date} </td>
								<td>${c.content} </td>
								<td class = "center"><a href = "../deletecomment?id=${c.id}&retURL=movie?id=${c.movieId}" onclick = "return confirm('Are you sure? This cannot be undone');" > Delete </a></td>
								
							</tr>
						</c:forEach>		
					</table>
					</c:if>
				
				</div>
				
				<div class = "box">
				<form method="POST" action="deletemovie?id=${movie.id}&retURL=movies">
					<input type = "submit" value = "Delete Movie" onclick = "return confirm('Are you sure? This cannot be undone');">
				</form>
				
				</div>
			
			</div>
		</div>
	</body>
</html>