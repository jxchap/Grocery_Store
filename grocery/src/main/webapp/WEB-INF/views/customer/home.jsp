<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Shopping Place!</title>

<script src="js/standardJS/jquery-2.1.1.min.js"></script>
<script src="js/standardJS/jquery.dataTables.min.js"></script>
<script src="js/standardJS/jquery-ui.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="js/customJS/searching/searchForFoodItems.js"></script>
<script type="text/javascript"
	src="js/customJS/misc/uponHomePageRender.js"></script>
<script type="text/javascript" src="js/customJS/cart/openCart.js"></script>
<script type="text/javascript"
	src="js/customJS/cart/updateDatabaseCart.js"></script>
<script type="text/javascript"
	src="js/customJS/cart/refreshContentsOfLocalCart.js"></script>
<script type="text/javascript" src="js/customJS/cart/addToLocalCart.js"></script>
<script type="text/javascript" src="js/customJS/cart/rerenderCart.js"></script>
<script type="text/javascript"
	src="js/customJS/checkout/goToCheckOutPage.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">



</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand"><h5>Store</h5></a>
		<ul class="navbar-nav">
			<li><a class="nav-link" href="/home">Home</a></li>
			<li><a class="nav-link" href="/mypurchases">Purchase History</a></li>
			<li><a class="nav-link" href="/myquestions">Questions</a></li>
			<li><a class="nav-link" href="/checkout">Checkout</a></li>
		</ul>

		<ul class="navbar-nav navbar-collapse justify-content-end">
			<li><a class="nav-link" href="/login">Login</a></li>
			<li><a class="nav-link" href="/logout">Logout</a></li>
		</ul>
	</nav>




	<div class="container">
		<h1>Welcome to the Grocery Store</h1>
		<h2>Start shopping below!</h2>

		<div id="navBar" class="pull-right">
			<button type="button" class="btn btn-secondary" id="cart">
				<svg xmlns="http://www.w3.org/2000/svg" width="45" height="45"
					fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
  						<path
						d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
				</svg>
			</button>

		</div>
		<div id="currentUserMessage">
			<br>Howdy, and welcome to our shop! <br>You're not logged
			in at the moment, but feel free to browse our store! <br>If
			you'd like to make a purchase please create an account first.
		</div>


	</div>
	<br>


	<div class="container border rounded"
		style="margin: auto; padding: 50px; margin-top: 50px; margin-bottom: 50px">
		<h3>Narrow your search results</h3>
		<div class="form-row">
			<div class="col-3">
				Product Name <input class="form-control" type="text"
					id="productNameToSearchFor" />
			</div>
			<div class="col-3">
				Product Description <input class="form-control" type="text"
					id="productDescriptionToSearchFor" />
			</div>
			<div class="col-2">
				Category <select class="form-control" id="categoriesBox"
					name="categories" style="min-width: 150%">

				</select>
			</div>
		</div>
		<input class="btn-sm btn-primary" type="button" id="searchProductsBtn"
			value="SEARCH" />


	</div>

	<div class="d-flex justify-content-center" id="arrowAreaMessage"></div>
	<br>
	<div class="d-flex justify-content-center" id="arrowArea"></div>
	<br>
	<div class="container border rounded">
		<div
			style='text-align: center; font-size: 20px; font-family: "Trebuchet MS", Helvetica, sans-serif'>Search
			Results:</div>

		<div id="listProductBox">
			<div id="listProduct"></div>
		</div>

	</div>

	<div class="modal" id="cartModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Cart Details</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="cartModal_modalBody"></div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
	
	<div class="modal" id="offersModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Some of Today's Offers!</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="offersModal_modalBody"></div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>



	<script>
var totalElementsInPageable;
var currentNumberOfFoodItemsDisplayedInPage;
var requestedPage;
var pageCount;
var cartArray = [];
var cartArrayReduced = [];
var cartArrayTotalPrice = 0;
var currentShoppingItems = [];


</script>
</body>
</html>