function debug(str) {
	$("body").prepend(str);
}
/**
 * 删除字符串两边的空白
 */
String.prototype.trim = function() {
	return this.replace(/^\s*|\s*$/g, "");
};
/**
 * 字符串格式化
 * var template1="我是{0}，今年{1}了";
 * var template2="我是{name}，今年{age}了";
 * var result1=template1.format("loogn",22);
 * var result2=template2.format({name:"loogn",age:22});
 * 两个结果都是"我是loogn，今年22了"
 * 
 */
String.prototype.format = function(args) {
    var result = this;
    if (arguments.length > 0) {    
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                if(args[key]!=undefined){
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] != undefined) {
                    var reg = new RegExp("({[" + i + "]})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
}
//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()	// 毫秒
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}
function setScrollHeight() {
	window.name = document.documentElement.scrollTop + document.body.scrollTop;
}
function resetScrollHeight() {
	var n=window.name;
    if(n == '' || isNaN(n)){
        return;
    }
    $(document).scrollTop(n)
    window.onscroll=setScrollHeight;
}
function setBaseCssForInput () {
	$("input:text").each(function(){
		$(this).addClass("txt");
	});
	$("input:password").each(function(){
		$(this).addClass("txt");
	});
	$("input:button").each(function(){
		$(this).addClass("btn");
	});
	$("input:submit").each(function(){
		$(this).addClass("btn");
	});
	$("input:radio").each(function(){
		$(this).addClass("radio");
	});
	$("input:checkbox").each(function(){
		$(this).addClass("chk");
	});
	if (g.ismozilla) {
	   $("html").addClass("ff");
	}
}
function setAnchorHref() {
	$("a[href='']").each(function(){
		$(this).attr("href", "javascript:void(0)");
	});
}
function goScrollTo(selector, speed, margintop) {
	if(typeof(speed) == "undefined") {
		speed = "fast";
	}
	if(typeof(margintop) == "undefined") {
		margintop = 0;
	}
	$('html,body').animate({scrollTop: $(selector).offset().top - margintop}, speed);
}
function gourl(url) {
	window.location.href = url;
}
function forbiddenIframe() {
	if(top.location !== self.location){
		top.location.href = self.location.href;
	}
}
function deleteConfirmHandler() {
	var msg = "您确定要删除吗？";
	return confirm(msg);
}
function computeTextBoxWordsCount(editor, counter) {
	var len = counter.html();
	editor.keyup(function(){
//		alert(len);
//		alert(editor.val().length);
//		alert(len - editor.val().length);
		var l = len - editor.val().length;
		counter.html(l);
		if(l < 0) {
			counter.addClass("red");
		} else {
			counter.removeClass("red");
		}
	});
	editor.trigger("keyup");
}
function isImageFile(path) {
	if(path == "" || /(jpg|jpeg|gif|png)$/i.test(path)) {
		return true;
	} else {
		return false;
	}
}
function isImageOk(img) {
    // During the onload event, IE correctly identifies any images that
    // weren't downloaded as not complete. Others should too. Gecko-based
    // browsers act like NS4 in that they report this incorrectly.
    if (!img.complete) {
        return false;
    }
    // However, they do have two very useful properties: naturalWidth and
    // naturalHeight. These give the true size of the image. If it failed
    // to load, either of these should be zero.
    if (typeof(img.naturalWidth) != "undefined" && img.naturalWidth == 0) {
        return false;
    }
    // No other way of checking: assume it's ok.
    return true;
}
function rememberWindowUrl() {
	$.cookie('returnurl', window.location.href, {path:'/'});
}
function setFontSize(ctrl, target, cookieid) {
	if($.cookie(cookieid)) {
		$(target).addClass($.cookie(cookieid));
		$(ctrl + "[data-size='" + $.cookie(cookieid) + "']").addClass("here");
	}
	$(ctrl).bind("click", function(){
		$(target).removeClass($.cookie(cookieid));
		$(ctrl + "[data-size='" + $.cookie(cookieid) + "']").removeClass("here");
		$.cookie(cookieid, $(this).data("size"), {expires:365,path:'/'});
		$(target).addClass($.cookie(cookieid));
		$(ctrl + "[data-size='" + $.cookie(cookieid) + "']").addClass("here");
	});
}
function getNoCachePath(path) {
	return path + "?s=" + new Date().format("ssS");
}

/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 *
 * @param num 数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g,'');
    if(num == 0){
    	return 0;
    }
    if(isNaN(num)){
		num = "0";
    }
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10){
    	cents = "0" + cents;
    }
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++){
    	num = num.substring(0,num.length-(4*i+3))+','+num.substring(num.length-(4*i+3));
    }
    return (((sign)?'':'-') + num + '.' + cents);
}
//判断是否是移动终端
function isMobile() {
	return !!navigator.userAgent.match(/AppleWebKit.*Mobile.*/);
	//以下代码用作以后参考
	//var browser = {
	//    versions: function () {
	//        var u = navigator.userAgent, app = navigator.appVersion;
	//        return {         //移动终端浏览器版本信息
	//            trident: u.indexOf('Trident') > -1, //IE内核
	//            presto: u.indexOf('Presto') > -1, //opera内核
	//            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
	//            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
	//            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
	//            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
	//            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
	//            iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
	//            iPad: u.indexOf('iPad') > -1, //是否iPad
	//            webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
	//        };
	//    }(),
	//    language: (navigator.browserLanguage || navigator.language).toLowerCase()
	//}
	//return browser.versions.mobile;
}