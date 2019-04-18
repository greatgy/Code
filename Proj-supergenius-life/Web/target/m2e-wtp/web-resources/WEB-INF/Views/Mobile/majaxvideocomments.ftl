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
		<li>
			<div class="userimg">
				[#if item.fromUser??]
				<img [#if "${fromMan.useravatar}" == ""] src="${baseimg}/imgs/default/user.png"[#else]src="${fromMan.useravatar}"[/#if] [#if fromMan.getIsUser()]class="memberUserimg userborder"[/#if] />
				[#if fromMan.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg"/>[/#if]
				[#else]
					<img src="${userimg}${fromMan.avatar}" />		
				[/#if]
			</div>
			<div class="commentitemRt replay_comment ment"  data-uid="${item.uid}" data-touseruid="${item.fromuseruid}"  data-tousername="[@p.PrivateString content="${fromUserName}" /]"  data-useruid="${item.useruid}">
				<p class="username">[@p.PrivateString content="${fromUserName}" /]
				<a id="deletecomment_${item.uid}_${useruid}" [#if useruid == item.useruid || useruid == item.fromuseruid]class="delete"[#else]class="hd"[/#if]><img src="${baseimg}/imgs/default/crush.png"/></a>
				</p>
				<div class="word">
					${item.content}
					<div class="pictureBox">
						[#if item.imgList??  && item.imgList?size>0]
							[#list item.imgList as img]
								<a href="${webimg}${img}" data-lightbox="example-set"><img alt="" src="${webimg}${img}" /></a>
							[/#list]
						[/#if]
					</div>
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
						[#if item.commentcount >0]<a class="leftcomments openReply" data-reply="true" onclick="showcomment(this);">&nbsp;|&nbsp;<span id="commentcount">${item.commentcount!'0'}</span>条回复<img src="${baseimg}/imgs/default/arrawDown.png" alt="展开" /></a>[#else]<a class="leftcomments hd" data-reply="true" onclick="showcomment(this);">&nbsp;|&nbsp;<span id="commentcount">${item.commentcount!'0'}</span>条回复<img src="${baseimg}/imgs/default/arrawDown.png" alt="展开" /></a>[/#if]
					</div>
				</div>
				<ul id="second" class="replyList">
				</ul>
				<div class="loadmorefinance hd">
					<a href="javascript:void(0)" id="loadmore" onclick="load(this)">加载更多</a>
				</div>
			</div>
		</li>
	[/#list]
[/#if]