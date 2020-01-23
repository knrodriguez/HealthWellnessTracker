<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Event | Health Wellness Tracker</title>
</head>
<body>
	<h1>Create An Event</h1>
	<div>
		<form:form action="newEvent" method="post" modelAttribute="newEvent">
			<table>
				<tr>
					<td>Event Name</td>
				</tr>
				<tr>
					<td><form:input path="eventName" placeholder="Event Name" /></td>
				</tr>
				<tr>
					<td>Event Category</td>
				</tr>
				<tr>
					<td><form:select path="eventCategory" name="eventCategory">
							<c:forEach items="${listEventCategory}" var="category">
								<form:option value="${category}">${category}</form:option>
							</c:forEach>
					</form:select></td>
				</tr>
				<tr>
					<td>Event Description</td>
				</tr>
				<tr>
					<td><form:input path="eventDescription"
							placeholder="Event Description" /></td>
				</tr>
				<tr></tr>
				<tr>
					<td align="right"><input type="submit" value="Submit"></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>