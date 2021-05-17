$(document).ready(function() {

	$('#createAccount').on('click', function() {

		accountInfo = createAccountBody()

		$.ajax({
			type: 'POST',
			url: '/addNewAccount',
			contentType: 'application/json',
			dataType: 'json',
			data: JSON.stringify(accountInfo),
			success: function(e) {
				alert(e.message);

				resetLoginPage()
			},
			error: function(e) {
				alert("Error!");
				console.log("ERROR: ", e);
			}
		})
	});
})

function createAccountBody() {

	var username = $("#newUsername").val();
	var password = $("#newPassword").val();
	var email = $("#newEmail").val();
	var mobileNum = $("#newMobile").val();
	var firstname = $("#newFirstName").val();
	var lastname = $("#newLastName").val();
	var address = $("#newAddress").val();
	var city = $("#newCity").val();
	var state = $("#newState").val();
	var zipcode = $("#newZipCode").val();


	var accountInfo = {
		"username": username,
		"password": password,
		"email": email,
		"mobileNum": mobileNum,
		"firstname": firstname,
		"lastname": lastname,
		"address": address,
		"city": city,
		"state": state,
		"zipcode": zipcode
	}

	return accountInfo;

}

function resetLoginPage() {
	$("#newUsername").val('')
	$("#newPassword").val('')
	$("#newEmail").val('')
	$("#newFirstName").val('')
	$("#newMobile").val('')
	$("#newLastName").val('')
	$("#newAddress").val('')
	$("#newCity").val('')
	$("#newState").val('')
	$("#newZipCode").val('')
}


