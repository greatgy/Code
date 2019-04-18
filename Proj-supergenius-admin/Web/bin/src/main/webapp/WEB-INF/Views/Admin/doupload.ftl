[#ftl]
<html>
<head>
<title>上传图片</title>
<style type="text/css">
</style>
<script type="text/javascript" src="${basejs}/js/jquery/jquery-1.9.0.min.js"></script>
<script type="text/javascript">
<!--
	[#if path??]
		var parent = $(window.parent.document);
		var imgs = $("#imgupload_container${index}", parent);
		var path = "${path}";
		var thumb = "";
		if (path.split(",").length <= 1) {
			thumb = path.split(",")[0];
		} else {
			thumb = path.split(",")[1];
		}
		var origin = path.split(",")[0];
		var img = "<div style='width:55px;float:left;'><a href='${webimg}" + origin + "' target='_blank' title='点击查看原图'><img style='width:50px;height:50px;' src='${webimg}"+ thumb +"' /></a><img src='${baseimg}/imgs/admin/icn_remove.png' class='remove' data-path='${path}' onclick='deleteimg(this,${index})' /></div>"
		imgs.append(img);// 添加图片		
		var inputdiv = $("#imguploadinput${index}", parent);		
		inputdiv.append("<input type='hidden' name='${name}' value='${path}' ></input>");
	[/#if]
	$(function($) {
  		[#if !multiple] // 对于只能够上传一张时进行处理
		if($("#imguploadinput${index} input[name='${name}']", parent).length > 0){
			$("form#formImg${index}").css("display","none");
		}
		[/#if]
	});
	function valid() {
		var path = $("input[name='file']").val();
		if(path == "") {
			alert('请选择要上传的图片。');
			return false;
		}
	}
//-->
</script>
</head>
<body>
	<form id="formImg${index}" name="formImg" onsubmit="return valid();" method="post" action="${base}${baseAdminPath}/ajax/file/img/upload" enctype="multipart/form-data">
		<input type="hidden" name="index" value="${index}" />
		<input type="hidden" name="name" value="${name}" />
		<input type="hidden" name="multiple" value="${multiple}" />
		<input type="hidden" name="imgpath" value="${imgpath}" />
		<input type="hidden" name="size" value="${size}" />
		<input type="file" name="file" accept="image/gif, image/jpeg, image/png"/>
		<input type="submit" name="submit" value="上传" />
	</form>
</body>
</html>