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


<script type="text/javascript"
	src="js/customJS/customer/purchases/uponMyPurchasesPageRender.js"></script>
<script type="text/javascript"
	src="js/customJS/customer/purchases/openOrderDetails.js"></script>
<script type="text/javascript"
	src="js/customJS/customer/questions/openAskNewQuestionModal.js"></script>
<script type="text/javascript"
	src="js/customJS/customer/questions/submitNewQuestion.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>


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
	
		<br>
		<br>
		

		<table id="customerPurchasesTable" class="display">
			<thead>
				<tr>
					<th>Order ID</th>
					<th>Order Date</th>
					<th>Address</th>
					<th>City</th>
					<th>State</th>
					<th>Zip Code</th>
					<th>Delivery Charge</th>
					<th>Total Tax</th>
					<th>Total Amount</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody id="customerPurchasesTableBody">

			</tbody>
		</table>
	</div>
	<br>
	
	<div class="modal" id="orderDetailsModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Order Details</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="orderDetailsModal_modalBody"></div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
	
		<div class="modal" id="askQuestionModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Submit your Question Below</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="askQuestion_ModalBody"></div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel
						Question</button>
				</div>

			</div>

		</div>
	</div>

<script>
var listOfPurchases = []
var orderIdQuestionAsking;
</script>
</body>
</html>