$(document).ready(function() {


	$("body").on('click', "#submitQuestionBtn", function() {

		let question = $("#question").val();
		let subject = $("#subject").val();



		var jsonRequestBody = {
			"questionContents": question,
			"orderId": orderIdQuestionAsking,
			"subject": subject
		}

		$.ajax({
			type: 'POST',
			url: '/submitNewQuestion',
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(jsonRequestBody),
			success: function(e) {

				alert(e.message)

				if (typeof getMyQuestions !== 'undefined') {
					getMyQuestions()
				}

			},
			error: function(e) {
				alert(e.message)
				console.log("ERROR: ", e);

			}
		})

		$("#askQuestionModal").modal("toggle");






	});




})





