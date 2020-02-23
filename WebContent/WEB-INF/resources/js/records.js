	
	function Form() {
		var open = false;
		var typeOfClick = "";		
		
		this.setOpen = function(openState) {
			this.open = openState;
		};
		
		this.isOpen = function() {
			return this.open;
		};	
		
		this.setTypeOfClick = function(clickType) {
			this.typeOfClick = clickType;
		}
		
		this.getTypeOfClick = function(){
			return this.typeOfClick;
		}
	};	
	
	//global form object that contains the status of the last form accessed
	var currentForm = new Form();

	function displayForm(info, clickType) {
		var reopen = true;
		document.getElementById('recordForm').classList.remove('was-validated');
		
		if(currentForm.isOpen()) {
			closeForm(currentForm.getTypeOfClick());
			if(clickType === "dateClick")
				reopen = false;
		} 
		
		if(reopen) {
			if(!setToCenterWindow()){
				setLeftAndTopCoordinates(info);
			}
			populateRecord(info, clickType);
			openForm(clickType);
		}
	};
	
/*
 * Populate the record form when a date or event is clicked.
 * Parameters: info - FullCalendar object detailing the mouse click, 
 * 			   clickType - String representing whether a date or event was clicked.
 */	
	function populateRecord(info, clickType) {
		if(clickType === "dateClick"){
			//get hours and minutes values from the clicked on DateTime
			var calendarDate = new Date(info.date);
			var hours = calendarDate.getHours();
			var minutes = calendarDate.getMinutes();
			//translate the hours and minutes values into input value of time
			document.getElementById("startTime").value = calculateTime(hours ,minutes);
			document.getElementById("endTime").value = calculateTime(hours+1, minutes);
			
			//automatically set the start and end dates to the date clicked on
			document.getElementById("startDate").valueAsDate = new Date(info.date);
			document.getElementById("endDate").valueAsDate = new Date(info.date);
			//Event dropdown will be blank
			$('.selectpicker').selectpicker('refresh');
			//set form action to create new record
			document.getElementById("recordForm").action = "createRecord";
		}
		else {
			//workaround to show events with the same date
			var startDate = new Date(info.event.start);
			var endDate;
			if(info.event.end === null){
				endDate = startDate;
			}
			else {
				endDate = new Date(info.event.end);
			}
			//create recordId element to pass its value 
			var recordIdEl = document.createElement("input");
			recordIdEl.setAttribute("type","hidden");
			recordIdEl.setAttribute("id", "recordIdEl");	
			recordIdEl.setAttribute("name","recordId");
			recordIdEl.setAttribute("value", info.event.id);	
			document.getElementById("recordForm").appendChild(recordIdEl);
			
			//set the values of all record elements to be viewed and/or edited
			document.getElementById("recordIdDelete").value = info.event.id;
			document.getElementById('recordTitle').value = info.event.title;
			document.getElementById('startDate').valueAsDate = startDate;
			document.getElementById('startTime').value = info.event.extendedProps.recordStartTime;
			document.getElementById('endTime').value = info.event.extendedProps.recordEndTime;
			document.getElementById('endDate').valueAsDate = endDate;
			document.getElementById('recordNotes').value = info.event.extendedProps.notes;
			$('.selectpicker').selectpicker('val',info.event.extendedProps.eventId);
			//if user edits record, change form action to edit the record
			document.getElementById("recordForm").action = "editRecord";			
		}
	};

