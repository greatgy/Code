[#ftl]
[#if createMillis??]
g.token = ${createMillis};
[/#if]
var g = {};
g.base = "${base}";
g.basejs = "${basejs}"
g.baseimg = "${baseimg}"
[#-- g.base网站主域名 --]
g.baseuri = "[#if user?? && user.domain?? && user.domain?length > 0]${user.domain}[#else]${base}[/#if]";
[#-- g.baseuri：二级域名时网站ajax地址 --]
[#-- 当使用一般没有domain的user的方式访问有domain的user主页时，以上g.baseuri会与浏览网页不一致，所以使用js获取地址 --]
var i = window.location.href.indexOf("genius.com");
if(i > 0) {
g.baseuri = window.location.href.substring(0, i + "genius.com".length);
}
