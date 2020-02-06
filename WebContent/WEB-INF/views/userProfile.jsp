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
	<h2>Edit User Profile</h2>
	<br>
	<form:form action="editUserProfile" modelAttribute="userProfile" method="POST">
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input type="text" path="name" value="${connectedUser.getName()}"/></td>
			</tr>
	 		<tr>
				<td>Birthdate:</td>
				<td><form:input type="date" path="birthdate" value="${connectedUser.getBirthdate()}"/></td>
			</tr>
			<tr> 
				<td>Age:</td>
				<td><form:input type="text" path="age" value="${connectedUser.getAge()}" /></td>
			</tr>
			<tr>
				<td>Country:</td>
				<td><form:input type="text" path="country" value="${connectedUser.getCountry()}" /></td>
			</tr>
			<tr>
				<td>Email Address:</td>
				<td><form:input type="email" path="emailAddress" value="${connectedUser.getEmailAddress()}" /></td>
			</tr>
			<tr> 
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>


</body>
</html>