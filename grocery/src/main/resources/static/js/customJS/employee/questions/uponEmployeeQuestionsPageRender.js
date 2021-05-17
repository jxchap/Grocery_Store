$(document).ready(function() {

	$(document).ready(function() {
		$('#employeeQuestionsTable').DataTable({
			"order": [[1, "desc"]]
		});
	});


	getMyQuestions()

})


function getMyQuestions() {

	$.ajax({
		type: 'GET',
		url: '/employeeGetQuestions',
		contentType: "application/json",
		dataType: "json",
		success: function(result) {

			employeeQuestionsArray = result
			console.log(employeeQuestionsArray)


			$('#employeeQuestionsTable').DataTable().clear().draw()
			$.each(result, function(iterationValue, resultRow) {

				let questionDate = resultRow.createdOn.year + "-" + resultRow.createdOn.monthValue + "-" + resultRow.createdOn.dayOfMonth

				let questionButton =
					"<button name='questionId " + resultRow.questionId + "'type='button' class='btn btn-primary btn-sm discussionDetails'>Discussion Details</button>"
					+ "<br><br><button name='questionId " + resultRow.questionId + "'type='button' class='btn btn-success btn-sm assignToMe'>Assign To Me</button>"



				$('#employeeQuestionsTable').DataTable().row.add(
					[
						questionDate,
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


