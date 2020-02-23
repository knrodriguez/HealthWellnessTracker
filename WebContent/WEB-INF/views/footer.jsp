<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Footer</title>
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
<style>
html, body {
	height: 100%;
}

body {
	display: flex;
	flex-direction: column;
}

.footer {
	flex-shrink: 0;
	padding: 1rem;
	background-color: #00456d !important;
	text-align: center;
}

.link-style {
	color: #e8eef1 !important;
}

.navbar-brand {
	color: #ffffff;
	text-shadow: black;
}
</style>
</head>
<body>
	<footer> <nav class="navbar footer">
	<span class="navbar-brand">Health &amp; Wellness Tracker</span>
	<ul class="nav justify-content-end">
		<li class="nav-item"><a class="nav-link link-style" href="#">FAQ</a>
		</li>
		<li class="nav-item"><a class="nav-link link-style" href="#">About Us</a></li>
		<li class="nav-item"><a class="nav-link link-style" href="#">Privacy
				Policy</a></li>
		<li class="nav-item"><a class="nav-link link-style" href="#">Contact Us</a></li>
	</ul>
	</nav> </footer>
</body>
</html>