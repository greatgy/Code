$(function() {
	/* 遮罩层及弹框居中 */
	var bottomh = $(".bottom").offset().top + $(".bottom").height() / 3;
	var windowh = $(window).innerHeight();
	var editContainerh = $("#editContainer").height();
	var tophigh = (windowh - editContainerh) / 2;
	var editContainerleft = ($(window).outerWidth() - $("#editContainer")
			.width()) / 2;
	$("#maskcover").height(bottomh);
	$("#editContainer").css({
		"top" : tophigh,
		"left" : editContainerleft
	});

	/* 点击关闭及遮罩层关闭弹框 */
	$("#cancelbtn").click(function() {
		$("#editContainer").hide();
		$("#maskcover").hide();
	});
	$("#maskcover").click(function() {
		$("#editContainer").hide();
		$(this).hide();
	});

	/* 点击编辑信息出现弹框 */
	$("#editInforbtn").click(function() {
		$("#editContainer").show();
		$("#maskcover").show();
	});
	/* 点击弹框中取消关闭弹框 */
	$(".buttons .cancel").click(function() {
		$("#editContainer").hide();
		$("#maskcover").hide();
	});

	/* 添加订阅弹框定位 */
	var subsboxH = $("#interestSubs").height();
	var topH = (windowh - subsboxH) / 2;
	var subsBoxleft = ($(window).outerWidth() - $("#interestSubs").width()) / 2;
	$("#mask").height(bottomh);
	$("#interestSubs").css({
		"top" : topH,
		"left" : subsBoxleft
	});

	/* 添加订阅弹框点击关闭及遮罩层关闭弹框 */
	$(".closeimg").click(function() {
		$("#interestSubs").hide();
		$("#mask").hide();
	});
	$("#mask").click(function() {
		$("#interestSubs").hide();
		$(this).hide();
	});
	/* 点击添加订阅出现弹框 */
	$("#addmoreimg").click(function() {
		$("#interestSubs").show();
		$("#mask").show();
	});
	$(".save").click(function() {
		if ($("#company").text() == "" || $("#department").text() == "" || $("#job").text() == "" || $("#summary").text() == "") {
			alert("填写完整的信息");
			return;
		} else {
			var params = {};
			params.uid=$("#uid").val();
			params.company = $("#company").text();
			params.department = $("#department").text();
			params.job = $("#job").text();
			params.summary = $("#summary").val();
			$.post(g.base + "/ajax/my/edit", params, function(data) {
				if (data.success) {
					$("#editContainer").hide();
					$("#maskcover").hide();
					window.location.reload();
				}
			})
		}
	})
});
