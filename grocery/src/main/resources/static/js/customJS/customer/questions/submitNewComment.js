$(document).ready(function() {


	$("body").on('click', "#submitCommentBtn", function() {

		let comment = $("#comment").val();




		var jsonRequestBody = {
			"comment": comment,
			"clickedQuestionId": clickedQuestionId,
		}

		$.ajax({
			type: 'POST',
			url: '/addNewCommentToQuestion',
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
		
		$("#questionDetailsModal").modal("toggle");
		$("#addCommentModal").modal("toggle");






	});




})





