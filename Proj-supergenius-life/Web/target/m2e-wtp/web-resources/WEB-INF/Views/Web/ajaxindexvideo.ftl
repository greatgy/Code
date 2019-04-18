[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if list??]
	[#list list as item]
		<li>
			<a href="${base}/videoDetail/${(item.cid)?c}/${item.uid}">
				<img class="talentimg" src="${webimg}/${item.imgmedium}" alt="${item.labelName}" title="${item.labelName}" />
				<div class="play">
					<img src="${baseimg}/imgs/default/playbtn.png" alt="播放" title="播放" />
				</div>
			</a>
			<div class="talentDetail">
				<span class="talentTitle"><i class="type">${item.labelName}</i>${item.title}</span>
				<span class="looktimes">
				<img src="${baseimg}/imgs/default/audience.png" alt="" />
					${item.clickcount}人观看
				</span>
				<p>
					<img src="${item.user.useravatar}" [#if item.user.getIsUser()]class="memberUserimg"[/#if] alt="${item.user.username}" title="${item.user.username}" />
					[#if item.user.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimg" alt="" />[/#if]
					[@p.PrivateString content="${item.user.username}" /]
					<span class="time">上传于<i>${(item.createtime).toString("yyyy-MM-dd ")}</i></span>
				</p>
			</div>
		</li>
	[/#list]
[/#if]

