[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>支付界面</title>
	<meta name="Keywords" content="超天才收银台，订单支付" />
	<meta name="Description" content="超天才收银台，订单支付" />
	<meta name="Copyright" content="2017 supergenius.cn, All Right Reserved,Inc." />
	<meta name="renderer" content="webkit">
	<link href="http://s.supergenius.cn/imgs/sys/favicon.ico" rel="shortcut icon" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/reset.css">
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/wxpay.css">
	<script type="text/javascript" src="${basejs}/js/jquery/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/jquery/jquery-migrate-1.0.0.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/jquery/plugins/jquery.cookie.min.js"></script>
	<script type="text/javascript" src="${basejs}/js/base-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/jslib-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
</head>
<body>
	<div class="wraper">
		<div class="container">
			<div class="main_top">
				<h4>
					<img src="${baseimg}/imgs/web/logo.png" class="logoimg" />
					收银台
				</h4>
			</div>
			<div class="main">
				<div class="orderSection">
					<div class="tip">请您及时付款，以便订单尽快处理！订单号：${account.accountsn}</div>
					<div class="money">
						应付金额<em class="pay">${account.money}</em>元
					</div>
				</div>
				<div class="orderDetails">
					[#-- <span class="address">收货地址：北京海淀区五环到六环之间海淀区上地五街xxxx</span>
					<span class="person">收货人：刘先生早9点前晚6点后 <i class="phonenumber">189xxxx1234</i></span> --]
					<span class="goods">商品名称：${account.typeName}</span>
				</div>
				<div class="wxpaySection">
					<h3>
						微信支付
						[#if err]
							<span class="redmind">页面出现错误！[#-- 请<a class="refresh">刷新</a>页面重新获取二维码 --]</span>
						[#else]
							
						[/#if]
					</h3>
					<div class="codeSection">
						<div class="codeleft">
							<img class="wxcode" src="${base}/api/image/qrcode/307/305?data=${url}" />
							<img class="paytip" src="${baseimg}/imgs/default/paytip.png" />
						</div>
						<div class="coderight">
							<img src="${baseimg}/imgs/default/wxstep.png" class="teachstep" />
						</div>
					</div>
				</div>
				<div class="copyright">
					copyright©2013-2017 超天才网supergenius.cn版权所有
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
<!--

// 自动查询
function ApiHandler(){
	var url = "http://account.supergenius.cn/api/${apiuid}/recharge/query/${payuid}"; 
	$.get(getNoCachePath(url), {site: ${site}}, function(data) {
		var result = $.parseJSON(data).result;
		if(result == 1){
			gourl("${resulturl}");
		} else {
			// todo 跳转到支付失败页
		}
	});
}
$(function(){
	setInterval("ApiHandler()",1000);
});

//-->
</script>