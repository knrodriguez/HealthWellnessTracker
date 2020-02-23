<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<title>Welcome!</title>
<style>
html, body {
	height: 100%;
}

body {
	background-image: url('<c:url value="/images/background.jpg"/>');
	background-size: cover;
	display: flex;
	flex-direction: column;
}

#bodyContainer {
	flex: 1 0 auto;
}

footer {
	flex-shrink: 0;
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
	<jsp:include page="generalHeader.jsp"/>
	<div id="bodyContainer">
		<!-- Get rid of underline in links is from CSS -->
		<div id="logo-image" class="text-center">
			<img src="<c:url value="/images/logo.png"/>" style="margin: 200" />
		</div>
	</div>

	<footer> <jsp:include page="footer.jsp" /> </footer>


</body>
</html>