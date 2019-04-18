/************************************************************************************************
 * 有".pop-up"clas属性的Element将触发弹出框登录
 */
$(function(){
	$(".pop-up").each(function(){
		$(this).bind("click", defaultInitLogin);
	});
})

var defaultInitLogin = function() {
	$("form [name='usersn']").val("");
	$(".uname img").attr("src", g.baseimg + "/imgs/default/userbefore.png");
	$("form [name='password']").val("");
	$(".upswd img").attr("src", g.baseimg + "/imgs/default/psword.png");
	$("form [name='a']").attr("checked", false);
	$(".warninginform").hide();
	$(".logindisable").css("display", "block");
	$(".loginlink").css("display", "none");
	if($("form [name='usersn']").val() != "" && $.trim($("[name='password']").val()).length >= 6) {
		$(".logindisable").css("display", "none");
		$(".loginlink").css("display", "block");
	}
	$(".mask").show();
	$("#loginmask").css("height", $(document.body).height());
	$("#loginshow").show();
}

/**
 * enter登录
 */
function keyLogin() {
	if (event.keyCode==13) {
		if($(".loginlink").css("display") == "block") {
			login();
		}
	}
}

/**
 * 登录
 */
function login(){
	var usersn = $("form [name='usersn']").val().trim();
	var password = $("form [name='password']").val();
	if (usersn == "" || password.length < 6) {
		return ;
	}
	var rememberme = false;
	if ($("form [name='a']").attr("checked") == "checked") {
		rememberme = true;
	}
	var url = g.base + "/ajax/login";
	var args = {
			'email':usersn,
			'password':password,
			'rememberme':rememberme
	}
	$.post(getNoCachePath(url), args, function(data){
		if (data.success) {
			window.location.reload();
		} else {
			$(".warninginform").show();
		}
	})
}

/**
 * 登录按钮状态切换
 */
function loginButton(){
	if ($.trim($("[name='usersn']").val()) != "" && $("[name='password']").val().length >= 6) {
		$(".logindisable").css("display", "none");
		$(".loginlink").css("display", "block");
	} else {
		$(".logindisable").css("display", "block");
		$(".loginlink").css("display", "none");
	}
}

$(function(){
	$("form.inputs .usersinform").focus(function(){
		$(".uname img").attr("src", g.baseimg + "/imgs/default/userafter.png");
	});
	$("form.inputs .usersinform").focusout(function(){
		var inputval01 = $.trim($("#usersinform").val());
		if(inputval01 != ''){
			$(".uname img").attr("src", g.baseimg + "/imgs/default/userafter.png");
		}else{
			$("#usersinform").val("")
			$(".uname img").attr("src", g.baseimg + "/imgs/default/userbefore.png");
		}
	});
	$("form.inputs .psword").focus(function(){
		$(".upswd img").attr("src", g.baseimg + "/imgs/default/pswordafter.png");
	});
	$("form.inputs .psword").focusout(function(){
		var inputval02 = $("form.inputs .psword").val();
		if(inputval02 != ''){
			$(".upswd img").attr("src", g.baseimg + "/imgs/default/pswordafter.png");
		}else{
			$(".upswd img").attr("src", g.baseimg + "/imgs/default/psword.png");
		}
	});
	$(".logincontainer .close").click(function(){
		$(".mask").hide();
		$(".maskcontentlogin").hide();
	});
	$(".warninginform").hide();
	/*遮罩层及弹框居中*/
	var maskcontentownh = $(".maskcontentlogin").height();
	var bottomh = $(".bottom").offset().top + 220;
	var windowh = $(window).innerHeight();
	var maskcontentownh = $(".maskcontentlogin").height();
	var maskcontenth = (windowh - maskcontentownh)/2;
	var maskcontentw = ($(window).outerWidth() - $(".maskcontentlogin").width())/2;
	$(".mask").height(bottomh);
	$(".maskcontentlogin").css({ "top": maskcontenth, "left": maskcontentw });
	/*点击关闭及遮罩层关闭弹框*/
	$(".cancel").click(function(){
		$(".maskcontentlogin").hide("fast","linear");
		$(".mask").hide();
	});
	$(".mask").click(function(){
		$(".maskcontentlogin").hide();
		$(this).hide();
	});
	
	/*登录点击事件*/
	$(".loginlink").click(function(){
		login();
	})
	/*自动登录*/
	$("#autologin").click(function(){
		if ($("[name='a']").attr("checked") == "checked") {
			$("[name='a']").attr("checked", false)
		} else {
			$("[name='a']").attr("checked", "checked");
		}
	})
});
