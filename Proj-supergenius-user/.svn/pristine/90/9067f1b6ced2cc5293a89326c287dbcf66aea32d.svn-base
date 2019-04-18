[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#list list as news]
     <a href="${base}/my/user/newdetail/${news.uid}">
        <li>
            <div class="protectmid">
                <h3><span style="color: red;">[#if news.isread == false]NEW[/#if]</span>${news.title}</h3>
                <span>[@p.TrimSubstring content="${news.content}" mylength=100 /]</span>
            </div>
        </li>
     </a>
[/#list]