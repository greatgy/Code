$(function(b){loadmore();b(document).on("click","a[id^='btnprize']",function(){unRegisterPraise();b(this).prize()});b(document).on("click","a[id^='deletecomment']",function(){var c=b(this).parent().parent();if(confirm("\u786e\u5b9a\u5220\u9664\u5417\uff1f"))deleteComment(c);else return!1});b.fn.scrollHandle({obj:window,fun:loadmore});prizeBindEvent();collectBindEvet()});
function submitCommentHandler(b){$(b).blur();beforeSubmitHandler(b);""==params.content.trim()?alert("\u8bc4\u8bba\u5185\u5bb9\u4e0d\u80fd\u4e3a\u7a7a"):$.post(base+"/ajax/my/lifecomment/"+channel+"/{0}".format(params.fromuid),params,function(c){afterSubmitHandler(c,b);c=$("#articlecommentcount").html();$("#articlecommentcount").html(parseInt(c)+1)})}var params={imgpath:"",content:"",fromuid:"",cid:"",isnick:""};
function beforeSubmitHandler(b){params={};params.fromuid=fromuid;params.href=href;params.cid=cid;var c=$(b).parent().siblings(".comment_textarea");params.content=c.val().replace(RegExp("\n","gm"),"<br />");b=$(b).parent().parent().parent().parent();var c=b.data("uid"),d=b.data("topuid");"undefined"!=typeof d&&(params.topuid=d);"undefined"!=typeof c&&(params.touid=c,params.touseruid=b.data("touseruid"),params.tousername=b.data("tousername"));$("#isnick").is(":checked")&&(params.isnick="1")}
function afterSubmitHandler(b,c){if(b.success)if(1!=b.bean.ismajor)$(c).parent().siblings("#content").val(""),addComment(b.bean,c),$("div").remove("#replyBoxed");else{$(c).parent().parent(".replyBox").remove();var d=b.bean,a;a='<li class="ment"><div class="userimg">';a+="<img ";""!=d.fromVisitorAvatar&&null!=d.fromVisitorAvatar?a+=' src="'+userimg+d.fromVisitorAvatar+'"/>':(a=""!=me.useravatar.trim()?a+(' src="'+me.useravatar+'"'):a+('src="'+me.defaultImg+'"'),a=me.isuser?a+('class="img-responsive userborder" /><img src="'+
baseimg+'/imgs/default/prince.png" class="img-responsive princeimg" alt="" />'):a+'class="img-responsive"/>');a+="</div>";"undefined"==typeof params.topuid&&(params.topuid=params.touid);a+='<div class="commentitemRt" data-uid="'+d.uid+'" data-topuid="'+params.topuid+'" data-touseruid="'+d.fromuseruid+'"  data-tousername="'+d.fromusername+'"  data-useruid="'+me.uid+'">';a+='<div class="username">';a=""==d.fromVisitorName||null==d.fromVisitorName?a+d.fromusername:a+d.fromVisitorName;a+='<a id="deletecomment_'+
d.uid+'" class="delete"><img src="'+baseimg+'/imgs/default/crush.png"/></a>';a+="</div>";a+='<div class="word">';a+="<span>@"+PrivateString(d.tousername)+":</span>"+d.content+"</p>";a+="</div>";a+='<div class="wordBottom">';a+='<span class="time">&nbsp;\u521a\u521a</span>';a+='<div class="commentOperate">';a+='<a class="zan" id="btnprize_'+channel+"_";a=""!=me||null!=me||"undefined"!=typeof me?a+me.oid:a+"0";a+="_"+cid+"_"+d.uid+'" data-counter=".prizecounter">';a+='<img src="'+baseimg+'/imgs/default/zan.png" data-isprize="false"><span class="zancountjs">0</span></a>';
a+="</div>";a+="</div>";a+="</div>";a+="</li>";$("#secondmajor").prepend(a);initBindEvent();initPage()}else alert("\u8bc4\u8bba\u5931\u8d25")}
function addComment(b,c){var d=$(c).parent().parent(),a="";""!=b.fromVisitorName&&null!=b.fromVisitorName&&(b.fromusername=b.fromVisitorName);-1!=$(d).attr("class").indexOf("wordbox")?(a+='<li><div class="userimg">',a+="<img ",""!=b.fromVisitorAvatar&&null!=b.fromVisitorAvatar?a+=' src="'+userimg+b.fromVisitorAvatar+'"/>':(a=""!=me.useravatar.trim()?a+(' src="'+me.useravatar+'"'):a+('src="'+me.defaultImg+'"'),a=me.isuser?a+('class="img-responsive userborder" /><img src="'+baseimg+'/imgs/default/prince.png" class="img-responsive princeimg" alt="" />'):
a+'class="img-responsive"/>'),a+="</div>",a+='<div class="commentitemRt replay_comment ment"  data-uid="'+b.uid+'" data-touseruid="'+b.fromuseruid+'"  data-tousername="'+b.fromusername+'" data-useruid="'+me.uid+'">',a+='<div class="username">',a+="&nbsp;"+b.fromusername+"&nbsp;",a+='<a id="deletecomment_'+b.uid+'" class="delete"><img src="'+baseimg+'/imgs/default/crush.png"/></a>',a+="</div>",a+='<div class="word">'+b.content,a+="</div>",a+='<div class="wordBottom">',a+='<span class="time">&nbsp;\u521a\u521a</span>',
a+='<div class="commentOperate">',a+='<a class="zan" id="btnprize_'+channel+"_",a=""!=me||null!=me||"undefined"!=typeof me?a+me.oid:a+"0",a+="_"+cid+"_"+b.uid+'" data-counter=".prizecounter">',a+='<img src="'+baseimg+'/imgs/default/zan.png" data-isprize="false"><span class="zancountjs">0</span></a>',a+='<a data-reply="replyopen" href="javascript:;" class="openReply" onclick="reply(this)">\u56de\u590d',a+="</a>",a+='<a data-reply="true" class="openReply hd" onclick="showcomment(this);">&nbsp;|&nbsp;<span id="commentcount">0</span>\u6761\u56de\u590d<img src="'+
baseimg+'/imgs/default/arrawDown.png" alt="\u5c55\u5f00" />',a+="</a>",a+="</div>",a+="</div>",a+='<ul id="second" class="replyList hd" >',a+="</ul>",a+="</div>",a+='<div class="loadmorefinance hd">',a+='<a href="javascript:void(0)" id="loadmore" onclick="load(this)">\u52a0\u8f7d\u66f4\u591a</a>',a+="</div>",a+="</div>",$("#displayComment").prepend(a)):(a+='<li class="ment">',a+='<div class="userimg">',a+="<img ",""!=b.fromVisitorAvatar&&null!=b.fromVisitorAvatar?a+=' src="'+userimg+b.fromVisitorAvatar+
'"/>':(a=""!=me.useravatar.trim()?a+(' src="'+me.useravatar+'"'):a+('src="'+me.defaultImg+'"'),a=me.isuser?a+('class="img-responsive userborder" /><img src="'+baseimg+'/imgs/default/prince.png" class="img-responsive princeimg" alt="" />'):a+'class="img-responsive"/>'),a+="</div>","undefined"==typeof params.topuid&&(params.topuid=params.touid),a+='<div class="commentitemRt" data-uid="'+b.uid+'" data-topuid="'+params.topuid+'" data-touseruid="'+b.fromuseruid+'"  data-tousername="'+b.fromusername+'"  data-useruid="'+
me.uid+'">',a+='<div class="username">',a=""==b.fromVisitorName||null==b.fromVisitorName?a+b.fromusername:a+b.fromVisitorName,a+='<a id="deletecomment_'+b.uid+'" class="delete"><img src="'+baseimg+'/imgs/default/crush.png"/></a>',a+="</div>",a+='<div class="word">',a+="<span>@"+PrivateString(b.tousername)+":</span>"+b.content+"</p>",a+="</div>",a+='<div class="wordBottom">',a+='<span class="time">&nbsp;\u521a\u521a</span>',a+='<div class="commentOperate">',a+='<a class="zan" id="btnprize_'+channel+
"_",a=""!=me||null!=me||"undefined"!=typeof me?a+me.oid:a+"0",a+="_"+cid+"_"+b.uid+'" data-counter=".prizecounter">',a+='<img src="'+baseimg+'/imgs/default/zan.png" data-isprize="false"><span class="zancountjs">0</span></a>',a+='<a data-reply="replyopen" href="javascript:;" class="openReply" onclick="reply(this)">\u56de\u590d',a+="</a>",a+="</div>",a+="</div>",a+="</div>",a+="</li>",-1!=$(c).parent().parent().parent().parent().attr("class").indexOf("replay_comment")?(a=$(c).parent().parent().siblings(".commentOperate").children(".openReply"),
showcomment(a),$(c).parent().parent().siblings("#second").hasClass("hd")&&showcomment(a),$(c).parent().parent().siblings(".commentOperate").children(".openReply").hasClass("hd")&&$(c).parent().parent().siblings(".commentOperate").children(".openReply").removeClass("hd"),a=$(c).parent().parent().siblings(".commentOperate").children(".openReply").children("#commentcount")):($(c).parent().parent().parent().parent().parent().after(a),a=$(c).parent().parent().parent().parent().parent().parent().siblings(".wordBottom").children(".commentOperate").children(".openReply").children("#commentcount")),
$(a).html(parseInt(a.html())+1),$(".comment_textarea1").focus(),$(c).parent().parent().siblings("#second").css("display","block"),initBindEvent(),reply($(d).siblings(".wordBottom").children(".reply")));initPage()}
function reply(b){var c=getIdNumber();"replyopen"==$(b).data("reply")?(0!=$(".replyBox").length&&($(".replyBox").siblings(".wordBottom").children(".commentOperate").children(".reply").data("reply","replyopen"),$(".replyBox").remove()),c='<div class="replyBox" id="replyBoxed">'+('<textarea class="comment_textarea comment_textarea1" id="'+c+'" data-suffix="'+c+'"/>'),c+='<div class="wordpiece">',c+='<a class="face"><img src="'+baseimg+'/imgs/default/smile.png" alt="" /></a>',c+='<button class="submitBtn" onclick="submitCommentHandler(this);">\u53d1\u8868</button>',
c+="</div>",c+="</div>",$(b).parent().after(c),$(".replyBox").css("display","block"),$(".comment_textarea1").focus(),$(b).data("reply","replyclose"),initBindEvent()):($(b).data("reply","replyopen"),$(".replyBox").remove())}function cancale(){0!=$(".replyBox").length?confirm("\u60a8\u786e\u5b9a\u8981\u653e\u5f03\u73b0\u5728\u7684\u8bc4\u8bba\u5417\uff1f")?$(".replyBox").remove():$(".comment_textarea1")[0].focus():$(".replyBox").remove()}var num=1,temp=0,diplayID="#displayComment";
function loadlast(b){hotnum=1;$(b).addClass("curjudge").siblings().removeClass("curjudge");num=1;temp=0;b=base+"/ajax/lifecomment/"+channel+"_"+cid+"_"+fromuid+"_{0}".format(num);$.get(b,function(b){""==b.trim()?(1==num&&$(".nothing").removeClass("hd"),$(window).unbind("scroll",defaultScrollHandler)):(num+=1,$(".nothing").addClass("hd"),$(diplayID).html(b),initPage())})}
function loadmore(){var b=base+"/ajax/lifecomment/"+channel+"_"+cid+"_"+fromuid+"_{0}".format(num);"type"in window&&1==type&&(b=base+"/ajax/lifecomment/hotcomment");temp<num&&(temp=num,$.ajax({type:"GET",url:b,data:{fromuid:fromuid,cid:cid,pagenum:num},success:function(b){""==b.trim()?(1==num&&$(".nothing").removeClass("hd"),$(window).unbind("scroll",defaultScrollHandler)):(num+=1,$(".nothing").addClass("hd"),$(diplayID).append(b),initPage(),prizeBindEvent())}}))}
function updatePrizeCount(b,c){var d=$(b).find(".zancountjs").html();c?$(b).find(".zancountjs").html(parseInt(d)+1):$(b).find(".zancountjs").html(parseInt(d)-1)}function unRegisterPraise(){$("a[id^='btnprize']").each(function(){$(this).off("click")})}function wantComment(){$("#content").focus()}var firstuid,pagenum;
function showcomment(b){"true"==$(b).data("reply")?($(b).parent().siblings("#second").addClass("hd"),$(b).parent().siblings("#second").css("display","none"),$(b).parent().siblings("#second").empty(),$(b).data("reply","false"),$(b).children("img").attr("src",baseimg+"/imgs/default/arrawDown.png")):($(b).data("reply","true"),$(b).parent().siblings("#second").removeClass("hd"),firstuid=$(b).parent().parent().parent().data("uid"),pagenum=1,getcomment(b),$("#loadmore").bind("click",function(){getcomment(b)}),
$(b).children("img").attr("src",baseimg+"/imgs/default/arrawDownup.png"))}function showsecondmajor(b){-1==$("#secondmajor").attr("class").indexOf("hd")?($("#secondmajor").addClass("hd"),$(b).text("\u5c55\u5f00"),$(b).append(' <img src="'+baseimg+'/imgs/default/arrawDown.png" alt="\u5c55\u5f00" />')):($("#secondmajor").removeClass("hd"),$(b).text("\u6536\u8d77"),$(b).append(' <img src="'+baseimg+'/imgs/default/arrawDownup.png" alt="\u6536\u8d77" />'))}
function load(b){b=$(b).parent().siblings(".commentRt").children(".operate").children(".leftcomments");getcomment(b)}
function getcomment(b){var c=base+"/ajax/lifecomment/secondComment_"+firstuid+"_"+cid+"_{0}".format(pagenum);$.ajax({type:"GET",url:c,success:function(c){""==c.trim()?$(".loadmorefinance").addClass("hd"):(19<$(b).parent().siblings("#second").children(".ment").length&&$(".loadmorefinance").removeClass("hd"),$(b).parent().siblings("#second").html(""),$(b).parent().siblings("#second").append(c),initPage(),$(b).parent().siblings("#second").css("display","block"),pagenum+=1,prizeBindEvent())}})}
function deleteComment(b){var c=$(b).data("topuid"),d=null!=c&&""!=c&&"undefined"!=c?$(b).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".leftcomments").children("#commentcount").html():$(b).children(".wordBottom").children(".commentOperate").children(".leftcomments").children("#commentcount").html(),a=$(b).data("uid"),e=$(b).data("touseruid"),f=$(b).data("useruid");$.ajax({type:"GET",url:base+"/ajax/lifecomment/delete/"+a+"/"+e+"/"+f,success:function(a){a&&(null!=
c&&(""!=c&&"undefined"!=c)&&($(b).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".leftcomments").children("#commentcount").html(parseInt(d)-1),0==$(b).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".leftcomments").children("#commentcount").html()&&$(b).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".leftcomments").addClass("hd")),$(b).parent().addClass("hd"))}})}
function replaceContent(b){return b=b.replace(/\[em_([0-9]*)\]/g,'<img src="'+g.baseimg+'/imgs/face/qq/$1.gif" border="0" />')}var idNumber=0;function getIdNumber(){idNumber++;return"finance_"+idNumber.toString()}function initBindEvent(){$(".face").unbind("click");$(".face").qqFace({assign:"content",path:g.baseimg+"/imgs/face/qq/"})}function initPage(){$(".word").each(function(){var b=replaceContent($(this).html());$(this).html(b)})}
function initData(b){return $(b).parent().parent().find("textarea").data("suffix")}
function PrivateString(b){var c=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)/;if(/^((\(\d{2,3}\))|(\d{3}\-))?(13|14|15|18)\d{9}$/.test(b))b=b.replace(/(\d{3})\d{4}(\d{4})/,"$1****$2");else if(c.test(b)){var c=String(b).indexOf("@"),d=b.substr(0,c);b=6<d.length?8<d.length?b.substr(0,3)+"****"+b.substr(c-4):b.substr(0,3)+"****"+b.substr(c-2):b.substr(0,3)+"****"+b.substr(c-1)}return b};
