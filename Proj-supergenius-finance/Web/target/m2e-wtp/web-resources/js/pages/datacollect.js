/*数据收集字段：domain:web服务器的主机名   url:当前URL title:标题  referrer：源地址 sh:高度 sw：宽度 cd:位色彩 lang：语言
			 time:访问页面时长   strStart：访问页面开始时间  strEnd:访问页面结束时间  useruid:用户uid username:用户名
			 islogin： 是否登录 isMember：是否是会员
			 
*/
var dataparams = {};
$(function(){
	$("#testbutton").click(function(){
		alert(randomSid(19));
	})
	
	$("body").click(function(){revive();});
	$("body").mousedown(function(){revive();});
	$("body").mouseup(function(){revive();});
	$("body").mousemove(function(){revive();});
	//(Firefox)
	$("body").bind("DOMMouseScroll", function() {revive();});
	//(IE,Google)
	$("body").bind("mousewheel",function(){revive();});
	$("body").keydown(function(e){revive();}); 
	$("body").keyup(function(e){revive();}); 
	$("body").keypress(function(e){revive();});
	dataparams.prize = 0;
	dataparams.collect = 0;
	dataparams.comment = 0;
})

//收集固定数据
function staticdata() {
    //alert(1);
    //Document对象数据
    if (document) {
        dataparams.domain = document.domain || '';
        dataparams.url = document.URL || '';
        dataparams.title = document.title || '';
        dataparams.referrer = document.referrer || '';
    }
    //Window对象数据
    if (window && window.screen) {
        dataparams.sh = window.screen.height || 0;
        dataparams.sw = window.screen.width || 0;
        dataparams.cd = window.screen.colorDepth || 0;
    }
    //navigator对象数据
    if (navigator) {
        dataparams.lang = navigator.language || '';
    }
    
    //用户相关数据
    dataparams.username = me.showname || "游客";
    if (me.uid != "" && me.uid != undefined && me.uid != null) {
    	dataparams.islogin = 1;
    	dataparams.useruid = me.uid;
    	dataparams.isMember = me.isuser;
    } else {
    	dataparams.islogin = 0;
    	var tempCount = document.cookie.indexOf("sid=");
    	var sid = "";
    	if (tempCount != -1) {
    		sid = document.cookie.substring(tempCount + 4);
    		tempCount = sid.indexOf(";")
    		if (tempCount != -1) {
        		dataparams.useruid = sid.substring(0,tempCount);
        	} else {
        		dataparams.useruid = sid;
        	} 
    	} else {
    		dataparams.useruid  = randomSid(19);
    	}
    }
    
    //文章相关数据
    dataparams.articleuid = fromuid;
    dataparams.articlecid = cid;
};

//记录访问时长
var start = new Date();
var strStart = start.getFullYear()+"-"+(start.getMonth()+1)+"-"+start.getDate()+" "+ start.getHours()+":"+start.getMinutes()+":"+start.getSeconds();
var len = 0;
var end;
var status = "in";
var second = 30;
function revive(){
    if(status == "out"){
	    start = new Date();
	    status = "in";
    }
    second = 30;
}
window.setInterval(function(){
	second -= 1;
    if(0 == second){
        end = new Date();
        len += (end.getTime() - start.getTime())/1000;
        status = "out";
    }
},1000);

//拼接参数串
function append(){
    var args = '';
    for (var i in dataparams) {
        // alert(i);
        if (args != '') {
            args += '&';
        }
        args += i + '=' + dataparams[i];
    }
    return args;
};

//通过Image对象请求后端脚本
function submitdata(args){
	var img = new Image(1, 1);
    console.log(args);
    console.log(encodeURIComponent(args));
    var src = g.base + '/log.gif?args=' + encodeURIComponent(args);
    //alert(src);
    img.src = src;
};


//监听关闭窗口，刷新，后退，跳转等事件
window.onbeforeunload = function(){
	staticdata();
	end = new Date();
	var strEnd = end.getFullYear()+"-"+(end.getMonth()+1)+"-"+end.getDate()+" "+end.getHours()+":"+end.getMinutes()+":"+end.getSeconds();
	len += (end.getTime() - start.getTime())/1000;
	dataparams.time = len;
	dataparams.strStart = strStart;
	dataparams.strEnd = strEnd;
	var args = append();
	submitdata(args);
}

//生成随机sid
function randomSid(len) {
	　　len = len || 19;
	　　var $chars = 'abcdefghijklmnopqrstuvwxyz12345678';
	　　var maxPos = $chars.length;
	　　var sid = '';
	　　for (i = 0; i < len; i++) {
	　　　　sid += $chars.charAt(Math.floor(Math.random() * maxPos));
	　　}
	　　return sid;
}