[#ftl]
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>账号管理--重置支付密码</title>
    <meta name="Keywords" content="会员中心账号管理，重置支付密码" />
    <meta name="Description" content="会员中心账号管理，重置支付密码" />
    <link rel="stylesheet" type="text/css" href="${basecss}/css/default/account.css">
<script type="text/javascript" src="${basejs}/js/libs/validator-1.0.0.js"></script>
<script type="text/javascript">
<!--
    
    $(function(){
            var regs = {
            isnum : /^.*[0-9]+.*$/,
            islish: /^.*[A-Za-z]+.*$/,
            issign: /^.*[_\!\.@#\$%\^&\*\(\)\[\]\\?\\\/\|\-~`\+\=\,\r\n\:\'\"。，；……！￥\{\}·]+.*$/
            };
            $("#txtnewpaypwd").keyup(function(){
                var count = 0;
                for(var b in regs){
                        if(regs[b].test($(this).val())) count++;
                    }
                    $(".safe span").removeClass("saferange");
                    switch(count)
                    {
                        case 1:
                            $(".safe span:eq(0)").addClass("saferange");
                            break;
                        case 2:
                            $(".safe span:eq(1)").addClass("saferange");
                            break;
                        case 3:
                            $(".safe span:eq(2)").addClass("saferange");
                            break;
                    }
            });
            
            if("${failed}" == "0") {
                $("#errtxtnewpaypwd").removeClass("hd");
            }
            
            $('input[name="newpaypwd"]').keydown(function(){  
            if(event.keyCode==13){
                $(".submit").click();
                return false;
            }  
            });
            $('input[name="newpaypwd2"]').keydown(function(){  
                if(event.keyCode==13){
                    $(".submit").click();
                    return false;
                }  
            });
    });

    function registerValidHandler(){
        var pwd = $("#txtnewpaypwd").val();
        var pwd2 = $("#txtnewpaypwd2").val();
        if(pwd != pwd2 || pwd2 == ""){
            return false;
        } else {
            return true;
        }
    }
    
    function submitform(){
        $("#resetpaypwdform").submit();
    }
//-->
</script>
</head>
<body>
    <div class="wraper">
        <div class="container">
            <div class="containerleft [#if !member??]containerleftgreen[/#if]">
                <h2>账号管理</h2>
                <ul class="accountlist">
                    <li class="accountitem01">
                        <a href="${base}/my/user/info"><span>个人信息</span></a>
                    </li>
                    <li class="accountitem02">
                        <a href="${base}/my/user/setpwd"><span>修改登录密码</span></a>
                    </li>
                    <li class="accountitem03 activeli">
                        <a href="${base}/my/user/setpaypwd"><span>修改支付密码</span></a>
                    </li>
                    [#-- 
                    <li class="accountitem04">
                        <a href="${base}/my/user/setemail"><span>修改绑定邮箱</span></a>
                    </li> --]
                    <li class="accountitem05">
                        <a href="${base}/my/user/safeqa"><span>账户保护</span></a>
                    </li>
                    <li class="accountitem06">
                        <a href="${base}/my/user/news"><span>我的消息</span></a>
                    </li>
                </ul>
            </div>
            <div class="containerright  [#if !member??]containerrightgreen[/#if] ">
                <h2>重置支付密码</h2>
                [#if success??]
	                <div action="" class="logincode">
	                    <div class="setmid">
	                        <img src="${baseimg}/imgs/default/success.png" alt="" />
	                        <p class="tip">您的支付密码已经设置成功！</p>
	                        <a class="submit" href="${base}/my/user/info">确定</a>
	                    </div>
	                </div>
                [#elseif not_exist??]
	                <div action="" class="logincode">
	                    <div class="setmid">
	                        <img src="${baseimg}/imgs/default/fail.png" alt="" />
	                        <p class="tip">您好！该链接已失效！请重新申请支付密码！</p>
	                        <a class="submit" href="${base}/my/user/setpaypwd?find=true">重新申请</a>
	                    </div>
	                </div>                
                [#else]
                <form id="resetpaypwdform" class="logincode" action="${base}/my/user/resetpaypwd" method="post">
                    <ul class="changelogincode">
                        <li>
                            <span>支付密码</span>
                            <input id="txtnewpaypwd" name="newpaypwd" type="password" customreg="^.{6,16}$|请填写6-16位字符" isnotempty="新支付密码不能为空！"  />
                            <div class="msg-box">
                            <div id="focustxtnewpaypwd" class="checknote hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span>请输入新支付密码</span></div>
                            <div id="errtxtnewpaypwd" class="checknote checknoteno hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtnewpaypwd"></span></div>
                            <div id="oktxtnewpaypwd" class="checknote checknoteyes hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span>输入正确</span></div>
                            </div>
                        </li>
                        <div class="safe">
                            <span>弱</span>
                            <span>中</span>
                            <span>强</span>
                        </div>
                        <li>
                            <span>确认支付密码</span>
                            <input id="txtnewpaypwd2" name="newpaypwd2" type="password"  isnotempty="新支付密码不能为空！" customvalid="registerValidHandler()|两次输入的密码不一致" />
                            <div class="msg-box">
                            <div id="focustxtnewpaypwd2" class="checknote hd"><img src="${baseimg}/imgs/default/light.png" alt="" /><span>请再次输入新支付密码</span></div>
                            <div id="errtxtnewpaypwd2" class="checknote checknoteno hd"><img src="${baseimg}/imgs/default/waring.png" alt="" /><span id="errmsgtxtnewpaypwd2"></span></div>
                            <div id="oktxtnewpaypwd2" class="checknote checknoteyes hd"><img src="${baseimg}/imgs/default/yes.png" alt="" /><span>输入正确</span></div>
                            </div>
                        </li>
                    </ul>
                    <a class="submit" onclick="submitform()" href="javascript:;">确认修改</a>
                </form>
                [/#if]
            </div>
        </div>
    </div>
</body>
</html>