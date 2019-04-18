$(function(){
	/*遮罩层及弹框居中*/
	var bottomh = $(".bottom").offset().top + 220;
	var windowh = $(window).innerHeight();
	var maskcontentownh = $(".maskContent").height();
	var maskcontenth = (windowh - maskcontentownh)/2;
	var maskcontentw = ($(window).outerWidth() - $(".maskContent").width())/2;
	$(".coursemask").height(bottomh);
	$(".maskContent").css({ "top": maskcontenth, "left": maskcontentw });

	/*点击关闭及遮罩层关闭弹框*/
	$(".cancelBtn").click(function(){
		$(".maskContent").hide("fast","linear");
		$(".coursemask").hide();
	});
	$(".coursemask").click(function(){
		$(".maskContent").hide();
		$(this).hide();
	});
});
	