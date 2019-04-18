/**
 * 除customvalid外其他都需要input标签
 * eg:
 * <input id="txtname" isnotempty="" ischinese="请输入真实姓名" customreg="^.{2,4}$|请输入您的真实姓名" maxlength="4" class="short" name="name" maxlength="30" value="${((me.name)!)}" type="text" placeholder="您的真实姓名？" />
 * id为空则不提示
 * 
 * 配置选项：
 * 可重复提交配置：form id="form" data-canresubmitrepeat="true" data-beforeSubmitHandler="func()"
 * 对于input验证失败后不获得焦点的处理方式是：<input isfocus="false" />
 * 
 * onkeyup="$(this).trigger('valid'):获得焦点时即可触发事件，不需要失去焦点即可
 * 
 * 对非form的字段进行校验时：
 * 先调用：$("#x").valid();
 * 然后手动触发验证:$("#x").data("validHandler")($("#x").data("vars"))
 * 
 * <input focus="" /> 获得焦点是是否显示提示信息$("#focus" + "txtname") 
 * 
 * 上传文件格式验证方法说明： fileValidBlurHandler
 * 1、对象必须拥有id属性，否则无法输出错误信息
 * 2、验证的file对象必须拥有filevalid属性，只有拥有此属性，才能触发该验证方法
 * 3、filevalid属性是需求格式的字符集和，多个格式使用逗号进行分隔
 * 4、由于绑定的blur事件触发过于频繁，不再使用alert输出信息，而是使用隐藏域，要求隐藏域须有errorinfo="true"的属性
 * 
 * 使用customreg自定义正则和提示信息
 * <input type="text" name="gender" customreg="^.{2,4}$|请输入您的性别" maxlength="4"  />
 * 解释：customreg="你要匹配的正则表达式|当不匹配后要提示的信息"
 * 
 * 
 */
