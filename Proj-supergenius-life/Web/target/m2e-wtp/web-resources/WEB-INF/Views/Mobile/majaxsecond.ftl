[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if secondList?size>0]
	[#list secondList as item2]
		[#if item2.fromUser??]
			[#assign fromMan2=item2.fromUser/]
		[#elseif item2.fromVisitor??]
			[#assign fromMan2=item2.fromVisitor/]
		[/#if]
		
		[#if item2.fromUser??]
			[#assign fromUserName2=fromMan2.username/]
		[#else]
			[#assign fromUserName2=fromMan2.nickname/]
		[/#if]
		<li class="ment" data-topuid="${firstuid}" data-uid="${item2.uid}" data-touseruid="${item2.fromuseruid}"  data-tousername="[@p.PrivateString content="${fromUserName2}" /]"  data-useruid="${item2.useruid}">
            <div class="userimg">
				[#if item2.fromUser??]
					<a href="javascript:;">
						<img [#if "${fromMan2.useravatar}" == ""]src="${baseimg}/imgs/default/user.png"[#else]src="${fromMan2.useravatar}"[/#if] [#if fromMan2.getIsUser()]class="userborder"[/#if] />
						[#if fromMan2.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg" alt="" />[/#if]
					</a>
				[#else]
					<a href="javascript:;"><img src="${userimg}${fromMan2.avatar}"/></a>					
				[/#if]
			</div>
			<div class="commentRt">
				<div class="name">
					[@p.PrivateString content="${fromUserName2}" /]
					<span class="commentTime">${(item2.createtime).toString("M月dd日  HH:mm")}</span>
					<a id="deletecomment_${item2.uid}_${useruid}" [#if useruid == item2.useruid || useruid == item2.fromuseruid]class="delete"[#else]class="hd"[/#if]><img src="${baseimg}/imgs/default/crush.png"/></a>
				</div>
				<p class="comment">
					<span>@[@p.PrivateString content="${item2.tousername}"/]:</span>
					${item2.content!''}
				</p>
				<div class="operate">
					[#if me??]
						<a href="javascript:;" data-reply="replyopen" class="reply" onclick="reply(this)" >回复</a>
					[#else]
						<a href="${base}/m/login?cid=${cid?c}&pcid=${pcid}" class="reply">回复</a>
					[/#if]
					<a class="zan" id="btnprize_commentspraise_${(me.oid?c)!"0"}_${cid?c}_${item2.uid}" data-counter=".prizecounter"><img src="${baseimg}/imgs/default/zan.png" data-isprize="${item2.isprize!'false'}"><span class="zancountjs">${item2.prizecount!'0'}</span></a>
				</div>
			</div>
		</li>
	[/#list]
[/#if]