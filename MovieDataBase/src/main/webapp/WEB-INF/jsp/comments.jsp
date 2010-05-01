			<c:choose>
				<c:when test="${not empty comments}">
					<c:set var="row" value="${row + 1}" />
					<c:forEach items="${comments}" var="comment">
						<div class = "left corner <c:if test="${row % 2 == 0 && ! comment.user.isVip}">blue</c:if><c:if test="${row % 2 != 0 && ! comment.user.isVip}">yellow </c:if> <c:if test="${comment.user.isVip}">green </c:if>	">
							<li class="<c:set var="row" value="${row + 1}" />" style="list-style-type: none; padding: 10px;">
							<a href="<c:url value="profile"><c:param name="code" value="${comment.userId}" /></c:url>">
								<c:out value="${comment.userId}" />
							</a>  |  
							<a href="<c:url value="viewMovie"><c:param name="code" value="${comment.movieId}"  /></c:url>">
								<c:out value="${comment.movie.title}" />
							</a>
							<c:if test="${user.isAdmin || user.username == comment.userId}">  
							<a href="<c:url value="deletecomment"><c:param name="id" value="${comment.id}" /></c:url>">
								<img src="images/delete.gif" style="float: right; cursor: pointer; border: 1px solid red; height: 10px; width: 10px;" alt="Delete comment"  />
							</a>
							</c:if>
							</dd>

							<br />
							<span style="font-size: 15px;">
								<c:out value="${comment.content}" />
							</span>
							<p style="font-size: 12px;">
								Rating:
								<c:choose>
									 <c:when test = "${comment.rating < 1}">Poor</c:when>
									 <c:when test = "${comment.rating < 2}">Nothing special</c:when>
									 <c:when test = "${comment.rating < 3}">Good</c:when>
									 <c:when test = "${comment.rating < 4}">Great</c:when>
									 <c:when test = "${comment.rating <= 5}">Awesome!</c:when>
								</c:choose> 
								
								 | Date: <fmt:formatDate value="${comment.date}" pattern="yyyy/MM/dd" />
							</p>
							</li>
						</div>	
						</c:forEach>
	
			</c:when>
		<c:otherwise>
			<p>There are no comments here yet.</p>
		</c:otherwise>
		</c:choose>