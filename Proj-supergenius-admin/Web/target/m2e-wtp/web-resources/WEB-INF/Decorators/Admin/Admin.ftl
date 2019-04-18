[#ftl]
[#compress]
<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
[#include "/WEB-INF/FtlLib/Admin/BaseDecorators/BaseHead.ftl" /]
<link rel="stylesheet" type="text/css" href="${basecss}/css/admin/right.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${basecss}/css/jquery/easyui/gray/easyui.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${basecss}/css/jquery/easyui/icon.css" media="screen" />
<script type="text/javascript" src="${basejs}/js/jquery/plugins/easyui/easyui-1.3.5.min.js"></script>
<script type="text/javascript" src="${basejs}/js/jquery/plugins/easyui/easyui-lang-zh_CN.min.js"></script>
[#--<script type="text/javascript" src="${basejs}/js/libs/jquery.easyui.extension-1.0.0.js"></script>--]
<script type="text/javascript" src="${basejs}/js/admin/breadscrumb.js"></script>
<script type="text/javascript" src="${basejs}/js/libs/set-1.0.0.js"></script>
<script type="text/javascript" src="${basejs}/js/admin/right-1.0.0.js"></script>
[#include "/WEB-INF/FtlLib/Admin/BaseDecorators/BaseJs.ftl" /]
${head}
</head>
<body class="rightpanel">
	<div id="main" class="column">
	<h4 id="alert"></h4>
	${body}
	</div>
</body>
</html>
[/#compress]