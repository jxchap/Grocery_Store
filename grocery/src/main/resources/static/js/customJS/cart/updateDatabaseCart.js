function updateDatabaseCart() {


	let newCart = []
	for (var i = 0; i < cartArray.length; i++) {
		newCart.push(cartArray[i].foodItemId)
	}

	var jsonRequestBody = {
		"newCart": newCart
	}

	$.ajax({
		type: 'POST',
		url: '/updateDatabaseCart',
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify(jsonRequestBody),
		success: function(result) {
			
			
			
			rerenderCart()

		},
		error: function(e) {

			console.log(e.message);
		}
	})

}















