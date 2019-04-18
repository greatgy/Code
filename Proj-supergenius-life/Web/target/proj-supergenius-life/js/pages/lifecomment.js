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
		dataparams.comment = dataparams.comment + 1; 
	});
}

var params = {
	content: "",
	fromuid: "",
	cid:"",
	isnick:"",
	href:""
}

function beforeSubmitHandler(obj){
	params = {};
	params.fromuid = fromuid;
	params.href = href;
	params.cid = cid;
	var objs = $(obj).parent().siblings(".comment_textarea");
	params.content = objs.val().replace(new RegExp('\n',"gm"), '<br />');
	//对评论的评论
	var tempobj = $(obj).parent().parent().parent().parent();
	if(!$(tempobj).hasClass("ment")){
		tempobj = $(obj).parent().parent().parent();
	}
	var touid = tempobj.data("uid");
	var topuid = tempobj.data("topuid");
	if(typeof topuid != undefined && topuid!="undefined"){
		params.topuid = topuid;
	}
	if(typeof touid != undefined && touid!="undefined"){
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
		if(result.bean.ismajor != 1) {
			$(obj).parent().siblings(".comment_textarea").val("");
			addComment(result.bean, obj);
			$("div").remove("#replyBoxed");
		} else {
			//对专家点评的回复
			console.log(123)
			var bean = result.bean;
			var html = '';
			html += '<li class="ment">';
			html += '<div class="qsUserimg">';
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
					html += 'class="userborder" /><img src="' + baseimg + '/imgs/default/prince.png" class="problemprinceimg" alt="" />';
				}else{
					html += '/>';
				}
			}
			html += '</div>';
			if(typeof params.topuid == "undefined"){
				params.topuid = params.touid;
			}
			html += '<div class="commentitemRt" data-uid="' + bean.uid + '" data-topuid="' + params.topuid + '" data-touseruid="' + bean.fromuseruid + '"  data-tousername="' + bean.fromusername + '" data-useruid="' + me.uid + '" >';
			html += '<div class="qsUsername">';
			if (bean.fromVisitorName == '' || bean.fromVisitorName == null) {
				html += '&nbsp;' + bean.fromusername + '&nbsp;';
			} else {
				html += '&nbsp;' + bean.fromVisitorName + '&nbsp;';
			}
			html += '<a id="deletecomment_' + bean.uid + '" class="delete"><img src="' + baseimg + '/imgs/default/crush.png"/></a>';
			html += '</div>';
			html += '<div class="qsword">';
			html += bean.content+'</div>';
			html += '<div class="wordBottom">';
			html += '<span class="qstime">&nbsp;刚刚</span>';
			html += '<div class="qsCommentOperate">';
			html += '<a class="zan" id="btnprizeproblem_answerpraise_';
			if (me != "" || me != null || typeof me != 'undefined') {
				html += me.oid;
			} else {
				html += '0';
			}
			html += '_' +cid+'_'+ bean.uid + '" data-counter=".prizecounter">';
			html += '<img src="' + baseimg + '/imgs/default/zan.png" data-isprize="' + false + '"><span class="zancountjs">0</span></a>';
			html += '<a data-reply="replyopen" href="javascript:;" class="reply" onclick="reply(this)">回复';
			html += '</a>';
			html += '</div>';
			html += '</div>';
			html += '</div>';
			html += '</li>';
		}
		$("#secondmajor").prepend(html);
		initBindEvent();
		initPage();
	}else{
		alert("评论失败");
	}
}

