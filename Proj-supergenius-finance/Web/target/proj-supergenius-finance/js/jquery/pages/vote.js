/*********************************************************************************************
 * 投票
 */
function InitDebateVoteEvent() {
	$("a[id^='debate_vote_two_']").each(function(){
		var isvoted = $(this).children("img").data("isvoted");
		changeDebateVoteImg(this);
		$(this).unbind("click");
		$(this).bind("click", vote);
	});
}

function InitDebateVoteStyleEvent() {
	$("a[id^='debate_vote_two_']").each(function(){
		var isvoted = $(this).children("img").data("isvoted");
		if (isvoted) {
			changeDebateVoteImg(this);
		}
	});
}

function voteStyle(obj, element){
	var curobj = obj;
	var camp = $(curobj).data("support");
	var debateuid = $(curobj).data("id");
	if (checkIsClickedStyle(debateuid, camp)) {
		return;
	}
	var isred;
	if (camp == "red") {
		isred = true;
	} else {
		isred = false;
	}
	setVoteData(debateuid, camp);
	ajaxchangeDebateVoteStyleImg(curobj, element);
}

/**
 * 设置5中手型
 * @param obj
 */
function ajaxchangeDebateVoteStyleImg(obj, element) {
	var support = $(obj).data("support");
	var nochick;
	if (support == "red") {
		element.find("a.debate_vote_red img").attr("src", g.baseimg + "/imgs/default/redzaned.png");
		nochick = "blue";
	} else {
		element.find("a.debate_vote_blue img").attr("src", g.baseimg + "/imgs/default/bluezaned.png");
		nochick = "red";
	}
	element.find("div.data_" + nochick + " a img").attr("src", g.baseimg + "/imgs/default/grayzan.png");
}

/**
 * 是否点击过
 * @param id
 */
function checkIsClickedStyle(id) {
	if (voteData[id] == "red") {
		return true;
	} else if (voteData[id] == "blue") {
		return true;
	} else {
		return false;
	}
}

/**
 * 设置5中手型
 * @param obj
 */
function changeDebateVoteImg(obj) {
	var support = $(obj).data("support");
	var isvoted = $(obj).children("img").data("isvoted");
	var isme = $(obj).children("img").data("isme");
	if (isvoted) {
		if (isme) {
			if (support == "red") {
				$(obj).children("img").attr("src", g.baseimg + "/imgs/default/redzaned.png");
			} else {
				$(obj).children("img").attr("src", g.baseimg + "/imgs/default/bluezaned.png");
			}
		} else {
			$(obj).children("img").attr("src", g.baseimg + "/imgs/default/grayzan.png");
		}
	}
}

/**
 * 设置5中手型
 * @param obj
 */
function ajaxchangeDebateVoteImg(obj) {
	var support = $(obj).data("support");
	if (support == "red") {
		$(obj).children("img").attr("src", g.baseimg + "/imgs/default/redzaned.png");
	} else {
		$(obj).children("img").attr("src", g.baseimg + "/imgs/default/bluezaned.png");
	}
	$(obj).siblings("a").children("img").attr("src", g.baseimg + "/imgs/default/grayzan.png");
	$(obj).parent().siblings("span").children("a").children("img").attr("src", g.baseimg + "/imgs/default/grayzan.png");
}

var voteData = {};
function vote(){
	var camp = $(this).data("support");
	var debateuid = $(this).data("id");
	if (checkIsClicked(debateuid, camp)) {
		return;
	}
	var curobj = this;
	var isred;
	if (camp == "red") {
		isred = true;
	} else {
		isred = false;
	}
	$.get(g.base + "/debate/vote/" + debateuid, {isred:isred}, function(data){
		var curvote;
		if(data.success == true){
			setVoteData(debateuid, camp);
			curvote = parseInt($(".num_" + debateuid + "_" + camp).html()) + 1;
			$(".num_" + debateuid + "_" + camp).html(curvote);
			ajaxchangeDebateVoteImg(curobj);
			//$(curobj).unbind("click");
			//$(curobj).siblings("a").unbind("click");
			//$(curobj).parent().siblings("span").children("a").unbind("click");
		} else {
			if(data.voted == true){
				alertVoteState(debateuid, data.isred);
			}else{
				alert("未知错误，投票失败，请刷新重试！");
			}
		}
	});
}

/**
 * 设置页面缓存
 * @param id
 * @param camp
 */
function setVoteData(id, camp) {
	voteData[id] = camp;
}

/**
 * 已经投过票
 * @param id
 * @param isred
 */
function alertVoteState(id, isred) {
	if (isred) {
		alert("你已经投票给红方");
		voteData[id] = "red";
	} else {
		alert("你已经投票给蓝方");
		voteData[id] = "blue";
	}
}

/**
 * 是否点击过
 * @param id
 */
function checkIsClicked(id) {
	if (voteData[id] == "red") {
		alert("你已经投票给红方");
		return true;
	} else if (voteData[id] == "blue") {
		alert("你已经投票给蓝方");
		return true;
	} else {
		return false;
	}
}