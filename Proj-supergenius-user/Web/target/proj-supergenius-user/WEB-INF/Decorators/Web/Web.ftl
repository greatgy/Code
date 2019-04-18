[#ftl]
[#compress]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<title>${title}[#if !isSEOConfiged?? || !isSEOConfiged]-会员中心[/#if]</title>
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/WebBaseHead.ftl" /]
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/WebBaseJs.ftl" /]
${head}
</head>
<body>
	<div class="wraper">
		[#include "/WEB-INF/FtlLib/Web/WebHeadArea.ftl" /]
		${body}
		[#include "/WEB-INF/FtlLib/Web/WebFootArea.ftl" /]
	</div>
</body>
</html>
[/#compress]