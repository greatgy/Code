[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if list??]
	[#list list as item]
		<li>
			<div class="topicRt">
				<a href="${base}/topic/review/${(item.cid)?c}/${item.oid}" title="${item.title}">
					${item.title}
				</a>
				<p>[@p.TrimSubstring content="${item.content}" mylength=60 /]</p>
				<div class="topicbottom">
					<span class="time">[@p.mytime datetime="${item.createtimeStr}"/]</span>
					<span class="topicnews"><img src="${baseimg}/imgs/default/dthumb.png" alt="">${item.prizecount}</span>
				</div>
			</div>
		</li>
	[/#list]
[/#if]

