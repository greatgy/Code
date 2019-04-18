[#ftl]
<html>
<head>
<meta charset="UTF-8">
	<title>demopay--付款</title>
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/pages/register.css">
<script type="text/javascript" src="${basejs}/js/jquery/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${basejs}/js/jquery/jquery-migrate-1.0.0.min.js"></script>
<script type="text/javascript" src="${basejs}/js/jquery/plugins/jquery.cookie.min.js"></script>
<script type="text/javascript" src="${basejs}/js/libs3/jquery.easing.min.js"></script>
<script type="text/javascript" src="${basejs}/js/libs3/jquery.lavalamp.min.js"></script>
<script type="text/javascript" src="${basejs}/js/base-1.0.0.js"></script>
<script type="text/javascript" src="${basejs}/js/jslib-1.0.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
<script type="text/javascript">
<!--
$(function(){
});

//-->
</script>
	
</head>
<body>
<div class="wraper">
	<div class="container">
		<form id="rechargeform" action="${base}/api/${apiuid}/recharge/${payuid}" method="post" data-beforesubmithandler="handler();" target="_blank" data-canresubmitrepeat="true" style="margin-top:40px;">
			<div class="main">
				<div class="navbar">
					输入金额：<input type="text" name="money" value="0.01">
				</div>
<!--隐藏属性-->
<input style="display:none;" name="notifyurl" type="text" value="http://account.supergenius.cn/demopay/recharge/notify" />
<input style="display:none;" name="resulturl" type="text" value="http://account.supergenius.cn/demopay/recharge/result%%s" />
<input style="display:none;" name="userip" type="text" value="192.168.1.1" />
<input style="display:none;" name="site" type="text" value="99" /><!--测试站点-->
<input style="display:none;" name="type" type="text" value="0" />
<input style="display:none;" name="useruid" type="text" value="uid11111111111111111111111111111" />
<input style="display:none;" name="username" type="text" value="测试者" />
<input style="display:none;" name="useremail" type="text" value="jasonhung_shao@163.com" />
<input style="display:none;" name="ismobile" type="text" value="false" />
<!--隐藏属性-->
				<div class="pay">
					<span>选择支付方式</span>
					<ul>
						<li>
							<div class="lileft">推荐支付</div>
							<div class="liright">
								<div class="bank">
									<input type="radio" name="banktype" value="11">
									<img src="${baseimg}/imgs/default/alipay.png" alt="支付宝" />
								</div>
								<div class="bank">
									<input type="radio" name="banktype"  value="13">
									<img src="${baseimg}/imgs/default/wxpay.png" alt="微信支付" />
								</div>
								<div class="bank">
									<input type="radio" name="banktype"  value="14">
									<img src="${baseimg}/imgs/default/paypal.jpg" alt="paypal" />
								</div>
							</div>
						</li>
						<li>
							<div class="lileft">网上银行</div>
							<div class="liright">
								<div class="bank">
									<input type="radio" name="banktype" value="0">
									<img src="${baseimg}/imgs/default/industry.png" alt="" />
								</div>
								<div class="bank">
									<input type="radio" name="banktype"  value="3">
									<img src="${baseimg}/imgs/default/agriculture.png" alt="" />
								</div>
								<div class="bank">
									<input type="radio" name="banktype"  value="4">
									<img src="${baseimg}/imgs/default/transport.png" alt="" />
								</div>
								<div class="bank">
									<input type="radio" name="banktype"  value="1">
									<img src="${baseimg}/imgs/default/business.png" alt="" />
								</div>
							</div>
							
						</li>
						<li>
							<div class="lileft">银联在线支付</div>
							<div class="liright">
								<div class="bank">
									<input type="radio" name="banktype"  value="6">
									<img src="${baseimg}/imgs/default/payonline.png" alt="" />
								</div>
								<div class="bank">
									<input type="radio" name="banktype"  value="15">
									<img src="${baseimg}/imgs/default/payeezy.png" alt="美洲银行" />
								</div>
							</div>
							
						</li>
					</ul>
					<input id="submit" class="btn" name="提交" type="submit" value="立即充值" /></div>
				</div>
			</div>	
		</form>
	</div>
</div>
<!--弹出层开始-->
<div id="alertrechargebox" style="display:none;">
	<div class="mask"></div>
	<div id="windowbg">
		<div class="confirmation">
	        <div id="purchase">
	        	<dl>
	            	<dt>请在新开网银页面完成付款。</dt>
	            	<dd>支付成功后订单状态可能会延迟更新，可稍后查看</dd>
	            </dl>
	            <div class="button">
	                <p><a href="" onclick="bankQueryResult()">已完成付款</a><a href="" onclick="bankQueryResult()">付款遇到问题</a></p>
	                <div class="clear"></div>
	            </div>
	        </div>
		</div>
	</div>
</div>
<!--弹出层结束-->
[#--myrecharge.ftl--]
<script type="text/javascript">
<!--
// 手动查询
function bankQueryResult() {
	var url = "http://account.supergenius.cn/api/${apiuid}/recharge/query/${payuid}"; 
	$.get(getNoCachePath(url), {site:99}, function(data){
		var result = $.parseJSON(data).result;
		gourl("http://account.supergenius.cn/demopay/recharge/result" + result);
		
	});
}

var interval;

function handler(){
	$('#alertrechargebox').css("display",'block');
	interval = setInterval("ApiHandler()",1000);
}

// 自动查询
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
</body>
</html>