[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>讲座报名管理</title>
<script type="text/javascript">
    $(function() {
        $("input[name='gift']").each(function() {
            if ($(this).val() == 1) {
                $(this).attr("checked", true);
            }
        })
    })
    
    function initDGData(row) {
		for (var i in row) {
			if (row[i].status == 10) {
				row[i].statusName = "已支付";
				row[i].isSendBook = "已发";
			} else if (row[i].status == 1) {
			    row[i].statusName = "已支付";
			    row[i].isSendBook = "待发";
			} else {
				row[i].statusName = "未支付";
			}
		}
	}
	function mytoolbarStateHandler(event, j) {
		var list = getSelections(j.dg);
		if (list.length > 0) {
			var row = list[0];
			if (row.status == 10 || row.status == 0) {
				$("#btnsetissendgift").linkbutton("disable");
				if (row.status == 10) {
				    $("#btnsetparticipate").linkbutton("enable");
				} else {
				    $("#btnsetparticipate").linkbutton("disable");
				}
			} else if (row.status == 1) {
				$("#btnsetissendgift").linkbutton("enable");
				$("#btnsetparticipate").linkbutton("enable");
			}
		}
	}

</script>
</head>
<body>
    <section>
        <table>
            <tr>
                <td>讲座申请总数：</td>
                <td><span>${apptotal}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>讲座申请成功数：</td>
                <td><span>${passcount}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>讲座申请失败数：</td>
                <td><span>${nopasstotal}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>申请通过率：</td>
                <td><span>[#if apptotal>0]${((passcount / apptotal)?default(0.00)) * 100}%[/#if]</span></td>
            <tr>
        </table>
        [@a.search]
            <tr>
				<td><label for="sname">姓名:</label></td>
                <td><input id="sname" type="text" name="name" style="width:150px;"/>&nbsp;&nbsp;&nbsp;&nbsp;</td> 
                <td><label for="semail">邮箱:</label></td>
                <td><input id="semail" type="text" name="email" style="width:150px;"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><label for="sstatus">状态: </label></td>
                <td><select id="sstatus" name="status" data-options="panelHeight:200">
                    <option class="easyui-combobox" value="">全部</option>
                        <option value="">全部</option>
                        <option value="10">已发赠品</option>
                        <option value="0">申请失败</option>
                        <option value="1">申请成功</option>
                	</select>
                </td>
			</tr>
			<tr >
				<td><label for="screatetime">申请时间：</label></td>
                <td colspan="3">
	                <input id="screatetime" type="text" name="createtimestart" editable="false" class="easyui-datebox"/>
	                --
	                <input id="screatetime" type="text" name="createtimeend" editable="false" class="easyui-datebox"/>
                </td>
			</tr>
        [/@a.search]
        [@a.datagrid]
        <th data-options="field:'name',width:50">姓名</th>
        <th data-options="field:'mobile',width:50">手机</th>
        <th data-options="field:'email'">邮箱</th>
        <th data-options="field:'lecturename'">讲座名称</th>
        <th data-options="field:'semester'">学期</th>
        <th data-options="field:'statusName'">状态</th>
        <th data-options="field:'isSendBook'">发送赠品</th>
        <th data-options="field:'address',width:100">邮寄地址</th>
        <th data-options="field:'createtimeStr'">申请时间</th>
        [/@a.datagrid]
        
        [@a.toolbar canadd=true canedit=true candel=false]
            <a id="btnsetparticipate" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setparticipatesection', title:'修改学期'}" onclick="editHandler(this, null, '#setparticipatesection')">修改学期</a>
            <a id="btnsetissendgift" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setissendgiftsection', title:'发送赠品'}" onclick="editHandler(this, null, '#setissendgiftsection')">发送赠品</a>
        [/@a.toolbar]
        
        [@a.tools /]
     </section>
    [@a.addsection]
    <tr><td>姓名：</td><td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td></tr>
    <tr><td>手机：</td><td><input class="easyui-validatebox" type="text" name="mobile" data-options="required:true" /></td></tr>
    <tr><td>邮箱：</td><td><input class="easyui-validatebox" type="text" name="email" data-options="required:true" /></td></tr>
    <tr><td>邮寄地址：</td><td><input class="easyui-validatebox" type="text" name="address" data-options="required:true" /></td></tr>
    <tr><td>赠送会员：</td><td>是<input type="radio" name="gift" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;否<input type="radio" name="gift" value="0"/></td></tr>
    [/@a.addsection]
    [@a.editsection]
    <tr><td><input type="hidden" name="uid" /></td></tr>
    <tr><td>讲座名称：</td><td><span set-key="lecturename"/></td></tr>
    <tr><td>学期：</td><td><span set-key="semester"/></td></tr>
    <tr><td>姓名：</td><td><span set-key="name"/></td></tr>
    <tr><td>手机：</td><td><span set-key="mobile"/></td></tr>
    <tr><td>邮箱：</td><td><span set-key="email"/></td></tr>
    <tr><td>邮寄地址：</td><td><input class="easyui-validatebox" type="text" name="address" data-options="required:true" /></td></tr>
    [/@a.editsection]
     [@a.viewsection]
         <tr>
            <td>讲座名称:</td>
            <td><span set-key="lecturename"></span></td>
        </tr>
	    <tr>
            <td>会员sn:</td>
            <td><span set-key="usersn"></span></td>
        </tr>
        <tr>
            <td>姓名:</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>手机:</td>
            <td><span set-key="mobile"></span></td>
        </tr>
        <tr>
            <td>邮箱:</td>
            <td><span set-key="email"></span></td>
        </tr>
        <tr>
            <td>邮寄地址:</td>
            <td><span set-key="address"></span></td>
        </tr>
        <tr>
            <td>学期:</td>
            <td><span set-key="semester"></span></td>
        </tr>
        <tr>
            <td>申请时间:</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
        <tr>
            <td>是否已邮寄赠品:</td>
            <td><span set-key="isSendBook"></span></td>
        </tr>
        <tr>
            <td>状态:</td>
            <td><span set-key="statusName"></span></td>
        </tr>
    [/@a.viewsection]
    
    [@a.editsection id="setparticipatesection" namesubmit="修改学期" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setsemester"]
        <tr>
            <td><input type="hidden" name="uid" /></td>
        </tr>
        <tr>
            <td>讲座名称:</td>
            <td><span set-key="lecturename"></span></td>
        </tr>
        <tr>
            <td>姓名:</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>邮箱:</td>
            <td><span set-key="email"></span></td>
        </tr>
        <tr>
            <td>现学期:</td>
            <td><span set-key="semester"></span></td>
        </tr>
        <tr>
        	<td><label for="year">修改学期</label></td>
            <td>
                <select id="semester" name="semester" style="width:100px;">
                	[#list 1..50 as i]
                		<option id="semester${i}" value="${i}">${i}</option>
                	[/#list]
                </select>
            </td>
        </tr>
    [/@a.editsection]
    
    [@a.editsection id="setissendgiftsection" namesubmit="发送赠品" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setissendgift"]
        <tr>
            <td><input type="hidden" name="uid" /></td>
        </tr>
        <tr>
            <td>讲座名称:</td>
            <td><span set-key="lecturename"></span></td>
        </tr>
        <tr>
            <td>姓名:</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>邮箱:</td>
            <td><span set-key="email"></span></td>
        </tr>
        <tr>
            <td>学期:</td>
            <td><span set-key="semester"></span></td>
        </tr>
        <tr>
            <td>地址:</td>
            <td><span set-key="address"></span></td>
        </tr>
        <tr>
            <td>赠品发放状态:</td>
            <td><span set-key="isSendBook"></span></td>
        </tr>
        <tr>
            <td>订单sn:</td>
            <td><input type="text" class="easyui-validatebox" name="expressnumber" data-options="required:true" /></td>
        </tr>
    [/@a.editsection]
</body>
</html>
