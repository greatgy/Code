[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>收支管理--充值记录</title>
	<meta name="Keywords" content="收支管理，充值记录" />
	<meta name="Description" content="收支管理，充值记录" />
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
						<li class="activeli">
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
				<div class="revenueright recordsright">
					<h2>账户充值记录</h2>
					[#if not_exist??]
						<p style="text-align:center;margin-top:200px;font-size:15px;color:#797979;">您还没有充值记录~</p>
					[#else]	
					<table class="recordslist">
						<tr class="thtr">
							<th class="th01">流水号</th>
							<th class="th02">充值时间</th>
							<th class="th03">金额</th>
							<th class="th04">资金渠道</th>
							<th class="th05">状态</th>
						</tr>
						[#list list as item]
						<tr>
							<td class="th01">${item.accountsn}</td>
							<td class="th02">${item.createtime.toString("YYYY年MM月dd日 HH:mm:ss")}</td>
							<td class="th03">${item.money!?string('0.00')}&nbsp;元</td>
							<td class="th04">${item.bankName}</td>
							<td class="th04">${item.stateName}</td>
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