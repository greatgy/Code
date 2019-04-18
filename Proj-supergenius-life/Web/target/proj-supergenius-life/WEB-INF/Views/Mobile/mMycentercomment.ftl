[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>个人中心--我的评论</title>
	<meta name="Keywords" content="天才人生，个人中心，我的评论" />
	<meta name="Description" content="天才人生，个人中心，我的评论" />
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
</head>
<body>
		<!-- 我的评论 -->
		[#if commentsList??]
            <!-- 我的提问 -->
                <div class="myCollect">
                    <h4 class="baseinfo">我的评论</h4>
                    <div class="manycomments">您共有<span>${commentscount!'0'}</span>条评论</div>
                    <ul class="tabBox commentsbox myTabbox">
                        [#list commentsList as comment]
                            <li>
                          <a [#if comment.channel == '5'] href="${base}/article/${(comment.firstCid)?c}/${(comment.articleoid)?c}?commentuid=${(comment.uid)}"
                                [#elseif comment.channel == '6']href="${base}/topic/review/${(comment.firstCid)?c}/${(comment.topicoid)?c}"
                                [#elseif comment.channel == '7']href="${base}/videoDetail/${(comment.firstCid)?c}/${(comment.fromuid)}"
                                [#elseif comment.channel == '8']href="${base}/catalogue/2/${(comment.firstCid)?c}"[/#if]>
                                <p>[@p.TrimSubstring content="${comment.content}" mylength=80 /]</p>
                                <div class="sourcefrom">
                                    [@p.TrimSubstring content="${comment.title}" mylength=80 /]
                                </div>
                                <div class="pubTime">
                                    [@p.mytime datetime="${comment.createtimeStr}"/]
                                    <div class="zan_play">
                                        <span>
                                            <img src="${baseimg}/imgs/default/thumb.png" alt="赞" title="赞" />
                                            赞${comment.prizecount!'0'}
                                        </span>
                                    </div>
                                </div>
                         </a>
                            </li>
                           [/#list]
                    </ul>
                </div>
        [/#if]
	<script type="text/javascript">
		        var pagenum=2;
        		var prepage=1;
        		function loadmorecomment() {
                    var url = "${base}/m/ajax/my/comments";
                    if(prepage!= pagenum){
                        prepage=pagenum;
                        $.get(url, {pagenum:pagenum}, function(data){
                            pagenum += 1;
                            if(data.trim() == ""){// 没有更多内容
                                $(window).unbind("scroll", defaultScrollHandler);
                            }
                            $(".commentsbox").append(data);
                        });
                    }
                }
        		$(function(){
        			[#if commentsList?size>0]
                        $.fn.scrollHandle({
                            obj: window,
                            fun: loadmorecomment
                        })
                    [#else]
                        $(".contentLeft ").html("<div class='IsEmpty'>空空如也~</div>");
                    [/#if]
        		});
	</script>
</body>
</html>