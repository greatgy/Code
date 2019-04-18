[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>搜索</title>
	<meta name="Keywords" content="搜索" />
	<meta name="Description" content="搜索" />
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
</head>
<body>
	<div class="containerBox">
		<div class="lead">
			<a href="#" class="topLogo"><img src="${baseimg}/imgs/default/logo.png" alt=""></a>
		</div>
		<form action="${base}/search" onsubmit ="return validate()">
			<input type="text" name="content" id="searchcontent"/>
			<button class="submit" >搜索</button>
		</form>
		<div class="hotSearch">
			<h3><img src="${baseimg}/imgs/default/hot.png" />热门搜索</h3>
			<ul class="searchitem">
				[#if list??]
					[#list list as item]
						<li>
							<a href="${base}/search?content=${item.content}&uid=${item.uid}">${item.content}</a></li>
						</li>
					[/#list]
				[/#if]	
			</ul>
		</div>
		<div class="hotSearch">
			<h3><img src="${baseimg}/imgs/default/hot.png" />热门推荐</h3>
			<div class="recommend">
				[#if ADContent??]
					[#list ADContent as content]
						<a href="${content.originurl}" class="member">
							<img src="${webimg}${content.content}" />
						</a>
					[/#list]
				[/#if]
			</div>
		</div>
	</div>
	<script type="text/javascript">
	<!--
	function validate(){
			if ($("#searchcontent").val().trim() == "") {
				alert("请输入内容");
				$("#searchcontent").focus();
				return false;
			}
			return true;
		}
	-->
	</script>
</body>
</html>