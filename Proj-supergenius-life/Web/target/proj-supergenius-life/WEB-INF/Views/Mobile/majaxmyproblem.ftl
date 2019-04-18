[#ftl]
[#list list as item]
	<li>
		<a href="${base}/m/problem/detail/${(item.cid)?c}/${(item.oid)?c}" class="articleTitle"  target="_blank">${item.title}</a>
		<p class="descripe">[@p.TrimSubstring content="${item.content}" mylength=180 /]</p>
		<div class="pubTime">
			[@p.mytime datetime="${item.createtimeStr}"/]
		</div>
	</li>
[/#list]