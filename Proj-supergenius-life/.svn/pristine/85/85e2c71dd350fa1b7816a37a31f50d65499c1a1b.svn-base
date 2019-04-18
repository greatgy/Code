[#ftl]

<meta name="Copyright" content="2015 supergenius.cn, All Right Reserved,Inc." />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<!-- 针对手持设备优化，主要是针对一些老的不识别viewport的浏览器，比如黑莓 -->
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
<link rel="stylesheet" type="text/css" href="${basecss}/css/default/reset.css">
<link rel="stylesheet" type="text/css" href="${basecss}/css/default/common.css">
<style type="text/css">
	@media (min-width: 768px) {
		.navbar-toggle {
		 	display: block;
		}
	}
</style>
<script src="${basejs}/js/libs3/jquery-2.1.4.min.js"></script>
<script src="${basejs}/js/libs3/jquery-migrate-1.2.1.min.js"></script>
<script src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
<script src="${basejs}/js/jslib-1.0.0.js"></script>
<script src="${basejs}/js/base-1.0.0.js"></script>
<!--[if (lt IE 9) & (!IEMobile)]>
<script type="text/javascript" src="${basejs}/js/html5/html5shiv.min.js"></script>

<![endif]-->
<script>
	 var phoneWidth = parseInt(window.screen.width);
	 var phoneHeight = parseInt(window.screen.height);
	 /*var phoneScale = phoneWidth/750;//除以的值按手机的物理分辨率*/
	 var physic = document.documentElement.clientWidth;
	 var phoneScale = physic/phoneWidth;
	 var ua = navigator.userAgent;
	 if (/Android (\d+\.\d+)/.test(ua)) {
	 var version = parseFloat(RegExp.$1);
	 // andriod 2.3
	 if (version > 2.3) {
	 document.write('<meta name="viewport" content="width=device-width,initial-scale='+phoneScale+',minimum-scale='+phoneScale+',maximum-scale='+phoneScale+',user-scalable=no">');
	 // andriod 2.3以上
	 } else {
	 document.write('<meta name="viewport" content="width=device-width,user-scalable=no">');
	 }
	 // 其他系统
	 } else {
	 document.write('<meta name="viewport" content="width=device-width, initial-scale='+phoneScale+',minimum-scale='+phoneScale+',maximum-scale ='+phoneScale +',user-scalable=no,">');
	 }
</script>      