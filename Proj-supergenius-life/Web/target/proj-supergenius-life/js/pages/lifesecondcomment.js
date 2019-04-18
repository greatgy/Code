$(function($) {
	loadmore();
	$(document).on('click', "a[id^='btnprize']", function(){
		unRegisterPraise();
		$(this).prize();
     }); 
	
	$(document).on('click', "a[id^='deletecomment']", function(){
		var obj = $(this).parent().parent();
		if(confirm("确定删除吗？")){
			deleteComment(obj);
		}else{
			return false;
		}
     });
	
	$.fn.scrollHandle({
		obj: window,
		fun: loadmore
	})
	prizeBindEvent(); 
	collectBindEvet();
});

function submitCommentHandler(obj){
	$(obj).blur();
	beforeSubmitHandler(obj);
	if(params.content.trim() == ""){
		alert("评论内容不能为空");
		return;
	}
	$.post(base + "/ajax/my/lifecomment/"+channel+"/{0}".format(params.fromuid), params,function(data){
		afterSubmitHandler(data, obj);
		var tempuid = $("#articlecommentcount").html();
		$("#articlecommentcount").html(parseInt(tempuid)+1);
	});
}

var params = {
	imgpath: "",
	content: "",
	fromuid: "",
	cid:"",
	isnick:""
}

function beforeSubmitHandler(obj){
	params = {};
	params.fromuid = fromuid;
	params.href = href;
	params.cid = cid;
	var objs = $(obj).parent().siblings(".comment_textarea");
	params.content = objs.val().replace(new RegExp('\n',"gm"), '<br />');
	var tempobj = $(obj).parent().parent().parent().parent();
	var touid = tempobj.data("uid");
	var topuid = tempobj.data("topuid");
	if(typeof topuid != "undefined"){
		params.topuid = topuid;
	}
	if(typeof touid != "undefined"){
		params.touid = touid;
		params.touseruid = tempobj.data("touseruid");
		params.tousername = tempobj.data("tousername");
	}
	if ($("#isnick").is(':checked')) {
		params.isnick = "1";
	}
}

function afterSubmitHandler(result, obj){
	if(result.success){
		if(result.bean.ismajor != 1) {
			$(obj).parent().siblings("#content").val("");
			addComment(result.bean, obj);
			$("div").remove("#replyBoxed");
		} else {
			$(obj).parent().parent(".replyBox").remove();
			var bean = result.bean;
			var html = '';
			html += '<li class="ment">';
			html += '<div class="userimg">';
			html += '<img ';
			if (bean.fromVisitorAvatar != "" && bean.fromVisitorAvatar != null) {
				html += ' src="' + userimg + bean.fromVisitorAvatar + '"/>';
			} else{
				if (me.useravatar.trim() != "") {
					html += ' src="' + me.useravatar + '"';
				} else{
					html += 'src="' + me.defaultImg + '"';
				}
				if (me.isuser) {
					html += 'class="img-responsive userborder" /><img src="' + baseimg + '/imgs/default/prince.png" class="img-responsive princeimg" alt="" />';
				}else{
					html += 'class="img-responsive"/>';
				}
			}
			html += '</div>';
			if(typeof params.topuid == "undefined"){
				params.topuid = params.touid;
			}
			html += '<div class="commentitemRt" data-uid="' + bean.uid + '" data-topuid="' + params.topuid + '" data-touseruid="' + bean.fromuseruid + '"  data-tousername="' + bean.fromusername + '"  data-useruid="' + me.uid + '">';
			html += '<div class="username">';
			if (bean.fromVisitorName == '' || bean.fromVisitorName == null) {
				html +=  bean.fromusername;
			} else {
				html +=  bean.fromVisitorName;
			}
			
			html += '<a id="deletecomment_' + bean.uid + '" class="delete"><img src="' + baseimg + '/imgs/default/crush.png"/></a>';
			html += '</div>';
			html += '<div class="word">';
			html += '<span>@'+PrivateString(bean.tousername)+':</span>'+bean.content+'</p>';
			html += '</div>';
			html += '<div class="wordBottom">';
			html += '<span class="time">&nbsp;刚刚</span>';
			html += '<div class="commentOperate">';
			html += '<a class="zan" id="btnprize_'+channel+'_';
			if (me != "" || me != null || typeof me != 'undefined') {
				html += me.oid;
			} else {
				html += '0';
			}
			html += '_' +cid+'_'+ bean.uid + '" data-counter=".prizecounter">';
			html += '<img src="' + baseimg + '/imgs/default/zan.png" data-isprize="' + false + '"><span class="zancountjs">0</span></a>';
			html += '</div>';
			html += '</div>';
			html += '</div>';
			html += '</li>'
			$("#secondmajor").prepend(html);
			initBindEvent();
			initPage();
		}	
	}else{
		alert("评论失败");
	}
}

