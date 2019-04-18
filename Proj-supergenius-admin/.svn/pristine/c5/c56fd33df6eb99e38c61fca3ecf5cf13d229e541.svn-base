[#ftl]
[#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /]
<html>
<head>
<title>※天才诺尔顿※</title>
[#include "/WEB-INF/FtlLib/Admin/BaseDecorators/BaseHead.ftl" /]
<link rel="stylesheet" type="text/css" href="${basecss}/css/admin/layout.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${basecss}/css/admin/right.css" media="screen" />
<style type="text/css" media="screen">
.breadcrumbs_container {position:relative;}
#messagebar {position:absolute; right:30px; margin:4px 0; border-radius:15px; padding:3px 0; background-position:9px 3px;}
</style>
<script type="text/javascript">
<!--
//-->
</script>
</head>
<body>
	<header id="header">
		<hgroup>
			<h1 class="site_title"><a href="#">Website Admin</a></h1>
			<h2 class="section_title"></h2><div class="btn_view_site"><a href="${base}" target="_blank">主页</a></div>
		</hgroup>
	</header>
	<section id="secondary_bar">
		<div class="user">
			<p>${admin.name!} (<a href="#">0 Messages</a>)</p>
			<a class="logout_user" href="${base}${baseAdminPath}/j_security_logout" title="退出">退出</a>
		</div>
		<div class="breadcrumbs_container">
			<article class="breadcrumbs"><a href="index.html">Website Admin</a> <div class="breadcrumb_divider"></div> <a class="current">Dashboard</a></article>
			<div id="messagebar"></div>
		</div>
	</section>
</body>
</html>