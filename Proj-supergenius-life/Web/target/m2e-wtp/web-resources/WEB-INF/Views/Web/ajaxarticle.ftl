[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if list??]
	[#list list as article]
			[#if article.imgmedium??]
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
			[#else]
				<li>
					<div class="reciveLi">
						<a href="${base}/m/article/${(article.firstCid)?c}/${(article.oid)?c}" class="articleTitle">
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
								<a href="${base}/m/article/${(article.firstCid)?c}/${(article.oid)?c}" class="littleicon">
									<img src="${baseimg}/imgs/default/clickcount.png" />
									${article.clickcount}
								</a>
								<a href="${base}/m/article/${(article.firstCid)?c}/${(article.oid)?c}" class="littleicon">
									<img src="${baseimg}/imgs/default/heart.png" />
									${article.prizecount}
								</a>
							</div>
						</div>
					</div>
				</li>
			[/#if]
	[/#list]
[/#if]

