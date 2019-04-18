[#ftl]
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'梦之初心--课程推荐'}</title>
	<meta name="Keywords" content="天才人生,课程推荐" />
	<meta name="Description" content="天才人生,课程推荐" />
	<!--# include file="/include/headcommon.html" -->
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
</head>
<body>
<ul class="classStage">
	<li>
		<div class="stages">
			<img src="${baseimg}/imgs/default/courseUser.png" alt="" />
			初一
		</div>
		<div class="courses">
			[#if sevenList??]
				[#assign i = 1]
				[#list sevenList as subject]
					[#if i < 5]
						<a href="${base}/course/morecourse?subid=${subject.sid}&grade=2">${subject.name}</a>
					[/#if]
					[#assign i = i + 1]
				[/#list]
				[#if sevenList?size > 4]
					<a href="${base}/course/morecourse?subid=${sevenList[0].sid}&grade=2">更多</a>
				[/#if]
			[/#if]
		</div>
	</li>
	<li>
		<div class="stages">
			<img src="${baseimg}/imgs/default/courseUser.png" alt="" />
			初二
		</div>
		<div class="courses">
			[#if eightList??]
				[#assign i = 1]
				[#list eightList as subject]
					[#if i < 5]
						<a href="${base}/course/morecourse?subid=${subject.sid}&grade=4">${subject.name}</a>
					[/#if]
					[#assign i = i + 1]
				[/#list]
				[#if eightList?size > 4]
					<a href="${base}/course/morecourse?subid=${sevenList[0].sid}&grade=4">更多</a>
				[/#if]
			[/#if]
		</div>
	</li>
	<li>
		<div class="stages">
			<img src="${baseimg}/imgs/default/courseUser.png" alt="" />
			初三
		</div>
		<div class="courses">
			[#if nineList??]
				[#assign i = 1]
				[#list nineList as subject]
					[#if i < 5]
						<a href="${base}/course/morecourse?subid=${subject.sid}&grade=8">${subject.name}</a>
					[/#if]
					[#assign i = i + 1]
				[/#list]
				[#if nineList?size > 4]
					<a href="${base}/course/morecourse?subid=${sevenList[0].sid}&grade=8">更多</a>
				[/#if]
			[/#if]
		</div>
	</li>
	<li class="memberPart">
		<div class="stages">
			[#if me??]
				[#if me.getIsUser()]
					<img src="${baseimg}/imgs/default/partmember.png" alt="会员专区" title="会员专区" />
				[#else]
					<a href="javascript:;" class="normalUsertip"><img src="${baseimg}/imgs/default/partmember.png" alt="会员专区" title="会员专区" /></a>
				[/#if]
			[#else]
					<a href="javascript:;" class="pop-up"><img src="${baseimg}/imgs/default/partmember.png" alt="会员专区" title="会员专区" /></a>
        	[/#if]
		</div>
		<div class="courses">
			[#if me??]
        		[#if me.getIsUser()]
        			<a href="${base}/problem/16384_1">语文</a>
        			<a href="${base}/problem/16384_1">数学</a>
        			<a href="${base}/problem/16384_1">英语</a>
        			<a href="${base}/problem/16384_1">物理</a>
        			<a href="${base}/problem/16384_1">更多</a>
        		[#else]
        			<a href="javascript:;" class="normalUsertip">语文</a>
        			<a href="javascript:;" class="normalUsertip">数学</a>
        			<a href="javascript:;" class="normalUsertip">英语</a>
        			<a href="javascript:;" class="normalUsertip">物理</a>
        			<a href="javascript:;" class="normalUsertip">更多</a>
        		[/#if]
        	[#else]
        		<a class="pop-up" href="javascript:;">语文</a>
				<a class="pop-up" href="javascript:;">数学</a>
				<a class="pop-up" href="javascript:;">英语</a>
				<a class="pop-up" href="javascript:;">物理</a>
				<a class="pop-up" href="javascript:;">更多</a>
        	[/#if]
		</div>
	</li>
</ul>
<div class="courseShow">
	<div class="classItem">
		<h3>
			<span><i class="specialNmuber">六</i>年级</span>(四年制)
			[#if me??]
				<a class="asklink" href="${base}/addproblem/${cid?c}">+我要提问</a>
			[#else]
				<a class="asklink pop-up" >+我要提问</a>
			[/#if]
		</h3>
		<div class="courseChose">
			<ul class="tabCourses">
				[#if sixList??]
					[#assign i = 1]
					[#list sixList as subject]
						<li data-grade="1" data-id="${subject.sid}" class="cur [#if i == 1] curCourse[/#if]">${subject.name}</li>
						[#assign i = i + 1]
					[/#list]
				[/#if]
			</ul>
			<div class="versionCourse">
				[#if sixCourseList??]
					[#assign i = 1]
					[#list sixCourseList as courtse]
						<a href="javascript:;" data-uid="${courtse.uid}" class="courseDetail">
							<img src="${webimg}${courtse.imgmedium}" alt="${courtse.name}" title="${courtse.name}" />
							<p>${courtse.name}</p>
						</a>	
						[#if i == 6]
							<a href="${base}/course/morecourse?subid=${courtse.sid}&grade=${courtse.grade}" class="moreCourse">&gt;</a>
						[/#if]
						[#assign i = i + 1]
					[/#list]
				[/#if]
			</div>
		</div>
	</div>
	<div class="classItem">
		<h3>
			<span><i class="specialNmuber">初</i>一</span>
			[#if me??]
				<a class="asklink" href="${base}/addproblem/${cid?c}">+我要提问</a>
			[#else]
				<a class="asklink pop-up" >+我要提问</a>
			[/#if]
		</h3>
		<div class="courseChose">
			<ul class="tabCourses">
				[#if sevenList??]
					[#assign i = 1]
					[#list sevenList as subject]
						<li data-grade="2" data-id="${subject.sid}" class="cur [#if i == 1] curCourse[/#if]">${subject.name}</li>
						[#assign i = i + 1]
					[/#list]
				[/#if]
			</ul>
			<div class="versionCourse">
				[#if sevenCourseList??]
					[#assign i = 1]
					[#list sevenCourseList as courtse]
						<a href="javascript:;" data-uid="${courtse.uid}" class="courseDetail">
							<img src="${webimg}${courtse.imgmedium}" alt="${courtse.name}" title="${courtse.name}" />
							<p>${courtse.name}</p>
						</a>	
						[#if i == 6]
							<a href="${base}/course/morecourse?subid=${courtse.sid}&grade=${courtse.grade}" class="moreCourse">&gt;</a>
						[/#if]
						[#assign i = i + 1]
					[/#list]
					
				[/#if]
			</div>
		</div>
	</div>
	<div class="classItem">
		<h3>
			<span><i class="specialNmuber">初</i>二</span>
			[#if me??]
				<a class="asklink" href="${base}/addproblem/${cid?c}">+我要提问</a>
			[#else]
				<a class="asklink pop-up" >+我要提问</a>
			[/#if]
		</h3>
		<div class="courseChose">
			<ul class="tabCourses">
				[#if eightList??]
					[#assign i = 1]
					[#list eightList as subject]
						<li data-grade="4" data-id="${subject.sid}" class="cur [#if i == 1] curCourse[/#if]">${subject.name}</li>
						[#assign i = i + 1]
					[/#list]
				[/#if]
			</ul>
			<div class="versionCourse">
				[#if eightCourseList??]
					[#assign i = 1]
					[#list eightCourseList as courtse]
						<a href="javascript:;" data-uid="${courtse.uid}" class="courseDetail">
							<img src="${webimg}${courtse.imgmedium}" alt="${courtse.name}" title="${courtse.name}" />
							<p>${courtse.name}</p>
						</a>	
						[#if i == 6]
							<a href="${base}/course/morecourse?subid=${courtse.sid}&grade=${courtse.grade}" class="moreCourse">&gt;</a>
						[/#if]
						[#assign i = i + 1]
					[/#list]
				[/#if]
			</div>
		</div>
	</div>
	<div class="classItem">
		<h3>
			<span><i class="specialNmuber">初</i>三</span>
			[#if me??]
				<a class="asklink" href="${base}/addproblem/${cid?c}">+我要提问</a>
			[#else]
				<a class="asklink pop-up" >+我要提问</a>
			[/#if]
		</h3>
		<div class="courseChose">
			<ul class="tabCourses">
				[#if nineList??]
					[#assign i = 1]
					[#list nineList as subject]
						<li data-grade="8" data-id="${subject.sid}" class="cur [#if i == 1] curCourse[/#if]">${subject.name}</li>
						[#assign i = i + 1]
					[/#list]
				[/#if]
			</ul>
			<div class="versionCourse">
				[#if nineCourseList??]
					[#assign i = 1]
					[#list nineCourseList as courtse]
						<a href="javascript:;" data-uid="${courtse.uid}" class="courseDetail">
							<img src="${webimg}${courtse.imgmedium}" alt="${courtse.name}" title="${courtse.name}" />
							<p>${courtse.name}</p>
						</a>	
						[#if i == 6]
							<a href="${base}/course/morecourse?subid=${courtse.sid}&grade=${courtse.grade}" class="moreCourse">&gt;</a>
						[/#if]
						[#assign i = i + 1]
					[/#list]
				[/#if]
			</div>
		</div>
	</div>
	<div class="classItem">
		<h3 class="freepart">
			<span><i class="specialNmuber">自</i>由交流区</span>
			[#if me??]
				<a class="asklink" href="${base}/addproblem/${cid?c}" style="float:right;margin-top:5px;">+我要提问</a>
			[#else]
				<a class="asklink pop-up" style="float:right;">+我要提问</a>
			[/#if]
		</h3>
		<ul class="quizList askQuestionList">
			[#if problemList??]
			[#list problemList as item]
				[#if item.user??]
					[#assign fromMan=item.user/]
				[#elseif item.visitor??]
					[#assign fromMan=item.visitor/]
				[/#if]
				<li>
					[#if item.user??]
						<img src="${fromMan.useravatar}" [#if fromMan.getIsUser()]class="memberUserimg"[/#if] alt="${fromMan.username}" title="${fromMan.username}" />
						[#if fromMan.getIsUser()]
							<img src="${baseimg}/imgs/default/prince.png" class="princeimg12" alt="" />
						[/#if]
					[#else]
						<img src="${userimg}${fromMan.avatar}"/>		
					[/#if]
					[#if item.ismember==1]
						[#if me??]
		            		<a [#if me.getIsUser()]href="${base}/problem/detail/${cid?c}/${item.oid?c}" class="quizRt"[#else]href="javascript:;" class="quizRt normalUsertip"[/#if] >
		            	[#else]
		            		<a href="javascript:;" class="quizRt pop-up" >
		            	[/#if]
					[#else]
						[#if me??]
		            		<a href="${base}/problem/detail/${cid?c}/${item.oid?c}" class="quizRt">
		            	[#else]
		            		<a href="javascript:;" class="quizRt pop-up" >
		            	[/#if]
					[/#if]
					<div class="col-xs-8 col-sm-9">
						[#if item.user?? && fromMan.getIsUser()]
							<h4>${item.title}[#if item.state==2]<span class="answerStatus">专家已解答</span>[/#if]</h4>
						[#else]
							<h4>${item.title}</h4>
						[/#if]
						<p>
						[#if item.content??]
						[#else]
							[@p.TrimSubstring content="${item.content}" mylength=50 /]
						[/#if]
						</p>
					</div>
					<div class="col-xs-4 col-sm-3">
						<div class="readitem col-sm-6 col-xs-6">
							阅读
							<span class="readNum">${item.clickcount}</span>	
						</div>
						<div class="readitem col-sm-6 col-xs-6">
							回答
							<span class="readNum">${item.commentscount}</span>	
						</div>
					</div>
					</a>
				</li>
			[/#list]
			[/#if]
		</ul>
	</div>
</div>
	<!-- 书籍信息弹窗 -->
	<div class="coursemask"></div><!-- 高度需自行计算赋值 -->
	<div class="bookDetail">
	</div>
	<!-- # include file="/include/footer.html" -->
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
			$(".coursemask").click(function () {
				$(".coursemask").hide();
				$(".bookDetail").hide();
			})
			$(document).on('click', ".closeBtn", function(){
				$(".coursemask").hide();
				$(".bookDetail").hide();
			})
		});
		//点击更多
			$(document).on('click', ".moreCourse", function(){
					var sid=$(this).data("id");
					var grade=$(this).data("grade");
					
					
		     });
			$(document).on('click', ".memberPart", function(){
				[#if me??]
					[#if me.getIsUser()]	
						window.location.href="${base}/problem/16384_1";
					[/#if]				
				[/#if]
		     });
		//点击科目，选中并后台请求相对应的课程
			$(".cur").click(function(){
				$(this).addClass("curCourse").siblings().removeClass("curCourse");
				var sid=$(this).data("id");
				var grade=$(this).data("grade");
				var obj = $(this).parent().siblings(".versionCourse");
				$.get("${base}/ajax/course", {"grade":grade,"sid":sid}, function(data){
					$(obj).html(data);
				});
			})
	</script>
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
</body>
</html>