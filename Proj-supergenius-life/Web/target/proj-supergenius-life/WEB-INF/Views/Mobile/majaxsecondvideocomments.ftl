[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if secondList?size>0]
	[#list secondList as item]
		[#if item.fromUser??]
			[#assign fromMan=item.fromUser/]
		[#elseif item.fromVisitor??]
			[#assign fromMan=item.fromVisitor/]
		[/#if]
		
		[#if item.fromUser??]
			[#assign fromUserName=fromMan.username/]
		[#else]
			[#assign fromUserName=fromMan.nickname/]
		[/#if]
		<li>
			<div class="userimg">
				[#if item.fromUser??]
				<img [#if "${fromMan.useravatar}" == ""] src="${baseimg}/imgs/default/user.png"[#else]src="${fromMan.useravatar}"[/#if] [#if fromMan.getIsUser()]class="memberUserimg userborder"[/#if] />
				[#if fromMan.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg"/>[/#if]
				[#else]
					<img src="${userimg}${fromMan.avatar}" />		
				[/#if]
			</div>
			<div class="commentitemRt"  data-topuid="${firstuid}" data-uid="${item.uid}" data-touseruid="${item.fromuseruid}"  data-tousername="[@p.PrivateString content="${fromUserName}" /]"  data-useruid="${item.useruid}">
				<p class="username">[@p.PrivateString content="${fromUserName}" /]
				<a id="deletecomment_${item.uid}_${useruid}" [#if useruid == item.useruid || useruid == item.fromuseruid]class="delete"[#else]class="hd"[/#if]><img src="${baseimg}/imgs/default/crush.png"/></a>
				</p>
				<div class="word">
					<span>@[@p.PrivateString content="${item.tousername}" /]:</span>
					${item.content}
				</div>
				<div class="wordBottom">
					<span class="time">${(item.createtime).toString("M月dd日")}</span>
					<div class="commentOperate">
						<a class="zan" id="btnprize_commentspraise_${(me.oid?c)!"0"}_${cid?c}_${item.uid}" data-counter=".prizecounter"><img src="${baseimg}/imgs/default/zan.png" data-isprize="${item.isprize!'false'}"><span class="zancountjs">${item.prizecount!'0'}</span></a>
						[#if me??]
							<a href="javascript:;" data-reply="replyopen" class="openReply" onclick="reply(this)" >回复</a>
						[#else]
							<a href="${base}/m/login?cid=${cid?c}&pcid=${pcid}" class="openReply pop-up">回复</a>
						[/#if]
					</div>
				</div>
			</div>
		</li>
	[/#list]
[/#if]