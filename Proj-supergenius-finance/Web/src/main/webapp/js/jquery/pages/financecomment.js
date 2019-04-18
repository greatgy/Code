function submitCommentHandler(obj){
	beforeSubmitHandler(obj);
	if(params.content.trim() == ""){
		alert("评论内容不能为空");
		return;
	}
	if(params.content.length > commentdefault.maxlength){
		alert("评论内容最多" + commentdefault.maxlength +　"个字");
		return;
	}
	$.post(g.base + "/ajax/comment/{0}_{1}".format(params.channel, params.fromuid), params,function(data){
		afterSubmitHandler(data, obj);
	});
}

var params = {
	content: "",
	fromuid: "",
	channel: ""
}

var commentdefault = {
	maxlength: 300
}

function beforeSubmitHandler(obj){
	params = {};
	params.fromuid = fromuid;
	params.channel = "finance";
	var objs = $(obj).parent().siblings(".comment_textarea");
	if (typeof objs[0] == "undefined") {
		objs = $(obj).siblings(".comment_textarea");
	}
	params.content = objs.val();
	var touid = $(obj).parent().parent().data("uid");
	if(typeof touid != "undefined"){
		params.touid = touid;
		params.touseruid = $(obj).parent().parent().data("touseruid");
		params.tousername = $(obj).parent().parent().data("tousername");
	}
	var topuid = $(obj).parent().parent().data("topuid");
	if(typeof topuid != "undefined"){
		params.topuid = topuid;
	}
	var fromusername = $("input[name='fromusername']").last().val();
	if(fromusername != ""){
		params.fromusername = fromusername;
	}
	
}

function afterSubmitHandler(result, obj){
	if(result.success){
		$(".comment_textarea1, .comment_textarea, [name='fromusername']").val("")
		addComment(result.bean, obj);
		$("#list_one_1 .nothing").addClass("hd");
		$(".db_btn2").css("background", "#b5b5b5");
		$(".replybox1").remove();
		unRegisterPraise();
		registerPraise();
	}else{
		alert("评论失败");
	}
}

