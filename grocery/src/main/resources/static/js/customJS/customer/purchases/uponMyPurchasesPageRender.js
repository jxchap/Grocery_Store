$(document).ready(function() {

	$(document).ready(function() {
		$('#customerPurchasesTable').DataTable({
			"order": [[1, "desc"]]
		});
	});


	getCustomerTransactions()

})


function getCustomerTransactions() {

	$.ajax({
		type: 'GET',
		url: '/getCustomerTransactions',
		contentType: "application/json",
		dataType: "json",
		success: function(result) {

			listOfPurchases = result
			console.log(listOfPurchases)

			$('#customerPurchasesTable').DataTable().clear().draw()
			$.each(result, function(iterationValue, resultRow) {

				let transactionDate = resultRow.transactionDate.year + "-" + resultRow.transactionDate.monthValue + "-" + resultRow.transactionDate.dayOfMonth


				let tax = 0;
				let totalAmount = 0;
				for (let j = 0; j < resultRow.transactionContents.length; j++) {
					tax += resultRow.transactionContents[j].totalTax
					totalAmount += resultRow.transactionContents[j].totalAmount
				}

				let questionButton = "<button id='orderId " + resultRow.transactionId + "'type='button' class='btn btn-success btn-sm askQuestion'>Ask Question</button>"
					+ "<br><br>"
					+ "<button id='orderId " + resultRow.transactionId + "'type='button' class='btn btn-primary btn-sm orderDetails'>Order Details</button>"


				let totalCharge = parseFloat(totalAmount.toFixed(2)) +  parseFloat(resultRow.deliveryCharge)
				$('#customerPurchasesTable').DataTable().row.add(
					[
						resultRow.transactionId,
						transactionDate,
						resultRow.deliveryAddress,
						resultRow.deliveryCity,
						resultRow.deliveryState,
						resultRow.deliveryZipcode,
						"$" + resultRow.deliveryCharge,
						"$" + tax.toFixed(2),
						"$" + totalCharge,
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


