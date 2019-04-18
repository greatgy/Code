$(function () {
	 var countries= [   
	       { "code":"ao", "name":"安哥拉" },   
	       { "code":"af", "name":"阿富汗" },   
	       { "code":"al", "name":"阿尔巴尼亚" },   
	       { "code":"dz", "name":"阿尔及利亚" },   
	       { "code":"ad", "name":"安道尔共和国" },   
	       { "code":"ai", "name":"安圭拉岛" },   
	       { "code":"ag", "name":"安提瓜和巴布达" },   
	       { "code":"ar", "name":"阿根廷" },   
	       { "code":"am", "name":"亚美尼亚" },   
	       { "code":"au", "name":"澳大利亚" },   
	       { "code":"at", "name":"奥地利" },   
	       { "code":"az", "name":"阿塞拜疆" },   
	       { "code":"bs", "name":"巴哈马" },   
	       { "code":"bh", "name":"巴林" },   
	       { "code":"bd", "name":"孟加拉国" },   
	       { "code":"bb", "name":"巴巴多斯" },   
	       { "code":"by", "name":"白俄罗斯" },   
	       { "code":"be", "name":"比利时" },   
	       { "code":"bz", "name":"伯利兹" },   
	       { "code":"bj", "name":"贝宁" },   
	       { "code":"bm", "name":"百慕大群岛" },   
	       { "code":"bo", "name":"玻利维亚" },   
	       { "code":"bw", "name":"博茨瓦纳" },   
	       { "code":"br", "name":"巴西" },   
	       { "code":"bn", "name":"文莱" },   
	       { "code":"bg", "name":"保加利亚" },   
	       { "code":"bf", "name":"布基纳法索" },   
	       { "code":"mm", "name":"缅甸" },   
	       { "code":"bi", "name":"布隆迪" },   
	       { "code":"cm", "name":"喀麦隆" },   
	       { "code":"ca", "name":"加拿大" },   
	       { "code":"cf", "name":"中非共和国" },   
	       { "code":"td", "name":"乍得" },   
	       { "code":"cl", "name":"智利" },   
	       { "code":"cn", "name":"中国" },   
	       { "code":"co", "name":"哥伦比亚" },   
	       { "code":"cg", "name":"刚果" },   
	       { "code":"ck", "name":"库克群岛" },   
	       { "code":"cr", "name":"哥斯达黎加" },   
	       { "code":"cu", "name":"古巴" },   
	       { "code":"cy", "name":"塞浦路斯" },   
	       { "code":"cz", "name":"捷克" },   
	       { "code":"dk", "name":"丹麦" },   
	       { "code":"dj", "name":"吉布提" },   
	       { "code":"do", "name":"多米尼加共和国" },   
	       { "code":"ec", "name":"厄瓜多尔" },   
	       { "code":"eg", "name":"埃及" },   
	       { "code":"sv", "name":"萨尔瓦多" },   
	       { "code":"ee", "name":"爱沙尼亚" },   
	       { "code":"et", "name":"埃塞俄比亚" },   
	       { "code":"fj", "name":"斐济" },   
	       { "code":"fi", "name":"芬兰" },   
	       { "code":"fr", "name":"法国" },   
	       { "code":"gf", "name":"法属圭亚那" },   
	       { "code":"ga", "name":"加蓬" },   
	       { "code":"gm", "name":"冈比亚" },   
	       { "code":"ge", "name":"格鲁吉亚" },   
	       { "code":"de", "name":"德国" },   
	       { "code":"gh", "name":"加纳" },   
	       { "code":"gi", "name":"直布罗陀" },   
	       { "code":"gr", "name":"希腊" },   
	       { "code":"gd", "name":"格林纳达" },   
	       { "code":"gu", "name":"关岛" },   
	       { "code":"gt", "name":"危地马拉" },   
	       { "code":"gn", "name":"几内亚" },   
	       { "code":"gy", "name":"圭亚那" },   
	       { "code":"ht", "name":"海地" },   
	       { "code":"hn", "name":"洪都拉斯" },   
	       { "code":"hk", "name":"香港" },   
	       { "code":"hu", "name":"匈牙利" },   
	       { "code":"is", "name":"冰岛" },   
	       { "code":"in", "name":"印度" },   
	       { "code":"id", "name":"印度尼西亚" },   
	       { "code":"ir", "name":"伊朗" },   
	       { "code":"iq", "name":"伊拉克" },   
	       { "code":"ie", "name":"爱尔兰" },   
	       { "code":"il", "name":"以色列" },   
	       { "code":"it", "name":"意大利" },   
	       { "code":"jm", "name":"牙买加" },   
	       { "code":"jp", "name":"日本" },   
	       { "code":"jo", "name":"约旦" },   
	       { "code":"kh", "name":"柬埔寨" },   
	       { "code":"kz", "name":"哈萨克斯坦" },   
	       { "code":"ke", "name":"肯尼亚" },   
	       { "code":"kr", "name":"韩国" },   
	       { "code":"kw", "name":"科威特" },   
	       { "code":"kg", "name":"吉尔吉斯坦" },   
	       { "code":"la", "name":"老挝" },   
	       { "code":"lv", "name":"拉脱维亚" },   
	       { "code":"lb", "name":"黎巴嫩" },   
	       { "code":"ls", "name":"莱索托" },   
	       { "code":"lr", "name":"利比里亚" },   
	       { "code":"ly", "name":"利比亚" },   
	       { "code":"li", "name":"列支敦士登" },   
	       { "code":"lt", "name":"立陶宛" },   
	       { "code":"lu", "name":"卢森堡" },   
	       { "code":"mo", "name":"澳门" },   
	       { "code":"mg", "name":"马达加斯加" },   
	       { "code":"mw", "name":"马拉维" },   
	       { "code":"my", "name":"马来西亚" },   
	       { "code":"mv", "name":"马尔代夫" },   
	       { "code":"ml", "name":"马里" },   
	       { "code":"mt", "name":"马耳他" },   
	       { "code":"mu", "name":"毛里求斯" },   
	       { "code":"mx", "name":"墨西哥" },   
	       { "code":"md", "name":"摩尔多瓦" },   
	       { "code":"mc", "name":"摩纳哥" },   
	       { "code":"mn", "name":"蒙古" },   
	       { "code":"ms", "name":"蒙特塞拉特岛" },   
	       { "code":"ma", "name":"摩洛哥" },   
	       { "code":"mz", "name":"莫桑比克" },   
	       { "code":"na", "name":"纳米比亚" },   
	       { "code":"nr", "name":"瑙鲁" },   
	       { "code":"np", "name":"尼泊尔" },   
	       { "code":"nl", "name":"荷兰" },   
	       { "code":"nz", "name":"新西兰" },   
	       { "code":"ni", "name":"尼加拉瓜" },   
	       { "code":"ne", "name":"尼日尔" },   
	       { "code":"ng", "name":"尼日利亚" },   
	       { "code":"kp", "name":"朝鲜" },   
	       { "code":"no", "name":"挪威" },   
	       { "code":"om", "name":"阿曼" },   
	       { "code":"pk", "name":"巴基斯坦" },   
	       { "code":"pa", "name":"巴拿马" },   
	       { "code":"pg", "name":"巴布亚新几内亚" },   
	       { "code":"py", "name":"巴拉圭" },   
	       { "code":"pe", "name":"秘鲁" },   
	       { "code":"ph", "name":"菲律宾" },   
	       { "code":"pl", "name":"波兰" },   
	       { "code":"pf", "name":"法属玻利尼西亚" },   
	       { "code":"pt", "name":"葡萄牙" },   
	       { "code":"pr", "name":"波多黎各" },   
	       { "code":"qa", "name":"卡塔尔" },   
	       { "code":"ro", "name":"罗马尼亚" },   
	       { "code":"ru", "name":"俄罗斯" },   
	       { "code":"lc", "name":"圣卢西亚" },   
	       { "code":"vc", "name":"圣文森特岛" },   
	       { "code":"sm", "name":"圣马力诺" },   
	       { "code":"st", "name":"圣多美和普林西比" },   
	       { "code":"sa", "name":"沙特阿拉伯" },   
	       { "code":"sn", "name":"塞内加尔" },   
	       { "code":"sc", "name":"塞舌尔" },   
	       { "code":"sl", "name":"塞拉利昂" },   
	       { "code":"sg", "name":"新加坡" },   
	       { "code":"sk", "name":"斯洛伐克" },   
	       { "code":"si", "name":"斯洛文尼亚" },   
	       { "code":"sb", "name":"所罗门群岛" },   
	       { "code":"so", "name":"索马里" },   
	       { "code":"za", "name":"南非" },   
	       { "code":"es", "name":"西班牙" },   
	       { "code":"lk", "name":"斯里兰卡" },   
	       { "code":"sd", "name":"苏丹" },   
	       { "code":"sr", "name":"苏里南" },   
	       { "code":"sz", "name":"斯威士兰" },   
	       { "code":"se", "name":"瑞典" },   
	       { "code":"ch", "name":"瑞士" },   
	       { "code":"sy", "name":"叙利亚" },   
	       { "code":"tw", "name":"台湾省" },   
	       { "code":"tj", "name":"塔吉克斯坦" },   
	       { "code":"tz", "name":"坦桑尼亚" },   
	       { "code":"th", "name":"泰国" },   
	       { "code":"tg", "name":"多哥" },   
	       { "code":"to", "name":"汤加" },   
	       { "code":"tt", "name":"特立尼达和多巴哥" },   
	       { "code":"tn", "name":"突尼斯" },   
	       { "code":"tr", "name":"土耳其" },   
	       { "code":"tm", "name":"土库曼斯坦" },   
	       { "code":"ug", "name":"乌干达" },   
	       { "code":"ua", "name":"乌克兰" },   
	       { "code":"ae", "name":"阿拉伯联合酋长国" },   
	       { "code":"gb", "name":"英国" },   
	       { "code":"us", "name":"美国" },   
	       { "code":"uy", "name":"乌拉圭" },   
	       { "code":"uz", "name":"乌兹别克斯坦" },   
	       { "code":"ve", "name":"委内瑞拉" },   
	       { "code":"vn", "name":"越南" },   
	       { "code":"ye", "name":"也门" },   
	       { "code":"zw", "name":"津巴布韦" },    
	       { "code":"zm", "name":"赞比亚" }   
	  ]


    var countryInput = $(document).find('.countrypicker');
    var countryList = "";


    //set defaults
    for (i = 0; i < countryInput.length; i++) {

        //check if flag
        flag = countryInput.eq(i).data('flag');
        
        if (flag) {
            countryList = "";
            
            //for each build list with flag
            $.each(countries, function (index, country) {
                var flagIcon = baseimg+"/imgs/flags/" + country.code + ".png";
                countryList += "<option data-country-code='" + country.code + "' data-tokens='" + country.code + " " + country.name + "' style='padding-left:25px; background-position: 4px 7px; background-image:url(" + flagIcon + ");background-repeat:no-repeat;' value='" + country.name + "'>" + country.name + "</option>";
            });

            //add flags to button
            countryInput.eq(i).on('loaded.bs.select', function (e) {
                var button = $(this).closest('.btn-group').children('.btn');
                button.hide();
                var def = $(this).find(':selected').data('country-code');
                var flagIcon = baseimg+"/imgs/flags/" + def + ".png";
                button.css("background-size", '20px');
                button.css("background-position", '10px 9px');
                button.css("padding-left", '40px');
                button.css("background-repeat", 'no-repeat');
                button.css("background-image", "url('" + flagIcon + "'");
                button.show();
            });

            //change flag on select change
            countryInput.eq(i).on('change', function () {
                button = $(this).closest('.btn-group').children('.btn');
                def = $(this).find(':selected').data('country-code');
                flagIcon = baseimg+"/imgs/flags/" + def + ".png";
                button.css("background-size", '20px');
                button.css("background-position", '10px 9px');
                button.css("padding-left", '40px');
                button.css("background-repeat", 'no-repeat');
                button.css("background-image", "url('" + flagIcon + "'");

            });
        }else{
            countryList ="";
            
            //for each build list without flag
            $.each(countries, function (index, country) {
                countryList += "<option data-country-code='" + country.code + "' data-tokens='" + country.code + " " + country.name + "' value='" + country.name + "'>" + country.name + "</option>";
            });
            
            
        }
        
         //append country list
        countryInput.eq(i).html(countryList);


        //check if default
        def = countryInput.eq(i).data('default');
        //if there's a default, set it
        if (def) {
            countryInput.eq(i).val(def);
        }


    }









});