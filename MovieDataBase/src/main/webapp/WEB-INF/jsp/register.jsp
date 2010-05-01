<%@ include file="header.jsp" %>
				
	<script type = "text/javascript">
	$(document).ready(function(){
	
		$('#signUpForm').validate({
			rules: {
				username:{
						 required: true,
						 minlength: 7
					 	},
				password :{
						required:true,
						minlength: 8
					 	},
				repassword :{
							required: true,
							equalTo: "#password"
					 	},
				email: {
							required: true,
							email: true
					 	},
				name: {
							required: true
					 	},
				surname: {
							required: true
					 	}
				
		},
			messages: {
				username: {
						required: "Choose an username.",
						minlength: "Username too short. Min 7 characters."
						},
				password: {
							required: "Provide a password.",
							minlength: "Password too short. Min 8 characters."
						},
				repassword: {
							equalTo: "Passwords don't match."
					},
				email: "Enter a valid e-mail address."	
			}
		});
	});
	</script>
				
	
<div id="page">
	<!-- start content -->
	<div id="content">
		<!-- start latest-post -->
		<div id="latest-post" class="post">
			<h1 class="title">Register</h1>
			<div class="entry">
				Fill with your personal data
				<div class = "corner blue">
					<form method="POST" action="register/saveuser" id = "signUpForm">
						<dl>
							<dd><span class = "left">Username:(*)</span><input id = "username" type = "text" name = "username"/></dd>
							<dd><span class = "left">Password:(*)</span><input id  = "password" type = "password" name = "password"/></dd>
							<dd><span class = "left">Re-enter password:(*)</span><input id = "repassword" type = "password" name = "repassword"/></dd>
							<dd><span class = "left">E-mail:(*)</span><input id = "email" type = "text" name = "email"/></dd>
							<dd><span class = "left">Name:(*)</span><input id = "name" type = "text" name = "name"/></dd>
							<dd><span class = "left">Surname:(*)</span><input id = "surname"type = "text" name = "surname"/></dd>
							<dd><span class = "left">Birthday:(*)</span><input id = "birth" type = "text" name = "date"  value = "YYYY-MM-DD"/>
							<img src="js/calendar/img.gif" id="calendar" style="cursor: pointer; border: 1px solid red; height: 14px; width: 20px;" title="Date selector"
      							onmouseover="this.style.background='red';" onmouseout="this.style.background=''" /></dd>
							<script type="text/javascript">
  							Calendar.setup(
  							{
 							    inputField :  "birth",      // ID of the input field
      							ifFormat   : "%Y-%m-%d",   // the date format
      							button     : "calendar"    // ID of the button
    						}
							  );
							</script>
							
							<dd><span class = "left"> (*) Required fields </span> </dd>							
							<!-- <dd><span class = "left">&nbsp;</span><span><input type = "checkbox" value = "TaC"/> I accept <a href = "#">Term & Conditions</a></dd> -->
							<dd>&nbsp;</dd>
							<dd><span class = "right"><input type ="submit" value = "Register"/></span></dd>
								
						</dl>
					</form>
				</div>
				By registering you gain a lot of adventages in the system, such us writing movie comments. If you know
				a lot of movies, you can even become a vip user!			</div>
				
			<div>	
				Already registered,  <a href="login">login here.</a> 
			</div>
		</div>			
					
	</div>	
	<!-- end content -->	
			
				

		<div id="sidebar">		
		</div>
	
</div>
<%@ include file="footer.jsp" %>

