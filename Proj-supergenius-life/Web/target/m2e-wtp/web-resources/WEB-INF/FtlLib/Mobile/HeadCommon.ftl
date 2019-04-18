[#ftl]
<meta name="Copyright" content="2018 supergenius.cn, All Right Reserved,Inc." />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<!--针对手持设备优化，主要是针对一些老的不识别viewport的浏览器，比如黑莓 -->
<meta name="HandheldFriendly" content="true">
<!-- 微软的老式浏览器 -->
<meta name="MobileOptimized" content="320">
<!-- uc强制竖屏 -->
<meta name="screen-orientation" content="portrait">
<!-- QQ强制竖屏 -->
<meta name="x5-orientation" content="portrait">
<!-- UC强制全屏 -->
<meta name="full-screen" content="yes">
<!-- QQ强制全屏 -->
<meta name="x5-fullscreen" content="true">
<!-- UC应用模式 -->
<meta name="browsermode" content="application">
<!-- QQ应用模式 -->
<meta name="x5-page-mode" content="app">
<!-- windows phone 点击无高光 -->
<meta name="msapplication-tap-highlight" content="no">
<link rel="stylesheet" type="text/css" href="${basecss}/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${basecss}/css/reset.css">
<link rel="stylesheet" type="text/css" href="${basecss}/css/mobile/common.css">

<script src="${basejs}/js/libs3/jquery-2.1.4.min.js"></script>
<script src="${basejs}/js/libs3/jquery-migrate-1.2.1.min.js"></script>
<script src="${basejs}/js/libs3/bootstrap.min.js"></script>
<script src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
<script src="${basejs}/js/jslib-1.0.0.js"></script>
<script src="${basejs}/js/base-1.0.0.js"></script>
<!--[if (lt IE 9) & (!IEMobile)]>
<script type="text/javascript" src="${basejs}/js/html5/html5shiv.min.js"></script>
<![endif]-->
<script type="text/javascript">
    /*按钮及遮罩层效果*/
    $(function(){
        $(".collapse_btn").click(function(){
            $(this).toggleClass("changeColor");
            $(".nav_back").toggleClass("hd");
        });
        $(".nav_back").click(function(){
            $(".collapse_btn").removeClass("changeColor");
            $("#myNavbar").toggleClass("in");
            $(this).addClass("collapsing hd").toggleClass("in");
        });
        
	    $(".backbtn").click(function () {
			if (document.referrer == '') {
			    // 没有来源页面信息的时候，改成首页URL地址
			    $('.backbtn').attr('href', '${base}/index');
			} else {
			    $('.backbtn').attr('href', 'javascript:history.go(-1);');
			}
		});
    });
    
</script>       