[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
</head>
<body>
	<div class="topicBox">
		<ul class="judgement">
			<li class="curjudge" onclick="lastTopics(this);" id="lastest">最新</li>
			<li onclick="hotTopics(this);">最热</li>
		</ul>
		[#if me??]
			<a href="${base}/m/my/topic/contribute?cid=${cid?c}" class="publishBtn">+发布话题</a>
		[#else]
			<a href="${base}/m/login?cid=${cid?c}&pcid=${pcid}" class="publishBtn">+发布话题</a>
		[/#if]
		<ul class="talentList topicList">
		</ul>
	</div>
	
	<script type="text/javascript">
		$(function(){
			$("#lastest").click();
			$.fn.scrollHandle({
				obj: window,
				fun: loadmore
			})
		})
		
		var pagenum = 1;
		var ishot = false;
		// 加载更多话题 
		function loadmore() {
			if(pagenum == 0){
				return false;
			}
			var json = {};
			json.cid='${cid?c}';
			json.pagenum=pagenum;
			prePage = pagenum - 1;
			var url = "";
			if(ishot == true){
				url = "${base}/m/ajax/topic/hottopic";
			} else{
				url = "${base}/m/ajax/topic/moretopic";
			}
			if(prePage != json.pagenum) {
			    prePage = json.pagenum;
				$.get(url, json, function(data){
					if(data.trim() == ""){// 没有更多内容
						pagenum=0;
						return false;
					}
					$(".topicList").append(data);
					pagenum+=1;
				});
			}
		}
		
		
		//获得最热话题
		function hotTopics(obj){
			ishot = true;
			pagenum = 1;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/m/ajax/topic/hottopic";
			var json = {};
			json.cid='${cid?c}';
			json.pagenum=pagenum;
			$.get(url, json,function(data){
				if(data != ''){
					pagenum +=1;
					$(".topicList").html(data);
				}
			});
		}
		
		//获得最新话题
		function lastTopics(obj){
			ishot = false;
			pagenum = 1;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/m/ajax/topic/moretopic";
			var json = {};
			json.cid='${cid?c}';
			json.pagenum=pagenum;
			$.get(url, json,function(data){
				if(data != ''){
					pagenum +=1;
					$(".topicList").html(data);
				}
			});
		}
	</script>
</body>
</html>