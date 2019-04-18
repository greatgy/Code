[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>裁判管理</title>
<script type="text/javascript">
<!--
    function initDGData(row) {
        for(var i in row) {
            if(row[i].status == 13) {
                row[i].statusName = "已退出";
            }
            if(row[i].status == 0) {
                row[i].statusName = "请假";
            }
            if(row[i].user != null) {
                row[i].userSn = row[i].user.usersn;
                row[i].showname = row[i].user.showname;
            }
        }
    }
    function mydgOnSelectHandler(event, j){
        $("#dopwd").val("");
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
                <td>裁判总数：</td>
                <td><span>${alljudge}(其中已退出${quitjudge}个)</span></td>
                <td>普通裁判数：</td>
                <td><span>${judge}</span></td>
                <td>特邀裁判数：</td>
                <td><span>${invitejudge}</span></td>
                <td>特批裁判数：</td>
                <td><span>${specialjudge}</span></td>
                <td>专职裁判数：</td>
                <td><span>${fulljudge}</span></td>
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
                <td><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态:</label></td>
                <td>
                    <select name="status">
                        <option value="">全部</option>
                        <option value="1">正常</option>
                        <option value="0">请假</option>
                        <option value="13">退出</option>
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
            <th data-options="field:'sn', width:50">裁判号</th>
            <th data-options="field:'showname'">姓名</th>
            <th data-options="field:'typeName'">裁判类型</th>
            <th data-options="field:'majorName'">专业</th>
            <th data-options="field:'judgecount'">评判场数</th>
            <th data-options="field:'complaintcount'">被举报次数</th>
            <th data-options="field:'statusName'">状态</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false canedit=true candel=false]
        [/@a.toolbar]
        [@a.tools /]
    </section>
    [@a.viewsection]
        <tr><td>姓名:</td><td><span set-key="showname"></span></td></tr>
        <tr><td>会员号:</td><td><span set-key="userSn"></span></td></tr>
        <tr><td>裁判号:</td><td><span set-key="sn"></span></td></tr>
        <tr><td>裁判类型:</td><td><span set-key="typeName"></span></td></tr>
        <tr><td>专业:</td><td><span set-key="majorName"></span></td></tr>
        <tr><td>评判场数:</td><td><span set-key="judgecount"></span></td></tr>
        <tr><td>被举报次数:</td><td><span set-key="complaintcount"></span></td></tr>
        <tr><td>通过时间:</td><td><span set-key="createtimeStr"></span></td></tr>
        <tr><td>通过理由：</td><td><span set-key="desc"></span></td></tr>
        <tr><td>状态：</td><td><span set-key="statusName"></span></td></tr>
    [/@a.viewsection]
    [@a.editsection]
    <tr><td><input type="hidden" name="uid" /></td></tr>
    <tr><td>姓名：</td><td><span set-key="showname" ></span></td></tr>
    <tr><td>会员号：</td><td><span set-key="userSn" ></span></td></tr>
    <tr><td>裁判号：</td><td><span set-key="sn" ></span></td></tr>
    <tr><td>类型：</td><td><span set-key="typeName" ></span></td></tr>
    <tr><td>专业：</td><td><span set-key="majorName" ></span></td></tr>
    <tr><td>被举报次数：</td><td><span set-key="complaintcount" ></span></td></tr>
    <tr><td>通过时间：</td><td><span set-key="createtimeStr" ></span></td></tr>
    <tr><td>通过理由：</td><td><span set-key="desc" ></span></td></tr>
    <tr><td>评判场次：</td><td><input class="easyui-validatebox" type="text" name="judgecount" data-options="required:true,validType:'Number'" /></td></tr>
    <tr>
        <td>状态：</td>
        <td><input type="radio" set-key="status" name="status" value="1" /><label>正常</label>&nbsp;
        <input type="radio" set-key="status" name="status" value="0" /><label>请假</label>&nbsp;
        <input type="radio" set-key="status" name="status" value="13" /><label>退出</label>&nbsp;</td>
    </tr>
    <tr><td>操作密码：</td><td><input id="dopwd" class="easyui-validatebox" type="password" name="dopwd" data-options="required:true" /></td></tr>
    [/@a.editsection]
</body>
</html>