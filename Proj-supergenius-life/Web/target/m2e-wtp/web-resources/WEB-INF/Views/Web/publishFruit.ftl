[#ftl]
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title!'上传视频'}</title>
<link href="${basecss}/css/default/contribute11.css" type="text/css" rel="stylesheet" />
<script src="https://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript" src="${basejs}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${basejs}/js/libs/jquery.form.js"></script>
<script type="text/javascript" src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
<script type="text/javascript">
	var origin = '';
	var origin1 = '';
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
	     origin = data.split(",")[3];
	     origin1 = data.split(",")[0];
	    $("#imgshow").removeClass("hd");
	    $("#imgshow_big").attr("src",'${webimg}' + origin)
	  }
	  xiuxiu.onClose = function (){
  		$("#imgbox").html("<span id='altcontent'></span>");
	  }
	}
</script>

<script type="text/javascript">
<!--
$(function(){
	CKEDITOR.replace('content',{
        toolbar : 'MyToolbar2',
        filebrowserFlashUploadUrl : "${base}/ajax/ckeditor/flash"
    });
    CKEDITOR.on('instanceReady', function (e) {
        $("#cke_17, #cke_18").hide();
    })
});
//-->
</script>
</head>
<body>
<div class="contribute_box" style="width:1000px; margin-bottom:100px;clear:both;">
    <div class="contribute_800" style="padding-bottom:30px">
    <form id="contribute" class="draft" action="${base}/my/video/add_${cid?c}" method="post" enctype="multipart/form-data">
    	<div id="form">	
    		<label class="mb_30"><span class="red">*</span>标题</label>
	        <input class="draft_input ml_35" type="text" id="txttitle" name="title" placeholder="输入文章标题"/>
            <br>
	        <label class="mb_30"><span class="red">*</span>配图</label>
	    	<a id="upload" href="javascript:showXiuXiu()" class="title_3_a">点此上传</a><br>
	        <div id="imgbox">
	        	<span id="altcontent"></span>
	        </div>
	        <div class="hd" id="imgshow" style="margin-left: 175px;">
	        	<img id="imgshow_big" src=''/>
	        </div>
	        <input id="contributeimg" name="contributeimg" type="text" value="" class="hd"/>
            <div>
	            <label class="mb_30" ><span class="red">*</span>类别</label>
	       		<input type="radio" name="keywords" id="piano" value="0" checked/><label for="piano">钢琴</label>
	       		<input type="radio" name="keywords" id="dance" value="1" /><label for="dance">舞蹈</label>
	       		<input type="radio" name="keywords" id="violin" value="2"/><label for="violin">小提琴</label>
	       		<input type="radio" name="keywords" id="painting" value="3"/><label for="painting">绘画</label>
	       		<input type="radio" name="keywords" id="theatre" value="4"/><label for="theatre">戏剧</label>
	       		<input type="radio" name="keywords" id="calligraphy" value="5"/><label for="calligraphy">书法</label>
	       		<input type="radio" name="keywords" id="music" value="6"/><label for="music">音乐</label>
	       		<input type="radio" name="keywords" id="judo" value="7"/><label for="judo">柔道</label>
	       		<input type="radio" name="keywords" id="recitation" value="8"/><label for="recitation">朗诵</label>
	       		<input type="radio" name="keywords" id="sculpture" value="9"/><label for="sculpture">雕塑&nbsp;&nbsp;&nbsp;&nbsp;</label>
	        </div>
			<div>	 
			    <label class="mb_30" ><span class="red"></span></label>      		
	       		<input type="radio" name="keywords" id="swimming" value="10"/><label for="swimming">游泳</label>
	       		<input type="radio" name="keywords" id="gymnastics" value="11"/><label for="gymnastics">体操</label>
	       		<input type="radio" name="keywords" id="skating" value="12"/><label for="skating">溜冰</label>
	       		<input type="radio" name="keywords" id="skiing" value="13"/><label for="skiing">滑雪</label>
	       	</div>
	       <br>
	       <label class="mb_30"><span class="red">*</span>成果附件</label>
	       <div style="width:700px;display: inline-block;vertical-align: top; margin-bottom: 40px">
		    	<textarea class="draft_input2" id="content" name="content" ></textarea>
		   </div>
		   <br>
	      <input type="button" class="draft_tg right" onclick="publish(1)" value="确认发布"></input>
	      <input type="hidden" name="type" value="0"/>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript">
    
   function publish(type){
   		var content = CKEDITOR.instances.content.getData().trim();
   		var title = $("#txttitle").val();
		if(title.length > 50 || 1 > title.length){
   			$("#txttitle").focus();
			alert("请输入标题");
   		}else if($("#imgshow_big").attr("src")==""){
	    	alert("请上传封面图片");
	    }else if(content == ""){
	    	$("#content").focus();
			alert("投稿内容不能为空！");
	    }else{
			$("#contribute").submit();
		}
   }
</script>
</body>
</html>
