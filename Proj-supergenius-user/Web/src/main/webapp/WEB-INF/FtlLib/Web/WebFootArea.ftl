[#ftl]
<div class="bottom">
    <div class="bottom_content">
        [#--<div class="bottom_top">
            <a href="http://tpi.supergenius.cn" target="_blank" ><img src="${baseimg}/imgs/default/merge_logo.png" alt="" /></a>
            <a href="http://finance.supergenius.cn" target="_blank" ><img src="${baseimg}/imgs/default/finance_logo.png" alt="" /></a>
                <a href="http://www.supergenius.cn" target="_blank" ><img src="${baseimg}/imgs/default/super_logo.png" alt="" /></a>
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
            [#--  <img src="${baseimg}/imgs/default/weibo.png" alt="" /> --]
            <a href="http://weibo.com/u/2939392194" target="_blank"><img src="${baseimg}/imgs/default/sina.png" alt="" /></a>
            <div class="weixin">
                <img src="${baseimg}/imgs/default/weixin.png" class="wx_img" alt="" />
                <div class="wx_code">
                    <img src="${baseimg}/imgs/default/app_code.jpg" class="app_img" alt="" />
                </div>
            </div>
        </div>
    </div>
    <div id="bctop"></div>
</div>

<script type="text/javascript">
    $(function(){
        
        $("#box-nav").lavaLamp({
            fx: "backout", 
            speed: 700,
            click: function(event, menuItem) {
                return true;
            }
        });

        /*鼠标移上微信出现二维码*/
        $(".wx_img").mouseover(function(){
            $(".wx_code").css("display","block");
        });
        $(".wx_img").mouseout(function(){
            $(".wx_code").css("display","none");
        });
        
        /*二级导航固定*/
        var firstTopH = 54;
        $(window).scroll(function () {
            var scrollTopH = $(window).scrollTop();
            var positionTop = firstTopH - scrollTopH;
            if(positionTop >= 0) {
                $(".top_02").css("top", positionTop);
            } else {
                $(".top_02").css("top", 0);
            }
        });

        /*返回顶部*/
        $(window).scroll(function () {
            var scrollTop = $(this).scrollTop();
            if(scrollTop < 380) {
                $("#bctop").hide();
            } else {
                $("#bctop").show();
                $("#bctop").click(function () {
                    $(window).scrollTop(0);
                });
            }
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