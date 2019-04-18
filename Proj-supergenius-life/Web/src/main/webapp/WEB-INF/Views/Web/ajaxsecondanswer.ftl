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
		<li class="ment">
            <div class="qsUserimg">
				[#if item2.fromUser??]
					<img [#if fromMan2.getIsUser()]class="userborder"[/#if][#if "${fromMan2.useravatar}" == ""]src="${baseimg}/imgs/default/user.png"[#else]src="${fromMan2.useravatar}"[/#if] />
					[#if fromMan2.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="problemprinceimg" alt="" />[/#if]
				[#else]
					<img src="${fromMan2.useravatar}" />					
				[/#if]
			</div>
			<div class="commentitemRt" data-topuid="${firstuid}" data-uid="${item2.uid}" data-touseruid="${item2.fromuseruid}"  data-tousername="[@p.PrivateString content="${fromUserName2}" /]" data-useruid="${item2.useruid}">
				<div class="qsUsername">
               		[@p.PrivateString content="${fromUserName2}" /]
					<a id="deletecomment_${item2.uid}_${useruid}" [#if useruid == item2.useruid || useruid == item2.fromuseruid]class="delete"[#else]class="hd"[/#if]><img src="${baseimg}/imgs/default/crush.png"/></a>
				</div>
				<div class="qsword">
					<span>@[@p.PrivateString content="${item2.tousername}" /]:</span>
					${item2.content!''}
				</div>
				<div class="wordBottom">
					<span class="qstime">${(item2.createtime).toString("M月dd日  HH:mm")}</span>
					<div class="qsCommentOperate">
						<a class="zan" id="btnprizeproblem_answerpraise_${(me.oid?c)!"0"}_${cid?c}_${item2.uid}" data-counter=".prizecounter"><img src="${base}/imgs/default/heart.png" data-isprize="${item2.isprize!'false'}"><span class="zancountjs">${item2.prizecount!'0'}</span></a>
						[#if me??]
							<a href="javascript:;" data-reply="replyopen" class="reply" onclick="reply(this)" >回复</a>
						[#else]
							<a href="javascript:;" class="reply pop-up">回复</a>
						[/#if]
					</div>
				</div>
			</div>
		</li>
	[/#list]
[/#if]
