[#ftl]
[#if list??]
	[#assign i = 1]
	[#list list as courtse]
		<a href="javascript:;" data-uid="${courtse.uid}" class="courseDetail">
			<img src="${webimg}${courtse.imgmedium}" alt="${courtse.name}" title="${courtse.name}" />
			<p>${courtse.name}</p>
		</a>	
		[#if i == 6]
			<a href="${base}/course/morecourse?sid=${courtse.sid}&grade=${courtse.grade}" class="moreCourse">&gt;</a>
		[/#if]
		[#assign i = i + 1]
	[/#list]
	
[/#if]