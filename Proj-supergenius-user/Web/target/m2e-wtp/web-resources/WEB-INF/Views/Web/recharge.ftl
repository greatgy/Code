[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>收支管理--账户充值</title>
	<meta name="Keywords" content="收支管理，账户充值" />
	<meta name="Description" content="收支管理，账户充值" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/revenue.css">
	<style type="text/css">
		.paywaysright .bank {
		    float: none;
		}
	</style>
<script type="text/javascript">
$(function() {
	$("#txtmoney").focus();
});
</script>
</head>
<body>
	<div class="wraper">
		<div class="container">
			<div class="main">
				<div class="revenueleft [#if !member??]containerleftgreen[/#if]">
					<h2>收支管理</h2>
					<ul class="revenuelist">
						<li>
							<a href="${base}/my/account/info" class="baseInfor">
								<span>账户基本信息</span>
							</a>
						</li>
						<li class="activeli">
							<a href="${base}/my/account/recharge" class="acCharge">
								<span>账户充值</span>
							</a>
						</li>
						<li>
							<a href="${base}/my/account/rechargedetail" class="chargeRecord">
								<span>充值记录</span>
							</a>
						</li>
						<li>
							<a href="${base}/my/account/tradedetail" class="revRecord">
								<span>收支记录</span>
							</a>
						</li>
					</ul>
				</div>
				<form id="rechargeform" class="revenueright" action="${accountbase}/api/${apiuid}/recharge/${payuid}" method="post" data-beforesubmithandler="handler();" target="_blank" data-canresubmitrepeat="true">
					<input type="hidden" name="notifyurl" type="text" value="${base}/api/account/recharge/notify" />
					<input type="hidden" name="resulturl" type="text" value="${base}/my/account/recharge/result%%s" />
					<input type="hidden" name="userip" type="text" value="${userip}" />
					<input type="hidden" name="site" type="text" value="${site}" />
					<input type="hidden" name="type" type="text" value="${type}" />
					<input type="hidden" name="useruid" type="text" value="${me.uid}" />
					<input type="hidden" name="username" type="text" value="${me.name}" />
					<input type="hidden" name="useremail" type="text" value="${me.email}" />
					<h2>账户充值</h2>
					<div class="rechargement">
						<ul class="revenuerigmain rechargerigtop">
							<li>
								您当前的账户余额为：
								<span class="nowmoney">${me.account!?string("0.00")}&nbsp;&nbsp;</span>元
							</li>
							<li>
								充值金额为：
								<input type="text" id="txtmoney" placeholder="请输入大于1元的金额" name="money" isnotempty="充值金额不能为空" isnum="请输入正确的金额" customreg="^[1-9]\d*(\.\d+)?$|请输入大于1元的金额" />&nbsp;&nbsp;元
								<span id="errtxtmoney" class="hd"><span id="errmsgtxtmoney" class="errmsgtxtmoney" style="color: red;"></span></span>
								<span id="oktxtmoney" class="hd"></span>
							</li>
						</ul>
						<div class="rechargerigmid [#if !member??]containerrightgreen[/#if]">
							<h3 style="display: inline;">选择支付方式</h3> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="selectbanktype" class="hd" style="color: red; font-size: 13px;">请选择支付方式</span>
							<ul class="payways" >
								<li>
									<div class="paywaysright">
										<div class="bank">
											<input type="radio" id="alipay" name="banktype" value="11"/>
											<label for="alipay"><img style="width: 108px;height: 28px;padding: 4px 8px;border: 1px solid #aeaeae;" src="${baseimg}/imgs/default/alipay.png" alt="支付宝" /></label>
										</div>
										<div class="bank">
											<input type="radio" id="wxpay" name="banktype" value="13"/>
											<label for="wxpay"><img style="width: 108px;height: 28px;padding: 4px 8px;border: 1px solid #aeaeae;" src="${baseimg}/imgs/default/wxpay.png" alt="微信支付" /></label>
										</div>
										[#-- 
										<div class="bank">
											<input type="radio" id="nongye" name="banktype" value="3"/>
											<label for="nongye"><img src="${baseimg}/imgs/default/nongye.png" alt="" /></label>
										</div>
										<div class="bank">
											<input type="radio" id="jiaotong" name="banktype" value="4"/>
											<label for="jiaotong"><img src="${baseimg}/imgs/default/jiaotong.png" alt="" /></label>
										</div>
										 --]
										<div class="bank">
											<input type="radio" id="onlinepay" name="banktype" value="6"/>
											<label for="onlinepay"><img style="width: 108px;height: 28px;padding: 4px 8px;border: 1px solid #aeaeae;" src="${baseimg}/imgs/default/online.png" alt="银联" /></label>
										</div>
										<div class="bank">
											<input type="radio" id="gongshang" name="banktype" value="0"/>
											<label for="gongshang"><img style="width: 108px;height: 28px;padding: 4px 8px;border: 1px solid #aeaeae;" src="${baseimg}/imgs/default/gongshang.png" alt="工商" /></label>
										</div>
										<div class="bank">
											<input type="radio" name="banktype" id="zhaoshang" value="1"/>
											<label for="zhaoshang"><img style="width: 108px;height: 28px;padding: 4px 8px;border: 1px solid #aeaeae;" src="${baseimg}/imgs/default/zhaoshang.png" alt="招商" /></label>
										</div>
									</div>
								</li>
							</ul>
							<a href="javascript:void(0);" id="submit" class="pay">立即充值</a>
						</div>
						<div class="rechargerigbot">
							<h3>网银支付常见问题</h3>
							<ul class="payquestions">
								<li>1、问：网银支付支持哪些银行？</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;答：目前暂时只支持工商银行、农业银行、招商银行、交通银行4家银行进行支付。</li>
								<br/>
								<li>2、问：网银支付还需要将银行卡开通网上银行功能么？</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;答：您如果通过选定网上银行则需要开通网银。</li>
								<br/>
								<li>3、问：我银行选择错了，支付的时候还能改吗？</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;答：您选择网银支付，如您选择了中国银行进行支付，提交订单后想换成其他银行，可以在支付时候修改支付银行。</li>
								<br/>
								<li>4、问：如果多次出现支付失败怎么办？</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;答：<a href="http://www.supergenius.cn/contact" target="_blank">请联系客服处理</a>。</li>
							</ul>
						</div>
					</div>
				</form>
			</div>
			<div class="mask"></div>
			<div class="maskcontent rechargecover">
				<div class="rechargecoverment">
					<h1>
						<img src="${baseimg}/imgs/default/recharsuccess.png" alt="" />
						请在新开网银页面完成付款
					</h1>
					<div class="tipreturn">支付成功后订单状态可能会延迟更新，可稍后查看</div>
					<div class="recharbtns">
					<p><a href="javascript:;" onclick="bankQueryResult()" class="finish">已完成付款</a><a href="javascript:;" class="default" onclick="bankQueryResult()">付款遇到问题</a></p>
					</div>
				</div>
			</div>
		</div>
<script src="${basejs}/js/libs/mask.js"></script>
<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
</div>
<script type="text/javascript">
<!--
$(function() {
	$(".payways input[name='banktype']").change(function() {
		if ($("input[name='banktype']:checked").length > 0) {
			$("#selectbanktype").addClass("hd");
		} else {
			$("#selectbanktype").removeClass("hd");
		}
	});
	$("#submit").click(function() {
		var regs = [/^[1-9]\d*(\.\d+)?/];
		var flag = true;
		if ( ! regs[0].test($("#txtmoney").val())) {
			flag = false;
		}
		if ($("#txtmoney").val() < 1) {
			flag = false;
		}
		if ($(".payways input[name='banktype']:checked").val() == undefined) {
			$("#selectbanktype").removeClass("hd");
			flag = false;
		}
		if (flag) {
			$("#rechargeform").submit();
			$(".maskcontent").show();
			$(".mask").show();
			$("#selectbanktype").addClass("hd");
			$("#errtxtmoney").addClass("hd");
		}
	});
});

// 手动查询
function bankQueryResult() {
	var url = "${base}/api/${apiuid}/recharge/query/${payuid}"; 
	$.get(getNoCachePath(url), {site:${site}}, function(data){
		var jsondata = $.parseJSON(data);
		if (jsondata.errs != undefined) {
			gourl("${base}/my/account/recharge/result9");
			clearInterval(interval);
			return;
		}
		var result = jsondata.result;
		gourl("${base}/my/account/recharge/result" + result+"?money="+$("#txtmoney").val());
		clearInterval(interval);
	});
}
var interval;
function handler(){
	interval = setInterval("ApiHandler()",5000);
}

// 自动查询
function ApiHandler(){
	var url = "${base}/api/${apiuid}/recharge/query/${payuid}"; 
	$.get(getNoCachePath(url), {site:${site}}, function(data){
		var jsondata = $.parseJSON(data);
		if (jsondata.errs != undefined) {
			gourl("${base}/my/account/recharge/result9");
			clearInterval(interval);
			return;
		}
		var result = jsondata.result;
		if(result != 0 && result != 2){
			gourl("${base}/my/account/recharge/result" + result+"?money="+$("#txtmoney").val());
			clearInterval(interval);
		}
	});
}
//-->
</script>
</body>
</html>