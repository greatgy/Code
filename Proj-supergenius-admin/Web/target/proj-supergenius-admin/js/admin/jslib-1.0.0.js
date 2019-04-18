
function ok(txt) {
	alertHandler("alert_success", txt);
}

function err(txt) {
	if($.messager) {
		$.messager.alert('错误', txt);
	} else {
		alertHandler("alert_error", txt);
	}
}

function warn(txt) {
	if($.messager) {
		$.messager.alert('注意', txt);
	} else {
		alertHandler("alert_warning", txt);
	}
}

function info(txt) {
	alertHandler("alert_info", txt);
}

function alertHandler(cls, txt){
	var target;
	if(window.parent.frames.topbar) {
		var topbody = window.parent.frames.topbar.document.body;
		target = $("#messagebar", topbody);
	} else {
		target = $("#alert");
	}
	target.attr("class", cls).html(txt).show();
	target.delay(5000).fadeOut("slow");
}

/**
 * 排序处理：
 * <a href="" class="orderby" data-orderby="lastlogintime">最后登录时间</a>
 */
function orderbyHandler() {
	var url = window.location.href;
	var tourl = $(this).attr("href");
	var field = "by" + $(this).data("orderby");
	var param = "";
	if(url.indexOf("?") > 0) {
		var querystr = url.split("?")[1];
		var arr = querystr.split("&");
		for (var i in arr) {
			var p = arr[i];
			if(p.indexOf("=") > 0 && p.split("=")[0] == field) {
				if(i > 0) {
					param += "&";
				}
				if(p.split("=")[1] == "asc") {
					param += field + "=desc";
				} else {
					param += field + "=asc";
				}
			} else {
				if(p.indexOf("by") != 0) {
					if(i > 0) {
						param += "&";
					}
					param += p;
				}
			}
		}
	}
	if(param.indexOf(field) == -1) {
		if(param.length > 0) {
			param += "&";
		}
		param += field + "=asc";
	}
	gourl(tourl + "?" + param);
	return false;
}

function webLog(str) {
	try{
		if(window.console&&window.console.log){
			console.log(str)
		}
	} catch (e){};
}