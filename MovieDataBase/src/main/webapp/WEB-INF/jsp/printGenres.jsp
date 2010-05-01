	<c:forEach items="${genres}" var="genre">
		 
		 <a href="<c:url value="listgenre"><c:param name="code" value="${genre.name}"/></c:url>">
			<c:out value="${genre.name}" />
		</a>|	 	
	</c:forEach>
	