CKEDITOR.dialog.add('musicupload', function(editor) { // 要和plugin.js 中的command 一致
	var musicurl = '';
	var escape = function(value) {
		return value;
	};
	return {
		title : '上传音频', 
		minWidth : 400,
		minHeight : 100,
		contents : [ { 
			id : 'Mupload',
			name : 'Mupload',
			label : '上传音频',
			title : '上传音频',
			elements : [{
				id : 'html1',
				type : 'html',
				html : '<form id="musicform" class="draft" method="post"><input type="file" name="localfile" accept="audio/*" id="local_file"/></form>'
			},{
				id : 'uploadmusic',
				type : 'button',
				label : '上传音乐',
				filebrowser:"info:src",
				onClick : function(){
					if ($("#local_file").val().length<1) {
						alert("没有选择文件")
						return false;
					}
					$("#info").css('display','block');
					//判断音乐文件的大小，单位KB	
					var fileSize = document.getElementById("local_file").files[0].size / 1024;  
					if(fileSize > 1024*10){  
						alert("文件太大")
					    return false;
					}
					console.log($('#musicform'))
					$('#musicform').ajaxSubmit( 
			            {  
			                url: g.base+'/m/ajax/upload/music',  
			                type: 'post',  
			                dataType: 'json',  
			                beforeSubmit: function () {},  
			                success: function (data) { 
			                	if(data.result == "success") {
			                		//alert("上传成功");
			                		//$("#info").css('display','none');
			                		$("#info").html("上传成功");
			                		musicurl = data.localUrl;
			                	}
			                },  
			                clearForm: false,
			                resetForm: false
			            }); 
				}
			},{
				id : 'info',
				type : 'html',
				html : '<span id="info" style="display:none;margin-left: 85px;margin-top: -30px;color: #ff0000;">上传中……</span>'
			}]
		} ],
		onOk : function() {
			var url = htmlEncode(g.webimg + musicurl);
			var element = CKEDITOR.dom.element.createFromHtml( '<div class="phoneaudiodiv"><img class="phoneaudioimg" id="imgAudio" data-type="true" src="'+ g.webimg + origin1 +'" alt="" /><audio id="audioEle" style="width:100%;" src="'+url+'" controls="controls"></audio ></div>');
			CKEDITOR.instances.content.insertElement(element);
		}
	};
});
function htmlEncode(str) {
	var temp = document.createElement("div");
	(temp.textContent != null) ? (temp.textContent = str)
			: (temp.innerText = str);
	var output = temp.innerHTML;
	temp = null;
	return output;
}