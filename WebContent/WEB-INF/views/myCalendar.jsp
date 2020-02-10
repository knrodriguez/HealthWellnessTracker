<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>My Calendar</title>
<link href='https://use.fontawesome.com/releases/v5.0.6/css/all.css'
	rel='stylesheet'>
<link
	href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css'
	rel='stylesheet' />

<link href='fullcalendar/bootstrap/main.css' rel='stylesheet' />
<link href='fullcalendar/core/main.css' rel='stylesheet' />
<link href='fullcalendar/daygrid/main.css' rel='stylesheet' />
<link href='fullcalendar/timegrid/main.css' rel='stylesheet' />
<link href='fullcalendar/list/main.css' rel='stylesheet' />
<style>
html, body {
	overflow: visible; /* scrollbars */
	height: 100%;
	width: 100%;
}

#calendar-container {
	position: static;
	width: 100vw;
	z-index: 1;
	height: 90%;
}

.fc {
	margin-right: 1%;
	margin-left: 1%;
	margin-bottom: 1%;
}

#header-container {
	position: relative;
	margin-right: 10%;
	top: 0; //
	bottom: -100px;
	text-align: center;
	height: 10%;
}

.fc-center {
	position: relative;
	padding-left: 10%;
	margin: 0;
	text-align: right;
	display: inline;
	vertical-align: middle;
}

.fc-toolbar .fc-left:before .fc-left:after {
	float: inherit;
	content: "test";
}

/* .newRecord {
	margin: 0.5%;
} */
.close .deleteRecord {
	font-size: 2em;
	outline: none;
	border-style: none;
}

.close:focus .deleteRecord:focus {
	outline: none;
	border-style: none;
}

#newRecordFormContainer {
	position: fixed;
	visibility: hidden;
	display: block;
	z-index: 1000; /*overlay in front of calendar*/
	border-style: solid;
	border-width: thin;
	height: auto;
	width: auto;
	background-color: white;
	top: 0;
	left: 0;
	transform: translate(0);
}

.recordForm:hover {
	
}

#recordContainer {
	position: fixed;
	visibility: hidden;
	display: block;
	z-index: 1000;
	border-style: solid;
	border-width: thin;
	height: auto;
	width: auto%;
	background-color: white;
	top: 0;
	left: 0;
	transform: translate(0);
}

/*works for input, not for class or id*/
input {
	border-color: transparent;
}

input:focus, input:active, input:hover {
	background-color: #EEEEEE;
}

div.icon-container a, i, button {
	margin: 10%;
}
</style>
<script src='fullcalendar/core/main.js'></script>
<script src='fullcalendar/daygrid/main.js'></script>
<script src='fullcalendar/timegrid/main.js'></script>
<script src='fullcalendar/interaction/main.js'></script>
<script src='fullcalendar/bootstrap/main.js'></script>
<script src='fullcalendar/list/main.js'></script>
<script src='js/records.js'></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
 $(document).ready(function() {  
	 $("#editRecord").click(function(event){
		 event.preventDefault();
		$('#recordFieldset').prop("disabled", false);
		$("#submitEditedRecord").show();
		$("#resetEditedRecord").show();
	});
 });
</script>
<script> 

	document.addEventListener('DOMContentLoaded', function() {
		//create JS variable for HTML Calendar element
		var calendarEl = document.getElementById('calendar');
		//create JS variable of loaded Calendar module
		var calendar = new FullCalendar.Calendar(calendarEl, {
			height: 'parent',
			contentHeight: 0.8*window.innerHeight,
			plugins : [ 'bootstrap','interaction', 'dayGrid', 'timeGrid', 'list'],
			themeSystem: 'bootstrap',
			selectable : true,
			editable : true,
			handleWindowResize: true,
			fixedWeekCount: false,
			eventLimit: true,
			header : {
				left : '',
				center: 'title',
				right : ' prev,next today dayGridMonth,timeGridWeek,timeGridDay,listMonth'
			},
			dateClick : function(info) {
				displayForm("newRecordFormContainer", info);
			},
			eventClick : function(info) {
				displayForm("recordContainer", info);	
			}
		});
		var calendarEvents = JSON.parse(${recordList});
		calendar.addEventSource(calendarEvents);
		//render Calendar
		calendar.render();
	});
	
