[#ftl strip_whitespace = true]

[#--
 * judge.ftl
 * 用户查询控件,后台通过你给定的name的值获得选中的用户的uid
 * 如：[@u.judgesearch name="judgeuid" /]:则后台通过judgeuid获得该值
 * 默认name的值为judgeuids1,judgeuids2,judgeuids3...依次递增
 * @author liubin
 * @since 1.0
--]
[#macro judgesearch name = null]
[#if dojudgesearchindex??]
    [#assign dojudgesearchindex = dojudgesearchindex + 1 ]
[#else]
    [#assign dojudgesearchindex = 0 ]
[/#if]
[#local id = "judgesearcher" + dojudgesearchindex]
[#if name == null]
    [#local name = "judges" + dojudgesearchindex]
[/#if]
[#local judgesearchtable = "judgesearchtable" + dojudgesearchindex]
[#local judge = "judge" + dojudgesearchindex]
[#local stype = "stype" + dojudgesearchindex]
[#local smajor = "smajor" + dojudgesearchindex]
[#local sstatus = "sstatus" + dojudgesearchindex]
<script type="text/javascript">
<!--
    var initform;
    /*
     *弹出裁判库
     */
    function ${id}searchJudge() {
        var major = getSelected(dg).major;
        $('.${smajor}').attr("value", major);
        $(".${stype}").empty();
        $(".${stype}").append("<option value=''>全部</option>");
        $.get("${base}${baseAdminPath}/manager/ajax/judge/search_condition", function(data) {
            for(var item in data.type) {
                $(".${stype}").append("<option value="+item+">"+data.type[item]+"</option>");
            }
        });
       $('#${id}').window("open");
       ${id}mySearchHandler();
    }
    
    /**
    *保存选择的用户的uid到页面上去
    */
    function ${id}mySave() {
        var uid = '';
        var name = '';
        var judge = $('#${judge}').datagrid('getSelected');
        if (judge != null) {
            uid = judge.user.uid;
            name = judge.user.showname;
        }
        $('input[name=${name}').val(uid);
        $('input[name=${name}name').val(name);
        ${id}myClose();
    }
    
    function ${id}myClose() {
        $('#${id}').window("close");
    }
    
    /**
    *查询按钮
    */
    function ${id}mySearchHandler() {
        $('#${judge}').datagrid("load", $('#${judgesearchtable}').serializeObj());
    }
    
    /**
    *重置按钮
    */
    function  ${id}myResetForm() {
        initform = $('#${judgesearchtable}').html();
        $('#${judgesearchtable}').html(initform);
        ${id}mySearchHandler();
    }
-->
</script>

<a href="#" class="easyui-linkbutton" onclick="${id}searchJudge()" data-options="iconCls:'icon-search'">选择</a>
<input name="${name}name" style="width:200px" readonly="readonly" />
<input name="${name}" type="hidden" />
<div id="${id}" class="easyui-window" title="选择裁判" style="width:800px;height:600px" data-options="iconCls:'icon-save',modal:true,closed:true,closable:true">
    <div style="margin-left:70px">
        <table id='${judgesearchtable}' class="judgesearchtable">
            <tr>
                <td><label>编号:</label></td>
                <td><input type="text" name="sn"/></td>
                <td><label>姓名:</label></td>
                <td><input type="text" name="name"/></td>
                <td><input type="hidden" class="${smajor}" name="major"/></td>
                <td><label>类型：</label></td>
                <td><select class="${stype}" name="type">
                    <option value="">全部</option>
                    </select>
                </td>
            </tr>
        </table>
    </div>
    <div class="formbtns" style="margin-left:-10px">
            <a href="" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="${id}mySearchHandler()">查询</a> 
            <a href="" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="${id}myResetForm()">重置</a>
    </div>
    <table id="${judge}" class="easyui-datagrid" style="width:800px;height:380px;border:solid 1px red;" title="裁判选择列表" data-options="url:'${base}${baseAdminPath}/manager/ajax/challenge/specifyjudge', fitColumns:true, method:'get', pagination:true, singleSelect:true">
        <thead>
            <tr>
                <th width="16%" data-options="field:'sn'">裁判号</th>
                <th width="14%" data-options="field:'username'">姓名</th>
                <th width="14%" data-options="field:'typeName'">裁判类型</th>
                <th width="14%" data-options="field:'majorName'">专业</th>
                <th width="14%" data-options="field:'judgecount'">评判场数</th>
                <th width="14%" data-options="field:'complaintcount'">被举报次数</th>
                <th width="14%" data-options="field:'statusName'">状态</th>
            </tr>
        </thead>
    </table>
    <div style="margin-left:320px; margin-top:20px; margin-bottom:30px">
        <a href="#" onclick="${id}mySave()" class="easyui-linkbutton">确定</a>
        <a href="#" onclick="${id}myClose()" class="easyui-linkbutton">取消</a>
    </div>
</div>
[/#macro]