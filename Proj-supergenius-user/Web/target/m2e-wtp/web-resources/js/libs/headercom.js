$(function(){
	$("#navuserlink").click(function(){
		$(".navuser .usernews").toggleClass("hd");
	});
	$(".toprightleft").mouseenter(
		function(){
			$(".clickbutton").html("&and;");
			$(".userdetails").removeClass("hd");
		}
	);
	$(".toprightleft").mouseleave(function(){
		$(".clickbutton").html("&or;");
		$(".userdetails").addClass("hd");
	});
});