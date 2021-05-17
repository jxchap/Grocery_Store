$(document).ready(function() {

	$(document).ready(function() {
		$('#offerTable').DataTable();
	});

	$("#categoriesBox").change(function() {
		refreshProducts()
	});

	getCategoriesForSelectBox()

})


function getCategoriesForSelectBox() {
	$('#categoriesBox').empty();

	$.ajax({
		type: 'GET',
		url: '/getFoodCategories',
		contentType: "application/json",
		dataType: "json",
		success: function(result) {

			categoriesBox = "<div class='col-2'> Category "
				+ "<select class='form-control' id='newProductModal_modalBody_categorySelectionBox'"
				+ "	name='category' style='min-width: 250%'>"


			$.each(result, function(key1, foodCategory) {
				$("#categoriesBox").append(
					"<option>" + foodCategory + "</option>")

				$("#productModificationModal_modalBody_categorySelectionBox").append(
					"<option>" + foodCategory + "</option>"
				)

				categoriesBox += "<option>" + foodCategory + "</option>"
			});

			categoriesBox += "</select></div>"

			refreshProducts()

		},
		error: function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	})
}

