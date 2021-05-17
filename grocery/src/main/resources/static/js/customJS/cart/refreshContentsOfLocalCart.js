function refreshContentsOfLocalCart() {


	$.ajax({
		type: 'GET',
		url: '/getCart',
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			console.log(result)
			cartArray = result;
			rerenderCart()

		},
		error: function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	})

}













