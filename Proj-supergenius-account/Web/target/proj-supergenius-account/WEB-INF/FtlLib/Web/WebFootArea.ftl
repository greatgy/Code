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
        <p class="bot_02">超天才网&copy;2013-2014  All Rights Reserved  ICP备09005826号 京ICP证130304号</p>
        <div class="spare">
            	关注我们：
            <img src="${baseimg}/imgs/default/weibo.png" alt="" />
            <a href="http://weibo.com/u/2939392194" target="_blank"><img src="${baseimg}/imgs/default/sina.png" alt="" /></a>
            <img src="${baseimg}/imgs/default/weixin.png" class="wx_img" alt="" />
        </div>
        <div class="wx_code">
            <img src="${baseimg}/imgs/default/app_code.jpg" class="app_img" alt="" />
        </div>
    </div>
</div>
<script src="${basejs}/js/libs3/jquery-1.9.0.min.js"></script>
<script src="${basejs}/js/libs3/jquery-migrate-1.0.0.min.js"></script>
<script src="${basejs}/js/libs3/jquery.lavalamp.min.js"></script>
<script src="${basejs}/js/libs3/jquery.easing.min.js"></script>
<script src="${basejs}/js/jslib-1.0.0.js"></script>
<script src="${basejs}/js/base-1.0.0.js"></script>
<script src="${basejs}/js/libs/headercom.js"></script>
<script>
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
    });
</script>