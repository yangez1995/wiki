$(document).ready(function() {
	var elements = [];
	$('security-element').each(function() {
		var element = {};
		element.url = $(this).attr('url');
		elements.push(element);
	});
	$.ajax({
		type : 'POST',
		url : '../getElementAuth',
		contentType:"application/json",
		data : JSON.stringify(elements),
		success : function(result) {
			
		}
	});
});