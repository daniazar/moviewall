<%@ include file="header.jsp" %>
				
<div id="page">
	<!-- start content -->
	<div id="content">
		<!-- start latest-post -->
		<div id="latest-post" class="post">
			<h1 class="title"><c:out value="${movie.title}" /></h1>
			<div class="entry">
				<%@ include file="movie.jsp" %>
				Green boxes means the comment is form a trusted and special users.
				<%@ include file="comments.jsp" %>
				<%@ include file="newComment.jsp" %>
				
			</div>
		</div>			
		<%@ include file="rightBar.jsp" %>	
				
	</div>	
	<!-- end content -->	
			
				

		<div id="sidebar">
			
		
		</div>
	
	
</div>
<%@ include file="footer.jsp" %>
