$(document).ready(function() {

	$(document).ready(function() {
		$('#offerTable').DataTable();
	});

	$("#categoriesBox").change(function() {
		refreshOffers()
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

			$.each(result, function(key1, foodCategory) {
				$("#categoriesBox").append(
					"<option>" + foodCategory + "</option>")

			});
			
			refreshOffers()

		},
		error: function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	})
}

