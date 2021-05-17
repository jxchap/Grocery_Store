function refreshProducts() {

	let categorySelection = $("#categoriesBox").val();

	var requestBody = {
		"categorySelection": categorySelection
	}


	$.ajax({
		type: 'POST',
		url: '/admin/searchFoodItemsByCategory',
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify(requestBody),
		success: function(result) {

			currentFoodItemArrayDisplayed = result
			
			$('#productTable').DataTable().clear().draw()
			$.each(result, function(iterationValue, resultRow) {

				$('#productTable').DataTable().row.add(
					[
						resultRow.foodItemId,
						resultRow.foodItemName,
						resultRow.description,
						resultRow.cost,
						resultRow.shelfLife,
						resultRow.batchSize,
						resultRow.reorderPoint,
						resultRow.status,
						"<button id='offerId " + resultRow.foodItemId + "'type='button' class='btn btn-warning modifyProductButton'>Modify</button>"
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













