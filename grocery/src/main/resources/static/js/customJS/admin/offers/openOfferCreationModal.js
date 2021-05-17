$(document).ready(function() {

	$('#addOfferButton').on('click', function() {
		$("#offerCreationModal").modal("toggle");
		$('#offerCreationModal_modalBody').empty();
		
		
		//build modal body
		$("#offerCreationModal_modalBody").append(
			
			 "Offer Name <div> <input class='form-control' type='text' id='newOffername'/></div>"
			+ "Discount Percentage <div> <input class='form-control' type='number' id='newOfferDiscountPercentage'/></div>"
			+ "Start Date <div> <input class='form-control' type='date' id='newOfferStartDate' /></div>"
			+ "End Date <div> <input class='form-control' type='date' id='newOfferEndDate' /></div>"
			+ "<input class='btn-sm btn-primary' type='button' id='submitOffer' value='Submit New Offer'/> "
			
		)
		
		//set default dates for modal
		var nextDayDate = new Date();
		nextDayDate.setDate(nextDayDate.getDate() + 1);

		document.getElementById("newOfferStartDate").valueAsDate = new Date();
		document.getElementById("newOfferEndDate").valueAsDate = nextDayDate;
	});

})
