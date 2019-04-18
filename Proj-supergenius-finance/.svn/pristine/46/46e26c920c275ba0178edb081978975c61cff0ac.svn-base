$(function(){
	$(".mf_a").hover(function() {
      $(this).find(".mf_bg").css({"top":"0px","background":"#000","opacity":"0.5"})
    }, function() {
       $(this).find(".mf_bg").css({"top":"110px","background":"#000","opacity":"0.5"})
    });
})
function setTab(name,cursel,n){
    for(i=1;i<=n;i++){    
        var menu=document.getElementById(name+i);
        var con=document.getElementById("list_"+name+"_"+i);
        menu.className=i==cursel?"current":"";
        con.style.display=i==cursel?"block":"none";
    }
}
