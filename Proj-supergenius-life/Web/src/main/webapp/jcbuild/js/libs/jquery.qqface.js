(function(a){a.fn.qqFace=function(b){var c=a.extend({id:"facebox",path:"face/",assign:"content",tip:"em_",default_assign:"content"},b);b=a("#"+c.assign);var d=c.id,f=c.path,j=c.tip;if(0>=b.length)return!1;a(this).click(function(b){var g=initData(this);c.assign=g?g:c.default_assign;if(a(this).siblings("div").html())a("#"+d).hide(),a("#"+d).remove();else{var e;if(0>=a("#"+d).length){e='<div id="'+d+'" style="position:absolute;display:none;z-index:1000;" class="qqFace"><table border="0" cellspacing="0" cellpadding="0"><tr>';
for(var h=1;75>=h;h++)g="["+j+h+"]",e+='<td><img src="'+f+h+'.gif" onclick="$(\'#'+c.assign+"').setCaret();$('#"+c.assign+"').insertAtCaret('"+g+"');\" /></td>",0==h%12&&(e+="</tr><tr>");e+="</tr></table></div>"}a(this).parent().append(e);e=a(this).position();g=e.top+a(this).outerHeight();a("#"+d).css("top",g);a("#"+d).css("left",e.left);a("#"+d).show();b.stopPropagation()}});a(document).click(function(){a("#"+d).hide();a("#"+d).remove()})}})(jQuery);
jQuery.extend({unselectContents:function(){window.getSelection?window.getSelection().removeAllRanges():document.selection&&document.selection.empty()}});
jQuery.fn.extend({selectContents:function(){$(this).each(function(a){var b,c,d,f;if((d=this.ownerDocument)&&(f=d.defaultView)&&"undefined"!=typeof f.getSelection&&"undefined"!=typeof d.createRange&&(b=window.getSelection())&&"undefined"!=typeof b.removeAllRanges)c=d.createRange(),c.selectNode(this),0==a&&b.removeAllRanges(),b.addRange(c);else if(document.body&&"undefined"!=typeof document.body.createTextRange&&(c=document.body.createTextRange()))c.moveToElementText(this),c.select()})},setCaret:function(){if($.browser.msie){var a=
function(){$(this).get(0).caretPos=document.selection.createRange().duplicate()};$(this).click(a).select(a).keyup(a)}},insertAtCaret:function(a){var b=$(this).get(0);if(document.all&&b.createTextRange&&b.caretPos)b=b.caretPos,b.text=""==b.text.charAt(b.text.length-1)?a+"":a;else if(b.setSelectionRange){var c=b.selectionStart,d=b.selectionEnd,f=b.value.substring(0,c),d=b.value.substring(d);b.value=f+a+d;b.focus();a=a.length;b.setSelectionRange(c+a,c+a);b.blur()}else b.innerHTML+=a}});
function initData(){};