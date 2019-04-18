[#ftl strip_whitespace=true]
[#--
 * Admin.ftl
 * 管理后台提供页面的类库
 * @author architect.bian
 * @since 1.0
 --]
[#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /]
[#--
 * 查询控件
 * action 查询提交地址
 * datagrid datagrid的id，默认为"#dg"
 * @author architect.bian
 * @since 1.0
 --]
[#macro search action, datagrid="#dg"]
	[#if !action?? && channel??]
		[#if site??]
			[#local action="${base}${baseAdminPath}/${site}/ajax/${channel}/list" ]
		[#else]
			[#local action="${base}${baseAdminPath}/ajax/${channel}/list" ]
		[/#if]
	[/#if]
	<form class="searchform" method="get" action="${action}" >
		<table class="normal">
			[#nested]
		</table>
	<div class="formbtns">
		<a href="" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchHandler(this, '${datagrid}')">查询</a>
		<a href="" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="resetForm(this)">重置</a>
	</div>
	</form>
	<script type="text/javascript">
    <!--
	$(":input[class='search']").keydown(function(){  
        if(event.keyCode==13){  
            $(".searchform .formbtns").children("a").eq(0).click();
            return false;
        }
    });
    //-->
    </script>
[/#macro]

[#--
 * DataGrid控件
 * id id
 * title 标题
 * url 加载数据的url
 * options easyui的options
 * toolbar toolbar
 * tools datagrid右上方的按钮
 * datagrid datagrid的id，默认为"#dg"
 * @author architect.bian
 * @since 1.0
 --]
[#macro datagrid id=null, title=null, url=null, options=null, toolbar="#toolbar", tools="", rownumbers=false]
	[#if title==null && channelname??]
		[#local title="${channelname}列表"]
	[/#if]
	[#if url==null && channel??]
		[#if site??]
			[#local url="${base}${baseAdminPath}/${site}/ajax/${channel}/list"]
		[#else]
			[#local url="${base}${baseAdminPath}/ajax/${channel}/list"]
		[/#if]
	[/#if]
	[#if options==null]
		[#local options="fitColumns:true,resizeHandle:'right',pagination:true,singleSelect:true,method:'get'"]
	[/#if]
	[#if id==null && tools==""]
		[#local tools="#tools"]
	[/#if]
	<table id="${id!"dg"}" [#if id != null] class="easyui-datagrid"[/#if] title="${title}" url="${url}" toolbar="${toolbar}" tools="${tools}" data-options="${options}" rownumbers=${rownumbers}>
        <thead>
            <tr>
                [#nested]
            </tr>
        </thead>
    </table>
[/#macro]

[#-- 
 * datagrid的toolbar控件
 * id id
 * urldel 删除所调用的url
 * onclickadd 添加按钮的onclick的值
 * addsection 添加是breadcrumb所对应的section，默认是#addsection
 * canadd 是否可以添加
 * titleadd 添加breadcrumb时crumb的title
 * nameadd 添加按钮的名称
 * @author architect.bian
 * @since 1.0
 --]
[#macro toolbar id="toolbar", urldel=null, onclickadd="addHandler(this)", onclickedit="editHandler(this)", onclickdel=null, onclickview="viewHandler(this)", addsection="#addsection", editsection="#editsection", viewsection="#viewsection" canadd=true, canedit=true, candel=true, canview=true, titleadd=null, titleedit=null, titleview="查看详情", nameadd="添加", nameedit="编辑", namedel="删除", nameview="查看详情"]
	[#if urldel==null && channel??]
		[#if site??]
			[#local urldel="${base}${baseAdminPath}/${site}/ajax/${channel}/delete"]
		[#else]
			[#local urldel="${base}${baseAdminPath}/ajax/${channel}/delete"]
		[/#if]
	[/#if]
	[#if onclickdel==null]
		[#local onclickdel="deleteHandler('${urldel}')"]
	[/#if]
	[#if titleadd==null && channelname??]
		[#local titleadd="添加${channelname}"]
	[/#if]
	[#if titleedit==null && channelname??]
		[#local titleedit="编辑${channelname}"]
	[/#if]
	
	<div id="${id}">
		[#if canadd]
   		<a id="btnadd" href="" class="easyui-linkbutton" data-options="iconCls:'icon-add'" data-crumbshow="{selector:'${addsection}', title:'${titleadd}'}" onclick="${onclickadd}">${nameadd}</a>
   		[/#if]
   		[#if canedit]
   		<a id="btnedit" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'${editsection}', title:'${titleedit}'}" onclick="${onclickedit}">${nameedit}</a>
   		[/#if]
   		[#if candel]
		<a id="btndel" href="" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="${onclickdel}">${namedel}</a>
		[/#if]
   		[#if canview]
		<a id="btnview" href="" class="easyui-linkbutton" data-options="iconCls:'icon-tip'" data-crumbshow="{selector:'${viewsection}', title:'${titleview}'}" onclick="${onclickview}">${nameview}</a>
		[/#if]
		[#nested]
	</div>
	<script type="text/javascript">
    <!--
    	var topTo = 0;
    	$(function() {
			topTo = $("#toolbar").offset().top;
		});
		$(window).scroll(function() {
			var top = $(this).scrollTop();
			if(top > topTo){
				$("#toolbar").css("position", "fixed");
				$("#toolbar").css("z-index", 1);
				$("#toolbar").css("top", 0);
			}else{
				$("#toolbar").css("position", "static");
				$("#toolbar").css("top", topTo);
			}
		});
	//-->
    </script>
[/#macro]

[#--
 * status控件
 * urldisable 冻结提交地址
 * urlenable 解冻提交地址
 * namedisable 冻结按钮的名称
 * nameenable 解冻按钮的名称
 * @author architect.bian
 * @since 1.0
 --]
[#macro status urldisable=null, urlenable=null, namedisable="冻结", nameenable="解冻" , candisable=true, canenable=true, style="width:500px; height:240px; padding:10px 20px;"]
	[#if urldisable==null && channel??]
		[#if site??]
			[#local urldisable="${base}${baseAdminPath}/${site}/ajax/${channel}/status/0"]
		[#else]
			[#local urldisable="${base}${baseAdminPath}/ajax/${channel}/status/0"]
		[/#if]
	[/#if]
	[#if urlenable==null && channel??]
		[#if site??]
			[#local urlenable="${base}${baseAdminPath}/${site}/ajax/${channel}/status/1"]
		[#else]
			[#local urlenable="${base}${baseAdminPath}/ajax/${channel}/status/1"]
		[/#if]
	[/#if]
	[#if candisable]
	<a id="btndisable" href="" class="easyui-linkbutton" data-options="" onclick="statusHandler(this, '${urldisable}')">${namedisable}</a>
	[/#if]
	[#if canenable]
	<a id="btnenable" href="" class="easyui-linkbutton" data-options="" onclick="statusHandler(this, '${urlenable}')">${nameenable}</a>
	[/#if]
	<div id="dlg-status" class="easyui-dialog" style="${style}" title="{0}选中的项" buttons="#dlg-status-btns" data-options="closed:true">
		<form id="statusform" method="post" action="#" onkeydown="if(event.keyCode==13){return false;}">
			<input type="hidden" set-key="uid" set-key2="oid" set-key3="id" name="ids" />
			<input type="hidden" name="adminuid" set-value="${admin.uid}" />
			<input type="hidden" set-key="status" name="statusfrom" />
			<table class="ftable">
				[#nested]
		        <tr>
		            <td>操作密码</td>
		            <td><input class="easyui-validatebox" type="password" set-value="" name="dopwd" data-options="required:true" /></td>
		        </tr>
		        <tr>
		            <td>备注</td>
		            <td><textarea class="easyui-validatebox" style="width:350px; height:60px;" set-value="" name="desc" data-options="required:true" ></textarea></td>
		        </tr>
		    </table>
		 </form>
		<div id="dlg-status-btns">
			<a href="" class="easyui-linkbutton" iconCls="icon-ok" onclick="status_submitHandler(this)">确定</a>
			<a href="" class="easyui-linkbutton" iconCls="icon-cancel" onclick="statusdlg_cancelHandler(this)">取消</a>
		</div>
	</div>
	<script type="text/javascript">
	<!--
		$("html").bind("status-reset-event", function(){
			$("#statusform").form("clear");
		});
	//-->
	</script>
[/#macro]

[#--
 * tools控件
 * id id
 * cansearch 是否可以搜索
 * titlesearchicon 查询按钮的title
 * onclicksearchicon 查询的onclick值
 * @author architect.bian
 * @since 1.0
 --]
[#macro tools id="tools", cansearch=true, titlesearchicon="查询", onclicksearchicon="searchShowHandler()"]
	[#if cansearch]
	<div id="${id}">
    	<a id="searchicon" href="" class="icon-filter" title="${titlesearchicon}" onclick="${onclicksearchicon}"></a>
    	[#nested]
	</div>
	[/#if]
[/#macro]

[#--
 * addsection控件
 * id id
 * addformid 表单的id
 * method form的method
 * action 要提交的url
 * canuploadfile 是否需要上传文件
 * namesubmit 提交按钮的名称
 * namereset 重置按钮的名称
 * onclicksubmit 提交按钮的onclick值
 * onclickreset 重置按钮的onclick值
 * @author architect.bian
 * @since 1.0
 --]
[#macro addsection id="addsection", addformid="addform", method="post", action=null, canuploadfile=false, namesubmit="提交", namereset="重置", onclicksubmit="submitHandler(this)", onclickreset="resetForm(this)"]
	[#if action==null && channel??]
		[#if site??]
			[#local action="${base}${baseAdminPath}/${site}/ajax/${channel}/add"]
		[#else]
			[#local action="${base}${baseAdminPath}/ajax/${channel}/add"]
		[/#if]
	[/#if]
	[#if canuploadfile]
		[#local enctype="enctype=\"multipart/form-data\""]
	[/#if]
		<section id="${id}" class="hd">
			<form id="${addformid}" method="${method}" action="${action}" ${enctype!}>
				<table class="ftable">
			        [#nested]
			    </table>
				<div class="formbtns">
					<a href="" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="${onclicksubmit}">${namesubmit}</a>
					<a href="" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="${onclickreset}">${namereset}</a>
					<a href="" class="easyui-linkbutton" data-options="iconCls:'icon-back'" data-crumbreturn="">返回</a>
				</div>
			 </form>
		</section>
[/#macro]

[#--
 * editsection控件
 * id id
 * editformid 表单的id
 * method form的method
 * action 要提交的url
 * canuploadfile 是否需要上传文件
 * namesubmit 提交按钮的名称
 * onclicksubmit 提交按钮的onclick值
 * @author architect.bian
 * @since 1.0
 --]
[#macro editsection id="editsection", editformid="editform", method="post", action=null, canuploadfile=false, namesubmit="提交", onclicksubmit="submitHandler(this)"]
	[#if action==null && channel??]
		[#if site??]
			[#local action="${base}${baseAdminPath}/${site}/ajax/${channel}/edit"]
		[#else]
			[#local action="${base}${baseAdminPath}/ajax/${channel}/edit"]
		[/#if]
	[/#if]
	[#if canuploadfile]
		[#local enctype="enctype=\"multipart/form-data\""]
	[/#if]
		<section id="${id}" class="hd">
			<form id="${editformid}" method="${method}" action="${action}" ${enctype!}>
				<table class="ftable">
			        [#nested]
			    </table>
				<div class="formbtns">
					<a href="" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="${onclicksubmit}">${namesubmit}</a>
					<a href="" class="easyui-linkbutton" data-options="iconCls:'icon-back'" data-crumbreturn="">返回</a>
				</div>
			 </form>
		</section>
[/#macro]

[#--
 * viewsection控件
 * id id
 * viewformid 表单的id
 * @author architect.bian
 * @since 1.0
 --]
[#macro viewsection id="viewsection", viewformid="viewform"]
	<section id="${id}" class="hd">
		<form id="${viewformid}">
			<table class="ftable">
		        [#nested]
		    </table>
			<div class="formbtns">
				<a href="" class="easyui-linkbutton" data-options="iconCls:'icon-back'" data-crumbreturn="">返回</a>
			</div>
		 </form>
	</section>
[/#macro]

[#--
 * doaction控件
 * id id
 * action 提交的url
 * name 操作按钮名称
 * onclick 点击事件处理方法
 * options linkbutton的option
 * confirm 是否有确认框
 * disabled 初始状态是否禁用
 * @author architect.bian
 * @since 1.0
 --]
[#macro doaction id=null, action="", name=null, onclick=null, options=null, confirm=true, disable=false, needselection=true]
	[#if doactionindex??]
		[#assign doactionindex = doactionindex + 1 ]
	[#else]
		[#assign doactionindex = 0 ]
	[/#if]
	[#if id==null]
		[#local id="doaction" + doactionindex]
	[/#if]
	[#if name==null]
		[#local name="Action" + doactionindex]
	[/#if]
	[#if onclick==null]
		[#local onclick="doactionHandler(this, '${action}', ${confirm?string}, ${needselection?string})"]
	[/#if]
	[#if options==null]
		[#local options=""]
	[/#if]
	<a id="${id}" href="" class="easyui-linkbutton" [#if disable]disabled="true"[/#if] data-options="${options}" onclick="${onclick}">${name}</a>
	<script type="text/javascript">
	<!--
		$(function(){
			$("html").bind("datagrid-onselect-event", ${id}StateHandler);
			$("html").bind("datagrid-onunselect-event", ${id}StateHandler);
			$("html").bind("datagrid-onloadsuccess-event", ${id}StateHandler);
		});
		function ${id}StateHandler(event, j) {
			[#if needselection]
			var list = getSelections(j.dg);
			if (list.length == 0) {
				$("#${id}").linkbutton("disable");
			} else {
				$("#${id}").linkbutton("enable");
			}
			[/#if]
		}
	//-->
	</script>
[/#macro]

[#--
 * doform控件
 * id id
 * action 提交的url
 * name 操作按钮名称
 * onclick 点击事件处理方法
 * options linkbutton的option
 * confirm 是否有确认框
 * disabled 初始状态是否禁用
 * @author architect.bian
 * @since 1.0
 --]
[#macro doform id=null, action="", name=null, onclick=null, options=null, confirm=true, disable=false]
	[#-- //TODO --]
[/#macro]

[#--
 * dodialog控件
 * id id
 * action 提交的url
 * name 操作按钮名称
 * onclick 点击事件处理方法
 * options linkbutton的option
 * disabled 初始状态是否禁用
 * @author architect.bian
 * @since 1.0
 --]
[#macro dodialog id=null, action="", name=null, onclick=null, options=null, disable=false, needselection=true]
	[#-- //TODO --]
[/#macro]

[#--
 * 上移下移控件
 * idup 上移按钮的ID
 * iddown 下移按钮的ID
 * actionup 上移提交的url
 * actiondown 下移提交的url
 * nameup 上移操作按钮名称
 * namedown 上移操作按钮名称
 * @author architect.bian
 * @since 1.0
 --]
[#macro updown idup=null, iddown=null, actionup=null, actiondown=null, nameup=null, namedown=null]
	[#if updownindex??]
		[#assign updownindex = updownindex + 1 ]
	[#else]
		[#assign updownindex = 0 ]
	[/#if]
	[#if idup==null]
		[#local idup="updown_up"+updownindex]
	[/#if]
	[#if iddown==null]
		[#local iddown="updown_down"+updownindex]
	[/#if]
	[#if actionup==null]
		[#if site??]
			[#local actionup="${base}${baseAdminPath}/${site}/ajax/${channel}/up"]
		[#else]
			[#local actionup="${base}${baseAdminPath}/ajax/${channel}/up"]
		[/#if]
	[/#if]
	[#if actiondown==null]
		[#if site??]
			[#local actiondown="${base}${baseAdminPath}/${site}/ajax/${channel}/down"]
		[#else]
			[#local actiondown="${base}${baseAdminPath}/ajax/${channel}/down"]
		[/#if]
	[/#if]
	[#if nameup==null]
		[#local nameup="上移"]
	[/#if]
	[#if namedown==null]
		[#local namedown="下移"]
	[/#if]
	[@a.doaction id="${idup}" action="${actionup}" name="${nameup}" confirm=false /]
	[@a.doaction id="${iddown}" action="${actiondown}" name="${namedown}" confirm=false /]
	<script type="text/javascript">
	<!--
	function ${idup}StateHandler(event, j) {
		var list = getSelections(j.dg);
		if (list.length == 0 || j.index == 0) {
			$("#${idup}").linkbutton("disable");
		} else {
			$("#${idup}").linkbutton("enable");
		}
	}
	function ${iddown}StateHandler(event, j) {
		var list = getSelections(j.dg);
		var all = getRows(j.dg);
		if (list.length == 0 || j.index == all.length - 1) {
			$("#${iddown}").linkbutton("disable");
		} else {
			$("#${iddown}").linkbutton("enable");
		}
	}
	//-->
	</script>
[/#macro]

[#--
 * 设置置顶、是否首页显示等控件
 * statefield 修改的字段,默认为istop
 * idenable 置顶按钮的ID
 * iddisable 取消置顶按钮的ID
 * actionenable 置顶提交的url
 * actiondisable 取消置顶提交的url
 * nameenable  置顶操作按钮名称
 * namedisable 取消置顶按钮名称
 * @author liushaomin
 * @since 1.0 
 --]
[#macro state statefield=null, idenable=null, iddisable=null, actionenable=null, actiondisable=null, nameenable=null, namedisable=null]
	[#if stateindex??]
		[#assign stateindex = stateindex + 1 ]
	[#else]
		[#assign stateindex = 0 ]
	[/#if]
	[#if statefield==null]
		[#local statefield="istop"]
	[/#if]
	[#if idenable==null]
		[#local idenable="state_enable" + stateindex]
	[/#if]
	[#if iddisable==null]
		[#local iddisable="state_disable" + stateindex]
	[/#if]
	[#if actionenable==null]
		[#if site??]
			[#local actionenable="${base}${baseAdminPath}/${site}/ajax/${channel}/enable"]
		[#else]
			[#local actionenable="${base}${baseAdminPath}/ajax/${channel}/enable"]
		[/#if]
	[/#if]
	[#if actiondisable==null]
		[#if site??]
			[#local actiondisable="${base}${baseAdminPath}/${site}/ajax/${channel}/disable"]
		[#else]
			[#local actiondisable="${base}${baseAdminPath}/ajax/${channel}/disable"]
		[/#if]
	[/#if]
	[#if nameenable==null]
		[#local nameenable="置顶"]
	[/#if]
	[#if namedisable==null]
		[#local namedisable="取消置顶"]
	[/#if]
	[@a.doaction id="${idenable}" action="${actionenable}" name="${nameenable}" confirm=true /]
	[@a.doaction id="${iddisable}" action="${actiondisable}" name="${namedisable}" confirm=true /]
	<script type="text/javascript">
	<!--
	function ${idenable}StateHandler(event, j) {
		var list = getSelections(j.dg);
		var row = list[0];
		if(list.length == 0 || row == undefined){
			$("#${idenable}").linkbutton("disable");
		}else{
			if(row.${statefield}){
				$("#${idenable}").linkbutton("disable");
			}else{
				$("#${idenable}").linkbutton("enable");
			}
		} 
	}
	function ${iddisable}StateHandler(event, j) {
		var list = getSelections(j.dg);
		var row = list[0];
		if(list.lengh == 0 || row == undefined){
			$("#${iddisable}").linkbutton("disable");
		}else{
			if(row.${statefield}){
				$("#${iddisable}").linkbutton("enable");
			}else{
				$("#${iddisable}").linkbutton("disable");
			}
		}
	}
	//-->
	</script>
[/#macro]

[#----------------------------------------------------------------以上是管理后台框架所提供的功能控件----------------------------------------------------------------]
[#----------------------------------------------------------------以下是管理后台封装的easyui表单控件----------------------------------------------------------------]
[#--
 * help控件
 * id id
 * viewformid 表单的id
 * @author architect.bian
 * @since 1.0
 --]
[#macro help title options=null]
	[#if options==null]
		[#local options="iconCls:'icon-help',plain:true,position:'right'"]
	[/#if]
	<a href="" class="easyui-linkbutton easyui-tooltip" data-options="${options}" title="${title}"></a>
[/#macro]

[#--
 * ckeditor控件
 * id 控件的id属性
 * name 控件的name属性,默认content
 * width 控件css样式的width
 * height 控件css样式的height
 * channel 上传文件的地址
 * iswater 图片是否添加水印
 * content 显示内容不是通过js获取时，需要从页面填充内容
 * filebrowserImageUploadUrl 控件内上传的文件的地址，默认为channel
 * filebrowserFlashUploadUrl 控件内上传的flash的地址，默认为channel
 * @author ShangJianguo
 * @since 1.0
--]
[#macro ckeditor id=null, name=null, width=500, height=150, channel=null, iswater=null, content=null, filebrowserImageUploadUrl=null, filebrowserFlashUploadUrl=null]
	[#if ckeindex??]
		[#assign ckeindex = ckeindex + 1 ]
	[#else]
		[#assign ckeindex = 0 ]
	[/#if]
	[#if id == null]
		[#local id = "ckeid" + ckeindex ]
	[/#if]
	[#if name == null]
		[#local name = "content"]
	[/#if]
	[#if channel == null]
		[#local channel = "${channel}" ]
	[/#if]
	[#if iswater == null]
		[#local iswater = "false" ]
	[/#if]
	[#if filebrowserImageUploadUrl == null]
		[#local filebrowserImageUploadUrl = "${base}${baseAdminPath}/ajax/upload/webdata/${channel}/${iswater}" ]
	[/#if]
	[#if filebrowserFlashUploadUrl == null]
		[#local filebrowserFlashUploadUrl = "${base}${baseAdminPath}/ajax/upload/webdata/${channel}/video" ]
	[/#if]
	<textarea class="ckeditor" id="${id}" set-toobj="$('.cke_contents iframe')['${ckeindex}'].contentWindow.document.body" style="width:${width}px; height:${height}px;" name="${name}">${content}</textarea>
	<script type="text/javascript">
	<!--
	    CKEDITOR.replace('${id}',{
	        toolbar : 'Full',
	        imageUploadUrl : "${filebrowserImageUploadUrl}/dropimg",
	        filebrowserImageUploadUrl : "${filebrowserImageUploadUrl}",
	        filebrowserFlashUploadUrl : "${filebrowserFlashUploadUrl}"
	    });
	    [#if ckeindex == 0]
	    $("html").bind("form-reset-event", clearCkeditor);
		/**
		 * 清空所有的ckeditor的富文本编辑器
		 */
		function clearCkeditor(){
			var id = "";
			var jsfunc = "";
			$(".cke_skin_v2").each(function(){
				id = $(this).attr("id");
				jsfunc = "CKEDITOR.instances."+id.substring(4)+".setData('')";
				eval(jsfunc);
			});
		}
	    [/#if]
	//-->
	</script>
[/#macro]

[#--
 * imgupload控件，在使用此控件进行开发时，完成图片上传后需要返回由图片的原图、大图、中图和小图的路径
 * 以英文逗号分割的一个字符串；并且每次上传成功后，返回的路径的标识符是path。
 * name form表单中的input的name，默认file,如果上传的是多张图片，在提交表单的时候数据为一个name[]数组，每一个里面包含原图、大图、中图和小图的路径
 * multiple 如果为false则只能够上传一张图，默认为true
 * channel 上传图片的目录，默认为后台配置的channel
 * size 除原图外还要生成的图片的尺寸（像素px），若不设置，则按照默认的大中小图；如果传参的话，则按照如下格式，顺序还是大中小图：格式为：w,h|w1,h1|w2,h2，其中w表示宽，h表示高。例如：size="930,600-310,200-155,100"	
 * @author ShangJianguo
 * @since 1.0
--]
[#macro imgupload name=null, multiple=true, channel=null, size=null, imgpath=null]
	[#if imguploadindex??]
		[#assign imguploadindex = imguploadindex + 1 ]
	[#else]
		[#assign imguploadindex = 0 ]
	[/#if]
	[#if name == null]
		[#local name="file" ]
	[/#if]
	[#if !size?? ]
		[#local size="900,300-300,200-150,100" ]
	[/#if]
	[#if imgpath == null && channel != null]
		[#local imgpath = "/imgs/webdata/${channel}" ]
	[/#if]
	<div id="imguploadinput${imguploadindex}"></div>
   	<iframe id="frameImg${imguploadindex}" name="frameImg${imguploadindex}" src="${base}${baseAdminPath}/ajax/file/img/upload?name=${name}&index=${imguploadindex}&multiple=${multiple}&imgpath=${imgpath}&size=${size}" height="40" width="500" scrolling="no" frameborder="0" ></iframe>
   	<div class="imgupload_container" id="imgupload_container${imguploadindex}" style="float:left;"></div>
   	[#if imguploadindex == 0]
   	<style type="text/css">
   		.imgupload_container {
   			width:80%;
   			float:left;
   		}
   		.imgupload_container div {
   			text-align: center;
   			padding:5px;
   		}
   		img.remove {
			cursor:pointer;
		}
   	</style>
   	<script type="text/javascript">
	<!--
		function deleteimg(obj, index) {
			$.messager.confirm('提醒','您确认想要删除该图片吗？',function(r){
			    if (r){
					var path = $(obj).data("path");
					console.log(path);
					$.get("${base}${baseAdminPath}/ajax/file/img/delete", {path: path}, function(data){
						if(data) {
							$(obj).parent().remove();
							deleteInput(path);
							$(window.frames["frameImg" + index].document).find("#formImg" + index).css("display","block");
						}
					});
			    }    
			});
		};
        function deleteInput(path) {
            $("input[value='"+path+"']").first().remove();
        }
	//-->
	</script>
	[/#if]
	<script type="text/javascript">
    <!--
        $("html").bind("form-clear-event", function() {
            $("#imguploadinput${imguploadindex}").html("");
            $("#imgupload_container${imguploadindex}").html("");
        });
    //-->
    </script>
[/#macro]

[#--
 * tree控件举例
 * id id
 * name name
 * zsetting ztree的setting变量
 * znodes ztree的nodes变量
 * @author architect.bian
 * @since 1.0
 --]
[#macro tree id=null name="tree" zsetting="setting" znodes="nodes"]
	[#-- 为一个页面多次引用该控件做处理 --]
	[#if treeindex??]
		[#assign treeindex = treeindex + 1 ]
	[#else]
		[#assign treeindex = 0 ]
	[/#if]
	[#if id == null]
		[#local id="tree" + treeindex]
	[/#if]
	<ul id="${id}" class="ztree"></ul>
	<input id="txt${id}" type="hidden" set-key="${name}" name="${name}" />

[#if treeindex == 0][#-- 页面第一次用此控件 --]
	<script type="text/javascript">
	<!--
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck: tree_checkHandler
			}
		};
		
		function tree_checkHandler(event, treeId, treeNode) {
			var arr = new Array();
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			var list = zTree.getCheckedNodes(true);
			for(var i = 0; i < list.length; i++){
				 if(typeof(list[i].value) != "undefined"){
				 	arr.push(list[i].value);
				 }
			}
			$("#txt" + treeId).val(arr.join(","));
		}
		//-->
	</script>
[/#if]
	<script type="text/javascript">
	<!--
		$(function(){
			$.fn.zTree.init($("#${id}"), ${zsetting}, ${znodes});
			$("#txt${id}").bind("set", tree${treeindex}SetHandler);
		});
		
		function tree${treeindex}SetHandler(j) {
			var value = $("#txt${id}").val();
			if(value.length > 0) {
				var arr = value.split(",");
				var tree = $.fn.zTree.getZTreeObj("${id}");
				tree.checkAllNodes(false);//取消所有已选择的
				for(var i = 0; i < arr.length; i++) {
					var nodes = tree.getNodesByParam("value", arr[i]);
					if(nodes != null && nodes.length > 0) {
						tree.checkNode(nodes[0], true, true);
					}
				}
			} else {
				tree${treeindex}ResetHandler(j);
			}
		}
		
		function tree${treeindex}ResetHandler(j) {
			var tree = $.fn.zTree.getZTreeObj("${id}");
			tree.checkAllNodes(false);
			$("#txt${id}").val("");
		}
	//-->
	</script>
[/#macro]

[#--
 * cutimgupload控件，可以截取图片的上传图片组件，在使用此控件进行开发时，完成图片上传后需要返回由图片的大图、中图和小图的路径
 * 以英文逗号分割的一个字符串；并且每次上传成功后，返回的路径的标识符是path。
 * name form表单中的input的name，默认file,如果上传的是多张图片，在提交表单的时候数据为一个name[]数组，每一个里面包含原图、大图、中图和小图的路径
 * multiple 如果为false则只能够上传一张图，默认为true
 * channel 上传图片的目录，默认为后台配置的channel
 * size 除原图外还要生成的图片的尺寸（像素px），若不需要剪切只上传原图则size=""，若不设置，则按照默认的大中小图；如果传参的话，则按照如下格式，顺序还是大中小图：格式为：w,h|w1,h1|w2,h2，其中w表示宽，h表示高。例如：size="930,600-310,200-155,100"  
 * @author ShangJianguo
 * @since 1.0
--]
[#macro cutimgupload name=null, multiple=true, channel=null, size=null, imgpath=null]
    [#if cutimguploadindex??]
        [#assign cutimguploadindex = cutimguploadindex + 1 ]
    [#else]
        [#assign cutimguploadindex = 0 ]
    [/#if]
    [#if name == null]
        [#local name="file" ]
    [/#if]
    [#if !size?? ]
        [#local size="900,300-300,200-150,100" ]
    [/#if]
    [#if imgpath == null && channel != null]
        [#local imgpath = "/imgs/webdata/${channel}" ]
    [/#if]
    <div id="cutimguploadinput${cutimguploadindex}"></div>
    <iframe id="cutframeImg${cutimguploadindex}" name="cutframeImg${cutimguploadindex}" src="${base}${baseAdminPath}/ajax/file/cutimg/upload?name=${name}&index=${cutimguploadindex}&multiple=${multiple}&imgpath=${imgpath}&size=${size}" height="40" width="500" scrolling="no" frameborder="0" ></iframe>
    <div class="imgupload_container" id="cutimgupload_container${cutimguploadindex}" style="float:left;"></div>
    [#if cutimguploadindex == 0]
    <style type="text/css">
        .imgupload_container {
            width:80%;
            float:left;
        }
        .imgupload_container div {
            text-align: center;
            padding:5px;
        }
        img.remove {
            cursor:pointer;
        }
    </style>
    [/#if]
    <script type="text/javascript">
    <!--
        $("html").bind("form-clear-event", function() {
            $("#cutimguploadinput${cutimguploadindex}").html("");
            $("#cutimgupload_container${cutimguploadindex}").html("");
        });
    //-->
    </script>
[/#macro]