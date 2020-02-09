<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Health Wellness Tracker | Login</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<style>
body{
    font-size:14px;
    color:#fff;
}
.simple-login-container{
    width:300px;
    max-width:100%;
    margin:50px auto;
}
.simple-login-container h2{
    text-align:center;
    font-size:20px;
}

.simple-login-container .btn-login{
    background-color:#FF5964;
    color:#fff;
}
a{
    color:#000;
}
</style>

</head>
<body>
	<div>
		<c:if test="${message != null}">
			<p>${message}</p>
		</c:if>
	</div>
	<div id='simple-login-container' title="Login Form" align="center">
		<form:form action="login" method="POST" modelAttribute="inputLogin">
			<table border="0">
				<tr>
					<td colspan="2" align="left"><h2><img src="<c:url value = "/images/logo.png"/>"></h2></td>
				</tr>
				<tr>
					<!-- <td><h2>Username:</h2></td> -->
					<td><form:input class="form-control" id="login" path="username" placeholder="Username"/></td>
				</tr>
				<tr>
					<!-- <td><h2>Password:</h2></td> -->
					<td><form:input class="form-control" id="login" type="password" path="password" placeholder="Password"/></td>
				</tr>
				<tr>
					<td align="center"><input id='btn-login' type="submit" value="Login"></td>
				</tr>
			</table>
		</form:form>
		<br> <a href="signup">Create An Account</a>
	</div>
</body>
</html>