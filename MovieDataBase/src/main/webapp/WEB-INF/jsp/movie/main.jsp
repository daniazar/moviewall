<%@ include file="../header.jsp" %>
				
<div id="page">
	<!-- start content -->
	<div id="content">
		<!-- start latest-post -->
		<div id="latest-post" class="post">
			<h1 class="title">Latest added movies</h1>
			<div class="entry">
			<h5>Here are the most recently added/modified movies in the system</h5>
				<%@ include file="movies.jsp" %>
				
			</div>
		</div>			


		<%@ include file="../rightBar.jsp" %>
		<div id="recent-posts">
		<div class="right green corner">
		<h1>Top five movies:</h1>
		<c:forEach items="${movieListRanking}" var="movie">
		
		<a href="<c:url value="viewMovie"><c:param name="movie" value="${movie.id}" /></c:url>">
		<c:out value="${movie.title}"  />

		</a>
		rating: <c:out value="${movie.rating}"  />
		<br/>
		
		</c:forEach>
		</div>
		
		<div class="right pink corner">
		<h1>This week Releases:</h1>
		<c:forEach items="${movieListRelease}" var="movie">
		
		<a href="<c:url value="viewMovie"><c:param name="movie" value="${movie.id}" /></c:url>">
		<c:out value="${movie.title}"  />

		</a>
		rating: <c:out value="${movie.rating}"  />
		<br/>
		
		</c:forEach>
		</div>
		
		</div>
	</div>	
	<!-- end content -->	
			
				

		<div id="sidebar">
			
		
		</div>
	
	
</div>
<%@ include file="../footer.jsp" %>
