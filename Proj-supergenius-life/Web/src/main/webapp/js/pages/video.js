function adaptVideo(articleContent, isPhone){
	//适配视频
	$(".flashCodeCss").children().css("width","100%");
	$(".flashCodeCss").children().css("height","100%");
	var html = '';
	var videosrc ='';
	var src= '';
	var embed = '';
	$(articleContent).children("p").each(function(){
		embed = $(this).children().find("embed");
		if(embed != undefined && embed != 'undefined' && embed.length>0){
			src = $(embed).attr("src");
			if(src != 'undefined' && src != undefined && src.indexOf("=")!=-1){
				videosrc=src.split("=")[1];
				if(isPhone != 'undefined' && isPhone != undefined && isPhone != ''){
					html = '<video width="350" height="200" controls="controls"><source src="'+videosrc+'" type="video/mp4"><source src="'+videosrc+'" type="video/webm"><source src="'+videosrc+'" type="video/ogv"></video>';
				} else if (isPhone == 0){
					html = '<video width="715" height="400" controls="controls"><source src="'+videosrc+'" type="video/mp4"><source src="'+videosrc+'" type="video/webm"><source src="'+videosrc+'" type="video/ogv"></video>';
				}
				if(navigator.userAgent.indexOf("iPad") != -1){  
		            html = '<video width="100%" height="auto" controls="controls"><source src="'+videosrc+'" type="video/mp4"><source src="'+videosrc+'" type="video/webm"><source src="'+videosrc+'" type="video/ogv"></video>';
				}
			}	
			$(this).html(html);
		}	
	})
	
	if(isPhone != 'undefined' && isPhone != undefined && isPhone != ''){
		var mod_player = $(articleContent).children().find("iframe");
		$(mod_player).css({"width":"100%","height":"100%"});
		$(".flashCodeCss").css("height","180px");
	}
}