<%@ include file="header.jsp" %>
			<div id = "content">
				
				<div class = "box">

						<form:form method="POST" action="savemovie" id = "movieForm" commandName="movieForm" >
						<dl>
							<form:input type="hidden" value="-1" name="id" path = "id"/>
							<dd><span class = "left"> Title: (*) </span> <form:input class = "fixedSize" type = "text" name = "title" value="" path = "title"/></dd>
							<dd><span class = "left"> Director: (*) </span><form:input class = "fixedSize" type = "text" name = "director" value="" path = "director"/></dd>
							<dd><span class = "left"> Duration(minutes): (*)</span><form:input class = "fixedSize" type = "text" name = "duration" value="" path = "duration"/></dd>
							<br/>
							<dd><span class = "left"> Release: (*)</span><form:input class = "fixedSize" type = "text" name = "release" readonly="true" value="" id="date" path = "release"/>

							<img src="../../js/calendar/img.gif" id="calendar" style="cursor: pointer; border: 1px solid red; height: 14px; width: 20px;" title="Date selector"
      							onmouseover="this.style.background='red';" onmouseout="this.style.background=''" /></dd>
							<script type="text/javascript">
  							Calendar.setup(
  							{
 							    inputField :  "date",      // ID of the input field
      							ifFormat   : "%Y-%m-%d",   // the date format
      							button     : "calendar"    // ID of the button
    						}
							  );
							</script>
							


						
							<dd><span class = "left">Genres:</span>
							<select id = "genres" class = "fixedSize" size = "4" multiple = "multiple" name = "genres" >
								<c:forEach items="${genreList}" var="gen">
									<c:if test="${gen.movieGenreTag == true}">
										<option selected = "selected" value = "${gen.name}"> ${gen.name} </option>
									</c:if>
									<c:if test="${gen.movieGenreTag == false}">
										<option value = "${gen.name}"> ${gen.name} </option>
									</c:if>
									${gen.name}
       							</c:forEach>   					
							</select>		
							</dd>
							<dd>&nbsp;</dd>
							<dd>&nbsp;</dd>
							<dd><span class = "left">Synopsis:</span>
								<form:textarea name = "sinopsys" cols = "40" rows = "20" path="sinopsys"/>
							</dd>
							<dd>&nbsp;</dd>
							<dd>&nbsp;</dd>
							<dd><span class = "left"> Image URL: (*) </span><form:input class = "fixedSize" type = "text" name = "imgurl" path = "imgUrl" value="" /></dd>
							<dd>&nbsp;</dd>
							<dd><span class = "left"> (*) Required fields </span> </dd>
							<dd>&nbsp;</dd>
							<dd>&nbsp;</dd>
							<dd><span class = "right"><input type = "submit" value = "Save" onclick = "return confirm('Are you sure? This cannot be undone');"/> </span></dd>
							
						</dl>
					</form:form>
					<span class = "right"><form action="movies" method="GET"> <input type = "submit" value = "Cancel"/></form>
					</span>
					<br/>

				</div>

				
					
							
			</div>
		</div>
	</body>
</html>