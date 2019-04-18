[#ftl]
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>发布问题</title>
	<meta name="Keywords" content="天才人生，发布问题" />
	<meta name="Description" content="天才人生，发布问题" />
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
</head>
<body>
	<div class="askQuestionBox questionBox [#if me == ''] pop-up [/#if]">
		<h3>提问</h3>
		<form action="${base}/problem/addproblem" class="questionForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="cid" value="${cid?c}">
			<input type="text" placeholder="一句话说明问题，并以句号结尾" name="title" id="txttitle">
			<p class="state">问题说明（可选）</p>
			<textarea placeholder="问题描述越清楚，越能带来专业的回答" name="content"></textarea>
			<a class="cancelBtn submitBtn" href="/dream/moreQuestion.html">取消</a>
			<a class="submitBtn" onclick="publish()">发布提问</a>
			[#if cid != 1073741824]
			<div class="hidename">
				<input type="checkbox" id="hideName" name="isnick"/>
			    <label for="hideName">匿名</label>
			</div>
			[/#if]
		</form>
	</div>
	<script type="text/javascript">
	    
	   function publish(){
	   		var title = $("#txttitle").val();
			if(title.length > 50 || 1 > title.length){
	   			$("#txttitle").focus();
				alert("请输入标题");
	   		} else{
				$(".questionForm").submit();
			}
	   }
	</script>
</body>
</html>