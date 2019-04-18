if("undefined"===typeof jQuery)throw Error("Bootstrap's JavaScript requires jQuery");+function(c){c=c.fn.jquery.split(" ")[0].split(".");if(2>c[0]&&9>c[1]||1==c[0]&&9==c[1]&&1>c[2])throw Error("Bootstrap's JavaScript requires jQuery version 1.9.1 or higher");}(jQuery);
+function(c){c.fn.emulateTransitionEnd=function(d){var e=!1,b=this;c(this).one("bsTransitionEnd",function(){e=!0});setTimeout(function(){e||c(b).trigger(c.support.transition.end)},d);return this};c(function(){var d=c.support,e;a:{e=document.createElement("bootstrap");var b={WebkitTransition:"webkitTransitionEnd",MozTransition:"transitionend",OTransition:"oTransitionEnd otransitionend",transition:"transitionend"},a;for(a in b)if(void 0!==e.style[a]){e={end:b[a]};break a}e=!1}d.transition=e;c.support.transition&&
(c.event.special.bsTransitionEnd={bindType:c.support.transition.end,delegateType:c.support.transition.end,handle:function(a){if(c(a.target).is(this))return a.handleObj.handler.apply(this,arguments)}})})}(jQuery);
+function(c){var d=function(b){c(b).on("click",'[data-dismiss="alert"]',this.close)};d.VERSION="3.3.5";d.TRANSITION_DURATION=150;d.prototype.close=function(b){function a(){h.detach().trigger("closed.bs.alert").remove()}var g=c(this),f=g.attr("data-target");f||(f=(f=g.attr("href"))&&f.replace(/.*(?=#[^\s]*$)/,""));var h=c(f);b&&b.preventDefault();h.length||(h=g.closest(".alert"));h.trigger(b=c.Event("close.bs.alert"));b.isDefaultPrevented()||(h.removeClass("in"),c.support.transition&&h.hasClass("fade")?
h.one("bsTransitionEnd",a).emulateTransitionEnd(d.TRANSITION_DURATION):a())};var e=c.fn.alert;c.fn.alert=function(b){return this.each(function(){var a=c(this),g=a.data("bs.alert");g||a.data("bs.alert",g=new d(this));"string"==typeof b&&g[b].call(a)})};c.fn.alert.Constructor=d;c.fn.alert.noConflict=function(){c.fn.alert=e;return this};c(document).on("click.bs.alert.data-api",'[data-dismiss="alert"]',d.prototype.close)}(jQuery);
+function(c){function d(a){return this.each(function(){var g=c(this),b=g.data("bs.button"),h="object"==typeof a&&a;b||g.data("bs.button",b=new e(this,h));"toggle"==a?b.toggle():a&&b.setState(a)})}var e=function(a,g){this.$element=c(a);this.options=c.extend({},e.DEFAULTS,g);this.isLoading=!1};e.VERSION="3.3.5";e.DEFAULTS={loadingText:"loading..."};e.prototype.setState=function(a){var g=this.$element,b=g.is("input")?"val":"html",h=g.data();a+="Text";null==h.resetText&&g.data("resetText",g[b]());setTimeout(c.proxy(function(){g[b](null==
h[a]?this.options[a]:h[a]);"loadingText"==a?(this.isLoading=!0,g.addClass("disabled").attr("disabled","disabled")):this.isLoading&&(this.isLoading=!1,g.removeClass("disabled").removeAttr("disabled"))},this),0)};e.prototype.toggle=function(){var a=!0,g=this.$element.closest('[data-toggle="buttons"]');if(g.length){var c=this.$element.find("input");"radio"==c.prop("type")?(c.prop("checked")&&(a=!1),g.find(".active").removeClass("active"),this.$element.addClass("active")):"checkbox"==c.prop("type")&&
(c.prop("checked")!==this.$element.hasClass("active")&&(a=!1),this.$element.toggleClass("active"));c.prop("checked",this.$element.hasClass("active"));a&&c.trigger("change")}else this.$element.attr("aria-pressed",!this.$element.hasClass("active")),this.$element.toggleClass("active")};var b=c.fn.button;c.fn.button=d;c.fn.button.Constructor=e;c.fn.button.noConflict=function(){c.fn.button=b;return this};c(document).on("click.bs.button.data-api",'[data-toggle^="button"]',function(a){var g=c(a.target);
g.hasClass("btn")||(g=g.closest(".btn"));d.call(g,"toggle");!c(a.target).is('input[type="radio"]')&&!c(a.target).is('input[type="checkbox"]')&&a.preventDefault()}).on("focus.bs.button.data-api blur.bs.button.data-api",'[data-toggle^="button"]',function(a){c(a.target).closest(".btn").toggleClass("focus",/^focus(in)?$/.test(a.type))})}(jQuery);
+function(c){function d(a){return this.each(function(){var b=c(this),h=b.data("bs.carousel"),k=c.extend({},e.DEFAULTS,b.data(),"object"==typeof a&&a),d="string"==typeof a?a:k.slide;h||b.data("bs.carousel",h=new e(this,k));if("number"==typeof a)h.to(a);else if(d)h[d]();else k.interval&&h.pause().cycle()})}var e=function(a,b){this.$element=c(a);this.$indicators=this.$element.find(".carousel-indicators");this.options=b;this.$items=this.$active=this.interval=this.sliding=this.paused=null;this.options.keyboard&&
this.$element.on("keydown.bs.carousel",c.proxy(this.keydown,this));"hover"==this.options.pause&&!("ontouchstart"in document.documentElement)&&this.$element.on("mouseenter.bs.carousel",c.proxy(this.pause,this)).on("mouseleave.bs.carousel",c.proxy(this.cycle,this))};e.VERSION="3.3.5";e.TRANSITION_DURATION=600;e.DEFAULTS={interval:5E3,pause:"hover",wrap:!0,keyboard:!0};e.prototype.keydown=function(a){if(!/input|textarea/i.test(a.target.tagName)){switch(a.which){case 37:this.prev();break;case 39:this.next();
break;default:return}a.preventDefault()}};e.prototype.cycle=function(a){a||(this.paused=!1);this.interval&&clearInterval(this.interval);this.options.interval&&!this.paused&&(this.interval=setInterval(c.proxy(this.next,this),this.options.interval));return this};e.prototype.getItemIndex=function(a){this.$items=a.parent().children(".item");return this.$items.index(a||this.$active)};e.prototype.getItemForDirection=function(a,c){var b=this.getItemIndex(c);return("prev"==a&&0===b||"next"==a&&b==this.$items.length-
1)&&!this.options.wrap?c:this.$items.eq((b+("prev"==a?-1:1))%this.$items.length)};e.prototype.to=function(a){var c=this,b=this.getItemIndex(this.$active=this.$element.find(".item.active"));if(!(a>this.$items.length-1||0>a))return this.sliding?this.$element.one("slid.bs.carousel",function(){c.to(a)}):b==a?this.pause().cycle():this.slide(a>b?"next":"prev",this.$items.eq(a))};e.prototype.pause=function(a){a||(this.paused=!0);this.$element.find(".next, .prev").length&&c.support.transition&&(this.$element.trigger(c.support.transition.end),
this.cycle(!0));this.interval=clearInterval(this.interval);return this};e.prototype.next=function(){if(!this.sliding)return this.slide("next")};e.prototype.prev=function(){if(!this.sliding)return this.slide("prev")};e.prototype.slide=function(a,b){var h=this.$element.find(".item.active"),k=b||this.getItemForDirection(a,h),d=this.interval,j="next"==a?"left":"right",m=this;if(k.hasClass("active"))return this.sliding=!1;var p=k[0],n=c.Event("slide.bs.carousel",{relatedTarget:p,direction:j});this.$element.trigger(n);
if(!n.isDefaultPrevented()){this.sliding=!0;d&&this.pause();this.$indicators.length&&(this.$indicators.find(".active").removeClass("active"),(n=c(this.$indicators.children()[this.getItemIndex(k)]))&&n.addClass("active"));var q=c.Event("slid.bs.carousel",{relatedTarget:p,direction:j});c.support.transition&&this.$element.hasClass("slide")?(k.addClass(a),k[0].offsetWidth,h.addClass(j),k.addClass(j),h.one("bsTransitionEnd",function(){k.removeClass([a,j].join(" ")).addClass("active");h.removeClass(["active",
j].join(" "));m.sliding=!1;setTimeout(function(){m.$element.trigger(q)},0)}).emulateTransitionEnd(e.TRANSITION_DURATION)):(h.removeClass("active"),k.addClass("active"),this.sliding=!1,this.$element.trigger(q));d&&this.cycle();return this}};var b=c.fn.carousel;c.fn.carousel=d;c.fn.carousel.Constructor=e;c.fn.carousel.noConflict=function(){c.fn.carousel=b;return this};var a=function(a){var b,h=c(this),k=c(h.attr("data-target")||(b=h.attr("href"))&&b.replace(/.*(?=#[^\s]+$)/,""));if(k.hasClass("carousel")){b=
c.extend({},k.data(),h.data());if(h=h.attr("data-slide-to"))b.interval=!1;d.call(k,b);h&&k.data("bs.carousel").to(h);a.preventDefault()}};c(document).on("click.bs.carousel.data-api","[data-slide]",a).on("click.bs.carousel.data-api","[data-slide-to]",a);c(window).on("load",function(){c('[data-ride="carousel"]').each(function(){var a=c(this);d.call(a,a.data())})})}(jQuery);
+function(c){function d(a){var b;a=a.attr("data-target")||(b=a.attr("href"))&&b.replace(/.*(?=#[^\s]+$)/,"");return c(a)}function e(a){return this.each(function(){var f=c(this),h=f.data("bs.collapse"),k=c.extend({},b.DEFAULTS,f.data(),"object"==typeof a&&a);!h&&(k.toggle&&/show|hide/.test(a))&&(k.toggle=!1);h||f.data("bs.collapse",h=new b(this,k));if("string"==typeof a)h[a]()})}var b=function(a,f){this.$element=c(a);this.options=c.extend({},b.DEFAULTS,f);this.$trigger=c('[data-toggle="collapse"][href="#'+
a.id+'"],[data-toggle="collapse"][data-target="#'+a.id+'"]');this.transitioning=null;this.options.parent?this.$parent=this.getParent():this.addAriaAndCollapsedClass(this.$element,this.$trigger);this.options.toggle&&this.toggle()};b.VERSION="3.3.5";b.TRANSITION_DURATION=350;b.DEFAULTS={toggle:!0};b.prototype.dimension=function(){return this.$element.hasClass("width")?"width":"height"};b.prototype.show=function(){if(!this.transitioning&&!this.$element.hasClass("in")){var a,f=this.$parent&&this.$parent.children(".panel").children(".in, .collapsing");
if(f&&f.length&&(a=f.data("bs.collapse"))&&a.transitioning)return;var h=c.Event("show.bs.collapse");this.$element.trigger(h);if(!h.isDefaultPrevented()){f&&f.length&&(e.call(f,"hide"),a||f.data("bs.collapse",null));var k=this.dimension();this.$element.removeClass("collapse").addClass("collapsing")[k](0).attr("aria-expanded",!0);this.$trigger.removeClass("collapsed").attr("aria-expanded",!0);this.transitioning=1;a=function(){this.$element.removeClass("collapsing").addClass("collapse in")[k]("");this.transitioning=
0;this.$element.trigger("shown.bs.collapse")};if(!c.support.transition)return a.call(this);f=c.camelCase(["scroll",k].join("-"));this.$element.one("bsTransitionEnd",c.proxy(a,this)).emulateTransitionEnd(b.TRANSITION_DURATION)[k](this.$element[0][f])}}};b.prototype.hide=function(){if(!this.transitioning&&this.$element.hasClass("in")){var a=c.Event("hide.bs.collapse");this.$element.trigger(a);if(!a.isDefaultPrevented()){a=this.dimension();this.$element[a](this.$element[a]())[0].offsetHeight;this.$element.addClass("collapsing").removeClass("collapse in").attr("aria-expanded",
!1);this.$trigger.addClass("collapsed").attr("aria-expanded",!1);this.transitioning=1;var f=function(){this.transitioning=0;this.$element.removeClass("collapsing").addClass("collapse").trigger("hidden.bs.collapse")};if(!c.support.transition)return f.call(this);this.$element[a](0).one("bsTransitionEnd",c.proxy(f,this)).emulateTransitionEnd(b.TRANSITION_DURATION)}}};b.prototype.toggle=function(){this[this.$element.hasClass("in")?"hide":"show"]()};b.prototype.getParent=function(){return c(this.options.parent).find('[data-toggle="collapse"][data-parent="'+
this.options.parent+'"]').each(c.proxy(function(a,b){var h=c(b);this.addAriaAndCollapsedClass(d(h),h)},this)).end()};b.prototype.addAriaAndCollapsedClass=function(a,c){var b=a.hasClass("in");a.attr("aria-expanded",b);c.toggleClass("collapsed",!b).attr("aria-expanded",b)};var a=c.fn.collapse;c.fn.collapse=e;c.fn.collapse.Constructor=b;c.fn.collapse.noConflict=function(){c.fn.collapse=a;return this};c(document).on("click.bs.collapse.data-api",'[data-toggle="collapse"]',function(a){var b=c(this);b.attr("data-target")||
a.preventDefault();a=d(b);b=a.data("bs.collapse")?"toggle":b.data();e.call(a,b)})}(jQuery);
+function(c){function d(a){var b=a.attr("data-target");b||(b=(b=a.attr("href"))&&/#[A-Za-z]/.test(b)&&b.replace(/.*(?=#[^\s]*$)/,""));return(b=b&&c(b))&&b.length?b:a.parent()}function e(g){g&&3===g.which||(c(b).remove(),c(a).each(function(){var a=c(this),b=d(a),f={relatedTarget:this};if(b.hasClass("open")&&(!g||!("click"==g.type&&/input|textarea/i.test(g.target.tagName)&&c.contains(b[0],g.target))))b.trigger(g=c.Event("hide.bs.dropdown",f)),g.isDefaultPrevented()||(a.attr("aria-expanded","false"),
b.removeClass("open").trigger("hidden.bs.dropdown",f))}))}var b=".dropdown-backdrop",a='[data-toggle="dropdown"]',g=function(a){c(a).on("click.bs.dropdown",this.toggle)};g.VERSION="3.3.5";g.prototype.toggle=function(a){var b=c(this);if(!b.is(".disabled, :disabled")){var g=d(b);a=g.hasClass("open");e();if(!a){if("ontouchstart"in document.documentElement&&!g.closest(".navbar-nav").length)c(document.createElement("div")).addClass("dropdown-backdrop").insertAfter(c(this)).on("click",e);var f={relatedTarget:this};
g.trigger(a=c.Event("show.bs.dropdown",f));if(a.isDefaultPrevented())return;b.trigger("focus").attr("aria-expanded","true");g.toggleClass("open").trigger("shown.bs.dropdown",f)}return!1}};g.prototype.keydown=function(b){if(/(38|40|27|32)/.test(b.which)&&!/input|textarea/i.test(b.target.tagName)){var g=c(this);b.preventDefault();b.stopPropagation();if(!g.is(".disabled, :disabled")){var f=d(g),e=f.hasClass("open");if(!e&&27!=b.which||e&&27==b.which)return 27==b.which&&f.find(a).trigger("focus"),g.trigger("click");
g=f.find(".dropdown-menu li:not(.disabled):visible a");g.length&&(f=g.index(b.target),38==b.which&&0<f&&f--,40==b.which&&f<g.length-1&&f++,~f||(f=0),g.eq(f).trigger("focus"))}}};var f=c.fn.dropdown;c.fn.dropdown=function(a){return this.each(function(){var b=c(this),f=b.data("bs.dropdown");f||b.data("bs.dropdown",f=new g(this));"string"==typeof a&&f[a].call(b)})};c.fn.dropdown.Constructor=g;c.fn.dropdown.noConflict=function(){c.fn.dropdown=f;return this};c(document).on("click.bs.dropdown.data-api",
e).on("click.bs.dropdown.data-api",".dropdown form",function(a){a.stopPropagation()}).on("click.bs.dropdown.data-api",a,g.prototype.toggle).on("keydown.bs.dropdown.data-api",a,g.prototype.keydown).on("keydown.bs.dropdown.data-api",".dropdown-menu",g.prototype.keydown)}(jQuery);
+function(c){function d(a,b){return this.each(function(){var f=c(this),h=f.data("bs.modal"),d=c.extend({},e.DEFAULTS,f.data(),"object"==typeof a&&a);h||f.data("bs.modal",h=new e(this,d));if("string"==typeof a)h[a](b);else d.show&&h.show(b)})}var e=function(a,b){this.options=b;this.$body=c(document.body);this.$element=c(a);this.$dialog=this.$element.find(".modal-dialog");this.originalBodyPad=this.isShown=this.$backdrop=null;this.scrollbarWidth=0;this.ignoreBackdropClick=!1;this.options.remote&&this.$element.find(".modal-content").load(this.options.remote,
c.proxy(function(){this.$element.trigger("loaded.bs.modal")},this))};e.VERSION="3.3.5";e.TRANSITION_DURATION=300;e.BACKDROP_TRANSITION_DURATION=150;e.DEFAULTS={backdrop:!0,keyboard:!0,show:!0};e.prototype.toggle=function(a){return this.isShown?this.hide():this.show(a)};e.prototype.show=function(a){var b=this,f=c.Event("show.bs.modal",{relatedTarget:a});this.$element.trigger(f);!this.isShown&&!f.isDefaultPrevented()&&(this.isShown=!0,this.checkScrollbar(),this.setScrollbar(),this.$body.addClass("modal-open"),
this.escape(),this.resize(),this.$element.on("click.dismiss.bs.modal",'[data-dismiss="modal"]',c.proxy(this.hide,this)),this.$dialog.on("mousedown.dismiss.bs.modal",function(){b.$element.one("mouseup.dismiss.bs.modal",function(a){c(a.target).is(b.$element)&&(b.ignoreBackdropClick=!0)})}),this.backdrop(function(){var f=c.support.transition&&b.$element.hasClass("fade");b.$element.parent().length||b.$element.appendTo(b.$body);b.$element.show().scrollTop(0);b.adjustDialog();f&&b.$element[0].offsetWidth;
b.$element.addClass("in");b.enforceFocus();var d=c.Event("shown.bs.modal",{relatedTarget:a});f?b.$dialog.one("bsTransitionEnd",function(){b.$element.trigger("focus").trigger(d)}).emulateTransitionEnd(e.TRANSITION_DURATION):b.$element.trigger("focus").trigger(d)}))};e.prototype.hide=function(a){a&&a.preventDefault();a=c.Event("hide.bs.modal");this.$element.trigger(a);this.isShown&&!a.isDefaultPrevented()&&(this.isShown=!1,this.escape(),this.resize(),c(document).off("focusin.bs.modal"),this.$element.removeClass("in").off("click.dismiss.bs.modal").off("mouseup.dismiss.bs.modal"),
this.$dialog.off("mousedown.dismiss.bs.modal"),c.support.transition&&this.$element.hasClass("fade")?this.$element.one("bsTransitionEnd",c.proxy(this.hideModal,this)).emulateTransitionEnd(e.TRANSITION_DURATION):this.hideModal())};e.prototype.enforceFocus=function(){c(document).off("focusin.bs.modal").on("focusin.bs.modal",c.proxy(function(a){this.$element[0]!==a.target&&!this.$element.has(a.target).length&&this.$element.trigger("focus")},this))};e.prototype.escape=function(){if(this.isShown&&this.options.keyboard)this.$element.on("keydown.dismiss.bs.modal",
c.proxy(function(a){27==a.which&&this.hide()},this));else this.isShown||this.$element.off("keydown.dismiss.bs.modal")};e.prototype.resize=function(){if(this.isShown)c(window).on("resize.bs.modal",c.proxy(this.handleUpdate,this));else c(window).off("resize.bs.modal")};e.prototype.hideModal=function(){var a=this;this.$element.hide();this.backdrop(function(){a.$body.removeClass("modal-open");a.resetAdjustments();a.resetScrollbar();a.$element.trigger("hidden.bs.modal")})};e.prototype.removeBackdrop=function(){this.$backdrop&&
this.$backdrop.remove();this.$backdrop=null};e.prototype.backdrop=function(a){var b=this,f=this.$element.hasClass("fade")?"fade":"";if(this.isShown&&this.options.backdrop){var h=c.support.transition&&f;this.$backdrop=c(document.createElement("div")).addClass("modal-backdrop "+f).appendTo(this.$body);this.$element.on("click.dismiss.bs.modal",c.proxy(function(a){this.ignoreBackdropClick?this.ignoreBackdropClick=!1:a.target===a.currentTarget&&("static"==this.options.backdrop?this.$element[0].focus():
this.hide())},this));h&&this.$backdrop[0].offsetWidth;this.$backdrop.addClass("in");a&&(h?this.$backdrop.one("bsTransitionEnd",a).emulateTransitionEnd(e.BACKDROP_TRANSITION_DURATION):a())}else!this.isShown&&this.$backdrop?(this.$backdrop.removeClass("in"),f=function(){b.removeBackdrop();a&&a()},c.support.transition&&this.$element.hasClass("fade")?this.$backdrop.one("bsTransitionEnd",f).emulateTransitionEnd(e.BACKDROP_TRANSITION_DURATION):f()):a&&a()};e.prototype.handleUpdate=function(){this.adjustDialog()};
e.prototype.adjustDialog=function(){var a=this.$element[0].scrollHeight>document.documentElement.clientHeight;this.$element.css({paddingLeft:!this.bodyIsOverflowing&&a?this.scrollbarWidth:"",paddingRight:this.bodyIsOverflowing&&!a?this.scrollbarWidth:""})};e.prototype.resetAdjustments=function(){this.$element.css({paddingLeft:"",paddingRight:""})};e.prototype.checkScrollbar=function(){var a=window.innerWidth;a||(a=document.documentElement.getBoundingClientRect(),a=a.right-Math.abs(a.left));this.bodyIsOverflowing=
document.body.clientWidth<a;this.scrollbarWidth=this.measureScrollbar()};e.prototype.setScrollbar=function(){var a=parseInt(this.$body.css("padding-right")||0,10);this.originalBodyPad=document.body.style.paddingRight||"";this.bodyIsOverflowing&&this.$body.css("padding-right",a+this.scrollbarWidth)};e.prototype.resetScrollbar=function(){this.$body.css("padding-right",this.originalBodyPad)};e.prototype.measureScrollbar=function(){var a=document.createElement("div");a.className="modal-scrollbar-measure";
this.$body.append(a);var b=a.offsetWidth-a.clientWidth;this.$body[0].removeChild(a);return b};var b=c.fn.modal;c.fn.modal=d;c.fn.modal.Constructor=e;c.fn.modal.noConflict=function(){c.fn.modal=b;return this};c(document).on("click.bs.modal.data-api",'[data-toggle="modal"]',function(a){var b=c(this),f=b.attr("href"),h=c(b.attr("data-target")||f&&f.replace(/.*(?=#[^\s]+$)/,"")),f=h.data("bs.modal")?"toggle":c.extend({remote:!/#/.test(f)&&f},h.data(),b.data());b.is("a")&&a.preventDefault();h.one("show.bs.modal",
function(a){if(!a.isDefaultPrevented())h.one("hidden.bs.modal",function(){b.is(":visible")&&b.trigger("focus")})});d.call(h,f,this)})}(jQuery);
+function(c){var d=function(b,a){this.inState=this.$element=this.hoverState=this.timeout=this.enabled=this.options=this.type=null;this.init("tooltip",b,a)};d.VERSION="3.3.5";d.TRANSITION_DURATION=150;d.DEFAULTS={animation:!0,placement:"top",selector:!1,template:'<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>',trigger:"hover focus",title:"",delay:0,html:!1,container:!1,viewport:{selector:"body",padding:0}};d.prototype.init=function(b,a,
g){this.enabled=!0;this.type=b;this.$element=c(a);this.options=this.getOptions(g);this.$viewport=this.options.viewport&&c(c.isFunction(this.options.viewport)?this.options.viewport.call(this,this.$element):this.options.viewport.selector||this.options.viewport);this.inState={click:!1,hover:!1,focus:!1};if(this.$element[0]instanceof document.constructor&&!this.options.selector)throw Error("`selector` option must be specified when initializing "+this.type+" on the window.document object!");b=this.options.trigger.split(" ");
for(a=b.length;a--;)if(g=b[a],"click"==g)this.$element.on("click."+this.type,this.options.selector,c.proxy(this.toggle,this));else if("manual"!=g){var f="hover"==g?"mouseleave":"focusout";this.$element.on(("hover"==g?"mouseenter":"focusin")+"."+this.type,this.options.selector,c.proxy(this.enter,this));this.$element.on(f+"."+this.type,this.options.selector,c.proxy(this.leave,this))}this.options.selector?this._options=c.extend({},this.options,{trigger:"manual",selector:""}):this.fixTitle()};d.prototype.getDefaults=
function(){return d.DEFAULTS};d.prototype.getOptions=function(b){b=c.extend({},this.getDefaults(),this.$element.data(),b);b.delay&&"number"==typeof b.delay&&(b.delay={show:b.delay,hide:b.delay});return b};d.prototype.getDelegateOptions=function(){var b={},a=this.getDefaults();this._options&&c.each(this._options,function(c,f){a[c]!=f&&(b[c]=f)});return b};d.prototype.enter=function(b){var a=b instanceof this.constructor?b:c(b.currentTarget).data("bs."+this.type);a||(a=new this.constructor(b.currentTarget,
this.getDelegateOptions()),c(b.currentTarget).data("bs."+this.type,a));b instanceof c.Event&&(a.inState["focusin"==b.type?"focus":"hover"]=!0);if(a.tip().hasClass("in")||"in"==a.hoverState)a.hoverState="in";else{clearTimeout(a.timeout);a.hoverState="in";if(!a.options.delay||!a.options.delay.show)return a.show();a.timeout=setTimeout(function(){"in"==a.hoverState&&a.show()},a.options.delay.show)}};d.prototype.isInStateTrue=function(){for(var b in this.inState)if(this.inState[b])return!0;return!1};d.prototype.leave=
function(b){var a=b instanceof this.constructor?b:c(b.currentTarget).data("bs."+this.type);a||(a=new this.constructor(b.currentTarget,this.getDelegateOptions()),c(b.currentTarget).data("bs."+this.type,a));b instanceof c.Event&&(a.inState["focusout"==b.type?"focus":"hover"]=!1);if(!a.isInStateTrue()){clearTimeout(a.timeout);a.hoverState="out";if(!a.options.delay||!a.options.delay.hide)return a.hide();a.timeout=setTimeout(function(){"out"==a.hoverState&&a.hide()},a.options.delay.hide)}};d.prototype.show=
function(){var b=c.Event("show.bs."+this.type);if(this.hasContent()&&this.enabled){this.$element.trigger(b);var a=c.contains(this.$element[0].ownerDocument.documentElement,this.$element[0]);if(!b.isDefaultPrevented()&&a){var g=this,b=this.tip(),a=this.getUID(this.type);this.setContent();b.attr("id",a);this.$element.attr("aria-describedby",a);this.options.animation&&b.addClass("fade");var a="function"==typeof this.options.placement?this.options.placement.call(this,b[0],this.$element[0]):this.options.placement,
f=/\s?auto?\s?/i,h=f.test(a);h&&(a=a.replace(f,"")||"top");b.detach().css({top:0,left:0,display:"block"}).addClass(a).data("bs."+this.type,this);this.options.container?b.appendTo(this.options.container):b.insertAfter(this.$element);this.$element.trigger("inserted.bs."+this.type);var f=this.getPosition(),e=b[0].offsetWidth,l=b[0].offsetHeight;if(h){var h=a,j=this.getPosition(this.$viewport),a="bottom"==a&&f.bottom+l>j.bottom?"top":"top"==a&&f.top-l<j.top?"bottom":"right"==a&&f.right+e>j.width?"left":
"left"==a&&f.left-e<j.left?"right":a;b.removeClass(h).addClass(a)}f=this.getCalculatedOffset(a,f,e,l);this.applyPlacement(f,a);a=function(){var a=g.hoverState;g.$element.trigger("shown.bs."+g.type);g.hoverState=null;"out"==a&&g.leave(g)};c.support.transition&&this.$tip.hasClass("fade")?b.one("bsTransitionEnd",a).emulateTransitionEnd(d.TRANSITION_DURATION):a()}}};d.prototype.applyPlacement=function(b,a){var g=this.tip(),f=g[0].offsetWidth,h=g[0].offsetHeight,d=parseInt(g.css("margin-top"),10),e=parseInt(g.css("margin-left"),
10);isNaN(d)&&(d=0);isNaN(e)&&(e=0);b.top+=d;b.left+=e;c.offset.setOffset(g[0],c.extend({using:function(a){g.css({top:Math.round(a.top),left:Math.round(a.left)})}},b),0);g.addClass("in");var e=g[0].offsetWidth,j=g[0].offsetHeight;"top"==a&&j!=h&&(b.top=b.top+h-j);var m=this.getViewportAdjustedDelta(a,b,e,j);m.left?b.left+=m.left:b.top+=m.top;f=(d=/top|bottom/.test(a))?2*m.left-f+e:2*m.top-h+j;h=d?"offsetWidth":"offsetHeight";g.offset(b);this.replaceArrow(f,g[0][h],d)};d.prototype.replaceArrow=function(b,
a,c){this.arrow().css(c?"left":"top",50*(1-b/a)+"%").css(c?"top":"left","")};d.prototype.setContent=function(){var b=this.tip(),a=this.getTitle();b.find(".tooltip-inner")[this.options.html?"html":"text"](a);b.removeClass("fade in top bottom left right")};d.prototype.hide=function(b){function a(){"in"!=g.hoverState&&f.detach();g.$element.removeAttr("aria-describedby").trigger("hidden.bs."+g.type);b&&b()}var g=this,f=c(this.$tip),h=c.Event("hide.bs."+this.type);this.$element.trigger(h);if(!h.isDefaultPrevented())return f.removeClass("in"),
c.support.transition&&f.hasClass("fade")?f.one("bsTransitionEnd",a).emulateTransitionEnd(d.TRANSITION_DURATION):a(),this.hoverState=null,this};d.prototype.fixTitle=function(){var b=this.$element;if(b.attr("title")||"string"!=typeof b.attr("data-original-title"))b.attr("data-original-title",b.attr("title")||"").attr("title","")};d.prototype.hasContent=function(){return this.getTitle()};d.prototype.getPosition=function(b){b=b||this.$element;var a=b[0],g="BODY"==a.tagName,a=a.getBoundingClientRect();
null==a.width&&(a=c.extend({},a,{width:a.right-a.left,height:a.bottom-a.top}));var f=g?{top:0,left:0}:b.offset();b={scroll:g?document.documentElement.scrollTop||document.body.scrollTop:b.scrollTop()};g=g?{width:c(window).width(),height:c(window).height()}:null;return c.extend({},a,b,g,f)};d.prototype.getCalculatedOffset=function(b,a,c,f){return"bottom"==b?{top:a.top+a.height,left:a.left+a.width/2-c/2}:"top"==b?{top:a.top-f,left:a.left+a.width/2-c/2}:"left"==b?{top:a.top+a.height/2-f/2,left:a.left-
c}:{top:a.top+a.height/2-f/2,left:a.left+a.width}};d.prototype.getViewportAdjustedDelta=function(b,a,c,f){var h={top:0,left:0};if(!this.$viewport)return h;var d=this.options.viewport&&this.options.viewport.padding||0,e=this.getPosition(this.$viewport);/right|left/.test(b)?(c=a.top-d-e.scroll,a=a.top+d-e.scroll+f,c<e.top?h.top=e.top-c:a>e.top+e.height&&(h.top=e.top+e.height-a)):(f=a.left-d,a=a.left+d+c,f<e.left?h.left=e.left-f:a>e.right&&(h.left=e.left+e.width-a));return h};d.prototype.getTitle=function(){var b=
this.$element,a=this.options;return b.attr("data-original-title")||("function"==typeof a.title?a.title.call(b[0]):a.title)};d.prototype.getUID=function(b){do b+=~~(1E6*Math.random());while(document.getElementById(b));return b};d.prototype.tip=function(){if(!this.$tip&&(this.$tip=c(this.options.template),1!=this.$tip.length))throw Error(this.type+" `template` option must consist of exactly 1 top-level element!");return this.$tip};d.prototype.arrow=function(){return this.$arrow=this.$arrow||this.tip().find(".tooltip-arrow")};
d.prototype.enable=function(){this.enabled=!0};d.prototype.disable=function(){this.enabled=!1};d.prototype.toggleEnabled=function(){this.enabled=!this.enabled};d.prototype.toggle=function(b){var a=this;b&&(a=c(b.currentTarget).data("bs."+this.type),a||(a=new this.constructor(b.currentTarget,this.getDelegateOptions()),c(b.currentTarget).data("bs."+this.type,a)));b?(a.inState.click=!a.inState.click,a.isInStateTrue()?a.enter(a):a.leave(a)):a.tip().hasClass("in")?a.leave(a):a.enter(a)};d.prototype.destroy=
function(){var b=this;clearTimeout(this.timeout);this.hide(function(){b.$element.off("."+b.type).removeData("bs."+b.type);b.$tip&&b.$tip.detach();b.$tip=null;b.$arrow=null;b.$viewport=null})};var e=c.fn.tooltip;c.fn.tooltip=function(b){return this.each(function(){var a=c(this),g=a.data("bs.tooltip"),f="object"==typeof b&&b;if(g||!/destroy|hide/.test(b))if(g||a.data("bs.tooltip",g=new d(this,f)),"string"==typeof b)g[b]()})};c.fn.tooltip.Constructor=d;c.fn.tooltip.noConflict=function(){c.fn.tooltip=
e;return this}}(jQuery);
+function(c){var d=function(b,a){this.init("popover",b,a)};if(!c.fn.tooltip)throw Error("Popover requires tooltip.js");d.VERSION="3.3.5";d.DEFAULTS=c.extend({},c.fn.tooltip.Constructor.DEFAULTS,{placement:"right",trigger:"click",content:"",template:'<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'});d.prototype=c.extend({},c.fn.tooltip.Constructor.prototype);d.prototype.constructor=d;d.prototype.getDefaults=function(){return d.DEFAULTS};
d.prototype.setContent=function(){var b=this.tip(),a=this.getTitle(),c=this.getContent();b.find(".popover-title")[this.options.html?"html":"text"](a);b.find(".popover-content").children().detach().end()[this.options.html?"string"==typeof c?"html":"append":"text"](c);b.removeClass("fade top bottom left right in");b.find(".popover-title").html()||b.find(".popover-title").hide()};d.prototype.hasContent=function(){return this.getTitle()||this.getContent()};d.prototype.getContent=function(){var b=this.$element,
a=this.options;return b.attr("data-content")||("function"==typeof a.content?a.content.call(b[0]):a.content)};d.prototype.arrow=function(){return this.$arrow=this.$arrow||this.tip().find(".arrow")};var e=c.fn.popover;c.fn.popover=function(b){return this.each(function(){var a=c(this),g=a.data("bs.popover"),f="object"==typeof b&&b;if(g||!/destroy|hide/.test(b))if(g||a.data("bs.popover",g=new d(this,f)),"string"==typeof b)g[b]()})};c.fn.popover.Constructor=d;c.fn.popover.noConflict=function(){c.fn.popover=
e;return this}}(jQuery);
+function(c){function d(a,b){this.$body=c(document.body);this.$scrollElement=c(a).is(document.body)?c(window):c(a);this.options=c.extend({},d.DEFAULTS,b);this.selector=(this.options.target||"")+" .nav li > a";this.offsets=[];this.targets=[];this.activeTarget=null;this.scrollHeight=0;this.$scrollElement.on("scroll.bs.scrollspy",c.proxy(this.process,this));this.refresh();this.process()}function e(a){return this.each(function(){var b=c(this),f=b.data("bs.scrollspy"),e="object"==typeof a&&a;f||b.data("bs.scrollspy",
f=new d(this,e));if("string"==typeof a)f[a]()})}d.VERSION="3.3.5";d.DEFAULTS={offset:10};d.prototype.getScrollHeight=function(){return this.$scrollElement[0].scrollHeight||Math.max(this.$body[0].scrollHeight,document.documentElement.scrollHeight)};d.prototype.refresh=function(){var a=this,b="offset",f=0;this.offsets=[];this.targets=[];this.scrollHeight=this.getScrollHeight();c.isWindow(this.$scrollElement[0])||(b="position",f=this.$scrollElement.scrollTop());this.$body.find(this.selector).map(function(){var a=
c(this),a=a.data("target")||a.attr("href"),e=/^#./.test(a)&&c(a);return e&&e.length&&e.is(":visible")&&[[e[b]().top+f,a]]||null}).sort(function(a,b){return a[0]-b[0]}).each(function(){a.offsets.push(this[0]);a.targets.push(this[1])})};d.prototype.process=function(){var a=this.$scrollElement.scrollTop()+this.options.offset,b=this.getScrollHeight(),c=this.options.offset+b-this.$scrollElement.height(),e=this.offsets,d=this.targets,l=this.activeTarget,j;this.scrollHeight!=b&&this.refresh();if(a>=c)return l!=
(j=d[d.length-1])&&this.activate(j);if(l&&a<e[0])return this.activeTarget=null,this.clear();for(j=e.length;j--;)l!=d[j]&&a>=e[j]&&(void 0===e[j+1]||a<e[j+1])&&this.activate(d[j])};d.prototype.activate=function(a){this.activeTarget=a;this.clear();a=c(this.selector+'[data-target="'+a+'"],'+this.selector+'[href="'+a+'"]').parents("li").addClass("active");a.parent(".dropdown-menu").length&&(a=a.closest("li.dropdown").addClass("active"));a.trigger("activate.bs.scrollspy")};d.prototype.clear=function(){c(this.selector).parentsUntil(this.options.target,
".active").removeClass("active")};var b=c.fn.scrollspy;c.fn.scrollspy=e;c.fn.scrollspy.Constructor=d;c.fn.scrollspy.noConflict=function(){c.fn.scrollspy=b;return this};c(window).on("load.bs.scrollspy.data-api",function(){c('[data-spy="scroll"]').each(function(){var a=c(this);e.call(a,a.data())})})}(jQuery);
+function(c){function d(a){return this.each(function(){var b=c(this),d=b.data("bs.tab");d||b.data("bs.tab",d=new e(this));if("string"==typeof a)d[a]()})}var e=function(a){this.element=c(a)};e.VERSION="3.3.5";e.TRANSITION_DURATION=150;e.prototype.show=function(){var a=this.element,b=a.closest("ul:not(.dropdown-menu)"),e=a.data("target");e||(e=(e=a.attr("href"))&&e.replace(/.*(?=#[^\s]*$)/,""));if(!a.parent("li").hasClass("active")){var d=b.find(".active:last a"),l=c.Event("hide.bs.tab",{relatedTarget:a[0]}),
j=c.Event("show.bs.tab",{relatedTarget:d[0]});d.trigger(l);a.trigger(j);!j.isDefaultPrevented()&&!l.isDefaultPrevented()&&(e=c(e),this.activate(a.closest("li"),b),this.activate(e,e.parent(),function(){d.trigger({type:"hidden.bs.tab",relatedTarget:a[0]});a.trigger({type:"shown.bs.tab",relatedTarget:d[0]})}))}};e.prototype.activate=function(a,b,d){function k(){l.removeClass("active").find("> .dropdown-menu > .active").removeClass("active").end().find('[data-toggle="tab"]').attr("aria-expanded",!1);
a.addClass("active").find('[data-toggle="tab"]').attr("aria-expanded",!0);j?(a[0].offsetWidth,a.addClass("in")):a.removeClass("fade");a.parent(".dropdown-menu").length&&a.closest("li.dropdown").addClass("active").end().find('[data-toggle="tab"]').attr("aria-expanded",!0);d&&d()}var l=b.find("> .active"),j=d&&c.support.transition&&(l.length&&l.hasClass("fade")||!!b.find("> .fade").length);l.length&&j?l.one("bsTransitionEnd",k).emulateTransitionEnd(e.TRANSITION_DURATION):k();l.removeClass("in")};var b=
c.fn.tab;c.fn.tab=d;c.fn.tab.Constructor=e;c.fn.tab.noConflict=function(){c.fn.tab=b;return this};var a=function(a){a.preventDefault();d.call(c(this),"show")};c(document).on("click.bs.tab.data-api",'[data-toggle="tab"]',a).on("click.bs.tab.data-api",'[data-toggle="pill"]',a)}(jQuery);
+function(c){function d(a){return this.each(function(){var b=c(this),f=b.data("bs.affix"),d="object"==typeof a&&a;f||b.data("bs.affix",f=new e(this,d));if("string"==typeof a)f[a]()})}var e=function(a,b){this.options=c.extend({},e.DEFAULTS,b);this.$target=c(this.options.target).on("scroll.bs.affix.data-api",c.proxy(this.checkPosition,this)).on("click.bs.affix.data-api",c.proxy(this.checkPositionWithEventLoop,this));this.$element=c(a);this.pinnedOffset=this.unpin=this.affixed=null;this.checkPosition()};
e.VERSION="3.3.5";e.RESET="affix affix-top affix-bottom";e.DEFAULTS={offset:0,target:window};e.prototype.getState=function(a,b,c,e){var d=this.$target.scrollTop(),l=this.$element.offset(),j=this.$target.height();if(null!=c&&"top"==this.affixed)return d<c?"top":!1;if("bottom"==this.affixed)return null!=c?d+this.unpin<=l.top?!1:"bottom":d+j<=a-e?!1:"bottom";var m=null==this.affixed,l=m?d:l.top;return null!=c&&d<=c?"top":null!=e&&l+(m?j:b)>=a-e?"bottom":!1};e.prototype.getPinnedOffset=function(){if(this.pinnedOffset)return this.pinnedOffset;
this.$element.removeClass(e.RESET).addClass("affix");var a=this.$target.scrollTop();return this.pinnedOffset=this.$element.offset().top-a};e.prototype.checkPositionWithEventLoop=function(){setTimeout(c.proxy(this.checkPosition,this),1)};e.prototype.checkPosition=function(){if(this.$element.is(":visible")){var a=this.$element.height(),b=this.options.offset,d=b.top,h=b.bottom,k=Math.max(c(document).height(),c(document.body).height());"object"!=typeof b&&(h=d=b);"function"==typeof d&&(d=b.top(this.$element));
"function"==typeof h&&(h=b.bottom(this.$element));b=this.getState(k,a,d,h);if(this.affixed!=b){null!=this.unpin&&this.$element.css("top","");var d="affix"+(b?"-"+b:""),l=c.Event(d+".bs.affix");this.$element.trigger(l);if(l.isDefaultPrevented())return;this.affixed=b;this.unpin="bottom"==b?this.getPinnedOffset():null;this.$element.removeClass(e.RESET).addClass(d).trigger(d.replace("affix","affixed")+".bs.affix")}"bottom"==b&&this.$element.offset({top:k-a-h})}};var b=c.fn.affix;c.fn.affix=d;c.fn.affix.Constructor=
e;c.fn.affix.noConflict=function(){c.fn.affix=b;return this};c(window).on("load",function(){c('[data-spy="affix"]').each(function(){var a=c(this),b=a.data();b.offset=b.offset||{};null!=b.offsetBottom&&(b.offset.bottom=b.offsetBottom);null!=b.offsetTop&&(b.offset.top=b.offsetTop);d.call(a,b)})})}(jQuery);
