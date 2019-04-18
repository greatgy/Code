[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#list commentsList as comment]
<li>
<a href="${base}/article/${(comment.cid)?c}/${(comment.articleoid)?c}?commentuid=${(comment.uid)}">
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