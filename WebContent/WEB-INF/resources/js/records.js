	
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
	
	function populateRecord(info, clickType) {
		if(clickType === "dateClick"){
			document.getElementById("startDate").valueAsDate = new Date(info.date);
			document.getElementById("endDate").valueAsDate = new Date(info.date);
			document.getElementById("eventId").selectedIndex = -1;
			document.getElementById("recordForm").action = "submitNewRecordForm";
		}
		else {
			var startDate = new Date(info.event.start);
			var endDate = new Date(info.event.end);
			var recordIdEl = document.createElement("input");
			recordIdEl.type = "text";
			recordIdEl.id = "recordIdEl";
			recordIdEl.style.display = "none";
			recordIdEl.value = info.event.id;
			document.getElementById("recordIdDelete").value = info.event.id;
			document.getElementById('recordTitle').value = info.event.title;
			document.getElementById('startDate').valueAsDate = startDate;
			document.getElementById('endDate').valueAsDate = endDate;
			document.getElementById('recordNotes').value = info.event.extendedProps.notes;
			document.getElementById('currentEvent').value = info.event.extendedProps.eventId;
			document.getElementById('currentEvent').innerHTML = info.event.extendedProps.eventName;
			document.getElementById("recordForm").action = "editRecord";			
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
		if(clickType === "eventClick"){
			document.getElementById("editRecord").style.visibility = "hidden";
			document.getElementById("deleteRecord").style.visibility = "hidden";
			var recordIdEl = document.getElementById("recordIdEl");
			recordIdEl.remove();
		}
		document.getElementById("recordForm").reset();
		document.getElementById('startDate').innerHTML='';
		document.getElementById('endDate').innerHTML = '';		
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