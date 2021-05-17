$(document).ready(function() {


	$("body").on('change', ".employeeSelect", function() {
		
		let employeeBeingAssigned = $(this).val();

		let clickedQuestionId = $(this).attr('name');
		clickedQuestionId = clickedQuestionId.substring(clickedQuestionId.indexOf(" ") + 1);

		var jsonRequestBody = {
			"clickedQuestionId": clickedQuestionId,
			"assignedTo" : employeeBeingAssigned 
		}


		$.ajax({
			type: 'POST',
			url: '/adminAssignToEmployee',
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(jsonRequestBody),
			success: function(e) {

				alert(e.message)


				getMyQuestions(optionBoxes)


			},
			error: function(e) {
				alert(e.message)
				console.log("ERROR: ", e);

			}
		})
		
	});

})





