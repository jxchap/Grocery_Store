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
	src="../js/customJS/admin/customersupport/uponAdminCustomerSupportPageRender.js"></script>
<script type="text/javascript"
	src="../js/customJS/admin/customersupport/openDiscussionDetailsModal.js"></script>
	<script type="text/javascript"
	src="../js/customJS/admin/customersupport/updateEmployeeAssignment.js"></script>

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
	
		<br>
		<br>
		

		<table id="employeeQuestionsTable" class="display">
			<thead>
				<tr>
					<th>Creation Date</th>
					<th>Subject</th>
					<th>Order ID</th>
					<th>Assigned To</th>
					<th>Status</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody id="employeeQuestionsTableBody">

			</tbody>
		</table>
	</div>
	<br>
	
	
		<div class="modal" id="questionDetailsModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Question Details</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="questionDetailsModal_modalBody"></div>

				<!-- Modal footer -->

				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

	<div class="modal" id="addCommentModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Submit your Comment Below</h4>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="addCommentModal_ModalBody"></div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel
						Comment</button>
				</div>

			</div>

		</div>
	</div>

<script>
var employeeQuestionsArray = []
var currentQuestionObject;
var clickedQuestionId;

var optionBoxes;


</script>
</body>
</html>