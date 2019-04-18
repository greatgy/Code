[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<link rel="stylesheet" href="${basecss}/css/mobile/pagination.css">
	<script type="text/javascript" src="${basejs}/js/mobile/jquery.pagination.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
</head>
<body>
	<div class="contentLeft">
		<div class="memberOnly">
			<h2>
				会员专区
				[#if me??]
	        		<a [#if me.getIsUser()] class="all" href="${base}/problem/${cid?c}_1"[#else]href="javascript:;" class="all normalUsertip"[/#if]>全部</a>
	        	[#else]
	        		<a href="${base}/m/login?cid=${cid?c}&pcid=${pcid}" class="all">全部</a>
	        	[/#if]
			</h2>
			<ul class="signList">
				[#if problemList??]
					[#list problemList as item]
						<li>
							<a [#if me??][#if me.getIsUser()]href="${base}/problem/detail/${(item.cid)?c}/${item.oid}"[#else]class="normalUsertip"[/#if][#else] href="${base}/m/login?cid=${cid?c}&pcid=${pcid}"[/#if]>${item.title}</a>
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
			<div class="M-box2"></div>
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
		[#if list?? && list?size>0]
			$('.M-box2').pagination({
	            coping:true,
	            homePage:'首页',
	            endPage:'末页',
	            prevContent:'上页',
	            nextContent:'下页',
	            isHide:true,
	            totalData:${count},
	            showData:${pagesize},
	             callback:function(api){
	                var data = {
	                    pagenum: api.getCurrent()
	                };
	                $.get('${base}/m/ajax/otherpage/catalogue/${cid?c}',data,function(json){
	                   $(".tabBox").html(json);
	                   $("#bctop").click();
	                });
	            }
	        });
		[/#if]	        
	</script>
</body>
</html>