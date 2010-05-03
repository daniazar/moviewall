<%@ include file="header.jsp" %>
			<div id = "content">
				<div class  = "search">
					<form method="POST">
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
						
						 <c:forEach items="${movieList}" var="m">
        				<tr class="elem">
          					<td class = "col">${m.title}</td>			
          					<td class = "col">${m.director}</td>
          					<td class = "col">${m.release}</td>
							<td class = "col">
							<c:if test="${m.rating <= 0} ">not rated</c:if> 
							<c:if test="${m.rating == 3.0} ">${m.rating}"</c:if>
							${m.rating} 
							</td>
							<td class= "col"> ${m.cantComments}</td>
							<td class = "col"> <a href = "deletemovie?movie=${m.id}" onclick = "return confirm('Are you sure? This cannot be undone');" >Delete</a>&nbsp;<a href = "movie?movie=${m.id}">Edit</a> </td>
          				
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