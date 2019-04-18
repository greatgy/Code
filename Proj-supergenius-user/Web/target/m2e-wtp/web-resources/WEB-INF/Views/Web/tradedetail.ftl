[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>收支管理--收支记录</title>
	<meta name="Keywords" content="收支管理，收支记录" />
	<meta name="Description" content="收支管理，收支记录" />
    <link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css">
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
						<li class="activeli">
							<a href="${base}/my/account/tradedetail" class="revRecord">
								<span>收支记录</span>
							</a>
						</li>
					</ul>
				</div>
				<div class="revenueright recordsright">
					<h2>账户收支记录</h2>
					[#if not_exist??]
						<p style="text-align:center;margin-top:200px;font-size:15px;color:#797979;">您还没有收支记录~</p>
					[#else]
					<table class="recordslist revenueslist">
						<tr class="thtr">
							<th class="th01">帐号收支记录</th>
							<th class="th02">交易日期</th>
							<th class="th03">名称</th>
							<th class="th04">金额</th>
							<th class="th05">收支</th>
							<th class="th06">余额</th>
							<th class="th07">备注</th>
						</tr>
						[#list list as item]
						<tr>
							<td class="th01">${item.sn}</td>
							<td class="th02">${item.createtime.toString("YYYY年MM月dd日 HH:mm:ss")}</td>
							<td class="th03">${item.name}</td>
							[#if item.isincome == true]
							<td class="th04 recive">${item.money!?string("0.00")}&nbsp;元</td>
							<td class="th05 recive">收入</td>
							[#else]
							<td class="th04 out">${item.money!?string("0.00")}&nbsp;元</td>
							<td class="th05 out">支出</td>
							[/#if]
							<td class="th06">${item.accountcurr!?string("0.00")}&nbsp;元</td>
							<td class="th07">${item.memo!"无"}</td>
						</tr>
						[/#list]
					</table>
					 [@p.Pager pager /]
					 [/#if]
					<div class="page"></div>
				</div>
			</div>
		</div>
		<script>
			$(".recordslist tr:even").css("background","#fafafa");
			$(".recordslist tr:odd").css("background","#fff");
		</script>
	</div>
</body>
</html>