/*
 * Sets the record form to display
 * Parameters: clickType - String representing whether a date or event was clicked.
 */
	function openForm(clickType){
		if(clickType === "dateClick"){
			//set the form to editable
			document.getElementById('recordFieldset').disabled = false;
			document.getElementById('editRecord').style.visibility = 'hidden';
			document.getElementById('deleteRecord').style.visibility = 'hidden';
			$('.selectpicker').selectpicker('val',0);
			//show submit and reset buttons
			document.getElementById('submitEditedRecord').style.display = 'block';
			document.getElementById('resetEditedRecord').style.display = 'block';
		} else {
			//show edit and delete icons;
			document.getElementById('editRecord').style.visibility = 'visible';
			document.getElementById('deleteRecord').style.visibility = 'visible';
		}
		
		//show the record form
		document.getElementById("recordContainer").style.visibility = "visible";
		
		//set the current form's status 
		currentForm.setOpen(true);	
		currentForm.setTypeOfClick(clickType);
	};

/*
 * Reset form input values and close record form
 * Parameters: clickType - String representing whether a date or event was clicked.
 */	
	function closeForm(clickType){
		if(currentForm.getTypeOfClick() === "eventClick"){
			document.getElementById("editRecord").style.visibility = "hidden";
			document.getElementById("deleteRecord").style.visibility = "hidden";
			document.getElementById("recordIdEl").remove();
		}

		//reset/clear all form fields
		document.getElementById("recordForm").reset();
		document.getElementById('startDate').innerHTML='';
		document.getElementById('endDate').innerHTML = '';
		document.getElementById('startTime').value='';
		document.getElementById('endTime').value = '';
		document.getElementById('recordFieldset').disabled = true;
		
		//hide the submit and reset buttons, and the form.
		document.getElementById('submitEditedRecord').style.display = 'none';
		document.getElementById('resetEditedRecord').style.display = 'none';
		document.getElementById("recordContainer").style.visibility = 'hidden'; 
		$('.selectpicker').selectpicker('refresh');
		//set the current form's status 
		currentForm.setOpen(false);
		if(clickType !== "closeButton"){
			currentForm.setTypeOfClick(clickType);
		}				
	};

/*
 * Sets the location of the form if page uses scrollbar
 * Returns: boolean whether the form's coordinates have been set 
 */
	function setToCenterWindow() {
		var container = document.getElementById("recordContainer");
		var set = false;

		if(document.body.scrollHeight > document.body.clientHeight) {
			container.style.top = '50%';
			container.style.left= '50%';
			container.style.margin= "-" + container.offsetHeight/2 
									+ "px 0px 0px -"
									+ container.offsetWidth/2 + "px";	
			set = true;
		} 
		return set;
	};

/*
 * Sets the form's coordinates based on user's click coordinates
 * Parameters: info - FullCalendar object that details mouse click.
 */
	function setLeftAndTopCoordinates(info) {		
		var container = document.getElementById("recordContainer");
		var containerWidth = container.offsetWidth;
		var containerHeight = container.offsetHeight;
		
		container.style.margin = '0';
		container.style.marginLeft = '1%';
		container.style.marginRight = '1%';
		
		//determine and set left coordinates of pop-up record form
		if((info.jsEvent.clientX + containerWidth) >= window.innerWidth-50){
			container.style.left = info.jsEvent.clientX-containerWidth+'px';
			container.style.transform = "translate(-8%)";
		} else {
			container.style.left = info.jsEvent.clientX+'px';
			container.style.transform = "translate(8%)";
		}

		//determine and set top coordinates of pop-up record form
		if ((info.jsEvent.clientY + containerHeight) >= window.innerHeight-50){
			container.style.top = info.jsEvent.clientY-containerHeight+'px';
		} else {
			container.style.top = info.jsEvent.clientY+'px';
		}
	};
	
/*
 * Format the time input values when creating a record
 * Parameters: hours - int ranging from 0 to 23, minutes - int ranging from 0 to 59
 * Returns: a String representing the hours and minutes in hh:mm format. 
 */
	function calculateTime(hours, minutes){
		if(hours === null && minutes === null){
			hours = "00";
			minutes = "00";
		}
		else {
			if(hours < 10){
				hours = "0" + hours;
			}
			if (minutes === 0){
				minutes = "00";
			}
		}
		return hours + ":" + minutes;
	}