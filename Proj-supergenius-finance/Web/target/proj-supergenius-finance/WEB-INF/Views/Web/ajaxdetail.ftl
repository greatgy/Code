[#ftl]
<div class="articleBox">
	<div class="articleframe">
		<h3>${bean.title}</h3>
		<div class="detailTips">
			[#if "${bean.createtime}" != ""]
					<span class="time">发布时间:${(bean.createtime).toString("yyyy年MM月dd日 ")}</span>
				[/#if]
				[#if "${bean.origin}" != ""]
					<span class="source">来源：${bean.origin}</span>
				[/#if]
				[#if "${bean.author}" != ""]
					<span class="author">作者：${bean.author}</span>
				[/#if]
		</div>
		<div class="articleContent">
			${bean.content?substring(0,3000)}
			<span>...........................<a href="javascript:;" id="moreContent" style="color: red;">查看更多</a></span>
		</div>
	</div>
</div>