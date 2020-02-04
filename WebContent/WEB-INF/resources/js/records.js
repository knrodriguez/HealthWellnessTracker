	
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
	var form = new Form();
	
	function displayForm(formId, info) {
		var formEl = document.getElementById(formId);
		var formElWidth = formEl.offsetWidth;
		var formElHeight = formEl.offsetHeight;
		
		//close form if already open
		if(form.isOpen() === true){
			document.getElementById("startDate").valueAsDate = null;
			closeForm(form.getElementId());
		}
		//set location of and open form
		else {
			if(formId === "newRecordFormContainer"){
				document.getElementById("startDate").valueAsDate = new Date(info.date);
			}
			else {
				populateRecord(info);
			}
			
			if(!setToCenterWindow(formEl)){
				setLeftAndTopCoordinates(info, formEl, formElWidth, formElHeight);
			}
			openForm(formId);
		}
	};
	
	function populateRecord(info) {
		var startDate = new Date(info.event.start);
		var endDate = new Date(info.event.start);
		document.getElementById('eventTitle').innerHTML = info.event.title;
		document.getElementById('eventStart').innerHTML += startDate.toDateString();
		document.getElementById('eventEnd').innerHTML += endDate.toDateString();
	};
	
	function openForm(elementId){
		document.getElementById(elementId).style.visibility = "visible";
		form.setElementId(elementId);
		form.setOpen(true);	
	};
	
	function closeForm(elementId){
		if(elementId === 'newRecordFormContainer'){
			document.getElementById("newRecordForm").reset();
		}
		else if (elementId === 'recordContainer'){
			//document.getElementById("recordForm").reset();
			document.getElementById('eventStart').innerHTML='';
			document.getElementById('eventEnd').innerHTML = '';
		}
		document.getElementById(elementId).style.visibility = 'hidden'; 
		form.setElementId(elementId);
		form.setOpen(false);
	};
	
	function clearForm(element){
		alert(element.childNodes.length);
		for(var i = element.childNodes.length-1; i > 1; i--) {
			element.childNodes[i].innerHTML = '';	
		}
	};
	
	function setToCenterWindow(container) {
		var set = false;
		if(document.body.scrollHeight > document.body.clientHeight){
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
		} else {
			container.style.left = info.jsEvent.clientX+'px';
		}
		
		//determine and set top coordinates of popup record form
		if ((info.jsEvent.clientY + containerHeight) >= window.innerHeight){
			container.style.top = info.jsEvent.clientY-containerHeight+'px';				
		} else {
			container.style.top = info.jsEvent.clientY+'px';
		}
	};