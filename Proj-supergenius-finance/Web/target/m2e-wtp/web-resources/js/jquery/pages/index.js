// JQuery load
(function() {
	$(document).ready(function() {
		$(".mf_a").hover(function() {
			$(this).find(".mf_bg").css({
				"top" : "0px",
				"height" : "100%",
				"background" : "#1d71b7",
				"opacity" : "0.6"
			});
			$(this).find(".p1").css({
				"height" : "100%"
			});
			$(this).find(".p1 a").css({
				"padding-top" : "30px"
			});
		}, function() {
			$(this).find(".mf_bg").css({
				"top" : "128px",
				"background" : "#000",
				"opacity" : "0.5"
			});
			$(this).find(".p1").css({
				"height" : "30px"
			});
			$(this).find(".p1 a").css({
				"padding-top" : "0"
			});
		});
		$("#mf_a .mf_a").hover(function() {
			$(this).find(".mf_bg").css('padding-top', '20px').stop().delay(50).animate({
				"top" : 0,
				opacity : 0.9
			}, 300);
		}, function() {
			$(this).find(".mf_bg").css('padding-top', '0px').stop().animate({
				"top" : 110
			}, 300);

		});
		new Components.BroadcastPanel($('.ma'), {
			hots : broadcastData
		});
	});
})();
