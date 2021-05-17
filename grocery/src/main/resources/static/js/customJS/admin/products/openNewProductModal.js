$(document).ready(function() {


	$("body").on('click', "#addProductButton", function() {


		$("#newProductModal").modal("toggle");
		$("#newProductModal_modalBody").empty();



		//build modal body
		$("#newProductModal_modalBody").append(
			"<form enctype='multipart/form-data' action='/admin/submitNewProduct' method = 'post'>"
			+ "Product Name <div> <input class='form-control' type='text' name='productName' /></div>"
			+ "Product Image <div> <input type='file' name='productImage' multiple><br></div>"
			+ categoriesBox
			+ "<br>Description <div> <input class='form-control' type='text' name='description' /></div>"
			+ "Price <div> <input class='form-control' type='number' name='price' /></div>"
			+ "Shelf Life <div> <input class='form-control' type='number' name='shelfLife' /></div>"
			+ "Batch Size <div> <input class='form-control' type='number' name='batchSize' /></div>"
			+ "Reorder Quantity <div> <input class='form-control' type='number' name='reorderQuantity' /></div>"
			+ "Status <fieldset name='status'>"
			+ "<div class='form-check'>"
    		+ "<input class='form-check-input' type='radio' name='productStatusGroup' id='productStatusActive' value ='Active' checked>"
   			+ "<label class='form-check-label' for='productStatusActive'>"
   			+ "Active"
   			+ "</label></div>"
			+ "<div class='form-check'>"
   			+ "<input class='form-check-input' type='radio' name='productStatusGroup' id='productStatusInActive' value ='Inactive' >"
   			+ "<label class='form-check-label' for='productStatusInActive'>"
   			+ "InActive"
			+ "</label></div>"
  			+ "</fieldset>"
			+ "<br><input class = 'btn btn-primary' type='submit' value='Submit New Product'/>"
			+ "</form>"
			

		)

	});

})
