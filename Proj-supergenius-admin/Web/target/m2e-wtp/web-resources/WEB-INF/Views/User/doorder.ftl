[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
[#import "/WEB-INF/FtlLib/Macro/User.ftl" as u]
<html>
<head>
<title>订单管理</title>
<script type="text/javascript" src="${basejs}/js/pages/export.js"></script>
<script type="text/javascript">
<!--
function initDGData(row) {
    for(var i in row) {
        row[i].userName = row[i].user.name;
    }
}
//-->
</script>
</head>
<body>
    <section>
        <table>
            <tr>
                <td>订单总数：</td>
                <td><span>${totalorder}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>视频订单：</td>
                <td><span>${videoorder}</span></td>
                <td>门票订单：</td>
                <td><span>${ticketorder}</span></td>
            <tr>
        </table>
       
        [@a.search]
            <tr>
	            <td>选择查询对象：</td>
	            <td colspan="3">[@u.usersearch name="useruids" /]</td>
                <td><label for="ssn">订单编号：</label></td>
                <td><input class="search" id="ssn" type="text" name="sn"/></td>
                <td><label for="sname">订单名称：</label></td>
                <td><input class="search" id="sname" type="text" name="name"/></td>
            </tr>
            <tr>
                <td><label for="stotalprice">订单金额区间:</label></td>
                <td><input id="stotalprice" type="text" name="totalpricestart"/></td>
                <td>--</td>
                <td><input id="stotalprice" type="text" name="totalpriceend"/></td>
                <td><label for="screatetime">订单时间：</label></td>
                <td><input id="screatetime" type="text" name="createtimestart" editable="false" class="easyui-datebox"/></td>
                <td>--</td>
                <td><input id="screatetime" type="text" name="createtimeend" editable="false" class="easyui-datebox"/></td>
            </tr>
            <tr>
                <td><label for="stype">订单类型：</label></td>
                <td>
                    <select id="stype" class="easyui-combobox" name="type" data-options="panelHeight:200" style="width:150px;">   
                        <option value="">全部</option>
                        [#list map?keys as mkey]
                            <option value="${mkey}">${map[mkey]}</option>
                        [/#list]
                    </select> 
                </td>
            </tr>
        [/@a.search]
        
        [@a.datagrid options="rownumbers:true,singleSelect:false,selectOnCheck:true,checkOnSelect:true,fitColumns:true,resizeHandle:'right',pagination:true,method:'get'"]
            <th data-options="field:'ck000',checkbox:true"></th>
            <th data-options="field:'sn',width:200">订单编号</th>
            <th data-options="field:'name',width:200">订单名称</th>
            <th data-options="field:'userName',width:200">购买者</th>
            <th data-options="field:'typeName',width:200">订单类型</th>
            <th data-options="field:'totalprice',width:200">价格</th>
            <th data-options="field:'createtimeStr',width:200">下单时间</th>
        [/@a.datagrid]
       
        [@a.toolbar canadd=false canedit=false candel=false]
            <a href="javascript:void(0)" onclick="exportexcel();" class="easyui-linkbutton" id="btexport" data-options="iconCls:'icon-print'">导出</a>
            <form id="export" action="${base}${baseAdminPath}/${site}/order/exportexcel" method="post" target="_blank">
                <input type="hidden" name="sns" value="" />
            </form>
        [/@a.toolbar]
        [@a.tools /]
    </section>
    [@a.viewsection]
        <tr>
            <td>订单编号：</td>
            <td><span set-key="sn"></span></td>
        </tr>
        <tr>
            <td>订单名称：</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>购买者：</td>
            <td><span set-key="userName"></span></td>
        </tr>
        <tr>
            <td>订单类型：</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr>
            <td>价格：</td>
            <td><span set-key="totalprice">&nbsp;元</span></td>
        </tr>
        <tr>
            <td>下单时间：</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
    [/@a.viewsection]
</body>
</html>
