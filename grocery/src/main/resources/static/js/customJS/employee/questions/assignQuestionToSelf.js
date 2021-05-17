$(document).ready(function() {


	$("body").on('click', ".assignToMe", function() {

		let clickedQuestionId = $(this).attr('name');
		clickedQuestionId = clickedQuestionId.substring(clickedQuestionId.indexOf(" ") + 1);

		var jsonRequestBody = {
			"clickedQuestionId": clickedQuestionId,
		}

		$.ajax({
			type: 'POST',
			url: '/employeeAssignToMe',
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(jsonRequestBody),
			success: function(e) {

				alert(e.message)


				getMyQuestions()


			},
			error: function(e) {
				alert(e.message)
				console.log("ERROR: ", e);

			}
		})
		
	});

})





