[#ftl]
<html>
<head>
<meta charset="utf-8" />
<title>页面错误</title>
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/WebBaseHead.ftl" /]
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/WebBaseJs.ftl" /]
<link rel="stylesheet" type="text/css" href="${basecss}/css/default/pages/tips.css" media="screen" />
</head>
<body>
	<div id="wrapper">
		[#include "/WEB-INF/FtlLib/Web/WebHeadArea.ftl" /]
		<div class="container">
			<div class="tipscontent">
				<img src="${baseimg}/imgs/default/upno.png" alt="" />
				<span class="word">
				[#if empty??]
					参数不正确
				[#elseif validationfailed??]
					站点请求参数错误
				[#elseif repeatSubmit??]
					重复提交
				[#else]
					${err.result}
				[/#if]
				</span>
			</div>
		</div>
		[#include "/WEB-INF/FtlLib/Web/WebFootArea.ftl" /]
	</div>

</body>
</html>