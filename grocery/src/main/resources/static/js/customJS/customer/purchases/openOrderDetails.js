$(document).ready(function() {
	
	$('#orderDetailTable').DataTable({
		"order": [[1, "desc"]]
	});

	$("body").on('click', ".orderDetails", function() {

		$("#orderDetailsModal").modal("toggle");

		let orderIdToView = $(this).attr('id');
		orderIdToView = orderIdToView.substring(orderIdToView.indexOf(" ") + 1);

		let orderForModal;
		for (let j = 0; j < listOfPurchases.length; j++) {
			if (listOfPurchases[j].transactionId == orderIdToView) {
				orderForModal = listOfPurchases[j]
			}
		}


		renderModalTable(orderForModal)

	});


});

function renderModalTable(orderForModal) {
	
	$('#orderDetailsModal_modalBody').empty();

	//build modal body
	$("#orderDetailsModal_modalBody").append(

		"<table id='orderDetailTable' class='display'>"
		+ "<thead>"
		+ "<tr><th>Item Name</th><th>Description</th><th>Quantity</th><th>Cost per Item</th><th>Item Tax</th><th>Item Total</th></tr>"
		+ "</thead>"
		+ "<tbody id='orderDetailTableBody'>"
		+ "</tbody></table>"

	)
	
	$('#orderDetailTable').DataTable().clear().draw()

	

	$.each(orderForModal.transactionContents, function(i, orderObject) {

		$('#orderDetailTable').DataTable().row.add(
			[
				orderObject.foodItemName,
				orderObject.foodItemDescription,
				orderObject.quantity,
				"$"+orderObject.costPerItem.toFixed(2),
				"$"+orderObject.totalTax.toFixed(2),
				"$"+orderObject.totalAmount.toFixed(2)	]
		).draw();
	})
}