function addComment(bean, obj){
	var parentobj = $(obj).parent().parent();
	var html = '';
	if (bean.fromVisitorName != '' && bean.fromVisitorName != null) {
		bean.fromusername= bean.fromVisitorName;
	}
	if($(parentobj).attr("class").indexOf("wordbox") != -1){
		// 对文章的回复
		html += '<li>';
		html += '<div class="userimg">';
		html += '<img ';
		if (bean.fromVisitorAvatar != "" && bean.fromVisitorAvatar != null) {
			html += ' src="' + userimg + bean.fromVisitorAvatar + '"/>';
		} else{
			if (me.useravatar.trim() != "") {
				html += ' src="' + me.useravatar + '"';
			} else{
				html += 'src="' + me.defaultImg + '"';
			}
			if (me.isuser) {
				html += 'class="img-responsive userborder" /><img src="' + baseimg + '/imgs/default/prince.png" class="img-responsive princeimg" alt="" />';
			}else{
				html += 'class="img-responsive"/>';
			}
		}
		html += '</div>';
		html += '<div class="commentitemRt replay_comment ment"  data-uid="' + bean.uid + '" data-touseruid="' + bean.fromuseruid + '"  data-tousername="' + bean.fromusername + '" data-useruid="' + me.uid + '">';
		html += '<div class="username">';
		html += '&nbsp;' + bean.fromusername + '&nbsp;';
		html += '<a id="deletecomment_' + bean.uid + '" class="delete"><img src="' + baseimg + '/imgs/default/crush.png"/></a>';
		html += '</div>';
		html += '<div class="word">' + bean.content;
		html += '</div>';
		html += '<div class="wordBottom">';
		html += '<span class="time">&nbsp;刚刚</span>';
		html += '<div class="commentOperate">';
		html += '<a class="zan" id="btnprize_'+channel+'_';
		if (me != "" || me != null || typeof me != 'undefined') {
			html += me.oid;
		} else {
			html += '0';
		}
		html += '_' +cid+'_'+ bean.uid + '" data-counter=".prizecounter">';
		html += '<img src="' + baseimg + '/imgs/default/zan.png" data-isprize="' + false + '"><span class="zancountjs">0</span></a>';
		html += '<a data-reply="replyopen" href="javascript:;" class="openReply" onclick="reply(this)">回复';
		html += '</a>';
		html += '<a data-reply="true" class="openReply hd" onclick="showcomment(this);">&nbsp;|&nbsp;<span id="commentcount">0</span>条回复<img src="' + baseimg + '/imgs/default/arrawDown.png" alt="展开" />';
		html += '</a>';
		html += '</div>';
		html += '</div>';
		html += '<ul id="second" class="replyList hd" >';
		html += '</ul>';
		html += '</div>';
		html += '<div class="loadmorefinance hd">';
		html += '<a href="javascript:void(0)" id="loadmore" onclick="load(this)">加载更多</a>';
		html += '</div>';
		html += '</div>';
		$("#displayComment").prepend(html);
	} else {
		html += '<li class="ment">';
		html += '<div class="userimg">';
		html += '<img ';
		if (bean.fromVisitorAvatar != "" && bean.fromVisitorAvatar != null) {
			html += ' src="' + userimg + bean.fromVisitorAvatar + '"/>';
		} else{
			if (me.useravatar.trim() != "") {
				html += ' src="' + me.useravatar + '"';
			} else{
				html += 'src="' + me.defaultImg + '"';
			}
			if (me.isuser) {
				html += 'class="img-responsive userborder" /><img src="' + baseimg + '/imgs/default/prince.png" class="img-responsive princeimg" alt="" />';
			}else{
				html += 'class="img-responsive"/>';
			}
		}
		html += '</div>';
		if(typeof params.topuid == "undefined"){
			params.topuid = params.touid;
		}
		html += '<div class="commentitemRt" data-uid="' + bean.uid + '" data-topuid="' + params.topuid + '" data-touseruid="' + bean.fromuseruid + '"  data-tousername="' + bean.fromusername + '"  data-useruid="' + me.uid + '">';
		html += '<div class="username">';
		if (bean.fromVisitorName == '' || bean.fromVisitorName == null) {
			html +=  bean.fromusername;
		} else {
			html +=  bean.fromVisitorName;
		}
		
		html += '<a id="deletecomment_' + bean.uid + '" class="delete"><img src="' + baseimg + '/imgs/default/crush.png"/></a>';
		html += '</div>';
		html += '<div class="word">';
		html += '<span>@'+PrivateString(bean.tousername)+':</span>'+bean.content+'</p>';
		html += '</div>';
		html += '<div class="wordBottom">';
		html += '<span class="time">&nbsp;刚刚</span>';
		html += '<div class="commentOperate">';
		html += '<a class="zan" id="btnprize_'+channel+'_';
		if (me != "" || me != null || typeof me != 'undefined') {
			html += me.oid;
		} else {
			html += '0';
		}
		html += '_' +cid+'_'+ bean.uid + '" data-counter=".prizecounter">';
		html += '<img src="' + baseimg + '/imgs/default/zan.png" data-isprize="' + false + '"><span class="zancountjs">0</span></a>';
		html += '<a data-reply="replyopen" href="javascript:;" class="openReply" onclick="reply(this)">回复';
		html += '</a>';
		html += '</div>';
		html += '</div>';
		html += '</div>';
		html += '</li>'
		if($(obj).parent().parent().parent().parent().attr("class").indexOf("replay_comment") != -1){
			// 对评论的回复
			var secondObj=$(obj).parent().parent().siblings(".commentOperate").children(".openReply");
			showcomment(secondObj);
			if($(obj).parent().parent().siblings("#second").hasClass("hd")){
				showcomment(secondObj);
			}
			// $(obj).parent().siblings("#second").prepend(html);
			if($(obj).parent().parent().siblings(".commentOperate").children(".openReply").hasClass("hd")){
				$(obj).parent().parent().siblings(".commentOperate").children(".openReply").removeClass("hd");
			}
			var temp = $(obj).parent().parent().siblings(".commentOperate").children(".openReply").children("#commentcount");
			$(temp).html(parseInt(temp.html())+1);
		}else{
			// 对回复的回复
			$(obj).parent().parent().parent().parent().parent().after(html);
			var temp = $(obj).parent().parent().parent().parent().parent().parent().siblings(".wordBottom").children(".commentOperate").children(".openReply").children("#commentcount");
			$(temp).html(parseInt(temp.html())+1);
		}
		$(".comment_textarea1").focus();
		$(obj).parent().parent().siblings("#second").css("display","block");
		initBindEvent();
		reply($(parentobj).siblings(".wordBottom").children(".reply"));
	}
	initPage();
}

