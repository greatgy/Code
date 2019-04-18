[#ftl]
<!-- 一级导航开始 -->
<div class="firstNavBox">
	<div class="container-fluid">
		<div class="row first_nav">
			<div class="navbar navbar01" role="navigation">
				<div class="navbar_com">
				[#if me??]
                	<div class="userbox" style="background: url([#if me.useravatar??]${me.useravatar}[#else]${basecss}/imgs/mobile/default/normal.png[/#if]) no-repeat center center;background-size:90%;">
                	<a href="${base}/my/center/article?phone=${me.uid}"></a>
	        	[#else]
	        		<div class="userbox" style="background: url(${basecss}/imgs/mobile/default/normal.png) no-repeat center center;background-size:90%;">
	        		<a href="${base}/phonelogin"></a>
	        	[/#if]
					</div>	
					<div class="logo_brand">
					[#if phone??]
						<div class="navbar-brand">
							<a href="http://www.supergenius.cn/m/index">
								<img src="${baseimg}/imgs/default/slogo.png" class="superlogo img-responsive" alt="超天才" title="超天才">
							</a>
							<a href="${base}">
								<img src="${baseimg}/imgs/default/phonelogo.png" class="img-responsive" alt="天财评论" title="天财评论" />
							</a>
						</div>
		            [#else]
						<a href="${base}" class="navbar-brand">
							<img src="${baseimg}/imgs/default/logo.png" class="img-responsive" alt="" />
						</a>
		            [/#if]
					<button class="navbar-toggle collapse_btn" type="button" data-toggle="collapse" data-target="#myNavbar"></button>
				</div>
				</div>
			</div>
		</div><!-- 一级导航结束 -->
	</div>
	<div class="container-fluid">
		<!-- button折叠导航开始 -->
		<div class="row collapse" id="myNavbar">
			<div class="nav navbar-nav nav-stacked collapseBox">
				<form class="phoneSearch">
					<input type="text" placeholder="输入搜索内容" id="headtop" name="keyword"/>
					<a class="phoneSearchimg" ><img src="${baseimg}/imgs/default/search.png" /></a>
				</form>
				<ul class="phoneNavList">
					[#if LeftCatalogue??]
				        [#list LeftCatalogue as catalogue]
							<li class="[#if cid??][#if catalogue.cid == cid]active[/#if][/#if] item col-xs-4 col-sm-3">
								<a href="${base}[#if catalogue.cid>0]/catalogue/${catalogue.cid}[/#if]" data-cid="${catalogue.cid}">${catalogue.name}</a>
				        	</li>
						[/#list]
			        [/#if]
				</ul>
			</div>
		</div><!-- button折叠导航结束 -->
	</div>
	<div class="nav_back hd"></div>
</div>
<script type="text/javascript">
	$(function () {
		$(".nav_back").height($("body").height());
	});
	
	 //绑定查找事件
        $("#headtop").bind("keydown", searchHandle);
        $(".phoneSearchimg").click(function () {
        	if ($("#headtop").val().trim() == "") {
				document.location = '${SolrSearchUrl}/index';
        	} else {
        		window.location.href = "${base}/search?keyword=" + $("#headtop").val();
        	}
        });
        
        //按enter键查找
		function searchHandle(event){
			if (event.keyCode==13) {
				if ($(this).val().trim() == "") {
					shakeHandle();
					$(this).blur();
					return;
				} else {
					window.location.href = "${base}/search?keyword=" + $("#headtop").val();
				}
			}
		}
		//设置搜索框内容为空时抖动提示
		function shakeHandle() {
			window.location.href = "${SolrSearchUrl}/index";
		}
</script>