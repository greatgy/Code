[#ftl]
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title!'上传视频'}</title>
<link href="${basecss}/css/mobile/default/contribute11.css" type="text/css" rel="stylesheet" />
<script src="https://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript" src="${basejs}/js/libs3/swiper.jquery.min.js"></script>
<script type="text/javascript" src="${basejs}/js/libs/jquery.form.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
<script type="text/javascript">
	
		var origin1 = '';
	 function preview(file) {
		  var prevDiv = document.getElementById('preview');
		 	var formData = new FormData();
			  formData.append("fileimg", $("#userface")[0].files[0]);
				  $.ajax({
					   url: '${base}/m/uploadimg',
					   type: 'post',
					   data: formData,
					   processData: false,
					   contentType: false,
					   success: function (msg) {
						origin1 = msg.split(",")[0];
					   $("#contributeimg").val(msg);
					   }
				  });
		  if (file.files && file.files[0]) {
			   var reader = new FileReader();
			   reader.onload = function (evt) {
			    prevDiv.innerHTML = '<img class="uploadimg"  src="' + evt.target.result + '" />';
			   }
		   reader.readAsDataURL(file.files[0]);
		  } else {
		   prevDiv.innerHTML = '<div class="uploadimg img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
		  }
	 }
	    
</script>
<script type="text/javascript" src="${basejs}/js/mobile/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
<!--

$(function(){
	CKEDITOR.replace('content',{
        toolbar : 'MyToolbar2',
        filebrowserFlashUploadUrl : "${base}/ajax/ckeditor/flash",
    });
    
    CKEDITOR.on('instanceReady', function (e) {
        $("#cke_17, #cke_18").hide();
    })

    $(document).on('click', "input[id^='course']", function(e){
    		$.ajax({
				type:"GET",
				url:'${base}/m/life/ajax/lifecourse/subjects',
				data:{grade:$(this).val()},
				async:false,
				success:function(data){
					var list = JSON.parse(data);
					var html = '<label class="mb_30 subject"><span>选择科目:</span></label>';
					html += '<div class="subjects grade">';
					for(var i= 0;i<list.length;i++){
						html += '<div class="grades">'
						if(i == 0){
							html += '<input type="radio" id="addcourse_'+list[i].sid+'" class="addcourse_'+list[i].sid+'" name="subjectid" value="'+list[i].sid+'" checked/><label for="addcourse_'+list[i].sid+'">'+list[i].name+'</label>';
						}else{
							html += '<input type="radio" id="addcourse_'+list[i].sid+'" class="addcourse_'+list[i].sid+'" name="subjectid" value="'+list[i].sid+'"/><label for="addcourse_'+list[i].sid+'">'+list[i].name+'</label>';
						}
						html += '</div>';
					}
					html += '</div>';
					$("#selectsubject").html(html);
					return false;
				}
			});
	    });
	$("#courseaddgrade_6").click();
});
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
   
//-->
</script>
</head>
<body>
<div class="contribute_box" style="margin-bottom:10px;clear:both;">
    <div class="contribute_800" style="padding-bottom:30px">
    <form id="contribute" class="draft" action="${base}/m/my/video/add_${cid?c}" method="post" enctype="multipart/form-data">
    	<div id="form">	
    		<label class="mb_30"><span class="red">*</span>标题:</label>
	        <input class="draft_input ml_35" type="text" id="txttitle" name="title" placeholder="输入文章标题"/>
            <br>
	        <label class="mb_30"><span class="red">*</span>配图:</label>
	        
		<input id="userface" type="file" onchange="preview(this)" style="width: 62%;display:  inline-block;"><br>
	    <input id="contributeimg" name="contributeimg" type="text" value="" class="hd"/>
		<div id="preview" style="margin-left: 30%;width: 100%;margin-bottom: 15px;"></div>
	        
	        <div id="addgrade">
	            <label class="mb_30"><span>选择年级:</span></label>
	            <div class="grade">
	            	<div class="grades"><input type="radio" id="courseaddgrade_6" name="grade" value="1" checked/><label for="courseaddgrade_6">六年级</label></div>
	                <div class="grades"><input type="radio" id="courseaddgrade_7" name="grade" value="2" /><label for="courseaddgrade_7">七年级</label></div>
	                <div class="grades"><input type="radio" id="courseaddgrade_8" name="grade" value="4" /><label for="courseaddgrade_8">八年级</label></div>
	                <div class="grades"><input type="radio" id="courseaddgrade_9" name="grade" value="8" /><label for="courseaddgrade_9">九年级</label></div>
                </div>
	        </div>
	   		<div id="selectsubject">
	            <label class="mb_30 subject"><span>选择科目:</span></label>
	        </div>
	       <br>
	       <label class="mb_30"><span class="red">*</span>成果附件:</label>
	       <div style="width:100%;display: inline-block;vertical-align: top; margin-bottom: 20px">
		    	<textarea class="draft_input2" id="content" name="content" ></textarea>
		   </div>
		   <br>
	      <input type="button" class="draft_tg right" onclick="publish(1)" value="确认发布"></input>
	      <input type="hidden" name="type" value="0"/>
      </div>
    </form>
  </div>
</div>


</body>
</html>
