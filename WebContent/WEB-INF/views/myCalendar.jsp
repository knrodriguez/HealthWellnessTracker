<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Calendar View</title>
<link href='https://use.fontawesome.com/releases/v5.0.6/css/all.css' rel='stylesheet'>
<link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' rel='stylesheet' />

<link href='fullcalendar/bootstrap/main.css' rel='stylesheet' />
<link href='fullcalendar/core/main.css' rel='stylesheet' />
<link href='fullcalendar/daygrid/main.css' rel='stylesheet' />
<link href='fullcalendar/timegrid/main.css' rel='stylesheet' />
<style>
html, body {
	overflow: visible; /* don't do scrollbars */
/* 	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px; */
}

#calendar-container {
	position: absolute;
	width: 100vw;
	z-index: 1;
	max-height: 90vh;
}

.fc-header-toolbar {
  /*
  the calendar will be butting up against the edges,
  but let's scoot in the header's buttons
  */
  padding-top: 1em;
  padding-left: 1em;
  padding-right: 1em;
}

.fc {
	margin-left: 1%;
	margin-right: 1%;
}

#header-container {
	position: relative;
	width: 100vw;
	top: 0;
	left: 0;
	text-align: center;
	z-index: 1;
	height: 5%;
}

#footer-container {
	position: relative;
	bottom: 0;
	left: 0;
	text-align: center;
	width: 100vw;
	z-index: 1;
}

#newRecordContainer {
	display: none;
	z-index: 1000; /*overlay in front of calendar*/
	position: absolute;
	border-style: solid;
	border-width: thin;
	width: 250px;
	height: 250px;
/* 	left: 0%;
	right: 0%;
	top: 0%; */
}

input {
	outline: graytext;
}

input:focus, input:active {
	border-color: transparent;
	border-bottom: 2px solid red;
}
</style>
<script src='fullcalendar/core/main.js'></script>
<script src='fullcalendar/daygrid/main.js'></script>
<script src='fullcalendar/timegrid/main.js'></script>
<script src='fullcalendar/interaction/main.js'></script>
<script src='/js/testingTimeGridView.js'></script>
<script src='fullcalendar/bootstrap/main.js'></script>
<script>
	var formOpened = false;
	function openNewRecordForm(){
		document.getElementById("newRecordContainer").style.display = "flex";
		formOpened = true;
	};
	
	function closeNewRecordForm(){
		document.getElementById("startDate").valueAsDate = null;
		document.getElementById('newRecordForm').reset();
		document.getElementById("newRecordContainer").style.display = "none";
		formOpened = false;
	};

	document.addEventListener('DOMContentLoaded', function() {
		//create JS variable for HTML Calendar element
		var calendarEl = document.getElementById('calendar');
		//create JS variable of loaded Calendar module
		var calendar = new FullCalendar.Calendar(calendarEl, {
			height: 'auto',
			contentHeight: 'auto',
			plugins : [ 'bootstrap','interaction', 'dayGrid', 'timeGrid' ],
			themeSystem: 'bootstrap',
			selectable : true,
			editable : true,
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'dayGridMonth,timeGridWeek,timeGridDay'
			},
			events : [ {
				title : 'All Day Event',
				description : 'description for All Day Event',
				start : '2020-01-01'
			}, {
				title : 'Long Event',
				description : 'description for Long Event',
				start : '2020-01-07',
				end : '2020-01-10'
			}, {
				groupId : '999',
				title : 'Repeating Event',
				description : 'description for Repeating Event',
				start : '2020-01-09T16:00:00'
			}, {
				groupId : '999',
				title : 'Repeating Event',
				description : 'description for Repeating Event',
				start : '2020-01-16T16:00:00'
			}, {
				title : 'Conference',
				description : 'description for Conference',
				start : '2020-01-11',
				end : '2020-01-13'
			}, {
				title : 'Meeting',
				description : 'description for Meeting',
				start : '2020-11-12T10:30:00',
				end : '2020-01-12T12:30:00'
			}, {
				title : 'Lunch',
				description : 'description for Lunch',
				start : '2020-01-12T12:00:00'
			}, {
				title : 'Meeting',
				description : 'description for Meeting',
				start : '2020-01-12T14:30:00'
			}, {
				title : 'Birthday Party',
				description : 'description for Birthday Party',
				start : '2020-01-13T07:00:00'
			}, {
				title : 'Click for Google',
				description : 'description for Click for Google',
				url : 'http://google.com/',
				start : '2020-01-28'
			} ],
			dateClick : function(info){
				var recordContainer = document.getElementById("newRecordContainer");
				var recordContainerSize = 250;
				
				if(formOpened === true){
					closeNewRecordForm();
				}
				else{
					//set startDate to calendar date clicked
					document.getElementById("startDate").valueAsDate = new Date(info.date);
					//determine and set left coordinates of popup record form
					if((info.jsEvent.clientX + recordContainerSize)> window.innerWidth){
						recordContainer.style.left = info.jsEvent.clientX-recordContainerSize+'px';		
					} else {recordContainer.style.left = info.jsEvent.clientX+'px';}
					
					//determine and set top coordinates of popup record form
					if ((info.jsEvent.clientY + recordContainerSize) > window.innerHeight){
						recordContainer.style.top = info.jsEvent.clientY-recordContainerSize+'px';				
					} else {recordContainer.style.top = info.jsEvent.clientY+'px';}
					openNewRecordForm();
				}
			}
		});
		//render Calendar
		calendar.render();
		//testing on handler -- must use built in event triggers. 
		//calendar.on('dateClick', function(info){});
	});

</script>
</head>
<body>

	<div id='header-container'> 
		<table>
			<tr>
				<td><h3>Header</h3></td>
				<td style="text-align: right;"><h3><a href="newEvent">Create New Event</a></h3></td>
				<!-- <td><h4>${user.getUserLogin().getUserId()}</h4></td>
				<td><h4>${user.getUserLogin().getUsername()}</h4></td> -->
			</tr>
		</table>
		
		

	</div>
	
	<div id='calendar-container'>
		<div id='calendar'></div>
	</div>
	
	<div id='newRecordContainer' class='newRecord'>
		<form:form id='newRecordForm' class='newRecord' action="submitNewRecordForm" method="POST" modelAttribute="newRecord">
			<table>
				<tr>
					<td><form:input id="recordName" path="recordName" placeholder="New Event"/></td>
				</tr>
<%-- 				<tr>
					<td>Event: <form:input id="dropdownEvent" path="recordTypeId"/></td>
				</tr> --%>
				<tr>
					<td>Start Date:<br>
						<form:input type="date" id="startDate" path="startDate"/>
					</td>
				</tr>
				<tr>
					<td>Event:<br>
						<form:select path="event">
							<form:option value='null' label="--Options--"/>
							<form:options items="${eventList}" itemValue="${event}" itemLabel="${event.eventName}"/>
						</form:select>
				<!-- 		<select id="event" name="event">
							<c:forEach items="${eventList}" var="event">
								<option value="${event.eventId}">${event.eventName}</option>
							</c:forEach>
						</select> -->
					</td>
				</tr>				
				<tr>
					<td>Record Notes:<br> 
						<form:input id="textinput" path="recordNotes"/>
					</td>
				</tr>
				<tr>
					<td><input type="submit" id="submit-button" value="submit"></td>
					<td><input type="button" id="cancel-button" value="Close" onclick=closeNewRecordForm()></td>
				</tr>
			</table>
		</form:form>
	</div>
	
	<div id='footer-container'>
		<h3><a href='homepage'>Home</a></h3>
	</div>

</body>
</html>