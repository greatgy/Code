function adaptWXphoto(a){$(a).children().find("img").each(function(){src=$(this).attr("src");"undefined"!=src&&(void 0!=src&&-1!=src.indexOf("qpic.cn"))&&(src=src.replace("tp=webp","tp=png"),$(this).attr("src",src))})};