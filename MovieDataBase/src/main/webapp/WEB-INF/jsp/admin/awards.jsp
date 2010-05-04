<%@ include file="header.jsp" %>
			<div id = "content">
				<div class  = "searchResult">
					<table>
						
						<tr class = "first">
							<td class = "col">Award</td>
							<td class = "col">Delete</td>
						</tr>
						
						<c:forEach items="${awardList}" var="award">
        				<tr class="elem">
          					<td class = "col">${award.name}</td>
          					<td class= "col"> <a href="<c:url value="deleteawards"><c:param name="award" value="${award.id}"/>
							</c:url>">
							delete
							</a> </td>
          				</tr>
      					</c:forEach>
					
					
						</table>
				</div>
			</div>
		</div>
	</body>
</html>