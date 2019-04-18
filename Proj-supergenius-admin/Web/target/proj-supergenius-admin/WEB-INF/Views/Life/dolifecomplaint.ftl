[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>举报管理</title>
<script type="text/javascript">
<!--
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
            if (row[i].kind == 0) {
                row[i].kindName = "文章";
            }
            if (row[i].kind == 1) {
                row[i].kindName = "视频";
            }
            if (row[i].kind == 2) {
                row[i].kindName = "话题";
            }
            if (row[i].kind == 3) {
                row[i].kindName = "问答";
            }
            if (row[i].refname != null || row[i].refname != '') {
                if (row[i].refurl != null) {
                    row[i].refname = '<a href="' + row[i].refurl + '" target="_blank">' + row[i].refname + '</a>';
                } else {
                    row[i].refname = row[i].refname;
                }    
            }
        }
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
            </tr>
        </table>
       
        [@a.search]
            <tr>
                <td><label>状态：</label></td>
                <td>
                    <input type="radio" id="statusal" name="status" value="" checked/><label for="statusal">全部</label>
                    <input type="radio" id="statusall" name="status" value="12" /><label for="statusall">待处理</label>
                    <input type="radio" id="statusone" name="status" value="0" /><label for="statusone">举报失败</label>
                    <input type="radio" id="statustwo" name="status" value="1" /><label for="statustwo">举报成功</label>
                </td>
            </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'refname',width:50">举报对象</th>
            <th data-options="field:'fromusername',width:20">举报人</th>
            <th data-options="field:'typeName',width:20">举报事由</th>
            <th data-options="field:'kindName',width:20">举报类型</th>
            <th data-options="field:'cataloguename',width:30">所属板块</th>
            <th data-options="field:'createtimeStr',width:20">举报时间</th>
            <th data-options="field:'statusName',width:10">审批状态</th>
            <th data-options="field:'updatetimeStr',width:20">审批时间</th>
        [/@a.datagrid]
       
        [@a.toolbar canadd=false canedit=false candel=false]
         <a id="btnsetresult" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setresult', title:'审核'}" onclick="editHandler(this, null, '#setresult')">设置举报结果</a>
        [/@a.toolbar]
        [@a.tools /]
    </section>
   
    [@a.editsection id="setresult" namesubmit="提交" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setresult"]
	    <tr><td><input type="hidden" name="uid" /></td></tr>
	    <tr><td>举报人：</td><td><span set-key="fromusername" ></span></td></tr>
	    <tr><td>举报对象：</td><td><span set-key="refname" ></span></td></tr>
	    <tr><td>举报类型：</td><td><span set-key="kindName" ></span></td></tr>
	    <tr><td>举报理由：</td><td><span set-key="typeName" ></span></td></tr>
	    <tr><td>凭证：</td><td><img src="" set-format="${webimg}{0}" set-key="file" style="width: 600px; height: 400px;"/></td></tr>
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
	[@a.viewsection]
		<tr>
            <td>举报人:</td>
            <td><span set-key="fromusername"></span></td>
        </tr>
        <tr>
            <td>举报对象:</td>
            <td><span set-key="refname"></span></td>
        </tr>
        <tr>
            <td>举报类型:</td>
            <td><span set-key="kindName"></span></td>
        </tr>
        <tr>
            <td>举报理由:</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr>
            <td>举报描述:</td>
            <td><span set-key="desc"></span></td>
        </tr>
        <tr>
            <td>凭证:</td>
            <td><img src="" set-format="${webimg}{0}" set-key="file" style="width: 600px; height: 400px;"/></td>
        </tr>
        <tr>
            <td>举报时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
        <tr>
            <td>审批状态:</td>
            <td><span set-key="statusName"></span></td>
        </tr>
        <tr>
            <td>操作人:</td>
            <td><span set-key="adminname"></span></td>
        </tr>
	[/@a.viewsection]
</body>
</html>