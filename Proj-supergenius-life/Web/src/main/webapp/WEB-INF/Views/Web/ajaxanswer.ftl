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
		<li class="commentitem">
			<div class="qsUserimg">
				[#if item.fromUser??]
					<img [#if fromMan.getIsUser()]class="userborder"[/#if][#if "${fromMan.useravatar}" == ""]src="${baseimg}/imgs/default/user.png"[#else]src="${fromMan.useravatar}"[/#if] />
					[#if fromMan.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="problemprinceimg" alt="" />[/#if]
				[#else]
					<img src="${fromMan.useravatar}" />		
				[/#if]
			</div>
			<div class="commentitemRt replay_comment" data-uid="${item.uid}" data-touseruid="${item.fromuseruid}"  data-tousername="[@p.PrivateString content="${fromUserName}" /]"  data-useruid="${item.useruid}">
				<div class="qsUsername">
					[@p.PrivateString content="${fromUserName}" /]
					<a id="deletecomment_${item.uid}_${useruid}" [#if useruid == item.useruid || useruid == item.fromuseruid]class="delete"[#else]class="hd"[/#if]><img src="${baseimg}/imgs/default/crush.png"/></a>
				</div>
				<div class="qsword">${item.content}</div>
				<div class="wordBottom">
					<span class="qstime">${(item.createtime).toString("M月dd日  HH:mm")}</span>
					<div class="qsCommentOperate">
						<a class="zan" id="btnprizeproblem_answerpraise_${(me.oid?c)!"0"}_${cid?c}_${item.uid}" data-counter=".prizecounter"><img src="${baseimg}/imgs/default/heart.png" data-isprize="${item.isprize!'false'}"><span class="zancountjs">${item.prizecount!'0'}</span></a>
						[#if me??]
							<a data-reply="replyopen" class="reply" onclick="reply(this)"><img src="${baseimg}/imgs/default/discuss.png"><span id="commentcount">${item.commentcount}</span></a>
						[#else]
							[#if phone??]
								<a data-reply="replyopen" class="reply" href="${base}/phonelogin"><img src="${baseimg}/imgs/default/discuss.png"><span id="commentcount">${item.commentcount}</span></a>
							[#else]
								<a data-reply="replyopen" class="reply pop-up" href="javascript:;"><img src="${baseimg}/imgs/default/discuss.png"><span id="commentcount">${item.commentcount}</span></a>
							[/#if]
						[/#if]
						[#if item.commentcount >0]<a class="leftcomments" onclick="showcomment(this);" data-reply="true">展开<img src="${baseimg}/imgs/default/arrawDown.png" alt="展开" /></a>[#else]<a class="leftcomments hd" onclick="showcomment(this);" data-reply="true">展开<img src="${baseimg}/imgs/default/arrawDown.png" alt="展开" /></a>[/#if]
					</div>   
				</div>
			</div>	
			<!--以下是二级评论部分-->
			<ul id="second" class="secondComment hd" ></ul>
			<div class="loadmorefinance hd">
				<a href="javascript:void(0)" id="loadmore" onclick="load(this)">加载更多</a>
			</div>
		</li>
	[/#list]
[/#if]