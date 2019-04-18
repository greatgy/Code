[#ftl strip_whitespace=true]
[#escape x as x?html]
[#--
 * Public.ftl
 *
 * @author architect.bian
 * @since 1.0
 --]
[#--
 * 分页 可根据totalCount的有值来判断是否显示页码
 * pager 分页数据
 * @author architect.bian
 * @since 1.0
 --]
[#macro Pager pager]
[#if pager?? && pager.totalCount > 0]
<div class="pagenum">
	<ul>
	[#assign query = "" /]
	[#if request.getQueryString()??]
		[#assign query = request.getQueryString()?split("num")[0] /]
		[#if query?length > 0]
			[#assign query = query + "&" /]
		[/#if]
	[/#if]
	[#if pager.pageCount > 1]
		[#if pager.pageNumber > 1]
			<li><a href="?${query}num=${pager.pageNumber - 1}">上一页</a></li>
		[/#if]
		[#list -2..3 as i]
			[#assign num = pager.pageNumber + i /]
			[#if pager.pageNumber <= 2]
				[#assign num = num + 1 /]
			[/#if]
			[#if num > 0 && num <= pager.pageCount]
				<li><a href="?${query}num=${num}" [#if pager.pageNumber == num] class="here"[/#if]>${num}</a></li>
			[/#if]
		[/#list]
		[#if pager.pageNumber < pager.pageCount]
			<li><a href="?${query}num=${pager.pageNumber + 1}">下一页</a></li>
		[/#if]
	[/#if]
	</ul>
</div>
[#elseif pager.listSize &gt;= pager.pageSize || (pager.pageNumber)! &gt; 1]
<div class="pagenum">
<ul>
[#if pager.pageNumber! &gt; 1]
<li><a class="last" target="_self" href="?[#if request.getQueryString()??]${request.getQueryString()?split("num")[0]}&[/#if]num=${pager.pageNumber - 1}">上一页</a></li>
[#else]
<li><a class="last" target="_self" href="?[#if request.getQueryString()??]${request.getQueryString()}[/#if]">上一页</a></li>
[/#if]
[#if pager.listSize &gt;= pager.pageSize]
<li><a class="next" target="_self" href="?[#if request.getQueryString()??]${request.getQueryString()?split("num")[0]}&[/#if]num=${pager.pageNumber + 1}">下一页</a></li>
[/#if]
</ul>
</div>
[/#if]
[/#macro]

[#--
 * 分享
 * @author architect.bian
 * @since 1.0
 --]
[#macro share]
<div class="bdsharebuttonbox">
	<a href="#" class="bds_more" data-cmd="more"></a>
	<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
	<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
	<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
	<a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
	<a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
</div>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
[/#macro]
 [/#escape]