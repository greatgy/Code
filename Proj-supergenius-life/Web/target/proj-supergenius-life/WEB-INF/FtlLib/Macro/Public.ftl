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
		[#if pager.pageNumber > 3]
			<li><a href="?${query}num=1">首页</a></li>
		[/#if]
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
			<li><a href="?${query}num=${pager.pageCount}">尾页</a></li>
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
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='${base}/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
[/#macro]

[#--
 * 弹框登录
 * @author YuYingJie
 * @since 1.0
 --]
[#macro login]
	<div class="mask" id="loginmask"></div>
	<div class="maskcontentlogin masklogin" id="loginshow" style="position: fixed;z-index: 1001;display: none;">
		<div class="logincontainer" onkeydown="keyLogin();">
			<a href="javascript:;" class="close"><img src="${baseimg}/imgs/default/no.png" alt="" /></a>
			<div class="logincontent">
				<h2>
					<img src="${baseimg}/imgs/default/login_logo.png" alt="" />
					登录
				</h2>
				<form action="" class="inputs">
					<ul class="inputsul">
						<li>
							<label for="usersinform" class="uname"><img src="${baseimg}/imgs/default/userbefore.png" alt="" /></label>
							<input type="text" id="usersinform" class="usersinform" maxlength="255" placeholder="邮箱/手机号" name="usersn" oninput="loginButton();"/>
						</li>
						<li>
							<label for="psword" class="upswd"><img src="${baseimg}/imgs/default/psword.png" alt="" /></label>
							<input type="password" class="psword" id="psword" maxlength="25" placeholder="请输入密码" name="password" oninput="loginButton();"/>
						</li>
					</ul>
					<div class="logindetail">
						<input type="checkbox" name="a" style="margin-top: 3px;" /><span id="autologin" style="cursor:default">自动登录</span>
						<a href="${userbase}/resetpwd/usersn" class="forgetpw" target="_blank">忘记密码？</a>
					</div>
					<div class="warninginform">
						<img src="${baseimg}/imgs/default/waring.png" alt="" />
						您输入的账号或密码不匹配，请重新输入
					</div>
					<a href="javascript:;" class="logindisable loginbut">登录</a>
					<a href="javascript:;" class="loginlink loginbut">登录</a> 
				</form>
				<div class="registerlink">
					<a href="${userbase}/register" target="_blank">注册</a>
				</div>
				<div class="otherLink">
					<a href="${userbase}/login/wx" target="_blank"><img class="imglogin" src="${baseimg}/imgs/default/bweixin.png" alt="微信" title="微信" /></a>
					<a href="${userbase}/login/qq" target="_blank"><img class="imglogin" src="${baseimg}/imgs/default/bqq.png" alt="qq" title="qq" /></a>
					<a href="${userbase}/login/weibo" target="_blank"><img class="imglogin" src="${baseimg}/imgs/default/bsina.png" alt="微博" title="微博" /></a>
				</div>
			</div>
		</div>
	</div>
	<style>
		/* 登录框样式 */

		.mask {
			width: 100%;
			position: absolute;
			top: 0;
			left: 0;
			z-index: 1001;
			background: #000;
			background: rgba(0,0,0,0.5);
			opacity: .8;
			filter: Alpha(opacity=50);
		 	display: none;
		}
		.masklogin {
			background: #dadada;
			opacity: 1;
		    padding: 10px 15px;
			border-radius: 10px;
			overflow: hidden;
		}
		img.imglogin {
		    width: 50px;
		    margin: 20px 37px 10px;
		}
		.otherLink {
		    text-align: center;
		}
		.logincontainer {
			width: 480px;
			min-height: 425px;
			overflow: hidden;
			background: #fff;
			margin: 9px auto;
			border-radius: 10px;
			position: relative;
		}
		.logincontent {
			width: 430px;
			margin: 0 auto;
			padding: 10px 0;
			text-align: left;
			overflow: hidden;
		}
		.logincontent h2 {
			height: 65px;
			line-height: 60px;
			border-bottom: 1px dotted #b7b7ba;
			font-size: 18px;
			font-weight: 700;
			color: #7D7D7E;
			overflow: hidden;
		}
		.logincontent h2 img {
			width: 165px;
			height: 54px;
			overflow: hidden;
			vertical-align: middle;
			margin-right: 30px;
		}
		form.inputs {
			padding-top: 20px;
			width: 80%;
		}
		form.inputs input {
			width: 100%;
			height: 33px;
			padding-left: 40px;
			padding-right: 5px;
			border: 1px solid #bfbfbf;
			line-height: 25px;
			font-size: 15px;
			color: #494F4D;
			margin-bottom: 15px;
			overflow: hidden;
		}
		form.inputs input:focus {
			box-shadow: 0 0 3px #4BA1FB;
		}
		.inputsul li {
			position: relative;
		}
		.inputsul li label {
			position: absolute;
			left: 0;
			top: 2px;
		}
		
		form.inputs .logindetail {
			font-size: 12px;
			color: #7f7f7f;
			margin-bottom: 15px;
		}
		form.inputs .logindetail input {
			width: 12px;
			height: 12px;
			vertical-align: top;
			overflow: hidden;
			margin: 0;
			margin-right: 5px;
			padding: 0;
		}
		form.inputs .logindetail input:focus {
			box-shadow: none;
		}
		.logindetail .forgetpw {
			float: right;
			margin-right: 10px;
			font-size: 12px;
			color: #7f7f7f;
		}
		.logindetail .forgetpw:hover {
			color: #F43939;
		}
		.warninginform {
			width: 296px;
			height: 32px;
			line-height: 32px;
			border: 1px solid #ff7519;
			background: #ffe8d6;
			padding-left: 10px;
			text-align: left;
			margin-left: 0;
			margin: 15px 0;
			font-size: 13px;
			color: #685f55;
			overflow: hidden;
			/* display: none; */
		}
		.warninginform img {
			width: 16px;
			height: 15px;
			vertical-align: middle;
			margin-right: 3px;
			overflow: hidden;
		}
		form.inputs .loginbut {
			width: 100%;
			height: 35px;
			text-align: center;
			display: block;
			line-height: 35px;
			border-radius: 5px;
			font-size: 16px;
			letter-spacing: 5px;
		}
		form.inputs .logindisable {
			background: #F06D76;
			color: #AB6A6A;
			opacity: 0.8;
		}
		form.inputs .loginlink  {
			background: #c61420;
			color: #fff;
			display: none;
		}
		.logincontent .registerlink {
			width: 315px;
			text-align: right;
			font-size: 15px;
			color: #707073;
			margin-top: 18px;
			overflow: hidden;
		}
		.registerlink a {
			color: #45393a;
			letter-spacing: 1px;
			margin-right: 5px;
			text-decoration: none;
		}
		.registerlink a:hover {
			color: #c61420;
		}
		.logincontainer .close {
			position: absolute;
			top: 10px;
			right: 15px;
			z-index: 10;
		}
		.logincontainer .close img {
			width: 20px;
			height: 21px;
			
			overflow: hidden;
		}
		
		form.inputs input:-webkit-autofill, form.inputs input:-webkit-autofill:hover, form.inputs input:-webkit-autofill:active, form.inputs input:-webkit-autofill:focus {
		    -webkit-box-shadow: 0 0 0 1000px white inset;
		}
	</style>
[/#macro]
[#--
 * 根据创建时间显示信息
 * datetime 创建时间(String格式，请使用creatimeStr)
 * @author ChenQi
 * @since 1.0
 --]
[#macro mytime datetime] 
[#assign time=datetime?datetime("yyyy-MM-dd HH:mm:ss")]
[#assign ct = (.now?long-time?long)/1000]
[#if ct gte 31104000][#--n年前--]${(ct/31104000)?int}年前 
  [#elseif ct gte 2592000][#--n月前--]${(ct/2592000)?int}个月前 
  [#elseif ct gte 86400*2][#--n天前--]${(ct/86400)?int}天前 
  [#elseif ct gte 86400][#--1天前--]昨天 
  [#elseif ct gte 3600][#--n小时前--]${(ct/3600)?int}小时前 
  [#elseif ct gte 60][#--n分钟前--]${(ct/60)?int}分钟前 
  [#elseif ct gt 0][#--n秒前--]${ct?int}秒前 
  [#else]刚刚 
[/#if]
[/#macro]
[#--
 * 字符串清空html并截取一定的字数
 * @content 要处理的文字	
 * @length 要截取的长度	
 * @shangjianguo
 * @since 1.0
 --]
[#macro TrimSubstring content mylength]
	[#if mylength<=0]
		[#local mylength=100]
	[/#if]
	[#local con=content?replace("<[^>]*>", "", "r")]
	[#local con=con?replace("&nbsp;", "", "r")]
	[#local apostrophe=true /]
	[#if (con?length)<='${mylength}'?number]
		[#local mylength='${con?length}']
		[#local apostrophe=false /]
	[/#if]
	[#local con=con?substring(0,'${mylength}'?number)]
	[#local con=con?replace("(&[a-z]*$)", "", "r")]
	${con}[#if apostrophe]...[/#if]
[/#macro]

[#--
 * 一级导航昵称处理
 * @content 要处理的文字
 * @length 要截取的长度
 * @shangjianguo
 * @since 1.0
 --]
[#macro TrimUsername content mylength]
	[#if mylength<=0]
		[#local mylength=100]
	[/#if]
	[#local con=content?replace("<[^>]*>", "", "r")]
	[#local con=con?replace("&nbsp;", "", "r")]
	[#local apostrophe=true /]
	[#if (con?length)<='${mylength}'?number]
		[#local mylength='${con?length}']
		[#local apostrophe=false /]
	[/#if]
	[#local con=con?substring(0,'${mylength}'?number)]
	[#local con=con?replace("(&[a-z]*$)", "", "r")]
	${con}[/#macro]
	
[#--
 * 邮箱或手机的字符串做隐藏部分处理
 * @content 要处理的文字	
 * @yangguang
 * @since 1.0
 --]
[#macro PrivateString content]
	[#local con= content]
	[#if content?matches("^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")]
		[#local index = content?last_index_of("@")]
		[#local prefix = content?substring(0, index)]
		[#if prefix?length > 6]
			[#if prefix?length > 8]
				[#local con= con?substring(0,3) + "****" + con?substring(index-4)]
			[#else]
				[#local con= con?substring(0,3) + "****" + con?substring(index-2)]
			[/#if]
		[#else]
			[#local con= con?substring(0,3) + "****" + con?substring(index-1)]
		[/#if]	
	[#elseif content?matches("^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(13|14|15|18)\\d{9}$")]
		[#local con= con?substring(0,3) + "****" + con?substring(7)]
	[/#if]
	${con}
[/#macro]
[#--
 * 邮箱或手机的字符串做隐藏部分处理
 * @content 要处理的文字	
 * @yangguang
 * @since 1.0
 --]
[#macro PrivateString content]
	[#local con= content]
	[#if content?matches("^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")]
		[#local index = content?last_index_of("@")]
		[#local prefix = content?substring(0, index)]
		[#if prefix?length > 6]
			[#if prefix?length > 8]
				[#local con= con?substring(0,3) + "****" + con?substring(index-4)]
			[#else]
				[#local con= con?substring(0,3) + "****" + con?substring(index-2)]
			[/#if]
		[#else]
			[#local con= con?substring(0,3) + "****" + con?substring(index-1)]
		[/#if]	
	[#elseif content?matches("^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(13|14|15|18)\\d{9}$")]
		[#local con= con?substring(0,3) + "****" + con?substring(7)]
	[/#if]
	${con}
[/#macro]
[/#escape]