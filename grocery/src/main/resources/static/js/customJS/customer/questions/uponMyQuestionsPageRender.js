$(document).ready(function() {

	$(document).ready(function() {
		$('#customerQuestionsTable').DataTable({
			"order": [[1, "desc"]]
		});
	});


	getMyQuestions()

})


function getMyQuestions() {

	$.ajax({
		type: 'GET',
		url: '/customerGetMyQuestions',
		contentType: "application/json",
		dataType: "json",
		success: function(result) {

			customerQuestionsArray = result
			console.log(customerQuestionsArray)


			$('#customerQuestionsTable').DataTable().clear().draw()
			$.each(result, function(iterationValue, resultRow) {

				let transactionDate = resultRow.createdOn.year + "-" + resultRow.createdOn.monthValue + "-" + resultRow.createdOn.dayOfMonth

				let questionButton =
					"<button id='questionId " + resultRow.questionId + "'type='button' class='btn btn-primary btn-sm discussionDetails'>Discussion Details</button>"



				$('#customerQuestionsTable').DataTable().row.add(
					[
						transactionDate,
						resultRow.subject,
						resultRow.orderId,
						resultRow.assignedTo,
						resultRow.status,
						questionButton
					]
				).draw();

			});
		},
		error: function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	})
}