function addComment(bean, obj){
	var parentobj = $(obj).parent().parent().parent();
	bean.content = replaceContent(bean.content);
	var html = '';
	var url = g.base + "/my/article";
	if($(parentobj).attr("class").indexOf("iscomment") != -1){
		// 对文章的回复
		html += '<div class="commentitem">';
		if (me.oid != 0 && me != "" && me != null && typeof me != 'undefined') {
			html += '<a href="' + url + '" target="_blank">';
		} else {
			html += '<a href="javascript:void(0);" style="font-size: 14px; color: #1d71b6; font-weight: bold;">';
		}
		html += '<img class="personimg" ';
		if (me.imglittle.trim() != "") {
			html += 'src="' + g.userimg + me.imglittle + '" />';
		} else{
			html += 'src="' + me.defaultImg + '">';
		}
		html += '</a>';
		html += '<div class="itemright replay_comment" data-uid="' + bean.uid + '" data-touseruid="' + bean.fromuseruid + '"  data-tousername="' + bean.fromusername + '" >';
		if (me.oid != 0 && me != "" && me != null && typeof me != 'undefined') {
			html += '<a href="' + url + '" target="_blank" class="name">';
		} else {
			html += '<a href="javascript:void(0);" style="font-size: 14px; color: #1d71b6; font-weight: bold;">';
		}
		html += '&nbsp;' + bean.fromusername + '&nbsp;';
		html += '</a>';
		html += ' <p>' + '&nbsp;' + bean.content + '</p>';
		html += '<div class="itembottom">';
		html += '<span class="time">&nbsp;刚刚</span>';
		html += '<div class="comlinkbox">';
		html += '<a href="javascript:;" onclick="reply(this)">';
		html += '<img src="' + g.baseimg + '/imgs/default/msgimg.png" />';
		html += '评论(' + 0 + ')';
		html += '</a>';
		html += '<a href="javascript:void(0)" id="btnprizecomments_';
		if (me != "" || me != null || typeof me != 'undefined') {
			html += me.oid;
		} else {
			html += '0';
		}
		html += '_' + bean.uid + '" data-counter=".prizecounter">';
		html += '<img src="' + g.baseimg + '/imgs/default/zanicon.png" data-isprize="' + false + '"/>';
		html += '<span style="margin-right:0px" class="zancountjs">赞('+ bean.prizecount +')';
		html += '</a>';
		html += '</div>';
		html += '</div>';
		html += '<div>';
		html += '</div>';
		html += '</div>'
		$("#list_one_1").prepend(html);
	} else {
		html += '<div class="seconditem">';
		if (me.oid != 0 && me != "" && me != null && typeof me != 'undefined') {
			html += '<a href="' + url + '" target="_blank">';
		} else {
			html += '<a href="javascript:void(0);">';
		}
		html += '<img class="personimg" ';
		if (me.imglittle.trim() != "") {
			html += 'src="' + g.userimg + me.imglittle + '" />';
		} else{
			html += 'src="' + me.defaultImg + '">';
		}
		html += '</a>';
		if(typeof params.topuid == "undefined"){
			params.topuid = params.touid;
		}
		html += '<div class="secondright" data-uid="' + params.topuid + '" data-touseruid="' + bean.fromuseruid + '"  data-tousername="' + bean.fromusername + '" >';
		if (me.oid != 0 && me != "" && me != null && typeof me != 'undefined') {
			html += '<a href="' + url + '" target="_blank" class="name">';
		} else {
			html += '<a href="javascript:void(0);" style="font-size: 14px; color: #1d71b6; font-weight: bold;">';
		}
		html += "&nbsp;" + bean.fromusername;
		html += '</a>&nbsp;回复&nbsp;';
		if (typeof bean.touseroid != "undefined" && bean.touseroid != 0) {
			var url1 = "javascript:void(0)";
			if (bean.touseroid == me.oid) {
				url1 = g.base + "/my/article";
			} else {
				url1 = g.base + "/his/article/" + bean.touseroid;
			}
			html += '<a href="' + url1 + '" class="name" target="_blank">';
		} else {
			html += '<a href="javascript:void(0);" style="font-size: 14px; color: #1d71b6; font-weight: bold;" >';
		}
		html += bean.tousername + '</a>';
		html += ' <p>' + '&nbsp;' + bean.content + '</p>';
		html += '<div class="itembottom">';
		html += '<span class="time">&nbsp;刚刚</span>';
		html += '<div class="comlinkbox">';
		html += '<a href="javascript:;" onclick="reply(this)">';
		html += '<img src="' + g.baseimg + '/imgs/default/retret.png"/>';
		html += '回复(' + 0 + ')';
		html += '</a>';
		html += '<a href="javascript:void(0)" id="btnprizecomments_';
		if (me != "" || me != null || typeof me != 'undefined') {
			html += me.oid;
		} else {
			html += '0';
		}
		html += '_' + bean.uid + '" data-counter=".prizecounter">';
		html += '<img src="' + g.baseimg + '/imgs/default/zanicon.png" data-isprize="' + false + '"/>';
		html += '<span style="margin-right:0px" class="zancountjs">赞('+ bean.prizecount +')';
		html += '</a>';
		html += '</div>';
		html += '</div>';
		html += '</div>'
		parentobj = $(obj).parent().parent();
		if($(parentobj).attr("class").indexOf("replay_comment") != -1){
			// 对评论的回复
			$(obj).parent().siblings("div").last().prepend(html);
		}else{
			// 对回复的回复
			$(obj).parent().parent().parent().after(html);
		}
		initBindEvent();
	}
}

/**
 * 点击评论或者回复,加载评论框
 * @param obj
 * @returns
 */
function reply(obj){
	var idnumber = getIdNumber();
	if($(".replybox1").length != 0) {
		if(!confirm("您确定要放弃现在的评论吗？")){
			$(".comment_textarea1")[0].focus();
			return;
		}else{
			$(".replybox1").remove();
		}
	}
	var html = "";
	html += '<div class="inputbox replybox1">';
	html += '<input class="comment_textarea comment_textarea1" id="' + idnumber + '" data-suffix="' + idnumber + '" type="text" />';
	html += '<a href="javascript:void(0);" class="inputbtn" onclick="submitCommentHandler(this)">提交</a>';
	html += '<p style="text-align: left; margin-top: 4px;"><span class="emotion">表情</span></p>';
	html += '</div>';
	$(obj).parent().parent().after(html);
	$(".comment_textarea1").focus();
	initBindEvent();
}

var pagenum = 1;
var id="#list_one_1";
function loadmore(){
	pagenum += 1;
	var url = g.base + "/ajax/comment/finance_"+fromuid+"_{0}".format(pagenum - 1);
	$.ajax({
		type:"GET",
		url:url,
		beforeSend:function(XMLHttpRequest) {
			$("#loading").show();
		},
		success:function(data){
			$("#loading").hide();
			if(data.trim() == ""){
				pagenum -= 1 ;
				$(window).unbind("scroll", defaultScrollHandler);
				if (pagenum == 1) {
					$(".nothing").removeClass("hd");
				} else {
					if ($("#list_one_1 .commentitem").length > 20) {
						$(".cursel1").html("已经是全部的评论了！").show();
					}
				}
			}else{
				$(id).append(data);
				initPage();//表情解析
				if($("#list_one_1 .commentitem").length < 20 && pagenum == 2){
					$(window).unbind("scroll", defaultScrollHandler);
				}
				unRegisterPraise();
				registerPraise();
			}
		}
	});
}

