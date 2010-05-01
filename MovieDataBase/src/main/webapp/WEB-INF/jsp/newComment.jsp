					<div class = "corner green">
					
					<c:if test="${empty user}">  
						<p>Only registered users can add a comment.</p>
					</c:if>
					<c:if test="${not empty user}">
					<script type = "text/javascript">
						function validate(){
							if($('#rating').val() == ""){
								alert('Don\'t forget to select a rating!');
								$('#rating').focus();
								return false;
							}

							if($('#comment').val() == "" || $('#comment').val() == 'Add your comment!'){
								return false;
							}
							return true;
						}
					</script>
					  
					<form method="POST" action="savecomment?user=${user.username}&movieid=${movie.id}" onsubmit = "return validate();">
						<ul>
							<li>Username: <i><c:out value="${user.username}" /></i></li>
							<li>Raiting :<select name = "rating" id  = "rating">
								<option value = "">-- Select a Raiting --</option>
								<option value = "1">Poor</option>
								<option value = "2">Nothing special</option>
								<option value = "3">Good</option>
								<option value = "4">Great</option>
								<option value = "5">Awesome!</option>
							</select> </li>
							<br/>
							<textarea type = "text" cols = "69" rows = "6" name = "comment" id = "comment" onblur = "if(this.value.length == 0) this.value = 'Add your comment!';"onclick = "if(this.value == 'Add your comment!') this.value = '';"/>Add your comment!</textarea>
							<br/>
							<input type = "submit" value = "Submit"/>
						</ul>
					</form>
										
				</c:if>
					</div>
