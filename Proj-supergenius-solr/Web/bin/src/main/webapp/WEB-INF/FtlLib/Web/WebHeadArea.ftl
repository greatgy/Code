[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[@p.login /]
<script type="text/javascript" src="${basejs}/js/pages/login.js"></script>
<div class="header_tou clear">
	<div class="top">
    	<div class="top_main1">
    	<ul class="top_left">
            <li><a href="http://www.supergenius.cn">超天才</a></li>
            <li class="selectlink">
                    <a href="javascript:;">
                        “引资购商”专栏
                    </a>
                    <div class="navlist">
                        <div class="trangle"></div>
                        <ul class="navlink">
                            <li><a href="${enterpriserbase}/lecture">专题讲座</a></li>
                            <li><a href="${enterpriserbase}/appcooperation">互助合作平台</a></li>
                            <li><a href="${enterpriserbase}/index">企业家培训</a></li>
                            <li><a href="http://book.supergenius.cn/index.html" target="_blank">《引资购商》</a></li>
                        </ul>
                    </div>
            </li>
            <li><a href="${managerbase}">职业经理人培训</a></li>
            <li><a href="http://www.supergenius.cn/enterpriser">企业家培训</a></li>
            <li><a href="${moralbase}">职业道德培训</a></li>
           [#-- <li><a href="http://guchujun.supergenius.cn">顾雏军专栏</a></li>--]
            <li><a href="${financebase}">天财评论</a></li>
            <li><a href="${startupbase}">天才创业</a></li>
            [#-- <li><a href="${base}" class="tl_act">天才AI</a></li>--]
            <li><a href="http://www.supergenius.cn/wisdom">智慧产业</a></li>
            [#--<li><a href="http://tpi.supergenius.cn">并购平台</a></li>--]
            <li><a href="http://sns.supergenius.cn">人间仙境</a></li>
            <li><a href="http://game.supergenius.cn/" target="_blank">天才数独棋</a></li>
            <li><a href="${aibase}" >天才AI</a></li>
        </ul>
	        <ul class="top_right">
	           [#if me??]
	        	<li class='welcome'><a href="${userbase}" target="_blank">${me.showname}</a></li>
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
	        	<li class='offline'><a href="javascript:offline();">退出</a></li>
	           [#else]
                <li class="tr_act"><a class="pop-up" style="cursor:pointer;">登录</a></li>
                <li><a href="${userbase}/register">注册</a></li>
	           [/#if]
                <li><a href="http://www.supergenius.cn/huiyuan" target="_blank">会员特权</a></li>
	        </ul>
        </div>
    </div>
    
   <script type="text/javascript">
	<!--  
    [#if me??]
	    function offline(){
	       $.post("${base}/offline", function(data){
	           if(data){
	               $(".welcome").addClass("hd");
	               $(".offline").addClass("hd");
	               $(".login").removeClass("hd");
	               $(".register").removeClass("hd");
	           }
	           window.location.reload();
	       });
	    }
	[/#if]
     -->
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
</div>
