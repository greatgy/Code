[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>收支管理--账户基本信息</title>
	<meta name="Keywords" content="收支管理，账户基本信息" />
	<meta name="Description" content="收支管理，账户基本信息" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/revenue.css">
<script type="text/javascript">
</script>
</head>
<body>
	<div class="wraper">
		<div class="container">
			<div class="main">
				<div class="revenueleft [#if !member??]containerleftgreen[/#if]">
					<h2>收支管理</h2>
					<ul class="revenuelist">
						<li class="activeli">
							<a href="${base}/my/account/info" class="baseInfor">
								<span>账户基本信息</span>
							</a>
						</li>
						<li>
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
				<div class="revenueright [#if !member??]containerrightgreen[/#if] ">
					<h2>账户信息</h2>
					<ul class="revenuerigmain">
						<li>
							账户余额：
							<span class="leftmoney">${me.account!?string("0.00")}&nbsp;元</span>
						</li>
						<li>
							累计支出：
							<span class="totalout">${me.totalpay!?string("0.00")}&nbsp;元</span>
						</li>
						<li>
							累计收入：
							<span class="totalin">${me.totalincome!?string("0.00")}&nbsp;元</span>
						</li>
						<li>
							开户时间：
							<span class="opentime">${(me.createtime).toString("YYYY年MM月dd日HH:mm")}</span>
						</li>
					</ul>
					<div class="btns">
						<a href="${base}/my/account/recharge" class="recharge">立即充值</a>
						<a href="${base}/my/user/setpaypwd" class="changepw">修改支付密码</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>