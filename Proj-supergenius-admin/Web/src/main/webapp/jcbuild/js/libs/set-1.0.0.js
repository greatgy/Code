(function(a){function j(b){a("[set-toobj]",b).each(function(){var b=eval(a(this).attr("set-toobj")),c=g(a(this));e(a(b),c);a(b).trigger("set")})}function h(b,a,c){"undefined"==typeof c&&(c=1);var d=b.attr(1==c?"set-key":"set-key"+c);"undefined"!=typeof d&&("undefined"!=typeof a[d]?e(b,a[d]):-1!=d.indexOf(".")?f(b,a,d):h(b,a,++c))}function f(b,a,c){if(-1==c.indexOf("."))e(b,a[c]);else{var d=c.substring(0,c.indexOf("."));c=c.substring(c.indexOf(".")+1,c.length);"undefined"!=typeof a[d]&&f(b,a[d],c)}}
function e(b,a){"undefined"!=typeof b.attr("set-format")&&0<b.attr("set-format").length&&(a=b.attr("set-format").format(a));b.is(":checkbox")?b.attr("checked",a):b.is(":radio")?b.val()==a?b.attr("checked",!0):b.attr("checked",!1):b.is(":input")?b.val(a):b.is("img")?b.attr("src",a):b.html(a)}function g(a){return a.is(":input")?a.val():a.is("img")?a.attr("src"):a.html()}a.fn.set=function(b){var f=a.extend({},{},b);a("[set-key]",this).each(function(){h(a(this),f);a(this).trigger("set")});a("[set-value]",
this).each(function(){var b=a(this).attr("set-value");e(a(this),b);a(this).trigger("set")});a("[set-to]",this).each(function(){var b=a(this).attr("set-to"),d=g(a(this));e(a(b),d);a(b).trigger("set")});j(this,f)}})(jQuery);