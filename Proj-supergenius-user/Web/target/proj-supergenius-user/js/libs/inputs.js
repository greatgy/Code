$(function(){
	$("form.inputs .usersinform").focus(function(){
		$(".uname img").attr("src","http://sv2m.supergenius.cn/imgs/default/userafter.png");
	});
	$("form.inputs .usersinform").focusout(function(){
		var inputval01 = $.trim($("#usersinform").val());
		if(inputval01 != ''){
			$(".uname img").attr("src","http://sv2m.supergenius.cn/imgs/default/userafter.png");
		}else {
			$("#usersinform").val("")
			$(".uname img").attr("src","http://sv2m.supergenius.cn/imgs/default/userbefore.png");
		}
	});
	$("form.inputs .psword").focus(function(){
		$(".upswd img").attr("src","http://sv2m.supergenius.cn/imgs/default/pswordafter.png");
	});
	$("form.inputs .psword").focusout(function(){
		var inputval02 = $("form.inputs .psword").val();
		if(inputval02 != ''){
			$(".upswd img").attr("src","http://sv2m.supergenius.cn/imgs/default/pswordafter.png");
		}else{
			$(".upswd img").attr("src","http://sv2m.supergenius.cn/imgs/default/psword.png");
		}
	}); 	
});
