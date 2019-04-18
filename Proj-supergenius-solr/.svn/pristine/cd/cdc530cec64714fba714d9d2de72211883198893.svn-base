/**
 * 搜索客户端
 */
;function SearchClient(url, opts) {
    var defaultOpts = {
    		start: 0, //从第几条开始
    		rows: 10, //每页有多少条
    		"hl": true, //&hl=true&hl.fl=title
    		"hl.fl": "*",
    		"hl.simple.pre": "<em>",
    		"hl.simple.post": "</em>",
    		fq: "", //filter query
    		//fl: " ", //field list 显示哪些字段,逗号分割，如id,sales_price:price,secret_sauce:prod(price,popularity),why_score:[explainstyle=nl]
    		sort: "", //排序，如：inStock desc,price asc
    		table: "*" //搜索的是哪个表
    		//"q.op": "AND" //default operator for query expressions, Possible values are "AND" or "OR"
    		//"df": ""
    };
    var opts = opts || defaultOpts;
    var client = {};

    /*------client自定义方法--------*/
    /**
     * 搜索
     * query举例：(title:谷歌 AND title:三星) OR content:巨头
     */
    client.search = function(query, args, cbFun) {
    	$.get(url + "?q=" + query, getParams(opts, args), cbFun);
    	/*$.ajax({
    		type:"GET",
    		url:url + "?q=" + query,
    		data:getParams(opts, args),
    		success:cbFun
    	});*/
    };
    
    /**
     * 渲染html
     */
	client.render = function (data, source, target, setDataDocFun) {
		var template = Handlebars.compile($(source).html());
		data = client.initData(data, setDataDocFun);
		var html = template(data);
		$(target).append(html);
	}
	
	/**
	* 渲染html后的处理，如修改或重新加载数据等等
	*/
	client.renderAfter = function () {
		
	}
	
	/**
	 * 获得处理后的数据
	 */
	client.initData = function (j, setDataDocFun) {
		var data = {};
		data.base = g.base;
		data.basejs = g.basejs;
		data.baseimg = g.baseimg;
		data.webimg = g.webimg;
		data.userimg = g.userimg;
		client.setDataDocs(data, j, setDataDocFun);
		return data;
	}
	
	/**
	 * 设置data.docs
	 */
	client.setDataDocs = function (data, j, setDataDocFun) {
		data.docs = j.response.docs;
		for(var i in data.docs) {
			var doc = data.docs[i];
			//setDataDocHighlight(doc, j.highlighting[doc[opts.idField]]);
			//setDataDocFun(doc, data)
		}
		return data;
	}

	/**
	 * 判断字符串是否以指定字符结尾
	 * return true or false
	 */
	String.prototype.endWith = function(str) {
		var reg = new RegExp(str + "$"); 
		return reg.test(this); 
	}
	
	/**
	 * 返回一个对象，arg0是默认值，arg1是替换的新值
	 * @param arg0
	 * @param arg1
	 * @returns
	 */
	
	function getParams(arg0, arg1) {
		var p = {};
		for(var k in arg0) {
    		p[k] = arg0[k];
    	}
		for(var k in arg1) {
			p[k] = arg1[k];
    	}
		//console.log(p.table);
		p.fq += " _table_:" + p.table;
		
		delete p.table;
		delete p.idField;
		return p;
	}
	
	/**
	 * 设置高亮
	 */
	function setDataDocHighlight(doc, other) {
		for(var k in other) {
			doc[k] = other[k][0];
		}
	}
	
    return client;
};
