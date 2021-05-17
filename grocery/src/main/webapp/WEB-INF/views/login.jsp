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
	src="js/customJS/misc/createNewAccount.js"></script>
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
	<h1>Log in</h1>
	<div class="login-page">
		<div class="form">

			<form class="login-form" action="/login" method="POST">
				<input type="text" placeholder="username" name="username"
					required="required" /> <br> <input type="password"
					placeholder="password" name="password" required="required" /><br>
				<button type="submit" name="submit" value="LogIn">login</button>

			</form>
		</div>
	</div>

	<br>
	<br>
	<h1>Forget Password?</h1>

	<input type="text" placeholder="email" name="emailForPassReset"
		required="required" id="emailForPassReset" />
	<br>
	<input class="btn-sm btn-primary" type="button" id="resetPassword"
		value="Reset Password" />
		
	<br>
	<br>

	<h1>Or Create a New Account</h1>

	<input type="text" placeholder="username" name="username"
		required="required" id="newUsername" />
	<br>
	<input type="password" placeholder="password" name="password"
		required="required" id="newPassword" />
	<br>
	<input type="email" placeholder="email" name="email"
		required="required" id="newEmail" />
	<br>
	<input type="text" placeholder="mobile" name="mobile"
		required="required" id="newMobile" />
	<br>

	<input type="text" placeholder="firstname" name="firstname"
		required="required" id="newFirstName" />
	<br>
	<input type="text" placeholder="lastname" name="lastname"
		required="required" id="newLastName" />
	<br>
	<input type="text" placeholder="address" name="address"
		required="required" id="newAddress" />
	<br>
	<input type="text" placeholder="city" name="city"
		required="required" id="newCity" />
	<br>
	<input type="text" placeholder="state" name="state"
		required="required" id="newState" />
	<br>
	<input type="text" placeholder="zipcode" name="zipcode"
		required="required" id="newZipCode" />
	<br>
	<input class="btn-sm btn-primary" type="button" id="createAccount"
		value="Create Account" />



</div>

</body>
</html>