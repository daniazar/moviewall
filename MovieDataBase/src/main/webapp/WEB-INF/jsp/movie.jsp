					<div class = "left corner blue">
						<c:if test="${not empty movie}">  
			
							<span style="font-size: 30px;">
							</span>
					
							<ul>
							<a href="<c:url value="${movie.imgUrl}"></c:url>">
									<img src="<c:out value="${movie.imgUrl}" />" class = "left"></img>

								</a>
								
							<li>Director: <c:out value="${movie.director}" /></li>
							<li>Rating:
								<c:choose>
									 <c:when test = "${movie.rating <= -1}">Not yet rated</c:when>
									 <c:when test = "${movie.rating < 1}">Poor</c:when>
									 <c:when test = "${movie.rating < 2}">Nothing special</c:when>
									 <c:when test = "${movie.rating < 3}">Good</c:when>
									 <c:when test = "${movie.rating < 4}">Great</c:when>
									 <c:when test = "${movie.rating <= 5}">Awesome!</c:when>
								</c:choose> </li>
							<c:set var="genres" value="${movie.genres}" />
							
							<li>Genres: <%@ include file="printGenres.jsp" %></li>
							<li>Duration: <c:out value="${movie.duration}" /></li>
							<li>Release: <fmt:formatDate value="${movie.release}" pattern="yyyy/MM/dd" /></li>
							<li>Last Modification: <fmt:formatDate value="${movie.creation}" pattern="yyyy/MM/dd" /></li>
									</ul>
							<br/>
							<br/>
							
							<div class = "short">
								<p><c:out value="${movie.short}" /></p>
								<p>...</p>
								<p><a href = "#" onclick = "showFull();"> Display full synopsis</a></p>
							</div>
							<div class = "full" style = "display:none">
								<p><a href = "#" onclick = "hideFull();"> Hide full synopsis</a></p>
								<p><c:out value="${movie.synopsis}" /></p>
								<p><a href = "#" onclick = "hideFull();"> Hide full synopsis</a></p>
							</div>
						</div>
			</c:if>
			<c:if test="${empty movie}">  
			<p>The movie doesn't exist.</p>
			</c:if>
		