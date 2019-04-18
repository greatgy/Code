[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="referrer" content="never" />
	<title>${title!'天才人生话题详情页'}</title>
	<link href="${basecss}/css/mobile/default/index.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${basejs}/js/pages/reviewprize.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
	<style type="text/css">
		.bdshare-button-style0-16 a, .bdshare-button-style0-16 .bds_more, .bdshare-button-style0-32 a{
    	        background-image: url(${baseimg}/imgs/icons_0_16.png)!important;
    	    }
	</style>
</head>
<body>
	<div class="topicDetailBox">
		<img src="${webimg}/${bean.imgbig}" alt="${bean.title}" title="${bean.title}" />
		<h3>
			<span>${bean.title}</span>
		</h3>
		${bean.content}
		<div class="topictips">
			<span>${bean.author}</span>
			<span>${(bean.createtime).toString("yyyy年MM月dd日 ")}</span>
			<span class="readTimes">阅读&nbsp;&nbsp;${bean.clickcount!'0'}</span>
		</div>
	</div>
	<div class="alterzan">
		<a href="javascript:;" id="btnprize_topicpraise_${(me.oid?c)!"0"}_${(bean.cid)?c}_${bean.uid}" data-counter=".prizecounter">
			<img src="${baseimg}/imgs/default/bigzan.png" class="normalimg" alt="赞" title="赞" data-notcomment="true" data-isprize="${bean.isprize!'false'}"/>
			<span class="zancountjs">${bean.prizecount!"0"}</span>
		</a>
	</div>
	<div class="sharefriends">
		成为正式话题才可参与讨论，快让朋友来帮你加油打气吧~
		<p>
			立即分享，让朋友帮点赞:
			<div class="bdsharebuttonbox" style="float: right;margin-top: -40px;margin-right:30px;overflow:inherit;">
				<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
				<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
			</div>	
		</p>
	</div>
	
	<div class="workplaceitem">
		<h3>
			往期备选话题
			[#if relateList?size>9]<a class="change" href="javascript:;" onclick="loadtopic();" id="changetopic">换一换<img src="${baseimg}/imgs/default/refresh.png" alt="换一批" title="换一批"></a>[/#if]
		</h3>
		<ul class="hotArticleList oldTopicsList">
			[#if relateList??]
				[#list relateList as item]
					<li>
						<a href="${base}/topic/review/${(item.cid)?c}/${item.oid}" title="${item.title}">
							${item.title}
						</a>
						<span class="topicnews"><img src="${baseimg}/imgs/default/dthumb.png" alt="">${item.prizecount}</span>
					</li>
				[/#list]
			[/#if]
		</ul>
	</div>
<script>
		var base = '${base}';
		$(function(){
			prizeBindEvent();
			window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='${base}/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
		});
		
		var topicnum = 2;
		// 话题换一换
		function loadtopic() {
			var	url = "${base}/ajax/topic/relatetopic";
			var json ={};
			json.pagenum=topicnum;
			json.isreview=1;
			json.cid='${cid?c}';
			$.get(url, json, function(data){
				if(data.trim() == ""){// 没有更多内容
					topicnum=1;
					$("#changetopic").click();
					return false;
				}
				$(".oldTopicsList").html(data);
				topicnum+=1;
			});
		}
		
	</script>
</body>
</html>

