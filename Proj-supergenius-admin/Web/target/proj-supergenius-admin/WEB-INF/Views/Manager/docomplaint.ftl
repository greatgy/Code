[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>举报管理</title>
<script type="text/javascript">
<!--
    var managerBaseRootPath = "${managerbase}";
    function initDGData(row) {
        for (var i in row) {
            if (row[i].status == 12) {
                row[i].statusName = "待处理";
            }
            if (row[i].status == 0) {
                row[i].statusName = "举报失败";
            }
            if (row[i].status == 1) {
                row[i].statusName = "举报成功";
            }
            if (row[i].fromuser != null) {
                row[i].fromuserShowname = '<a href="' + managerBaseRootPath + '/u/' + row[i].fromuser.oid + '" target="_blank">' + row[i].fromuser.showname + '</a>';
            }
            if (row[i].touser != null) {
                row[i].touserShowname = '<a href="' + managerBaseRootPath + '/u/' + row[i].touser.oid + '" target="_blank">' + row[i].touser.showname + '</a>';
            }
            if (row[i].refname != null) {
                if (row[i].refurl != null) {
                    row[i].refName = '<a href="' + row[i].refurl + '" target="_blank">' + row[i].refname + '</a>';
                } else {
                    row[i].refName = row[i].refname;
                }    
            }
            if (row[i].fromuseruid != '' || row[i].fromuseruid != null) {
                var complaintlog1 = '<table border="1" cellpadding="5" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;"><tr style="text-align: center"><td width="250px">相关事件</td><td width="100px">举报人类型</td><td width="50px">举报类型</td><td width="150px">举报结果</td><td width="150px">举报时间</td></tr>';
                for (var k in row) {
                    if (row[k].fromuseruid == row[i].fromuseruid || row[k].touseruid == row[i].fromuseruid) {
                        var type = '';
                        if (row[k].fromuseruid == row[i].fromuseruid) {
                            type = '举报人';
                        } else {
                            type = '被举报人';
                        }
                        complaintlog1 = complaintlog1 + '<tr style="text-align: center;"><td width="250px">' + row[k].refname + '</td><td width="100px">' + type + '</td><td width="50px">' + row[k].typeName + '</td><td width="150px">' + row[k].result + '</td><td width="150px">' + row[k].createtimeStr + '</td></tr>';
                    }
                }
                row[i].complaintfrom = complaintlog1 + '</table>';
            }
            if (row[i].touseruid != '' || row[i].touseruid != null) {
                var complaintlog2 = '<table border="1" cellpadding="5" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;"><tr style="text-align: center"><td width="250px">相关事件</td><td width="100px">举报人类型</td><td width="50px">举报类型</td><td width="150px">举报结果</td><td width="150px">举报时间</td></tr>';
                for (var k in row) {
                    if (row[k].touseruid == row[i].touseruid || row[k].fromuseruid == row[i].touseruid) {
                        var type = '';
                        if (row[k].touseruid == row[i].touseruid) {
                            type = '被举报人';
                        } else {
                            type = '举报人';
                        }
                        complaintlog2 = complaintlog2 + '<tr style="text-align: center"><td width="250px">' + row[k].refname + '</td><td width="100px">' + type + '</td><td width="50px">' + row[k].typeName + '</td><td width="150px">' + row[k].result + '</td><td width="150px">' + row[k].createtimeStr + '</td></tr>';
                    }
                }
                row[i].complaintto = complaintlog2 + '</table>';
            }
            if (row[i].tousertype == "134217728") {
                row[i].tousertypeName = "答辩";
                row[i].reftypename = "答辩";
            }
            if (row[i].tousertype == "268435456") {
                row[i].tousertypeName = "挑战";
                row[i].reftypename = "挑战";
            }
            if(row[i].file != null) {
                var name = row[i].file.substring(row[i].file.lastIndexOf("/") + 1);
                row[i].file = "<a href='javascript:;' onclick='downloadFile(\"" + row[i].file + "\")'>"+ name +"</a>&nbsp&nbsp";
            } else {
                row[i].file = "暂无";
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
    
    function mytoolbarStateHandler(event, j) {
        var row = getSelected(j.dg); 
        if (row && row.status == 12) {
            $("#btnsetresult").linkbutton("enable");
        } else {
            $("#btnsetresult").linkbutton("disable");
        }
    }
    
//-->
</script>
</head>
<body>
    <section>
        <table>
            <tr>
                <td>总举报数：</td>
                <td><span>${total}</span></td>
                <td>举报专家次数：</td>
                <td><span>${count}</span></td>
                <td>举报裁判次数：</td>
                <td><span>${count2}</span></td>
                <td>成功举报数：</td>
                <td><span>${success}</span></td>
            </tr>
        </table>
       
        [@a.search]
            <tr>
                <td><label>搜索框:</label></td>
                <td><input type="text" name="keyword"/></td>
                <td><label>专业：</label></td>
                <td>
                    <select name="major">
                        <option value="">全部</option>
                            [#list majors?keys as item]
                                <option value="${item}">${majors[item]}</option>
                            [/#list]
                    </select>
                </td>
                <td><label>类型：</label></td>
                <td><select name="type">
                    <option value="">全部</option>
                        [#list types?keys as item]
                            <option value="${item}">${types[item]}</option>
                        [/#list]
                    </select>
                </td>
                <td><label>状态：</label></td>
                <td>
                    <select name="status">
                        <option value="">全部</option>
                        <option value="12">待处理</option>
                        <option value="0">举报失败</option>
                        <option value="1">举报成功</option>
                    </select>
                </td>
            </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'fromuserShowname'">举报人</th>
            <th data-options="field:'touserShowname'">被举报人</th>
            <th data-options="field:'refName'">相关场次</th>
            <th data-options="field:'typeName'">举报事由</th>
            <th data-options="field:'reftypename'">类型</th>
            <th data-options="field:'createtimeStr'">举报时间</th>
            <th data-options="field:'statusName'">审批状态</th>
            <th data-options="field:'updatetimeStr'">审批时间</th>
        [/@a.datagrid]
       
        [@a.toolbar canadd=false canedit=false candel=false]
         <a id="btnsetresult" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setresult', title:'设置举报结果'}" onclick="editHandler(this, null, '#setresult')">设置举报结果</a>
        [/@a.toolbar]
        [@a.tools /]
    </section>
    
    [@a.viewsection]
        <tr>
            <td style="font-weight:bold;">举报信息</td>
        </tr>
        <tr>
           <td>举报场次：</td>
           <td>
                <span set-key="refName"></span>
           </td>
        </tr>
        <tr>
           <td>类型：</td>
           <td>
                <span set-key="reftypename"></span>
           </td>
        </tr>
        <tr>
           <td>举报时间：</td>
           <td>
                <span set-key="createtimeStr"></span>
           </td>
        </tr>
        <tr>
           <td>举报事由：</td>
           <td>
                <span set-key="typeName"></span>
           </td>
        </tr>
        <tr>
           <td>附言：</td>
           <td>
                <span set-key="desc"></span>
           </td>
        </tr>
        <tr>
           <td>凭证：</td>
           <td>
                <span set-key="file"></span>
           </td>
        </tr>
        <tr>
           <td>审批状态：</td>
           <td>
                <span set-key="statusName"></span>
           </td>
        </tr>
        <tr>
           <td>处理办法：</td>
           <td>
                <span set-key="result"></span>
           </td>
        </tr>
        <tr>
           <td>审批时间：</td>
           <td>
                <span set-key="updatetimeStr"></span>
           </td>
        </tr>

        <tr>
            <td style="font-weight:bold;">举报人信息</td>
        </tr>
        <tr>
            <td>姓名:</td>
            <td><span set-key="fromuserShowname"></span></td>
        </tr>
        <tr>
            <td>会员号:</td>
            <td><span set-key="fromuser.usersn"></span></td>
        </tr>
        <tr>
            <td>学员级别:</td>
            <td><span set-key="reflevelname"></span></td>
        </tr>
        <tr>
            <td>历史举报记录:</td>
            <td><span set-key="complaintfrom"></span></td>
        </tr>
        <tr>
            <td style="font-weight:bold;">被举报人信息</td>
        </tr>
        <tr>
            <td>姓名:</td>
            <td><span set-key="touserShowname"></span></td>
        </tr>
        <tr>
            <td>级别:</td>
            <td><span set-key="reflevelname2"></span></td>
        </tr>
        <tr>
            <td>执教场次:</td>
            <td><span set-key="refcount"></span></td>
        </tr>
        <tr>
            <td>历史被举报记录:</td>
            <td><span set-key="complaintto"></span></td>
        </tr>
    [/@a.viewsection]
    
    [@a.editsection id="setresult" namesubmit="设置举报结果" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setresult"]
    <tr><td><input type="hidden" name="uid" /></td></tr>
    <tr><td>举报场次：</td><td><span set-key="refname" ></span></td></tr>
    <tr><td>举报人：</td><td><span set-key="fromuser.showname" ></span></td></tr>
    <tr><td>被举报人：</td><td><span set-key="touser.showname" ></span></td></tr>
    <tr><td>举报理由：</td><td><span set-key="typeName" ></span></td></tr>
    <tr><td>凭证：</td><td><span set-key="file" ></span></td></tr>
    <tr><td>举报时间：</td><td><span set-key="createtimeStr" ></span></td></tr>
    <tr><td>举报处理结果:</td>
        <td>
            <input type="radio" name="status" value="1"/>举报有效
            <input type="radio" name="status" value="0"/>举报无效
        </td>
    </tr>
    <tr>
        <td>添加审批结果:</td>
        <td>
            <textarea class="easyui-validatebox" name="result" data-options="required:true" cols="110" rows="10"></textarea>
        </td>
    </tr>
    [/@a.editsection]

</body>
</html>