var wikiId = 0;
var pageIndex = 1;
$(document).ready(function() {
	wikiId = window.localStorage.getItem('wikiId');
	resetList();
});

function resetList() {
	$.post('getHistorys', {'wikiId': wikiId, 'pageIndex': pageIndex}, function(data) {
		$(data).each(function(i, history) {
			$('#list').append(
				'<tr id="' + history.id + '">' + 
					'<td>' + history.version + '</td>' +
					'<td>' + changeTableName(history.changeTable) + '</td>' +
					'<td>' + history.nickname + '</td>' +
					'<td>' + timeStampToDateTime(history.editTime) + '</td>' +
					'<td><a onclick="check(this)">查看</a></td>' + 
				'</tr>'
			);
		});
	});
}

function changeTableName(name) {
	switch(name) {
	case 'card': {
		return '名片';
	}
	case 'label': {
		return '标签';
	}
	case 'catal': {
		return '目录';
	}
	case 'chapter': {
		return '章节';
	}
	}
}

function check(e) {
	var params = {};
	var tr = $(e).parent().parent();
	params.wikiId = wikiId;
	params.historyId = $(tr).attr('id');
	params.version = $(tr).find('td:first').text();
	params.type = $(tr).find('td:eq(1)').text();
	params.updateBy = $(tr).find('td:eq(2)').text();
	params.updateTime = $(tr).find('td:eq(3)').text();
	window.localStorage.setItem('data', JSON.stringify(params));
    window.location.href = 'compare/index';
}