[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>互助平台报名管理</title>
</head>
<body>
    <section>
        <table>
            <tr>
                <td>报名总数：</td>
                <td><span>${apptotal}</span></td>
            </tr>
        </table>
        
        [@a.search]
            <tr>
                <td><label for="sname">关键字:</label></td>
                <td><input id="sname" type="text" name="keyword" style="width:150px;"/>&nbsp;&nbsp;&nbsp;&nbsp;</td> 
            </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'name',width:200">姓名</th>
            <th data-options="field:'userSn',width:200">会员号</th>
            <th data-options="field:'mobile',width:200">手机</th>
            <th data-options="field:'email',width:200">邮箱</th>
            <th data-options="field:'semester',width:200">学期</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false canedit=true candel=false]
        [/@a.toolbar]
        [@a.tools /]
     </section>

     [@a.viewsection]
         <tr><td>姓名:</td><td><span set-key="name"></span></td></tr>
        <tr><td>会员号:</td><td><span set-key="userSn"></span></td></tr>
        <tr><td>手机:</td><td><span set-key="mobile"></span></td></tr>
        <tr><td>邮箱:</td><td><span set-key="email"></span></td></tr>
        <tr><td>单位:</td><td><span set-key="company"></span></td></tr>
        <tr><td>职位:</td><td><span set-key="job"></span></td></tr>
        <tr><td>邮寄地址:</td><td><span set-key="address"></span></td></tr>
        <tr><td>学期:</td><td><span set-key="semester"></span></td></tr>
        <tr><td>申请时间:</td><td><span set-key="createtimeStr"></span></td></tr>
    [/@a.viewsection]
    
    [@a.editsection]
        <tr><td><input type="hidden" name="uid" /></td></tr>
        <tr><td>学期：</td><td><span set-key="semester"/></td></tr>
        <tr><td>姓名：</td><td><span set-key="name"/></td></tr>
        <tr><td>手机：</td><td><span set-key="mobile"/></td></tr>
        <tr><td>邮箱：</td><td><span set-key="email"/></td></tr>
        <tr><td>邮寄地址：</td><td><input class="easyui-validatebox" type="text" name="address" data-options="required:true" /></td></tr>
        <tr><td>公司：</td><td><input class="easyui-validatebox" type="text" name="company" data-options="required:true" /></td></tr>
        <tr><td>职位：</td><td><input class="easyui-validatebox" type="text" name="job" data-options="required:true" /></td></tr>
    [/@a.editsection]
</body>
</html>
