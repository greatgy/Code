[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
[#import "/WEB-INF/FtlLib/Macro/User.ftl" as u]
<html>
<head>
<title>群发邮件管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--
function initDGData(row) {
    for(var i in row) {
      var receiverString = JSON.parse(row[i].receiver);
      row[i].receiverName="";
        for(var p in receiverString) {
          row[i].receiverName = row[i].receiverName + receiverString[p] + ",";
        }
      row[i].receiverName = row[i].receiverName.substring(0,row[i].receiverName.length-1);
   }
}
//-->
</script>
</head>
<body>
    <section>
        [@a.datagrid]
            <th data-options="field:'uid',width:200">编号</th>
            <th data-options="field:'title',width:200">邮件标题</th>
            <th data-options="field:'content',width:200">邮件内容摘要</th>
            <th data-options="field:'receiverName',width:200">收件人</th>
            <th data-options="field:'sender',width:200">发件人</th>
            <th data-options="field:'createtimeStr',width:200">发送时间</th>
        [/@a.datagrid]
        [@a.toolbar canedit=false candel=false]
        [/@a.toolbar]
        [@a.tools /]
    </section>
    [@a.addsection]
        <tr>
            <td>选择发送对象：</td>
            <td>[@u.usersearch name="useruids"/]</td>
        </tr>
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="content"/]
            </td>
        </tr>
    [/@a.addsection]
    
    [@a.viewsection]
        <tr>
            <td>编号：</td>
            <td><span set-key="uid"></span></td>
        </tr>
        <tr>
            <td>邮件标题：</td>
            <td><span set-key="title"></span></td>
        </tr>
        <tr>
            <td>邮件内容摘要：</td>
            <td><span set-key="content"></span></td>
        </tr>
        <tr>
            <td>收件人：</td>
            <td> <span set-key="receiverName"></span></td>
        </tr>
        <tr>
            <td>发件人：</td>
            <td><span set-key="sender"></span></td>
        </tr>
        <tr>
            <td>发送时间：</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
    [/@a.viewsection]
</body>
</html>