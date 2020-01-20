<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<div align="left">
		<c:if test="${message != null}">
			<p>${message}</p>
		</c:if>
		<form:form action="login" method="POST" modelAttribute="inputLogin">
			<table border="0">
				<tr>
					<td colspan="2" align="left"><h2>Spring MVC Form</h2></td>
				</tr>
				<tr>
					<td>User Name:</td>
					<td><form:input path="username" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:input type="password" path="password" /></td>
				</tr>
				<tr>
					<td align="left"><input type="submit" value="Login"></td>
				</tr>
			</table>
		</form:form>
		<br> <a href="signup">Sign Up</a>
	</div>

	<!--%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%-->


	<!-- 	<h1> This is the index page. </h1>
	<a href= "index">Index</a>
	<h2>USER CRUD OPERATIONS INPUT PAGE</h2>

 	<form action="garbage" method="POST">
	  id:
	  <input type="text" name="userID" placeholder="ID">
	  <br><br>
	  First Name:
	  <input type="text" name="firstName" placeholder="First Name">
	  <br><br>
	  Last Name:
	  <input type="text" name="lastName" placeholder="Last Name">
	  <br><br>
	  Email:
	  <input type="text" name="email" placeholder="Email Address">
	  <br><br>
	  Username:
	  <input type="text" name="username" placeholder="Username">
	  <br><br>
	  Password:
	  <input type="password" name="password" placeholder="Password">
	  <br><br>
	  <input type="submit" value="Add">
	  <input type="submit" value="Delete">
	  <input type="submit" value="Update">
	  <input type="submit" value="Submit">
	</form>
	
	<table>
    <caption>All Users</caption>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">Email</th>
        <th scope="col">Username</th>
        <th scope="col">Password</th>
    </tr>
    <tr>
    	<td>1</td>
        <td>Bob</td>
        <td>Denny</td>
        <td>bdenny@gmail.com</td>
        <td>bdenny</td>
        <td>nunyabusiness</td>
    </tr>
	<tr>
    	<td>2</td>
        <td>Mary</td>
        <td>Typhoid</td>
        <td>mtyphoid@gmail.com</td>
        <td>mtyphoid</td>
        <td>nunyabusiness</td>
    </tr>
	<tr>
    	<td>3</td>
        <td>Alice</td>
        <td>Nottingham</td>
        <td>anottingham@gmail.com</td>
        <td>anottingham</td>
        <td>nunyabusiness</td>
    </tr>
	<tr>
    	<td>4</td>
        <td>Zaddy</td>
        <td>Gertswag</td>
        <td>fme@gmail.com</td>
        <td>fme</td>
        <td>yaszaddyyyy</td>
    </tr>
	<tr>
    	<td>5</td>
        <td>Millenials</td>
        <td>AreTheWorst</td>
        <td>millenialsaretheworst@gmail.com</td>
        <td>millenialsstink</td>
        <td>nunyabusiness</td>
    </tr>
</table>
	
	
	<p>If you click the "Submit" button, the form-data will be sent to a page called "/action_page.php".</p>
	 -->

</body>
</html>