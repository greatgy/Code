[#ftl]
<html>
<head>
<meta charset="utf-8" />
<title>页面错误</title>
<link rel="stylesheet" type="text/css" href="${basecss}/css/base.css" />
<link rel="stylesheet" type="text/css" href="${basecss}/css/main.css" />
<link rel="stylesheet" type="text/css" href="${basecss}/css/default/common1.css" />
<style type="text/css">
html,body{width:100%;height:100%;margin:0px;padding:0px;}
.erro{text-align:left;width:400px; margin:100px auto 50px auto; height:180px; padding-left:280px; padding-top:146px; background:url(${baseimg}/imgs/erro_bg.png) no-repeat; color:#fff;}
.erro p a{ color:#fff100; display: inline-block; cursor:pointer; text-decoration:underline;}
</style>
</head>
<body>
<div id="wrapper">
    <header id="header">
    	<div class="container">
        	<a class="logo" href="http://www.genius.com">logo</a>
    	</div>
	</header>
	<div class="erro">
	[#if err.repeatSubmit??]
		<h2 style="font-size:20px; line-height:36px;">${err.title!"重复操作！"}</h2>
	[#else]
		<h2 style="font-size:20px; line-height:36px;">${err.title!"操作出现错误！"}</h2>
	    <p style="line-height:25px; font-size:14px;">
		${err.result}
	    </p>
	[/#if]
	</div>
</div>
</body>
</html>