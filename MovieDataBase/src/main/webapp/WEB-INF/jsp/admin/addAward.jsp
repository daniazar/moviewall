<%@ include file="header.jsp"%>
<div id="content">
<div class="searchResult">
<table>

	<form method="POST" action="NewAward">
	<dl>
		<label>Movie:</label>
		<select name="movie">
			<c:forEach var="movie" items="${movieList}">
				<option value="<c:out value="${movie.id}"/>"><c:out
					value="${movie.title}" /></option>
			</c:forEach>
		</select>

		<label>Award:</label>
		<select name="award">
			<c:forEach var="award" items="${awardList}">
				<option value="<c:out value="${award.id}"/>"><c:out
					value="${award.name}" /></option>
			</c:forEach>
		</select>
		<label>won?:</label>
		<select name="won">

				<option value="false">no</option>
				<option value="true">yes</option>		
		</select>

		<dd>&nbsp;</dd>
		<dd>&nbsp;</dd>
		<dd><span class="right"><input type="submit" value="Save"
			onclick="return confirm('Are you sure? This cannot be undone');" /> </span></dd>

	</dl>
	</form>


</table>
</div>
</div>
</div>
</body>
</html>