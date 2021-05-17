$(document).ready(function() {
	

	$('body').on('click', '#submitModifiedProduct', function() {
		
		var productId = $("#productId").val();
		var foodItemName = $("#modifyProductName").val();
		var description = $("#modifyProductDescription").val();
		var cost = $("#modifyProductPrice").val();
		var shelfLife = $("#modifyProductShelfLife").val();
		var batchSize = $("#modifyProductBatchSize").val();
		var reorderPoint = $("#modifyProductReorderQ").val();
		var status = $("input:radio[name='productStatusGroup']:checked").val();
		var foodCategory = $("#productModificationModal_modalBody_categorySelectionBox").val();
		
		
		var requestBody = {
			"productId" : productId,
			"foodItemName" : foodItemName,
			"description" : description,
			"cost" : cost,
			"shelfLife" : shelfLife,
			"batchSize" : batchSize,
			"reorderPoint" : reorderPoint,
			"status" : status,
			"foodCategory" : foodCategory,			
		}


		$.ajax({
			type: 'POST',
			url: '/admin/submitModifiedProduct',
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(requestBody),
			success: function(result) {

				alert(result.message)
				$("#productModificationModal").modal("toggle");
				refreshProducts()

			},
			error: function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		})
	});



})

