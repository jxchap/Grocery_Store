$(document).ready(function() {

	$('#resetPassword').on('click', function() {

		sendResetPasswordToken()
	});

	$('#sendCredentialsAndToken').on('click', function() {

		sendCredentialsAndToken()
	});
})

function sendResetPasswordToken() {


	var emailForPassReset = $("#emailForPassReset").val();

	let requestBody = {
		"emailForPassReset": emailForPassReset
	}

	$.ajax({
		type: 'POST',
		url: '/sendResetPasswordToken',
		contentType: 'application/json',
		dataType: 'json',
		data: JSON.stringify(requestBody),
		success: function(e) {


			alert(e.message);


		},
		error: function(e) {
			alert("Error!");
			console.log("ERROR: ", e);
		}
	})

}

function sendCredentialsAndToken() {


	var email = $("#email").val();
	var newPassword = $("#newPassword").val();
	var token = $("#token").val();

	let requestBody = {
		"email": email,
		"newPassword": newPassword,
		"token": token
	}
	
	console.log(requestBody)

	$.ajax({
		type: 'POST',
		url: '/resetPassword',
		contentType: 'application/json',
		dataType: 'json',
		data: JSON.stringify(requestBody),
		success: function(e) {


			alert(e.message);
			if(e.message == "Password successfully Updated"){
				window.location.href = '/login';
			}


		},
		error: function(e) {
			alert("Error!");
			console.log("ERROR: ", e);
		}
	})



}