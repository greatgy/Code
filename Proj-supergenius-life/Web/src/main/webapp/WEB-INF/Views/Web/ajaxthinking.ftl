[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<div class="topicDetailBox">
	<img src="${webimg}/${bean.imgbig}" alt="${bean.title}" title="${bean.title}" />
	<h3>
		<span>${bean.title}</span>
	</h3>
	${bean.content}
	<div class="topictips">
		<span>${bean.author}</span>
		<span>${(bean.createtime).toString("yyyy年MM月dd日 ")}</span>
		<span class="readTimes">阅读&nbsp;&nbsp;${bean.clickcount!'0'}</span>
	</div>
</div>
<div class="shareBox">
	<a class="littleicon" href="javascript:;" id="btnprize_topicpraise_${(me.oid?c)!"0"}_${(bean.cid)?c}_${bean.uid}" data-counter=".prizecounter">
		<img src="${baseimg}/imgs/default/heart.png" data-notcomment="true" data-isprize="${bean.isprize!'false'}"/>
		<span class="zancountjs">${bean.prizecount!"0"}</span>
	</a>
	<a class="littleicon" href="javascript:;" onclick="wantComment();">
		<img src="${baseimg}/imgs/default/discuss.png" />
		<span id="articlecommentcount">${bean.commentscount!"0"}</span>
	</a>
	<a href="javascript:;" [#if me??]id="btncollecttopic_${bean.uid}" data-iscollect="${bean.iscollect!'false'}" class="littleicon"[#else]class="littleicon pop-up" [/#if]>
		<img src="${baseimg}/imgs/default/collect.png" data-iscollect="${bean.iscollect!'false'}" class="liwaysimg02"/>
		<span id="articlecollectcount">${bean.collectcount!"0"}</span>
	</a>
	<div class="share">
		分享:
		<div class="bdsharebuttonbox bdshare-button-style0-16" data-bd-bind="1503546374732">
			<a href="#" class="bds_more" data-cmd="more"></a>
			<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
			<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
			<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
		</div>
	</div>
</div>
<form class="wordbox topicWordbox">
	<textarea id="content" class="comment_textarea"></textarea>
	<div class="wordpiece">
		<a href="#" class="face"><img src="${baseimg}/imgs/default/smile.png" alt="" /></a>
		[#if me??]
			<button class="submitBtn firstedcomments" onclick="submitCommentHandler(this);">发表</button>
			<span class="hidename"><input type="checkbox" id="isnick" /><label for="isnick">匿名</label></span>
		[#else]
			<button class="pop-up submitBtn firstedcomments" onclick="submitCommentHandler(this);">发表</button>
			<span class="hidename"><input type="checkbox" id="isnick" /><label for="isnick">匿名</label></span>
		[/#if]
	</div>
</form>
<input type="hidden" name="thinkingfromuid" value="${bean.uid}"/>
<div class="commentBox">
	<h3 class="allComments">
		全部评论
	</h3>
	<ul class="commentList conCommentlist" id="displayComment">
	</ul>
</div>
