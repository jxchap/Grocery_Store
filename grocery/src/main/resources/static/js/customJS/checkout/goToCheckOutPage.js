$(document).ready(function() {

	$("body").on('click', "#checkOutBtn", function() {

		updateDatabaseCart()


		window.location.href = '/checkout';

	});
}); 

