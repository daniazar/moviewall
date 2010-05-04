<%@ include file="jsp/header.jsp" %>
				
<div id="page">
	<!-- start content -->
	<div id="content">
		<!-- start latest-post -->
		<div id="latest-post" class="post">
			<h1 class="title"><c:out value="Error" /></h1>
			<h5>An internal error occurred</h5>
			<div class="entry">
		<div class="left corner blue">	

		<span class="error">
		<c:out value="${error.message}" /></span>
		
			</div>
		</div>	
		</div>		
		<%@ include file="jsp/rightBar.jsp" %>	
				
	</div>	
	<!-- end content -->	
			
				

		<div id="sidebar">
			
		
		</div>
	
	
</div>
<%@ include file="jsp/footer.jsp" %>

