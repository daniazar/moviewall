
			<c:choose>
				<c:when test="${not empty userList}">
					<c:set var="row" value="${0}" />
					<c:forEach items="${userList}" var="myuser">
						<%@ include file="../color.jsp" %>
					
							<span style="font-size: 25px;">
								 <a href="<c:url value="profile"><c:param name="user" value="${myuser.username}" /></c:url>">
									<c:out value="${myuser.username}" />
								</a>
							</span>					
						</div>
							
			</c:forEach>
			</c:when>
		<c:otherwise>
			<p>There are no users registered in the system.</p>
		</c:otherwise>
		</c:choose>
