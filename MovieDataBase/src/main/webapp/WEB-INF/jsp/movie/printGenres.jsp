	<c:forEach items="${genres}" var="genre">
		 
		 <a href="<c:url value="genres"><c:param name="genre" value="${genre.name}"/></c:url>">
			<c:out value="${genre.name}" />
		</a>|	 	
	</c:forEach>
	