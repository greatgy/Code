[#ftl]
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生'}</title>
	<meta name="Keywords" content="天才人生,全部课程" />
	<meta name="Description" content="天才人生,全部课程" />
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
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
		<h3>
			[#if grade == 1]<span><i class="specialNmuber">六</i>年级</span>[#elseif grade == 2]<span><i class="specialNmuber">初</i>一</span>[#elseif grade == 4]<span><i class="specialNmuber">初</i>二</span>[#elseif grade == 8]<span><i class="specialNmuber">初</i>三</span>[/#if]
			[#if me??]
				<a class="asklink" href="${base}/m/addproblem/${cid?c}">+我要提问</a>
			[#else]
				<a class="asklink" href="${base}/m/login?cid=${cid?c}&pcid=${pcid}">+我要提问</a>
			[/#if]
		</h3>
		<ul class="coursesList">
			[#if subjectList??]
				[#list subjectList as subject]
					<li class=" lisu [#if subject.sid == subid]curChose[/#if]">
						<a href="${base}/m/course/morecourse?cid=${cid?c}&pcid=${pcid}&subid=${subject.sid}&grade=[#if grade == 1]1[#elseif grade == 2]2[#elseif grade == 4]4[#elseif grade == 8]8[/#if]" >
							${subject.name}
							
						</a>
					</li>
				[/#list]
			[/#if]
		</ul>
	</div>
	<div class="courseRight versionCourse">
		[#if courseList??]
			[#list courseList as courtse]
				<a href="javascript:;" data-uid="${courtse.uid}" class="courseDetail">
					<img src="${webimg}${courtse.imgmedium}" alt="${courtse.name}" title="${courtse.name}" />
					<p style="font-size: 0.86rem;">${courtse.name}</p>
				</a>	
			[/#list]
		[/#if]
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
			$(document).on('click', ".lisu", function(){
				window.location.href=$(this).children("a").attr("href");
		     });
			$(".coursemask").click(function () {
				$(".coursemask").hide();
				$(".bookDetail").hide();
			})
			$(document).on('click', ".closeBtn", function(){
				$(".coursemask").hide();
				$(".bookDetail").hide();
			})
		});
	</script>
</body>
</html>