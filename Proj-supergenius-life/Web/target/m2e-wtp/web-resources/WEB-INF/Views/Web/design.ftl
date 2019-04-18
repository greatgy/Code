[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/history.js"></script>
</head>
<body>
	<div class="contentLeft">
		<div class="memberOnly">
			<h2>
				会员专区
				<a 
				[#if me??]
	        		[#if me.getIsUser()] class="all" href="${base}/problem/${cid?c}_1"[#else]href="javascript:;" class="all normalUsertip"[/#if]
	        	[#else]
	        		href="javascript:;" class="pop-up all"
	        	[/#if]>全部</a>
			</h2>
			<ul class="signList">
				[#if problemList??]
					[#list problemList as item]
						<li>
							<a [#if me??][#if me.getIsUser()]href="${base}/problem/detail/${(item.cid)?c}/${item.oid}"[#else]class="normalUsertip"[/#if][#else] class="pop-up"[/#if]>${item.title}</a>
							[#if item.state != 0]
								<span>专家已点评</span>
							[/#if]
						</li>
					[/#list]
				[/#if]
			</ul>
		</div>
		<div class="articles">
			<h3>文章</h3>
			<ul class="tabBox">
				[#if list??]
					[#list list as article]
						<li>
                            <a class="tabitemimg" href="${base}/m/article/[#if cid??]${cid?c}[#else]${(article.firstCid)?c}[/#if]/${(article.oid)?c}">
                                <img src="${webimg}${article.imgmedium}" alt="${article.title}" title="${article.title}" />
                            </a>
                            <div class="tabitemRt">
                                <a href="${base}/m/article/[#if cid??]${cid?c}[#else]${(article.firstCid)?c}[/#if]/${(article.oid)?c}" class="articleTitle">
                                    [#if article.isoriginal == 1]
                                        <span class="original">[原创]</span>
                                    [/#if]
                                    [#if article.kind == 1]
                                        <span class="videotype">[视频]</span>
                                    [#elseif article.kind == 2]
                                        <span class="pictype">[图片]</span>
                                    [/#if]
                                    ${article.title}
                                </a>
                                <p class="descripe">
                                    [#if article.summary==""]
                                        [@p.TrimSubstring content="${article.content}" mylength=80 /]
                                    [#else]
                                        [@p.TrimSubstring content="${article.summary}" mylength=80 /]
                                    [/#if]
                                </p>
                                <div class="timepiece">
                                    <span class="time">
                                        [@p.mytime datetime="${article.createtimeStr}"/]
                                    </span>
                                    <div class="operateBox">
                                        <a href="${base}/m/article/[#if cid??]${cid?c}[#else]${(article.firstCid)?c}[/#if]/${(article.oid)?c}" class="littleicon">
                                            <img src="${baseimg}/imgs/default/clickcount.png" />
                                            ${article.clickcount}
                                        </a>
                                        <a href="${base}/m/article/[#if cid??]${cid?c}[#else]${(article.firstCid)?c}[/#if]/${(article.oid)?c}" class="littleicon">
                                            <img src="${baseimg}/imgs/default/heart.png" />
                                            ${article.prizecount}
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </li>
					[/#list]
				[/#if]
			</ul>
		</div>
	</div>
	<div class="contentRight">
			[#if adList?? && adList?size>0]
				<div class="vdbox">
					[#list adList as item]
						<a href="${item.originurl}"><img src="${webimg}/${item.content}" alt="${item.name}" /></a>
					[/#list]
				</div>
			[/#if]
		<div class="workplaceitem">
			<h3>
				热门文章
			</h3>
			<ul class="hotArticles">
				[#if hotArticleList??]
					[#list hotArticleList as item]
						<li>
							<a href="${base}/article/[#if cid??]${cid?c}[#else]${(item.firstCid)?c}[/#if]/${(item.oid)?c}">
								${item.title}
							</a>
							<span class="flashtime">[@p.mytime datetime="${item.createtimeStr}"/]</span>
						</li>
					[/#list]
				[/#if]
		    </ul>
		</div>
	</div>
	<script>
		$(function(){
			$.fn.scrollHandle({
				obj: window,
				fun: loadmore
			})
			ajax_history('${base}/ajax/article/catalogue/${cid?c}', '${pagesize}', '.tabBox', 1, '${FirstLoadSize}');// 加载文章的url, 下拉加载时的加载size, 文章列表父级, pagenum, 首次加载size（去除轮播后的）
		})
		
		var prePage = 2;
		var pagenum = 3;
		// 加载更多文章
		function loadmore() {
			var	url = "${base}/ajax/otherpage/catalogue/${cid?c}";
			if(prePage != pagenum) {
			    prePage = pagenum;
				$.get(url, {pagenum:pagenum}, function(data){
					if(data.trim() == ""){// 没有更多内容
						$(window).unbind("scroll", defaultScrollHandler);
						return false;
					}
					$(".tabBox").append(data);
					history_num = pagenum;
					pagenum+=1;
				});
			}
		}
	</script>
</body>
</html>