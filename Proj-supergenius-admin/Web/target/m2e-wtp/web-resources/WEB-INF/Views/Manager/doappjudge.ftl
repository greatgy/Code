[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>裁判申请管理</title>
<script type="text/javascript">
<!--
    function initDGData(row) {
        $("#btnsetstage").linkbutton("disable");
        $("#btnsetcheck").linkbutton("disable");
        $("#btnsetresult").linkbutton("disable");
        for(var i in row) {
            if(row[i].isonline) {
                row[i].isonline = "在线视频";
            } else {
                row[i].isonline = "线下";
            }
            var fileList = "";
            var file2List = "";
            if(row[i].fileList.length == 0) {
                fileList = "暂无";
            } else {
                for(var j in row[i].fileList) {
                    if(row[i].fileList[j] != "" && row[i].fileList[j] != null) {
                        var name = row[i].fileList[j].substring(row[i].fileList[j].lastIndexOf("/") + 1);
                        fileList = fileList + "<a href='javascript:;' onclick='downloadFile(\"" + row[i].fileList[j] + "\")'>" + name + "</a>&nbsp&nbsp";
                    }
                }
            }
            if(row[i].file2List.length == 0) {
                file2List = "暂无";
            } else {
                for(var k in row[i].file2List) {
                    if(row[i].file2List[k] != "" && row[i].file2List[k] != null) {
                        var name = row[i].file2List[k].substring(row[i].file2List[k].lastIndexOf("/") + 1);
                        file2List = file2List + "<a href='javascript:;' onclick='downloadFile(\"" + row[i].file2List[k] + "\")'>" + name + "</a>&nbsp&nbsp";
                    }
                }
            } 
            
            if(fileList == "") {
                fileList = "暂无";
            }
            if(file2List == "") {
                 file2List = "暂无";
            }
            row[i].fileList = fileList;
            row[i].file2List = file2List;
            row[i].lesscount = 50 - row[i].topiccount;
            if(row[i].file != null) {
                var name = row[i].file.substring(row[i].file.lastIndexOf("/") + 1);
                row[i].file = "<a href='javascript:;' onclick='downloadFile(\"" + row[i].file + "\")'>"+ name +"</a>&nbsp&nbsp";
            } else {
                row[i].file = "暂无";
            }
            if(row[i].providetimes != null) {
                var providetimes = "";
                var times = row[i].providetimes.split(",");
                for(var m in times) {
                    if(m == 0) {
                        providetimes = providetimes + "<input type='radio' checked='checked' name='providetime' value='" + times[m] + "'/>" + times[m] + "&nbsp;&nbsp;";
                    } else {
                        providetimes = providetimes + "<input type='radio' name='providetime' value='" + times[m] + "'/>" + times[m] + "&nbsp;&nbsp;";
                        if(m == 4) {
                            providetimes = providetimes + "<br/>";
                        }
                    }
                }
                row[i].providetimes = providetimes;
            }
        }
    }
    function downloadFile(name) {
        var path = name.substring(0, name.lastIndexOf("/") + 1);
        var filename = name.substring(name.lastIndexOf("/") + 1, name.lastIndexOf("."));
        var suffix = name.substring(name.lastIndexOf("."));
        var url = "${base}${baseAdminPath}/${site}/${channel}/download" + "?name=" + filename + "&suffix=" + suffix + "&path=" + path;
        gourl(url);
    }
    function mydgOnSelectHandler(event, j){
        if(j.data.stage == "0") {
            $("#btnsetstage").linkbutton("enable");
        } else {
            $("#btnsetstage").linkbutton("disable");
        }
        if(j.data.stage == "7" || j.data.stage == "6" || j.data.stage == "4") {
            $("#btnsetresult").linkbutton("enable");
        } else {
            $("#btnsetresult").linkbutton("disable");
        }
        if(j.data.stage == "3") {
            $("#btnsetcheck").linkbutton("enable");
        } else {
            $("#btnsetcheck").linkbutton("disable");
        }
        $("#resultpass").attr("checked","checked");
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
                <td>裁判申请总数：</td>
                <td><span>${appcount}</span></td>
                <td>通过数：</td>
                <td><span>${passcount}</span></td>
            </tr>
        </table>
       
        [@a.search]
            <tr>
                <td><label>关键字:</label></td>
                <td><input type="text" name="keyword"/></td>
                <td><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请阶段:</label></td>
                <td><select name="type">
                    <option value="">全部</option>
                        [#list stages?keys as item]
                            <option value="${item}">${stages[item]}</option>
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
            </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'userSn', width:50">会员号</th>
            <th data-options="field:'username'">学员姓名</th>
            <th data-options="field:'typeName'">裁判类型</th>
            <th data-options="field:'majorName'">专业</th>
            <th data-options="field:'createtimeStr'">申请时间</th>
            <th data-options="field:'stageName'">状态</th>
        [/@a.datagrid]
       
        [@a.toolbar canadd=false canedit=false candel=false]
        <a id="btnsetstage" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setstage', title:'审核裁判申请'}" onclick="editHandler(this, null, '#setstage')">审核裁判申请</a>
        <a id="btnsetcheck" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setcheck', title:'审核裁判题目'}" onclick="editHandler(this, null, '#setcheck')">审核裁判题目</a>
        <a id="btnsetresult" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setresult', title:'设置面试结果'}" onclick="editHandler(this, null, '#setresult')">设置面试结果</a>
        [/@a.toolbar]
        [@a.tools /]
    </section>
    [@a.viewsection]
        <tr><td>会员号:</td><td><span set-key="userSn"></span></td></tr>
        <tr><td>学员姓名:</td><td><span set-key="username"></span></td></tr>
        <tr><td>申请专业:</td><td><span set-key="majorName"></span></td></tr>
        <tr><td>学员级别:</td><td><span set-key="majorlevelName"></span></td></tr>
        <tr><td>已获证书:</td><td><span set-key="certificate"></span></td></tr>
        <tr><td>申请附言:</td><td><span set-key="desc"></span></td></tr>
        <tr><td>裁判题目:</td><td><span set-key="fileList"></span></td></tr>
        <tr><td>反馈材料：</td><td><span set-key="file2List"></span></td></tr>
        <tr><td>面试方式：</td><td><span set-key="isonline"></span></td></tr>
        <tr><td>申请时间：</td><td><span set-key="createtimeStr"></span></td></tr>
        <tr><td>确定的面试时间：</td><td><span set-key="providetime"></span></td></tr>
        <tr><td>状态：</td><td><span set-key="stageName"></span></td></tr>
    [/@a.viewsection]
    [@a.editsection id="setstage" namesubmit="提交审核" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setstage"]
        <tr><td></td><td><input type="hidden" class="easyui-validatebox" name="uid" data-options="required:true" set-key="uid" /></td></tr>
        <tr><td>会员号：</td><td><span set-key="userSn"></span></td></tr>
        <tr><td>学员姓名：</td><td><span set-key="username"></span></td></tr>
        <tr><td>专业：</td><td><span set-key="majorName"></span></td></tr>
        <tr><td>证书：</td><td><span set-key="certificate"></span></td></tr>
        <tr><td>审核结果：</td><td><input type="radio" name="result" value="1" />通过&nbsp;<input type="radio" name="result" value="0" />不通过</td></tr>
        <tr><td>理由：</td><td><input type="text" class="easyui-validatebox" name="reason" data-options="required:true" /></td></tr>
    [/@a.editsection]
    [@a.editsection id="setcheck" namesubmit="提交审核" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setcheck" canuploadfile=true]
        <tr><td></td><td><input type="hidden" class="easyui-validatebox" name="uid" data-options="required:true" set-key="uid" /></td></tr>
        <tr><td>文件：</td><td><span set-key="file"></span></td></tr>
        <tr><td>反馈材料：</td><td><input class="easyui-filebox" type="file" name="filedata" /></td></tr>
        <tr><td>面试时间：</td><td set-key="providetimes"></td></tr>
        <tr><td>审核结果：</td><td>已通过数：<span set-key="topiccount"></span>&nbsp;&nbsp;&nbsp;&nbsp;还需题目数：<span set-key="lesscount"></span>&nbsp;&nbsp;&nbsp;&nbsp;本次通过数：<input type="text" class="easyui-validatebox" name="passcount" data-options="required:true,validType:'Number'" /></td></tr>
        <tr><td>理由：</td><td><textarea rows=4 cols=70 name="reason" data-options="required:true" class="textarea easyui-validatebox"></textarea></td></tr>
    [/@a.editsection]
    [@a.editsection id="setresult" namesubmit="提交结果" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setresult" canuploadfile=true]
        <tr><td></td><td><input type="hidden" class="easyui-validatebox" name="uid" data-options="required:true" set-key="uid" /></td></tr>
        <tr><td>面试结果：</td><td><input type="radio" id="resultpass" value="1" name="result" />通过&nbsp;<input type="radio" value="0" name="result" />不通过</td></tr>
        <tr><td>理由：</td><td><textarea rows=4 cols=50 name="reason" data-options="required:true" class="textarea easyui-validatebox"></textarea></td></tr>
        <tr><td>反馈材料：</td><td><input class="easyui-filebox" type="file" name="filedata" /></td></tr>
    [/@a.editsection]
</body>
</html>