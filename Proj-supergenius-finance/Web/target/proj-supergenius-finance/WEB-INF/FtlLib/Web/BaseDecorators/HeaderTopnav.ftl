[#ftl]
<link rel="stylesheet" type="text/css" href="${basecss}/css/swiper.min.css">
<script type="text/javascript">
	var base = '${base}';
	var baseimg = '${baseimg}';
	var userimg = '${userimg}';
</script>
<div class="topnav">
    <a href="${base}/index" class="logo">
        <img src="${baseimg}/imgs/default/logo.png" alt="天才职场" alt="天才职场" />
    </a>
    <div class="navlist">
        <ul class="swiper-wrapper">
	        [#if LeftCatalogue??]
		        [#list LeftCatalogue as catalogue]
					<li class="finance_catalogue swiper-slide [#if cid??][#if catalogue.cid == cid]current[/#if][/#if]" id="catalogue_${catalogue.cid}">
						<a href="${base}[#if catalogue.cid>0]/catalogue/${catalogue.cid}[/#if]" data-cid="${catalogue.cid}">${catalogue.name}</a>
		        	</li>
				[/#list]
	        [/#if]
        </ul>
    </div>
    <div class="topRight">
        <div class="search">
            <input type="text" id="keywordsearch" name="keyword" placeholder="请输入搜索内容" />
            <a class="searchimg" href="javascript:void(0);">
                <img src="${baseimg}/imgs/default/search.png" alt="搜索" title="搜索" />
            </a>
        </div>
        <div class="loginbox">
        	[#if me??]
        		<a [#if !me.getIsUser()] class="votebtn normalUsertip" [#else] class="votebtn" [#if cid??]href="${base}/my/member/contribute?cid=${cid}"[#else]href="${base}/my/member/contribute"[/#if] [/#if]>投稿</a>
    		[#else]
	        	<a class="votebtn pop-up" href="javascript:;">投稿</a>
        	[/#if]
    		<div class="logined">
    			[#if me??]
        			<a href="${base}/my/center/article" id="secondLogined"  style="display:inline-block;"><img [#if me.useravatar??]src="${me.useravatar}"[#else]src="${baseimg}/imgs/default/defaultimg.png"[/#if] [#if me.getIsUser()]class="userborder"[/#if]/></a>
        			[#if me.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg" alt="" />[/#if]
        		[#else]
	        		<a class="userlogin pop-up" href="javascript:;"><img src="${baseimg}/imgs/default/defaultimg.png"/></a>
	        	[/#if]
        		<div class="userselfbox">
        			<div class="trangle"></div>
	            	<div class="userselfcontent">
	            		<ul  class="userights">
	            			<li>
	            				<a [#if me??] href="${base}/my/center/article" [#else] href="javascript:;" class="pop-up" [/#if]>我的文章</a>
	            			</li>
	            			<li>
	            				<a [#if me??] href="${base}/my/center/msg" [#else] href="javascript:;" class="pop-up" [/#if] id="message">消息通知</a>
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
</div>
<script type="text/javascript">
    $(function () {
    	if(navigator.userAgent.indexOf("iPad") != -1){  
            $("#keywordsearch").attr("placeholder","");
		}
			
    	var cla = 0;
    	$(".finance_catalogue").each(function(){ 
    		var tem = $(this).attr("class");
			if($(this).hasClass("current")){
				cla=1;
			}
		});
		if(cla == 0){
			$("#catalogue_0").addClass("current");
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
        
    });
</script>
<style>
	@-webkit-keyframes txtsearch-out {
      0% {
        -webkit-transform: translateX(2px);
      }
      20% {
        -webkit-transform: translateX(-2px);
      }
      40% {
        -webkit-transform: translateX(1px);
      }
      60% {
        -webkit-transform: translateX(-1px);
      }
      80% {
        -webkit-transform: translateX(0.3px);
      }
      100% {
        -webkit-transform: translateX(-0.3px);
      }
	}
	
	@keyframes txtsearch-out{
	 0% {
        -webkit-transform: translateX(2px);
      }
      20% {
        -webkit-transform: translateX(-2px);
      }
      40% {
        -webkit-transform: translateX(1px);
      }
      60% {
        -webkit-transform: translateX(-1px);
      }
      80% {
        -webkit-transform: translateX(0.3px);
      }
      100% {
        -webkit-transform: translateX(-0.3px);
      }
	}
	
	@-moz-keyframes txtsearch-out {
	  0% {
        -webkit-transform: translateX(2px);
      }
      20% {
        -webkit-transform: translateX(-2px);
      }
      40% {
        -webkit-transform: translateX(1px);
      }
      60% {
        -webkit-transform: translateX(-1px);
      }
      80% {
        -webkit-transform: translateX(0.3px);
      }
      100% {
        -webkit-transform: translateX(-0.3px);
      }
	}
	.txtsearch {
        -webkit-animation-name: txtsearch-out;
        -webkit-animation-duration: 0.35s;
        -webkit-animation-timing-function: ease-out;
        -webkit-animation-iteration-count: 1;
	}
</style>