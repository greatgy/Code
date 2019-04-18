[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
[#if memberVideoList??]
				[#list memberVideoList as item]	
						<li>
							<a 
			            		href="${base}/videoDetail/${(item.cid)?c}/${item.uid}" target="_blank">
								<img class="talentimg" src="${webimg}${item.imgmedium}" alt="${item.title}" title="${item.title}" />
							</a>
							<div class="talentDetail">
								<span class="talentTitle">${item.title}</span>
								[#if item.ismember == 1] 
									[#if item.state == 2]
										<span class="comment commented">
											专家已点评
										</span>
									[#elseif item.state == 0]
										<span class="comment">
											未点评
										</span>
									[/#if]
								[/#if]
								<p>
									<span style="display:block"><img src="${item.user.useravatar}" [#if item.user.getIsUser()]class="memberUserimg"[/#if] alt="${item.user.username}" title="${item.user.username}" />
									[#if item.user.getIsUser()]<img src="${baseimg}/imgs/default/prince.png" class="princeimgs" alt="" />[/#if]
									[@p.PrivateString content="${item.user.username}" /]</span>
									<span class="time">上传于<i>${(item.createtime).toString("YYYY.M.dd")}</i></span>
								</p>
							</div>
						</li>
				[/#list]
			[/#if]