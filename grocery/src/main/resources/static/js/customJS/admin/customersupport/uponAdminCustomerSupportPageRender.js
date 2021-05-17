$(document).ready(function() {

	$(document).ready(function() {
		$('#employeeQuestionsTable').DataTable({
			"order": [[1, "desc"]]
		});
	});

	getListOfEmployees()



})

function getListOfEmployees() {

	$.ajax({
		type: 'GET',
		url: '/adminGetListOfEmployees',
		contentType: "application/json",
		dataType: "json",
		success: function(result) {

			console.log(result)
			optionBoxes = "<option value='     '>" + "     " + "</option> <option value='unassign'>" + "Unassign" + "</option>";

			$.each(result, function(iterationValue, resultRow) {
				optionBoxes +=
					"<option value='" + resultRow.username + "'>" + resultRow.firstname + " " + resultRow.lastname + "</option>"
			})

			getMyQuestions(optionBoxes)

		},
		error: function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	})
}

function getMyQuestions(optionBoxes) {

	$.ajax({
		type: 'GET',
		url: '/employeeGetQuestions',
		contentType: "application/json",
		dataType: "json",
		success: function(result) {

			questionsArray = result
			console.log(questionsArray)


			$('#employeeQuestionsTable').DataTable().clear().draw()
			$.each(result, function(iterationValue, resultRow) {

				let questionDate = resultRow.createdOn.year + "-" + resultRow.createdOn.monthValue + "-" + resultRow.createdOn.dayOfMonth

				let questionButton =
					"<button name='questionId " + resultRow.questionId + "'type='button' class='btn btn-primary btn-sm discussionDetails'>Discussion Details</button>"
					+ "<br><br>Change Ticket Assignment<br><select name='questionId " + resultRow.questionId + "' class = 'employeeSelect'>" + optionBoxes + "</select>"



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




