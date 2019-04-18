[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>专家管理</title>
<script type="text/javascript">
<!--
    function initDGData(row) {
        $("#btnsetlevel").linkbutton("disable");
        for(var i in row) {
            if(row[i].status == 13) {
                row[i].statusName = "已退出";
            }
            if(row[i].status == 0) {
                row[i].statusName = "请假";
            }
            if(row[i].user != null) {
                row[i].email = row[i].user.email;
                row[i].userSn = row[i].user.usersn;
                row[i].showname = row[i].user.showname;
            }
        }
    }
    
     function mydgOnSelectHandler(event, j){
        $("#btnsetlevel").linkbutton("enable");
        $("#dopwd").val("");
        $("#dopwd2").val("");
     }
     
     $.extend($.fn.validatebox.defaults.rules, {  
    //数字  
    Number: {  
        validator: function (value) {  
            var reg =/^[0-9]*$/;  
            return reg.test(value);  
        },  
        message: '请输入正确的数字.'  
    }   
})
//-->
</script>
</head>
<body>
    <section>
        <table>
            <tr>
                <td>专家总数：</td>
                <td><span>${allcount}(其中已退出${quitcount}个)</span></td>
                <td>普通专家数：</td>
                <td><span>${count}</span></td>
                <td>高级专家数：</td>
                <td><span>${highcount}</span></td>
                <td>特级专家数：</td>
                <td><span>${specialcount}</span></td>
                <td>专职专家数：</td>
                <td><span>${fullcount}</span></td>
            </tr>
        </table>
       
        [@a.search]
            <tr>
                <td><label>关键字:</label></td>
                <td><input type="text" name="keyword"/></td>
                <td><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类型:</label></td>
                <td><select name="type">
                    <option value="">全部</option>
                        [#list types?keys as item]
                            <option value="${item}">${types[item]}</option>
                        [/#list]
                    </select>
                </td>
                <td><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业:</label></td>
                <td>
                    <select name="major">
                        <option value="">全部</option>
                            [#list majors?keys as item]
                                <option value="${item}">${majors[item]}</option>
                            [/#list]
                    </select>
                </td>
                <td><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级别:</label></td>
                <td>
                    <select name="level">
                        <option value="">全部</option>
                            [#list levels?keys as item]
                                <option value="${item}">${levels[item]}</option>
                            [/#list]
                    </select>
                </td>
                <td><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是否全职:</label></td>
                <td>
                    <select name="isfulltime">
                        <option value="">全部</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </td>
            </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'userSn', width:50">会员号</th>
            <th data-options="field:'showname'">姓名</th>
            <th data-options="field:'sn'">专家号</th>
            <th data-options="field:'typeName'">专家类型</th>
            <th data-options="field:'levelName'">专家级别</th>
            <th data-options="field:'majorName'">专业</th>
            <th data-options="field:'chaircount'">评判答辩场数</th>
            <th data-options="field:'complaintcount'">被举报次数</th>
            <th data-options="field:'statusName'">状态</th>
        [/@a.datagrid]
       
        [@a.toolbar canadd=false canedit=true candel=false]
        <a id="btnsetlevel" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setlevel', title:'更改专家级别'}" onclick="editHandler(this, null, '#setlevel')">更改专家级别</a>
        [/@a.toolbar]
        [@a.tools /]
    </section>
    [@a.viewsection]
        <tr><td>会员号:</td><td><span set-key="userSn"></span></td></tr>
        <tr><td>姓名:</td><td><span set-key="showname"></span></td></tr>
        <tr><td>专家号:</td><td><span set-key="sn"></span></td></tr>
        <tr><td>专家类型:</td><td><span set-key="typeName"></span></td></tr>
        <tr><td>专业:</td><td><span set-key="majorName"></span></td></tr>
        <tr><td>专家级别:</td><td><span set-key="levelName"></span></td></tr>
        <tr><td>评判场数:</td><td><span set-key="chaircount"></span></td></tr>
        <tr><td>被举报次数:</td><td><span set-key="complaintcount"></span></td></tr>
        <tr><td>状态:</td><td><span set-key="statusName"></span></td></tr>
    [/@a.viewsection]
    [@a.editsection]
    <tr><td><input type="hidden" name="uid" /></td></tr>
    <tr><td>会员号：</td><td><span set-key="userSn"></span></td></tr>
    <tr><td>姓名：</td><td><span set-key="showname"></span></td></tr>
    <tr><td>专家号：</td><td><span set-key="sn"></span></td></tr>
    <tr><td>类型：</td><td><span set-key="typeName"></span></td></tr>
    <tr><td>级别：</td><td><span set-key="levelName"></span></td></tr>
    <tr><td>专业：</td><td><span set-key="majorName"></span></td></tr>
    <tr><td>被举报次数：</td><td><span set-key="complaintcount"></span></td></tr>
    <tr><td>通过时间：</td><td><span set-key="createtimeStr"></span></td></tr>
    <tr><td>通过理由：</td><td><span set-key="desc"></span></td></tr>
    <tr><td>评判场次：</td><td><input class="easyui-validatebox" set-key="chaircount" type="text" name="chaircount" data-options="required:true,validType:'Number'" /></td></tr>
    <tr>
        <td>状态：</td>
        <td><input type="radio" set-key="status" name="status" value="1" /><label>正常</label>&nbsp;
        <input type="radio" set-key="status" name="status" value="0" /><label>请假</label>&nbsp;
        <input type="radio" set-key="status" name="status" value="13" /><label>退出</label>&nbsp;</td>
    </tr>
    <tr><td>操作密码：</td><td><input class="easyui-validatebox" id="dopwd" type="password" name="dopwd" data-options="required:true" /></td></tr>
    [/@a.editsection]
    
    [@a.editsection id="setlevel" namesubmit="确认更改" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setlevel"]
    <tr><td><input type="hidden" name="uid" /></td></tr>
    <tr><td>会员号：</td><td><span set-key="userSn"></span></td></tr>
    <tr><td>姓名：</td><td><span set-key="showname"></span></td></tr>
    <tr><td>邮箱：</td><td><span set-key="email"></span></td></tr>
    <tr><td>专家号：</td><td><span set-key="sn"></span></td></tr>
    <tr><td>类型：</td><td><span set-key="typeName"></span></td></tr>
    <tr><td>专业：</td><td><span set-key="majorName"></span></td></tr>
    <tr><td>评判场次：</td><td><span set-key="chaircount"></span></td></tr>
    <tr><td>更改前级别：</td><td><span set-key="levelName"></span></td></tr>
    <tr><td>更改后级别：</td>
        <td><select name="level" set-key="level">
                    [#list levels?keys as item]
                        <option value="${item}">${levels[item]}</option>
                    [/#list]
            </select>
        </td>
    </tr>
    <tr><td>更改原因：</td><td><textarea rows=4 cols=50 name="reason" data-options="required:true" class="textarea easyui-validatebox"></textarea></td></tr>
    <tr><td>操作密码：</td><td><input class="easyui-validatebox" id="dopwd2" type="password" name="dopwd" data-options="required:true" /></td></tr>
    [/@a.editsection]
</body>
</html>