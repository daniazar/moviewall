
			<c:choose>
				<c:when test="${not empty movies}">
					<c:set var="row" value="${0}" />
					<c:forEach items="${movies}" var="movie">
						<%@ include file="color.jsp" %>
							<span style="font-size: 30px;">
								 <a href="<c:url value="viewMovie"><c:param name="code" value="${movie.id}" /></c:url>">
									<c:out value="${movie.title}" />
								</a>
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
								</c:choose> 
							
							</li>
							<c:set var="genres" value="${movie.genres}" />
							
							<li>Genres: <%@ include file="printGenres.jsp" %></li>
							
							<li>Duration: <c:out value="${movie.duration}" /></li>
							<li>Release: <fmt:formatDate value="${movie.release}" pattern="yyyy/MM/dd" /></li>
							<li>Last Modification: <fmt:formatDate value="${movie.creation}" pattern="yyyy/MM/dd" /></li>
							
									</ul>
							<br/>
							<br/>
							Comments: <c:out value="${movie.cantComments}" />
						</div>
							
			</c:forEach>
			</c:when>
		<c:otherwise>
			<p>There are no movies in this place.</p>
		</c:otherwise>
		</c:choose>
