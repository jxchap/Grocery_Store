$(document).ready(function() {

	$('#searchProductsBtn').on('click', function() {
		requestedPage = 0;
		searchForFoodItems(requestedPage)
	});

	$('body').on('click', '#leftArrowButton', function() {
		requestedPage--;
		checkPageNumber(requestedPage)
		searchForFoodItems(requestedPage)
	});

	$('body').on('click', '#rightArrowButton', function() {
		requestedPage++;
		checkPageNumber(requestedPage)
		searchForFoodItems(requestedPage)
	});

})

function checkPageNumber(pageparam) {
	if (pageparam < 0) { requestedPage = pageCount - 1 }
	if (pageparam >= pageCount) { requestedPage = 0 }
}

function searchForFoodItems(pageNumberParam) {

	$('#arrowAreaMessage').empty();
	$('#arrowArea').empty();

	$('#listProductBox').empty();
	$('#listProductBox').prepend('<div id = "listProduct"> </div>');

	var foodItemName = $("#productNameToSearchFor").val();
	var description = $("#productDescriptionToSearchFor").val();
	var foodCategory = $("#categoriesBox").val();
	var pageNumber = pageNumberParam;

	var searchTerms = {
		"foodItemName": foodItemName,
		"description": description,
		"foodCategory": foodCategory,
		"pageNumber": pageNumber
	}

	$.ajax({
		type: 'POST',
		url: '/searchFoodItems',
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify(searchTerms),
		success: function(result) {

			currentShoppingItems = result.content
			pageCount = result.totalPages

			//render arrows and navigation message
			$("#arrowAreaMessage").append("Displaying " + (result.pageable.offset + 1) + " through " + (result.pageable.offset + result.numberOfElements) + " of " + result.totalElements)
			$("#arrowArea").append(

				"<button type='button' class='btn btn-primary' id='leftArrowButton'>"
				+ "<svg xmlns='http://www.w3.org/2000/svg' width='30' height='30' fill='currentColor' class='bi bi-arrow-left' viewBox='0 0 16 16'>"
				+ "<path fill-rule='evenodd' d='M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z'></path>"
				+ "</svg>  Previous 10  </button>"
				+
				"<button type='button' class='btn btn-primary' id='rightArrowButton'>"
				+ "<svg xmlns='http://www.w3.org/2000/svg' width='30' height='30' fill='currentColor' class='bi bi-arrow-right' viewBox='0 0 16 16'>"
				+ "<path fill-rule='evenodd' d='M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z'></path>"
				+ "</svg>  Next 10  </button>"
			)

			//build product rows
			$.each(result.content, function(key1, foodObject) {
				
				let foodPriceBlock = "$" + foodObject.cost
				if(foodObject.foodCategory.offers.length > 0){
					foodPriceBlock = "<del>$" + foodObject.cost + "</del> $" + (foodObject.cost * (foodObject.foodCategory.offers[0].discountPercentage / 100)).toFixed(2)
				}

				$("#listProduct").append(
					"<table class = 'table' > "
					+ "<tr><th>Product Preview</th><th>Name</th><th>Description</th><th>Category</th><th>Price</th></tr>"
					+ "<tr><td><img src='../images/" + (foodObject.imageLocation) + ".jpg' height='150' width = '150'></td><td>" + (foodObject.foodItemName) + " </td><td> " + (foodObject.description) + "</td><td>" + (foodObject.foodCategory.foodCategoryName) + "</td><td>" + (foodPriceBlock) + "</td><td><button class='btn btn-success addCart' id ='foodItemId " + (foodObject.foodItemId) + "'>Add to Cart</button></td></tr>"
					+ "</table>")
			});

			console.log(result);
		},
		error: function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	})

}













