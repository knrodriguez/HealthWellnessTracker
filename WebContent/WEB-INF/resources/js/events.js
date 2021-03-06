/**
 * 
 */

function editMyEventsTable(){
	$(".editEventLink").click(function(e) {
		e.preventDefault();
		var id = $(this).attr("id").replace("editEvent", "");
		showSaveCancelIcons(id);
		$("#saveEvent" + id).click(function(e) {
			e.preventDefault();
			$("#eventForm" + id).submit();
		});
		$("#cancelEditEvent" + id).click(function(e) {
			e.preventDefault();
			$("#eventForm" + id)[0].reset();
			showEditDeleteIcons(id);
		});
	});
	$(".deleteEventLink").click(function(e) {
		e.preventDefault();
		var id = $(this).attr("id").replace("deleteEvent", "");
		var deleteEventName = $("#eventName" + id).val();
		$("#eventIdToDelete").val(id);
		$("#confirmationModal").modal("show");
	});
	$("#saveNewEvent").click(function(e) {
		e.preventDefault();
		$("#newEventForm").submit();
	});
	$("#cancelNewEvent").click(function(e) {
		e.preventDefault();
		$("#newEventForm")[0].reset();
	});
}


function showEditDeleteIcons(id) {
	$("#editEvent" + id).show();
	$("#deleteEvent" + id).show();
	$("#saveEvent" + id).hide();
	$("#cancelEditEvent" + id).hide();
	$(".row" + id).find("*").prop("disabled", true);
}

function showSaveCancelIcons(id) {
	$(".row" + id).find("*").prop("disabled", false);
	$("#editEvent" + id).hide();
	$("#deleteEvent" + id).hide();
	$("#saveEvent" + id).show();
	$("#cancelEditEvent" + id).show();
}