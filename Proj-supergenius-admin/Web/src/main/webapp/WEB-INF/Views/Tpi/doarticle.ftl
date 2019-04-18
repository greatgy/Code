[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>文章管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function(){
		$(".channel").find("input[value='1']").click(function(){
			$(".type2").find("input[name='ntype']").attr("checked",null);
			$(".type1").attr("style",'display:none');
			$(".type2").attr("style",'display:block');
        });
        $(".channel").find("input[value='0']").click(function(){
        	$(".type1").find("input[name='ctype']").attr("checked",null);
        	$(".type1").attr("style",'display:block');
			$(".type2").attr("style",'display:none');
        });
    });
    
    function cccFormatter(value,row){
    	if (row.channel == 0) {
    		return row.ctype;
    	} else if (row.channel == 1) {
    		return row.ntype;
    	} else {
			return "未选定";    	
    	}
    }
</script> 	
</head>
<body>
	<section>
        [@a.search]
            <tr>
                <td><label for="schannel">板块：</label></td>
                <td>
                    <select id="schannel" class="easyui-combobox" name="channel" data-options="panelHeight:100" style="width:100px;">   
                        <option value="">全部</option>
                        [#list map?keys as mkey]
                            <option value="${mkey}">${map[mkey]}</option>
                        [/#list]
                    </select>  
                </td>
                <td><label for="sctype">类别(案例分析)：</label></td>
                <td>
                    <select id="sctype" class="easyui-combobox" name="ctype" data-options="panelHeight:200" style="width:100px;">   
                        <option value="">全部</option>
                        [#list map1?keys as mkey]
                            <option value="${mkey}">${map1[mkey]}</option>
                        [/#list]
                    </select>  
                </td>
                <td><label for="sntype">类别(并购动态)：</label></td>
                <td>
                    <select id="sntype" class="easyui-combobox" name="ntype" data-options="panelHeight:200" style="width:100px;">   
                        <option value="">全部</option>
                        [#list map2?keys as mkey]
                            <option value="${mkey}">${map2[mkey]}</option>
                        [/#list]
                    </select>  
                </td>
            </tr>
            <tr>
                <td><label for="sauthor">作者：</label></td>
                <td><input id="sauthor" type="text" name="author" /></td>
                <td><label for="sorigin">来源：</label></td>
                <td><input id="sorigin" type="text" name="origin" /></td>
            </tr>
            <tr>
                <td><label for="sistop">置顶：</label></td>
                <td>
                    <input type="radio" name="istop" value=""/> 全部
                    <input type="radio" name="istop" value="1"/>是
                    <input type="radio" name="istop" value="0"/>否
                </td>
                <td><label for="sispublic">公开：</label></td>
                <td>
                    <input type="radio" name="ispublic" value=""/> 全部 
                    <input type="radio" name="ispublic" value="1"/>是
                    <input type="radio" name="ispublic" value="0"/>否
                </td>
            </tr>
            <tr>
                <td><label for="stitle">标题：</label></td>
                <td><input id="stitle" type="text" name="title" /></td>
                <td><label for="sstatus">状态：</label></td>
                <td>
                    <input type="radio" name="status" value=""/> 全部
                    <input type="radio" name="status" value="1"/>已发布
                    <input type="radio" name="status" value="0"/>未发布
                </td>
            </tr>
            <tr>
                <td>起始发布时间：</td>
                <td><input id="timestart" name="createtimestart" type="text" data-options="editable:false" class="easyui-datebox" /></td>
                <td>结束发布时间：</td>
                <td><input id="timeend" name="createtimeend" type="text" data-options="editable:false" class="easyui-datebox" /></td>
            </tr>
        [/@a.search]
        
        [@a.datagrid]
            <th data-options="field:'title',width:500">标题</th>
            <th data-options="field:'author',width:100">作者</th>
            <th data-options="field:'channelName',width:200">版块</th>
            <th data-options="field:'typeName',width:200">分类</th>
            <th data-options="field:'origin',width:200">文章来源</th>
            <th data-options="field:'statusName',width:100">状态</th>
            <th data-options="field:'createtimeStr',width:150">发布日期</th>
        [/@a.datagrid]
        [@a.toolbar]
            [@a.status namedisable="取消发布" nameenable="发布"/]
            [@a.state statefield="istop" actionenable="${base}${baseAdminPath}/ajax/article/istop/1" actiondisable="${base}${baseAdminPath}/ajax/article/istop/0" namedisable="取消置顶" nameenable="置顶"/]
            [@a.state statefield="ispublic" actionenable="${base}${baseAdminPath}/ajax/article/ispublic/1" actiondisable="${base}${baseAdminPath}/ajax/article/ispublic/0" namedisable="取消公开" nameenable="公开"/]
        [/@a.toolbar]
        [@a.tools /]
	</section>
	[@a.addsection]
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>作者：</td>
            <td><input class="easyui-validatebox" type="text" name="author" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>来源：</td>
            <td><input class="easyui-validatebox" type="text" name="origin" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>标题图：</td>
            <td>[@a.imgupload multiple=false imgpath="/imgs/webdata/finance/debate" /]</td>
        </tr>
        <tr>
            <td>板块：</td>
            <td>
      		 <div class="channel">
                [#list map?keys as mkey]
                    <input type="radio" id="rdchannel_${mkey_index}" name="channel" value="${mkey}"/> <label for="rdchannel_${mkey_index}">${map[mkey]}</label>&nbsp;
                [/#list]
      		 </div>
            </td>
        </tr>
        <tr class="type">
            <td>分类：</td>
            <td class="type1" style="display:none">
            	[#list map1?keys as mkey]
                    <input type="radio" id="rdtype_${mkey_index}" name="ctype" value="${mkey}" /> <label for="rdtype_${mkey_index}">${map1[mkey]}</label>&nbsp;
                [/#list]
            </td>
            <td class="type2" style="display:none">
                [#list map2?keys as mkey]
                    <input type="radio" id="rdtype_${mkey_index}" name="ntype" value="${mkey}" /> <label for="rdtype_${mkey_index}">${map2[mkey]}</label>&nbsp;
                [/#list]
            </td>
        </tr>
        <tr>
            <td>是否公开：</td>
            <td>
                <input id="rdpublic" type="radio" name="ispublic" value="1" checked="checked" /> <label for="rdpublic">公开</label> &nbsp;
                <input id="rdpublic1" type="radio" name="ispublic" value="0"/> <label for="rdpublic1">不公开</label> &nbsp;
            </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="content"/]
            </td>
        </tr>
        
    [/@a.addsection]

    [@a.editsection]
        <tr>
            <td><input type="hidden" name="uid" /></td>
            <td><input type="hidden" name="adminuid" /></td>
        </tr>
        <tr>
            <td>标题：</td>
            <td><input class="easyui-validatebox" type="text" name="title" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>作者：</td>
            <td><input class="easyui-validatebox" type="text" name="author" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>来源：</td>
            <td><input class="easyui-validatebox" type="text" name="origin" data-options="required:true" /></td>
        </tr>
        <tr>
            <td>标题图：</td>
            <td>
                <img style="width:200px; height:200px" src="" set-format="${webimg}{0}" set-key="imglittle"/>
                [@a.imgupload multiple=false imgpath="/imgs/webdata/finance/debate" /]
            </td>
        </tr>
        <tr>
            <td>板块：</td>
            <td>
                <div class="channel">
                [#list map?keys as mkey]
                    <input type="radio" id="rdchannel_${mkey_index}" name="channel" value="${mkey}"/> <label for="rdchannel_${mkey_index}">${map[mkey]}</label>&nbsp;
                [/#list]
      		 	</div>
            </td>
        </tr>
        <tr class="type">
            <td>分类：</td>
            <td class="type1" style="display:none">
            	[#list map1?keys as mkey]
                    <input type="radio" id="rdtype_${mkey_index}" name="ctype" value="${mkey}" /> <label for="rdtype_${mkey_index}">${map1[mkey]}</label>&nbsp;
                [/#list]
            </td>
            <td class="type2" style="display:none">
                [#list map2?keys as mkey]
                    <input type="radio" id="rdtype_${mkey_index}" name="ntype" value="${mkey}" /> <label for="rdtype_${mkey_index}">${map2[mkey]}</label>&nbsp;
                [/#list]
            </td>
        </tr>
        <tr>
            <td>是否公开：</td>
            <td>
                <input type="radio" name="public" value="0"/> 不公开 &nbsp;
                <input type="radio" name="public" value="1"/> 公开 &nbsp;
            </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td>
                [@a.ckeditor name="content"/]
            </td>
        </tr>
    [/@a.editsection]
    
    [@a.viewsection]
        <tr>
            <td>标题：</td>
            <td><span set-key="title"></span></td>
        </tr>
        <tr>
            <td>作者：</td>
            <td><span set-key="author"></span></td>
        </tr>
        <tr>
            <td>来源：</td>
            <td><span set-key="origin"></span></td>
        </tr>
        <tr>
            <td>板块：</td>
            <td><span set-key="channelName"></span></td>
        </tr>
        <tr>
            <td>分类：</td>
            <td><span set-key="typeName"></span></td>
        </tr>
        <tr>
            <td>是否公开：</td>
            <td><span set-key="ispublic"></span></td>
        </tr>
        <tr>
           <td>标题图：</td>
           <td>
                <img style="width:200px; height:200px" src="" set-format="${webimg}{0}" set-key="imglittle"/>
           </td>
        </tr>
        <tr>
            <td>内容：</td>
            <td><span set-key="content"></span></td>
        </tr>
    [/@a.viewsection]
    
</body>
</html>
