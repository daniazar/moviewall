<%@ include file="../header.jsp" %>
				
<div id="page">
	<!-- start content -->
	<div id="content">
		<!-- start latest-post -->
		<div id="latest-post" class="post">
			<h1 class="title">Login</h1>
			<div class="entry">
			<div class="post">
				<div class="entry left corner blue">
				
					<div class="error"><b><c:out value="${error}" /> </b></div>
					
					<div class="mierror"><b><c:out value="${mierror}" /> </b></div>	
				
					<p>Enter your username and password to enter the system.</p>
					<form:form method="POST" action="login" commandName="loginForm">
						
						<dl>
							<dt><span class = "left">Username: </span><span class = "middle"><form:input type = "text" path="username" />	
							</span><span class="error"><form:errors path="username" /></span></dt>
							<dt><span class = "left">Password: </span><span class = "middle"><form:input type = "password" path="password" />
							</span><span class="error"><form:errors path="password" /></span></dt>
							<dt>   <input type = "submit" value = "Sign in"/></dt>
							<dt> Not yet a member?<a href = "register">  Register </a> </dt>
						</dl>
					</form:form>
					<br/>
					<div">If you don't have an user yet, you can <a href="register">register</a> now its free.</div>
					
				</div>
			</div>	
				
			</div>
		</div>			


		<!-- <%@ include file="../rightBar.jsp" %> -->
	</div>	
	<!-- end content -->	
			
				

		<div id="sidebar">
			
		
		</div>
	
	
</div>
<%@ include file="../footer.jsp" %>
