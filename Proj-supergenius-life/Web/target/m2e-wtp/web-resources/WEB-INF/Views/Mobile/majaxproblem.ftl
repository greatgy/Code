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
		[#if item.fromUser??]
			<img src="${fromMan.useravatar}" [#if fromMan.getIsUser()]class="memberUserimg"[/#if] alt="${fromMan.username}" title="${fromMan.username}" />
			[#if fromMan.getIsUser()]
				<img src="${baseimg}/imgs/default/prince.png" class="princeimg12" alt="" />
			[/#if]
		[#else]
			<img src="${userimg}${fromMan.avatar}"/>	
		[/#if]
		[#if item.ismember==1]
			[#if me??]
        		<a [#if me.getIsUser()]href="${base}/m/problem/detail/${(item.cid)?c}/${(item.oid)?c}" class="quizRt"[#else]href="javascript:;" class="quizRt normalUsertip"[/#if] >
        	[#else]
        		<a href="${base}/m/login?cid=${item.cid?c}&pcid=${pcid}" class="quizRt">
        	[/#if]
		[#else]
			[#if me??]
        		<a href="${base}/m/problem/detail/${(item.cid)?c}/${(item.oid)?c}" class="quizRt">
        	[#else]
        		<a href="${base}/m/login?cid=${item.cid?c}&pcid=${pcid}" class="quizRt">
        	[/#if]
		[/#if]
		<div class="col-sm-9">
			<h4>
				[#if article.istop == 1]
					<span class="upTop">【置顶】</span>
				[/#if]
				${item.title}[#if ((item.cid)?c == 1073741824||(item.cid)?c == 16384) && item.state==2]<span class="answerStatus">专家已解答</span>[/#if]
			</h4>
			<p>
			[#if item.content==""]
			[#else]
				[@p.TrimSubstring content="${item.content}" mylength=50 /]
			[/#if]
			</p>
		</div>
		<div class="col-sm-3">
			<div class="readitem">
				阅读
				<span class="readNum">${item.clickcount}</span>	
			</div>
			<div class="readitem">
				回答
				<span class="readNum">${item.commentscount}</span>	
			</div>
		</div>
		</a>
	</li>
[/#list]
[/#if]