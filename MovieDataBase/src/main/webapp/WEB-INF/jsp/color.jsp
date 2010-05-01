<div class = "left corner 
<c:choose>
			
<c:when test="${row % 5 == 0}">blue</c:when>
<c:when test="${row % 5 == 1 }">pink </c:when>
<c:when test="${row % 5 == 2 }">green </c:when>
<c:when test="${row % 5 == 3 }">grey </c:when>
<c:when test="${row % 5 == 4 }">yellow </c:when>
</c:choose>
">
<c:set var="row" value="${row + 1}" />
						
			 