/*重写点赞的事件绑定*/
function registerPraise(){
	$(".comlinkbox a[id^='btnprize']").each(function(){$(this).prize()});
	prizeBindEvent();
}

$(function($) {
	$(".loadmorefinance").bind("click", function(){
		loadmore();
	});
	$.fn.scrollHandle({
		obj: window,
		fun: loadmore
	})
	$("#collectli").bind("click", function(){
		collect(this);
	})
	$(".loadmorefinance").click();
	$(".loadmorefinance").unbind("click");
	prizeBindEvent();
	collectBindEvet();
	if ($(".pagenum ul").html() ==  "" || $(".pagenum ul li").html() == null) {
		$(".pagenum").hide();
	}
	setSubscribeStyle();
});

function setTab(name,cursel,n){
	if(cursel == 1){
		$(window).unbind("scroll", defaultScrollHandler);
		$.fn.scrollHandle({
			obj: window,
			fun: loadmore
		})
		$(".cursel2").hide();
		$(".cursel1").show();
	}else{
		$(window).unbind("scroll", defaultScrollHandler);
		$(".cursel1").hide();
		$(".cursel2").show();
	}
	for(i=1;i<=n;i++){	  
		var menu=document.getElementById(name+i);
		var con=document.getElementById("list_"+name+"_"+i);
		menu.className=i==cursel?"current":"";
		con.style.display=i==cursel?"block":"none";
	}
}

function showmore(obj){
	$(obj).siblings(".com_listInner").removeClass("hd");
	$(obj).addClass("hd");
}

$(function () {
    $(".comment_textarea").live("keyup", function(){
        if($(".comment_textarea").last().val() != ""){
            $(".db_btn2").last().css("background","#eb6100")
        }else{
            $(".db_btn2").last().css("background","#b5b5b5")
        }
    });
})

/**
 * 重写解除事件绑定
 */
function unRegisterPraise(){
	$(".comlinkbox a[id^='btnprize']").each(function(){
		$(this).unbind("click");
	});
}

/**
 * 重写更新赞的数量
 * @param obj
 * @param isprize
 */
function updatePrizeCount(obj, isprize){
	var countstr = $(obj).find(".zancountjs").html();
	if ($(obj).parent().hasClass("comlinkbox")) {
		countstr = countstr.substring(2, countstr.length - 1);
		if (isprize) {
			$(obj).find(".zancountjs").html("赞(" + (parseInt(countstr)+1) + ")");
		} else {
			$(obj).find(".zancountjs").html("赞(" + (parseInt(countstr)-1) + ")");
		}
		
	} else {
		if (isprize) {
			$(obj).find(".zancountjs").html(parseInt(countstr)+1);
		} else {
			$(obj).find(".zancountjs").html(parseInt(countstr)-1);
		}
	}
}

/**
 * 获得输入框焦点
 * @returns
 */
function wantComment() {
	$(".contro_text").focus();
}

/**
 * 订阅
 * @param id
 * @returns
 */
function subscribeHandle(obj, id) {
	var url = g.base + "/my/subscribe/" + id;
	$.get(getNoCachePath(url), function(data){
		if(isAjaxNeedLogin(data)){
			defaultInitLogin();
		}else{
			if (data) {
				$(obj).parent().find("img").attr("src", g.baseimg + "/imgs/default/cancelsubs.png");
				$(obj).parent().find(".addsub").html("已订阅").css("color","#f00");
			} else {
				$(obj).parent().find("img").attr("src", g.baseimg + "/imgs/default/addsubs.png");
				$(obj).parent().find(".addsub").html("订阅").css("color","#2979ba");
			}
		}
	})
}

function setSubscribeStyle(){
	var isubscribe = $("#subscribe").data("isubscribe");
	if (isubscribe) {
		$("#subscribe").find("img").attr("src", g.baseimg + "/imgs/default/cancelsubs.png");
		$("#subscribe").find(".addsub").html("已订阅").css("color","#f00");
	}
}

/***************************************************************************************************
 * 表情
 */
//将[em_*]替换为img标签
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
	$('.emotion').unbind( "click" );
	$('.emotion').qqFace({ 
        assign:'content', 
        path: g.baseimg + '/imgs/face/qq/'
    });
}

/*生成页面表情*/
function initPage() {
	$(".comments").each(function(){
		var content = replaceContent($(this).html());
		$(this).html(content);
	});
}

/*返回要赋值的输入框*/
function initData(obj) {
	var assign=$(obj).parent().parent().find("input").data("suffix");
	return assign;        
}
