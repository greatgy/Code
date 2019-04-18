[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[@p.login /]
<link rel="stylesheet" type="text/css" href="${basecss}/css/default/base.css" />
<script type="text/javascript" src="${basejs}/js/pages/login.js"></script>
	<div class="top clear">
    	<div class="top_main1">
    		[#if phone??]
            <a href="http://www.supergenius.cn"  class="tl_act"><img src="${baseimg}/imgs/phonelogo.png"/></a>
            [#else]
            <a href="http://www.supergenius.cn"  class="tl_act"><img src="${baseimg}/imgs/logo.png" /></a>
            [/#if]
	    	<ul class="top_left">
	            <li><a href="http://www.supergenius.cn">首页</a></li>
	            <li><a href="${gupagebase}">顾雏军专栏</a></li>
	            <li><a href="${financebase}"  class="current_li">天财评论</a></li>
	            <li><a href="${lifebase}">天才人生</a></li>
	            <li><a href="${startupbase}">天才创业</a></li>
	            <li><a href="${careerbase}">天才职场</a></li>
	            <li><a href="${enterpriserbase}">引资购商</a></li>
	            <li><a href="${managernewsbase}">职业经理人培训</a></li>
	            <li><a href="${entrepreneurbase}">企业家培训</a></li>
	            <li><a href="http://moralnews.supergenius.cn/">职业道德培训</a></li>
	           [#-- <li><a href="http://guchujun.supergenius.cn">顾雏军专栏</a></li>--]
	            <li><a href="${aibase}">天才AI</a></li>
	            <li><a href="http://game.supergenius.cn/" target="_blank">天才数独棋</a></li>
	            <li><a href="http://www.supergenius.cn/wisdom">智慧产业</a></li>
	            <li><a 
	            	[#if me??]
	            		[#if me.getIsUser()]href="http://sns.supergenius.cn"[#else]href="javascript:;" class="normalUsertip"[/#if]
	            	[#else]
	            		href="${userbase}"
	            	[/#if]>
	            	人间仙境</a></li>
	            
	        </ul>
	        <ul class="top_right">
	        	<li class="member"><a href="http://www.supergenius.cn/huiyuan" target="_blank">会员通道</a></li>
	           [#if me??]
	            <li class='member'><a href="javascript:offline();">退出</a></li>
	        	<li class="member"><a href="${userbase}" target="_blank"><span class="headerName">
	        	
	        		[#if me.username?length <= 4]
	        			<span class="username1">${me.username}</span>
	        		[#elseif me.username?length <= 8]
	        		   <span class="username2">${me.username}</span>
	        		[#else]
	        			<span class="username3">${me.username}<span>
	        		[/#if]
	        		
	        	</span></a></li>
	        	<li class="newsnotice">
	        		<a href="javascript:;" class="newsnum hasnews" style="display:none;">
		        		新消息
		        		（<b class="number">0</b>）
                    	<span class="rdot"></span>
	        		</a>
	        		<a href="javascript:;" class="newsnum nonews" style="display:none;">
	        			消息
	        		</a>
	        		<div class="newsection" id="sitemsg">
	        			<div class="trangle">
						  <em></em>
						  <i></i>
						</div>
	        			<ul class="datalist" style="display:none;">
	        				<li data-channel="finance">
                            	<a href="${base}/my/msg" class="newmessage" target="_blank">
                              		天财评论有新消息
  									（<b class="number">0</b>）
								</a>
                              	<a href="javascript:;" class="del ignore">
                                	<img src="${baseimg}/imgs/default/delimg.png" />
                              	</a>
	        				</li>
	        				[#--<li data-channel="tpi">
                            	<a href="${tpibase}/mycenter/message/inbox" class="newmessage" target="_blank">
                              		并购平台有新消息
  									（<b class="number">0</b>）
								</a>
                              	<a href="javascript:;" class="del ignore">
                                	<img src="${baseimg}/imgs/default/delimg.png" />
                              	</a>
	        				</li>--]
	        				<li data-channel="moral">
                            	<a href="${moralbase}/mycenter/message" class="newmessage" target="_blank">
                              		职业道德培训有新消息
  									（<b class="number">0</b>）
								</a>
                              	<a href="javascript:;" class="del ignore">
                                	<img src="${baseimg}/imgs/default/delimg.png" />
                              	</a>
	        				</li>
	        				<li data-channel="manager">
                            	<a href="${managerbase}/mycenter/msg" class="newmessage" target="_blank">
                              		职业经理人培训有新消息
  									（<b class="number">0</b>）
								</a>
                              	<a href="javascript:;" class="del ignore">
                                	<img src="${baseimg}/imgs/default/delimg.png" />
                              	</a>
	        				</li>
                      	</ul>
                      	<div class="datalist nomsg" style="display:none;">
                      		<pre>
                      			<p>最纯洁的职业经理人绝不会利用职业生涯中所习得的技能和知识创业或参与行业竞争</p>
								<span class="authorname">———超天才职业经理人培训学院</span>
							</pre>
  							<div class="nonews">暂无新消息</div>
                      	</div>
    				</div>
				</li>
	           [#else]
                <li class="register"><a href="${userbase}/register">注册</a></li>
                <li class="tr_act login"><a class="pop-up" style="cursor:pointer;">登录</a></li>
	           [/#if]
	        </ul>
        </div>
    <!-- 非会员提醒 -->
    [#if me??]
		<div class="fairnormalUser">
			<h2>
				温馨提示
				<a href="javascripst:;" class="closebtn">X</a>
			</h2>
			<div class="normalTipbox">
				<p>目前该板块仅对超天才会员开放，成为超天才会员，享更多特权</p>
				<a href="${userbase}/register_improve/${me.uid}" class="beuser">加入超天才会员</a>
			</div>
		</div>
	[/#if]
   <script type="text/javascript">
   		$(document).on('click', ".normalUsertip", function(){
			$(".fairnormalUser").show();
			$(".mask").show();
			$("#loginmask").css("height", $(document.body).height());
	     });
	     $(document).on('click', ".closebtn", function(){
			$(".fairnormalUser").hide();
			$(".mask").hide();
	     });
	     $(document).on('click', ".mask", function(){
			$(".fairnormalUser").hide();
			$(this).hide();
	     });
	     $(function(){
   			[#if me??]
	        	if(navigator.userAgent.indexOf("iPad") != -1){
	        		[#if me.username?length <= 4]
	        		    $(".username1").html('[@p.TrimUsername content="${me.username}" mylength=4 /]');
	        		[#else]
	        			$(".username2").html('<span class="username3">[@p.TrimUsername content="${me.username}" mylength=4 /]</span>');
	        			$(".username3").html('[@p.TrimUsername content="${me.username}" mylength=4 /]');
	        		[/#if]
	            }
	         [/#if]
			});
	<!--  
	    function offline(){
	       $.post("${base}/offline", function(data){
	           if(data){
	               $(".welcome").addClass("hd");
	               $(".offline").addClass("hd");
	               $(".login").removeClass("hd");
	               $(".register").removeClass("hd");
	           }
	           var url = window.location.href;
				if(url.indexOf("/my/") >= 0 ) { //判断url地址中是否包含link字符串
				  gourl('${base}');
				}else{
				 	window.location.reload();
				}
	       });
	    }
     -->
	</script>
	<style>
		@media screen and (min-width: 748px) and (max-width: 1120px){
		    .username3{
		            width: 61px;
		            display: inline-block;
		            overflow: hidden;
		            height: 23px;
		    }
		     .username2{
		            width: 61px;
		            display: inline-block;
		            overflow: hidden;
		            height: 23px;
		    }
	    	.username3{
				background: -webkit-linear-gradient(left, #ffffff , #6f6f6f);
		        -webkit-background-clip: text;
		        color: transparent;
		        -webkit-text-fill-color: transparent;
			}
		    .username2{
				background: -webkit-linear-gradient(left, #ffffff , #4d4d4d);
		        -webkit-background-clip: text;
		        color: transparent;
		        -webkit-text-fill-color: transparent;
			}

		}

		@media screen and (min-width: 1120px){
			.username3{
				background: -webkit-linear-gradient(left, #ffffff , #6f6f6f);
		        -webkit-background-clip: text;
		        color: transparent;
		        -webkit-text-fill-color: transparent;
			}
			.username3{
		            width: 112px;
		            display: inline-block;
		            overflow: hidden;
		            height: 23px;
		    }
		 }
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
</div>
