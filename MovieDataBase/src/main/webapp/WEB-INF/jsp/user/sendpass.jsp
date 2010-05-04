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
				
					<p>Enter your username and password to enter the system.</p>
					<form method="POST" action="sendpass" >
						
						<dl>
							<dt><span class = "left">Username: </span><span class = "middle"><input type = "text" name="username" />	
							</span></dt>
						
							<dt>   <input type = "submit" value = "Send password"/></dt>
							<dt> Not yet a member?<a href = "register">  Register </a> </dt>

						</dl>
					</form>
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
