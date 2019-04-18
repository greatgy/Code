[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>用户管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--
    function investFormatter(value){
        if(value != null){
    		return value.typeName;
    	}
    }

    function recFormatter(value){
        if(value != null){
    		return value.typeName;
    	}
    }

    function mergerFormatter(value){
    	if(value != null){
    		return value.typeName;
    	}
    }
    
    function audit(dg){
        var rows = getSelections(dg)
        if(rows.length == 0) {
           alert("请选择要审核的数据");
           return;
        }
        if(rows[0].state != 2) {
            alert("此状态不可审核");
            return;
        }
        $('#auditwin').window('open');
    }
    function subauditform(){
         var rows = getSelections("#dg")
         var ids = new Array();
         for(var i=0; i<rows.length; i++){
            ids.push(rows[i].uid);
         }
         $("input[name='ids']").val(ids);
         $('#auditform').form({    
            onSubmit: function(){
                var content = $("textarea[name='content']").val();
                if(content == ""){
                    alert("备注内容不能为空");
                    return false;
                }  
                var result = $("input[name='result']:checked").val();
                if(typeof(result) == "undefined"){
                    alert("请选择审核结果");
                    return false;
                }
            },    
            success:function(data){
                data = JSON.parse(data);
                if(data.success == true) {
                    closeauditwin();
                    $("#dg").datagrid("reload");
                    alert("审核成功");
                }else {
                    alert("审核失败");
                }
            },
         });
        $('#auditform').submit();
    }
    
    function closeauditwin(){
        $("#auditwin").window('close');
        $("#auditform").form("clear");
    }

    function specialAuditP(dg){
        $.messager.confirm('警告','您确认想要特批此条记录吗？',function(r){    
            if (r){    
                var rows = getSelections(dg);
                if(rows.length == 0) {
                   alert("请选择要特批的数据");
                   return;
                }
                var idarray = new Array();
                for(var i=0;i<rows.length;i++){
                    idarray.push(rows[i].uid);
                }
                $.post("${base}${baseAdminPath}/ajax/tpiuser/specialaudit/offlinepaypermit?ids=" + idarray, function(data){
                    $("#dg").datagrid("reload");
                    $("#auditform").form("clear");
                    alert("审批成功");
                });
            }    
        });  
    }
    
    function specialAuditNP(dg){
        $.messager.confirm('警告','您确认想要特批此条记录吗？',function(r){    
            if (r){    
                var rows = getSelections(dg);
                if(rows.length == 0) {
                   alert("请选择要特批的数据");
                   return;
                }
                var idarray = new Array();
                for(var i=0;i<rows.length;i++){
                    idarray.push(rows[i].uid);
                }
                $.post("${base}${baseAdminPath}/ajax/tpiuser/specialaudit/nopaypermit?ids=" + idarray, function(data){
                    $("#dg").datagrid("reload");
                    $("#auditform").form("clear");
                    alert("审批成功");
                });
            }    
        });  
    }
    
    function initDGData(row) {
    	for(var i in row) {
    		if(row[i].istop) {
    			row[i].istopName = "置顶";
    		} else {
    			row[i].istopName = "未置顶";
    		}
    		if(row[i].isindex) {
    			row[i].isindexName = "是";
    		} else {
    			row[i].isindexName = "否";
    		}
    	}
    }
//-->
</script>
</head>
<body>
    <div id="auditwin" class="easyui-window" title="审核对话框" 
        data-options="iconCls:'icon-save',modal:true,minimizable:false,maximizable:false,closed:true,collapsible:false" style="width:400px;height:210px;padding:10px;">
        <form id="auditform" method="post" action="${base}${baseAdminPath}/ajax/tpiuser/audit">
            <table>
                <tr>
                    <td><input type="hidden" name="ids" value=""></td>
                </tr>
                <tr>
                    <td><label for="semail">备注：</label></td>
                    <td><textarea name="content" style="width:260px;height:50px;"></textarea></td>
                </tr>
                <tr>
                    <td><input type="radio" name="result" value="1"/> 通过</td>
                    <td><input type="radio" name="result" value="0"/> 不通过</td>
                </tr>
                <tr>
                    <td>
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="subauditform()">确定</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeauditwin()">取消</a>                    
                    </td>
                </tr>
            </table>
        </form>    
    </div>
	<section>
        [@a.search]
            <tr>
                <td><label for="semail">邮箱：</label></td>
                <td><input id="semail" type="text" name="email" /></td>
                <td><label for="susername">会员名：</label></td>
                <td><input id="susername" type="text" name="username" /></td>
            </tr>
            <tr>
                <td><label for="susersn">会员号：</label></td>
                <td><input id="susersn" type="text" name="usersn" /></td>
                [#if type == "investment"]
                <td><label for="vtype">投资机构类型：</label></td>
                <td>
                    <select id="vtype" class="easyui-combobox" name="investInfo.type" data-options="panelHeight:150" style="width:100px;">   
                        <option value="">全部</option>
                        [#list map2?keys as key]
                            <option value="${key}">${map2[key]}</option>
                        [/#list]                           
                    </select>
                </td>  
                [#elseif type == "merger"]
                     <td><label for="vtype">并购机构类型：</label></td>
                    <td>
                        <select id="vtype" class="easyui-combobox" name="mergerInfo.type" data-options="panelHeight:200" style="width:120px;">   
                            <option value="">全部</option>
                            [#list map2?keys as key]
                                <option value="${key}">${map2[key]}</option>
                            [/#list]                           
                        </select>
                    </td>  
                [/#if]
            </tr>
            <tr>
                <td><label for="sstatus">账号状态：</label></td>
                <td>
                    <input type="radio" name="state" value=""/> 全部
                    [#list map?keys as key]
                    <input type="radio" name="state" value="${key}"/> ${map[key]}    
                    [/#list]
                    <input type="hidden" name="type" value="${type}"/>
                </td>
            </tr>
            <tr>
            	<td><label for="sstatus">账号状态：</label></td>
            	<td>
            		<input type="radio" name="paytype" value=""/> 全部
            		[#list map3?keys as key]
            		<input type="radio" name="paytype" value="${key}"/> ${map3[key]}
            		[/#list]
            	</td>
            </tr>
            <tr>
                <td>起始提交时间：</td>
                <td><input id="timestart" name="committimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
                <td>结束提交时间：</td>
                <td><input id="timeend" name="committimeend" type="text" data-options="editable:false" class="easyui-datebox" /></td>
            </tr>
        [/@a.search]
        
        [@a.datagrid options="fitColumns:true,resizeHandle:'right',pagination:true,singleSelect:true,method:'get',queryParams: {type: '${type}'}"]
            <th data-options="field:'usersn',width:200">会员号</th>
            <th data-options="field:'username',width:200">会员名</th>
            [#--
            [#if type == "investment"]<th data-options="field:'investInfo',width:300" formatter="investFormatter">投资机构类型</th>[/#if]
            [#if type == "recommend"]<th data-options="field:'recommendInfo',width:300" formatter="recFormatter">推荐机构类型</th>[/#if]
            [#if type == "merger"]<th data-options="field:'mergerInfo',width:300" formatter="mergerFormatter">并购机构类型</th>[/#if]
            --]
            <th data-options="field:'istopName',width:100" >置顶</th>
            <th data-options="field:'isindexName',width:50">首页</th>
            <th data-options="field:'email',width:300">注册邮箱</th>
            <th data-options="field:'stateName',width:300">账号状态</th>
            <th data-options="field:'statusName',width:100">冻结/解冻</th>
            <th data-options="field:'committimeStr',width:200">提交时间</th>
            <th data-options="field:'payTypeName',width:200">用户来源</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false canedit=false]
            <a id="btn" href="" onclick="audit('#dg')" class="easyui-linkbutton" data-options="">审核信息</a>
            [@a.status /]
            [@a.state statefield="istop" actionenable="${base}${baseAdminPath}/ajax/tpiuser/istop/1" actiondisable="${base}${baseAdminPath}/ajax/tpiuser/istop/0" namedisable="取消置顶" nameenable="置顶"/]
            [@a.state statefield="isindex" actionenable="${base}${baseAdminPath}/ajax/tpiuser/isindex/1" actiondisable="${base}${baseAdminPath}/ajax/tpiuser/isindex/0" namedisable="取消推荐首页" nameenable="推荐到首页"/]
            <a id="btn" href="" onclick="specialAuditP('#dg')" class="easyui-linkbutton" data-options="">线下交易特批</a>
            <a id="btn" href="" onclick="specialAuditNP('#dg')" class="easyui-linkbutton" data-options="">未缴费特批</a>
        [/@a.toolbar]
        [@a.tools /]
	</section>
	
    [@a.viewsection]
        <tr>
            <td>会员号：</td>
            <td><span set-key="usersn"></span></td>
        </tr>
        <tr>
            <td>会员名：</td>
            <td><span set-key="username"></span></td>
        </tr>
        <tr>
            <td>投资机构类型：</td>
            <td><span set-key="type"></span></td>
        </tr>
        <tr>
            <td>EMAIL地址：</td>
            <td><span set-key="email"></span></td>
        </tr>
        <tr>
            <td>账号状态：</td>
            <td><span set-key="status"></span></td>
        </tr>
        <tr>
            <td>头像：</td>
            <td>
                <img style="width:200px; height:200px" src="" set-format="${webimg}{0}" set-key="avatarlittle"/>
            </td>
        </tr>
        <tr>
           <td>所在地：</td>
           <td><span set-key="contactinfo.address"></span></td>
        </tr>
        
        [#if type == "investment"]
        <tr>
            <td colspan="2"><h2 style="font-size:1.5em; font-weight:bold;">投资机构信息</h2></td>
        </tr>        
        <tr>
            <td>投资机构介绍：</td>
            <td><span set-key="investInfo.introduce"></span></td>
        </tr>        
        <tr>
            <td>投资机构团队：</td>
            <td><span set-key="investInfo.group"></span></td>
        </tr>        
        <tr>
            <td>投资机构规模：</td>
            <td><span set-key="investInfo.scaleName"></span></td>
        </tr>        
        <tr>
            <td>投资行业：</td>
            <td><span set-key="investInfo.industry"></span></td>
        </tr>        
        <tr>
            <td>投资标准：</td>
            <td><span set-key="investInfo.standard"></span></td>
        </tr>        
        <tr>
            <td>投资限制：</td>
            <td><span set-key="investInfo.limit"></span></td>
        </tr>        
        <tr>
            <td>风险措施：</td>
            <td><span set-key="investInfo.riskmeasure"></span></td>
        </tr>        
        <tr>
            <td>风险措施：</td>
            <td><span set-key="investInfo.riskmeasure"></span></td>
        </tr>        
        [/#if]
        [#if type == "recommend"]
             <tr>
                <td colspan="2"><h2 style="font-size:1.5em; font-weight:bold;">推荐机构信息</h2></td>
            </tr>  
            <tr>
                <td>营业执照（公司）：</td>
                <td><span set-key="recommendInfo.businesslicense"></span></td>
            </tr>        
            <tr>
                <td>法人身份证照（公司）：</td>
                <td><span set-key="recommendInfo.legalpersoncard"></span></td>
            </tr>        
            <tr>
                <td> 盖有机构公章的联系人身份证明（政府机构）：</td>
                <td><span set-key="recommendInfo.sealpersoncard"></span></td>
            </tr>        
            <tr>
                <td>联系人身份证清晰照片(政府机构)：</td>
                <td><span set-key="recommendInfo.personcard"></span></td>
            </tr>        
        [/#if]
        [#if type == "merger"]
            <tr>
                <td colspan="2"><h2 style="font-size:1.5em; font-weight:bold;">并购机构信息</h2></td>
            </tr>
            <tr>
                <td>并购机构介绍：</td>
                <td><span set-key="mergerInfo.introduce"></span></td>
            </tr>        
            <tr>
                <td>并购机构团队：</td>
                <td><span set-key="mergerInfo.group"></span></td>
            </tr>        
            <tr>
                <td>并购机构规模：</td>
                <td><span set-key="mergerInfo.scaleName"></span></td>
            </tr>        
            <tr>
                <td>并购行业：</td>
                <td><span set-key="mergerInfo.industry"></span></td>
            </tr>        
            <tr>
                <td>并购标准：</td>
                <td><span set-key="mergerInfo.standard"></span></td>
            </tr>        
            <tr>
                <td>并购限制：</td>
                <td><span set-key="mergerInfo.limit"></span></td>
            </tr>        
            <tr>
                <td>风险措施：</td>
                <td><span set-key="mergerInfo.riskmeasure"></span></td>
            </tr>        
            <tr>
                <td>风险措施：</td>
                <td><span set-key="mergerInfo.riskmeasure"></span></td>
            </tr>   
        [/#if]
        
       <tr>
            <td colspan="2"><h2 style="font-size:1.5em; font-weight:bold;">联系信息</h2></td>
       </tr>     
       <tr>
            <td>机构电话 ：</td>
            <td><span set-key="contactinfo.tel"></span></td>
        </tr> 
       <tr>
            <td>机构邮箱 ：</td>
            <td><span set-key="contactinfo.email"></span></td>
       </tr> 
       <tr>
            <td>联系人 ：</td>
            <td><span set-key="contactinfo.contactman"></span></td>
       </tr> 
       <tr>
            <td>联系人手机 ：</td>
            <td><span set-key="contactinfo.contactmantel"></span></td>
       </tr> 
       <tr>
            <td>联系人邮箱 ：</td>
            <td><span set-key="contactinfo.contactmanemail"></span></td>
       </tr> 
    [/@a.viewsection]
    
</body>
</html>
