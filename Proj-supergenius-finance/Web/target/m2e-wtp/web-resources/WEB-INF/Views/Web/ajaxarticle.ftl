[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if list??]
	[#list list as article]
		<li>
			<a class="tabitemimg" href="${base}/article/[#if cid??]${cid}[#else]${article.firstCid}[/#if]/${(article.oid)?c}">
				<img src="${webimg}${article.imgmedium}" alt="${article.title}" title="${article.title}" />
			</a>
			<div class="tabitemRt">
				<a href="${base}/article/[#if cid??]${cid}[#else]${article.firstCid}[/#if]/${(article.oid)?c}" class="articleTitle">
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
						<a href="${base}/article/[#if cid??]${cid}[#else]${article.firstCid}[/#if]/${(article.oid)?c}" class="littleicon">
							<img src="${baseimg}/imgs/default/clickcount.png" />
							${article.clickcount}
						</a>
						<a href="${base}/article/[#if cid??]${cid}[#else]${article.firstCid}[/#if]/${(article.oid)?c}" class="littleicon">
							<img src="${baseimg}/imgs/default/discuss.png" />
							${article.commentscount}
						</a>
						<a href="${base}/article/[#if cid??]${cid}[#else]${article.firstCid}[/#if]/${(article.oid)?c}" class="littleicon">
							<img src="${baseimg}/imgs/default/heart.png" />
							${article.prizecount}
						</a>
					</div>
				</div>
			</div>
		</li>
	[/#list]
[/#if]
