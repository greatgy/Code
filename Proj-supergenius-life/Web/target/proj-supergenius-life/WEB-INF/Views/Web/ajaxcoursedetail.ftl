[#ftl]
[#if course??]
	<img src="${webimg}${course.imgmedium}" alt="${course.name}" title="${course.name}" />
			<div class="bookRight">
				<p>出版方：${course.press}</p>
				<p>适用年级：[#if course.grade == 1]六年级[#elseif course.grade == 2]初一[#elseif course.grade == 4]初二[#elseif course.grade == 8]初三[/#if]</p>
			</div>
	<a href="javascript:;" class="closeBtn"><img src="${baseimg}/imgs/default/close.png" alt="关闭" title="关闭" /></a>
[/#if]