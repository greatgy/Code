var toTop = 0;
var history_num = 0;
var history_href = location.href;

$(function(){
	$(document).on('click', "a", function(){
		history.replaceState("popstate", "back", history_href);
     }); 
});

$(window).scroll(function() {
	toTop = parseInt($(this).scrollTop());
	var suffix = location.href.split("^=^")[1];
	if (suffix != undefined && suffix != 'undefined') {
		history_href = location.href.split("#")[0] + "#" + toTop + "^=^" + history_num;
	} else {
		if (history_num > 0) {
			history_href = location.href.split("#")[0] + "#" + toTop + "^=^" + history_num;
		} else {
			history_href = location.href.split("#")[0] + "#" + toTop;
		}
	}
});	

function ajax_history(url, pagesize, tabBox, firstNum, firstSize) {
	if (history.pushState) {
		if (location.href.indexOf("#")!=-1) {
			var uriSuffix = location.href.split("#")[1];
			var scrollTop = uriSuffix.split("^=^")[0];
		    var num = uriSuffix.split("^=^")[1];
			var loadsize = num * pagesize - (firstNum - 1);
			if (num == undefined || num == 'undefined') {
				loadsize = firstSize - (firstNum - 1);
			} else {
				history_num = parseInt(num);
				pagenum = parseInt(num) + 1;
			}
			$(window).unbind("scroll", defaultScrollHandler);
			$.ajaxSetup({cache:false, async:false});
			$.get(url , {pagenum: firstNum, pagesize: loadsize}, function(data) {
				scrollTop = parseInt(scrollTop);
				$(tabBox).html(data);
			});
			$(window).scrollTop(scrollTop);
			$.ajaxSetup({cache:true, async:true});
		}
	}
}

