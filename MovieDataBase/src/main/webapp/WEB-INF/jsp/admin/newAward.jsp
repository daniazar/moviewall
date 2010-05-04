<%@ include file="header.jsp" %>
			<div id = "content">
				<div class  = "searchResult">
					<table>
						
						<form method="POST" action="newAward">
						<dl>
							<dd><span class = "left"> Award: </span> <input class = "fixedSize" type = "text" name = "award" value=""/></dd>
							<dd><span class = "left"> (*) Required fields </span> </dd>
							<dd>&nbsp;</dd>
							<dd>&nbsp;</dd>
							<dd><span class = "right"><input type = "submit" value = "Save" onclick = "return confirm('Are you sure? This cannot be undone');"/>  </span></dd>
							
						</dl>
					</form>
										
					
						</table>
				</div>
			</div>
		</div>
	</body>
</html>