[#ftl]
[#compress]
<!DOCTYPE html>
<!--[if IEMobile 7]><html class="iem6"><![endif]-->
<!--[if lt IE 7]><html class="ie6"><![endif]-->
<!--[if (IE 7)&!(IEMobile)]><html class="ie7"><![endif]-->
<!--[if (IE 8)&!(IEMobile)]><html class="ie8"><![endif]-->
<!--[if (IE 9)&!(IEMobile)]><html class="ie9"><![endif]-->
<!--[if gt IE 9]><!--><html class="iegt9"><!--<![endif]-->
<head>
<meta charset="UTF-8" />
<title>${title}[#if !isSEOConfiged?? || !isSEOConfiged][/#if]</title>
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/HeadCommon.ftl" /]
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/WebBaseJs.ftl" /]
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/WebBaseHead.ftl" /]
${head}
<style type="text/css">
@media only screen and (min-width:1000px){#header,#main,#footer{}}
.iegt9 .main-menu li:hover .submenu {display:block !important;}
</style>
</head>
<body>
		<div class="header_fix">
		    [#--[#include "/WEB-INF/FtlLib/Mobile/HeaderTopNav.ftl" /]--]
		</div>
		<div class="containerBox">
			${body}
		</div>
		[#include "/WEB-INF/FtlLib/Web/WebFootArea.ftl" /]
</body>
</html>
[/#compress]