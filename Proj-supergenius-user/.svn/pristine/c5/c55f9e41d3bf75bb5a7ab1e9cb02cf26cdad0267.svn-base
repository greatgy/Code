[#ftl]
<script type="text/javascript">
<!--
var g = {};
[#include "/WEB-INF/FtlLib/Web/BaseDecorators/BaseJs.ftl" /]

function toggleMenu(id) {
	var path = window.location.href;
	if(path.indexOf("?") > 0) {
		path = path.substring(0, path.indexOf("?"));
	}
//	alert(path);
	var len = 0;
	var curr;
	var thiscurr;
	$(id).each(function(){
//		var charindex = $(this).attr("href").lastIndexOf("/") ;
//		var p = $(this).attr("href").slice(charindex + 1);
		var p = encodeURI($(this).attr("href"));
//		alert(path + "   |   " + p);
		//var regex = new RegExp("^" + p + ".*");
		if(path.toLowerCase() == p.toLowerCase()) {
			curr = this;
			thiscurr = this;
		} else if(path.indexOf(p) > -1) {
			if(p.length > len) {
				len = p.length;
				curr = this;
			}
		}
	})
	if(thiscurr == null) {
		thiscurr = curr;
	}
	$(thiscurr).addClass("here");
}
function initMenu() {
	toggleMenu(".s_nav a");
}
[#if alertmsg??]
alert("${alertmsg}");
[/#if]
//-->
</script>