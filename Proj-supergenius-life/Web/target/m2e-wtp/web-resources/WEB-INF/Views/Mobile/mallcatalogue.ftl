[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<style type="text/css">
	@media screen and (max-width: 767px){
		.phoneFooter{
		    position:absolute;
		    margin-left:18px;
		}
    }
	</style>
</head>
<body>
	<ul class="navlist">
        [#if cataloguelist??]
	    	[#list cataloguelist as map]
	    		[#if map["first"].cid != 1]
		    		<li class="catalogue_allCatalogue_${(map["first"].cid)?c}[#if pcid?? && pcid==(map["first"].cid)?c] curnavLi[/#if]">
			            [#if map["second"]?? && map["second"]?size>0]
				            <span class="labeltitle">
			                	${map["first"].name}
				            </span>
			            	<ul class="rollnav">
				            	[#assign secondlist = map["second"]]
				            	[#list secondlist as item]
				            		<li [#if cid?? && cid?c == (item.cid)?c]class="curselected"[/#if]><a href="${base}/catalogue/${(item.pcid)?c}/${(item.cid)?c}">${item.name}</a></li>
				            	[/#list]
			            	</ul>
			            [#else]
			            	<a class="aa" href="${base}/catalogue/${map["first"].pcid}/${(map["first"].cid)?c}">${map["first"].name}</a>
			            [/#if]
			        </li>
			       [/#if] 
	    	[/#list]
	    [/#if]
    </ul>
    <script>
    	$(function(){
    		if($(".curnavLi").length == 0){
    			$("li[class^='catalogue_allCatalogue_']:first").addClass("curnavLi");
    		}
    	})
    	$("li").click(function(){
    		$(this).addClass("curnavLi").siblings().removeClass("curnavLi");
    	})
    </script>
</body>
</html>