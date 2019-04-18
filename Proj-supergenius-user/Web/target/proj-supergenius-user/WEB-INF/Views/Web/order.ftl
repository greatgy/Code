[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>订单管理</title>
	<meta name="Keywords" content="订单管理" />
	<meta name="Description" content="订单管理" />
	<link rel="stylesheet" type="text/css" href="${basecss}/css/default/order.css">
</head>
<body>
    <div class="wraper">
		<div class="container">
		  <div class="main">
			<div class="myorders">
				<h2>我的订单</h2>
				<ul class="[#if member??] orderlistred [#else]orderlist[/#if]">
					<div class="[#if member??] orderlisttit1 [#else]orderlisttit[/#if]">
						<div class="part01 partitem">流水号</div>
						<div class="part02 partitem">订单商品</div>
						<div class="part03 partitem">类型</div>
						<div class="part04 partitem">金额(元)</div>
						<div class="part05 partitem">下单时间</div>
						<div class="part06 partitem">状态</div>
						[#--<div class="part07 partitem">操作</div>--]
					</div>
					[#if not_exist??]
						<p style="text-align:center;margin-top:130px;font-size:15px;color:#797979;">您还没有订单记录~</p>
					[#else]
						[#list list as item]
							<li>
							<div class="part01 orderlitem">${item.sn}</div>
							<div class="part02 orderlitem" title="${(item.orderGoods?first).name}">
								[#if (item.orderGoods?first).type == 2]
									[#if (item.orderGoods?first).refuid??]
										<a href="${managerbase}/my/pk/detail/${(item.orderGoods?first).refuid}" target="_blank">${(item.orderGoods?first).name}</a>
									[#else]
										${(item.orderGoods?first).name!''}
									[/#if]
							    [#elseif (item.orderGoods?first).type == 1]
							    	[#if (item.orderGoods?first).refuid??]
										<a href="${managerbase}/my/video/detail/${(item.orderGoods?first).refuid}" target="_blank">${(item.orderGoods?first).name}</a>
									[#else]
										${(item.orderGoods?first).name!''}
									[/#if]
								[/#if]
							</div>
							<div class="part03 orderlitem">${(item.orderGoods?first).typeName}</div>
							<div class="part04 orderlitem">${(item.money)!?string("0.00")}</div>
							<div class="part05 orderlitem">${item.createtime.toString("YYYY-MM-dd HH:mm:ss")}</div>
							<div class="part06 orderlitem">${item.stateName}</div>
							</li>
						[/#list]
					[/#if]
				</ul>
				[#if !not_exist??]
					[@p.Pager pager /]
				[/#if]
			</div>
		  </div>
		</div>
	</div>
</body>
</html>