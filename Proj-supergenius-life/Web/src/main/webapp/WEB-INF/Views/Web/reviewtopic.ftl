[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<title>${title!'天才人生'}</title>
	<link href="${basecss}/css/default/index.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
</head>
<body>
	<div class="contentLeft">
		<h3 class="altertag">
			备选话题<span>获赞越多越可能成为正式话题</span>
			[#if me??]
				<a href="${base}/my/topic/contribute?cid=${cid?c}" class="shareLearnExperience">话题投稿</a>
			[#else]
				<a class="shareLearnExperience pop-up">话题投稿</a>
			[/#if]
		</h3>
		<ul class="hotArticleList alterList">
			[#if list??]
				[#assign i=1]
				[#list list as item]
					<li>
						<span class="[#if i<4]redtopic[/#if] normalNum">${i}</span>
						<a href="${base}/topic/review/${(item.cid)?c}/${item.oid}" title="${item.title}" target="_blank">
							${item.title}
						</a>
						<i class="minutes">[@p.mytime datetime="${item.createtimeStr}"/]</i>
						<p>[@p.TrimSubstring content="${item.content}" mylength=60 /]</p>
						<span class="topicnews"><img src="${baseimg}/imgs/default/dthumb.png" alt="">${item.prizecount}</span>
					</li>
					[#assign i = i+1]
				[/#list]
			[/#if]
		</ul>
	</div>
	<div class="contentRight">
		<ul class="hotArticle topTopic">
			<li>
				<span class="uptopicon">置顶</span>
				<a href="${base}/topic/${(topic.cid)?c}/${topic.oid}" title="${topic.title}">
					<img src="${webimg}${topic.imgmedium}" alt="${topic.title}" title="${topic.title}">
					<div class="description">
						${topic.title}
					</div>
				</a>
			</li>
		</ul>
		<div class="workplaceitem">
			<h3>
				往期话题
				[#if relateList?size>9]<a class="change" href="javascript:;"  onclick="loadtopic();" id="changetopic">换一换<img src="${baseimg}/imgs/default/refresh.png" alt="换一批" title="换一批"></a>[/#if]
			</h3>
			<ul class="hotArticleList oldTopicsList">
				[#if relateList??]
					[#list relateList as item]
						<li>
							<a href="${base}/topic/${(item.cid)?c}/${item.oid}" title="${item.title}">
								${item.title}
							</a>
							<span class="topicnews"><img src="${baseimg}/imgs/default/dthumb.png" alt="">${item.prizecount}</span>
						</li>
					[/#list]
				[/#if]
			</ul>
		</div>
	</div>
<script>
		var relatenum = 2;
		var topicnum = 2;
		$(function(){
			$.fn.scrollHandle({
				obj: window,
				fun: loadmore
			})
		});
		
		// 加载更多备选话题
		function loadmore() {
			var json = {};
			json.cid='${cid?c}';
			json.pagenum=topicnum;
			var	url = "${base}/ajax/topic/review/moretopic";
			if(prePage != json.pagenum) {
			    prePage = json.pagenum;
				$.get(url, json, function(data){
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
						return false;
					}
					$(".alterList").append(data);
					topicnum+=1;
				});
			}
		}
		
		// 话题换一换
		function loadtopic() {
			var	url = "${base}/ajax/topic/relatetopic";
			var json ={};
			json.pagenum=relatenum;
			json.cid='${cid?c}';
			$.get(url, json, function(data){
				if(data.trim() == ""){// 没有更多内容
					$("#changetopic").click();
					relatenum=1;
					return false;
				}
				$(".oldTopicsList").html(data);
				relatenum+=1;
			});
		}
		
	</script>
</body>
</html>

