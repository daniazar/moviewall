			<div class="post">
				<div><h1>Find movies</h1></div>
				<div class="entry">
					Enter movie keywords:
					<form action = "search">
						<input type = "text" name = "q" value = ""/>
						<input type = "submit" value = "Search!"/>		
					</form>
					<br/>
					
					<ul>
						<li><a href = "ranking">Top five movies</a></li>
					</ul>
				<h2>List movies by genre:</h2>
					<ul>
									<c:choose>
				<c:when test="${not empty allgenres}">
					<c:set var="row" value="${0}" />
					<c:forEach items="${allgenres}" var="genre">
						<div> 
							<c:if test="${row <= 4}">  
							<c:set var="row" value="${row + 1}" />
							
							<li>
							<a href="<c:url value="genres"><c:param name="genre" value="${genre.name}" /></c:url>">
									<c:out value="${genre.name}" />
								</a>
							</li>
							</c:if>
							<c:if test="${row == 5}">  
								<c:set var="row" value="${row + 1}" />
							
								 <a href="genresList">
									more...
								</a>
							
							</c:if> 
							 
						</div>	
								</c:forEach>
	
							</c:when>
							<c:otherwise>
								<p>There are no genres.</p>
							</c:otherwise>
							</c:choose>
					</ul>
				</div>
			</div>
