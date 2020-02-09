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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".editEventLink").click(function(e) {
			e.preventDefault();
			var id = $(this).attr("id").replace("editEvent", "");
			$(".row" + id).find("*").prop("disabled", false);
			$("#editEvent" + id).hide();
			$("#deleteEvent" + id).hide();
			$("#saveEvent" + id).show();
			$("#cancelEditEvent" + id).show();
			$("#saveEvent" + id).click(function(e) {
				$("#eventForm" + id).submit();
				$("#editEvent" + id).show();
				$("#deleteEvent" + id).show();
				$("#saveEvent" + id).hide();
				$("#cancelEditEvent" + id).hide();
				$(".row" + id).find("*").prop("disabled", true);
			});
			$("#cancelEditEvent" + id).click(function(e) {
				http: //www.$.post/
				$("#eventForm" + id)[0].reset();
				$("#editEvent" + id).show();
				$("#deleteEvent" + id).show();
				$("#saveEvent" + id).hide();
				$("#cancelEditEvent" + id).hide();
				$(".row" + id).find("*").prop("disabled", true);
			});
		});
		$(".deleteEventLink").click(function(e) {
			e.preventDefault();
			var id = $(this).attr("id").replace("deleteEvent", "");
			var deleteEventName = $("#eventName" + id).val();
			$("#eventIdToDelete").val(id);
			$("#confirmationModal").modal("show");
		});
	});
</script>
</head>
<body>
	<h1>My Events</h1>
	<div id="allUserEvents">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Type</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userEvents}" var="event">
					<form:form action="editEvent" id="eventForm${event.eventId}"
						modelAttribute="editedEvent">
						<tr class="row${event.eventId}">
							<form:input type="hidden" path="eventId" value="${event.eventId}" />
							<td><form:input id='eventName${event.eventId}'
									path="eventName" disabled="true" value="${event.eventName}" /></td>
							<td><form:input id='eventCategory${event.eventId}'
									path="eventCategory" disabled="true"
									value="${event.eventCategory}" /></td>
							<td><form:input id='eventDescription${event.eventId}'
									path="eventDescription" disabled="true"
									value="${event.eventDescription}" /></td>
							<td><i class="fas fa-pencil-alt fa-lg editEventLink"
								id='editEvent${event.eventId}' style="display: inline-block;"></i>
								<i class="far fa-save fa-lg"
									id='saveEvent${event.eventId}' style="display: none;"></i></td>
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
		<div class="modal-dialog modal-dialog-centered" role="document">
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

	<div id="createNewEvent">
		<form:form action="newEvent" method="post" modelAttribute="newEvent">
			<table>
				<tr>
					<td>Event Name</td>
				</tr>
				<tr>
					<td><form:input path="eventName" placeholder="Event Name" /></td>
				</tr>
				<tr>
					<td>Event Category</td>
				</tr>
				<tr>
					<td><form:select path="eventCategory" name="eventCategory">
							<c:forEach items="${listEventCategory}" var="category">
								<form:option value="${category}">${category}</form:option>
							</c:forEach>
						</form:select></td>
				</tr>
				<tr>
					<td>Event Description</td>
				</tr>
				<tr>
					<td><form:input path="eventDescription"
							placeholder="Event Description" /></td>
				</tr>
				<tr></tr>
				<tr>
					<td align="right"><input type="submit" value="Submit"></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>