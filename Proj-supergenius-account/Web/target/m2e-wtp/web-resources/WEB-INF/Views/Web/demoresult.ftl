[#ftl]
<html>
<head>
<title></title>
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/WebBaseHead.ftl" /]
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/WebBaseJs.ftl" /]
<link rel="stylesheet" type="text/css" href="${basecss}/css/default/pages/tips.css" media="screen" />
<script type="text/javascript">
<!--
var interval;
$(function() { 
	interval = setInterval("ApiHandler()",1000);
});
function ApiHandler(){
	var url = "http://account.supergenius.cn/api/${apiuid}/recharge/query/${payuid}"; 
	$.get(getNoCachePath(url), {site:99}, function(data){
		var result = $.parseJSON(data).result;
		if(result != 0 && result != 2 && result != 5){
			gourl("http://account.supergenius.cn/demopay/recharge/result" + result);
			clearInterval(interval);
		}
	});
}
//-->
</script>
</head>
<body>

	<div id="wrapper">
		[#include "/WEB-INF/FtlLib/Web/WebHeadArea.ftl" /]
		<div class="container">
			<div class="tipscontent">
				[#if result == 0]
					<img src="${baseimg}/imgs/default/upno.png" alt="" />
					<span class="word">充值未完成</span>
				[#elseif result == 2]
					<img src="${baseimg}/imgs/default/upno.png" alt="" />
					<span class="word">已付款，等待系统处理</span>
				[#elseif result == 3]
					<img src="${baseimg}/imgs/default/upno.png" alt="" />
					<span class="word">参数为空</span>
				[#elseif result == 4]
					<img src="${baseimg}/imgs/default/upno.png" alt="" />
					<span class="word">apiuid验证不通过</span>
				[#elseif result == 5]
					<img src="${baseimg}/imgs/default/upno.png" alt="" />
					<span class="word">未成功发送支付请求</span>
				[#elseif result == 1]
					<img src="${baseimg}/imgs/default/upyes.png" alt="" />
					<span class="word">支付成功</span>
				[#else]
					<img src="${baseimg}/imgs/default/upno.png" alt="" />
					<span class="word">404</span>
				[/#if]
			</div>
		</div>
		[#include "/WEB-INF/FtlLib/Web/WebFootArea.ftl" /]
	</div>

</body>
</html>