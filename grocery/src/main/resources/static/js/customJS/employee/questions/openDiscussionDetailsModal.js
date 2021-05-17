$(document).ready(function() {

	$("body").on('click', ".discussionDetails", function() {

		$("#questionDetailsModal").modal("toggle");
		$('#questionDetailsModal_modalBody').empty();

		//id will be set here for use with ajax when sending question to db
		clickedQuestionId = $(this).attr('name');
		clickedQuestionId = clickedQuestionId.substring(clickedQuestionId.indexOf(" ") + 1);


		for (var i = 0; i < employeeQuestionsArray.length; i++) {
			if (employeeQuestionsArray[i].questionId == clickedQuestionId) {
				currentQuestionObject = employeeQuestionsArray[i]
			}
		}

		//build modal body
		$.each(currentQuestionObject.questionContents, function(key1, questionRow) {

			let commentDateTime = questionRow.messageDate.year + "-" + questionRow.messageDate.monthValue + "-" + questionRow.messageDate.dayOfMonth + "-" + questionRow.messageDate.hour + "-" + questionRow.messageDate.minute

			$("#questionDetailsModal_modalBody").append(
				"Author: " + questionRow.username
				+ "<br>Date/Time: " + commentDateTime
				+ "<br>Message: " + questionRow.message
				+ "<hr>")
		});




	});


});
