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
	$(".problemmask").hide();
	$(".problemmaskContent").hide();
	$.post(base + "/m/ajax/answer/{0}".format(params.fromuid), params,function(data){
		afterSubmitHandler(data, obj);
	});

}

var params = {
	content: "",
	fromuid: "",
	cid:"",
	isnick:""
}

function beforeSubmitHandler(obj){
	params = {};
	params.fromuid = fromuid;
	params.cid = cid;
	var objs = $(obj).siblings("#content");
	params.content = objs.val();
	var tempobj = $(obj).parent().parent();
	if(!tempobj.hasClass("contentLeft") || !tempobj.hasClass("commentitemRt")){
		tempobj=$(obj).parent().siblings(".commentitemRt");
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
		if(result.bean.ismajor != 1) {
			$(obj).siblings("#content").val("");
			$(".nothing").addClass("hd");
			addComment(result.bean, obj);
			$("div").remove("#replyBoxed");
		} else {
			//对专家点评的回复
			$(obj).parent(".replyBox").remove();
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
			html += '</div>';
			html += '</div>';
			html += '</div>';
			html += '</li>';
		}
		$(".leftcommentss").removeClass("hd");
		console.log($(".reply").data("reply"))
		$(".reply").data("reply","replyopen");
		$("#secondmajor").prepend(html);
		initBindEvent();
		initPage();
	}else{
		alert("评论失败");
		$(".problemmask").show();
		$(".problemmaskContent").show();
	}
}

