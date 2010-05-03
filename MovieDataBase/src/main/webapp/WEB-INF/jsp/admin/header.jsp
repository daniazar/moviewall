<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
	<head>
	<link href="../../css/adminStyle.css" rel="stylesheet" type="text/css" />
	<link href="../../css/jquery.multiSelect.css" rel="stylesheet" type="text/css" />
	<script type = "text/javascript" src = "../../js/jquery.js"></script>
	<script src="../../js/jquery.bgiframe.min.js" type="text/javascript"></script>
	<script src="../../js/jquery.multiSelect.js" type="text/javascript"></script>
	<link href="../../js/calendar/calendar-blue.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../../js/calendar/calendar.js"></script>
	<script type="text/javascript" src="../../js/calendar/lang/calendar-en.js"></script>
	<script type="text/javascript" src="../../js/calendar/calendar-setup.js"></script>
	<script type = "text/javascript" src = "../../js/jquery.validate.js"></script>
	<script>
			$(document).ready(function(){
				$('#searchField').select();
			});
	</script>	

		
	<script type = "text/javascript">
			$(document).ready(function(){
				$('#genres').multiSelect({ oneOrMoreSelected: '*' });

				$('.delete').each(function(){
					$(this).click(function(){ return confirm('Are you sure? This cannot be undone');});
				});
				
					$('#movieForm').validate({
				rules: {
						title:{
								 required: true,
								 minlength: 1
							 	},
						director :{
								required:true,
								minlength: 1
							 	},
						duration :{
									required: true,
									min: 1,
							 	},
						release: {
									required: true,
							 	},
						imgurl: {
									required: true
							 	}
						
				},
				messages: {
					title: {
							required: "Provide a title for the movie.",
							minlength: "Title too short. Min 1 character."
							},
					director: {
								required: "Provide a director",
								minlength: "Director too short. Min 1 character."
							},
					duration: {
								required: "Provide a duration.",
								min: "The duration must be positive"	
						},
					release: {
								required: "Provide a date of release."
						},	
					imgurl: {
								required: "Provide a url for the image."
					}
						
				}
				});
				
			});
		</script>
	</head>
	<body>
	
		<div id = "wrapper">
			<div id = "header" class = "blue">
				Admin Section
			</div>
			<div id = "leftmenu" class = "blue">
				<ul>
					<li><a href = "home">Admin Home</a></li>
					<li><a href = "../movie/main">Main Home</a></li>
					<li><a href = "movies">Movies</a></li>
					<li><a href = "users">Users</a></li>
					<li><a href = "../user/logout">Logout</a></li>
				</ul>
			</div>
