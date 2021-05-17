$(document).ready(function() {

	$("body").on('click', ".addComment", function() {

		$("#addCommentModal").modal("toggle");
		$('#addCommentModal_ModalBody').empty();


		//build modal body
		$("#addCommentModal_ModalBody").append(
			
			"<textarea id='comment' style='height: 250px !important; width: 750px !important' maxlength='1200' placeholder='Your Comment...'></textarea>"
			+ "<br><input class='btn-sm btn-primary' type='button' id='submitCommentBtn' value='Submit Comment'/> "
		)




	});


});
