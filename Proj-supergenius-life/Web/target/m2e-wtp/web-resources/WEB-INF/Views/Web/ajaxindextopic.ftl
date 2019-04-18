[#ftl]
[#if list??]
	[#list list as topic]
		<li>
			<a href="${base}/topic/${(topic.cid)?c}/${(topic.oid)?c}" title="${topic.title}">
				<img src="${webimg}/${topic.imgmedium}" alt="${topic.title}" title="${topic.title}">
				<div class="description">
					${topic.title}
				</div>
			</a>
		</li>
	[/#list]
[/#if]

