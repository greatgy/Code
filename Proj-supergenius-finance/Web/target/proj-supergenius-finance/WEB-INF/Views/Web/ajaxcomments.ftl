[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if list?size>0]
	[#list list as item]
		[#if item.fromUser??]
			[#assign fromMan=item.fromUser/]
		[#elseif item.fromVisitor??]
			[#assign fromMan=item.fromVisitor/]
		[/#if]
		<div class="commentitem">
			<div class="userimg">
				[#if item.fromUser??]
					<a href="${base}/finance/uhome/${fromMan.uid}">
						<img [#if "${fromMan.useravatar}" == ""] src="${baseimg}/imgs/default/user.png"[#else]src="${fromMan.useravatar}"[/#if] [#if fromMan.getIsUser()]class="userborder"[/#if] />
						[#if fromMan.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg" alt="" />[/#if]
					</a>
				[#else]
					<img src="${userimg}${fromMan.avatar}" />		
				[/#if]
			</div>
			<div class="commentRt replay_comment ment" data-uid="${item.uid}" data-touseruid="${item.fromuseruid}"  data-tousername="${item.fromusername}"  data-useruid="${item.useruid}">
				<div class="name">
					[#if item.fromUser??]
						<a href="${base}/finance/uhome/${fromMan.uid}">[@p.PrivateString content="${fromMan.username}" /]</a>
					[#else]
						${fromMan.nickname}
					[/#if]
					<span class="commentTime">${(item.createtime).toString("M月dd日  HH:mm:ss")}</span>
					<a id="deletecomment_${item.uid}_${useruid}" [#if useruid == item.useruid || useruid == item.fromuseruid]class="delete"[#else]class="hd"[/#if]><img src="${baseimg}/imgs/default/crush.png"/></a>
				</div>
				<p class="comment">${item.content}</p>
				<div class="commentBottom">
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
						[#if item.commentcount >0]<a class="leftcomments" data-reply="true" onclick="showcomment(this);">&nbsp;|&nbsp;<span class="commentcount">${item.commentcount!'0'}</span>条回复<img src="${baseimg}/imgs/default/arrawDown.png" alt="展开" /></a>[#else]<a class="leftcomments hd" data-reply="true" onclick="showcomment(this);">&nbsp;|&nbsp;<span class="commentcount">${item.commentcount!'0'}</span>条回复<img src="${baseimg}/imgs/default/arrawDown.png" alt="展开" /></a>[/#if]
						<a class="zan" id="btnprize_comments_${(me.oid?c)!"0"}_${cid}_${item.uid}" data-counter=".prizecounter"><img src="${baseimg}/imgs/default/zan.png" data-isprize="${item.isprize!'false'}"><span class="zancountjs">${item.prizecount!'0'}</span></a>
					</div>
					<!--以下是二级评论部分-->
					<ul class="secondComment hd" ></ul>
					<div class="loadmorefinance hd">
						<a href="javascript:void(0)" id="loadmore" onclick="load(this)">加载更多</a>
					</div>
				</div>	
			</div>	
		</div>
	[/#list]
[/#if]