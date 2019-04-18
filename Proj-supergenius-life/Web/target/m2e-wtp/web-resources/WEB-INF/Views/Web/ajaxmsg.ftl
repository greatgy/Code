[#ftl]
[#if msg??]
	[#list msg as item]
		<ul class="newslist">
			<li>
				[#if item.isread == false]
				<i>NEW</i>
				[/#if]
				[#if item.type ==1]
					<span class="newstip">${item.fromusername}赞了你的文章</span>
				[#elseif item.type ==2]
					<span class="newstip">${item.fromusername}评论了你的文章</span>
				[#elseif item.type ==3]
					<span class="newstip">${item.fromusername}赞了你的评论</span>
				[#elseif item.type ==4]
					<span class="newstip">${item.fromusername}回复了你的评论</span>
				[#elseif item.type ==5]
					<span class="newstip">${item.fromusername}赞了你的话题</span>
				[#elseif item.type ==6]
					<span class="newstip">${item.fromusername}评论了你的话题</span>
				[#elseif item.type ==7]
					<span class="newstip">专家点评了你的视频</span>
				[#elseif item.type ==8]
					<span class="newstip">专家点评了你的问题</span>
				[#elseif item.type ==9]
					<span class="newstip">您的举报系统已处理</span>
				[/#if]
				<a href="javascript:;" class="form readNews" data-href="${item.href}" data-uid="${item.uid}">${item.content}</a>
				<a href="javascript:;" class="delete" data-uid="${item.uid}"><img src="${baseimg}/imgs/default/crush.png" alt="删除" title="删除" /></a>
			</li>
		</ul>
	[/#list]
[/#if]