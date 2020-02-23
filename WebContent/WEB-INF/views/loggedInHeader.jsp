<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Logged In Header</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<style>
.header {
	background-color: #bad3e4;
}

.link {
	color: #495057 !important;
	text-shadow: white;
}

.link:hover {
	color: #868e96 !important;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light header"> <span
		class="navbar-brand" id="pageHeader"></span>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav">
			<li class="nav-item ml-auto align-self-center"><a
				class="nav-link link" href="viewUserProfile">Profile </a></li>
			<li class="nav-item ml-auto align-self-center"><a
				class="nav-link link" href="myEvents">Events </a></li>
			<li class="nav-item ml-auto align-self-center"><a
				class="nav-link link" href="myCalendar">Calendar</a></li>
			<li class="nav-item ml-auto align-self-center"><a
				class="nav-link link" href="logout">Logout</a></li>
		</ul>
	</div>
	<span class="d-flex navbar-brand mr-auto p-2">Health &amp;
		Wellness Tracker</span> </nav>

	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script>
		$('#pageHeader').html(document.title);
	</script>
</body>
</html>
