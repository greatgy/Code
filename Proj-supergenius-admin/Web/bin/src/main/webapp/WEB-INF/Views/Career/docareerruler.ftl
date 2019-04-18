[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Admin.ftl" as a]
<html>
<head>
<title>规则管理</title>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<style>
.tdcolor{
    color: red;
}

.txtcolor input {
    color: red;
}
</style>
<script type="text/javascript">

function rulerEditHandler(obj, dg, editform) {
	/*进入编辑规则页面从后台获取表格*/
	$.post("${base}${baseAdminPath}/career/ajax/careerruler/rulertable", function(result) {
		var data = $.parseJSON(result);
		var list = data.list;
		var listhtml = "";
		for (var i = 0 ; i < list.length ; i++) {
			listhtml += "<tr id="+list[i].uid+">"
			listhtml += "<td>"+ list[i].name +"</td>"
			listhtml += "<td><input type='number' min='0' max='1' step='0.01' style='border-style:none' name='research' onBlur='check()' value='"+ list[i].research +"'></input></td>"
			listhtml += "<td><input type='number' min='0' max='1' step='0.01' style='border-style:none' name='convention' onBlur='check()' value='"+ list[i].convention +"'></input></td>"
			listhtml += "<td><input type='number' min='0' max='1' step='0.01' style='border-style:none' name='art' onBlur='check()' value='"+ list[i].art +"'></input></td>"
			listhtml += "<td><input type='number' min='0' max='1' step='0.01' style='border-style:none' name='enterprise' onBlur='check()' value='"+ list[i].enterprise +"'></input></td>"
			listhtml += "<td><input type='number' min='0' max='1' step='0.01' style='border-style:none' name='sociology' onBlur='check()' value='"+ list[i].sociology +"'></input></td>"
			listhtml += "<td><input type='number' min='0' max='1' step='0.01' style='border-style:none' name='reality' onBlur='check()' value='"+ list[i].reality +"'></input></td>"
			listhtml += "<td name='rowsum'></td>"
			listhtml += "</tr>"
		}
		$("#rulertb").find("tr").remove(); 
		$("#rulertb").append(listhtml);
		var urls = getFreshRowUrls(dg);
		if (editBefore(obj, dg, editform, urls, 0)) {
	        indexEditAction(obj, dg, editform);
	    }
	    check();
	}, 'html');
}

function indexEditAction(obj, dg, editform) {
    crumbShow(obj);
}

function initsum() {
    /*初始化合计*/
    var sum = new Array(+$('#mytable thead tr td:not(:first-child)').length - 1).fill(0);
	$('#mytable tbody tr').each(function() {
		var x = 0;
		var rowsum = 0;
		var y = 0;
	    $(this).children('td').each(function() {
	    	   if (y > 0 && y <= sum.length) {
		        sum[y-1] += +$(this).find("input").val()*100;
		        rowsum += +$(this).find("input").val()*100;
		        $(this).find("input").removeClass("tdcolor");
		       }
		       if (y > sum.length){
		       	 $(this).text(rowsum/100);
		         $(this).removeClass("tdcolor");
				 $(this).parent().removeClass("txtcolor");
		       }
		       y++;
		});
	});
	$('#mytable #totalRow td:not(:first-child)').each(function() {
	    var index = $(this).index() - 1;
		if (index < sum.length) {
		    $(this).text(sum[index]/100);
		    $(this).removeClass("tdcolor");
		}
	});
}

function check() {
	initsum();
	var iserr = 0;
	$("td[name='rowsum']").each(function() {
		if ($(this).text() !=1 ) {
			$(this).addClass("tdcolor");
			$(this).parent().addClass("txtcolor");
			iserr = 1;
		}
	});
	$('#mytable #totalRow td:not(:first-child)').each(function() {
		if ($(this).text() !=1 ) {
			$(this).addClass("tdcolor");
			$("input[name='"+$(this).attr("name")+"']").addClass("tdcolor");
			iserr = 1;
		}
	});
	return iserr;
}

function rulerSubmitHandler(obj, dg) {
	/*自定义提交方法*/
    if (check() == 0) {
    	initrulernow();
    	$(obj).parents("form").form('submit', {
	        success : function(str) {
	            var result = $.parseJSON(str);
	            if (result.success || result.addSuccess || result.editSuccess) {
	                window.location.href = "${base}${baseAdminPath}/career/careerruler";
	            } else {
	                submitErrHandler(result);
	            }
	        }
	    });
    } else {
    	err("必须确保表格横向和纵向之和均为1");
		return false;
    }
    
}

function initrulernow() {
	/*拼接json字符串*/
    var rulerList = "[";
    $("#mytable tbody tr").each(function() {
    	 var childjson = "";
    	 $(this).children('td:not(:first-child):not(:last-child)').each(function() {
    	 	childjson += "\""+ $(this).find("input").attr("name") +"\":" + $(this).find("input").val() + ",";
    	 });
    	 rulerList += "{\"uid\":\"" +$(this).attr("id")+ "\",\"CLASS\":\"com.supergenius.xo.career.entity.Ruler\","+ childjson.substring(0, childjson.length - 1)+"},";
    });
    rulerList = rulerList.substring(0, rulerList.length - 1) + "]";
    console.log(rulerList);
    $("#rulerListnow").val(rulerList);
}
</script>
</head>
<body>
	<section>
		[@a.search]
	        <tr>
	        	<td colspan="10">
		        	名称: <input id="name" type="text" name="name" />
	        	</td>
	        </tr>
	        <tr>
	            <td>起始操作时间: <input name="createtimestart" type="text" editable="false" class="easyui-datebox" /></td>
                <td>结束操作时间: <input name="createtimeend" type="text" editable="false" class="easyui-datebox" /></td>
	        </tr>
		[/@a.search]
		
		[@a.datagrid]
            <th data-options="field:'name',width:75">类型</th>
            <th data-options="field:'research',width:75">研究型</th>
            <th data-options="field:'convention',width:75">常规型</th>
            <th data-options="field:'art',width:75">艺术型</th>
            <th data-options="field:'enterprise',width:75">企业型</th>
            <th data-options="field:'sociology',width:75">社会型</th>
            <th data-options="field:'reality',width:75">现实型</th>
            <th data-options="field:'updatetimeStr',width:100">操作时间</th>
            <th data-options="field:'adminname',width:75">操作人</th>
	    [/@a.datagrid]
	    
	    [@a.toolbar canadd=false canedit=false canview=false candel=false]
	    <a id="btneditruler" href="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-crumbshow="{selector:'#editruler', title:'编辑规则'}" onclick="rulerEditHandler(this, null, '#editruler')">规则编辑</a>
	    [/@a.toolbar]
	    [@a.tools /]
	    
	</section>
	[@a.editsection id="editruler" namesubmit="提交" action="${base}${baseAdminPath}/${site}/ajax/${channel}/editruler"  onclicksubmit="rulerSubmitHandler(this)"]
	    <input type="text" id="rulerListnow" name="rulerListnow" style="display:none" />
	   <table id="mytable" border="1">
			<thead>
			    <tr>
			        <td>类型</td>
			        <td>研究型</td>
			        <td>常规型</td>
			        <td>艺术型</td>
			        <td>企业型</td>
			        <td>社会型</td>
			        <td>现实型</td>
			        <td>合计</td>
			    </tr>
			</thead>
			<tbody id="rulertb">
			</tbody>
			<tfoot>
			    <tr id="totalRow">
			        <td>合计</td>
			        <td name="research"></td>
			        <td name="convention"></td>
			        <td name="art"></td>
			        <td name="enterprise"></td>
			        <td name="sociology"></td>
			        <td name="reality"></td>
			    </tr>
			</tfoot>
			</table>
	[/@a.editsection]
</body>
</html>
