[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if list??]
	[#list list as item]
		<li>
			<a href="${base}/article/${(item.cid)?c}/${(item.oid)?c}" class="articleTitle">
				${item.title}
			</a>
			<p class="descripe">
				[#if item.summary !=""]
					[@p.TrimSubstring content="${item.summary}" mylength=50 /]
				[/#if]
			</p>
			<div class="timepiece">
				<span class="time">
					[@p.mytime datetime="${item.createtimeStr}"/]
				</span>
				<span class="location"><img src="${baseimg}/imgs/default/location.png" alt="位置" title="位置" />${item.placename}</span>
			</div>
		</li>
	[/#list]
[/#if]

