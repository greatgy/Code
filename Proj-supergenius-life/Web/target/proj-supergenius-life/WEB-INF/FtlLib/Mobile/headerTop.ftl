[#ftl]
<!-- 一级导航开始 -->
<div class="firstNavBox">
	<div class="container-fluid">
		<div class="row first_nav">
			<div class="navbar navbar01" role="navigation">
				<div class="navbar_com">
					[#if me??]
						<div class="userbox" style="background: url([#if me.useravatar??]${me.useravatar}[#else]${basecss}/imgs/mobile/default/defaultimg.png[/#if]) no-repeat center center;background-size: 42px 42px;">
							<a href="${base}/m/my/center/mycenter"></a>
						</div>
					[#else]
						<div class="userbox" style="background: url(${basecss}/imgs/mobile/default/defaultimg.png) no-repeat center center;background-size:90%;">
							<a href="${base}/m/login?cid=${cid?c}&pcid=${pcid}"></a>
						</div>
					[/#if]	
					<div class="logo_brand">
						<div class="navbar-brand">
							<a href="http://www.supergenius.cn/m/index">
								<img src="${baseimg}/imgs/mobile/default/slogo.png" class="superlogo img-responsive" alt="超天才" title="超天才">
							</a>
							<a href="${base}/m/index">
								<img src="${baseimg}/imgs/default/logo.png" class="img-responsive" alt="" />
							</a>
						</div>
					</div>
					<a class="searchLink" href="${SolrSearchUrl}/index">
						<img src="${baseimg}/imgs/mobile/default/searchimg.png" alt="" />
					</a>
				</div>
			</div>
		</div><!-- 一级导航结束 -->
	</div>
</div>