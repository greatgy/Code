[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>用户管理</title>
<script type="text/javascript">
<!--
//-->
</script>
</head>
<body>
	<section>
        [@a.search]
            <tr>
                <td><label for="sfromusername">评论人姓名：</label></td>
                <td><input id="sfromusername" type="text" name="fromusername" /></td>
                <td><label for="scontent">内容关键字：</label></td>
                <td><input id="scontent" type="text" name="content" /></td>
            </tr>
            <tr>
                <td>起始发布时间：</td>
                <td><input id="timestart" name="createtimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
                <td>结束发布时间：</td>
                <td><input id="timeend" name="createtimeend" type="text" data-options="editable:false" class="easyui-datebox" /></td>
            </tr>
            <tr>
                <td><label for="schannel">评论对象类型：</label></td>
                <td>
                    <input type="radio" name="channel" value=""/>全部
                    <input type="radio" name="channel" value="0"/>项目点评
                    <input type="radio" name="channel" value="1"/>团队点评
                </td>
                <td><label for="susername">状态：</label></td>
                <td>
                    <input type="radio" name="status" value="1"/>全部
                    <input type="radio" name="status" value="1"/>正常
                    <input type="radio" name="status" value="0"/>冻结
                </td>
            </tr>
            <tr>
                <td><label for="sfromtitle">评论对象名：</label></td>
                <td><input id="sfromtitle" type="text" name="fromtitle" /></td>
            </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'channelName',width:200">评论对象类型</th>
            <th data-options="field:'fromtitle',width:300">评论对象</th>
            <th data-options="field:'fromusername',width:100" >评论人</th>
            <th data-options="field:'content',width:100">评论内容</th>
            <th data-options="field:'statusName',width:200">冻结/解冻</th>
            <th data-options="field:'createtimeStr',width:250">评论时间</th>
        [/@a.datagrid]
        [@a.toolbar canadd=false canedit=false]
            [@a.status /]
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
