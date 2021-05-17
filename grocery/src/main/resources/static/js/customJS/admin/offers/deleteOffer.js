$(document).ready(function() {

	$('body').on('click', '.deleteOfferButton', function() {

		let offerIdToDelete = $(this).attr('id');
		offerIdToDelete = offerIdToDelete.substring(offerIdToDelete.indexOf(" ") + 1);
		let categorySelection = $("#categoriesBox").val();

		var requestBody = {
			"categorySelection": categorySelection,
			"offerIdToDelete": offerIdToDelete
		}


		$.ajax({
			type: 'POST',
			url: '/admin/deleteOffer',
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(requestBody),
			success: function(result) {

				alert(result.message)
				refreshOffers()

			},
			error: function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		})
	});



})