function addComment(bean, obj){
	var parentobj = $(obj).parent().parent().parent().parent();
	var html = '';
	if (bean.fromVisitorName != '' && bean.fromVisitorName != null) {
		bean.fromusername= bean.fromVisitorName;
	}
	if($(obj).attr("class").indexOf("firstedcomments") != -1){
		// 对文章的回复
		html += '<div class="commentitem">';
		html += '<div class="userimg">';
		html += '<a href="javascript:;">';
		if (bean.fromVisitorAvatar != "" && bean.fromVisitorAvatar != null) {
			html += '<img src="' + userimg + bean.fromVisitorAvatar + '"/>';
		} else {
			if (me.useravatar.trim() != "") {
				html += '<img src="'  + me.useravatar + '"';
			} else{
				html += '<img src="' + me.defaultImg  + '"';
			}
			if (me.isuser) {
				html += 'class="userborder" /><img  src="' + baseimg + '/imgs/default/prince.png" class="princeimg" alt="" /></a>';
			} else {
				html += '></a>';
			}
		}
		html += '</div>';
		html += '<div class="commentRt replay_comment ment"  data-uid="' + bean.uid + '" data-touseruid="' + bean.fromuseruid + '"  data-tousername="' + bean.fromusername + '" data-useruid="' + me.uid + '">';
		html += '<div class="name">';
		html += '<a href="javascript:;">';
		html += '&nbsp;' + bean.fromusername + '&nbsp;';
		html += '</a>';
		html += '<span class="commentTime">&nbsp;刚刚</span>';
		html += '<a id="deletecomment_' + bean.uid + '" class="delete"><img src="' + baseimg + '/imgs/default/crush.png"/></a>';
		html += '</div>';
		html += '<p class="comment">' + bean.content + '</p>';
		html += '<div class="commentBottom">';
		html += '<div class="operate">';
		html += '<a data-reply="replyopen" href="javascript:;" class="reply" onclick="reply(this)">回复';
		html += '</a>';
		html += '<a data-reply="true" class="leftcomments hd" onclick="showcomment(this);">&nbsp;|&nbsp;<span class="commentcount">0</span>条回复<img src="' + baseimg + '/imgs/default/arrawDown.png" alt="展开" />';
		html += '</a>';
		html += '<a class="zan" id="btnprize_commentspraise_';
		if (me != "" || me != null || typeof me != 'undefined') {
			html += me.oid;
		} else {
			html += '0';
		}
		html += '_' +cid+'_'+ bean.uid + '" data-counter=".prizecounter">';
		html += '<img src="' + baseimg + '/imgs/default/zan.png" data-isprize="' + false + '"><span class="zancountjs">0</span></a>';
		html += '</div>';
		html += '<ul class="secondComment hd" >';
		html += '</ul>';
		html += '<div class="loadmorefinance hd">';
		html += '<a href="javascript:void(0)" id="loadmore" onclick="load(this)">加载更多</a>';
		html += '</div>';
		html += '</div>';
		html += '</div>';
		html += '</div>';
		$("#displayComment").prepend(html);
	} else {
		html += '<li class="ment" data-uid="' + bean.uid + '" data-topuid="' + params.topuid + '" data-touseruid="' + bean.fromuseruid + '"  data-tousername="' + bean.fromusername + '"  data-useruid="' + me.uid + '">';
		html += '<div class="userimg">';
		html += '<a href="javascript:;">';
		if (bean.fromVisitorAvatar != "" && bean.fromVisitorAvatar != null) {
			html += '<img src="' + userimg + bean.fromVisitorAvatar + '"/>';
		} else {
			if (me.useravatar.trim() != "") {
				html += '<img src="'  + me.useravatar + '"';
			} else{
				html += '<img src="' + me.defaultImg  + '"';
			}
			if (me.isuser) {
				html += 'class="userborder" /><img  src="' + baseimg + '/imgs/default/prince.png" class="princeimg" alt="" /></a>';
			} else {
				html += '></a>';
			}
		}
		html += '</div>';
		if(typeof params.topuid == "undefined"){
			params.topuid = params.touid;
		}
		html += '<div class="commentRt">';
		html += '<div class="name">';
		html += '<a href="javascript:;">';
		html += bean.fromusername;
		html += '</a>';
		html += '<span class="commentTime">&nbsp;刚刚</span>';
		html += '<a id="deletecomment_' + bean.uid + '" class="delete"><img src="' + baseimg + '/imgs/default/crush.png"/></a>';
		html += '</div>';
		html += '<p class="comment">';
		html += '<span>@'+PrivateString(bean.tousername)+':</span>'+bean.content+'</p>';
		html += '<div class="operate">';
		html += '<a data-reply="replyopen" href="javascript:;" class="reply" onclick="reply(this)">回复</a>';
		html += '<a class="zan" id="btnprize_commentspraise_';
		if (me != "" || me != null || typeof me != 'undefined') {
			html += me.oid;
		} else {
			html += '0';
		}
		html += '_' +cid+'_'+ bean.uid + '" data-counter=".prizecounter">';
		html += '<img src="' + baseimg + '/imgs/default/zan.png" data-isprize="' + false + '"><span class="zancountjs">0</span></a>';
		html += '</div>';
		html += '</div>';
		html += '</li>'
		if($(parentobj).attr("class").indexOf("replay_comment") != -1){
			// 对评论的回复
			var secondObj=$(parentobj).children().find(".leftcomments");//定位到一级评论展开回复
			showcomment(secondObj);
			if($(secondObj).hasClass("hd")){
				$(secondObj).removeClass("hd");
			}
			if($(secondObj).parent().siblings(".secondComment").hasClass("hd")){
				showcomment(secondObj);
			}
			//动态改变一级评论的二级评论数
			var temp = $(secondObj).children(".commentcount").html();
			$(secondObj).children(".commentcount").html(parseInt(temp)+1);
		}else{
			// 对回复的回复
			$(parentobj).after(html);
			//更改一级评论的二级评论数
			var temp = $(parentobj).parent().siblings(".operate").children(".leftcomments").children(".commentcount").html();
			$(parentobj).parent().siblings(".operate").children(".leftcomments").children(".commentcount").html(parseInt(temp)+1);
		}
		initBindEvent();
		reply($(obj).parent().parent().siblings(".operate").children(".reply"));
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
		html += '<textarea class="comment_textarea" id="' + idnumber + '" data-suffix="' + idnumber + '"/>';
		html += '<div class="wordpiece">';
		html += '<a class="face"><img src="'+baseimg+'/imgs/default/smile.png" alt=""></a>';
		html += '<button type="submit" onclick="submitCommentHandler(this);" class="submitBtn">发表</button>';
		html += '</div>';
		html += '</div>';
		$(obj).parent().after(html);
		$(".replyBox").css("display","block");
		$(".comment_textarea1").focus();
		initBindEvent();
		$(obj).data("reply","replyclose");
		return ;
	}else{
		$(obj).data("reply","replyopen");
		$(".replyBox").remove();
	}
}
var num=1;
var temp=0;
var diplayID="#displayComment";
function loadlast(obj){
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
			prizeBindEvent();
		}
	});
}
function loadmore(){
    if( num == 1){
        var url = base + "/ajax/lifecomment/"+channel+"_" + cid+"_"+fromuid+"_{0}".format(num)+"?commentuid="+commentuid;
    }else{
        var url = base + "/ajax/lifecomment/"+channel+"_" + cid+"_"+fromuid+"_{0}".format(num)+"?commentuid=com";
    }
	if(temp<num){
		temp=num;
		$.ajax({
			type:"GET",
			url:url,
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
	var second = $(obj).parent().siblings(".secondComment");//一级评论的二级评论部分
	if(display == "true"){
		$(second).addClass("hd");
		$(second).empty();
		$(obj).data("reply","false");
		$(obj).children("img").attr('src', baseimg +'/imgs/default/arrawDown.png'); 
		return;
	} else{
		$(obj).data("reply","true");
		$(second).removeClass("hd");
		firstuid = $(obj).parent().parent().parent().data("uid");//一级评论uid， 尝试parents().find();
		pagenum=1;
		getcomment(obj);
		$("#loadmore").bind("click", function(){
			getcomment(obj);
		});
		$(obj).children("img").attr('src', baseimg +'/imgs/default/arrawDownup.png'); 
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
				var second = $(obj).parent().siblings(".secondComment");
				if($(second).children(".ment").length>19){
					$(".loadmorefinance").removeClass("hd");
				}
				$(second).append(data);
				initPage();
				$(second).css("display","block");
				pagenum+=1;
				prizeBindEvent();
			}
		}
	});
}

