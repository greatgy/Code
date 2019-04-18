[#ftl strip_whitespace=true]

<#--
 * User.ftl
 * 用户查询控件,后台通过你给定的name的值获得选中的用户的uid
 * 如：[@u.usersearch name="useruid" /]:则后台通过useruid获得该值
 * 默认name的值为useruids1,useruids2,useruids3...依次递增
 * @author XieMing
 * @since 1.0
-->
[#macro usersearch name=null]
[#if dousersearchindex??]
    [#assign dousersearchindex = dousersearchindex + 1 ]
[#else]
    [#assign dousersearchindex = 0 ]
[/#if]
[#local id="usersearcher" + dousersearchindex]
[#if name==null]
    [#local name="useruids" + dousersearchindex]
[/#if]
[#local usersearchtable="usersearchtable" + dousersearchindex]
[#local userlist="userlist" + dousersearchindex]
[#local schannelfrom="schannelfrom"+dousersearchindex]
[#local sstatus="sstatus"+dousersearchindex]
<script type="text/javascript">
<!--
	var initform;
	var uids = '';
	var names = '';
	
    $(function() {
        $.get("${base}${baseAdminPath}/user/ajax/member/search_condition", function(data) {
            for(var item in data.userchannel) {
            $(".${schannelfrom}").append("<option value="+item+">"+data.userchannel[item]+"</option>");
            }
            for(var item in data.userstate) {
                $(".${sstatus}").append("<option value="+item+">"+data.userstate[item]+"</option>");
            }
        });
        
        $('#${userlist}').datagrid({
        	onCheck: function(index, row) {
        		statusWarning(row);
        		${id}Check(index, row);
        	},
        	onUncheck: function(index, row) {
        		${id}UnCheck(row);
        	},
        	onLoadSuccess: function(data) {
        		var allRows = data.rows;
        		for (var r = 0; r < allRows.length; r++) {
        			if (uids.indexOf(allRows[r].uid) >= 0) {
        				$('#${userlist}').datagrid("checkRow", r);
        			}
        		}
        	}
        })
        
        
        	
    });
    
    function ${id}searchUser() {
    	$("#selecteduser").html("");
       $('#${id}').window("open");
       ${id}mySearchHandler();
    }
    
    function ${id}Check(index, userCheck) {
        if (uids.indexOf(userCheck.uid) < 0) {
        	uids += "," + userCheck.uid;
        	names += "," + userCheck.name;
        	$("#selecteduser").append("<a href='javascript:;' index='" + index + "' onClick='removeUser(this);' uid='" + userCheck.uid + "' style='text-decoration:none; margin-right:15px;'>" + userCheck.name + "</a>");
        }
    }
    
    function ${id}UnCheck(userCheck) {
        uids = uids.replace("," + userCheck.uid, '');
        names = names.replace("," + userCheck.name, '');
        $("#selecteduser").find("a").each(function() {
        	if ($(this).attr("uid") == userCheck.uid) {
        		$(this).remove();
        	}
        });
    }
    
    function removeUser(objid) {
    	var obj = $(objid);
    	uids = uids.replace("," + obj.attr("uid"), '');
        names = names.replace("," + obj.text(), '');
        $('#${userlist}').datagrid('unselectRow', obj.attr("index"));
        obj.remove();
    }
    
     /**
    *保存选择的用户的uid到页面上去
    */
    function ${id}mySave() {
        $('input[name=${name}').val(uids.substring(1));
        $('input[name=${name}name').val(names.substring(1));
        ${id}myClose();
    }
    
    function ${id}myClose() {
    	uids = '';
		names = '';
        $('#${id}').window("close");
    }
    
    function statusWarning(row) {
    	if (row.status != 1) {
    		alert("您选择的用户： " + row.name + " 状态不是正常");
    	}
    }
    
    /**
    *查询按钮
    */
    function ${id}mySearchHandler() {
        $('#${userlist}').datagrid("load", $('#${usersearchtable}').serializeObj());
    }
    
    /**
    *重置按钮
    */
    function  ${id}myResetForm() {
        initform = $('#${usersearchtable}').html();
        $('#${usersearchtable}').html(initform);
        ${id}mySearchHandler();
    }
-->
</script>

<a href="#" class="easyui-linkbutton" onclick="${id}searchUser()" data-options="iconCls:'icon-search'">选择</a>
<input name="${name}name" style="width:200px" readonly="readonly">
<input name="${name}" type="hidden" />
<div id="${id}" class="easyui-window" title="选择用户" style="width:800px;height:600px" data-options="iconCls:'icon-save',modal:true,closed:true,closable:true">
    <div style="margin-left:70px">
        <table id='${usersearchtable}' class="usersearchtable">
            <tr>
                <td><label for="sname">名称:</label>
                <input type="text" name="name" style="width:200px" /></td>
                <td><label for="susersn">会员号:</label>
                <input type="text" name="usersn" style="width:188px" /></td>
                <td>
                    <label for="schannelfrom">用户类型: </label>
                    <select class="${schannelfrom}" name="channelfrom">
                        <option value="">全部</option>
                    </select>
                </td>
                
            </tr>
            <tr>
                <td><label for="semail">邮箱:</label>
                <input type="text" name="email" style="width:200px" /></td>
                <td><label for="sisStudent">职业道德培训学员：</label>
                    <input type="radio" name="sisStudent" value=""/>全部
                    <input type="radio" name="sisStudent" value="true"/>是
                    <input type="radio" name="sisStudent" value="false"/>否
                </td>
                <td>
                    <label for="sstatus">账户状态: </label>
                    <select class="${sstatus}" name="status">
                        <option value="">全部</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>账户余额（区间）:</td>
                <td><input name="accountstart" type="text" style="width:136px" />———————</td>
                <td><input name="accountend" type="text" style="width:136px" /></td>
            </tr>
        </table>
    </div>
    <div class="formbtns" style="margin-left:-10px">
            <a href="" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="${id}mySearchHandler()">查询</a> 
            <a href="" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="${id}myResetForm()">重置</a>
    </div>
    <table id="${userlist}" class="easyui-datagrid" style="width:786px;height:250px;border:solid 1px red;" title="用户选择列表" data-options="url:'${base}${baseAdminPath}/user/ajax/member/list',fitColumns:true,method:'get',pagination:true">
        <thead>
            <tr>
                <th data-options="field:'ck000',checkbox:true"></th>
                <th data-options="field:'usersn',width:100">会员号</th>
                <th data-options="field:'name'">姓名</th>
                <th data-options="field:'email',width:100,align:'right'">邮箱</th>
                <th data-options="field:'channelfromName',width:100">用户类型</th>
                <th data-options="field:'statusName'">账户状态</th>
                <th data-options="field:'account'">账户金额</th>
            </tr>
        </thead>
    </table>
    <div style="margin-left:320px; margin-top:10px;">
        <a href="#" onclick="${id}mySave()" class="easyui-linkbutton">完成</a>
        <a href="#" onclick="${id}myClose()" class="easyui-linkbutton">取消</a>
    </div>
    <div style="margin-top:10px;" id="selected">
        <p style="font-size:15px;">已选中：</p>
        <div id="selecteduser" style="width:800px;">
	        [#--选中的user--]
        </div>
    </div>
</div>
[/#macro]