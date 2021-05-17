function handleCheckout() {

	let total = parseFloat((cartArrayTotalPrice + totalTax).toFixed(2))

	paypal.Buttons({

		createOrder: function(data, actions) {
			// This function sets up the details of the transaction, including the amount and line item details.
			return actions.order.create({
				purchase_units: [{
					amount: {
						value: total
					}
				}]
			});
		},
		onApprove: function(data, actions) {
			// This function captures the funds from the transaction.
			return actions.order.capture().then(function(details) {
				//handle the aftermath of a successful payment

				sendTransactionToBackend()

			});
		}
	}).render('#payPalDiv');

}



function sendTransactionToBackend() {

	//get order form details

	let firstName = $('#firstName').val()
	let lastName = $('#lastName').val()
	let email = $('#email').val()
	let address = $('#address').val()
	let state = $('#state').val()
	let city = $('#city').val()
	let zip = $('#zip').val()

	$.each(reducedCartArray, function(key1, foodObject) {

		if (foodObject.foodCategory.offers.length > 0) {
			foodObject.cost = foodObject.cost * (foodObject.foodCategory.offers[0].discountPercentage / 100)
		}

	})





	var jsonRequestBody = {
		"reducedCartArray": reducedCartArray,
		"firstName": firstName,
		"lastName": lastName,
		"email": email,
		"address": address,
		"state": state,
		"city": city,
		"zip": zip,

	}


	$.ajax({
		type: 'POST',
		url: '/purchaseCart',
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify(jsonRequestBody),
		success: function(result) {

			cleanUpCartGoHome()

		},
		error: function(e) {

			console.log(e.message);
		}
	})
}

function cleanUpCartGoHome() {
	cartArray = []
	let now = new Date();
	let groceryLocalCartObject = {
		"cartArray": cartArray,
		"localCartLastSaved": now
	}
	sessionStorage.setItem('groceryLocalCartObject', JSON.stringify(groceryLocalCartObject));

	window.location.href = '/home';

}
