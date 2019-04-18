[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#list list as item]
	<li>
		<a href="${base}/m/topic/${(item.cid)?c}/${(item.oid)?c}" class="articleTitle" target="_blank">
			${item.title}
		</a>
		<p class="descripe">[@p.TrimSubstring content="${item.content}" mylength=180 /]</p>
	</li>
[/#list]