;(function ($) {

	$.fn.valid = function(options) {
		var defaults = {
			forms : {},
			regs : {
				isnotempty:/\S+/,
				isid:/^[A-Za-z0-9\.]{6,}$/,
				ispwd:/^(?!\d+$)(?![a-zA-Z]+$)(?![-`=\\\[\];',./\~!@#$%^&*()_+|{}:"<>?]+$)[\da-zA-Z-`=\\\[\];',./\~!@#$%^&*()_+|{}:"<>?]{6,16}$/,
				isnum:/^[-\+]?\d+(\.\d+)?$/,
				isint:/^[-\+]?\d+$/,
				iszip:/^[1-9]\d{5}$/,
				isdate:/^\d{4}-\d{1,2}-\d{1,2}$/,
				isdatetime:/^\d{4}-\d{1,2}-\d{1,2}\s\d{1,2}:\d{1,2}:\d{1,2}$/,
				isqq:/^[1-9]\d{4,10}$/,
				isenglish:/^[A-Za-z ]+$/,
				ischinese:/^[\u0391-\uFFE5]+$/,
				isurl:/^http[s]?:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
				isemail:/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)/,
				isemailormobile:/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)|^((\(\d{2,3}\))|(\d{3}\-))?(13|14|15|18)\d{9}$/,
				isphone:/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
				ismobile:/^((\(\d{2,3}\))|(\d{3}\-))?(13|14|15|18)\d{9}$/,
				ispassport:/^1[45][0-9]{7}|G[0-9]{8}|P[0-9]{7}|S[0-9]{7,8}|D[0-9]+$/,
				isofficer:/南字第(\d{8})号|北字第(\d{8})号|沈字第(\d{8})号|兰字第(\d{8})号|成字第(\d{8})号|济字第(\d{8})号|广字第(\d{8})号|海字第(\d{8})号|空字第(\d{8})号|参字第(\d{8})号|政字第(\d{8})号|后字第(\d{8})号|装字第(\d{8})号/,
				isidentityid:/^\d{6}[1|2]\d{3}[0|1]\d{1}[0|1|2|3]\d{1}\d{3}[0-9a-zA-Z]$/		
			},
			regsmsg : {
				isnotempty:"不能为空",
				isid:"请输入不少于6位由字母/数字/点组成的字符",
				ispwd:"由6～16位数字、字母、特殊字符的组合组成，字母区分大小写",
				isnum:"请输入数字",
				isint:"请输入整数",
				iszip:"请正确输入邮编",
				isdate:"请输入日期，如2000-01-01",
				isdatetime:"请输入时间，如2000-01-01 13:36:56",
				isqq:"请输入QQ",
				isenglish:"请输入英文",
				ischinese:"请输入中文",
				isurl:"请输入网址",
				isemail:"请正确输入Email地址",
				isemailormobile:"请正确输入Email地址/手机号",
				isphone:"请输入电话号码",
				ismobile:"请输入正确的手机号",
				ispassport:"请输入正确的护照号",
				isofficer:"请输入正确的军官证号",
				isidentityid:"请输入正确的身份证号"
					},
			focusHandler: defaultFocusHandler,
			regsBlurHandler: defaultRegsBlurHandler,
			customRegBlurHandler: defaultCustomRegsHandler,
			customValidBlurHandler: defaultCustomValidHandler,
			ajaxValidBlurHandler: defaultAjaxValidHandler,
			fileValidBlurHandler: defaultFileValidHandler,
			formsValidBlurHandler: defaultFormsValidHandler,
			boxValidBlurHandler: defaultBoxValidHandler
		};
		var vars = $.extend({}, defaults, options);
		vars.boxs = $(this);
		var regElems;
		for (var k in vars.regs) {
			var elems = $("input[" + k + "]");
			regElems = regElems ? regElems.add(elems) : elems;
		}
		regElems.bind("blur valid", vars, vars.regsBlurHandler);
		$("input", vars.boxs).bind("focus", vars, vars.focusHandler);
		$("input[customreg]", vars.boxs).bind("blur valid", vars, vars.customRegBlurHandler);
		$("input[ajaxvalid]", vars.boxs).bind("blur valid", vars, vars.ajaxValidBlurHandler);
		$("input[filevalid]", vars.boxs).bind("blur valid", vars, vars.fileValidBlurHandler);
		$("[customvalid]", vars.boxs).bind("blur valid", vars, vars.customValidBlurHandler);
		vars.boxs.bind("submit valid", vars, vars.formsValidBlurHandler);
		vars.boxs.data("submited", false);
		//手动触发验证:$("#x").data("validHandler")($("#x").data("vars")
		vars.boxs.data("validHandler", vars.boxValidBlurHandler);
		vars.boxs.data("vars", vars);
	 };
	 
	 function defaultFocusHandler(e){
		 var vars = e.data;
		 if((typeof($(this).attr("focus")) == "undefined" || $(this).attr("focus").toLowerCase() != "false") && $(this).val().trim() == "") {
			 var objfocus = $(this).attr("id") ? "#focus" + $(this).attr("id") : false;
			 var errid = $(this).attr("id") ? "#err" + $(this).attr("id") : false;
			 var okid = $(this).attr("id") ? "#ok" + $(this).attr("id") : false;
			 if(okid) {
				 if(!$(okid).hasClass("hd")) {
					 return;
				 }
			 }
			 if(objfocus) {
				 $(objfocus).removeClass("hd");
				 if(typeof($(this).attr("focus")) != "undefined" && $(this).attr("focus").length > 0 && $(this).attr("focus").toLowerCase() != "true") {
					 $(objfocus).text($(this).attr("focus"));
				 }
				 if(errid) {
					 $(errid).addClass("hd");
				 }
			 }
		 }
		 $(this).removeAttr("vflag");
	 }
	 
	 function defaultRegsBlurHandler(e){
		 var vars = e.data;
		 var flag = true;
		 var errid = $(this).attr("id") ? "#err" + $(this).attr("id") : false;
		 var errmsgid = $(this).attr("id") ? "#errmsg" + $(this).attr("id") : false;
		 for (var k in vars.regs) {
			 if(typeof($(this).attr(k)) != "undefined") {
				 if((k == "isnotempty" && !vars.regs[k].test($(this).val())) || ($(this).val() != "" && !vars.regs[k].test($(this).val()))) {
					 flag = false;
					 var errmsg = $(this).attr(k) || vars.regsmsg[k];
					 if($(errmsgid).length == 0) {
						 if(errmsgid) {
							 alert(errmsg);
						 } else {
							 $(this).attr("title", errmsg);
						 }
					 } else {
						 $(errmsgid).html(errmsg);
					 }
					 //$(errmsgid).removeClass("hd");
					 break;
				 } else {
					 //$(errmsgid).addClass("hd");
				 }
			 }
		 }
		 setFlagForThis(this, flag);
	 }
	 
	 function defaultCustomRegsHandler(e){
		 if($(this).attr("vflag") == "false") {
			 return;
		 }
		 var vars = e.data;
		 var flag = true;
		 var errid = $(this).attr("id") ? "#err" + $(this).attr("id") : false;
		 var errmsgid = $(this).attr("id") ? "#errmsg" + $(this).attr("id") : false;
		 var args = $(this).attr("customreg").split("|");
		 var reg = args.length > 0 ? args[0] : null;
		 if(reg) {
			 reg = new RegExp(reg);
		 }
		 var currobj = $(this);
		 var errmsg = args.length > 1 ? args[1] : "输入不正确";
		 if(!reg.test(currobj.val())) {
			 flag = false;
			 if($(errmsgid).length == 0) {
				 if(errmsgid) {
					 alert(errmsg);
				 }
			 } else {
				 $(errmsgid).html(errmsg);
			 }
			 //$(errmsgid).removeClass("hd");
		 } else {
			 //$(errmsgid).addClass("hd");
		 }
		 setFlagForThis(this, flag);
	 }
	 
	 function defaultCustomValidHandler(e){
		 var vars = e.data;
		 var flag = true;
		 var errid = $(this).attr("id") ? "#err" + $(this).attr("id") : false;
		 var errmsgid = $(this).attr("id") ? "#errmsg" + $(this).attr("id") : false;
		 var args = $(this).attr("customvalid").split("|");
		 var fun = args.length > 0 ? args[0] : null;
		 var currobj = $(this);
		 var errmsg = args.length > 1 ? args[1] : "输入不正确";
		 if(!eval(fun)) {
			 flag = false;
			 if($(errmsgid).length == 0) {
				 if(errmsgid) {
					 alert(errmsg);
				 }
			 } else {
				 $(errmsgid).html(errmsg);
			 }
			 //$(errmsgid).removeClass("hd");
		 } else {
			 //$(errmsgid).addClass("hd");
		 }
		 setFlagForThis(this, flag);
	 }
	 
	 /*
	  * <input ajaxvalid="${base}/api/user/emailvalid|此邮箱已被使用|返回1是显示第二种错误提示信息|返回2是显示第三种错误提示信息" />
	  */
	 function defaultAjaxValidHandler(e){
		 if($(this).attr("vflag") == "false") {
			 return;
		 }
		 var vars = e.data;
		 var flag = typeof($(this).attr("vflag")) == "undefined" ? true : $(this).attr("vflag");
		 if(flag == "false"){return;}
		 var errid = $(this).attr("id") ? "#err" + $(this).attr("id") : false;
		 var errmsgid = $(this).attr("id") ? "#errmsg" + $(this).attr("id") : false;
		 var args = $(this).attr("ajaxvalid").split("|");
		 var path = args.length > 0 ? args[0] : null;
		 var currobj = $(this);
		 var errmsg = args.length > 1 ? args.slice(1) : [""];
		 var json = { v: $(this).val(),l: $("#txtemail").val()};
		 $.ajax({
				url:getNoCachePath(path),
				type:"get",
				data:json,
				dataType:"json",
				success:ajaxValidSuccessHandler,
				error:ajaxValidErrorHandler,
				complete:ajaxValidCompleteHandler
			});
		function ajaxValidSuccessHandler(data) {
			if(data.toString() == "true") {//返回true，验证通过，返回0、1、2等都不通过，显示对应的错误提示:errmsg[data]
				$(errmsgid).addClass("hd");
				setFlagForThis(currobj[0], true);
			} else {
				if(data == false) {
					data = 0;
				}
				if($(errmsgid).length == 0) {
					if(errmsgid) {
						 alert(errmsg[data]);
					 }
				 } else {
					 $(errmsgid).html(errmsg[data]);
				 }
				//$(errmsgid).removeClass("hd");
				setFlagForThis(currobj[0], false);
			}
		}
		function ajaxValidErrorHandler() {}
		function ajaxValidCompleteHandler() {}
	 }
	 
	 function defaultFileValidHandler(e){
		 if($(this).attr("vflag") == "false") {
			 return;
		 }
		 var vars = e.data;
		 var flag = typeof($(this).attr("vflag")) == "undefined" ? true : $(this).attr("vflag");
		 if(flag == "false"){return;}
		 var errid = $(this).attr("id") ? "#err" + $(this).attr("id") : false;
		 var errmsgid = $(this).attr("id") ? "#errmsg" + $(this).attr("id") : false;
		 var args = $(this).attr("filevalid").split("|");
		 var types = args.length > 0 ? args[0] : null;
		 var currobj = $(this);
		 var errmsg = args.length > 1 ? args.slice(1) : "请上传正确的文件类型";

		 var fileName = $(this).val();
		 var needTypes = types.split(",");
		 if (fileName.lastIndexOf(".") != -1){
			 var uploadType = (fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length)).toLowerCase();
			 var isNeedType = false;
			 for ( var i = 0; i < needTypes.length; i++) {
				if (needTypes[i] == uploadType) {
					isNeedType = true;
					break;
				}
			 }
			 if (!isNeedType) {
				 flag = false;
			 }
		} else {
			flag = false;
		}
		 
		 if(!flag) {
			 if($(errmsgid).length == 0) {
				 if(errmsgid) {
					 alert(errmsg);
				 }
			 } else {
				 $(errmsgid).html(errmsg);
			 }
			 //$(errmsgid).removeClass("hd");
		 } else {
			 //$(errmsgid).addClass("hd");
		 }
		
		setFlagForThis(this, flag);
	 }
	 
	 function defaultFormsValidHandler(e) {
		 //alert($(this).html());
		 //alert($(this).data("submited"));
//		 if($(this).data("submited")){//防止重复提交
//			 alert("重复操作");
//			 return false;
//		 }
		 var vars = e.data;
		 $("[name]", this).removeAttr("vflag");
		 $("[name]", this).trigger("blur", vars);//先触发验证
		 if($("[name][vflag='false']", this).length == 0) {
			 if(!$(this).data("canresubmitrepeat")) {
				 $(this).data("submited", true);//标记为已提交表单
			 }
			 if($(this).data("beforesubmithandler")) {
				 try {
					 eval($(this).data("beforesubmithandler"));
				 } catch(e) {}
			 }
			 return true;
		 } else {
			 $("input[vflag='false']", this).first().focus();
			 return false;
		 }
	 }
	 
	 function defaultBoxValidHandler(e) {
		 //alert($(this).data("submited"));
		 var vars = e;
		 $("[name]", vars.boxs).removeAttr("vflag");
		 $("[name]", vars.boxs).trigger("blur", vars);//先触发验证
		 if($("[name][vflag='false']", vars.boxs).length == 0) {
			 if(!vars.boxs.data("canresubmitrepeat")) {
				 vars.boxs.data("submited", true);//标记为已提交表单
			 }
			 if(vars.boxs.data("beforeSubmitHandler")) {
				 try {
					 eval(vars.boxs.data("beforeSubmitHandler"));
				 } catch(e) {}
			 }
			 return true;
		 } else {
			 $("input[vflag='false']:not(input[isfocus = 'false'])", vars.boxs).first().focus();
			 return false;
		 }
	 }

	 function setFlagForThis(obj, flag) {
		 if($(obj).attr("vflag") == "false") {//已有验证未通过
			 //alert($(obj).attr("vflag"));
			 return;
		 }
		 $(obj).attr("vflag", flag);
		 var errid = $(obj).attr("id") ? "#err" + $(obj).attr("id") : false;
		 var errmsgid = $(obj).attr("id") ? "#errmsg" + $(obj).attr("id") : false;
		 var okid = $(obj).attr("id") ? "#ok" + $(obj).attr("id") : false;
		 var objfocus = $(obj).attr("id") ? "#focus" + $(obj).attr("id") : false;
		 if(objfocus) {
			 $(objfocus).addClass("hd");
		 }
		 if(flag) {//验证成功
			 if(okid) {
				 $(okid).removeClass("hd");
			 }
			 if(errid) {
				 $(errmsgid).addClass("hd");
				 $(errid).addClass("hd");
			 } else {
				 //$(this).attr("title", errmsg);
			 }
		 } else {
			 if(errid) {
				 $(errid).removeClass("hd");
				 $(errmsgid).removeClass("hd");
			 } else {
				 //$(this).attr("title", "");
			 }
			 if(okid) {
				 $(okid).addClass("hd");
			 }
		 }
	 }
	 
})(jQuery);
$(function(){
//	alert($("form:not(form[autovalid='false'])").length);
	try {
		$("form:not(form[autovalid = 'false'])").valid();
	} catch(e){}
});