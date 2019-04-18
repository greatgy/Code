[#ftl]
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<meta name="Keywords" content="天才人生,全部课程" />
	<meta name="Description" content="天才人生,全部课程" />
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
	<style>
		.coursemask {
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
	</style>
</head>
<body>
	<div class="courseLeft">
		<h3>课程导航</h3>
		<h4>六年级<img src="${baseimg}/imgs/default/upicon.png" class="arrowicon [#if grade != 1] animateArrow [/#if]" alt="" /></h4>
		<ul class="coursesList [#if grade != 1] hd [/#if]">
			[#if sixList??]
				[#list sixList as subject]
					<li data-grade="1" data-id="${subject.sid}" class=" lisu [#if subject.sid == subid && grade == 1]curChose[/#if]">
						<a>
							${subject.name}
						</a>
					</li>
				[/#list]
			[/#if]
		</ul>
		<h4>初一<img src="${baseimg}/imgs/default/upicon.png" class="arrowicon [#if grade != 2] animateArrow [/#if] " alt="" /></h4>
		<ul class="coursesList [#if grade != 2] hd [/#if]">
			[#if sevenList??]
				[#list sevenList as subject]
					<li data-grade="2" data-id="${subject.sid}" class=" lisu [#if subject.sid == subid && grade == 2]curChose[/#if]">
						<a>
							${subject.name}
						</a>
					</li>
				[/#list]
			[/#if]
		</ul>
		<h4>初二<img src="${baseimg}/imgs/default/upicon.png" class="arrowicon [#if grade != 3] animateArrow [/#if] " alt="" /></h4>
		<ul class="coursesList [#if grade != 3] hd [/#if]">
			[#if eightList??]
				[#list eightList as subject]
					<li data-grade="3" data-id="${subject.sid}" class=" lisu [#if subject.sid == subid && grade == 3]curChose[/#if]">
						<a>
							${subject.name}
						</a>
					</li>
				[/#list]
			[/#if]
		</ul>
		<h4>初三<img src="${baseimg}/imgs/default/upicon.png" class="arrowicon [#if grade != 4] animateArrow [/#if]" alt="" /></h4>
		<ul class="coursesList [#if grade != 4] hd [/#if]">
			[#if nineList??]
				[#list nineList as subject]
					<li data-grade="4" data-id="${subject.sid}" class=" lisu [#if subject.sid == subid && grade == 4]curChose[/#if]">
						<a>
							${subject.name}
						</a>
					</li>
				[/#list]
			[/#if]
		</ul>
	</div>
	<div class="courseRight">
		<div class="versionCourse">
			[#if courseList??]
				[#list courseList as courtse]
						<a href="javascript:;" data-uid="${courtse.uid}" class="courseDetail">
							<img src="${webimg}${courtse.imgmedium}" alt="${courtse.name}" title="${courtse.name}" />
							<p>${courtse.name}</p>
						</a>	
				[/#list]
			[/#if]
		</div>
	</div>
	<!-- 书籍信息弹窗 -->
	<div class="coursemask"></div>
	<div class="bookDetail">
	</div>
		 <script type="text/javascript" src="${basejs}/js/libs/mask.js"></script>
	<script type="text/javascript">
		$(function () {
			$(document).on('click', ".courseDetail", function(){
				$(".coursemask").show();
					var uid=$(this).data("uid");
					$.get("${base}/ajax/course/detail", {"uid":uid}, function(data){
						$(".bookDetail").html(data);
					});
					$(".bookDetail").show();
		     });
			[#--  $(document).on('click', ".lisu", function(){
				window.location.href=$(this).children("a").attr("href");
		     });--]
			$(".coursemask").click(function () {
				$(".coursemask").hide();
				$(".bookDetail").hide();
			})
			$(document).on('click', ".closeBtn", function(){
				$(".coursemask").hide();
				$(".bookDetail").hide();
			})
			$(document).on('click', ".arrowicon", function(){
				$(this).parent().next().toggleClass('hd');
				$(this).toggleClass("animateArrow");
			})
		});
		
		//点击科目，选中并后台请求相对应的课程
			$(".lisu").click(function(){
				$(".lisu").removeClass("curChose");
				$(this).addClass("curChose");
				var sid=$(this).data("id");
				var grade=$(this).data("grade");
				$.get("${base}/ajax/morecourse", {"grade":grade,"subid":sid}, function(data){
					console.log(data);
					$(".versionCourse").html(data);
				});
			})
	</script>
</body>
</html>