/**
 * 点击评论或者回复,加载评论框
 * 
 * @param obj
 * @returns
 */
function reply(obj){
	var idnumber = getIdNumber();
	if($(obj).data("reply") == "replyopen"){
		if($(".replyBox").length != 0) {
			$(".replyBox").siblings(".wordBottom").children(".commentOperate").children(".reply").data("reply","replyopen");
			$(".replyBox").remove();
		}
		var html = "";
		html += '<div class="replyBox" id="replyBoxed">';
		html += '<textarea class="comment_textarea comment_textarea1" id="' + idnumber + '" data-suffix="' + idnumber + '"/>';
		html += '<div class="wordpiece">';
		html += '<a class="face"><img src="'+baseimg+'/imgs/default/smile.png" alt="" /></a>';
		html += '<button class="submitBtn" onclick="submitCommentHandler(this);">发表</button>';
		html += '</div>';
		html += '</div>';
		$(obj).parent().after(html);
		$(".replyBox").css("display","block");
		$(".comment_textarea1").focus();
		$(obj).data("reply","replyclose");
		initBindEvent();
	}else{
		$(obj).data("reply","replyopen");
		$(".replyBox").remove();
	}
}
function cancale(obj) {
	if($(".replyBox").length != 0) {
		if(!confirm("您确定要放弃现在的评论吗？")){
			$(".comment_textarea1")[0].focus();
			return;
		}else{
			$(".replyBox").remove();
			return;
		}
	}
	$(".replyBox").remove();
	return;
}
var num=1;
var temp=0;
var diplayID="#displayComment";
function loadlast(obj){
	hotnum = 1;
	$(obj).addClass("curjudge").siblings().removeClass("curjudge");
	num=1;
	temp=0;
	var url = base + "/ajax/lifecomment/"+channel+"_" + cid+"_"+fromuid+"_{0}".format(num);
	$.get(url, function(data){
		if(data.trim() == ""){
			if (num == 1) {
				$(".nothing").removeClass("hd");
			} 
			$(window).unbind("scroll", defaultScrollHandler);
		}else{
			num += 1;
			$(".nothing").addClass("hd");
			$(diplayID).html(data);
			initPage();// 表情解析     
		}
	});
}
function loadmore(){
	var url = base + "/ajax/lifecomment/"+channel+"_" + cid+"_"+fromuid+"_{0}".format(num);
	if("type" in window){
		if(type == 1){
			url = base + "/ajax/lifecomment/hotcomment";
		}
	}
	if(temp<num){
		temp=num;
		$.ajax({
			type:"GET",
			url:url,
			data: {fromuid:fromuid, cid:cid, pagenum:num},
			success:function(data){
				if(data.trim() == ""){
					if (num == 1) {
						$(".nothing").removeClass("hd");
					} 
					$(window).unbind("scroll", defaultScrollHandler);
				}else{
					num += 1;
					$(".nothing").addClass("hd");
					$(diplayID).append(data);
					initPage();// 表情解析
					prizeBindEvent();
				}
			}
		});
	}
}

