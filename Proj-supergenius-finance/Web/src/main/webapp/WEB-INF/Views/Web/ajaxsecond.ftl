[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if secondList?size>0]
	[#list secondList as item2]
		[#if item2.fromUser??]
			[#assign fromMan2=item2.fromUser/]
		[#elseif item2.fromVisitor??]
			[#assign fromMan2=item2.fromVisitor/]
		[/#if]
		<li class="ment" data-topuid="${firstuid}" data-uid="${item2.uid}" data-touseruid="${item2.fromuseruid}"  data-tousername="${item2.fromusername}"  data-useruid="${item2.useruid}">
            <div class="userimg">
				[#if item2.fromUser??]
					<a href="${base}/finance/uhome/${fromMan2.uid}">
						<img [#if "${fromMan2.useravatar}" == ""]src="${baseimg}/imgs/default/user.png"[#else]src="${fromMan2.useravatar}"[/#if] [#if fromMan2.getIsUser()]class="userborder"[/#if] />
						[#if fromMan2.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg" alt="" />[/#if]
					</a>
				[#else]
					<img src="${userimg}${fromMan2.avatar}"/>					
				[/#if]
			</div>
			<div class="commentRt">
				<div class="name">
	                [#if item2.fromUser??]
						<a href="${base}/finance/uhome/${fromMan2.uid}">[@p.PrivateString content="${fromMan2.username}" /]</a>
					[#else]
						${fromMan2.nickname}
					[/#if]
					<span class="commentTime">${(item2.createtime).toString("M月dd日  HH:mm:ss")}</span>
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
						[#if phone??]
							<a href="${base}/phonelogin" class="reply">回复</a>
						[#else]
							<a href="javascript:;" class="reply pop-up">回复</a>
						[/#if]
					[/#if]
					<a class="zan" id="btnprize_comments_${(me.oid?c)!"0"}_${cid}_${item2.uid}" data-counter=".prizecounter"><img src="${baseimg}/imgs/default/zan.png" data-isprize="${item2.isprize!'false'}"><span class="zancountjs">${item2.prizecount!'0'}</span></a>
				</div>
			</div>
		</li>
	[/#list]
[/#if]
