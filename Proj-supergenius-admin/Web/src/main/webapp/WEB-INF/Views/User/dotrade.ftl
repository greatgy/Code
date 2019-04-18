[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
[#import "/WEB-INF/FtlLib/Macro/User.ftl" as u]
<html>
<head>
<title>交易明细管理</title>
<script type="text/javascript" src="${basejs}/js/pages/export.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<section>
		<table>
            <tr>
                <td>总交易金额：</td>
                <td><span>${summoney!?string('0.00')}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>职业经理人培训交易额：</td>
                <td><span>未做</span></td>
                <td>职业道德培训交易额：</td>
                <td><span>未做</span></td>
                <td>企业家培训交易额：</td>
                <td><span>未做</span></td>
                <td>天才数独交易额：</td>
                <td><span>未做</span></td>
            <tr>
        </table>
        
        [@a.search]
            <tr>
                <td>选择查询对象：</td>
	            <td colspan="3">[@u.usersearch name="useruids"/]</td>
            </tr>
            <tr>
                <td><label for="screatetime">交易时间：</label></td>
                <td colspan="3">
	                <input id="screatetime" type="text" name="createtimestart" editable="false" class="easyui-datebox"/>
	                --
	                <input id="screatetime" type="text" name="createtimeend" editable="false" class="easyui-datebox"/>
                </td>
                <td><label for="stotalprice">订单金额区间:</label></td>
                <td>
	                <input id="stotalprice" type="text" name="totalpricestart"/>
	               	--
	                <input id="stotalprice" type="text" name="totalpriceend"/>
                </td>
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
                <td><label for="ssite">来源：</label></td>
                <td>
                    <select id="ssite" class="easyui-combobox" name="site" data-options="panelHeight:200" style="width:150px;">   
                        <option value="">全部</option>
                        [#list map2?keys as mkey]
                            <option value="${mkey}">${map2[mkey]}</option>
                        [/#list]
                    </select> 
                </td>
            </tr>
        [/@a.search]
        
        [@a.datagrid options="rownumbers:true,singleSelect:false,selectOnCheck:true,checkOnSelect:true,fitColumns:true,resizeHandle:'right',pagination:true,method:'get'"]
        	<th data-options="field:'ck000',checkbox:true"></th>
            <th data-options="field:'sn',width:200">流水号</th>
            <th data-options="field:'userName'">会员名</th>
            <th data-options="field:'usersn'">会员号</th>
            <th data-options="field:'email'">邮箱</th>
            <th data-options="field:'siteName'">来源</th>
            <th data-options="field:'typeName'">类型</th>
            <th data-options="field:'money'">金额</th>
            <th data-options="field:'accountcurr'">账户余额</th>
            <th data-options="field:'createtimeStr'">交易时间</th>
        [/@a.datagrid]   
        [@a.toolbar canadd=false canedit=false candel=false]
            <a href="javascript:void(0)" onclick="exportexcel();" class="easyui-linkbutton" id="btexport" data-options="iconCls:'icon-print'">导出</a>
            <form id="export" action="${base}${baseAdminPath}/${site}/trade/exportexcel" method="post" target="_blank">
                <input type="hidden" name="sns" value="" />
            </form>
        [/@a.toolbar]
        [@a.tools /]
	</section>
	
    [@a.viewsection]
        <tr>
            <td>流水号：</td>
            <td><span set-key="sn"></span></td>
        </tr>
        <tr>
            <td>会员名：</td>
            <td><span set-key="userName"></span></td>
        </tr>
        <tr>
            <td>会员号：</td>
            <td><span set-key="usersn"></span></td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td><span set-key="email"></span></td>
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
            <td>金额：</td>
            <td><span set-key="money"></span></td>
        </tr>
        <tr>
            <td>账户余额：</td>
            <td><span set-key="accountcurr"></span></td>
        </tr>
        <tr>
            <td>交易时间：</td>
            <td><span set-key="createtimeStr"></span></td>
        </tr>
    [/@a.viewsection]
    
</body>
</html>
