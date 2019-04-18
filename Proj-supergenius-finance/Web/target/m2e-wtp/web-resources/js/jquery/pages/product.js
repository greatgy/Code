$(function(){
	$(".db_last a").hover(function(){
         $(".db_last a").removeClass('db_lastcolor');
         $(this).addClass('db_lastcolor')
	})
	$(".db_lflink a").hover(function(){
		$(".db_lflink a").removeClass('db_color');
		$(this).addClass('db_color')
	})
})