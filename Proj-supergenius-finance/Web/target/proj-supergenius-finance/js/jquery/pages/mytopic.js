$(function(){
	$(".yaoqing").click(function(){
		$('.topic_invite').css("display","block");
		$('#login').css("display","block")
	})
	$(".invite_close img").click(function(){
		$('.topic_invite').css("display","none");
		$('#login').css("display","none")
	})
	$("#two1").click(function(){
		$("#invite_two_1").css("display","block")
		$("#invite_two_2").css("display","none")
		$(this).addClass('current ')
		$("#two2").removeClass('current ')
	})
	$("#two2").click(function(){
		$("#invite_two_1").css("display","none")
		$("#invite_two_2").css("display","block")
		$(this).addClass('current ')
		$("#two1").removeClass('current ')
	})

	$(".invite_rt_but").click(function(){
		$("#wei").css("display","none")
		$("#cheng").css('display',"block")

	})
	$("#jixu").click(function(){
		$("#wei").css("display","block")
		$("#cheng").css('display',"none")
	})
})