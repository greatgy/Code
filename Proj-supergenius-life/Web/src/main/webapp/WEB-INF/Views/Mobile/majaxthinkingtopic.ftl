[#ftl]
[#if list??]
	[#list list as item]
		<li>
			<a href="${base}/topic/${(item.cid)?c}/${item.oid}" title="${item.title}">
				${item.title}
			</a>
			<span class="topicnews"><img src="${baseimg}/imgs/default/topicnews.png" alt="">${item.prizecount}</span>
		</li>
	[/#list]	
[/#if]