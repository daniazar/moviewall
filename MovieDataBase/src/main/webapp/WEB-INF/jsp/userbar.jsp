			<c:choose>
				<c:when test="${not empty user}">
				<h1>Welcome <c:out value="${user.username}" /></h1>
				<br/>
				<div>
				<h2>User menu:</h2>
				<ul>
					<li><a href="<c:url value="../user/profile"><c:param name="user" value="${user.username}" /></c:url>">My Profile</a></li>
					
					<c:if test="${user.isAdmin}"> 
					<li><a href="../admin/home">Administrator Menu</a></li>
					</c:if> 

					<li><a href="../movie/allmovies">Movies</a></li>
					<li><a href="../user/allusers">Users</a></li>
					<li><a href="../user/logout">Logout</a></li>
				</ul>	
				</div>
			</c:when>
			<c:otherwise>
				<div><h1>Login</h1></div>
			<div class="post">
				<div class="entry">
					Enter username and password to enter the system
					<form method="POST" action="../user/login2">
							<dl>
							<dt><span class = "left">Username: </span><span class = "right"><input type = "text" name="username" />	
							</span></dt>
							<dt><span class = "left">Password: </span><span class = "right"><input type = "password" name="password" />
							</span></dt>
							<dt>   <input type = "submit" value = "Sign in"/></dt>
							<dt> Not yet a member?<a href = "../user/register">  Register </a> </dt>
						</dl>
					</form>
				</div>
			</div>	
			</c:otherwise>
			</c:choose>
			
			