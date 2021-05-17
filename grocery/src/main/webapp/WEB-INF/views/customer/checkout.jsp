<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Ensures optimal rendering on mobile devices. -->
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- Optimal Internet Explorer compatibility -->
<title>Your Shopping Place!</title>

<script src="js/standardJS/jquery-2.1.1.min.js"></script>
<script src="js/standardJS/jquery.dataTables.min.js"></script>
<script src="js/standardJS/jquery-ui.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="js/customJS/checkout/uponCheckoutPageLoad.js"></script>
	<script type="text/javascript"
	src="js/customJS/checkout/handleCheckout.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script
	src="https://www.paypal.com/sdk/js?client-id=AcazDrtzO_xmeKCpNYEwRyKdxsXhIHXoZYYfNZdLJoMgIBiJ4VsV3c8DGjM50iXY6Lfv1Cq2qKiza_F-"></script>
</head>
<body class="bg-light">

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

		<div class="row">
			<div class="col-md-4 order-md-2 mb-4">
				<h4 class="d-flex justify-content-between align-items-center mb-3">
					<span class="text-muted">Your cart</span> <span id="cartCount"
						class="badge badge-secondary badge-pill"></span>
				</h4>
				<ul id="cartList" class="list-group mb-3">


				</ul>

			</div>
			<div class="col-md-8 order-md-1">
				<h4 class="mb-3">Shipping address</h4>
				<form class="needs-validation was-validated" novalidate="">
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="firstName">First name</label> <input type="text"
								class="form-control" id="firstName" placeholder="" value=""
								required="" readonly>
							<div class="invalid-feedback">Valid first name is required.
							</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="lastName">Last name</label> <input type="text"
								class="form-control" id="lastName" placeholder="" value=""
								required="" readonly>
							<div class="invalid-feedback">Valid last name is required.
							</div>
						</div>
					</div>

					<div class="mb-3">
						<label for="email">Email </label>
						<input type="email" class="form-control" id="email"
							placeholder="you@example.com" readonly>
						<div class="invalid-feedback">Please enter a valid email
							address for shipping updates.</div>
					</div>

					<div class="mb-3">
						<label for="address">Address</label> <input type="text"
							class="form-control" id="address" placeholder="1234 Main St"
							required="">
						<div class="invalid-feedback">Please enter your shipping
							address.</div>
					</div>

					<div class="row">
					
					<div class="col-md-4 mb-3">
							<label for="city">City</label> <input type="text" class="form-control" id="city"
								placeholder="City Name" required="">
							<div class="invalid-feedback">Please provide a valid city.
							</div>
						</div>
					
					
					
					
					
						<div class="col-md-4 mb-3">
							<label for="state">State</label> <select
								class="custom-select d-block w-100" id="state" required="">
								<option>AL</option>
								<option>AK</option>
								<option>AZ</option>
								<option>AR</option>
								<option>CA</option>
								<option>CO</option>
								<option>CT</option>
								<option>DE</option>
								<option>FL</option>
								<option>GA</option>
								<option>HI</option>
								<option>ID</option>
								<option>IL</option>
								<option>IN</option>
								<option>IA</option>
								<option>KS</option>
								<option>KY</option>
								<option>LA</option>
								<option>ME</option>
								<option>MD</option>
								<option>MA</option>
								<option>MI</option>
								<option>MN</option>
								<option>MS</option>
								<option>MO</option>
								<option>MT</option>
								<option>NE</option>
								<option>NV</option>
								<option>NH</option>
								<option>NJ</option>
								<option>NM</option>
								<option>NY</option>
								<option>NC</option>
								<option>ND</option>
								<option>OH</option>
								<option>OK</option>
								<option>OR</option>
								<option>PA</option>
								<option>RI</option>
								<option>SC</option>
								<option>SD</option>
								<option>TN</option>
								<option>TX</option>
								<option>UT</option>
								<option>VT</option>
								<option>VA</option>
								<option>WA</option>
								<option>WV</option>
								<option>WI</option>
								<option>WY</option>
							</select>
							<div class="invalid-feedback">Please provide a valid state.
							</div>
						</div>
						<div class="col-md-3 mb-3">
							<label for="zip">Zip Code</label> <input type="text"
								class="form-control" id="zip" placeholder="" required="">
							<div class="invalid-feedback">Zip code required.</div>
						</div>
					</div>

					<hr class="mb-4">

					<div id="payPalDiv" align="center"></div>


				</form>
			</div>
		</div>
		<br>
	</div>

	<div class="modal" id="checkoutNoAccount">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Account Creation Required</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="checkoutNoAccount_modalBody">
					<p>If you'd like to make a purchase, please create an account
						first. Your cart will be transferred to the new account.</p>

					<a href="/login" class="btn btn-primary">Create an Account</a>
				</div>



				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>



</body>



<script>
var cartArray = [];
var reducedCartArray = [];
var groceryLocalCartObject = JSON.parse(sessionStorage.getItem('groceryLocalCartObject'));
var cartArrayTotalPrice = 0;

var totalTax;

</script>

</body>
</html>