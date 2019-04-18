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
	$.post(base + "/ajax/addessay/" + cid, params,function(data){
		afterSubmitHandler(data, obj);
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
	params.cid = cid;
	params.imgpath = $("#callbackPath2").val();
	var objs = $(obj).parent().siblings(".comment_textarea");
	params.content = objs.val().replace(new RegExp('\n',"gm"),'<br/>');
	var tempobj = $(obj).parent().parent().parent();
	if(!tempobj.hasClass("contentLeft") || !tempobj.hasClass("commentitemRt")){
		tempobj=$(obj).parent().parent().parent();
	}
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
	if ($("#isnick").attr('checked')) {
		params.isnick = "1";
	}
}

function afterSubmitHandler(result, obj){
	if(result.success){
		$(obj).parent().siblings("#content").val("");
		addComment(result.bean, obj);
		$j(".imgb").imgbox();
		$("div").remove("#replyBoxed");
		$("#callbackPath2").val("");
		$("#preBox").empty();
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
		html += '<li class="commentitem">';
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
				html += 'class="userborder" /><img src="' + baseimg + '/imgs/default/prince.png" class="princeimgeasy" alt="" />';
			}else{
				html += '/>';
			}
		}
		html += '</div>';
		html += '<div class="commentitemRt replay_comment"  data-uid="' + bean.uid + '" data-touseruid="' + bean.fromuseruid + '"  data-tousername="' + bean.fromusername + '" data-useruid="' + me.uid + '">';
		html += '<div class="username">';
		html += '&nbsp;' + bean.fromusername + '&nbsp;';
		html += '<a id="deletecomment_' + bean.uid + '" class="delete"><img src="' + baseimg + '/imgs/default/crush.png"/></a>';
		html += '</div>';
		html += '<div class="word">';
		html += bean.content;
		html += '<div class="pictureBox">';
		var data = $("#callbackPath2").val().split("@");
		
		for (var i = 0; i < data.length - 1 ; i++) {
			var imgmedium = data[i].split(",")[2];
			html +=  '<a class="imgb" title="" href="'+webimg+imgmedium+'" target="_blank"><img alt="" src="'+webimg+imgmedium+'" /></a>';
		}
		html += '</div>';
		html += '</div>';
		html += '<div class="wordBottom">';
		html += '<span class="time">&nbsp;刚刚</span>';
		html += '<div class="commentOperate">';
		html += '<a class="zan" id="btnprizeessay_essay_';
		if (me != "" || me != null || typeof me != 'undefined') {
			html += me.oid;
		} else {
			html += '0';
		}
		html += '_' +cid+'_'+ bean.uid + '" data-counter=".prizecounter">';
		html += '<img src="' + baseimg + '/imgs/default/zan.png" data-isprize="' + false + '"><span class="zancountjs">0</span></a>';
		html += '<a data-reply="replyopen" href="javascript:;" class="reply" onclick="reply(this)"><img src="' + baseimg + '/imgs/default/discuss.png"><span id="commentcount">0</span></a>';
		html += '</a>';
		html += '<a data-reply="true" class="leftcomments hd" onclick="showcomment(this);">';
		html += '</a>';
		html += '</div>';
		html += '</div>';
		html += '<ul id="second" class="replyList hd" >';
		html += '</ul>';
		html += '</div>';
		html += '<div class="loadmorefinance hd">';
		html += '<a href="javascript:void(0)" id="loadmore" onclick="load(this)">加载更多</a>';
		html += '</div>';
		html += '</li>';
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
				html += 'class="userborder" /><img src="' + baseimg + '/imgs/default/prince.png" class="princeimgeasy" alt="" />';
			}else{
				html += '/>';
			}
		}
		html += '</div>';
		if(typeof params.topuid == "undefined"){
			params.topuid = params.touid;
		}
		html += '<div class="commentitemRt" data-uid="' + bean.uid + '" data-topuid="' + params.topuid + '" data-touseruid="' + bean.fromuseruid + '"  data-tousername="' + bean.fromusername + '" data-useruid="' + me.uid + '" >';
		html += '<div class="username">';
		html += '&nbsp;' + bean.fromusername + '&nbsp;';
		html += '<a id="deletecomment_' + bean.uid + '" class="delete"><img src="' + baseimg + '/imgs/default/crush.png"/></a>';
		html += '</div>';
		html += '<div class="word">';
		html += '<span>@'+PrivateString(bean.tousername)+':</span>'+bean.content+'</div>';
		html += '<div class="wordBottom">';
		html += '<span class="time">&nbsp;刚刚</span>';
		html += '<div class="commentOperate">';
		html += '<a class="zan" id="btnprizeessay_essay_';
		if (me != "" || me != null || typeof me != 'undefined') {
			html += me.oid;
		} else {
			html += '0';
		}
		html += '_' +cid+'_'+ bean.uid + '" data-counter=".prizecounter">';
		html += '<img src="' + baseimg + '/imgs/default/zan.png" data-isprize="' + false + '"><span class="zancountjs">0</span></a>';
		html += '<a data-reply="replyopen" href="javascript:;"  class="reply" onclick="reply(this)">回复';
		html += '</a>';
		html += '</div>';
		html += '</div>';
		html += '</div>';
		html += '</li>';
		if($(obj).parent().parent().parent().attr("class").indexOf("replay_comment") != -1){
			// 对评论的回复
			var secondObj=$(obj).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".leftcomments");
			showcomment(secondObj);
			if($(secondObj).parent().parent().siblings("#second").hasClass("hd")){
				showcomment(secondObj);
			}
			if($(secondObj).hasClass("hd")){
				$(secondObj).removeClass("hd");
			}
			var temp = $(obj).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".reply").children("#commentcount");
			$(temp).html(parseInt(temp.html())+1);
		}else{
			// 对回复的回复
			$(obj).parent().parent().parent().parent().after(html);
			var temp = $(obj).parent().parent().parent().parent().parent().siblings(".wordBottom").children(".commentOperate").children(".reply").children("#commentcount");
			$(temp).html(parseInt(temp.html())+1);
		}
		$(obj).parent().parent().siblings("#second").css("display","block");
		initBindEvent();
		reply($(parentobj).siblings(".wordBottom").children().find(".reply"));
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
		html += '<a class="face"><img src="'+baseimg+'/imgs/default/smile.png" alt=""></a>';
		html += '<button class="submitBtn" onclick="submitCommentHandler(this)">提交</button>';
		html += '</div>';
		html += '</div>';
		$(obj).parent().parent().after(html);
		$(".replyBox").css("display","block");
		$(".comment_textarea1").focus();
		initBindEvent();
		$(obj).data("reply","replyclose");
		return;
	}else{
		$(obj).data("reply","replyopen");
		$(".replyBox").remove();
	}
}
var num=1;
var diplayID="#displayComment";
function loadmore(){
	if (num==0) {
		return false;
	}
	var url= "";
	if (type == 0) {
		url = base + "/ajax/essay/"+cid+"_{0}".format(num);
	} else {
		url = base + "/ajax/essay/hot/"+cid+"_{0}".format(num);
	}
	$.ajax({
		type:"GET",
		url:url,
		success:function(data){
			if(data.trim() == ""){
				if (num == 1) {
					$(".nothing").removeClass("hd");
				} 
				num=0;
			}else{
				num += 1;
				$(".nothing").addClass("hd");
				$(diplayID).append(data);
				initPage();
				$j(".imgb").imgbox();
				prizeBindEvent();
			}
		}
	});
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
	var replybox = $(obj).parent().parent();
	if(display == "true"){
		$(replybox).siblings("#second").addClass("hd");
		$(replybox).siblings("#second").css("display","none");
		$(replybox).siblings("#second").empty();
		$(obj).data("reply","false");
		$(obj).text('展开');
		$(obj).append(' <img src="' + baseimg + '/imgs/default/arrawDown.png" alt="展开" />');
		return;
	} else{
		$(obj).data("reply","true");
		$(replybox).siblings("#second").removeClass("hd");
		firstuid = $(replybox).parent().data("uid");
		pagenum=1;
		getcomment(obj);
		$("#loadmore").bind("click", function(){
			getcomment(obj);
		});
		$(obj).text('收起');
		$(obj).append(' <img src="' + baseimg + '/imgs/default/arrawDownup.png" alt="收起" />');
	}
}

