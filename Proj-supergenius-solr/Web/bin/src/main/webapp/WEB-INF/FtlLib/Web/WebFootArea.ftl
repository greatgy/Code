[#ftl]

	<div class="phoneFooter">
    	<p>超天才网©2017  www.supergenius.cn  All Rights Reserved  ICP备09005826号 京ICP证130304号</p>
	</div>
	<div class="bottom">
		<div class="bottom_content">
	        [#--<div class="bottom_top">
	           	<a href="http://tpi.supergenius.cn/" target="_blank"><img src="${baseimg}/imgs/default/merge_logo.png" alt=""/></a>
	            <a href="http://finance.supergenius.cn/" target="_blank"><img src="${baseimg}/imgs/default/finance_logo.png" alt="" /></a>
	            <a href="http://www.supergenius.cn/" target="_blank"><img src="${baseimg}/imgs/default/super_logo.png" alt="" /></a>
	        </div>--]
			<P class="bot_01">
				<a href="http://www.supergenius.cn/contact" target="_blank">联系我们</a>|
	            <a href="http://www.supergenius.cn/joinus" target="_blank">加入我们</a>|
	            [#--<a href="http://www.supergenius.cn/friendlink" target="_blank">友情链接</a>|--]
	            <a href="http://www.supergenius.cn/legal" target="_blank">法律声明</a>|
	            <a href="http://www.supergenius.cn/aboutus" target="_blank">关于我们</a>|
	            <a href="http://www.supergenius.cn/discuss" target="_blank">评论互动</a>
			</p>
		    <p class="bot_02">超天才网&copy;<span id="datepre">2013</span>-<span id="datenext">2014</span>  All Rights Reserved  ICP备09005826号 京ICP证130304号</p>
		    <div class="spare">
	          	  关注我们：
	        <a href="http://weibo.com/5538576591/profile?topnav=1&wvr=6" target="_blank"><img src="${baseimg}/imgs/default/sina.png" alt="" /></a>
	        <a href="http://t.qq.com/chaotiancaiwang?preview" target="_blank"><img src="${baseimg}/imgs/default/weibo.png" alt="" /></a>	          
	            <img src="${baseimg}/imgs/default/weixin.png" class="wx_img" alt="" />
	        </div>
	        <div class="wx_code">
	            <img src="${baseimg}/imgs/default/weixin_001.jpg" class="app_img" alt="" />
	        </div>
	    </div>
    </div>
    <div id="bctop"></div>
<script type="text/javascript" src="${basejs}/js/common.js"></script>
<script type="text/javascript">
	$(window).scroll(function() {
		var top = $(this).scrollTop();
		if (top <300) {
			$("#bctop").hide();
		}
		if(top>300){
			$("#bctop").show();
			$("#bctop").click(function(){
				$(window).scrollTop(0);
			})
		}
		if (top < 1440) {
			$(".tp_07_right").find(".tp_07_two").css("position","static");
		}
		if (top > 1440){
			$(".tp_07_right").find(".tp_07_two").slideDown("slow",function(){
				$(".tp_07_right").find(".tp_07_two").css("position","fixed").css({"top":"0"});
			});
		}
	});
</script>

<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?1165205bbe740f0169faee50717fff27";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>



<script>
	$(function() {
		/*鼠标移上微信出现二维码*/
        $(".wx_img").mouseover(function(){
            $(".wx_code").css("display","block");
            $(".app_img").css("overflow","visible");
        });
        $(".wx_img").mouseout(function(){
            $(".wx_code").css("display","none");
            $(".app_img").css("overflow","hidden");
        });	
        
        /*备案号自动获取年份*/
        var d = new Date();
        var nowYear = +d.getFullYear();
        var preYear = nowYear - 1;
        var nextYear = nowYear + 1;
        $("#datepre").text(preYear);
        $("#datenext").text(nextYear);
	});
	
</script>
