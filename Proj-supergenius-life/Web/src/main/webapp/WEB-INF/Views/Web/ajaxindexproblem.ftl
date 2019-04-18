[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if list??]
	[#list list as item]
		<li>
			<a href="${base}/problem/detail/${(item.cid)?c}/${(item.oid)?c}">
				<span>${item.title}</span>
				<p>[@p.TrimSubstring content="${item.content}" mylength=200 /]</p>
			</a>
		</li>
	[/#list]
[/#if]

