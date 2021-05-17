$(document).ready(function() {

	displayRandomOffers()

	getCategoriesForSelectBox()

	buildAuthenticationContextAndSyncCart()


})

function getCategoriesForSelectBox() {
	$('#categoriesBox').empty();
	$('#categoriesBox').prepend("<option>All</option>");


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

		},
		error: function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	})
}

function buildAuthenticationContextAndSyncCart() {

	let groceryLocalCartObject = JSON.parse(sessionStorage.getItem('groceryLocalCartObject'));

	$.ajax({
		type: 'GET',
		url: '/buildAuthenticationContext',
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			if (result !== undefined) {

				$('#currentUserMessage').empty();
				$("#currentUserMessage").append(
					"Welcome " + result.firstName + "!"
				)

				isLoggedIn = true;

				if (groceryLocalCartObject !== undefined && groceryLocalCartObject !== null) {
					let dbCartTimeStamp = new Date(result.dbCartLastSaved)
					let localCartTimeStamp = new Date(groceryLocalCartObject.localCartLastSaved)

					if (dbCartTimeStamp < localCartTimeStamp) {
						cartArray = groceryLocalCartObject.cartArray;
						updateDatabaseCart()
					}
					else {
						refreshContentsOfLocalCart()
					}
				} else {
					refreshContentsOfLocalCart()
				}
				setTimeout(function() { setInterval(updateDatabaseCart, 90000) }, 90000);

			} else {
				if (groceryLocalCartObject !== undefined && groceryLocalCartObject !== null) {
					cartArray = groceryLocalCartObject.cartArray;
					rerenderCart()
				}


			}


		},
		error: function(e) {
			if (groceryLocalCartObject !== undefined && groceryLocalCartObject !== null) {
				cartArray = groceryLocalCartObject.cartArray;
				rerenderCart()
			}
		}
	})
}

function displayRandomOffers() {



	$.ajax({
		type: 'GET',
		url: '/getRandomBestActiveOffers',
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			

			$("#offersModal").modal("toggle");


			$.each(result, function(key1, resultRow) {

				$("#offersModal_modalBody").append(
					"<br>Category: " + resultRow.category
					+ "<br>Offer Name: " + resultRow.offerName
					+ "<br>Message: Take " + resultRow.discountPercentage + "% off products in the " + resultRow.category + " section!"
					+ "<br><br><hr>")

			})




		},
		error: function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	})
}
