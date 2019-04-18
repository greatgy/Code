$(function(){
	/*遮罩层及弹框居中*/
	var bottomh = $(".bottom").offset().top + 220;
	var windowh = $(window).innerHeight();
	var maskcontentownh = $(".maskcontent").height();
	var maskcontenth = (windowh - maskcontentownh - 20)/2;
	var maskcontentw = ($(window).outerWidth() - 420)/2;
	$(".mask").height(bottomh);
	$(".maskcontent").offset({ top: maskcontenth, left: maskcontentw });;

	/*点击关闭及遮罩层关闭弹框*/
	$(".cancel").click(function(){
		$(".maskcontent").hide("fast","linear");
		$(".mask").hide();
	});
	$(".mask").click(function(){
		$(".maskcontent").hide();
		$(this).hide();
	});
});
	