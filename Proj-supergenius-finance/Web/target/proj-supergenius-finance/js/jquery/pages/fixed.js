$(function (){
	var lastChild = $(".ranklist").last();
    var offsetT = lastChild.offset().top;
    var topH = $(".top_02").height() + 10;
    $(window).load(function(){
        Position(topH);
    });
    //比较scrollTop和offsetTop,当scrollT大于offsetT即固定
    function Position (topH) {
        $(window).scroll(function(){
            var scrollT = $(window).scrollTop();
            if (scrollT >= offsetT) {
                lastChild.css({
                    position: "fixed",
                    top: topH + "px"
                });
            }else {
                lastChild.css({
                    position: "static"
                });
            }
        });  
    }
});
	
