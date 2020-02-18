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
<style>

#allUserEvents {
	padding: 1%;
	left: 0;
	flex: 1 0 auto;
}

i:hover {
	color: gray; 
	cursor:pointer;
}
</style>
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
		$("#editNewEvent").click(function(e) {
			e.preventDefault();
			$(".newRow").find("*").prop("disabled", false);
			$("#editNewEvent").hide();
			$("#deleteNewEvent").hide();
			$("#saveNewEvent").show();
			$("#cancelNewEvent").show();
			$("#saveNewEvent").click(function(e){
				e.preventDefault();
				$("#newEventForm").submit();
				$("#editNewEvent").show();
				$("#deleteNewEvent").show();
				$("#saveNewEvent").hide();
				$("#cancelNewEvent").hide();
				$(".newRow").find("*").prop("disabled", true);		
			});
			$("#cancelNewEvent").click(function(e) {
				e.preventDefault();
				$("#newEventForm")[0].reset();
				$("#editNewEvent").show();
				$("#deleteNewEvent").show();
				$("#saveNewEvent").hide();
				$("#cancelNewEvent").hide();
				$(".newRow").find("*").prop("disabled", true);	
			});
		});
	});
</script>
</head>
<body>

	<jsp:include page="header.jsp" />
	<div id="allUserEvents">
		<h2>My Events</h2>
		<table class="table table-striped table-hover">
			<thead class="thead-light">
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Type</th>
					<th scope="col">Description</th>
					<th scope="col"></th>
					<th scope="col"></th>

				</tr>
			</thead>
			<tbody>
				<form:form id="newEventForm" action="newEvent" method="post"
					modelAttribute="newEvent">
					<tr class="newRow">
						<td><form:input id="newEventName" path="eventName"
								disabled="true" value="" /></td>
						<td><form:select id="newEventCategory" path="eventCategory"
								disabled="true">
								<form:options items="${listEventCategory}" />
							</form:select></td>
						<td><form:input id="newEventDescription"
								path="eventDescription" disabled="true" />
						<td><i class="fas fa-pencil-alt fa-lg" id='editNewEvent'
							style="display: inline-block;"></i> <i class="far fa-save fa-lg"
							id='saveNewEvent' style="display: none;"></i></td>
						<td><i class="fas fa-minus fa-lg" id="deleteNewEvent"
							disabled="true" style="display: inline-block; color:gray;"></i> <i
							class="far fa-times-circle fa-lg" id="cancelNewEvent"
							style="display: none;"></i></td>
					</tr>
				</form:form>
				<c:forEach items="${userEvents}" var="event">
					<form:form action="editEvent" id="eventForm${event.eventId}"
						modelAttribute="editedEvent">
						<tr class="row${event.eventId}">
							<form:input type="hidden" path="eventId" value="${event.eventId}" />
							<td><form:input id='eventName${event.eventId}'
									path="eventName" disabled="true" value="${event.eventName}" /></td>
							<td><form:select id='eventCategory${event.eventId}'
									path="eventCategory" disabled="true">
									<form:option value="${event.eventCategory}" selected="true" />
									<form:options items="${listEventCategory}" />
								</form:select></td>
							<td><form:input id='eventDescription${event.eventId}'
									path="eventDescription" disabled="true"
									value="${event.eventDescription}" /></td>
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

	<jsp:include page="footer.jsp" />
</body>
</html>