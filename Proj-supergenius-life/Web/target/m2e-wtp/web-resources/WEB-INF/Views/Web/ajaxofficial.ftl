[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if list??]
	[#list list as article]
		[#if article.imgmedium??]
			<li>
				<a class="tabitemimg" href="${base}/m/article/${article.firstCid?c}/${(article.oid)?c}">
					<img src="${webimg}${article.imgmedium}" alt="${article.title}" title="${article.title}" />
				</a>
				<div class="tabitemRt">
					<a href="${base}/m/article/${article.firstCid?c}/${(article.oid)?c}" class="articleTitle">
						${article.title}
					</a>
					<div class="timepiece">
						<span class="time">
							[@p.mytime datetime="${article.createtimeStr}"/]
						</span>
						<div class="operateBox">
							<a href="${base}/m/article/${article.firstCid?c}/${(article.oid)?c}" class="littleicon">
								<img src="${baseimg}/imgs/default/clickcount.png" />
								${article.clickcount}
							</a>
						</div>
					</div>
				</div>
			</li>
		[#else]
			<li>
				<div class="reciveLi">
					<a href="${base}/m/article/${(article.firstCid)?c}/${(article.oid)?c}" class="articleTitle">
						${article.title}
					</a>
					<div class="timepiece">
						<span class="time">
							[@p.mytime datetime="${article.createtimeStr}"/]
						</span>
						<div class="operateBox">
							<a href="${base}/m/article/${(article.firstCid)?c}/${(article.oid)?c}" class="littleicon">
								<img src="${baseimg}/imgs/default/clickcount.png" />
								${article.clickcount}
							</a>
						</div>
					</div>
				</div>
			</li>
		[/#if]
	[/#list]
[/#if]

