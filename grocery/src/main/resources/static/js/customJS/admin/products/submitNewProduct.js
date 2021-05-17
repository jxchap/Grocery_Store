$(document).ready(function() {


	$('body').on('click', '#submitNewProduct', function() {



		var foodItemName = $("#newProductName").val();
		var description = $("#newProductDescription").val();
		var cost = $("#newProductPrice").val();
		var shelfLife = $("#newProductShelfLife").val();
		var batchSize = $("#newProductBatchSize").val();
		var reorderPoint = $("#newProductReorderQ").val();
		var status = $("input:radio[name='productStatusGroup']:checked").val();
		var foodCategory = $("#newProductModal_modalBody_categorySelectionBox").val();


		var requestBody = {
			"foodItemName": foodItemName,
			"description": description,
			"cost": cost,
			"shelfLife": shelfLife,
			"batchSize": batchSize,
			"reorderPoint": reorderPoint,
			"status": status,
			"foodCategory": foodCategory,
		}


		var formData = new FormData();
		var productImage = $('input[name="img"]').get(0).files[0];
		console.log(productImage)

		//For image file
		formData.append('productImage', productImage);
		formData.append('requestBody', requestBody);




		console.log(formData)




		$.ajax({
			type: 'POST',
			url: '/admin/submitNewProduct',
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(formData),
			success: function(result) {

				alert(result.message)
				$("#newProductModal").modal("toggle");
				refreshProducts()

			},
			error: function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		})

	});



})

