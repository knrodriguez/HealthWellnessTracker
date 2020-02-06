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
<!-- originally static-->
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
	position: static;
	margin-right: 10%;
	top: 0;
	//bottom: -100px;
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

.close {
	position: relative;
	top: -13px;
	right: 0;
	margin-right: 1%;
	font-size: 2em;
}

.close:focus {
	outline:none;
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

#recordContainer {
	position: fixed;
	visibility: hidden;
	display: block;
	z-index: 1000;
	border-style: solid;
	border-width: thin;
	height: auto;
	width: auto;
	background-color: white;
	top: 0;
	left: 0;
	transform: translate(10%);	
}

/*works for input, not for class or id*/
input {
	border-color: transparent;
}

input:focus, input:active, input:hover {
	background-color: #EEEEEE;
}
</style>
<script src='fullcalendar/core/main.js'></script>
<script src='fullcalendar/daygrid/main.js'></script>
<script src='fullcalendar/timegrid/main.js'></script>
<script src='fullcalendar/interaction/main.js'></script>
<script src='/js/testingTimeGridView.js'></script>
<script src='fullcalendar/bootstrap/main.js'></script>
<script src='fullcalendar/list/main.js'></script>
<script src='js/records.js'></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
/*  $(document).ready(function() {
    $.getJSON("http://localhost:8080/HealthWellnessTracker/myCalendar",
    		function(data) {
    	alert(data)
    });
 });  */
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
			//themeSystem: 'bootstrap',
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
				//save newRecordForm dimensions
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

	<div id='header-container'>
		<table>
			<tr>
				<td><h3>Header</h3></td>
				<td style="text-align: right;"><h3>
						<a href="newEvent">Create New Event</a>
					</h3></td>
				<td style="text-align: right;"><h3>
						<a href="viewUserProfile">Edit Profile</a>
					</h3></td>
			</tr>
		</table>



	</div>

	<div id='newRecordFormContainer' class="newRecord shadow p-3 mb-5 bg-white rounded">
		<form:form id='newRecordForm'
			action="submitNewRecordForm" method="POST" modelAttribute="newRecord">
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
					<td colspan=2 style='font-size: 1.25em;'>
						<form:input required="required" id="formInput" path="recordName" 
							placeholder="New Event" />
					</td>
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
					<td colspan=2>Event: 
						<select id="event" name="eventSelected">
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
					<td>
						<input type="submit" id="submit-button" value="Submit">
						<input type="reset" id="closeNewRecordForm" value="Reset">
					</td>
				</tr>
			</table>
		</form:form>
	</div>
	
	<div id='calendar-container'>
		<div id='calendar'></div>
	</div>

	<div id='recordContainer'>
		<form:form id="recordForm" class="recordForm">
			<table>
				<tr>
					<td id='eventTitle'></td>
				</tr>
				<tr>
					<td>Start:&nbsp;</td><td id='eventStart'></td>
				</tr>
				<tr>
					<td>End:&nbsp;</td><td id='eventEnd'></td>
				</tr>
			</table>
		</form:form>
	</div>

</body>
</html>