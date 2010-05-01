
			<c:choose>
				<c:when test="${not empty users}">
					<c:set var="row" value="${0}" />
					<c:forEach items="${users}" var="myuser">
						<%@ include file="color.jsp" %>
					
							<span style="font-size: 25px;">
								 <a href="<c:url value="profile"><c:param name="code" value="${myuser.username}" /></c:url>">
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
