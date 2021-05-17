<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login Page</title>
<script src="js/standardJS/jquery-2.1.1.min.js"></script>
<script src="js/standardJS/jquery.dataTables.min.js"></script>
<script src="js/standardJS/jquery-ui.min.js"></script>

<script type="text/javascript"
	src="js/customJS/misc/resetPassword.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">

	<br>
	<br>

	<h1>Input details below to update password</h1>


	<input type="text" placeholder="email" name="email"
		required="required" id="email" />
	<br>
	<input type="password" placeholder="new password" name="newPassword"
		required="required" id="newPassword" />
	<br>
	<input type="password" placeholder="token from email" name="token"
		required="required" id="token" />
	<br>
	<input class="btn-sm btn-primary" type="button" id="sendCredentialsAndToken"
		value="Reset your Password" />



</div>

</body>
</html>