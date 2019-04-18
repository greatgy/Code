[#ftl]
<div class="firstTopnav">
    <a href="${base}" class="logo">
        <img src="${baseimg}/imgs/default/logo.png" alt="天才人生" title="天才人生首页" />
    </a>
    <div class="searchMiddle">
        <input type="text" placeholder="请输入搜索内容" id="keywordsearch" name="keyword"/>
        <a class="searchimg">
            <img src="${baseimg}/imgs/default/search.png" alt="搜索" title="搜索" />
        </a>
    </div>
    <div class="loginbox">
    	[#if me??]
    		<a [#if !me.getIsUser()] class="votebtn normalUsertip" [#else] class="votebtn" [#if cid??]href="${base}/my/member/contribute?cid=${cid?c}"[#else]href="${base}/my/member/contribute"[/#if] [/#if]>投稿</a>
		[#else]
        	<a class="votebtn pop-up" href="javascript:;">投稿</a>
    	[/#if]
    	<div class="logined">
        [#if me??]
			<a href="${base}/my/center/article" class="logined"><img [#if me.useravatar??]src="${me.useravatar}"[#else]src="${baseimg}/imgs/default/defaultimg.png"[/#if] [#if me.getIsUser()]class="userborder"[/#if]/></a>
			[#if me.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg" alt="" />[/#if]
		[#else]
    		<a class="logined pop-up" href="javascript:;"><img src="${baseimg}/imgs/default/defaultimg.png"/></a>
    	[/#if]
    	<div class="userselfbox">
			<div class="trangle"></div>
        	<div class="userselfcontent">
        		<ul  class="userights">
        			<li>
        				<a [#if me??] href="${base}/my/center/article" [#else] href="javascript:;" class="pop-up" [/#if]>我的文章</a>
        			</li>
        			<li>
        				<a [#if me??] href="${base}/my/center/question" [#else] href="javascript:;" class="pop-up" [/#if]>我的提问</a>
        			</li>
        			<li>
        				<a [#if me??] href="${base}/my/center/video" [#else] href="javascript:;" class="pop-up" [/#if]>我的视频</a>
        			</li>
        			<li>
        				<a [#if me??] href="${base}/my/center/topic" [#else] href="javascript:;" class="pop-up" [/#if]>我的话题</a>
        			</li>
        			<li>
        				<a [#if me??] href="${base}/my/center/msg" [#else] href="javascript:;" class="pop-up" [/#if] id="message">消息通知</a>
        			</li>
        			<li>
        				<a [#if me??] href="${base}/my/center/comment" [#else] href="javascript:;" class="pop-up" [/#if]>我的评论</a>
        			</li>
        			<li>
        				<a  [#if me??]href="${base}/my/center/collect" [#else] href="javascript:;" class="pop-up" [/#if] data-channel="collect">我的收藏</a>
        			</li>
        			<li>
        				<a [#if me??] href="${base}/my/center/info" [#else] href="javascript:;" class="pop-up" [/#if] >个人资料</a>
        			</li>
        		</ul>
        	</div>
    	</div>
    	</div>
    </div>
</div>
<div class="topnav">
    <ul class="navlist">
        [#if cataloguelist??]
        	[#list cataloguelist as map]
        		<li class="catalogue_${(map["first"].cid)?c} [#if pcid?? && pcid?c == (map["first"].cid)?c || cid?? && cid?c == (map["first"].cid)?c]current[/#if]">
		            [#if map["second"]?? && map["second"]?size>0]
			            <span class="labeltitle">
		                	${map["first"].name}
		                	[#if map["first"].cid == 8]
		                		<i class="classType">初中</i>
		                	[#elseif map["first"].cid == 16]
		                		<i class="classType">高中</i>
		                	[#elseif map["first"].cid == 32]
		                		<i class="classType">大学</i>
				            [/#if]
			            </span>
		            	<ul class="rollnav">
			            	[#assign secondlist = map["second"]]
			            	[#list secondlist as item]
			            		<li [#if cid?? && cid?c == (item.cid)?c]class="current"[/#if]><a href="${base}/catalogue/${(item.pcid)?c}/${(item.cid)?c}">${item.name}</a></li>
			            	[/#list]
		            	</ul>
		            [#else]
		            	<a class="aa" href="${base}/catalogue/${map["first"].pcid}/${(map["first"].cid)?c}">${map["first"].name}</a>
		            [/#if]
		        </li>
        	[/#list]
        [/#if]
    </ul>
</div>

<script type="text/javascript">
    $(function () {
    	var cla = 0;
    	$("li[class^='catalogue_']").each(function(){ 
    		var tem = $(this).attr("class");
			if($(this).hasClass("current")){
				cla=1;
			}
		});
		if(cla==0){
			$(".catalogue_10").addClass("current");
		}
		// 消息数量
		[#if me??]
			$.get("${base}/ajax/my/unreads", function(data){
    			if(data != 0){
                    $(".userselfbox").before("<span class='reddot'></span>");
                    $("#message").append("<span>"+data+"</span>");
    			}
            });
        [/#if]
		//绑定查找事件
        $("#keywordsearch").bind("keydown", searchHandle);
        $(".searchimg").click(function () {
        	if ($("#keywordsearch").val().trim() == "") {
				document.location = '${SolrSearchUrl}/index';
        	} else {
        		window.location.href = "${base}/search?keyword=" + $("#keywordsearch").val();
        	}
        });
        
        //按enter键查找
		function searchHandle(event){
			if (event.keyCode==13) {
				if ($(this).val().trim() == "") {
					shakeHandle();
					$(this).blur();
					return;
				} else {
					window.location.href = "${base}/search?keyword=" + $("#keywordsearch").val();
				}
			}
		}
		//设置搜索框内容为空时抖动提示
		function shakeHandle() {
			window.location.href = "${SolrSearchUrl}/index";
		}
    
        $(window).scroll(function () {
            var windowScroll = $(window).scrollTop();
            if(windowScroll > 52) {
                $(".header_fix").css("top", "0");
            } else {
                $(".header_fix").css("top", "52px");
            }
        });
    });
</script>