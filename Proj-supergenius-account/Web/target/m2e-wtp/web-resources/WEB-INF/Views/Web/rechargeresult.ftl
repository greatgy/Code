[#ftl]
<html>
<head>
<title></title>
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/WebBaseHead.ftl" /]
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/WebBaseJs.ftl" /]
<link rel="stylesheet" type="text/css" href="${basecss}/css/default/pages/tips.css" media="screen" />
<meta http-equiv="refresh" content="${time}!'2';url=${resulturl}">
<script type="text/javascript">
<!--
window.location.href="${resulturl}"
//-->
</script>
</head>
<body>
	<div id="wrapper">
		[#include "/WEB-INF/FtlLib/Web/WebHeadArea.ftl" /]
		 <div class="container">
			<div class="tipscontent">
				[#if err??]
					<img src="${baseimg}/imgs/default/upno.png" alt="" />
					<span class="word">
						[#if err.notexist??]
							银行没有返回结果
						[#elseif exception??]
							支付异常请联系管理员
						[/#if]
					</span>
				[#else]
					<img src="${baseimg}/imgs/default/upyes.png" alt="" />
					<span class="word">
						支付成功
					</span>
				[/#if]
			</div>
		</div>
		[#include "/WEB-INF/FtlLib/Web/WebFootArea.ftl" /]
	</div>
</body>
</html>
