$(function(){
	var xiala = 0 ;
	$("#xiala").click(function(){
		
	       if( 0 == xiala){
	       	$("#xiala_n").css("display","block");
	       	xiala = 1 ;
	       }else{
	       	$("#xiala_n").css("display","none");
	       	xiala = 0 ;
	       }
	});
	$("#xiala_n li").click(function(){
		var test = $(this).find("span").html();
		$("#xiala_n").css("display","none");
		$("#xiala").prev().val(test);
		xiala = 0 ;
	})
})