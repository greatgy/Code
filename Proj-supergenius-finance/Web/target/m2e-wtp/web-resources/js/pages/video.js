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
					html = '<video width="300" height="150" controls="controls"><source src="'+videosrc+'" type="video/mp4"><source src="'+videosrc+'" type="video/webm"><source src="'+videosrc+'" type="video/ogv"></video>';
				} else if (isPhone == 0){
					html = '<video width="600" height="450" controls="controls"><source src="'+videosrc+'" type="video/mp4"><source src="'+videosrc+'" type="video/webm"><source src="'+videosrc+'" type="video/ogv"></video>';
				}
				if(navigator.userAgent.indexOf("iPad") != -1){  
		            html = '<video width="400" height="150" controls="controls"><source src="'+videosrc+'" type="video/mp4"><source src="'+videosrc+'" type="video/webm"><source src="'+videosrc+'" type="video/ogv"></video>';
				}
			}	
			$(this).html(html);
		}	
	})
	
	if(isPhone != 'undefined' && isPhone != undefined && isPhone != ''){
		var mod_player = $(articleContent).children().find("iframe");
		$(mod_player).css({"width":"319px","height":"180px"});
		$(".flashCodeCss").css("height","180px");
	}
}