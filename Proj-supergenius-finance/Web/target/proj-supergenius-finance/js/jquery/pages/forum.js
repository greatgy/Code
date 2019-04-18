var param = {
	content : ""
}

function subtalk(obj) {
	beforetalk(obj);
	if ("" == param.content) {
		alert("请输入发言内容");
	} else {
		$.post(g.base + "/forum/talk", param);
	}
}

function beforetalk(obj) {
	param.content = $(obj).siblings(".backwords").val();
	if ($(obj).siblings(".replysomeone").find("#userUid").length > 0) {
		param.touid = $(obj).siblings(".replysomeone").find("#userUid").text();
	}
}

function aftertalk(data, obj) {
	if (data.success) {
		$(obj).siblings(".backwords").val("");
		$(".replysomeone").hide();
		addNewTalk(data.result, obj);
	} else {
		alert("发言失败");
	}
}

function addNewTalk(bean) {
	var content = replaceContent(bean.content);
	var html = '';
	html += '<li><div class="moraleft"><div class="circleline"><em></em></div></div><div class="moralright"><h5>刚刚</h5><div class="moralperson">';
	html += '<img ';
	if (bean.userAvatar != null) {
		html += 'src="' + g.userimg + bean.userAvatar + '" ';
	} else {
		html += 'src="' + g.baseimg + '/imgs/default/tourist.png" ';
	}
	html += 'alt="" />';
	if (bean.touid != null && bean.touid != "") {
		html += '<span><i>' + bean.fromusername + '</i> 回复 <i>'
				+ bean.tousername + '</i></span>';
	} else {
		html += '<span><i>' + bean.fromusername + '</i></span>';
	}
	html += '<input type="hidden" value="' + bean.uid + '" id="uid"/>';
	html += '<a onclick="replytalk(this)" class="replyback">回复</a></div>';
	html += '<p class="comments">' + bean.content + '</p>';
	html += '</div></li>';
	$(".moralists").append(html);
	$('.moralists').scrollTop($('.moralists')[0].scrollHeight);
}

function replytalk(obj) {
	$(".backwords").focus();
	var name = $(obj).siblings("span").text();
	var uid = $(obj).siblings("#uid").val();
	if (name.indexOf("回复") > 0) {
		var strs = new Array();
		strs = name.split("回复");
		name = strs[0];
	}
	$(".replysomeone").find("span").text(name);
	$(".replysomeone").find("#userUid").text(uid);
	$(".replysomeone").show();
}

/* 生成页面表情 */
function initPage() {
	$(".comments").each(function() {
		var content = replaceContent($(this).html());
		$(this).html(content);
	});
}

// 将[em_*]替换为img标签
function replaceContent(str) {
	str = str.replace(/\[em_([0-9]*)\]/g, '<img src="' + g.baseimg
			+ '/imgs/face/qq/$1.gif" border="0" />');
	return str;
}
