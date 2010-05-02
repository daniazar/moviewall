<%@ include file="../header.jsp" %>

<script type = "text/javascript">
	$(document).ready(function(){
		$('#searchField').select();				
	});
</script>
<div id="page">
	<!-- start content -->
	<div id="content">
		<!-- start latest-post -->
		<div id="latest-post" class="post">
			<h1 class="title">Movies Search Results</h1>
			<div class="entry">
			<form class = "left corner blue">
				<input id = "searchField" type = "text" name = "q" value = "${query}" class = "search"/><input type = "submit" value = "Search"/>
			</form>
				<h5>This are the results that matches your search.</h5>
			<div class="left corner grey">	
				<table class = "searchResult">
					<tr class = "header">
						<td class = "col">Name</td>
						<td class = "col">Director</td>
						<td class = "col">Duration</td>
						<td class = "col">Comments</td>
						<td class = "col">Rating</td>
						<td class = "col">Release</td>
				</tr>
				<c:forEach items = "${movieList}" var = "m">
				<tr>
					<td><a href = "viewMovie?movie=${m.id}">${m.title}</a></td>
					<td>${m.director}</td>
					<td>${m.duration}</td>
					<td>${m.cantComments}</td>
					<td>
					<c:choose>
						 <c:when test = "${m.rating < 1}">Poor</c:when>
						 <c:when test = "${m.rating < 2}">Nothing special</c:when>
						 <c:when test = "${m.rating < 3}">Good</c:when>
						 <c:when test = "${m.rating < 4}">Great</c:when>
						 <c:when test = "${m.rating <= 5}">Awsome!</c:when>
					</c:choose> 
					</td>
					<td>${m.release}</td>
				</tr>
				</c:forEach>
				</table>
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
