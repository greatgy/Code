[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if list??]
	[#list list as item]
		<li>
			<a href="${base}/article/${(item.cid)?c}/${(item.oid)?c}" class="articleTitle"  target="_blank">
				${item.title}
				<span class="location"><img src="${baseimg}/imgs/default/location.png" alt="位置" title="位置" />${item.placename}</span>
			</a>
			<p class="descripe">
				[#if item.content==""]
				[#else]
					[@p.TrimSubstring content="${item.content}" mylength=50 /]
				[/#if]
			</p>
			<div class="timepiece">
				<span class="time">
					[@p.mytime datetime="${item.createtimeStr}"/]
				</span>
				<div class="operateBox">
					<a href="${base}/article/${(item.cid)?c}/${(item.oid)?c}" class="littleicon">
						<img src="${baseimg}/imgs/default/clickcount.png">
						${item.clickcount}
					</a>
					<a href="${base}/article/${(item.cid)?c}/${(item.oid)?c}" class="littleicon">
						<img src="${baseimg}/imgs/default/heart.png">
						${item.commentscount}
					</a>
				</div>
			</div>
		</li>
	[/#list]
[/#if]
