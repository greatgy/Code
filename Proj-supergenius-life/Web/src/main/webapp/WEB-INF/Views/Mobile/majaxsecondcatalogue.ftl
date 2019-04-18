[#ftl]
[#if list??]
	[#list list as item]
		<li [#if cid?? && cid?c == (item.cid)?c]class="curselected"[/#if]><a href="${base}/m/catalogue/${item.pcid}/${(item.cid)?c}">${item.name}</a></li>
	[/#list]
[/#if]