$(document).ready(function() {
	var options = {
		table : { //表格
			title : '用户帐号管理', //表格标题
			column : [{
				name : 'ID'
			}, {
				name : '用户名'
			}, {
				name : '注册时间'
			}, {
				name : '最后登陆时间'
			}, {
				name : '状态'
			}, {
				name : '选项',
				width : '55px'
			}],
			ajax : {
				url : 'user/account/getPage', //请求地址
				success : function(result) {
					pageNumber = Math.ceil(result.data.pageNumber / pageSize);
					var table = '';
					$(result.data.list).each(function(i, obj) {
						table += '<tr>' +
								 '<td>' + obj.id + '</td>' +
								 '<td>' + obj.username + '</td>' +
								 '<td>' + obj.regDate + '</td>' +
								 '<td>' + timeStampToDateTime(obj.logTime) + '</td>';
						if(obj.locked == 0) {
							table += '<td>可用</td>' +
									 '<td><button class="btn-xs btn-danger" onclick="lockUser(' + obj.id + ')">锁定</button></td>' +
									 '</tr>';
						} else {
							table += '<td>禁用</td>' +
								  	 '<td><button class="btn-xs btn-primary" onclick="unlockUser(' + obj.id + ')">解锁</button></td>' +
									 '</tr>';
						}
					});
					$('#manageUI-table-tbody').append(table);
					refreshNumber();
				}
			}
		},
		easySearchParam : ['username'],
		complexSearch : true,
		complexSearchItems : [{
			name : 'ID',
			param : 'id',
			type : 'string'
		}, {
			name : '用户名',
			param : 'username',
			type : 'string'
		}, {
			name : '状态',
			param : 'locked',
			type : 'select',
			url : 'user/account/getLocked'
		}]
	}
	$('#user-account-manageUI').manageUI(options);
});

function lockUser(id) {
	$.post('user/account/lock', {'id' : id}, function(result) {
		if(result.code == '200') {
			refreshPage();
		} else if(result.code == '403'){
			validateWarningFrame(result.msg);
		}
	});
}

function unlockUser(id) {
	$.post('user/account/unlock', {'id' : id}, function(result) {
		if(result.code == '200') {
			refreshPage();
		} else if(result.code == '403'){
			validateWarningFrame(result.msg);
		}
	});
}