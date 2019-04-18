[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if list??]
	[#list list as item]
		<li>
			<a href="${base}/topic/review/${(item.cid)?c}/${item.oid}" title="${item.title}"  target="_blank">
				${item.title}
			</a>
			<i class="minutes">[@p.mytime datetime="${item.createtimeStr}"/]</i>
			<p>[@p.TrimSubstring content="${item.content}" mylength=60 /]</p>
			<span class="topicnews"><img src="${baseimg}/imgs/default/dthumb.png" alt="">${item.prizecount}</span>
		</li>
	[/#list]
[/#if]

