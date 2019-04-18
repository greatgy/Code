var params = {
	uid : ""
};
var Pagenum = {
	comment_num : 1,
	notify_num : 1,
	sys_num : 1
};

$(function($) {
	initExpression();
	$("#prev").click(function() {
		pageupdown($(this));
	});
	$("#next").click(function() {
		pageupdown($(this));
	});
})

function delMessage(obj) {
	params.uid = $(obj).data("uid");
	$.post(g.base + "/ajax/my/msg/delete", params, function(data) {
		$(obj).parents("li").remove();
	});
}

// 消息类型切换
function typeupdown(obj) {
	var type = $(obj).data("type");
	var num = $(".curSelect").find("a").find("span").text();
	var pagenum = 1;
	var url = g.base + "/ajax/my/msg/" + type;
	if (type == "comment") {
		$(".valtit").text("我收到的评论");
	} else if (type == "notify") {
		$(".valtit").text("我收到的通知");
	} else if (type == "system") {
		$(".valtit").text("我收到的系统消息");
	} else {
		return;
	}
	if ($(".curSelect").find("a").find("span").text() != "0") {
		var param = {
			type : $(".curSelect").find("a").data("type")
		}
		$.post(g.base + "/ajax/my/msg/num", param, function(data1) {
			$(".curSelect").find("a").find("span").text(data1);
			$.post(url, pagenum, function(data2) {
				loadData(obj, data2);
			})
		})
	} else {
		$.post(url, pagenum, function(data2) {
			loadData(obj, data2);
		})
	}
}

/**
 * 判断当前是否登录超时
 * 1如果超时显示登录框
 * 2正常显示数据
 * @param obj
 * @param data
 */
function loadData(obj, data){
	if(isAjaxNeedLogin(data)){
		defaultInitLogin();
	}else{
		$(".curSelect").removeClass("curSelect");
		$(obj).parent().addClass("curSelect");
		$("#content").empty();
		$("#content").append(data);
		initExpression();
		Pagenum.comment_num = 1;
		Pagenum.notify_num = 1;
		Pagenum.sys_num = 1;
	}
}

// 消息翻页
function pageupdown(obj) {
	var type = $(".curSelect").find("a").data("type");
	var num = $(".curSelect").find("a").find("span").text();
	var url = g.base + "/ajax/my/msg/" + type;
	var dir = $(obj).data("dir");
	var pager = 1;
	if (type == "comment") {
		pager = changePage(Pagenum.comment_num, dir);
		if (pager == 0 || pager > Pagemax.comment_max) {
			return;
		}
		Pagenum.comment_num = pager;
	} else if (type == "notify") {
		pager = changePage(Pagenum.notify_num, dir);
		if (pager == 0 || pager > Pagemax.notify_max) {
			return;
		}
		Pagenum.notify_num = pager;
	} else if (type == "system") {
		pager = changePage(Pagenum.sys_num, dir);
		if (pager == 0 || pager > Pagemax.sys_max) {
			return;
		}
		Pagenum.sys_num = pager;
	}
	$.post(url, {
		pagenum : pager
	}, function(data) {
		$("#content").empty();
		$("#content").append(data);
		initExpression();
	})
}

// 计算页码的变化
function changePage(pager, dir) {
	if (dir == "up") {
		pager--;
	} else if (dir == "down") {
		pager++;
	}
	return pager;
}

// 将所有的[em_*]替换为图片
function initExpression() {
	$(".judgeContent").each(function() {
		var content = replaceContents($(this).html());
		$(this).html(content);
	});
}

// 将[em_*]替换为img标签
function replaceContents(str) {
	str = str.replace(/\[em_([0-9]*)\]/g, '<img src="' + g.baseimg
			+ '/imgs/face/qq/$1.gif" border="0" />');
	return str;
}