function deleteComment(obj){
	var topuid = $(obj).parent().data("topuid");
	var parent = obj;
	if(!$(obj).hasClass("ment")){
		parent = $(obj).parent();
	}
	var secondcommentcount;
	var articlecommentcount = $("#articlecommentcount").html();
	var temp=$(obj).parent().parent().siblings(".operate").children(".leftcomments");
	if($(obj).hasClass("replay_comment")){
		secondcommentcount = $(obj).children(".commentBottom").children().find(".commentcount").html();
	}else{
		secondcommentcount = $(parent).parent().siblings(".operate").children(".leftcomments").children(".commentcount").html();
	}
	var commentuid = $(parent).data("uid");
	var fromuseruid = $(parent).data("touseruid");
	var useruid = $(parent).data("useruid");
	var url = base + "/ajax/lifecomment/delete/"+commentuid+"/"+fromuseruid+"/"+useruid;
	$.ajax({
		type:"GET",
		url:url,
		success:function(data){
			if(data){
				if(topuid != null && topuid != '' && topuid != 'undefined'){
					$(temp).children(".commentcount").html(parseInt(secondcommentcount) - 1);
					var tempcount = $(temp).children(".commentcount").html();
					$("#articlecommentcount").html(parseInt(articlecommentcount) - 1);
					if(tempcount == 0){
						$(temp).addClass("hd");
						$(parent).parent().addClass("hd");
					}
					$(parent).addClass("hd");
				}else{
					$("#articlecommentcount").html(parseInt(articlecommentcount) - parseInt(secondcommentcount) -1);
					$(parent).parent().addClass("hd");
				}
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
	$(".comment").each(function(){
		var content = replaceContent($(this).html());
		$(this).html(content);
	});
}

/* 返回要赋值的输入框 */
function initData(obj) {
	var assign=$(obj).parent().parent().parent().find("textarea").data("suffix");
	return assign;        
}

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