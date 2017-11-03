function validateErrorFrame(content) {
	layer.open({
		title : '验证信息',
		content : content,
		icon : 2
	});
}

function validateWarningFrame(content) {
	layer.open({
		title : '验证信息',
		content : content,
		icon : 7
	});
}