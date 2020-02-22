<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Events | Health Wellness Tracker</title>
<link href='https://use.fontawesome.com/releases/v5.0.6/css/all.css'
	rel='stylesheet'>
<link
	href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css'
	rel='stylesheet' />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="js/events.js"></script>
<style>
#allUserEvents {
	padding: 1%;
	left: 0;
	flex: 1 0 auto;
}

i:hover {
	color: gray;
	cursor: pointer;
}

.table td {
	vertical-align: middle !important;
	text-align: center;
}

.table td input {
	vertical-align: middle !important;
}

.form-control:disabled, .form-control[readonly] {
	background: none !important;
	border: none;
	outline: none;
	color: black;
}

select.dropdown:disabled {
	display: none;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		editMyEventsTable();
	});
</script>
</head>
<body>

	<jsp:include page="header.jsp" />
	<div id="allUserEvents" class="table-responsive">
		<h2>My Events</h2>
		<div class="error" role="alert">
			<c:if test="${message != null}">
				<div class="alert alert-danger col-md-10">
					<c:out value="${message}" />
				</div>
			</c:if>
		</div>
		<table class="table table-sm table-striped eventTable">
			<thead class="thead-light">
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Type</th>
					<th scope="col">Description</th>
					<th scope="col"></th>
					<th scope="col"></th>

				</tr>
			</thead>
			<tbody class="centerItems">
				<form:form id="newEventForm" action="myEvents" method="post"
					modelAttribute="newEvent">
					<tr class="newRow">
						<td><div class="form-group">
								<form:input id="newEventName" class="form-control"
									path="eventName" value="" />
								<form:errors path="eventName" />
							</div></td>
						<td><div class="form-group dropdown">
								<form:select id="newEventCategory" class="form-control"
									path="eventCategory">
									<form:options items="${listEventCategory}" />
								</form:select>
							</div></td>
						<td><div class="form-group">
								<form:input id="newEventDescription" class="form-control"
									path="eventDescription" />
							</div></td>
						<td><i class="far fa-save fa-lg" id='saveNewEvent'></i></td>
						<td><i class="far fa-times-circle fa-lg" id="cancelNewEvent"></i></td>
					</tr>
				</form:form>
				<c:forEach items="${userEvents}" var="event">
					<form:form action="editEvent" id="eventForm${event.eventId}"
						modelAttribute="editedEvent">
						<tr class="row${event.eventId}">
							<form:input type="hidden" path="eventId" value="${event.eventId}" />
							<td><div class="form-group">
									<form:input id='eventName${event.eventId}' class="form-control"
										path="eventName" disabled="true" value="${event.eventName}" />
								</div></td>
							<td><div class="form-group dropdown">
									<form:select id='eventCategory${event.eventId}'
										class="form-control" path="eventCategory" disabled="true">
										<form:option value="${event.eventCategory}" selected="true" />
										<form:options items="${listEventCategory}" />
									</form:select>
								</div></td>
							<td><div class="form-group">
									<form:input id='eventDescription${event.eventId}'
										class="form-control" path="eventDescription" disabled="true"
										value="${event.eventDescription}" />
								</div></td>
							<td><i class="fas fa-pencil-alt fa-lg editEventLink"
								id='editEvent${event.eventId}' style="display: inline-block;"></i>
								<i class="far fa-save fa-lg" id='saveEvent${event.eventId}'
								style="display: none;"></i></td>
							<td><i class="fas fa-minus fa-lg deleteEventLink"
								id="deleteEvent${event.eventId}" style="display: inline-block;"></i>
								<i class="far fa-times-circle fa-lg"
								id="cancelEditEvent${event.eventId}" style="display: none;"></i></td>
						</tr>
					</form:form>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="modal fade" id="confirmationModal" tabindex="-1"
		role="dialog" aria-labelledby="confirmationModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="confirmationModalLabel">Confirm
						Delete</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="confirmationModalBody">Are you
					sure you want to delete ${eventNameToDelete}?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<form:form action="deleteEvent">
						<input type="hidden" id="eventIdToDelete" name="eventId" value="">
						<button type="submit" class="btn btn-danger" id="deleteButton">Delete</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>