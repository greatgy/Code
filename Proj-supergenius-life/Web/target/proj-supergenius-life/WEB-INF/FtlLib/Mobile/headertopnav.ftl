[#ftl]
<link rel="stylesheet" href="${basecss}/css/swiper.min.css">
<style>
/* 导航弹出遮罩层 */
.navmaskshow {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.1);
    z-index: 1001;
    display: block;
}
</style>
<div class="navmask"></div><div class="topnav">
    <div class="shownav">
        <ul class="navlist">
        </ul>
        <a href="javascript:;" class="collopse"><img src="${baseimg}/imgs/default/arrawDown.png" /></a>
    </div>
    <ul class="collopseNav hd">
        [#if cataloguelist??]
        	[#assign i=1]
        	[#list cataloguelist as map]
        		[#if i<4][#assign level=1][#elseif i<7][#assign level=2][#elseif i<10][#assign level=3][#else][#assign level=4][/#if]
        		<li data-level="${level}" class="catalogue_${(map["first"].cid)?c} mobile_${level} [#if pcid?? && pcid?c == (map["first"].cid)?c || cid?? && cid?c == (map["first"].cid)?c]currentnav[/#if]">
	            	[#if map["second"]?? && map["second"]?size>0]
	            		<a class="catalogue_mobile" data-value="${(map["first"].cid)?c}">${map["first"].name}</a>
	            	[#else]
	            		<a class="aa" href="${base}/m/catalogue/${map["first"].pcid}/${(map["first"].cid)?c}">${map["first"].name}</a>
	            	[/#if]	
		        </li>
		        [#assign i=i+1]
        	[/#list]
        [/#if]
        <li>
            <a class="morelinks" href="${base}/m/life/allcatalogue?cid=${cid?c}&pcid=${pcid}">+&nbsp;更多</a>
        </li>
    </ul>
</div>
<div class="popmask"></div>
<div class="popNavlist">
    <h3 class="firstCatalogue"></h3>
    <ul class="rollnavs">
    </ul>
</div>

<script type="text/javascript">
    $(function () {
    	if($(".catalogue_"+${cid?c}).length!=0){
    		var level=$(".catalogue_"+${cid?c}).data("level");
    		var html=$(".mobile_"+level).clone();           
    		if(level == '4'){
    			html = '<li><a href="${base}/m/catalogue/0/128">人生问答</a></li><li><a class="catalogue_mobile" data-value="256">我的偶像</a></li><li class="current"><a href="${base}/m/catalogue/0/512">会员通道</a></li>';
    		}
    		$(".navlist").html(html);
    		$(".catalogue_"+${cid?c}).each(function(){
    			if($(this).parent().hasClass("navlist")){
    				$(this).addClass("current").removeClass("currentnav");
    			}
    		})
    	}else if($(".catalogue_"+${pcid?c}).length!=0){
    		var level=$(".catalogue_"+${pcid?c}).data("level");
    		var html=$(".mobile_"+level).clone();
    		$(".navlist").html(html);
    		$(".catalogue_"+${pcid?c}).each(function(){
    			if($(this).parent().hasClass("navlist")){
    				$(this).addClass("current").removeClass("currentnav");
    			}
    		})
    	} else{
    		var html='<li class="current"><a href="${base}/m/catalogue/0/1">首页</a></li><li><a href="${base}/m/catalogue/2/1024">人生定位</a></li><li><a href="${base}/m/catalogue/0/4">疯狂理想</a></li>';
	        $(".navlist").html(html);      
    	}
    	
        $(".collopse").click(function () {
            $(".navmask").toggleClass("navmaskshow");
            $(this).toggleClass("toggleimg");
            $(".collopseNav").toggleClass("hd");
        });
        $(".navmask").click(function () {
           	$(".navmask").toggleClass("navmaskshow");
            $(".collopseNav").toggleClass("hd");
        });
        $(".popmask").click(function () {
            $(this).hide();
            $(".popNavlist").hide();
        });

		$(".catalogue_mobile").click(function(){
			var cid = $(this).data("value");
			var cataloguename= $(this).html();
			if(cid==8){
				cataloguename+='<i>初中</i>';
			} else if(cid==16){
				cataloguename+='<i>高中</i>';
			} else if(cid==32){
				cataloguename+='<i>大学</i>';
			}
			$.get("${base}/m/ajax/secondcatalogue", {cid:cid, currentCid:${cid?c}}, function(data){
				if(data!=''){
					$(".rollnavs").html(data);
					$(".firstCatalogue").html(cataloguename);
					$(".popmask").show();
            		$(".popNavlist").show();
				}
			})
		})
    });
</script>