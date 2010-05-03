<%@ include file="../header.jsp" %>
				
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
						required: "<span class=\"error\">Choose an username.</span>",
						minlength: "<span class=\"error\">Username too short. Min 7 characters.</span>"
						},
				password: {
							required: "<span class=\"error\">Provide a password.</span>",
							minlength: "<span class=\"error\">Password too short. Min 8 characters.</span>"
						},
				repassword: {
							equalTo: "<span class=\"error\">Passwords don't match.</span>"
					},
				email: "<span class=\"error\">Enter a valid e-mail address.</span>"	
				,
			name		 : "<span class=\"error\">This field is required.</span>",
			surname : "<span class=\"error\">This field is required.</span>",				
		 	},
	
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
					<form:form method="POST" action="register" id = "signUpForm" commandName="registerForm">
						<dl>
							<dd><span class = "left">Username:(*)</span><form:input id = "username" type = "text" path = "username"/><span class="error"><form:errors path="username" /></span></dd>
							<dd><span class = "left">Password:(*)</span><form:input id  = "password" type = "password" path = "password"/><span class="error"><form:errors path="password" /></span></dd>
							<dd><span class = "left">Re-enter password:(*)</span><input id = "repassword" type = "password" name = "repassword"/></dd>
							<dd><span class = "left">E-mail:(*)</span><form:input id = "email" type = "text" path = "email"/><span class="error"><form:errors path="email" /></span></dd>
							<dd><span class = "left">Name:(*)</span><form:input id = "name" type = "text" path = "name"/><span class="error"><form:errors path="name" /></span></dd>
							<dd><span class = "left">Surname:(*)</span><form:input id = "surname"type = "text" path = "surname"/><span class="error"><form:errors path="surname" /></span></dd>
							<dd><span class = "left">Birthday:(*)</span><form:input id = "birth" type = "text" readonly="true" path = "birthday"  value = "YYYY-MM-DD"/><span class="error"><form:errors path="birthday" /></span>
							<img src="../../js/calendar/img.gif" id="calendar" style="cursor: pointer; border: 1px solid red; height: 14px; width: 20px;" title="Date selector"
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
					</form:form>
					By registering you gain a lot of adventages in the system, such us writing movie comments. If you know
				a lot of movies, you can even become a vip user!
				<div>	
				Already registered,  <a href="login">login here.</a> 
			</div>
				
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

