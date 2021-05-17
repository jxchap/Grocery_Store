$(document).ready(function() {
	
	


	$('body').on('click', '#submitOffer', function() {
		
		let categorySelection = $("#categoriesBox").val();
	
		var offerName = $("#newOffername").val();
		var discountPercentage = $("#newOfferDiscountPercentage").val();
		var startsOn = $("#newOfferStartDate").val();
		var expiresOn = $("#newOfferEndDate").val();
		

		

		var offerBody = {
			"offerName": offerName,
			"discountPercentage": discountPercentage,
			"startsOn": startsOn,
			"expiresOn": expiresOn,
			"category" : categorySelection
		}
		
		var requestBody = {
			"categorySelection" : categorySelection,
			"offerBody" : offerBody			
		}


		$.ajax({
			type: 'POST',
			url: '/admin/submitNewOffer',
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(requestBody),
			success: function(result) {

				alert(result.message)
				$("#offerCreationModal").modal("toggle");
				refreshOffers()

			},
			error: function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		})
	});



})

