[#ftl]
[#compress]
<!DOCTYPE html>
<!--[if IEMobile 7]><html class="iem6"><![endif]-->
<!--[if lt IE 7]><html class="ie6"><![endif]-->
<!--[if (IE 7)&!(IEMobile)]><html class="ie7"><![endif]-->
<!--[if (IE 8)&!(IEMobile)]><html class="ie8"><![endif]-->
<!--[if (IE 9)&!(IEMobile)]><html class="ie9"><![endif]-->
<!--[if gt IE 9]><!--><html class="iegt9"><!--<![endif]-->[#-- manifest="${manifest!"front.manifest"}"--]
<head>
<meta charset="UTF-8" />
<title>${title}[#if !isSEOConfiged?? || !isSEOConfiged]-支付项目[/#if]</title>
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/WebBaseHead.ftl" /]
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/WebBaseJs.ftl" /]
${head}
<style type="text/css">
@media only screen and (min-width:1000px){#header,#main,#footer{}}
.iegt9 .main-menu li:hover .submenu {display:block !important;}
</style>
</head>
<body>
	<div id="wrapper">
		[#include "/WEB-INF/FtlLib/Web/WebHeadArea.ftl" /]
		${body}
		[#include "/WEB-INF/FtlLib/Web/BodayAfterArea.ftl" /]
		[#include "/WEB-INF/FtlLib/Web/WebFootArea.ftl" /]
	</div>
</body>
</html>
[/#compress]