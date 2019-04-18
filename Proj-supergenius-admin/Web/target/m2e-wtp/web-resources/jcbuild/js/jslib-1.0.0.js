function debug(a){$("body").prepend(a)}String.prototype.trim=function(){return this.replace(/^\s*|\s*$/g,"")};String.prototype.format=function(a){var b=this;if(0<arguments.length)if(1==arguments.length&&"object"==typeof a)for(var c in a){if(void 0!=a[c])var d=RegExp("({"+c+"})","g"),b=b.replace(d,a[c])}else for(c=0;c<arguments.length;c++)void 0!=arguments[c]&&(d=RegExp("({["+c+"]})","g"),b=b.replace(d,arguments[c]));return b};
Date.prototype.format=function(a){var b={"M+":this.getMonth()+1,"d+":this.getDate(),"h+":this.getHours(),"m+":this.getMinutes(),"s+":this.getSeconds(),"q+":Math.floor((this.getMonth()+3)/3),S:this.getMilliseconds()};/(y+)/.test(a)&&(a=a.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length)));for(var c in b)RegExp("("+c+")").test(a)&&(a=a.replace(RegExp.$1,1==RegExp.$1.length?b[c]:("00"+b[c]).substr((""+b[c]).length)));return a};
function setScrollHeight(){window.name=document.documentElement.scrollTop+document.body.scrollTop}function resetScrollHeight(){var a=window.name;""==a||isNaN(a)||($(document).scrollTop(a),window.onscroll=setScrollHeight)}
function setBaseCssForInput(){$("input:text").each(function(){$(this).addClass("txt")});$("input:password").each(function(){$(this).addClass("txt")});$("input:button").each(function(){$(this).addClass("btn")});$("input:submit").each(function(){$(this).addClass("btn")});$("input:radio").each(function(){$(this).addClass("radio")});$("input:checkbox").each(function(){$(this).addClass("chk")});g.ismozilla&&$("html").addClass("ff")}
function setAnchorHref(){$("a[href='']").each(function(){$(this).attr("href","javascript:void(0)")})}function goScrollTo(a,b,c){"undefined"==typeof b&&(b="fast");"undefined"==typeof c&&(c=0);$("html,body").animate({scrollTop:$(a).offset().top-c},b)}function gourl(a){window.location.href=a}function forbiddenIframe(){top.location!==self.location&&(top.location.href=self.location.href)}function deleteConfirmHandler(){return confirm("\u60a8\u786e\u5b9a\u8981\u5220\u9664\u5417\uff1f")}
function computeTextBoxWordsCount(a,b){var c=b.html();a.keyup(function(){var d=c-a.val().length;b.html(d);0>d?b.addClass("red"):b.removeClass("red")});a.trigger("keyup")}function isImageFile(a){return""==a||/(jpg|jpeg|gif|png)$/i.test(a)?!0:!1}function isImageOk(a){return!a.complete||"undefined"!=typeof a.naturalWidth&&0==a.naturalWidth?!1:!0}function rememberWindowUrl(){$.cookie("returnurl",window.location.href,{path:"/"})}
function setFontSize(a,b,c){$.cookie(c)&&($(b).addClass($.cookie(c)),$(a+"[data-size='"+$.cookie(c)+"']").addClass("here"));$(a).bind("click",function(){$(b).removeClass($.cookie(c));$(a+"[data-size='"+$.cookie(c)+"']").removeClass("here");$.cookie(c,$(this).data("size"),{expires:365,path:"/"});$(b).addClass($.cookie(c));$(a+"[data-size='"+$.cookie(c)+"']").addClass("here")})}function getNoCachePath(a){return a+"?s="+(new Date).format("ssS")}
function formatCurrency(a){a=a.toString().replace(/\$|\,/g,"");if(0==a)return 0;isNaN(a)&&(a="0");sign=a==(a=Math.abs(a));a=Math.floor(100*a+0.50000000001);cents=a%100;a=Math.floor(a/100).toString();10>cents&&(cents="0"+cents);for(var b=0;b<Math.floor((a.length-(1+b))/3);b++)a=a.substring(0,a.length-(4*b+3))+","+a.substring(a.length-(4*b+3));return(sign?"":"-")+a+"."+cents}function isMobile(){return!!navigator.userAgent.match(/AppleWebKit.*Mobile.*/)};