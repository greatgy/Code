[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>用户管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--


function submitpwd(obj) {
    confirm("确定将会员号为"+$("#usersn").val()+"的"+$("#name").val()+"的密码进行修改");
    submitHandler(obj);
}
function mytoolbarStateHandler(event, j) {
    var list = getSelections(j.dg); 
    if (list.length > 0) {
        if (list[0].freeze == 0){
            $("#enable").linkbutton("disable");
        	$("#disable").linkbutton("enable");
        } else {
            $("#enable").linkbutton("enable");
        	$("#disable").linkbutton("disable");
        }
    } else {
         $("#enable").linkbutton("disable");
        $("#disable").linkbutton("disable");
    }
}
$.extend($.fn.validatebox.defaults.rules, {  
    //验证汉字  
    CHS: {  
        validator: function (value) {  
            return /^[\u0391-\uFFE5]+$/.test(value);  
        },  
        message: '只能输入汉字'  
    }, 
    //验证英文
    EGLS: {  
        validator: function (value) {  
            return /^[a-zA-Z]+$/.test(value);  
        },  
        message: '只能输入英文字母'  
    }, 
    //移动手机号码验证  
    mobile: {//value值为文本框中的值  
        validator: function (value) {  
            var reg = /^1[3|4|5|8|9]\d{9}$/;  
            return reg.test(value);  
        },  
        message: '输入手机号码格式不准确.'  
    },  
    //用户密码验证 
    mima: {
        validator: function (value) {  
            var reg =/^(?!\d+$)(?![a-zA-Z]+$)(?![-`=\\\[\];',./\~!@#$%^&*()_+|{}:"<>?]+$)[\da-zA-Z-`=\\\[\];',./\~!@#$%^&*()_+|{}:"<>?]{6,16}$/;  
            return reg.test(value);  
        },  
        message: '由6～16位数字、字母、特殊字符的任意组合组成、字母区分大小写.' 
    },
    //数字  
    Number: {  
        validator: function (value) {  
            var reg =/^[0-9]*$/;  
            return reg.test(value);  
        },  
        message: '请输入正确的金额数字.'  
    }   
})
function showAvatar(avatarlittle) {
  if( avatarlittle != null ){ 
  	return '<img height="20" width="20" src="${userimg}'+avatarlittle+'" />';
  } else {
  	return '<img height="20" width="20" src="${baseimg}/imgs/web/noavatar.png" />';
  }
}
function myButtonHandler(obj, url) {
		var objid = $(obj).attr("id");
		if (objid == "disable") {
		    confirmPostAjax('冻结该选项', '确定要冻结该文章吗？', '${base}${baseAdminPath}/${site}/ajax/consumer/status/0', "post");
		}
		if (objid == "enable") {
		    confirmPostAjax('解冻该选项', '确认要解冻该文章吗？', '${base}${baseAdminPath}/${site}/ajax/consumer/status/1', "post");
		}
		
}
function confirmPostAjax(title, question, url) {
	$.messager.confirm(title, question, function(flag) {
		if (flag) {
			ajaxPostSubmit(url);
		}
	});
}
function ajaxPostSubmit(url) {
	console.log(getSelected(dg).uid);
	$.post(url, {uid : getSelected(dg).uid}, function(result) {
		if (result.success) {
			getDataGrid(dg).datagrid("reload"); // 重新加载数据表格
			resultHandler(result);
		} else {
			resultHandler(result);
		}
	});
}
//重写结果处理方法
function myResultHandler(result){
	if (result.exist) {
		err('添加失败，邮箱已被注册！');
	}
	if(result.addFailed) {
	   err('添加失败，请勿重复添加！');
	}
}
-->
</script> 



</head>
<body>
    <section>
        <table>
            <tr>
                <td>注册用户总数量：</td>
                <td><span>${totalmember}</span></td>
            <tr>
        </table>
        [@a.search]
            <tr>
                <td><label for="sname">名称:</label></td>
                <td><input class="search" id="sname" type="text" name="name" /></td>
            </tr>
            <tr>  
                <td><label for="aisle">用户类型: </label></td>
                <td><select id="aisle" name="aisle">
                    <option value="">全部</option>
                    [#assign chapter = map?keys]
                        [#list chapter as item]
                            <option value="${item}">${map[item]}</option>
                        [/#list]
                	</select>
                </td>
                <td><label for="sstatus">账户状态: </label></td>
                <td><select id="sstatus" name="status">
                        <option value="all">全部</option>
                        [#assign chapter = map1?keys]
                            [#list chapter as item]
                                <option value="${item}">${map1[item]}</option>
                            [/#list]
                    </select>
                </td>
                </tr>
        [/@a.search]
        
        [@a.datagrid ]
            <th data-options="field:'username', width:15">用户名</th>
            <th data-options="field:'aisleName', width:15">用户类型</th>
            <th data-options="field:'account', width:15">账户金额</th>
            <th data-options="field:'avatarlittle', width:15, formatter:showAvatar">头像</th>
            <th data-options="field:'statusName', width:15">账户状态</th>
            <th data-options="field:'lastlogintimeStr', width:15">最后登陆时间</th>
            <th data-options="field:'lastloginip', width:15">最后登陆IP</th>
            <th data-options="field:'type', hidden:'true'"/>
        [/@a.datagrid]
        [@a.toolbar canadd=false  nameedit="特批为会员" candel=false]
            <a id="disable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">冻结</a>
        	<a id="enable" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="myButtonHandler(this, null)">解冻</a>
        [/@a.toolbar]
        [@a.tools /]
    </section>
    
    [@a.editsection]
        <tr>
            <td><input type="hidden" name="uid" /></td>
        </tr>
        <tr>
            <td>特批原由:</td>
            <td><textarea class="easyui-validatebox" style="width:300px;height:60px" name="reason" data-options="required:true" /></textarea>
        </tr>
    [/@a.editsection]
    
    [@a.viewsection]
        <tr>
            <td>用户名:</td>
            <td><span set-key="username"></span></td>
        </tr>
        <tr>   
            <td>性别:</td>
            <td><span set-key="genderName"></span></td>
        </tr>
        <tr>
            <td>供职单位:</td>
            <td><span set-key="company"></span></td>
        </tr>
        <tr>
            <td>担任职务:</td>
            <td><span set-key="job"></span></td>
        </tr>
        <tr>
            <td>个人简介:</td>
            <td><span set-key="summary"></span></td>
        </tr>
        <tr>
            <td>用户类型:</td>
            <td><span set-key="aisleName"></span></td>
        </tr>
        <tr>
            <td>账户金额:</td>
            <td><span set-key="account"></span></td>
        </tr>
        <tr>
           <td>头像：</td>
           <td>
                <img style="width:60px; height:60px" src="" set-format="${userimg}/{0}" set-key="avatarlittle"/>
           </td>
        </tr>
        <tr>
            <td>最后登陆时间:</td>
            <td><span set-key="lastlogintimeStr"></span></td>
        </tr>
         <tr>
            <td>最后登陆IP:</td>
            <td><span set-key="lastloginip"></span></td>
        </tr>
        <tr>
            <td>账户状态:</td>
            <td><span set-key="statusName"></span></td>
        </tr>
        <tr>
            <td>特邀（批）理由:</td>
            <td><span set-key="reason"></span></td>
        </tr>
    [/@a.viewsection]
    
</body>
</html>
