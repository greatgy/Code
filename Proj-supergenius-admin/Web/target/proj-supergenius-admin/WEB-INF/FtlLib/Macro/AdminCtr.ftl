[#ftl strip_whitespace=true]
[#--
 * AdminCtrl.ftl
 * 管理后台提供的控件
 * @author architect.bian
 * @since 1.0
 --]
 
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