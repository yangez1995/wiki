var nowSetUserId = 0;

$(document).ready(function() {
	var options = {
		table : { //表格
			title : '用户角色管理', //表格标题
			column : [{
				name : 'ID'
			}, {
				name : '用户名'
			}, {
				name : '角色'
			}, {
				name : '选项',
				width : '55px'
			}],
			ajax : {
				url : 'user/role/getPage', //请求地址
				success : function(result) {
					pageNumber = Math.ceil(result.data.pageNumber / pageSize);
					var table = '';
					$(result.data.list).each(function(i, user) {
						table += '<tr>' +
								 '<td>' + user.id + '</td>' +
								 '<td>' + user.username + '</td>' + 
								 '<td>';
						$(user.roles).each(function(j, role) {
							table += '<span class="label label-primary" style="line-height: 20px;">' + role.name + '</span>&nbsp;';
						});
						table += '</td>' +
								 '<td><button class="btn-xs btn-primary" onclick="showUpdateModal(' + replaceQuotes(JSON.stringify(user)) + ')">设置</button></td>'
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
			name : '角色',
			param : 'role',
			type : 'select',
			url : 'user/role/getRoles'
		}],
		canUpdate : true, 
		updateModal : { 
			title : '设置角色',
			body : '<table class="table table-striped table-bordered model-table">' +
					   '<thead><tr>' + 
					   	   '<th>角色名</th>' + 
					   	   '<th width="43px">选项</th>' +
					   '</tr></thead>' +
					   '<tbody id="set-role-list"></tbody>' +
				   '</table>', 
			footer : '<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="updateRoles()">确认</button>' +
					 '<button type="button" class="btn btn-info" onclick="chooseAddRole()">添加角色</button>'
		},
	}
	$('#user-role-manageUI').manageUI(options);
});

/**
 * 点击设置按钮，加载用户角色信息并弹出角色设置模态框
 */
function showUpdateModal(user) {
	nowSetUserId = user.id;
	$('#set-role-list').html('');
	var table = '';
	$(user.roles).each(function(i, role){
		table += '<tr id="' + role.id + '">' + 
				     '<td>' + role.name + '</td>' +
				     '<td>' + 
				         '<button class="btn btn-danger btn-xs" onclick="removeRole(this)">移除</button>' + 
				     '</td>' + 
				 '</tr>';
	});
	$('#set-role-list').html(table);
	$('#update').modal({
		backdrop: 'static'
	});
}
/**
 * 删除角色
 */
function removeRole(e) {
	$(e).parent().parent().remove();
}
/**
 * 选择添加角色，确定可添加角色并弹出模态框。
 */
function chooseAddRole() {
	var ids = [];
	$('#set-role-list').find('tr').each(function(i, tr) {
		var param = {};
		param.value = $(tr).attr('id');
		ids.push(param);
	});
	$.ajax({
		type : 'POST',
		url : 'user/role/getOtherRoles',
		contentType:"application/json",
		data : JSON.stringify(ids),
		success : function(result) {
			$('#other-roles').html('');
			$(result.data).each(function(i, role) {
				$('#other-roles').append('<option value="' + role.value + '">' + role.name + '</option>');
			});
			$('#add-role').modal({
				backdrop: 'static'
			});
		}
	});
}
/**
 * 添加角色
 */
function addRole() {
	if($('#other-roles').val() != '' && $('#other-roles').val() != null) {
		$('#set-role-list').append(
			'<tr id="' + $('#other-roles').val() + '">' + 
			    '<td>' + $('#other-roles').find('option:selected').text() + '</td>' +
			    '<td>' + 
			        '<button class="btn btn-danger btn-xs" onclick="removeRole(this)">移除</button>' + 
			    '</td>' + 
			'</tr>'	
		);
	}
}

function updateRoles() {
	var ids = [];
	$('#set-role-list').find('tr').each(function(i, tr){
		ids.push($(tr).attr('id'));
	});
	var params = {};
	params.id = nowSetUserId;
	params.ids = ids;
	$.ajax({
		type : 'POST',
		url : 'user/role/update',
		contentType : 'application/json',
		dataType : 'json',
		data : JSON.stringify(params),
		success : function(result) {
			if(result.code == '200') {
				refreshPage();
			} else if(result.code == '403'){
				validateWarningFrame(result.msg);
			}
		}
	});
}