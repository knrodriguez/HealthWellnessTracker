<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error 404</title>
</head>
<style>
body{
	height:100%;
}
#bodyContainer{
	height: 100%;
	width: 100%;
	text-align: center;
	background-color: #f8f9fa;
}
#bodyContainer h1{
	padding: 2%;
}
#bodyContainer h3{
	padding: 2%;
}

</style>
<body>
	
	<jsp:include page="generalHeader.jsp" />
	<div id="bodyContainer">
		<h1>Page Not Found</h1>
		<h3>Oops, looks like this page is lost. Sorry about that.</h3>
		<h4>
			<a href="homepage">Go back to the Health &amp; Wellness Tracker Home
				page.</a>
		</h4>

	</div>
	<jsp:include page="footer.jsp" />

</body>
</html>