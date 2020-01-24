<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create User Profile</title>
</head>
<body>
	<h2>Create User Profile</h2>
	<br>
	<h2>${userLogin.userId}</h2>
	<br>
	<h2>${userLogin.username}</h2>
	<br>
	<form:form action="saveUserProfile" modelAttribute="userProfile" method="POST">
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input path="name" value="fullname" /></td>
			</tr>
	 		<tr>
				<td>Birthdate:</td>
				<td><form:input type="date" path="birthdate" /></td>
			</tr>
			<tr> 
				<td>Age:</td>
				<td><form:input path="age" value="18" /></td>
			</tr>
			<tr>
				<td>Country:</td>
				<td><form:input path="country" value="USA" /></td>
			</tr>
			<tr>
				<td>Email Address:</td>
				<td><form:input path="emailAddress" value="fake@gmail.com" /></td>
			</tr>
			<tr> 
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>


</body>
</html>