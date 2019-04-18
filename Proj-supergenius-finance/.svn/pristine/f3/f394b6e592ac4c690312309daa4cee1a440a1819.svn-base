function adaptWXphoto(articleContent){
	$(articleContent).children().find("img").each(function(){
		src = $(this).attr("src");
		if(src != 'undefined' && src != undefined && src.indexOf("qpic.cn")!=-1){
			src = src.replace("tp=webp","tp=png");
			$(this).attr("src",src);
		}	
	})
}