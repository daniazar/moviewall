<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Movie Wall: did you enjoyed it?</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
	<link href="../../css/style.css" rel="stylesheet" type="text/css" />
	<script type = "text/javascript" src = "../../js/jquery.js"></script>
		<script type = "text/javascript" src = "../../js/jquery.validate.js"></script>
	<script type = "text/javascript" src = "../../js/jquery.corners.js"></script>
	<link href="../../js/calendar/calendar-blue.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../../js/calendar/calendar.js"></script>
	<script type="text/javascript" src="../../js/calendar/lang/calendar-en.js"></script>
	<script type="text/javascript" src="../../js/calendar/calendar-setup.js"></script>
	
	
	
	<script type = "text/javascript">
		$(document).ready(function(){
			$('.corner').corner();
		});
		function showFull(){
			$('.full').show();
			$('.short').hide();
		}

		function hideFull(){
			$('.full').hide();
			$('.short').show();
		}
		
	
	</script>
</head>
<body>
<!-- start header -->
<div id="header">
	<div id=logo>
		<a href="main"><img src="../../images/logo.png" height="70" width="180" style="float: left;"/> </a>
		<p></p><p></p><p>&nbsp;&nbsp;&nbsp; <a href="main">did you enjoyed it?</a></p>
	</div>
	
	<div id="menu">
		<ul>
			<li><a href="main">Home</a></li>
			<li><a href="/MovieWall/web/general/about">About us</a></li>
			<li><a href="/MovieWall/web/movies/viewAllMovies">Movies</a></li>
			<li>  <a href="/MovieWall/web/user/allusers">Users</a></li>
			<c:choose>
		<c:when test="${not empty user}">
		<a href="<c:url value="profile"><c:param name="code" value="${user.username}" /></c:url>"><c:out value="${user.username}" /> </a>
		<a href="logout">Logout</a>
			 
		</c:when>
		<c:otherwise>
			<span style="float: right;"> <a href="login">Login</a> <a href="register">Register</a></span>				
		</c:otherwise>
	</c:choose>
			
		</ul>
	</div>
</div>
<!-- end header -->