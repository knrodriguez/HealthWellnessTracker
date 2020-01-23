<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Health Wellness Tracker | Login</title>
</head>
<body>
	<div>
		<c:if test="${message != null}">
			<p>${message}</p>
		</c:if>
	</div>
	<div title="Login Form" align="center">
		<form:form action="login" method="POST" modelAttribute="inputLogin">
			<table border="0">
				<tr>
					<td colspan="2" align="left"><h2><img src="<c:url value = "/images/logo.png"/>"></h2></td>
				</tr>
				<tr>
					<!-- <td><h2>Username:</h2></td> -->
					<td><form:input id="login" path="username" placeholder="Username"/></td>
				</tr>
				<tr>
					<!-- <td><h2>Password:</h2></td> -->
					<td><form:input id="login" type="password" path="password" placeholder="Password"/></td>
				</tr>
				<tr>
					<td align="center"><input type="submit" value="Login"></td>
				</tr>
			</table>
		</form:form>
		<br> <a href="signup">Create An Account</a>
	</div>
</body>
</html>