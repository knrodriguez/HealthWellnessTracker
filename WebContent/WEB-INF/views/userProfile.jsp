<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile</title>
<style>
body {
	background-color: #f8f9fa !important;
}
#profileContainer {
	flex: 1 0 auto;
	margin: 1%;
}

.footer {
	position: relative;
	right: 0;
	bottom: 0;
	left: 0;
	padding: 1rem;
	background-color: #efefef;
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="loggedInHeader.jsp" />
	<div id="profileContainer">
		<h2>${connectedUser.getUserLogin().getUsername()}'s Profile</h2>
		<div class="error" role="alert">
				<c:if test="${alertCode != null}">
					<!-- StatusCodes with code of 2000+ are success codes -->
					<c:set value="${alertCode.getCode() < 2000 ? 'alert-danger' : 'alert-success'}" 
						var="alertStyle" />
						<div class="alert ${alertStyle} col-md-10">
							<c:out value="${alertCode.toString()}" />
						</div>
					</c:if>
			</div>
		<form:form action="editUserProfile" modelAttribute="userProfile"
			method="POST">
			<div class="form-group col-md-4">
				<label for="name">Name:</label>
				<form:input type="text" class="form-control" 
				path="name" value="${connectedUser.getName()}" />
			</div>
			<div class="form-group col-md-4">
				<label for="birthdate">Birthdate:</label>
				<form:input type="date" class="form-control"
					path="birthdate" value="${connectedUser.getBirthdate()}" />
			</div>
			<div class="form-group col-md-4">
				<label for="age">Age:</label>
				<form:input type="number" class="form-control"
					path="age" value="${connectedUser.getAge()}" required="true" min="0" max="120"/>
			</div>
			<div class="form-group col-md-4">
				<label for="country">Country:</label>
				<form:input type="text" class="form-control"
					path="country" value="${connectedUser.getCountry()}" />
			</div>
			<div class="form-group col-md-4">
				<label for="emailAddress">Email Address:</label>
				<form:input type="email" class="form-control"
					path="emailAddress" value="${connectedUser.getEmailAddress()}" />
			</div>
			<button type="submit" class="btn btn-primary" style='margin-left:2%;'>Submit</button>
		</form:form>
	</div>
	<jsp:include page="footer.jsp" />

</body>
</html>