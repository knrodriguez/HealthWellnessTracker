	
	function Form() {
		var elementId = "";
		var open = false;
		
		this.setElementId = function(newElementId) {
			this.elementId = newElementId;
		};
		
		this.setOpen = function(openState) {
			this.open = openState;
		};
		
		this.getElementId = function() {
			return this.elementId;
		};
		
		this.isOpen = function() {
			return this.open;
		};	
	};	
	
	//global form object that contains the status of the last form accessed
	var currentForm = new Form();
	
	function displayForm(newFormId, info) {
		var newFormEl = document.getElementById(newFormId);
		var reopen = true;
		
		if(currentForm.isOpen()) {
			closeForm(currentForm.getElementId());
			if(newFormId !== "recordContainer")
				reopen = false;
		} 
		
		if(reopen) {
						
			if(!setToCenterWindow(newFormEl))
				setLeftAndTopCoordinates(info, newFormEl, 
									newFormEl.offsetWidth, 
									newFormEl.offsetHeight);
			
			populateRecord(newFormId, info);
			openForm(newFormId);
		}
	};
	
	function populateRecord(formId, info) {
		if(formId === "newRecordFormContainer"){
			document.getElementById("startDate").valueAsDate = new Date(info.date);
			document.getElementById("endDate").valueAsDate = new Date(info.date);
		}
		else {
			var startDate = new Date(info.event.start);
			var endDate = new Date(info.event.end);
			document.getElementById("recordId").value = info.event.id;
			document.getElementById('eventTitle').value = info.event.title;
			document.getElementById('eventStart').valueAsDate = startDate;
			document.getElementById('eventEnd').valueAsDate = endDate;
			document.getElementById('eventNotes').value = info.event.extendedProps.notes;
		}
	};
	
	function openForm(elementId){
		document.getElementById(elementId).style.visibility = "visible";
		currentForm.setElementId(elementId);
		currentForm.setOpen(true);	
	};
	
	function closeForm(elementId){
		switch(elementId){
		case 'newRecordFormContainer':
			document.getElementById("startDate").valueAsDate = null;
			document.getElementById("newRecordForm").reset();
			break;
		case 'recordContainer':
			//document.getElementById("recordForm").reset();
			document.getElementById('eventStart').innerHTML='';
			document.getElementById('eventEnd').innerHTML = '';
			break;
		default:
			break;
		}		
		
		document.getElementById(elementId).style.visibility = 'hidden'; 
		currentForm.setElementId(elementId);
		currentForm.setOpen(false);
	};
	
	function clearForm(element){
		alert(element.childNodes.length);
		for(var i = element.childNodes.length-1; i > 1; i--) {
			element.childNodes[i].innerHTML = '';	
		}
	};
	
	function setToCenterWindow(container) {
		var set = false;
		if(document.body.scrollHeight > document.body.innerWidth) {
			container.style.top = '50%';
			container.style.left= '50%';
			container.style.margin= "-" + container.offsetHeight/2 
									+ "px 0px 0px -" + container.offsetWidth/2 
									+ "px";	
			set = true;
		} 
		return set;
	};
	
	function setLeftAndTopCoordinates(info, container, containerWidth, 
										containerHeight) {
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