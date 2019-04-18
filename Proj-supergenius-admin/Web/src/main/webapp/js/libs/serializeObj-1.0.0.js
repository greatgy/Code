/**
 * 将某个区域里的所有name属性的值放到某个json对象中并返回,非form表单也支持
 */
$.fn.serializeObj = function()
{
    var o = {};
    if($("form", this.parent())[0]) {
    	var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
    } else {
    	var functiontext = function() {
    		if($(this).val().length > 0) {
    			o[$(this).attr("name")] = $(this).val();
    		}
    	};
    	var functioncheck = function() {
    		if($(this).val().length > 0 && this.checked) {
    			o[$(this).attr("name")] = $(this).val();
    		}
    	};
    	$("input[type='text']", this).each(functiontext);
    	$("input[type='hidden']", this).each(functiontext);
    	$("input[type='radio']", this).each(functioncheck);
    	$("input[type='checkbox']", this).each(functioncheck);
    	$("textarea", this).each(functiontext);
    	$("select", this).each(functiontext);
    }
    return o;
};