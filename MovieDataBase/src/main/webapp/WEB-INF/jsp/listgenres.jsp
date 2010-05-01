<%@ include file="header.jsp" %>
				
<div id="page">
	<!-- start content -->
	<div id="content">
		<!-- start latest-post -->
		<div id="latest-post" class="post">
			<h1 class="title">All the genres in the system</h1>
			<div class="entry">


				<c:choose>
				<c:when test="${not empty allgenres}">
					<c:set var="row" value="${0}" />
					<c:forEach items="${allgenres}" var="genre">
						<%@ include file="color.jsp" %>
					
							<span style="font-size: 25px;">
								 <a href="<c:url value="listgenre"><c:param name="code" value="${genre.name}" /></c:url>">
									<c:out value="${genre.name}" />
								</a>
							</span>
							
						</div>	
					</c:forEach>
	
					</c:when>
					<c:otherwise>
					<p>There are no genres.</p>
					</c:otherwise>
					</c:choose>


				
			</div>
		</div>			
					<%@ include file="rightBar.jsp" %>	

	</div>	
	<!-- end content -->	
			
				

		<div id="sidebar">		
		</div>
	
	
</div>
<%@ include file="footer.jsp" %>


