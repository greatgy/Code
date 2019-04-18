[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if list?size>0]
	[#list list as item]
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
		<div class="commentitem">
			<div class="userimg">
				[#if item.fromUser??]
					<a href="javascript:;">
						<img [#if "${fromMan.useravatar}" == ""] src="${baseimg}/imgs/default/user.png"[#else]src="${fromMan.useravatar}"[/#if] [#if fromMan.getIsUser()]class="userborder"[/#if] />
						[#if fromMan.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg"/>[/#if]
					</a>
				[#else]
					<a href="javascript:;"><img src="${userimg}${fromMan.avatar}" /></a>		
				[/#if]
			</div>
			<div class="commentRt replay_comment ment" data-uid="${item.uid}" data-touseruid="${item.fromuseruid}"  data-tousername="[@p.PrivateString content="${fromUserName}" /]"  data-useruid="${item.useruid}">
				<div class="name">
					[@p.PrivateString content="${fromUserName}" /]
					<span class="commentTime">${(item.createtime).toString("M月dd日")}</span>
					<a id="deletecomment_${item.uid}_${useruid}" [#if useruid == item.useruid || useruid == item.fromuseruid]class="delete"[#else]class="hd"[/#if]><img src="${baseimg}/imgs/default/crush.png"/></a>
				</div>
				<p class="comment">${item.content}</p>
				<div class="commentBottom">
					<div class="operate">
						[#if me??]
							<a href="javascript:;" data-reply="replyopen" class="reply" onclick="reply(this)" >回复</a>
						[#else]
							<a href="${base}/m/login?cid=${cid?c}&pcid=${pcid}" class="reply">回复</a>
						[/#if]
						[#if item.commentcount >0]<a class="leftcomments" data-reply="true" onclick="showcomment(this);">&nbsp;|&nbsp;<span class="commentcount">${item.commentcount!'0'}</span>条回复<img src="${baseimg}/imgs/default/arrawDown.png" alt="展开" /></a>[#else]<a class="leftcomments hd" data-reply="true" onclick="showcomment(this);">&nbsp;|&nbsp;<span class="commentcount">${item.commentcount!'0'}</span>条回复<img src="${baseimg}/imgs/default/arrawDown.png" alt="展开" /></a>[/#if]
						<a class="zan" id="btnprize_commentspraise_${(me.oid?c)!"0"}_${cid?c}_${item.uid}" data-counter=".prizecounter"><img src="${baseimg}/imgs/default/zan.png" data-isprize="${item.isprize!'false'}"><span class="zancountjs">${item.prizecount!'0'}</span></a>
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
