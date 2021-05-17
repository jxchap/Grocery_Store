$(document).ready(function() {


	$("body").on('click', ".modifyProductButton", function() {

		//get reference to food object for modification
		let offerIdToModify = $(this).attr('id');
		offerIdToModify = offerIdToModify.substring(offerIdToModify.indexOf(" ") + 1);
		for (var i = 0; i < currentFoodItemArrayDisplayed.length; i++) {
			if (offerIdToModify == currentFoodItemArrayDisplayed[i].foodItemId) {
				currentFoodItemToBeModified = currentFoodItemArrayDisplayed[i]
				console.log(currentFoodItemToBeModified)
			}
		}

		$("#productModificationModal").modal("toggle");
		$("#productModificationModal_modalBody").empty();

		//build radio button group
		let radioGroupStatus = ""
		if (currentFoodItemToBeModified.status == "Active") {
			radioGroupStatus =
				"Status <fieldset id='productStatus'>"
				+ "<div class='form-check'>"
				+ "<input class='form-check-input' type='radio' name='productStatusGroup' id='productStatusActive' value ='Active' checked>"
				+ "<label class='form-check-label' for='productStatusActive'>"
				+ "Active"
				+ "</label></div>"
				+ "<div class='form-check'>"
				+ "<input class='form-check-input' type='radio' name='productStatusGroup' id='productStatusInActive' value ='Inactive' >"
				+ "<label class='form-check-label' for='productStatusInActive'>"
				+ "InActive"
				+ "</label></div>"
				+ "</fieldset>"
		} else {
			radioGroupStatus =
				"Status <fieldset id='productStatus'>"
				+ "<div class='form-check'>"
				+ "<input class='form-check-input' type='radio' name='productStatusGroup' id='productStatusActive' value ='Active'>"
				+ "<label class='form-check-label' for='productStatusActive'>"
				+ "Active"
				+ "</label></div>"
				+ "<div class='form-check'>"
				+ "<input class='form-check-input' type='radio' name='productStatusGroup' id='productStatusInActive' value ='Inactive' checked >"
				+ "<label class='form-check-label' for='productStatusInActive'>"
				+ "InActive"
				+ "</label></div>"
				+ "</fieldset>"
		}




		//build modal body
		$("#productModificationModal_modalBody").append(
			"Product ID <div> <input class='form-control' type='text' id='productId' readonly value ='" + (currentFoodItemToBeModified.foodItemId) + "'/></div>"
			+ "Product Name <div> <input class='form-control' type='text' id='modifyProductName' value ='" + (currentFoodItemToBeModified.foodItemName) + "'/></div>"
			+ "Description <div> <input class='form-control' type='text' id='modifyProductDescription' value ='" + (currentFoodItemToBeModified.description) + "'/></div>"
			+ "Price <div> <input class='form-control' type='number' id='modifyProductPrice' value ='" + (currentFoodItemToBeModified.cost) + "'/></div>"
			+ "Shelf Life <div> <input class='form-control' type='number' id='modifyProductShelfLife' value ='" + (currentFoodItemToBeModified.shelfLife) + "'/></div>"
			+ "Batch Size <div> <input class='form-control' type='number' id='modifyProductBatchSize' value ='" + (currentFoodItemToBeModified.batchSize) + "'/></div>"
			+ "Reorder Quantity <div> <input class='form-control' type='number' id='modifyProductReorderQ' value ='" + (currentFoodItemToBeModified.reorderPoint) + "'/></div>"
			+ radioGroupStatus
			+ "<br>"


		)

	});

})
