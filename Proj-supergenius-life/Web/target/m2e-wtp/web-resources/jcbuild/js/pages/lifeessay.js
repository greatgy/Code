$(function(b){loadmore();b(document).on("click","a[id^='btnprize']",function(){unRegisterPraise();b(this).prize()});b(document).on("click","a[id^='deletecomment']",function(){var c=b(this).parent().parent();if(confirm("\u786e\u5b9a\u5220\u9664\u5417\uff1f"))deleteComment(c);else return!1});b.fn.scrollHandle({obj:window,fun:loadmore});prizeBindEvent();collectBindEvet()});
function submitCommentHandler(b){$(b).blur();beforeSubmitHandler(b);""==params.content.trim()?alert("\u8bc4\u8bba\u5185\u5bb9\u4e0d\u80fd\u4e3a\u7a7a"):$.post(base+"/ajax/addessay/"+cid,params,function(c){afterSubmitHandler(c,b)})}var params={imgpath:"",content:"",fromuid:"",cid:"",isnick:""};
function beforeSubmitHandler(b){params={};params.cid=cid;params.imgpath=$("#callbackPath2").val();var c=$(b).parent().siblings(".comment_textarea");params.content=c.val().replace(RegExp("\n","gm"),"<br/>");c=$(b).parent().parent().parent();if(!c.hasClass("contentLeft")||!c.hasClass("commentitemRt"))c=$(b).parent().parent().parent();b=c.data("uid");var d=c.data("topuid");"undefined"!=typeof d&&(params.topuid=d);"undefined"!=typeof b&&(params.touid=b,params.touseruid=c.data("touseruid"),params.tousername=
c.data("tousername"));$("#isnick").attr("checked")&&(params.isnick="1")}function afterSubmitHandler(b,c){b.success?($(c).parent().siblings("#content").val(""),addComment(b.bean,c),$j(".imgb").imgbox(),$("div").remove("#replyBoxed"),$("#callbackPath2").val(""),$("#preBox").empty()):alert("\u8bc4\u8bba\u5931\u8d25")}
function addComment(b,c){var d=$(c).parent().parent(),a="";""!=b.fromVisitorName&&null!=b.fromVisitorName&&(b.fromusername=b.fromVisitorName);if(-1!=$(d).attr("class").indexOf("wordbox")){a+='<li class="commentitem"><div class="userimg">';a+="<img ";""!=b.fromVisitorAvatar&&null!=b.fromVisitorAvatar?a+=' src="'+userimg+b.fromVisitorAvatar+'"/>':(a=""!=me.useravatar.trim()?a+(' src="'+me.useravatar+'"'):a+('src="'+me.defaultImg+'"'),a=me.isuser?a+('class="userborder" /><img src="'+baseimg+'/imgs/default/prince.png" class="princeimgeasy" alt="" />'):
a+"/>");for(var a=a+"</div>",a=a+('<div class="commentitemRt replay_comment"  data-uid="'+b.uid+'" data-touseruid="'+b.fromuseruid+'"  data-tousername="'+b.fromusername+'" data-useruid="'+me.uid+'">'),a=a+'<div class="username">',a=a+("&nbsp;"+b.fromusername+"&nbsp;"),a=a+('<a id="deletecomment_'+b.uid+'" class="delete"><img src="'+baseimg+'/imgs/default/crush.png"/></a>'),a=a+"</div>",a=a+'<div class="word">',a=a+b.content,a=a+'<div class="pictureBox">',d=$("#callbackPath2").val().split("@"),e=0;e<
d.length-1;e++)var f=d[e].split(",")[2],a=a+('<a class="imgb" title="" href="'+webimg+f+'" target="_blank"><img alt="" src="'+webimg+f+'" /></a>');a+="</div>";a+="</div>";a+='<div class="wordBottom">';a+='<span class="time">&nbsp;\u521a\u521a</span>';a+='<div class="commentOperate">';a+='<a class="zan" id="btnprizeessay_essay_';a=""!=me||null!=me||"undefined"!=typeof me?a+me.oid:a+"0";a+="_"+cid+"_"+b.uid+'" data-counter=".prizecounter">';a+='<img src="'+baseimg+'/imgs/default/zan.png" data-isprize="false"><span class="zancountjs">0</span></a>';
a+='<a data-reply="replyopen" href="javascript:;" class="reply" onclick="reply(this)"><img src="'+baseimg+'/imgs/default/discuss.png"><span id="commentcount">0</span></a>';a+="</a>";a+='<a data-reply="true" class="leftcomments hd" onclick="showcomment(this);">';a+="</a>";a+="</div>";a+="</div>";a+='<ul id="second" class="replyList hd" >';a+="</ul>";a+="</div>";a+='<div class="loadmorefinance hd">';a+='<a href="javascript:void(0)" id="loadmore" onclick="load(this)">\u52a0\u8f7d\u66f4\u591a</a>';a+=
"</div>";a+="</li>";$("#displayComment").prepend(a)}else a+='<li class="ment">',a+='<div class="userimg">',a+="<img ",""!=b.fromVisitorAvatar&&null!=b.fromVisitorAvatar?a+=' src="'+userimg+b.fromVisitorAvatar+'"/>':(a=""!=me.useravatar.trim()?a+(' src="'+me.useravatar+'"'):a+('src="'+me.defaultImg+'"'),a=me.isuser?a+('class="userborder" /><img src="'+baseimg+'/imgs/default/prince.png" class="princeimgeasy" alt="" />'):a+"/>"),a+="</div>","undefined"==typeof params.topuid&&(params.topuid=params.touid),
a+='<div class="commentitemRt" data-uid="'+b.uid+'" data-topuid="'+params.topuid+'" data-touseruid="'+b.fromuseruid+'"  data-tousername="'+b.fromusername+'" data-useruid="'+me.uid+'" >',a+='<div class="username">',a+="&nbsp;"+b.fromusername+"&nbsp;",a+='<a id="deletecomment_'+b.uid+'" class="delete"><img src="'+baseimg+'/imgs/default/crush.png"/></a>',a+="</div>",a+='<div class="word">',a+="<span>@"+PrivateString(b.tousername)+":</span>"+b.content+"</div>",a+='<div class="wordBottom">',a+='<span class="time">&nbsp;\u521a\u521a</span>',
a+='<div class="commentOperate">',a+='<a class="zan" id="btnprizeessay_essay_',a=""!=me||null!=me||"undefined"!=typeof me?a+me.oid:a+"0",a+="_"+cid+"_"+b.uid+'" data-counter=".prizecounter">',a+='<img src="'+baseimg+'/imgs/default/zan.png" data-isprize="false"><span class="zancountjs">0</span></a>',a+='<a data-reply="replyopen" href="javascript:;"  class="reply" onclick="reply(this)">\u56de\u590d',a+="</a>",a+="</div>",a+="</div>",a+="</div>",a+="</li>",-1!=$(c).parent().parent().parent().attr("class").indexOf("replay_comment")?
(a=$(c).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".leftcomments"),showcomment(a),$(a).parent().parent().siblings("#second").hasClass("hd")&&showcomment(a),$(a).hasClass("hd")&&$(a).removeClass("hd"),a=$(c).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".reply").children("#commentcount")):($(c).parent().parent().parent().parent().after(a),a=$(c).parent().parent().parent().parent().parent().siblings(".wordBottom").children(".commentOperate").children(".reply").children("#commentcount")),
$(a).html(parseInt(a.html())+1),$(c).parent().parent().siblings("#second").css("display","block"),initBindEvent(),reply($(d).siblings(".wordBottom").children().find(".reply"));initPage()}
function reply(b){var c=getIdNumber();"replyopen"==$(b).data("reply")?(0!=$(".replyBox").length&&($(".replyBox").siblings(".wordBottom").children(".commentOperate").children(".reply").data("reply","replyopen"),$(".replyBox").remove()),c='<div class="replyBox" id="replyBoxed">'+('<textarea class="comment_textarea comment_textarea1" id="'+c+'" data-suffix="'+c+'"/>'),c+='<div class="wordpiece">',c+='<a class="face"><img src="'+baseimg+'/imgs/default/smile.png" alt=""></a>',c+='<button class="submitBtn" onclick="submitCommentHandler(this)">\u63d0\u4ea4</button>',
c+="</div>",c+="</div>",$(b).parent().parent().after(c),$(".replyBox").css("display","block"),$(".comment_textarea1").focus(),initBindEvent(),$(b).data("reply","replyclose")):($(b).data("reply","replyopen"),$(".replyBox").remove())}var num=1,diplayID="#displayComment";
function loadmore(){if(0==num)return!1;var b="",b=0==type?base+"/ajax/essay/"+cid+"_{0}".format(num):base+"/ajax/essay/hot/"+cid+"_{0}".format(num);$.ajax({type:"GET",url:b,success:function(b){""==b.trim()?(1==num&&$(".nothing").removeClass("hd"),num=0):(num+=1,$(".nothing").addClass("hd"),$(diplayID).append(b),initPage(),$j(".imgb").imgbox(),prizeBindEvent())}})}
function updatePrizeCount(b,c){var d=$(b).find(".zancountjs").html();c?$(b).find(".zancountjs").html(parseInt(d)+1):$(b).find(".zancountjs").html(parseInt(d)-1)}function unRegisterPraise(){$("a[id^='btnprize']").each(function(){$(this).off("click")})}function wantComment(){$("#content").focus()}var firstuid,pagenum;
function showcomment(b){var c=$(b).data("reply"),d=$(b).parent().parent();"true"==c?($(d).siblings("#second").addClass("hd"),$(d).siblings("#second").css("display","none"),$(d).siblings("#second").empty(),$(b).data("reply","false"),$(b).text("\u5c55\u5f00"),$(b).append(' <img src="'+baseimg+'/imgs/default/arrawDown.png" alt="\u5c55\u5f00" />')):($(b).data("reply","true"),$(d).siblings("#second").removeClass("hd"),firstuid=$(d).parent().data("uid"),pagenum=1,getcomment(b),$("#loadmore").bind("click",
function(){getcomment(b)}),$(b).text("\u6536\u8d77"),$(b).append(' <img src="'+baseimg+'/imgs/default/arrawDownup.png" alt="\u6536\u8d77" />'))}function load(b){b=$(b).parent().siblings(".commentitemRt").children(".wordBottom").children(".qsCommentOperate").children(".leftcomments");getcomment(b)}
function getcomment(b){var c=base+"/ajax/essay/"+firstuid+"_"+cid+"_{0}".format(pagenum);$.ajax({type:"GET",url:c,success:function(c){""==c.trim()?$(".loadmorefinance").addClass("hd"):(19<$(b).parent().parent().siblings("#second").children(".ment").length&&$(".loadmorefinance").removeClass("hd"),$(b).parent().parent().siblings("#second").append(c),initPage(),$(b).parent().parent().siblings("#second").css("display","block"),pagenum+=1,prizeBindEvent())}})}
function deleteComment(b){var c=$(b).data("topuid");$(b).children(".wordBottom").children(".qsCommentOperate").children(".reply").children("#commentcount").html();var d=$(b).data("uid"),a=$(b).data("touseruid"),e=$(b).data("useruid");$.ajax({type:"GET",url:base+"/ajax/essay/delete/"+d+"/"+a+"/"+e,success:function(a){a&&(null!=c&&(""!=c&&"undefined"!=c)&&(a=$(b).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".reply").children("#commentcount"),$(a).html(parseInt(a.html())-
1),0==parseInt(a.html())&&$(a).parent().siblings(".leftcomments").addClass("hd")),$(b).parent().addClass("hd"))}})}function replaceContent(b){return b=b.replace(/\[em_([0-9]*)\]/g,'<img src="'+g.baseimg+'/imgs/face/qq/$1.gif" border="0" />')}var idNumber=0;function getIdNumber(){idNumber++;return"finance_"+idNumber.toString()}function initBindEvent(){$(".face").unbind("click");$(".face").qqFace({assign:"content",path:g.baseimg+"/imgs/face/qq/"})}
function initPage(){$(".word").each(function(){var b=replaceContent($(this).html());$(this).html(b)})}function initData(b){return $(b).parent().parent().parent().find("textarea").data("suffix")}
function PrivateString(b){var c=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)/;if(/^((\(\d{2,3}\))|(\d{3}\-))?(13|14|15|18)\d{9}$/.test(b))b=b.replace(/(\d{3})\d{4}(\d{4})/,"$1****$2");else if(c.test(b)){var c=String(b).indexOf("@"),d=b.substr(0,c);b=6<d.length?8<d.length?b.substr(0,3)+"****"+b.substr(c-4):b.substr(0,3)+"****"+b.substr(c-2):b.substr(0,3)+"****"+b.substr(c-1)}return b};