</script>
</head>
<body>

	<jsp:include page="header.jsp" />

	<div id='newRecordFormContainer'
		class="newRecord shadow p-3 mb-5 bg-white rounded">
		<form:form id='newRecordForm' action="submitNewRecordForm"
			method="POST" modelAttribute="newRecord">
			<table class='newRecord shadow-sm p-3 mb-5 bg-white rounded'>
				<tr>
					<td></td>
					<td>
						<button type="button" class="close" aria-label="Close"
							onclick="closeForm('newRecordFormContainer')">
							<span aria-hidden="true">&times;</span>
						</button>
					</td>
					<!-- <img src="<c:url value="/images/" />"> -->
				</tr>
				<tr>
					<td colspan=2 style='font-size: 1.25em;'><form:input
							required="required" id="formInput" path="recordName"
							placeholder="New Event" /></td>
				</tr>
				<tr>
					<td>Start: <form:input type="date" id="startDate"
							path="startDate" />
					</td>
					<%-- 					<td><form:input type="time" id="startTime" path="startTime" /> 
					</td>--%>
				</tr>
				<tr>
					<td>End: <form:input type="date" id="endDate" path="endDate" />
					</td>
					<%-- 					<td><form:input type="time" id="endTime" path="endTime" /></td> --%>
				</tr>
				<tr>
					<td colspan=2>Event: <select id="event" name="eventSelected">
							<c:forEach items="${eventList}" var="event">
								<option value="${event.eventId}">${event.eventName}</option>
							</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td colspan=2>Notes:&nbsp;<form:input id="textinput"
							path="recordNotes" />
					</td>
				</tr>
				<tr>
					<td><input type="submit" id="submit-button" value="Submit">
						<input type="reset" id="closeNewRecordForm" value="Reset">
					</td>
				</tr>
			</table>
		</form:form>
	</div>

	<div id='calendar-container'>
		<div id='calendar'></div>
	</div>

	<div id='recordContainer' class="shadow p-3 mb-5 bg-white rounded">
		<div class="d-flex flex-row justify-content-end">
			<a class="p-2" href="#" id="editRecord"> <i
				class="fas fa-pencil-alt" style='color: gray;'></i></a> <a
				href="#confirmationModal" class="p-2" data-toggle="modal"
				id="deleteRecord"> <i class="far fa-trash-alt fa-lg"
				style="color: gray;"></i></a>
			<button type="button" class="close p-2" aria-label="Close"
				onclick="closeForm('recordContainer')">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<form:form action="editRecord" id="recordForm" class="recordForm"
			modelAttribute="updatedRecord">
			<fieldset id="recordFieldset" disabled="disabled">
				<table>
					<tr>
						<td><form:input id="eventTitle" path="recordName" type="text"
								value="" /></td>
					</tr>
					<tr>
						<td>Start:&nbsp;<form:input id='eventStart' path="startDate"
								type='date' value="" /></td>
					</tr>
					<tr>
						<td>End:&nbsp;<form:input id='eventEnd' path="endDate"
								type='date' value="" /></td>
					</tr>
					<tr>
						<td>Event:&nbsp;<form:select
								id='event' path="records.EventId">
								<form:option id="selectedEventName" value="" selected="true" />
								<form:options items="${eventList}" itemValue="eventId" itemLabel="eventName"/>
							</form:select></td>
					</tr>
					<tr>
						<td>Notes:&nbsp;<form:input id='eventNotes'
								path="recordNotes" type='text' value="" /></td>
					</tr>
				</table>
				<input type="hidden" name="recordId" id="recordIdEdit" value="" />
				<button id="submitEditedRecord" type="submit"
					class="btn btn-primary" style="display: none;">Submit</button>
				<button id="resetEditedRecord" type="reset" class="btn btn-danger"
					style="display: none;">Reset</button>
			</fieldset>
		</form:form>
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
					sure you want to delete it?</div>
				<div class="modal-footer">
					<form:form action="deleteRecord">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<input type="hidden" name="recordId" id="recordIdDelete" value="">
						<button type="submit" class="btn btn-danger"
							id="deleteRecordButton">Delete</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>