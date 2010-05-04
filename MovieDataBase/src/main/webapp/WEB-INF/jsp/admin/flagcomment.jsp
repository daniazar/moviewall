<%@ include file="header.jsp" %>
			<div id = "content">
				<div class  = "searchResult">
					
			
					<table>
						<tr class = "first">
							<td class = "col">User</td>
							<td class = "col">Rating</td>
							<td class = "col">Date</td>
							<td class = "col">Content</td>
							<td class = "col">Action</td>
						</tr>
						
						<c:forEach items="${commentList}" var="c">
							<tr>
								<td>${c.user.username}</td>
								<td>${c.raiting} </td>
								<td>${c.date} </td>
								<td>${c.content} </td>
								<td class = "center"><a href = "../general/deletecomment?comment=${c.id}" onclick = "return confirm('Are you sure? This cannot be undone');" > Delete </a></td>
								<td class = "center"><a href = "../admin/unflagcomment?comment=${c.id}" > Unflag  </a></td>
								
							</tr>
						</c:forEach>		
					</table>
					
				</div>
			</div>
		</div>
	</body>
</html>