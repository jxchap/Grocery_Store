$(document).ready(function() {

	//load user details into form &
	//build shopping cart from session storage
	buildCheckOutPage()

})

function buildCheckOutPage() {


	$.ajax({
		type: 'GET',
		url: '/buildAuthenticationContext',
		contentType: "application/json",
		dataType: "json",
		success: function(result) {

			if (result !== undefined && result !== null) {

				$('#firstName').val(result.firstName)
				$('#lastName').val(result.lastName)
				$('#email').val(result.email)
				$('#address').val(result.address)
				$('#state').val(result.state)
				$('#city').val(result.city)
				$('#zip').val(result.zip)

				buildShoppingCart()
			}
		},
		error: function(e) {
			$("#checkoutNoAccount").modal("toggle");
		}
	})
}

function buildShoppingCart() {

	if (groceryLocalCartObject !== null && groceryLocalCartObject !== undefined) {

		reducedCartArray = reduceCartArray()
		$('#cartCount').append(groceryLocalCartObject.cartArray.length)
		$.each(reducedCartArray, function(key1, foodObject) {

			let foodPriceBlock = "$" + foodObject.cost.toFixed(2)
			if (foodObject.foodCategory.offers.length > 0) {
				foodPriceBlock = "<del>$" + foodObject.cost + "</del> $" + (foodObject.cost * (foodObject.foodCategory.offers[0].discountPercentage / 100)).toFixed(2)
			}




			$("#cartList").append(

				"<li class='list-group-item d-flex justify-content-between lh-condensed'>"
				+ "<div>"
				+ "<h6 class='my-0'>" + foodObject.foodItemName + " x" + foodObject.quantity + "</h6>"
				+ "<small class='text-muted'>" + foodObject.description + "</small>"
				+ "</div>"
				+ "<span class='text-muted'>" + foodPriceBlock + "</span>"
				+ "</li>"
			)
		})

		totalTax = cartArrayTotalPrice * .0825

		if (cartArrayTotalPrice < 20) {
			cartArrayTotalPrice += 5
			$("#cartList").append(

				"<li class='list-group-item d-flex justify-content-between bg-light'>"
				+ "<div class='text-danger'>"
				+ "<h6 class='my-0'>Delivery Charge</h6>"
				+ "</div> <span class='text-danger'>$5.00</span></li>"


			)
		}
		$("#cartList").append(

			"<li class='list-group-item d-flex justify-content-between bg-light'>"
			+ "<div class='text-danger'>"
			+ "<h6 class='my-0'>Sales Tax</h6>"
			+ "</div> <span class='text-danger'>$" + totalTax.toFixed(2) + "</span></li>"

			+ "<li class='list-group-item d-flex justify-content-between'>"
			+ "<span>Total (USD)</span> <strong>$" + (cartArrayTotalPrice + totalTax).toFixed(2) + "</strong>"
			+ "</li>"
		)
	}

	handleCheckout()
}

function reduceCartArray() {


	cartArrayTotalPrice = 0;
	let returnArray = []
	for (var j = 0; j < groceryLocalCartObject.cartArray.length; j++) {

		if (groceryLocalCartObject.cartArray[j].foodCategory.offers.length > 0) {
			cartArrayTotalPrice += (groceryLocalCartObject.cartArray[j].cost * (groceryLocalCartObject.cartArray[j].foodCategory.offers[0].discountPercentage / 100))
		} else {
			cartArrayTotalPrice += groceryLocalCartObject.cartArray[j].cost
		}

		let tempObject = JSON.parse(JSON.stringify(groceryLocalCartObject.cartArray[j]))
		returnArray.push(tempObject)
	}

	for (var j = 0; j < returnArray.length; j++) {
		returnArray[j].quantity = 1
	}

	returnArray.sort((a, b) => {
		return a.foodItemId - b.foodItemId;
	});

	for (var j = 1; j < returnArray.length || returnArray[j] != null;) {
		if (returnArray[j].foodItemId == returnArray[j - 1].foodItemId) {
			returnArray[j - 1].quantity = returnArray[j - 1].quantity + 1
			returnArray[j - 1].cost = returnArray[j - 1].cost + returnArray[j].cost
			returnArray.splice(j, 1)
		} else {
			j++
		}
	}

	return returnArray;

}


