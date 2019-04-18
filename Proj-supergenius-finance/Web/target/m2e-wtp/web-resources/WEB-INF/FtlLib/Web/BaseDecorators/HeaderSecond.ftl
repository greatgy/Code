[#ftl]
<div class="topnavPhone">
    <div class="swiper-button-prev"></div>
    <div class="navlist swiper-Container">
        <ul class="swiper-wrapper">
            [#if LeftCatalogue??]
		        [#list LeftCatalogue as catalogue]
					<li class="[#if cid??][#if catalogue.cid == cid]current[/#if][/#if] swiper-slide">
						<a href="${base}[#if catalogue.cid > 0]/catalogue/${catalogue.cid}[/#if]" data-cid="${catalogue.cid}">${catalogue.name}</a>
		        	</li>
				[/#list]
        	[/#if]
        </ul>
    </div>
    <div class="swiper-button-next"></div>
</div>
<script type="text/javascript">
    $(function () {
        var mySwiper = new Swiper('.swiper-Container',{
            loop:false,
            grabCursor: true,
            spaceBetween: 0,
            eventTarget: 'swiper-Container',
            slidesPerView: 'auto',
            nextButton: '.swiper-button-next',
            prevButton: '.swiper-button-prev',
            followFinger: 'false',
        }); 
        
    });
</script>