/**
 * 重写更新赞的数量
 * 
 * @param obj
 * @param isprize
 */
function updatePrizeCount(obj, isprize){
	var countstr = $(obj).find(".zancountjs").html();
	if (isprize) {
		$(obj).find(".zancountjs").html(parseInt(countstr)+1);
	} else {
		$(obj).find(".zancountjs").html(parseInt(countstr)-1);
	}
}

/**
 * 重写解除事件绑定
 */
function unRegisterPraise(){
	$("a[id^='btnprize']").each(function(){
		$(this).off("click");
	});
}

/**
 * 获得输入框焦点
 * 
 * @returns
 */
function wantComment() {
	$("#content").focus();
}

var firstuid;
var pagenum;
function showcomment(obj){
	var display=$(obj).data("reply");
	if(display == "true"){
		$(obj).parent().siblings("#second").addClass("hd");
		$(obj).parent().siblings("#second").css("display","none");
		$(obj).parent().siblings("#second").empty();
		$(obj).data("reply","false");
		$(obj).children("img").attr('src', baseimg +'/imgs/default/arrawDown.png'); 
		return;
	} else{
		$(obj).data("reply","true");
		$(obj).parent().siblings("#second").removeClass("hd");
		firstuid = $(obj).parent().parent().parent().data("uid");
		pagenum=1;
		getcomment(obj);
		$("#loadmore").bind("click", function(){
			getcomment(obj);
		});
		$(obj).children("img").attr('src', baseimg +'/imgs/default/arrawDownup.png'); 
	}
}

