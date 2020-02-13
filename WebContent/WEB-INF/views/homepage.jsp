<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="Kirstie Rodriguez">
<meta name="description"
	content="Health Wellness Tracker gives users the ability to input and monitor their habits, symptoms, 
	illnesses, and the like with easy-to-see views over time.">
<link href='https://use.fontawesome.com/releases/v5.0.6/css/all.css'
	rel='stylesheet'>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale = 1">
<title>Health Wellness Tracker</title>
<style>
body {
	background-image: url('<c:url value="/images/background.jpg"/>');
	background-size: cover;
}

/* #logo-image {
	display: block;
	margin-left: auto;
	margin-right:auto;
	width: 30%;
} */

</style>
</head>
<body>
	<!-- Get rid of underline in links is from CSS -->
	<nav class="navbar navbar-light bg-light"> <a
		class="navbar-brand ml-auto" href="login">Login</a> </nav>

	<div id="logo-image" class="text-center">
		<img src="<c:url value="/images/logo.png"/>" style="margin: 200" />
	</div>

	<nav class="navbar fixed-bottom navbar-light bg-light"> <a
		class="navbar-brand" href="#">Fixed bottom</a>
	<ul class="nav justify-content-end">
		<li class="nav-item"><a class="nav-link active" href="#">FAQ</a>
		</li>
		<li class="nav-item"><a class="nav-link" href="#">About Us</a></li>
		<li class="nav-item"><a class="nav-link" href="#">Privacy
				Policy</a></li>
		<li class="nav-item"><a class="nav-link disabled" href="#"
			tabindex="-1" aria-disabled="false">Contact Us</a></li>
	</ul>
	</nav>
</body>
</html>