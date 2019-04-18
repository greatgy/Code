[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>收支管理--账户充值结果</title>
	<meta name="Keywords" content="收支管理，账户充值成功" />
	<meta name="Description" content="收支管理，账户充值成功" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/revenue.css">
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
				<div class="revenueright">
					<div class="recharsuccessmid">
						<div style="text-align: center;margin-top: 200px;">
							[#if result == 1]
								<img src="${baseimg}/imgs/default/upyes.png" alt="" />
								<span class="word" style="font-size:20px;">支付成功</span>
								<br/>
								<br/>
								<br/>
								[#if me??]
									<div class="returnsuccess">[#if money??]充值的金额：<b>${money}</b>&nbsp;元；[/#if]当前余额：<b>${me.account!?string('0.00')}</b>&nbsp;元</div>
									<a href="${base}/my/account/rechargedetail" class="lookrecards" style="margin-left: -20px;">查看充值记录</a>
								[/#if]
							[#else]
								<img src="${baseimg}/imgs/default/upno.png" alt="" />
								<span class="word">充值失败，请重新充值！</span>
							[/#if]
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>