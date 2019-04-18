[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>用户审核管理员管理</title>
<script type="text/javascript">
<!--
//-->
</script>
</head>
<body>
    [#list map?keys as item]
        <form action="${base}${baseAdminPath}/auditadmin" method="post">
            ${map[item]}：<input name="emails" value="${map2[item]}" placeholder="多个管理员邮箱以英文逗号隔开" />
            <input type="hidden" name="type" value="${item}" />
            <input type="submit" value="保存" />
        </form>
        <br />
        <br />
    [/#list]
</body>
</html>
