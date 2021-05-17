$(document).ready(function() {

	$("body").on('click', ".addCart", function() {

		let itemIdToAdd = $(this).attr('id');

		itemIdToAdd = itemIdToAdd.substring(itemIdToAdd.indexOf(" ") + 1);

		for (var i = 0; i < currentShoppingItems.length; i++) {
			let tempString = "" + currentShoppingItems[i].foodItemId

			if (itemIdToAdd == tempString) {

				cartArray.push(currentShoppingItems[i])
				let now = new Date();
				let groceryLocalCartObject = {
					"cartArray": cartArray,
					"localCartLastSaved": now
				}
				sessionStorage.setItem('groceryLocalCartObject', JSON.stringify(groceryLocalCartObject));
				//console.log(JSON.parse(sessionStorage.getItem('groceryObject')));


			}
		}


		rerenderCart()

	});
}); 