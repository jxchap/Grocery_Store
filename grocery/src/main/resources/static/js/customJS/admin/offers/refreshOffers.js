function refreshOffers() {

	let categorySelection = $("#categoriesBox").val();

	var requestBody = {
		"categorySelection": categorySelection
	}


	$.ajax({
		type: 'POST',
		url: '/admin/getOffersForCategoryAdmin',
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify(requestBody),
		success: function(result) {

			$('#offerTable').DataTable().clear().draw()
			$.each(result.offers, function(iterationValue, resultRow) {

				$('#offerTable').DataTable().row.add(
					[
						resultRow.offerid,
						resultRow.offerName,
						resultRow.discountPercentage,
						resultRow.startsOn,
						resultRow.expiresOn,
						resultRow.status,
						"<button id='offerId " + resultRow.offerid + "'type='button' class='btn btn-danger deleteOfferButton'>Delete</button>"
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













