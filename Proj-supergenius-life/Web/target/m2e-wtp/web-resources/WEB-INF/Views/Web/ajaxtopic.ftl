[#ftl]
[#if list??]
	[#list list as item]
		<li>
			<a href="${base}/topic/${(item.cid)?c}/${item.oid}"  target="_blank">
				<img class="talentimg" src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}" />
				<span class="topicTitle" title="${item.title}" alt="${item.title}">${item.title}</span>
			</a>
			<div class="topicsDetail">
				${item.author}                                  
				<span class="time">${(item.createtime).toString("yyyy年MM月dd日 ")}</span>
			</div>
		</li>
	[/#list]
[/#if]
