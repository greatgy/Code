var params = {
    content: "",
    fromuid: "",
    channel: ""
}
var commentdefault = {
    maxlength: 300
}
function beforeSubmitHandler(obj){
	params.fromuid = fromuid;
    params.channel = "topic";
    params.content = $(obj).parent().siblings(".contro_text").val();
    if(typeof touid != "undefined"){
        params.touseruid = $(obj).data("touseruid");
    }
    var fromusername = $("input[name='fromusername']").val();
    if(fromusername != "" && typeof fromusername != "undefined"){
        params.fromusername = fromusername;
    }
}
function afterSubmitHandler(result, obj){
    if(result.success){
        $(".contro_text").val("");
        addComment(result.bean, obj);
        $(".reply_title").html("");
        registerPraise();
    }else{
        alert("添加失败");
    }
}
function submitCommentHandler(obj){
    beforeSubmitHandler(obj);
    if(params.content == ""){
        alert("评论内容不能为空");
        return;
    }
    if(params.content.length >= commentdefault.maxlength){
        alert("评论内容最多" + commentdefault.maxlength +　"个字");
        return;
    }
    $.post(g.base+"/ajax/comment/{0}_{1}".format(params.channel, params.fromuid), params,function(data){
        afterSubmitHandler(data, obj);
    });
}
function startreply(obj) {
	if(typeof $(".inputbox").attr("class") == "undefined") {
		var html = '<div class="inputbox"><input type="text" id="reply" data-suffix="reply" /><a href="javascript:void(0);" class="inputbtn" onclick="reply(this)" data-comuid="' + $(obj).data("uid") + '" data-touseruid="' + $(obj).data("touseruid") + '">提交</a><p style="text-align: left; margin-top: 4px;"><span class="emotion">表情</span></p></div>';
		$(obj).parent().parent().after(html);
		initBindEvent();
	} else {
		if(confirm("您确定要放弃现在的评论吗？")) {
			if($(obj).parent().parent().siblings(".inputbox").length > 0){
				$(".inputbox").remove();
			} else {
				$(".inputbox").remove();
				var html = '<div class="inputbox"><input type="text" id="reply" data-suffix="reply" /><a href="javascript:void(0);" class="inputbtn" onclick="reply(this)" data-comuid="' + $(obj).data("uid") + '" data-touseruid="' + $(obj).data("touseruid") + '">提交</a><p style="text-align: left; margin-top: 4px;"><span class="emotion">表情</span></p></div>';
				$(obj).parent().parent().after(html);
				initBindEvent();
			}
        } else {
        	return; 
        }
	}
}
function beforereply(obj) {
	params.content = $("#reply").val();
	params.touid=$(obj).data("comuid");
	params.touseruid=$(obj).data("touseruid");
	params.fromuid = fromuid;
	params.channel = "topic";
}
function reply(obj) {
	beforereply(obj);
	if(params.content == ""){
        alert("评论内容不能为空");
        return;
    }
	if(params.content.length >= commentdefault.maxlength){
        alert("评论内容最多" + commentdefault.maxlength +　"个字");
        return;
    }
	$.post(g.base+"/ajax/comment/{0}_{1}".format(params.channel, params.fromuid), params, function(data){
        afterreply(data, obj);
    });
}
function afterreply(result, obj) {
	if(result.success){
		addReply(result.bean,obj);
		$(".inputbox").remove();
    }else{
        alert("添加失败");
    }
}
function addComment(result, obj) {
	result.content = replaceContent(result.content);
    var html = "";
    html += '<div class="commentitem"><a><img ';
    if (me.oid != 0 && me != "" && me != null && typeof me != 'undefined') {
    	if (me.imglittle.trim() != "") {
    		html += 'src="' + g.userimg + me.imglittle + '" class="personimg" /></a><div class="itemright">';
    	} else {
		    html += 'src="' + me.defaultImg + '" class="personimg" /></a><div class="itemright">';
	    }
    } else {
    	html += 'src="' + me.defaultImg + '" class="personimg" /></a><div class="itemright">';
    }
    if (result.fromuseroid != 0) {
    	html += '<a class="name">' + result.fromusername + '</a>';
    } else {
    	html += '<a style="font-size: 14px; color: #1d71b6; font-weight: bold;">' + result.fromusername + '</a>';
    }
    html += '<p>'+ result.content + '</p>';
    html += '<div class="itembottom"><span class="time">' + result.createtimeStr + '</span>';
    html += '<div class="comlinkbox">';
    html += '<a href="javascript:void(0);" onclick="startreply(this);" data-toname="' + result.fromusername + '" data-touseruid="' + result.fromuseruid + '"data-uid="' + result.uid + '"><img src="'+g.baseimg+'/imgs/default/msgimg.png" />评论(' + result.prizecount + ')</a>';
    html += '<a href="javascript:void(0)" id="btnprizecomments_'+me.oid+'_' + result.uid + '" data-counter=".prizecounter">';
    html += '<img src="' + g.baseimg + '/imgs/default/heart_01.png" data-isprize="false"/>';
    html += '<span class="zancountjs">赞(' + result.prizecount + ')</span></a>';
    html += '</div></div></div></div>';
    if($("#list_one_2").css('display')=="block"){
    	$("#list_one_2").hide();
    	$("#one2").removeClass("current");
    	$("#list_one_1").show();
    	$("#one1").addClass("current");
    }
    if($(".commentitem").length > 0){
    	$(".nothing").after(html);
    } else {
    	$(".nothing").addClass("hd");
    	$(".nothing").after(html);
    }
    unRegisterPraise();
    registerPraise();
}
function addReply(bean,obj){
	bean.content = replaceContent(bean.content);
	var html = "";
	html += '<div class="seconditem"><a><img ';
	if (me.oid != 0 && me != "" && me != null && typeof me != 'undefined') {
		if (me.imglittle.trim() != "") {
			html += 'src="' + g.userimg + me.imglittle + '" class="personimg" /></a><div class="secondright">';
		} else {
			html += 'src="' + me.defaultImg + '" class="personimg" /></a><div class="secondright">';
		}
		html += '<a class="name" target="_blank">' + bean.fromusername + '</a>&nbsp;&nbsp;回复&nbsp;';
	} else {
		html += 'src="' + me.defaultImg + '" class="personimg" /></a><div class="secondright">';
		html += '<a style="font-size: 14px; color: #1d71b6; font-weight: bold;">' + bean.fromusername + '</a>&nbsp;&nbsp;回复&nbsp;';
	}
	if (bean.touseroid != 0) {
		html += '<a class="name" target="_blank">' + bean.tousername + '</a>';
	} else {
		html += '<a style="font-size: 14px; color: #1d71b6; font-weight: bold;">' + bean.tousername + '</a>';
	}
	html += '<p>' + bean.content + '</p><div class="itembottom"><span class="time">' + bean.createtimeStr + '</span>';
	html += '<div class="comlinkbox"><a href="javascript:;" onclick="startreply(this);" data-toname="' + bean.fromusername +'" data-uid="' + bean.touid + '"  data-touseruid="' + bean.fromuseruid + '" target="_blank">';
	html += '<img src="' + g.baseimg + '/imgs/default/retret.png" />回复(0)</a>';
	html += '<a href="javascript:;" id="btnprizecomments_' + me.oid + '_' + bean.uid + '" data-counter=".prizecounter">';
	html += '<img src="' + g.baseimg + '/imgs/default/heart_01.png" data-isprize="false"/><span class="zancountjs">赞(' + bean.prizecount + ')</span>';
	html += '</a></div></div></div></div>';
	$(".inputbox").after(html);
	unRegisterPraise();
    registerPraise();
}

/**
 * 重写解除事件绑定
 */
function unRegisterPraise(){
	$(".comlinkbox a[id^='btnprize']").each(function(){
		$(this).unbind("click");
	});
}

/**
 * 重写点赞的事件绑定
 */
function registerPraise(){
	$(".comlinkbox a[id^='btnprize']").each(function(){$(this).prize()});
	prizeBindEvent();
}

/**
 * 重写更新赞的数量
 * 
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

/***************************************订阅*****************************************/
/**
 * 订阅处理
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

/*****************************************************************************************************
 * 表情
 * */
//将[em_*]替换为img标签
function replaceContent(str) {
	str = str.replace(/\[em_([0-9]*)\]/g,'<img src="' + g.baseimg + '/imgs/face/qq/$1.gif" border="0" />');
	return str;
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