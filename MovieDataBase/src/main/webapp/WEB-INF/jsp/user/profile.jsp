<%@ include file="../header.jsp" %>
				
<div id="page">
	<!-- start content -->
	<div id="content">
		<!-- start latest-post -->
		<div id="latest-post" class="post">
			<h1 class="title">User profile</h1>
			<div class="entry">
			<h5>Here is some information about <c:out value="${userp.username}" /> .</h5>
				<div class = "left corner blue">
					<div>
						<ul>
							<span style="font-size: 30px;">
								 <c:out value="${userp.username}" />
							</span>
					
							<p>Name: <c:out value="${userp.name}" /></p>
							<p>Lastname: <c:out value="${userp.surname}" /></p>
							<p>Age: <c:out value="${userp.age}" /></p>
							<p>Birthday: <fmt:formatDate value="${userp.birthday}" pattern="MMMM dd" /></p>
							<p>E-mail: <c:out value="${userp.email}" /></p>
							<p>Category: Normal<c:if test="${userp.isVip}">  |  Vip  </c:if><c:if test="${userp.isAdmin}">  |  Admin</c:if></p>
						</ul>							
			</div>
			</div>		
			<div class="entry comment">
				<h2>Comments:</h2>
				<%@ include file="../comments.jsp" %>
				
			</div>
					
				</div>
				</div>
		<%@ include file="../rightBar.jsp" %>
	
	
	</div>
			
	<!-- end content -->
	<div id="sidebar">
	</div>
	
	
	
	
</div>

<%@ include file="../footer.jsp" %>