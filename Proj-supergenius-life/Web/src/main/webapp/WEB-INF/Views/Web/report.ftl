[#ftl]
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title!'举报'}</title>
<link href="${basecss}/css/default/index.css" type="text/css" rel="stylesheet" />
<script src="https://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript">
	function showXiuXiu(){
	  /*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
	  xiuxiu.setLaunchVars("uploadBtnLabel", "上传");
	  xiuxiu.setLaunchVars("titleVisible", "0");
	  xiuxiu.setLaunchVars("cropPresets", "4:3");
	  xiuxiu.setLaunchVars("customMenu", ["edit"]);
	  xiuxiu.embedSWF("altcontent",1,"100%","400px");
	  xiuxiu.setUploadURL("${base}/uploadimg");//修改为您自己的图片上传接口
	  xiuxiu.setUploadType(2);
	  xiuxiu.setUploadDataFieldName("fileimg");
	  xiuxiu.onInit = function (){
	    xiuxiu.loadPhoto("");//默认待处理图片
	  }
	  xiuxiu.onUploadResponse = function (data){
	    $("#imgbox").html("<span id='altcontent'></span>");
	    $("#contributeimg").val(data);
	    var origin = data.split(",")[3];
	    $("#imgshow").removeClass("hd");
	    $("#imgshow_big").attr("src",'${webimg}' + origin)
	  }
	  xiuxiu.onClose = function (){
  		$("#imgbox").html("<span id='altcontent'></span>");
	  }
	}
	$(function () {
			$(".submitBtn").click(function () {
				$(".draft").submit();
			});
		});
</script>
</head>
<body>
	<div class="answerQuestionBox questionBox">
		<h3>举报</h3>
		<form id="contribute" class="draft" action="${base}/life/my/report" method="post" enctype="multipart/form-data">
			<ul class="reportDetail">
				<li>
					<span class="itemlabel"><i>*</i>举报对象</span>
					<div class="reportRight">
						<input class="reportname" type="text" id="txttitle" name="refname" value="${title}"/>
						<input type="hidden" id="cid" name="cid" value="${cid?c}"/>
					</div>
				</li>
				<li>
					<span class="itemlabel"><i>*</i>举报理由</span>
					<div class="reportRight">
						<select name="reporttype">
							<option value="0">广告骚扰</option>
							<option value="1">欺诈骗钱</option>
							<option value="2">色情</option>
							<option value="3">政治</option>
							<option value="4">暴力</option>
							<option value="5">侮辱诋毁</option>
							<option value="6">其他</option>
						</select>
					</div>
				</li>
				<li>
					<span class="itemlabel"><i>*</i>详细说明</span>
					<div class="reportRight">
						<textarea name="desc"></textarea>
						<div class="detailTip">*您的举报可能对举报对象重大影响，请慎重选择且确保举报内容属实！</div>
					</div>
				</li>
				<li>
					<span class="itemlabel">配图</span>
					<div class="reportRight">
						<a id="upload" href="javascript:showXiuXiu()">点此上传</a><br>
						<div id="imgbox">
				        	<span id="altcontent"></span>
				        </div>
				        <div class="hd" id="imgshow" style="margin-left: 175px;">
				        	<img id="imgshow_big" src=''/>
				        </div>
				        <input id="contributeimg" name="contributeimg" type="text" value="" class="hd"/>
					</div>
				</li>
			</ul>
			<input type="hidden" name="refurl" value="${url}">
			<input type="hidden" name="fromuid" value="${fromuid}">
		</form>
		<div class="btns">
			<a class="cancelBtn submitBtn" href="${url}">取消</a>
			<a class="submitBtn" >确认举报</a>
		</div>
	</div>
</body>
</html>
