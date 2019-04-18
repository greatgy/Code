[#ftl]
<div class="header_tou clear">
    [#include "/WEB-INF/FtlLib/Web/WebHeadArea.ftl" /]
    <div class="top_02">
        <div class="top_main2  [#if !member??]top_main2_green[/#if]"> 
            <div class="top_22">
                <span class="vipcenter">会员中心</span>
                <ul class="box-nav" id="box-nav">
                    <li><a href="${base}/my/home">我的超天才</a></li>
                    <li><a href="${base}/my/user">账号管理</a></li>
					<li><a href="${base}/my/account">收支管理</a></li>
                    <li><a href="${base}/my/order">我的订单</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>