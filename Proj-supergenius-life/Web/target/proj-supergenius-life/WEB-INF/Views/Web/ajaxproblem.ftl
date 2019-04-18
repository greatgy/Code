[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#-- [#if problemList??]
	[#list problemList as item]
		<li>
			<a href="${base}/problem/detail/${(item.cid)?c}/${(item.oid)?c}" class="articleTitle">
				${item.title}
			</a>
			<p class="descripe">
				[#if item.content==""]
				[#else]
					[@p.TrimSubstring content="${item.content}" mylength=50 /]
				[/#if]
			</p>
			<div class="timepiece">
				<span class="time">
					[@p.mytime datetime="${item.createtimeStr}"/]
				</span>
				<div class="operateBox">
					<a href="${base}/problem/detail/${(item.cid)?c}/${(item.oid)?c}" class="littleicon">
						<img src="${baseimg}/imgs/default/clickcount.png">
						${item.clickcount}
					</a>
					<a href="${base}/problem/detail/${(item.cid)?c}/${(item.oid)?c}" class="littleicon">
						<img src="${baseimg}/imgs/default/heart.png">
						${item.commentscount}
					</a>
				</div>
			</div>
		</li>
	[/#list]
[/#if]
 --]
[#if problemList??]
[#list problemList as item]
	[#if item.user??]
		[#assign fromMan=item.user/]
	[#elseif item.visitor??]
		[#assign fromMan=item.visitor/]
	[/#if]
	<li>
		[#if item.user??]
			<img src="${fromMan.useravatar}" [#if fromMan.getIsUser()]class="memberUserimg"[/#if] alt="${fromMan.username}" title="${fromMan.username}" />
			[#if fromMan.getIsUser()]
				<img src="${baseimg}/imgs/default/prince.png" class="princeimg12" alt="" />
			[/#if]
		[#else]
			<img src="${userimg}${fromMan.avatar}"/>		
		[/#if]
		[#if item.ismember==1]
			[#if me??]
        		<a [#if me.getIsUser()]href="${base}/videoDetail/${cid}/${item.uid}" class="quizRt"[#else]href="javascript:;" class="quizRt normalUsertip"[/#if] >
        	[#else]
        		<a href="javascript:;" class="quizRt pop-up" >
        	[/#if]
		[#else]
			[#if me??]
        		<a href="${base}/videoDetail/${cid}/${item.uid}" class="quizRt">
        	[#else]
        		<a href="javascript:;" class="quizRt pop-up" >
        	[/#if]
		[/#if]
		<div class="col-xs-8 col-sm-9">
			<h4>${item.title}[#if ((item.cid)?c == 1073741824||(item.cid)?c == 16384) && item.state==2]<span class="answerStatus">专家已解答</span>[/#if]</h4>
			<p>
			[#if item.content==""]
			[#else]
				[@p.TrimSubstring content="${item.content}" mylength=50 /]
			[/#if]
			</p>
		</div>
		<div class="col-xs-4 col-sm-3">
			<div class="readitem col-sm-6 col-xs-6">
				阅读
				<span class="readNum">${item.clickcount}</span>	
			</div>
			<div class="readitem col-sm-6 col-xs-6">
				回答
				<span class="readNum">${item.commentscount}</span>	
			</div>
		</div>
		</a>
	</li>
[/#list]
[/#if]