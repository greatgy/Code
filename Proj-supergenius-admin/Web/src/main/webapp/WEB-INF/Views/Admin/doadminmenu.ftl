[#ftl]
[#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /]
<html>
<head>
<title>※超天才※</title>
[#include "/WEB-INF/FtlLib/Admin/BaseDecorators/BaseHead.ftl" /]
<link rel="stylesheet" type="text/css" href="${basecss}/css/admin/layout.css" media="screen" />
<style type="text/css" media="screen">
	.toggle-icon{
		width:12px;
		height:10px;
		float:right;
		margin-top:5px;
		cursor:pointer;
		background:url('${baseimg}/imgs/admin/adminicons.png') no-repeat -54px -55px;
	}
	.open {
		background:url('${baseimg}/imgs/admin/adminicons.png') no-repeat -54px -76px;
	}
</style>
<script type="text/javascript">
<!--
$(function($) {
	$("#sidebar a .menu-name").append("<span class='toggle-icon open'></span>");
	$("#sidebar a .menu-name").click(function(){
		$(this).parent().next("ul.toggle:first").toggle(200);
		$(this).children().toggleClass("open");
	});
	$(".closed").click();
	
	$("ul").each(function() {
	   if($(this).children().length == 0) {
	       $(this).prev("a").remove();
	   }
	});
});
//-->
</script>
</head>
<body>
	<aside id="sidebar" class="column" style="height: 100%;overflow-y: auto">
		<a href><h3 class="menu-name closed">快捷通道</h3></a>
		<ul class="toggle">
			<li class="icn_new_article"><a target="_blank" href="http://finance.supergenius.cn/">返回前台</a></li>
			<li class="icn_edit_article"><a href="${base}${baseAdminPath}/j_security_logout">退出</a></li>
		</ul>
		<a href><h3 class="menu-name closed">管理员管理</h3></a>
		<ul class="toggle">
			[@security.authorize url="${base}${baseAdminPath}/adminindex"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/adminhome">管理控制台</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/admin"]
			<li class="icn_view_users"><a target="rightpanel" href="${base}${baseAdminPath}/admin">管理员管理</a></li>
			[/@security.authorize]
	    	[@security.authorize url="${base}${baseAdminPath}/role"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/role">角色管理</a></li>
			[/@security.authorize]
		</ul>
		<a href><h3 class="menu-name closed">网站配置</h3></a>
		<ul class="toggle">
			[@security.authorize url="${base}${baseAdminPath}/seotpi"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seotpi">并购平台SEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/seoofficial"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seoofficial">官网SEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/seomoral"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seomoral">职业道德培训SEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/seomanager"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seomanager">职业经理人培训SEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/seoenterpriser"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seoenterpriser">引资购商SEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/seostartup"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seostartup">天才创业SEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/seoai"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seoai">天才AISEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/seocareer"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seocareer">天才职场SEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/seofinance"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seofinance">天财评论SEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/seogupage"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seogupage">顾雏军专栏SEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/seolife"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seolife">天才人生SEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/seomanagernews"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seomanagernews">职业经理人培训文章SEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/seoentrepreneur"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seoentrepreneur">企业家培训SEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/seomoralnews"]
			<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/seomoralnews">职业道德培训文章SEO管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/command"]
			<li class="icn_security"><a target="rightpanel" href="${base}${baseAdminPath}/command">网站初始化</a></li>
			[/@security.authorize]
		</ul>
		<a href><h3 class="menu-name closed">管理员</h3></a>
		<ul class="toggle">
			<li class="icn_settings"><a target="rightpanel" href="${base}${baseAdminPath}/adminhome">配置</a></li>
			<li class="icn_security"><a target="rightpanel" href="${base}${baseAdminPath}/adminpwd">登录密码</a></li>
			<li class="icn_security"><a target="rightpanel" href="${base}${baseAdminPath}/admindopwd">操作密码</a></li>
			<li class="icn_jump_back"><a href="${base}${baseAdminPath}/j_security_logout">退出</a></li>
		</ul>
		<a href><h3 class="menu-name closed">官网后台</h3></a>
		<ul class="toggle">
			[@security.authorize url="${base}${baseAdminPath}/official/banner"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/official/banner">banner管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/characters/content"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/characters/content">网站内容管理(文字内容)</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/html/content"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/html/content">网站内容管理(HTML代码)</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/official/recruit"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/official/recruit">招聘管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/official/article"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/official/article">文章管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/official/news"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/official/news">新闻管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/official/video"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/official/video">视频管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/official/discuss/0"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/official/discuss/0">评论互动管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/official/discuss/2"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/official/discuss/2">顾雏军专栏评论</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/solr/solrcontent"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/solr/solrcontent">搜索推荐管理</a></li>
			[/@security.authorize]
		</ul>
		 <a href><h3 class="menu-name closed">天财评论</h3></a>
        <ul class="toggle">
        	[@security.authorize url="${base}${baseAdminPath}/finance/economics"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/economics">财经资讯</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/finance/invest"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/invest">投资资讯</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/finance/merger"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/merger">并购资讯</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/finance/risk"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/risk">风险资讯</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/finance/technology"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/technology">科技资讯</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/finance/gold"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/gold">醒世金语</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/finance/entrepreneur"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/entrepreneur">企业家</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/finance/profession"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/profession">职业经理人</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/finance/nightwords"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/nightwords">职场夜话</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/finance/financearticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/financearticle">文章管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/finance/financecomments"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/financecomments">评论管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/finance/financelabel"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/financelabel">标签管理</a></li>
            [/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/finance/financecontent"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/financecontent">广告位管理</a></li>
            [/@security.authorize]			
			[@security.authorize url="${base}${baseAdminPath}/finance/financecatalogue"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/finance/financecatalogue">模块管理</a></li>
            [/@security.authorize]        
        </ul>
        
		<a href><h3 class="menu-name closed">并购平台</h3></a>
        <ul class="toggle">
        	[@security.authorize url="${base}${baseAdminPath}/article"]
            	<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/article">文章管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/team"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/team">团队管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/type"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/type">团队(项目)类别管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/project/0"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/project/0">超天才网推荐项目管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/project/1"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/project/1">推荐机构推荐项目管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/project/2"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/project/2">个人推荐项目管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/wish/1"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/wish/1">并购方案管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/wish/2"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/wish/2">投资计划管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/tpiuser/0"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/tpiuser/0">投资机构管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/tpiuser/1"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/tpiuser/1">推荐机构管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/tpiuser/2"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/tpiuser/2">并购机构管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/comment"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/comment">评论管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/notice"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/notice">招聘管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/message"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/message">消息中心管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/tpicontent"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/tpicontent">网站内容管理</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/auditadmin"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/auditadmin">用户审核管理员邮箱</a></li>
			[/@security.authorize]
			[@security.authorize url="${base}${baseAdminPath}/emailtemplate"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/emailtemplate">系统邮件模板管理</a></li>
			[/@security.authorize]
        </ul>
        
        <a href><h3 class="menu-name closed">职业道德培训</h3></a>
        <ul class="toggle">
        	
        	[@security.authorize url="${base}${baseAdminPath}/moralnews/moralnewsarticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moralnews/moralnewsarticle">文章管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/moralnews/moralnewssimplearticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moralnews/moralnewssimplearticle">文章帖子管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/moralnews/moralnewscomments"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moralnews/moralnewscomments">文章评论管理</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/moralnews/moralnewscatalogue"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moralnews/moralnewscatalogue">文章模块管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/moralnews/moralnewsad"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moralnews/moralnewsad">文章广告位管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/moralnews/moralnewsannouncement"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moralnews/moralnewsannouncement">文章公告管理</a></li>
            [/@security.authorize]

        	[@security.authorize url="${base}${baseAdminPath}/moral/doc"]
            	<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moral/doc">培训讲义管理</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/moral/video"]
            	<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moral/video">培训视频管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/moral/cases"]
            	<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moral/cases">案例库管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/moral/student"]
            	<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moral/student">学员管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/moral/userdoc"]
            	<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moral/userdoc">分享管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/moral/moralarticle"]
            	<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moral/moralarticle">发帖管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/moral/qst"]
            	<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moral/qst">题库管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/moral/exam"]
            	<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moral/exam">考试管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/moral/comment"]
            	<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moral/comment">评论管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/moral/moralmessage"]
            	<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moral/moralmessage">消息管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/moral/announcement"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/moral/announcement">社区公告管理</a></li>
            [/@security.authorize]
        </ul>
        
        <a href><h3 class="menu-name closed">会员中心</h3></a>
        <ul class="toggle">
            [@security.authorize url="${base}${baseAdminPath}/user/member"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/user/member">会员列表</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/user/consumer"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/user/consumer">用户列表</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/user/order"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/user/order">订单管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/user/trade"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/user/trade">交易管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/user/emailtemplate"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/user/emailtemplate">系统邮件管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/user/emaillog"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/user/emaillog">群发邮件管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/user/visitor"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/user/visitor">游客管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/account/recharge"]
            	<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/account/recharge">充值管理</a></li>
            [/@security.authorize]
        </ul>
        
        <a href><h3 class="menu-name closed">职业经理人培训</h3></a>
        <ul class="toggle">
            [@security.authorize url="${base}${baseAdminPath}/managernews/managernewscatalogue"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/managernews/managernewsarticle">文章管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/managernews/managernewscomments"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/managernews/managernewscomments">文章评论管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/managernews/managernewsad"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/managernews/managernewsad">广告位管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/managernews/managernewscatalogue"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/managernews/managernewscatalogue">模块管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/manager/htmlcontent"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/htmlcontent">内容管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/manager/video"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/video">视频管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/manager/appstudent"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/appstudent">学员申请管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/manager/student"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/student">学员管理</a></li>
            [/@security.authorize]
             [@security.authorize url="${base}${baseAdminPath}/manager/challenge"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/challenge">挑战管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/manager/reply"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/reply">答辩管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/manager/appjudge"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/appjudge">裁判申请管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/manager/judge"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/judge">裁判管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/manager/appexpert"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/appexpert">专家申请管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/manager/expert"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/expert">专家管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/manager/certificate"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/certificate">证书颁发管理</a></li>
            [/@security.authorize]
            [#--
	            [@security.authorize url="${base}${baseAdminPath}/manager/conference"]
	                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/conference">会议室管理</a></li>
	            [/@security.authorize]
            --]
            [@security.authorize url="${base}${baseAdminPath}/manager/comments"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/comments">评论管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/manager/complain"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/manager/complaint">举报管理</a></li>
            [/@security.authorize]
        </ul>
        <a href><h3 class="menu-name closed">企业家培训</h3></a>
        <ul class="toggle">
            [@security.authorize url="${base}${baseAdminPath}/entrepreneur/entrepreneurarticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/entrepreneur/entrepreneurarticle">文章管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/entrepreneur/entrepreneurcomments"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/entrepreneur/entrepreneurcomments">评论管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/entrepreneur/entrepreneurcatalogue"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/entrepreneur/entrepreneurcatalogue">模块管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/entrepreneur/entrepreneurad"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/entrepreneur/entrepreneurad">广告位管理</a></li>
            [/@security.authorize]
        </ul>
        <a href><h3 class="menu-name closed">引资购商</h3></a>
        <ul class="toggle">
        	[@security.authorize url="${base}${baseAdminPath}/enterpriser/enterpriserarticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/enterpriser/enterpriserarticle">文章管理</a></li>
            [/@security.authorize] 
            [@security.authorize url="${base}${baseAdminPath}/enterpriser/enterpriserforum"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/enterpriser/enterpriserforum">论坛管理</a></li>
            [/@security.authorize] 
            [@security.authorize url="${base}${baseAdminPath}/enterpriser/enterpriserphoto"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/enterpriser/enterpriserphoto">引资购商图片管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/enterpriser/enterpriservideo"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/enterpriser/enterpriservideo">视频管理</a></li>
            [/@security.authorize] 
            [@security.authorize url="${base}${baseAdminPath}/enterpriser/enterpriserhtmlcontent"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/enterpriser/enterpriserhtmlcontent">内容管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/enterpriser/enterprisercatalogue"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/enterpriser/enterprisercatalogue">模块管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/enterpriser/enterprisercomments"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/enterpriser/enterprisercomments">评论管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/enterpriser/enterprisercontent"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/enterpriser/enterprisercontent">广告位管理</a></li>
            [/@security.authorize]
             [@security.authorize url="${base}${baseAdminPath}/enterpriser/lecture"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/enterpriser/lecture">讲座管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/enterpriser/participate"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/enterpriser/participate">讲座报名管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/enterpriser/appcooperation"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/enterpriser/appcooperation">互助平台报名管理</a></li>
            [/@security.authorize] 
              [@security.authorize url="${base}${baseAdminPath}/enterpriser/announcement"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/enterpriser/announcement">社区公告管理</a></li>
            [/@security.authorize]
        </ul>
        
        <a href><h3 class="menu-name closed">创业测试</h3></a>
        <ul class="toggle">
            [@security.authorize url="${base}${baseAdminPath}/startup/question"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/startup/question">试题管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/startup/ruler"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/startup/ruler">规则管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/startup/statistic"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/startup/statistic">统计信息</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/startup/music"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/startup/music">背景音乐管理</a></li>
            [/@security.authorize]
            
        </ul>
        <a href><h3 class="menu-name closed">创业平台</h3></a>
        <ul class="toggle">
            [@security.authorize url="${base}${baseAdminPath}/startup/startuparticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/startup/startuparticle">文章管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/startup/label"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/startup/label">标签管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/startup/startupcontent"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/startup/startupcontent">广告位管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/startup/startupcatalogue"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/startup/startupcatalogue">模块管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/startup/startupcomments"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/startup/startupcomments">评论管理</a></li>
            [/@security.authorize]
        </ul>
        <a href><h3 class="menu-name closed">天才AI</h3></a>
        <ul class="toggle">
        	[@security.authorize url="${base}${baseAdminPath}/ai/aiarticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/ai/aiarticle">会员文章管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/ai/aicontribute"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/ai/aicontribute">游客投稿管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/ai/ailabel"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/ai/ailabel">标签管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/ai/aicontent"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/ai/aicontent">广告位管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/ai/aicatalogue"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/ai/aicatalogue">模块管理</a></li>
            [/@security.authorize]   
            [@security.authorize url="${base}${baseAdminPath}/ai/aicomments"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/ai/aicomments">评论管理</a></li>
            [/@security.authorize]
        </ul>
        
        <a href><h3 class="menu-name closed">天才职场</h3></a>
        <ul class="toggle">
        	[@security.authorize url="${base}${baseAdminPath}/career/teasearticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/teasearticle">职场吐槽-文章</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/career/careercomplainarea"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/careercomplainarea">职场吐槽-专区管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/career/puzzledarticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/puzzledarticle">职场困惑-文章</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/career/careerpuzzledproblem"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/careerpuzzledproblem">职场困惑-问题</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/career/careerpuzzledanswer"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/careerpuzzledanswer">职场困惑-回答管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/career/gossiparticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/gossiparticle">职场八卦</a></li>
            [/@security.authorize] 
            [@security.authorize url="${base}${baseAdminPath}/career/careertease"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/careertease">职场鬼话</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/career/experiencearticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/experiencearticle">职场心得</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/career/guidearticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/guidearticle">应聘指南</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/career/careercomments"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/careercomments">评论管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/career/careercatalogue"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/careercatalogue">模块管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/career/careerbanner"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/careerbanner">banner管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/career/careercontent"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/careercontent">广告位管理</a></li>
            [/@security.authorize]
        </ul>
        
        <a href><h3 class="menu-name closed">职场测试</h3></a>
        <ul class="toggle">
        	[@security.authorize url="${base}${baseAdminPath}/career/careerquestion"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/careerquestion">题目管理</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/career/careerruler"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/careerruler">规则管理</a></li>
            [/@security.authorize]        
            [@security.authorize url="${base}${baseAdminPath}/career/statistic"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/statistic">统计管理</a></li>
            [/@security.authorize]   
            [@security.authorize url="${base}${baseAdminPath}/career/careerrulercontent"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/careerrulercontent">结果管理</a></li>
            [/@security.authorize]  
            [@security.authorize url="${base}${baseAdminPath}/career/careermusic"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/career/careermusic">音乐管理</a></li>
            [/@security.authorize] 
        </ul>
        
        <a href><h3 class="menu-name closed">顾雏军专栏</h3></a>
        <ul class="toggle">
            [@security.authorize url="${base}${baseAdminPath}/gupagearticle/gupagenews"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/gupagearticle/gupagenews">动态管理</a></li>
            [/@security.authorize] 
            [@security.authorize url="${base}${baseAdminPath}/gupage/pager/gupagepager"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/gupage/pager/gupagepager">学术论文管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/gupage/pager/gupagepatent"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/gupage/pager/gupagepatent">专利管理</a></li>
            [/@security.authorize]  
            [@security.authorize url="${base}${baseAdminPath}/gupagearticle/gupagearticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/gupagearticle/gupagearticle">个人文章管理</a></li>
            [/@security.authorize] 
        	[@security.authorize url="${base}${baseAdminPath}/gupage/gupagephoto"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/gupage/gupagephoto">图片管理</a></li>
            [/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/gupage/gupagevideo"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/gupage/gupagevideo">视频管理</a></li>
            [/@security.authorize]  
            [@security.authorize url="${base}${baseAdminPath}/gupage/gupagedebate"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/gupage/gupagedebate">郎顾之争管理</a></li>
            [/@security.authorize]    
            [@security.authorize url="${base}${baseAdminPath}/gupage/gupagecontent"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/gupage/gupagecontent">广告位管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/gupage/gupagecatalogue"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/gupage/gupagecatalogue">模块管理</a></li>
            [/@security.authorize]
            [@security.authorize url="${base}${baseAdminPath}/gupage/gupagecomments"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/gupage/gupagecomments">评论管理</a></li>
            [/@security.authorize]      
        </ul>
        
        <a href><h3 class="menu-name closed">天才人生</h3></a>
        <ul class="toggle">
        	[@security.authorize url="${base}${baseAdminPath}/life/lifearticle"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/lifearticle">文章管理</a></li>
            [/@security.authorize] 
        	[@security.authorize url="${base}${baseAdminPath}/life/lifeproblem"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/lifeproblem">问题管理</a></li>
            [/@security.authorize] 
        	[@security.authorize url="${base}${baseAdminPath}/life/lifeanswer"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/lifeanswer">回答管理</a></li>
            [/@security.authorize] 
        	[@security.authorize url="${base}${baseAdminPath}/life/lifetopic"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/lifetopic">话题管理</a></li>
            [/@security.authorize] 
        	[@security.authorize url="${base}${baseAdminPath}/life/lifecourse"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/lifecourse">课程管理</a></li>
            [/@security.authorize]  
        	[@security.authorize url="${base}${baseAdminPath}/life/lifesubject"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/lifesubject">科目管理</a></li>
            [/@security.authorize]  
        	[@security.authorize url="${base}${baseAdminPath}/life/lifevideo"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/lifevideo">视频管理</a></li>
            [/@security.authorize]  
        	[@security.authorize url="${base}${baseAdminPath}/life/lifeessay"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/lifeessay">动态管理</a></li>
            [/@security.authorize]     
        	[@security.authorize url="${base}${baseAdminPath}/life/lifecontent"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/lifecontent">内容管理</a></li>
            [/@security.authorize]     
        	[@security.authorize url="${base}${baseAdminPath}/life/lifecomments"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/lifecomments">评论管理</a></li>
            [/@security.authorize]     
        	[@security.authorize url="${base}${baseAdminPath}/life/lifecatalogue"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/lifecatalogue">模块管理</a></li>
            [/@security.authorize]     
        	[@security.authorize url="${base}${baseAdminPath}/life/lifebanner"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/lifebanner">banner管理</a></li>
            [/@security.authorize]     
        	[@security.authorize url="${base}${baseAdminPath}/life/lifead"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/lifead">广告位管理</a></li>
        	[/@security.authorize]
        	[@security.authorize url="${base}${baseAdminPath}/life/complaint"]
                <li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/life/complaint">举报管理</a></li>
            [/@security.authorize]  
        </ul>
        

        <a href><h3 class="menu-name closed">全站文章</h3></a>
		<ul class="toggle">
			[@security.authorize url="${base}${baseAdminPath}/official/allarticle"]
				<li class="icn_folder"><a target="rightpanel" href="${base}${baseAdminPath}/official/allarticle">全站文章</a></li>
			[/@security.authorize]
		</ul>
		<footer>
			<hr>
			<p><strong>Copyright © 2016 Website SuperGenius</strong></p>
			<p>it@supergenius.cn</p>
		</footer>
	</aside>
</body>
</html>