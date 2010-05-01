<%@ include file="header.jsp" %>
				
<div id="page">
	<!-- start content -->
	<div id="content">
		<!-- start latest-post -->
		<div id="latest-post" class="post">
			<h1 class="title">Latest added movies</h1>
			<div class="entry">
			Here are the most recently added/modified movies in the system
				<%@ include file="movies.jsp" %>
				
			</div>
		</div>			


		<%@ include file="rightBar.jsp" %>
	</div>	
	<!-- end content -->	
			
				

		<div id="sidebar">
			
		
		</div>
	
	
</div>
<%@ include file="footer.jsp" %>
