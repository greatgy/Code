/**
 * 用data给含有set-key*属性或者set-value属性的对象进行赋值
 * input使用val()，其他则使用html()赋值
 * set-value优先级最高，会覆盖set-key*的赋值
 * set-key1会覆盖set-key,set-key2会覆盖set-key1。对于key值支持使用‘.’符号来获取某一个对象内的值，而且支持多层嵌套。
 * set-format赋值时使用string.format格式化值
 * set-to 赋值给属性值选择器对应的对象，如#age
 * set-toobj 赋值给属性值eval后对应的对象，如set-toobj="$(&quot;[set-key='mobile']&quot;)"
 */
;(function ($) {

	$.fn.set = function(options) {
		var defaults = {};
		var vars = $.extend({}, defaults, options);
		setKeyHandler(this, vars);
		setValueHandler(this, vars);
		setToHandler(this, vars);
		setToObjHandler(this, vars);
	 };
	 
	 function setKeyHandler(form, vars) {
		 $("[set-key]", form).each(function(){//通过vars设置值
			 	setKey($(this), vars);
				$(this).trigger("set");
			});
	 }
	 
	 function setValueHandler(form, vars) {
		 $("[set-value]", form).each(function(){//通过set-value指定的值设置值
				var v = $(this).attr("set-value");
				assign($(this), v);
				$(this).trigger("set");
			});
	 }
	 
	 function setToHandler(form, vars) {
		 $("[set-to]", form).each(function(){//通过set-value指定的值设置值
			 var id = $(this).attr("set-to");
			 var v = getVal($(this));
			 assign($(id), v);
			 $(id).trigger("set");
		 });
	 }
	 
	 function setToObjHandler(form, vars) {
		 $("[set-toobj]", form).each(function(){//通过set-value指定的值设置值
			 var obj = eval($(this).attr("set-toobj"));
			 var v = getVal($(this));
			 assign($(obj), v);
			 $(obj).trigger("set");
		 });
	 }
	 
	 function setKey(obj, vars, i) {
		 if(typeof(i) == "undefined") {i = 1;}
		 var key = i == 1 ? "set-key" : "set-key" + i;
		 var k = obj.attr(key);
		 if(typeof(k) != "undefined") {
			 if(typeof(vars[k]) != "undefined") {
				 assign(obj, vars[k]);
			 } else {
				 if(k.indexOf(".") != -1){// 有对象嵌套
					 reSetKey(obj, vars, k);
				 }else {
					 setKey(obj, vars, ++i);
				 }
			 }
		 }
	 }
	 
	 /*
	  * 对于有对象嵌套的字段，采用递归循环判断，进行赋值
	  */
	 function reSetKey(obj, vars, key){
		 if(key.indexOf(".") == -1){
			 assign(obj, vars[key]);
			 return;
		 }
		 var newkey = key.substring(0, key.indexOf("."));
		 var leftkey = key.substring(key.indexOf(".")+1, key.length);
		 if(typeof(vars[newkey]) != "undefined"){
			reSetKey(obj, vars[newkey], leftkey); 
		 }
	 }
	 
	 //赋值
	 function assign(obj, v) {
		 if(typeof(obj.attr("set-format")) != "undefined" && obj.attr("set-format").length > 0) {
			 v = obj.attr("set-format").format(v);
		 }
		 if(obj.is(":checkbox")) {
			 obj.attr("checked", v);
		 } else if(obj.is(":radio")) {
			 if(obj.val() == v) {
				 obj.attr("checked", true);
			 } else {
				 obj.attr("checked", false);
			 }
		 } else if(obj.is(":input")) {
			 obj.val(v);
		 } else if(obj.is("img")) {
			 obj.attr("src", v);
		 } else {
			 obj.html(v);
		 }
	 }
	 //获取内容或值
	 function getVal(obj) {
		if(obj.is(":input")) {
			return obj.val();
		} else if(obj.is("img")) {
			return obj.attr("src");
		} else {
			return obj.html();
		}
	 }
})(jQuery);