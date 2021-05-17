<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Grocery Admin Page</title>

<script src="../js/standardJS/jquery-2.1.1.min.js"></script>
<script src="../js/standardJS/jquery.dataTables.min.js"></script>
<script src="../js/standardJS/jquery-ui.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="../js/customJS/admin/replenishment/uponReplenishmentPageRender.js"></script>
<script type="text/javascript"
	src="../js/customJS/admin/replenishment/confirmPurchase.js"></script>

</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand"><h5>Admin</h5></a>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/admin/offers">Offers
					<span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="/admin/products">Products</a>
			</li>
			<li class="nav-item"><a class="nav-link"
				href="/admin/replenishment">Replenishment</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/admin/customersupport">Customer Support</a></li>
		</ul>

		<ul class="navbar-nav navbar-collapse justify-content-end">
			<li><a class="nav-link" href="/login">Login</a></li>
			<li><a class="nav-link" href="/logout">Logout</a></li>
		</ul>
	</nav>


	<div class="container">

		<br> <br>


		<table id="replenishmentTable" class="display">
			<thead>
				<tr>
					<th>Order ID</th>
					<th>Order Date</th>
					<th>Food-Item ID</th>
					<th>Food-Item Name</th>
					<th>Quantity</th>
					<th>Payment Status</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody id="replenishmentTableBody">

			</tbody>
		</table>
	</div>
	<br>

	<script>


</script>
</body>
</html>