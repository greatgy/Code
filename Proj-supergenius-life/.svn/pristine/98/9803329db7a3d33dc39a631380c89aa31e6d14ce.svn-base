[#ftl]
[#if list??]
	[#list list as item]
		<li>
			<a [#if item.examine==1]href="${base}/topic/${(item.cid)?c}/${item.oid}"[#else]href="${base}/topic/review/${(item.cid)?c}/${item.oid}"[/#if] title="${item.title}">
				${item.title}
			</a>
			<span class="topicnews"><img src="${baseimg}/imgs/default/dthumb.png" alt="">${item.prizecount}</span>
		</li>
	[/#list]
[/#if]