function addComment(bean, obj){
	var parentobj = $(obj).parent().parent().parent();
	var html = '';
	if (bean.fromVisitorName != '' && bean.fromVisitorName != null) {
		bean.fromusername= bean.fromVisitorName;
	}
	if($(obj).parent().attr("class").indexOf("problemmaskContent") != -1){
		// 对文章的回复
		html += '<li class="commentitem">';
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
		html += '<div class="commentitemRt replay_comment"  data-uid="' + bean.uid + '" data-touseruid="' + bean.fromuseruid + '"  data-tousername="' + bean.fromusername + '" data-useruid="' + me.uid + '">';
		html += '<div class="qsUsername">';
		html += '&nbsp;' + bean.fromusername + '&nbsp;';
		html += '<a id="deletecomment_' + bean.uid + '" class="delete"><img src="' + baseimg + '/imgs/default/crush.png"/></a>';
		html += '</div>';
		html += '<div class="qsword">' + bean.content + '</div>';
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
		html += '<a data-reply="replyopen" href="javascript:;" class="reply" onclick="reply(this)"><img src="' + baseimg + '/imgs/default/discuss.png"><span id="commentcount">0</span></a>';
		html += '</a>';
		html += '<a data-reply="true" class="leftcomments hd" onclick="showcomment(this);">展开';
		html += '</a>';
		html += '</div>';
		html += '</div>';
		html += '</div>';
		html += '<div id="second" class="qsReplyList hd" >';
		html += '</div>';
		html += '<div class="loadmorefinance hd">';
		html += '<a href="javascript:void(0)" id="loadmore" onclick="load(this)">加载更多</a>';
		html += '</div>';
		html += '</li>';
		$("#displayComment").prepend(html);
	} else {
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
		html += '&nbsp;' + bean.fromusername + '&nbsp;';
		html += '<a id="deletecomment_' + bean.uid + '" class="delete"><img src="' + baseimg + '/imgs/default/crush.png"/></a>';
		html += '</div>';
		html += '<div class="qsword">';
		html += '<span>@'+PrivateString(bean.tousername)+':</span>'+bean.content+'</div>';
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
		html += '<a class="leftcomments hd" onclick="showcomment(this);">&nbsp;|&nbsp;<span id="commentcount">0</span>条回复<img src="' + baseimg + '/imgs/default/arrawDown.png" alt="展开" />';
		html += '</a>';
		html += '</div>';
		html += '</div>';
		html += '</div>';
		html += '</li>';
		if($(obj).parent().siblings(".commentitemRt").attr("class").indexOf("replay_comment") != -1){
			// 对评论的回复
			var secondObj=$(obj).parent().siblings(".commentitemRt").children(".wordBottom").children(".qsCommentOperate").children(".leftcomments");
			showcomment(secondObj);
			if($(secondObj).parent().parent().parent().siblings("#second").hasClass("hd")){
				showcomment(secondObj);
			}
			// $(obj).parent().siblings("#second").prepend(html);
			if($(obj).parent().siblings(".commentitemRt").children(".wordBottom").children(".qsCommentOperate").children(".leftcomments").hasClass("hd")){
				$(obj).parent().siblings(".commentitemRt").children(".wordBottom").children(".qsCommentOperate").children(".leftcomments").removeClass("hd");
			}
			var temp = $(obj).parent().siblings(".commentitemRt").children(".wordBottom").children(".qsCommentOperate").children(".reply").children("#commentcount").html();
			$(obj).parent().siblings(".commentitemRt").children(".wordBottom").children(".qsCommentOperate").children(".reply").children("#commentcount").html(parseInt(temp)+1);
		}else{
			// 对回复的回复
			$(obj).parent().parent().after(html);
			var temp = $(obj).parent().parent().parent().siblings(".commentitemRt").children(".wordBottom").children(".qsCommentOperate").children(".reply").children("#commentcount").html();
			$(obj).parent().parent().parent().siblings(".commentitemRt").children(".wordBottom").children(".qsCommentOperate").children(".reply").children("#commentcount").html(parseInt(temp)+1);
		}
		$(obj).parent().siblings("#second").css("display","block");
		initBindEvent();
		reply($(obj).parent().siblings(".commentitemRt ").children().find(".reply"));
	}
	var count = $("#problemcommentcount").html();
	$("#problemcommentcount").html(parseInt(count)+1);
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
		html += '<textarea class="comment_textarea1" id="content"/>';
		html += '<a class="qsface"><img src="' + baseimg + '/imgs/default/smile.png" alt=""></a>';
		html += '<button class="submitBtn" onclick="submitCommentHandler(this)">提交</button>';
		html += '</div>';
		$(obj).parent().parent().parent().after(html);
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
var diplayID="#displayComment";
function loadmore(){
	var url= "";
	if (type == 0) {
		url = base + "/m/ajax/answer/"+cid+"_"+fromuid+"_{0}".format(num);
	} else {
		url = base + "/m/ajax/lifeanswer/hotanswer";
	}
	$.ajax({
		type:"GET",
		url:url,
		data: {fromuid:fromuid, cid:cid, pagenum:num},
		success:function(data){
			if(data.trim() == ""){
				if (num == 1) {
					if(!majorreply){
						$(".nothing").removeClass("hd");
					}
				} 
				$(window).unbind("scroll", defaultScrollHandler);
			}else{
				num += 1;
				$(".nothing").addClass("hd");
				$(diplayID).append(data);
				initPage();
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

var firstuid=0;
var pagenum;
function showcomment(obj){
	var display=$(obj).data("reply");
	if(display == "true"){
		$(obj).parent().parent().parent().siblings("#second").addClass("hd");
		$(obj).parent().parent().parent().siblings("#second").css("display","none");
		$(obj).parent().parent().parent().siblings("#second").empty();
		$(obj).data("reply","false");
		$(obj).text('展开');
		$(obj).append(' <img src="' + baseimg + '/imgs/default/arrawDown.png" alt="展开" />');
		return;
	} else{
		$(obj).data("reply","true");
		$(obj).parent().parent().parent().siblings("#second").removeClass("hd");
		firstuid = $(obj).parent().parent().parent().data("uid");
		pagenum=1;
		getcomment(obj);
		$("#loadmore").bind("click", function(){
			getcomment(obj);
		});
		$(obj).text('收起');
		$(obj).append(' <img src="' + baseimg + '/imgs/default/arrawDownup.png" alt="收起" />');
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
	var moreobj = $(obj).parent().siblings(".commentitemRt").children(".wordBottom").children(".qsCommentOperate").children(".leftcomments");
	getcomment(moreobj);
}
function getcomment(obj){
	var url = base + "/m/ajax/secondanswer/"+firstuid+"_"+cid+"_{0}".format(pagenum);
	$.ajax({
		type:"GET",
		url:url,
		success:function(data){
			if(data.trim() == ""){
				$(".loadmorefinance").addClass("hd");
			}else{
				if($(obj).parent().parent().parent().siblings("#second").children(".ment").length>19){
					$(".loadmorefinance").removeClass("hd");
				}
				$(obj).parent().parent().parent().siblings("#second").append(data);
				$(obj).parent().parent().parent().siblings("#second").css("display","block");
				pagenum+=1;
				initPage();
				prizeBindEvent();
			}
		}
	});
}

function deleteComment(obj){
	var topuid = $(obj).data("topuid");
	var problemcommentcount = $("#problemcommentcount").html();
	var secondcommentcount = $(obj).children(".wordBottom").children(".qsCommentOperate").children(".reply").children("#commentcount").html();
	var commentuid = $(obj).data("uid");
	var fromuseruid = $(obj).data("touseruid");
	var useruid = $(obj).data("useruid");
	var url = base + "/m/ajax/answer/delete/"+commentuid+"/"+fromuseruid+"/lifeproblem/"+useruid;
	$.ajax({
		type:"GET",
		url:url,
		success:function(data){
			if(data){
				if(topuid != null && topuid != '' && topuid != 'undefined'){
					var tempcount = $(obj).parent().parent().siblings(".replay_comment").children(".wordBottom").children(".qsCommentOperate").children(".reply").children("#commentcount").html();
					$(obj).parent().parent().siblings(".replay_comment").children(".wordBottom").children(".qsCommentOperate").children(".reply").children("#commentcount").html(parseInt(tempcount) - 1);
					$("#problemcommentcount").html(parseInt(problemcommentcount) - 1);
					if(parseInt(tempcount) - 1 == 0){
						$(obj).parent().parent().siblings(".replay_comment").children(".wordBottom").children(".qsCommentOperate").children(".leftcomments").addClass("hd");
					}
				}else{
					$("#problemcommentcount").html(parseInt(problemcommentcount) - parseInt(secondcommentcount) -1);
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
	$('.qsface').unbind( "click" );
	$('.qsface').qqFace({ 
        assign:'content', 
        path: g.baseimg + '/imgs/face/qq/'
    });
}

/* 生成页面表情 */
function initPage() {
	$(".qsword").each(function(){
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

