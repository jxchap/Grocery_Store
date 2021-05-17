$(document).ready(function() {

	$("body").on('click', ".askQuestion", function() {

		$("#askQuestionModal").modal("toggle");
		$('#askQuestion_ModalBody').empty();

		//id will be set here for use with ajax when sending question to db
		orderIdQuestionAsking = $(this).attr('id');
		if (orderIdQuestionAsking !== undefined && orderIdQuestionAsking !== null) {
			orderIdQuestionAsking = orderIdQuestionAsking.substring(orderIdQuestionAsking.indexOf(" ") + 1);
		}


		console.log(orderIdQuestionAsking)

		//build modal body
		$("#askQuestion_ModalBody").append(
			"<input id = 'subject' style='width: 500px !important' maxlength='100' placeholder='Subject' ></input><br><br>"
			+ "<textarea id='question' style='height: 250px !important; width: 750px !important' maxlength='1200' placeholder='Your Question...'></textarea>"
			+ "<br><input class='btn-sm btn-primary' type='button' id='submitQuestionBtn' value='Submit Question'/> "
		)

	});


});
