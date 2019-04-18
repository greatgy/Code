[#ftl]
<html>
<head>
<meta charset="UTF-8">
	<title>demopay--退款</title>
	<script type="text/javascript" src="${basejs}/js/jquery/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/base-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/jslib-1.0.0.js"></script>
</head>
<body>
<div class="wraper">
	<div class="container">
		<div class="account">
			<table>
				[#if list??]
					[#list list as account]
						<tr>
							<td>${account.accountsn}</td>
							<td>${account.createtimeStr}</td>
							<td><a onclick="apply('${account.payuid}')">退款</a></td>
						</tr>	
					[/#list]
				[/#if]
			</table>
		</div>
		<br/>
		<br/>
		<br/>
		<div class="refund">
			<table>
				[#if list2??]
					[#list list2 as refund]
						<tr>
							<td>${refund.out_refund_no}</td>
							<td>${refund.createtimeStr}</td>
							<td><a onclick="agree('${refund.uid}')">同意退款</a></td>
						</tr>	
					[/#list]
				[/#if]
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
// 申请退款
function apply(payuid){
	var url = "http://account.supergenius.cn/api/uid11111111111111111111111111111/refund/" + payuid; 
	$.get(getNoCachePath(url), {site:99}, function(data){
		var result = $.parseJSON(data).result;
		if(result == 1){
			alert("refund is success");
		}else{
			alert(result);
		}
		gourl(window.location.href);
	});
}

// 同意退款
function agree(refunduid){
	var url = "http://account.supergenius.cn/agreerefund/" + refunduid; 
	$.get(getNoCachePath(url), function(data){
		var result = $.parseJSON(data).result;
		if(result == 1){
			alert("refund is success");
		}else{
			alert(result);
		}
		gourl(window.location.href);
	});
}
//-->
</script>
</body>
</html>