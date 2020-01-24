<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Profile Page</title>
</head>
<body>
	<h2>${userId}</h2>
	<h2>Welcome, ${userLogin.username}.</h2>
	<h2>Here is your profile, ${userLogin.username}</h2>
	<div class="vertical-menu">
		<a href="homepage" class="active">Home</a> |
		<a href="userProfile">Your Profile</a> |
		<a href="logout">Log Out</a><br>|
		<a href="newEvent">Create New Event</a><br>
	</div>
	<a href="createUserProfile">Create Profile</a>
	
</body>
</html>

