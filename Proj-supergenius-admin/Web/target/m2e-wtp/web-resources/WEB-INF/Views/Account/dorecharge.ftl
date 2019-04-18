[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>支付管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${basejs}/js/pages/account.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<section>
		<table>
			<tr>
				<td>充值总额：</td>
				<td><span>${rechargemoney}</span></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>会员费总额：</td>
				<td><span>${userfeemoney}</span></td>
			<tr>
		</table>
		
		[@a.search]
            <tr>
                <td><label for="susername">用户名：</label></td>
                <td><input id="susername" type="text" name="username"/></td>
                <td><label for="saccountsn">流水号：</label></td>
                <td><input id="saccountsn" type="text" name="accountsn"/></td>
            </tr>
            <tr>
                <td><label for="sbank">银行：</label></td>
                <td>
               		<select id="sbank" class="easyui-combobox" name="bank" data-options="panelHeight:200" style="width:150px;">   
                        <option value="">全部</option>
                        [#list map?keys as mkey]
                            <option value="${mkey}">${map[mkey]}</option>
                        [/#list]
                    </select> 
                </td>
                <td><label for="ssite">来源：</label></td>
                <td>
                	<select id="ssite" class="easyui-combobox" name="site" data-options="panelHeight:200" style="width:100px;">   
                        <option value="">全部</option>
                        [#list map1?keys as mkey]
                            <option value="${mkey}">${map1[mkey]}</option>
                        [/#list]
                    </select>
                </td>
                <td><label for="stype">类型：</label></td>
                <td>
					<select id="stype" class="easyui-combobox" name="type" data-options="panelHeight:200" style="width:100px;">   
                        <option value="">全部</option>
                        [#list map2?keys as mkey]
                            <option value="${mkey}">${map2[mkey]}</option>
                        [/#list]
                    </select> 
                </td>
            </tr>
            <tr>
            	<td>起始时间：</td>
                <td><input id="timestart" name="createtimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
           		<td>结束时间：</td>
           		<td><input id="timeend" name="createtimeend" type="text" data-options="editable:false" class="easyui-datebox" /></td>
            </tr>
        [/@a.search]

        [@a.datagrid options="rownumbers:true,singleSelect:false,selectOnCheck:true,checkOnSelect:true,fitColumns:true,resizeHandle:'right',pagination:true,method:'get'"]
        	<th data-options="field:'ck000',checkbox:true"></th>
            <th data-options="field:'accountsn',width:200">流水号</th>
            <th data-options="field:'username',width:200">会员</th>
            <th data-options="field:'typeName',width:200">类型</th>
            <th data-options="field:'siteName',width:200">来源</th>
            <th data-options="field:'useremail',width:200">email</th>
            <th data-options="field:'money',width:200">金额</th>
            <th data-options="field:'bankName',width:200">银行</th>
            <th data-options="field:'stateName',width:200">状态</th>
            <th data-options="field:'createtimeStr',width:200">操作时间</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false canedit=false candel=false]
        	<a href="javascript:void(0)" onclick="exportexcel();" class="easyui-linkbutton" id="btexport" data-options="iconCls:'icon-print'">导出</a>
        	<form id="export" action="${base}${baseAdminPath}/account/exportexcel" method="post" target="_blank">
        		<input type="hidden" name="sns" value="" />
        	</form>
        [/@a.toolbar]
        [@a.tools /]
	</section>
    [@a.viewsection]
        <tr>
            <td>流水号：</td>
            <td><span set-key="accountsn"></span></td>
        </tr>
        <tr>
            <td>会员：</td>
            <td><span set-key="username"></span></td>
        </tr>
        <tr>
            <td>来源：</td>
            <td><span set-key="siteName"></span></td>
        </tr>
        <tr>
            <td>类型：</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr>
            <td>银行：</td>
            <td><span set-key="bankName"></span></td>
        </tr>
        <tr>
            <td>金额：</td>
            <td><span set-key="money"></span>&nbsp;元</td>
        </tr>
         <tr>
            <td>状态：</td>
            <td><span set-key="stateName"></span></td>
        </tr>
        <tr>
            <td>操作时间：</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
    [/@a.viewsection]
</body>
</html>
