	
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
		
		if(currentForm.isOpen()) {
			closeForm(clickType);
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
	
	function populateRecord(info, clickType) {
		if(clickType === "dateClick"){
			document.getElementById("eventStart").valueAsDate = new Date(info.date);
			document.getElementById("eventEnd").valueAsDate = new Date(info.date);
			document.getElementById("event").selectedIndex = -1;
		}
		else {
			var startDate = new Date(info.event.start);
			var endDate = new Date(info.event.end);
			document.getElementById("recordIdEdit").value = info.event.id;
			document.getElementById("recordIdDelete").value = info.event.id;
			document.getElementById('eventTitle').value = info.event.title;
			document.getElementById('eventStart').valueAsDate = startDate;
			document.getElementById('eventEnd').valueAsDate = endDate;
			document.getElementById('eventNotes').value = info.event.extendedProps.notes;
			document.getElementById('currentEvent').value = info.event.extendedProps.eventId;
			document.getElementById('currentEvent').innerHTML = info.event.extendedProps.eventName;
		}
	};
	
	function openForm(clickType){
		if(clickType === "dateClick"){
			document.getElementById('recordFieldset').disabled = false;
			document.getElementById('editRecord').style.visibility = 'hidden';
			document.getElementById('deleteRecord').style.visibility = 'hidden';
			document.getElementById('submitEditedRecord').style.display = 'block';
			document.getElementById('resetEditedRecord').style.display = 'block';
		} else {
			document.getElementById('editRecord').style.visibility = 'visible';
			document.getElementById('deleteRecord').style.visibility = 'visible';
		}
		document.getElementById("recordContainer").style.visibility = "visible";
		currentForm.setOpen(true);	
		currentForm.setTypeOfClick(clickType);
	};
	
	function closeForm(clickType){
		document.getElementById("recordForm").reset();
		document.getElementById('eventStart').innerHTML='';
		document.getElementById('eventEnd').innerHTML = '';		
		document.getElementById('currentEvent').innerHTML = "";
		document.getElementById('recordFieldset').disabled = true;
		document.getElementById('submitEditedRecord').style.display = 'none';
		document.getElementById('resetEditedRecord').style.display = 'none';
		document.getElementById("recordContainer").style.visibility = 'hidden'; 
		currentForm.setOpen(false);
		currentForm.setTypeOfClick(clickType);
	};
	
	function clearForm(element){
		alert(element.childNodes.length);
		for(var i = element.childNodes.length-1; i > 1; i--) {
			element.childNodes[i].innerHTML = '';	
		}
	};
	
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
	
	function setLeftAndTopCoordinates(info) {		
		var container = document.getElementById("recordContainer");
		var containerWidth = container.offsetWidth;
		var containerHeight = container.offsetHeight;
		
		container.style.margin = '0';
		//determine and set left coordinates of popup record form
		if((info.jsEvent.clientX + containerWidth) >= window.innerWidth){
			container.style.left = info.jsEvent.clientX-containerWidth+'px';
			container.style.transform = "translate(-8%)";
		} else {
			container.style.left = info.jsEvent.clientX+'px';
			container.style.transform = "translate(8%)";
		}

		//determine and set top coordinates of popup record form
		if ((info.jsEvent.clientY + containerHeight) >= window.innerHeight){
			container.style.top = info.jsEvent.clientY-containerHeight+'px';
		} else {
			container.style.top = info.jsEvent.clientY+'px';
		}
	};