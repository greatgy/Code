[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>用户管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
<!--
function mytoolbarStateHandler(event, j) {
    $("#btnsetjudge").linkbutton("disable");
    $("#btnsetexport").linkbutton("disable");
    $("#btnsetaccount").linkbutton("disable");
    $("#btnsetpwd").linkbutton("disable");
    getBtnEdit().linkbutton("disable");
    var list = getSelections(j.dg);
    var row = list[0];
    if (list.length != 0) {
        $("#btnsetjudge").linkbutton("enable");
        if(row.statusName == "等待交费") {
            getBtnEdit().linkbutton("enable");
        }
        /*if(268435456 & parseInt(row.type)) {
            $("#btnsetjudge").linkbutton("disable");
        }
        if(134217728 & parseInt(row.type)) {
            $("#btnsetexport").linkbutton("disable");
        }*/
        $("#btnsetexport").linkbutton("enable");
        $("#btnsetaccount").linkbutton("enable");
        $("#btnsetpwd").linkbutton("enable");
    }
}

function submitpwd(obj) {
    confirm("确定将会员号为"+$("#usersn").val()+"的"+$("#name").val()+"的密码进行修改");
    submitHandler(obj);
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
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>会员总数量：</td>
                <td><span>${totalhuiyuan}</span></td>
                <td>缴费会员总数量：</td>
                <td><span>${payuser}</span></td>
                <td>特批会员数量：</td>
                <td><span>${specialuser}</span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>特邀嘉宾数量：</td>
                <td><span>${inviteuser}</span></td>
            <tr>
        </table>
        [@a.search]
            <tr>
                <td><label for="sname">名称:</label></td>
                <td><input class="search" id="sname" type="text" name="name" /></td>
                <td><label for="semail">EMAIL:</label></td>
                <td><input class="search" id="semail" type="text" name="email" /></td>
                <td><label for="susersn">会员号:</label></td>
                <td><input class="search" id="susersn" type="text" name="usersn" /></td>
            </tr>
            <tr>
                <td>账户余额（区间）:</td>
                <td colspan="3"><input id="accountstart" name="accountstart" type="text" />
              	 --
                <input id="accountend" name="accountend" type="text" /></td>
            </tr> 
            <tr>  
                <td><label for="schannelfrom">用户类型: </label></td>
                <td><select id="schannelfrom" name="channelfrom">
                    <option value="">全部</option>
                    [#assign chapter = map?keys]
                        [#list chapter as item]
                            <option value="${item}">${map[item]}</option>
                        [/#list]
                	</select>
                </td>
                <td><label for="sstatus">账户状态: </label></td>
                <td><select id="sstatus" name="status">
                        <option value="1">正常</option>
                        <option value="all">全部</option>
                        [#assign chapter = map1?keys]
                            [#list chapter as item]
                                <option value="${item}">${map1[item]}</option>
                            [/#list]
                    </select>
                </td>
                <td><label for="sisStudent">职业道德学员：</label></td>
                <td>
                    <input type="radio" name="sisStudent" value=""/>全部
                    <input type="radio" name="sisStudent" value="true"/>是
                    <input type="radio" name="sisStudent" value="false"/>否
                </td>
                </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'usersn', width:100">会员号</th>
            <th data-options="field:'name'">用户名</th>
            <th data-options="field:'channelfromName'">用户类型</th>
            <th data-options="field:'email'">EMAIL</th>
            <th data-options="field:'account'">账户金额</th>
            <th data-options="field:'avatarlittle', width:160, formatter:showAvatar">头像</th>
            <th data-options="field:'statusName'">账户状态</th>
            <th data-options="field:'lastlogintimeStr'">最后登陆时间</th>
            <th data-options="field:'lastloginip'">最后登陆IP</th>
            <th data-options="field:'type', hidden:'true'"/>
        [/@a.datagrid]
        [@a.toolbar nameadd="添加特邀嘉宾" nameedit="特批该用户" candel=false]
            <a id="btnsetjudge" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setjudgesection', title:'设置裁判'}" onclick="editHandler(this, null, '#setjudgesection')">设置裁判</a>
            <a id="btnsetexport" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setexportsection', title:'设置专家'}" onclick="editHandler(this, null, '#setexportsection')">设置专家</a>
            <a id="btnsetaccount" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setaccountsection', title:'设置余额'}" onclick="editHandler(this, null, '#setaccountsection')">设置余额</a>
            <a id="btnsetpwd" href="" class="easyui-linkbutton hd" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#setpwdsection', title:'设置密码'}" onclick="editHandler(this, null, '#setpwdsection')">设置密码</a>
            [@a.status/]
        [/@a.toolbar]
        [@a.tools /]
    </section>
    
    [@a.addsection]
        <tr>
            <td>邮箱:</td>
            <td><input type="text" class="easyui-validatebox" name="email" data-options="required:true,validType:'email'" /></td>
        </tr>
        <tr>
            <td>登录密码:</td>
            <td><input type="text" class="easyui-validatebox" name="password" data-options="required:true,validType:'mima'" /></td>
        </tr>
        <tr>
            <td>支付密码:</td>
            <td><input type="text" class="easyui-validatebox" name="paypwd" data-options="required:true,validType:'mima'" /></td>
        </tr>
        <tr>
            <td>英文名:</td>
            <td><input type="text" class="easyui-validatebox" name="nickname" data-options="required:true,validType:'EGLS'" /></td>
        </tr>
        <tr>
            <td>真实姓名:</td>
            <td><input type="text" class="easyui-validatebox" name="name" data-options="required:true,validType:'CHS'" /></td>
        </tr>
        <tr>
            <td>会员名:</td>
            <td>
                <select id="cc" class="easyui-combobox" name="showname" style="width:80px;" data-options="panelHeight:50">   
                    <option value="chinese" selected="selected">中文名</option>
                    <option value="english">英文名</option>
                </select>  
            </td>
        </tr>
        <tr>
            <td>性别:</td>
            <td>
                <select id="cc" class="easyui-combobox" name="gender" style="width:80px;" data-options="panelHeight:50">   
                    <option value="1" selected="selected">男</option>
                    <option value="0">女</option>
                </select>  
            </td>
        </tr>
        <tr>
            <td>手机号:</td>
            <td><input type="text" class="easyui-validatebox" name="mobile" data-options="validType:'mobile'" /></td>
        </tr>
        <tr>
            <td>嘉宾简介:</td>
            <td><input type="text" name="summary" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>添加理由:</td>
            <td><input type="text" name="reason" data-options="required:true" /></td>
        </tr>
    [/@a.addsection]
    
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
            <td>会员号:</td>
            <td><span set-key="usersn"></span></td>
        </tr>
        <tr>
            <td>中文名:</td>
            <td><span set-key="name"></span></td>
        </tr>
        <tr>
            <td>英文名:</td>
            <td><span set-key="nickname"></span></td>
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
            <td><span set-key="channelfromName"></span></td>
        </tr>
        <tr>
            <td>EMAIL:</td>
            <td><span set-key="email"></span></td>
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
    
    [@a.editsection id="setjudgesection" namesubmit="设置裁判" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setjudge"]
        <tr>
            <td><input type="hidden" name="uid" /></td>
        </tr>
        <tr>
            <td>学员名:</td>
            <td><span set-key="name" name="showname" /></td>
        </tr>
        <tr>
            <td>邮箱:</td>
            <td><span set-key="email" name="showname" /></td>
        </tr>
        <tr>
            <td>会员号:</td>
            <td><span set-key="usersn" name="usersn" /></td>
        </tr>
        <tr>
            <td>选择专业:</td>
            <td>
            	<select name="major">
                    <option value="">--请选择--</option>
                    [#assign major = map2?keys]
                        [#list major as item]
                            <option value="${item}">${map2[item]}</option>
                        [/#list]
                </select>
            </td>
        </tr>
    [/@a.editsection]
    
    [@a.editsection id="setexportsection" namesubmit="设置专家" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setexport"]
        <tr>
            <td><input type="hidden" name="uid" /></td>
        </tr>
        <tr>
            <td>学员名:</td>
            <td><span set-key="name" name="showname" /></td>
        </tr>
        <tr>
            <td>邮箱:</td>
            <td><span set-key="email" name="email" /></td>
        </tr>
        <tr>
            <td>会员号:</td>
            <td><span set-key="usersn" name="usersn" /></td>
        </tr>
        <tr>
            <td>选择专家级别:</td>
            <td>
            	<select name="expertlevel">
            		<option value="">--请选择--</option>
            		[#assign level = map3?keys]
            			[#list level as item]
            				<option value="${item}">${map3[item]}</option>
            			[/#list]
            	</select>
            </td>
        </tr>
        <tr>
            <td>选择专业:</td>
            <td>
            	<select name="major">
                    <option value="">--请选择--</option>
                    [#assign major = map2?keys]
                        [#list major as item]
                            <option value="${item}">${map2[item]}</option>
                        [/#list]
                </select>
            </td>
        </tr>
    [/@a.editsection]
    
    [@a.editsection id="setaccountsection" namesubmit="设置余额" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setaccount"]
        <tr>
            <td><input type="hidden" name="uid" /></td>
        </tr>
        <tr>
            <td>当前余额:</td>
            <td><span set-key="account" name="account" /></td>
        </tr>
        <tr>
            <td>金额:</td>
            <td><input class="easyui-validatebox" type="text" name="number" data-options="required:true,validType:'Number'" /></td>
        </tr>
        <tr>
            <td>
                <label for="isplus">操作：</label>
            </td>
            <td>
                <input type="radio" name="isplus" value="false"/>减
                <input type="radio" name="isplus" value="true"/>加
            </td>
        </tr>
        <tr>
            <td>操作理由:</td>
            <!--<td><textarea class="easyui-validatebox" name="desc" style="width:300px;height:60px" data-options="required:true" /></td>-->
            <td><input class="easyui-validatebox" name="desc" data-options="required:true" /></td>
        </tr>
    [/@a.editsection]
    
    [@a.editsection id="setpwdsection" namesubmit="设置密码" action="${base}${baseAdminPath}/${site}/ajax/${channel}/setpwd" onclicksubmit="submitpwd(this)"]
        <tr>
            <td><input type="hidden" name="uid" /></td>
        </tr>
        <tr>
            <td><input type="hidden" id="name" name="name" /></td>
        </tr>
        <tr>
            <td><input type="hidden" id="usersn" name="usersn" /></td>
        </tr>
        <tr>
            <td>新密码:</td>
            <td><input class="easyui-validatebox" type="text" name="pwd" data-options="required:true,validType:'mima'"/></td>
        </tr>
    [/@a.editsection]
    
</body>
</html>
