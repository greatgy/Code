[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if list??]
	[#list list as article]	
		<li>
            <a class="tabitemimg" href="${officialbase}/classroom/${(article.oid)?c}">
                <img src="${webimg}${article.imgmedium}" title="${article.title}" alt="${article.title}">
            </a>
            <div class="tabitemRt">
                <a href="${officialbase}/classroom/${(article.oid)?c}" class="articleTitle" title="${article.title}">
                    ${article.title}
                </a>
                <span class="date">[@p.mytime datetime="${article.createtimeStr}"/]</span>
                <p class="descripe">
                	[#if article.summary==""]
						[@p.TrimSubstring content="${article.content}" mylength=80 /]
					[#else]
						[@p.TrimSubstring content="${article.summary}" mylength=80 /]
					[/#if]
                </p>
            </div>
        </li>
	[/#list]
[/#if]