function showsecondmajor(obj){
	if($("#secondmajor").attr("class").indexOf("hd") == -1){
		$("#secondmajor").addClass("hd");
		$(obj).text('展开');
		$(obj).append(' <img src="' + baseimg + '/imgs/default/arrawDown.png" alt="展开" />');
		return;
	} else{
		$("#secondmajor").removeClass("hd");
		$(obj).text('收起');
		$(obj).append(' <img src="' + baseimg + '/imgs/default/arrawDownup.png" alt="收起" />');
	}
}

function load(obj){
	var moreobj = $(obj).parent().siblings(".commentRt").children(".operate").children(".leftcomments");
	getcomment(moreobj);
}
function getcomment(obj){
	var url = base + "/ajax/lifecomment/secondComment_"+firstuid+"_"+cid+"_{0}".format(pagenum);
	$.ajax({
		type:"GET",
		url:url,
		success:function(data){
			if(data.trim() == ""){
				$(".loadmorefinance").addClass("hd");
			}else{
				if($(obj).parent().siblings("#second").children(".ment").length>19){
					$(".loadmorefinance").removeClass("hd");
				}
				$(obj).parent().siblings("#second").html('');
				$(obj).parent().siblings("#second").append(data);
				initPage();
				$(obj).parent().siblings("#second").css("display","block");
				pagenum+=1;
				prizeBindEvent();
			}
		}
	});
}

function deleteComment(obj){
	var topuid = $(obj).data("topuid");
	if(topuid != null && topuid != '' && topuid != 'undefined'){
		var secondcommentcount = $(obj).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".leftcomments").children("#commentcount").html();
	}else{
		var secondcommentcount = $(obj).children(".wordBottom").children(".commentOperate").children(".leftcomments").children("#commentcount").html();
	}
	var commentuid = $(obj).data("uid");
	var fromuseruid = $(obj).data("touseruid");
	var useruid = $(obj).data("useruid");
	var url = base + "/ajax/lifecomment/delete/"+commentuid+"/"+fromuseruid+"/"+useruid;
	$.ajax({
		type:"GET",
		url:url,
		success:function(data){
			if(data){
				if(topuid != null && topuid != '' && topuid != 'undefined'){
					$(obj).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".leftcomments").children("#commentcount").html(parseInt(secondcommentcount) - 1);
					var tempcount = $(obj).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".leftcomments").children("#commentcount").html();
					if(tempcount == 0){
						$(obj).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".leftcomments").addClass("hd");
					}
				}
				$(obj).parent().addClass("hd");
			}else{
				return;
			}
		}
	});
}

/*******************************************************************************
 * 表情
 */
// 将[em_*]替换为img标签
function replaceContent(str) {
	str = str.replace(/\[em_([0-9]*)\]/g,'<img src="' + g.baseimg + '/imgs/face/qq/$1.gif" border="0" />');
	return str;
}

var idNumber = 0;
function getIdNumber() {
	idNumber++;
	return "finance_" + idNumber.toString();
}

function initBindEvent() {
	$('.face').unbind( "click" );
	$('.face').qqFace({ 
        assign:'content', 
        path: g.baseimg + '/imgs/face/qq/'
    });
}

/* 生成页面表情 */
function initPage() {
	$(".word").each(function(){
		var content = replaceContent($(this).html());
		$(this).html(content);
	});
}

/* 返回要赋值的输入框 */
function initData(obj) {
	var assign=$(obj).parent().parent().find("textarea").data("suffix");
	return assign;        
}

/***********************************隐藏用户名********************************************/
function PrivateString(content){
	var emailReg = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)/;
    var mobileReg = /^((\(\d{2,3}\))|(\d{3}\-))?(13|14|15|18)\d{9}$/;
    if(mobileReg.test(content)){
    	content=content.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2');
    }else if(emailReg.test(content)){
         var index=String(content).indexOf('@');
         var prefix = content.substr(0, index);
		if(prefix.length > 6){
			if(prefix.length > 8){
				content=content.substr(0,3)+'****'+content.substr(index-4);
			}else{
				content=content.substr(0,3)+'****'+content.substr(index-2);
			}
		}else{
			content = content.substr(0,3)+'****'+content.substr(index-1);
		}
    }
    return content;
 }