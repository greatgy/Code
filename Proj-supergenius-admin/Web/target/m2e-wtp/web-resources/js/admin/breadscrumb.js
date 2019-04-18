/**
 * 一些功能在同一个页面显示不全时，并且功能是分步的，或者属于上一级的子功能时使用
 * 使用方法如下：
 * <body>
 * <section>
 * <a href="" data-crumbshow="{index:1,title:{name:'great'}}">点击查看</a>
 * </section>
 * <section class="hd" data-crumb="{title:'{name}的详细信息'}">
 * <a href="" data-crumbreturn="">返回</a>
 * </section>
 */
;(function ($) {

	$.fn.breadcrumb = function(options) {
		var defaults = {
			crumbs: [],
			crumbdivider: "",
			crumbhtml: "",
			current: 0,
			target: $(".breadcrumb"),
			crumbSelectHandler: defaultCrumbSelectHandler,
			crumbShowHandler: defaultCrumbShowHandler,
			crumbClickHandler: defaultCrumbClickHandler,
			crumbDblclickHandler: defaultCrumbDblclickHandler,
			crumbReturnClickHandler: defaultCrumbReturnClickHandler
		};
		var vars = $.extend({}, defaults, options);
		$("html").bind("selectEvent", vars, vars.crumbSelectHandler);
		$("html").bind("returnEvent", vars, vars.crumbReturnClickHandler);
		$("[data-crumbshow]").bind("showEvent", vars, vars.crumbShowHandler);
		$("[data-crumbreturn]").bind("click", vars, vars.crumbReturnClickHandler);
		var firstSection = $("section").first();
		if(firstSection != null) {
			var o1 = {index: 0};
			var o2 = firstSection.data("crumb");
			if(o2 == null) {
				o2 = {title: window.document.title};
			}
			addCrumb(vars, o1, o2);
		}
	 };
	 
	 /**
	  * 显示/添加一个breadcrumb
	  */
	 function defaultCrumbShowHandler(e){
		 var vars = e.data;
		 var o1 = $(this).data("crumbshow");
		 eval("o1 = " + o1);//转成json
		 if(o1.selector) {
			 o1.index = $("section").index($(o1.selector));
			 $(this).data("index", o1.index);
			 //alert(o1.index);
		 }
		 var o2 = $("section").eq(o1.index).data("crumb");
		 eval("o2 = " + o2);
//		 alert(o1.index);
		 addCrumb(vars, o1, o2);
		 setSectionShow(vars, vars.crumbs.length - 1);
	 }
	 
	 /**
	  * 在breadcrumb上单击
	  */
	 function defaultCrumbClickHandler(e) {
		 var vars = e.data;
		 setSectionShow(vars, $(this).data("oid"));
	 }
	 
	 function defaultCrumbSelectHandler(e, i) {
		 var vars = e.data;
//		 alert(i);
//		 alert(vars.crumbs[i].data("index"));
		 setSectionShow(vars, i);
	 }
	 
	 /**
	  * 在breadcrumb上双击
	  */
	 function defaultCrumbDblclickHandler(e) {
		 var vars = e.data;
//		 alert($(this).data("oid"));
//		 alert(vars.crumbs.length);
		 if($(this).data("oid") == (vars.crumbs.length - 1) && $(this).data("oid") > 0) {
			 defaultCrumbReturnClickHandler(e);
		 }
	 }
	 
	 /**
	  * 删除最后一个crumb，选中上一个crumb，若当前的crumb不是最后一个则不进行操作
	  */
	 function defaultCrumbReturnClickHandler(e) {
		 var vars = e.data;
		 if(vars.crumbs.length > 1 && vars.crumbs.length == vars.current + 1) {//不是第一个，且是最后一个
			 removeCrumb(vars);
		 }
	 }
	 
	 /**
	  * 
	  * @param v
	  * @param opt1 触发者的opt，指定选哪个
	  * @param opt2 section的opt
	  * @returns
	  */
	 function addCrumb(vars, opt1, opt2) {
		 try {
			 if(opt2 == null) {opt2 = {};}
			 if(opt2.title) {
				 opt1.title = opt2.title.format(opt1.title);
			 }
			 for (var i in vars.crumbs) {
				 if((vars.crumbs[i].data("opt1").index == opt1.index) && (vars.crumbs[i].data("opt1").title == opt1.title)) {
					 return;
				 }
			 }
//			 alert(vars.crumbhtml.format(opt1));
			 var node = $(vars.crumbhtml.format(opt1));
			 node.bind("click", vars, vars.crumbClickHandler);
			 node.bind("dblclick", vars, vars.crumbDblclickHandler);
			 node.data("index", opt1.index);
			 node.data("opt1", opt1);
			 node.data("oid", vars.crumbs.length);
			 if(opt1.index > 0) {
				 node.prepend(vars.crumbdivider);
				 vars.target.append(node);
			 } else {
				 vars.target.html(node);
			 }
			 vars.crumbs.push(node);
		} catch (e) {
			webLog(e);
		}
	 }
	 
	 /**
	  * 删除最后一个crumb
	  */
	 function removeCrumb(vars) {
		 try {
			setSectionShow(vars, vars.crumbs.length - 2);//先选择倒数第二个section
			var node = vars.crumbs.pop();//然后删除某个crumb
			node.remove();
		} catch (e) {
			webLog(e);
		}
	 }
	 
	 function setSectionShow(vars, i) {
		 vars.current = i;
		 $("section").addClass("hd");
		 $("section").eq(vars.crumbs[i].data("index")).removeClass("hd");
	 }
})(jQuery);
function crumbReturn(){
	$("html").trigger("returnEvent");
}
function crumbShow(obj){
	$(obj).trigger("showEvent");
}
function crumbGo(i) {
	$("html").trigger("selectEvent", i);
}
/**
   $(function(){
		try {
			$("[data-crumb]").breadcrumb({target:$(".breadcrumbs", window.parent.frames.topbar.document.body),crumbdivider:'<div class="breadcrumb_divider"></div>',crumbhtml:'<a href="javascript:void(0)" data-scrumb="{index:{index}}">{title}</a>'});
		} catch(e){}
	});
*/