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
	height:100%;
	width: 100%;
}

#calendar-container {
	position: static;
	width: 100vw;
	z-index: 1;
	height:90%;
}

.fc {
	margin-right: 1%;
	margin-left: 1%;
	margin-bottom: 1%;
}

#header-container {
	position: static;
	width: 100vw;
	top: 0;
	left: 0;
	text-align: center;
	z-index: 1;
	height: 10%;
}

.fc-toolbar h2 {
  font-size: 1.75em;
  margin: 0;
  text-align: right;
  
}

.fc-toolbar .fc-left:before .fc-left:after {
    float: right;
    content: url("images/logo.png");
    
}

#newRecordContainer {
	position: fixed;
	visibility: hidden;
	display: block;
	z-index: 1000; /*overlay in front of calendar*/
	border-style: solid;
	border-width: thin;
	height: auto;
	width: auto;
	background-color: white;
}
/*works for input, not for class or id*/
	input {
		border-color: transparent;
	}
	
	input:focus, input:active, input:hover{
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
<script>
	var formOpened = false;
	function openNewRecordForm(){
		document.getElementById("newRecordContainer").style.visibility = "visible";
		formOpened = true;
	};
	
	function closeNewRecordForm(){
		document.getElementById("startDate").valueAsDate = null;
		document.getElementById('newRecordForm').reset();
		document.getElementById("newRecordContainer").style.visibility = "hidden"; 
		formOpened = false;
	};

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
				center : 'title',
				right : 'prev,next today dayGridMonth,timeGridWeek,timeGridDay,listMonth'
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
				var recordContainerWidth = recordContainer.offsetWidth;
				var recordContainerHeight = recordContainer.offsetHeight;

				
				//close form if already open
				if(formOpened === true){
					closeNewRecordForm();
				}
				else{
					//set startDate to calendar date clicked
					document.getElementById("startDate").valueAsDate = new Date(info.date);
					
					//if window is small enough to have scrollbar, set form to center of page
					if(document.body.scrollHeight > document.body.clientHeight){
						recordContainer.style.top = '50%';
						recordContainer.style.left= '50%';
						recordContainer.style.margin= "-" + recordContainer.offsetHeight/2 + "px 0px 0px -" 
													+ recordContainer.offsetWidth/2 + "px";	
					} 
					else {
						//determine and set left coordinates of popup record form
						if((info.jsEvent.clientX + recordContainerWidth) >= window.innerWidth){
							recordContainer.style.left = info.jsEvent.clientX-recordContainerWidth+'px';		
						} else {recordContainer.style.left = info.jsEvent.clientX+'px';}
						
						//determine and set top coordinates of popup record form
						if ((info.jsEvent.clientY + recordContainerHeight) >= window.innerHeight){
							recordContainer.style.top = info.jsEvent.clientY-recordContainerHeight+'px';				
						} else {recordContainer.style.top = info.jsEvent.clientY+'px';}
					}
					openNewRecordForm();
				}
			}
		});

		//render Calendar
		calendar.render();
/* 		var view = calendar.view;
		alert("The view's title is " + view.title); */
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
				<td style="text-align: right;"><h3>
						<a href="newEvent">Create New Event</a>
					</h3></td>
				<td style="text-align: right;"><h3>
						<a href="editUserProfile">Edit Profile</a>
					</h3></td>
				<!-- <td><h4>${user.getUserLogin().getUserId()}</h4></td>
				<td><h4>${user.getUserLogin().getUsername()}</h4></td> -->
			</tr>
		</table>



	</div>

	<div id='newRecordContainer' class='newRecord'>
		<form:form id='newRecordForm' class='newRecord'
			action="submitNewRecordForm" method="POST" modelAttribute="newRecord">
			<table>
				<tr>
					<td><form:input id="formInput" path="recordName"
							placeholder="New Event" /></td>
				</tr>
				<tr>
					<td>Start: <form:input type="date" id="startDate"
							path="startDate" />
					</td>
					<td><form:input type="time" id="startTime" path="startTime" />
					</td>
				</tr>
				<tr>
					<td>End: <form:input type="date" id="endDate" path="endDate" />
					</td>
					<td><form:input type="time" id="endTime" path="endTime" /></td>
				</tr>
				<tr>
					<td colspan=2>Event: <%-- 						<form:select path="event" multiple="false" >
							<form:option value="null" label="-- null value --"/>
							<form:options items="${eventList}" itemLabel="eventName"/>
						</form:select> --%> <select id="event" name="eventSelected">
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
					<td><input type="submit" id="submit-button" value="submit"></td>
					<td><input type="button" id="cancel-button" value="Close"
						onclick=closeNewRecordForm()></td>
				</tr>
			</table>
		</form:form>
	</div>
    <div id='calendar-container'>
			<div id='calendar'></div>
	</div>

</body>
</html>