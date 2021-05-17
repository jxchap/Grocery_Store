$(document).ready(function() {

	$(document).ready(function() {
		$('#replenishmentTable').DataTable({
			"order": [[ 5, "asc" ],[1, "desc"]]
		});
	});


	refreshPurchases()

})


function refreshPurchases() {

	$.ajax({
		type: 'GET',
		url: '/admin/getPurchasesAdmin',
		contentType: "application/json",
		dataType: "json",
		success: function(result) {

			$('#replenishmentTable').DataTable().clear().draw()
			$.each(result, function(iterationValue, resultRow) {
				
				let paymentButton = "<button id='orderId " + resultRow.orderId + "'type='button' class='btn btn-success confirmPurchase'>Confirm Payment</button>"
				
				if (resultRow.paid == true){
					paymentButton = "N/A"
				}
				
				
				
				resultRow.paid = resultRow.paid == false ? "Not Paid" : "Paid"
				
				
				$('#replenishmentTable').DataTable().row.add(
					[
						resultRow.orderId,
						resultRow.orderDate,
						resultRow.foodItemId,
						resultRow.foodItemName,
						resultRow.quantityOrdered,
						resultRow.paid,
						paymentButton
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




