$(document).ready(function() {

	$('body').on('click', '.confirmPurchase', function() {

		let purchaseToConfirm = $(this).attr('id');
		purchaseToConfirm = purchaseToConfirm.substring(purchaseToConfirm.indexOf(" ") + 1);
		confirmPurchases(purchaseToConfirm)
	})

})


function confirmPurchases(purchaseToConfirm) {



	let jsonRequestBody = {
		"purchaseToConfirm": purchaseToConfirm
	}

	$.ajax({
		type: 'POST',
		url: '/admin/confirmPurchaseAdmin',
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify(jsonRequestBody),
		success: function(result) {

			refreshPurchases()

		},
		error: function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	})
}





