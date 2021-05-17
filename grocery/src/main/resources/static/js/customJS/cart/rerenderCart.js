function rerenderCart() {

	$('#cartModal_modalBody').empty();

	cartArrayReduced = reduceCartArray();

	//build cart item head
	$("#cartModal_modalBody").append(
		"<div class=''>"
		+ "<div class='row'>"
		+ "<div class= 'col d-flex justify-content-start '><h4><b>Shopping Cart</b></h4></div>"
		+ "<div class='col d-flex justify-content-end text-muted'> Total Items: " + cartArray.length + "</div>"
		+ "</div><br>"
	)
	//build cart rows
	$.each(cartArrayReduced, function(i, cartObject) {

		console.log("rerendering cart")
		console.log(cartArrayReduced)

		let foodPriceBlock = "$" + cartObject.cost.toFixed(2)
		if (cartObject.foodCategory.offers.length > 0) {
			foodPriceBlock = "<del>$" + cartObject.cost + "</del> $" + (cartObject.cost * (cartObject.foodCategory.offers[0].discountPercentage / 100)).toFixed(2)
		}

		$("#cartModal_modalBody").append(





			"<div class='row border'>"
			+ "<div class='col'><img  src='../images/" + (cartObject.imageLocation) + ".jpg' height='100' width = '100'></div>"
			+ "<div class='col align-self-center'>"
			+ "<div class='row text-muted '>" + cartObject.foodItemName + "</div>"
			+ "<div class='row' >" + cartObject.description + "</div >"
			+ "</div>"
			+ "<div class='col align-self-center'> <a href='#' id = 'minus " + cartObject.foodItemId + "' class = 'minus'>  -  </a>" + cartObject.quantity + "<a href='#' id = 'plus " + cartObject.foodItemId + "' class = 'plus'> + </a> </div>"
			+ "<div class='col align-self-center'> " + foodPriceBlock + " </div><div class='col align-self-center hover'> <button type='button' class='btn btn-danger deleteItem' id = 'delete " + cartObject.foodItemId + "'>&#10005;</span></div>"
			+ "</div >"


		)
	});

	//build remaining cart
	let checkOutButton = ""
	if (cartArray.length > 0) { checkOutButton = "<br><button id='checkOutBtn' type='button' class='btn btn-primary'>Check Out</button>" }
	$("#cartModal_modalBody").append("</div> " + checkOutButton + "</div>")
}

function reduceCartArray() {


	cartArrayTotalPrice = 0;
	let returnArray = []
	for (var j = 0; j < cartArray.length; j++) {
		cartArrayTotalPrice += cartArray[j].cost
		let tempObject = JSON.parse(JSON.stringify(cartArray[j]))
		returnArray.push(tempObject)
	}

	for (var j = 0; j < returnArray.length; j++) {
		returnArray[j].quantity = 1
	}

	returnArray.sort((a, b) => {
		return a.foodItemId - b.foodItemId;
	});

	for (var j = 1; j < returnArray.length || returnArray[j] != null;) {
		if (returnArray[j].foodItemId == returnArray[j - 1].foodItemId) {
			returnArray[j - 1].quantity = returnArray[j - 1].quantity + 1
			returnArray[j - 1].cost = returnArray[j - 1].cost + returnArray[j].cost
			returnArray.splice(j, 1)
		} else {
			j++
		}
	}

	return returnArray;

}