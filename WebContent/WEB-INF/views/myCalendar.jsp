<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<title>My Calendar</title>
<link href='https://use.fontawesome.com/releases/v5.0.6/css/all.css'
	rel='stylesheet'>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<link href='fullcalendar/bootstrap/main.css' rel='stylesheet' />
<link href='fullcalendar/core/main.css' rel='stylesheet' />
<link href='fullcalendar/daygrid/main.css' rel='stylesheet' />
<link href='fullcalendar/timegrid/main.css' rel='stylesheet' />
<link href='fullcalendar/list/main.css' rel='stylesheet' />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
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

.fc-center {
	position: relative;
	padding-left: 10%;
	margin: 0;
	text-align: right;
	display: inline;
	vertical-align: middle;
}

.close .deleteRecord {
	font-size: 2em;
	outline: none;
	border-style: none;
}

.recordForm {
	border-style: 0px;
}

#recordContainer {
	position: fixed;
	visibility: hidden;
	display: block;
	z-index: 1000;
	border-style: solid;
	border-width: thin;
	height: auto;
	width: 400px;
	background-color: white;
	top: 0;
	left: 0;
	transform: translate(0);
}

/*works for input, not for class or id*/
div.icon-container a, i, button {
	margin: 10%;
}

.form-control {
	border: 0px;
	box-shadow: 0px;
}

.fc th {
	border: 0;
	padding-right: 5px;
	text-align: right;
}

.fc td {
	border-top-style: none;
	border-bottom-style: solid;
	border-bottom-width: 2px;
	/* 	border-left-style: none;
	border-right-style:none; */
	border-width: 1px;
}

.fc-day-grid * .fc-day {
	/* border-top-style: solid; */
	border-bottom-style: solid;
	border-left-style: solid;
	border-right-style: solid;
	border-width: 1px;
}

.fc-day-grid * td:first-child, .fc-day-grid * td:last-child {
	background-color: #f8f9fa;
}

.fc-axis ~ td {
	border-left-style: solid;
	border-right-style: solid;
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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/i18n/defaults-*.min.js"></script>
<script type="text/javascript">
 $(document).ready(function() {  
	 
	 $("#editRecord").click(function(event){
		 event.preventDefault();
		$('#recordFieldset').prop("disabled", false);
		$("#submitEditedRecord").show();
		$("#resetEditedRecord").show();
	 });
	 $("#submitEditedRecord").click(function(e) {
		 var form = $("#recordForm");
		 if (form[0].checkValidity() === false){
			 e.preventDefault();
			 e.stopPropagation();
		 }
		 form.addClass('was-validated');
		 $("#eventId").select2();

		 
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
				displayForm(info, "dateClick");
			},
			eventClick : function(info) {
				displayForm(info, "eventClick");
			},
			eventTimeFormat: {
				hour:'2-digit',
				minute: '2-digit',
				hour12: true
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

	<div id='calendar-container'>
		<div id='calendar'></div>
	</div>

	<div id='recordContainer'
		class="shadow p-3 mb-5 bg-white rounded border border-0">
		<div class="d-flex flex-row justify-content-end">
			<a class="p-2" href="#" id="editRecord" title="Edit"> <i
				class="fas fa-pencil-alt" style='color: gray;'></i></a> <a
				href="#confirmationModal" class="p-2" data-toggle="modal"
				id="deleteRecord"> <i class="far fa-trash-alt fa-lg"
				style="color: gray;"></i></a>
			<button type="button" class="close p-2" aria-label="Close"
				onclick="closeForm('recordContainer')">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<form:form id="recordForm" class="recordForm" modelAttribute="record">
			<fieldset id="recordFieldset" disabled="disabled">
				<div class="form-group">
					<form:input id="recordTitle" class="form-control form-control-lg"
						path="recordName" type="text" value="" placeholder="New Record"
						required="true" />
					<div class="valid-feedback"></div>
					<div class="invalid-feedback">Please include record name.</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-1 col-form-label col-form-label-sm">Start:&nbsp;&nbsp;</label>
					<div class="col-sm-6">
						<form:input id='startDate' class="form-control form-control-sm"
							path="startDate" type='date' value="" required="true" />
					</div>
					<div class="col-sm-5">
						<input id="startTime" class="form-control form-control-sm"
							name="timeStarts" type="time">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-1 col-form-label col-form-label-sm">End:&nbsp;&nbsp;</label>
					<div class="col-sm-6">
						<form:input id='endDate' class="form-control form-control-sm"
							path="endDate" type='date' value="" required="true" />
					</div>
					<div class="col-sm-5">
						<input id="endTime" class="form-control form-control-sm"
							name="timeEnds" type="time">
					</div>
				</div>
				<div class="form-group row dropdown">
					<label class="col-sm-2 col-form-label">Event:&nbsp;</label>
					<div class="col-sm-10">
						<select id="eventId" class="eventSelectClass selectpicker form-control"
							 data-dropup-auto="false" data-live-search="true" data-size="5" name="eventId" 
							 required="true">
								<c:forEach items="${eventList}" var="eventOption">
									<option value="${eventOption.eventId}" title="${eventOption.eventName}"
									data-subtext="${eventOption.eventCategory}">${eventOption.eventName}</option>
								</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 col-form-label">Notes:&nbsp;</label>
					<div class="col-sm-10">
						<form:input id='recordNotes' class="form-control"
							path="recordNotes" type='text' value="" />
					</div>
				</div>
				<div class="form-group row">
					<div class="col">
						<button id="submitEditedRecord" type="submit"
							class="btn btn-primary" style="display: none;">Submit</button>
					</div>
					<div class="col">
						<button id="resetEditedRecord" type="reset" class="btn btn-danger"
							style="display: none;">Reset</button>
					</div>
				</div>
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

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>