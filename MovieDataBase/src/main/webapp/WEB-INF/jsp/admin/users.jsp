<%@ include file="header.jsp" %>
			<div id = "content">
				<div class  = "search">
					<form method="POST">
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
							<td class = "col">VIP</td>
							<td class = "col">Admin</td>
						</tr>
						
						<c:forEach items="${userList}" var="user">
        				<tr class="elem">
          					<td class = "col">${user.username}</td>
          					<td class = "col">${user.name}</td>
          					<td class = "col">${user.surname}</td>
          					<td class = "col">${user.email}</td>
          				
          					<td class= "col"> <a href="<c:url value="userupgrade"><c:param name="userp" value="${user.username}"/>
																<c:param name="admin" value="${user.isAdmin}"/>
																<c:param name="vip" value="${not user.isVip}"/>
							</c:url>">
								<c:out value="${user.isVip}" />
							</a> </td>
							
							          					<td class = "col"> 
							<a href="<c:url value="userupgrade"><c:param name="userp" value="${user.username}"/>
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