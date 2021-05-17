$(document).ready(function() {

	$("body").on('click', "#cart", function() {
		$("#cartModal").modal("toggle");

	});

	$("body").on('click', ".minus", function() {
		let itemIdtoSubtract = $(this).attr('id');
		itemIdtoSubtract = itemIdtoSubtract.substring(itemIdtoSubtract.indexOf(" ") + 1);
		subtractCartItemBy1(itemIdtoSubtract)

	});

	$("body").on('click', ".plus", function() {

		let itemIdtoAdd = $(this).attr('id');
		itemIdtoAdd = itemIdtoAdd.substring(itemIdtoAdd.indexOf(" ") + 1);
		increaseCartItemBy1(itemIdtoAdd)

	});

	$("body").on('click', ".deleteItem", function() {

		let itemIdtoDelete = $(this).attr('id');
		itemIdtoDelete = itemIdtoDelete.substring(itemIdtoDelete.indexOf(" ") + 1);
		deleteItemFromCart(itemIdtoDelete)

	});
});


function subtractCartItemBy1(itemIdtoSubtract) {

	outer: for (var j = 0; j < cartArrayReduced.length; j++) {
		if (cartArrayReduced[j].foodItemId == itemIdtoSubtract & cartArrayReduced[j].quantity > 1) {
			for (var j = 0; j < cartArray.length; j++) {
				if (cartArray[j].foodItemId == itemIdtoSubtract) {
					cartArray.splice(j, 1)
					rerenderCart()
					break outer;
				}
			}
		}
	}

	let now = new Date();
	let groceryLocalCartObject = {
		"cartArray": cartArray,
		"localCartLastSaved": now
	}
	sessionStorage.setItem('groceryLocalCartObject', JSON.stringify(groceryLocalCartObject));
}

function increaseCartItemBy1(itemIdToAdd) {

	outer: for (var i = 0; i < cartArray.length; i++) {
		let tempString = "" + cartArray[i].foodItemId

		if (itemIdToAdd == tempString) {
			cartArray.push(cartArray[i])
			break outer;
		}
	}

	let now = new Date();
	let groceryLocalCartObject = {
		"cartArray": cartArray,
		"localCartLastSaved": now
	}
	sessionStorage.setItem('groceryLocalCartObject', JSON.stringify(groceryLocalCartObject));
	rerenderCart()
}

function deleteItemFromCart(itemIdtoDelete) {


	for (var j = 0; j < cartArray.length; j++) {
		if (cartArray[j].foodItemId == itemIdtoDelete) {
			cartArray.splice(j, 1)
			j--

		}
	}

	let now = new Date();
	let groceryLocalCartObject = {
		"cartArray": cartArray,
		"localCartLastSaved": now
	}
	sessionStorage.setItem('groceryLocalCartObject', JSON.stringify(groceryLocalCartObject));
	rerenderCart()

}