function load(obj){
	var moreobj = $(obj).parent().siblings(".commentitemRt").children(".wordBottom").children(".qsCommentOperate").children(".leftcomments");
	getcomment(moreobj);
}
function getcomment(obj){
	var url = base + "/ajax/essay/"+firstuid+"_"+cid+"_{0}".format(pagenum);
	$.ajax({
		type:"GET",
		url:url,
		success:function(data){
			if(data.trim() == ""){
				$(".loadmorefinance").addClass("hd");
			}else{
				if($(obj).parent().parent().siblings("#second").children(".ment").length>19){
					$(".loadmorefinance").removeClass("hd");
				}
				$(obj).parent().parent().siblings("#second").append(data);
				initPage();
				$(obj).parent().parent().siblings("#second").css("display","block");
				pagenum+=1;
				prizeBindEvent();
			}
		}
	});
}

function deleteComment(obj){
	var topuid = $(obj).data("topuid");
	var secondcommentcount = $(obj).children(".wordBottom").children(".qsCommentOperate").children(".reply").children("#commentcount").html();
	var commentuid = $(obj).data("uid");
	var fromuseruid = $(obj).data("touseruid");
	var useruid = $(obj).data("useruid");
	var url = base + "/ajax/essay/delete/"+commentuid+"/"+fromuseruid+"/"+useruid;
	$.ajax({
		type:"GET",
		url:url,
		success:function(data){
			if(data){
				if(topuid != null && topuid != '' && topuid != 'undefined'){
					var tempcount = $(obj).parent().parent().siblings(".wordBottom").children(".commentOperate").children(".reply").children("#commentcount");
					$(tempcount).html(parseInt(tempcount.html()) - 1);
					if(parseInt(tempcount.html()) == 0){
						$(tempcount).parent().siblings(".leftcomments").addClass("hd");
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
	var assign=$(obj).parent().parent().parent().find("textarea").data("suffix");
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