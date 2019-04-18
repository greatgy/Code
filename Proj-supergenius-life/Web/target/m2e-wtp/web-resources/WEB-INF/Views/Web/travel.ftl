[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生——行万里路'}</title>
	<link rel="stylesheet" href="${basecss}/css/default/index.css">
	<link rel="stylesheet" type="text/css" href="${basejs}/css/default/jquery.autocomplete.css" />
	<script src="${basejs}/js/libs3/echarts.min.js"></script>
	<script src="${basejs}/js/pages/world.js"></script>
	<script src="${basejs}/js/pages/china.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script class="resources library" src="${basejs}/js/pages/area.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/localdata.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/jquery.autocomplete.js"></script>

	<script type="text/javascript">
		$(document).ready(function(){ 
		
		  $("#searchcity").autocomplete(objects, {
            minChars: 0, //表示在自动完成激活之前填入的最小字符
            max: 5, //表示列表里的条目数
            width:179,//下拉框的高度
            autoFill: false, //表示自动填充
            mustMatch: false, //表示必须匹配条目,文本框里输入的内容,必须是data参数里的数据,如果不匹配,文本框就被清空
            matchContains: true, //表示包含匹配,相当于模糊匹配
            scrollHeight: 200, //表示列表显示高度,默认高度为180

            formatItem: function (row) {
                return row.name;
            },
            formatMatch: function (row) {
                return row.name;
            },
            formatResult: function (row) {
                return row.value;
            }
        }).result(function(event,data,formatted){
        	more = 3;
			area(this,more);
            [#--  pagenum = 1;
            more = 3;
			var url = "${base}/ajax/article/area";
			var provinces = data.value;
			if(provinces.length > 2) {
				provinces = provinces.substring(0,provinces.length - 1);
			}
			var json = {};
			json.cid='${cid?c}';
			json.pagenum=pagenum;
			json.province = provinces;
			$.get(url, json,function(data){
				if(data != ''){
					pagenum +=1;
					$(".tabBox").html(data);
				}else {
					$(".tabBox").html('<div class="nodata">暂无足迹</div>');
				}
			});--]
        });
		  
		  
	　　　　$('#s_province').change(function(){  
				more = 4;
				pagenum = 1;
				var obj = $(".areas");
				$(obj).addClass("curjudge").siblings().removeClass("curjudge");
				var provinces = $("#s_province").val();
				if(provinces.length > 2) {
					provinces = provinces.substring(0,provinces.length - 1);
				}
				var url = "${base}/ajax/article/area";
				var json = {};
				json.cid='${cid?c}';
				json.pagenum=pagenum;
				json.province = provinces;
				$.get(url, json,function(data){
					if(data != ''){
						pagenum +=1;
						$(".tabBox").html(data);
					} else {
						$(".tabBox").html('<div class="nodata">暂无足迹</div>');
					}
				});
	　　　})
			$('#s_city').change(function(){  
				more = 4;
	　　　　　　	var obj = $(".areas");
				pagenum = 1;
				$(obj).addClass("curjudge").siblings().removeClass("curjudge");
				var url = "${base}/ajax/article/area";
				var provinces = $("#s_province").val();
				if(provinces.length > 2) {
					provinces = provinces.substring(0,provinces.length - 1);
				}
				var citys = $("#s_city").val();
				if(citys.length > 2) {
					citys = citys.substring(0,citys.length - 1);
				}
				var json = {};
				json.cid='${cid?c}';
				json.pagenum=pagenum;
				json.province = provinces;
				json.city = citys;
				$.get(url, json,function(data){
					if(data != ''){
						pagenum +=1;
						$(".tabBox").html(data);
					} else {
						$(".tabBox").html('<div class="nodata">暂无足迹</div>');
					}
				});
		})
	}) 
	</script>
</head>
<body>
	<div id="map-world-wrap" class="map hd"></div>
	<div id="map-china-wrap" class="map"></div>
	<div class="footprint">
		<ul class="judgement">
			<li onclick="lastArticles(this);" id="lastest" class="curjudge">最新</li>
			<li onclick="hotArticles(this);">最热</li>
			<li class="areas" onclick="area(this,1);">地区</li>
		</ul>
		[#if me??]
			<a class="publishBtn" href="${base}/addproblem/${cid?c}">添加足迹</a>
		[#else]
			<a class="publishBtn pop-up" >添加足迹</a>
		[/#if]
			<div class="places airechose hd">
				<form action="">
					<div class="selectPlace">
						<label for="">按省份选择：</label>
						<select id="s_province" name="s_province"></select>
						<select id="s_city" name="s_city" ></select>
					</div>
					<div class="searchPlace">
						<label for="">直接搜索：</label>
						<input type="text" name="" id="searchcity" placeholder="请输入城市中文或拼音" />
					</div>
				</form>
				[#if hotplace??]
				<div class="hotPlace">
					<label>热门城市：</label>
					<ul class="placeList">
						[#list  hotplace as place]
							<li class = "hotplace" vlue="${place}"><a href="#" onclick="area(this,5);" >${place}</a></li>
						[/#list]
					</ul>
				</div>
				[/#if]
			</div>
		<ul class="tabBox footbox" id="newList">
		</ul>
	</div>
	<script>
		var maxworld = ${maxWorld};
		var maxchina = '${maxChina}';
		var worldData = ${world};
        var chinaData = ${china};
		var travel =${travelList};
        var canback = true;
        function worldmap(){
			var worldChart = echarts.init(document.getElementById('map-world-wrap'));
			var option = {
			  	tooltip: {
			        trigger: 'item',
			        formatter: function (params) {
			        	if (params.name != '') {
			        		return params.name + ' : ' + params.value;
			        	}
			        }
			    },
			    toolbox: {
	                show: true,
	                left:'left',
	                top:'bottom',
	                itemSize:120,
	                feature: {
	                    myTool: {
			                show: true,
			                title: '中国地图',
			                icon: 'image://${baseimg}/imgs/default/china.png',
			                onclick: function (){
			                    $("#map-world-wrap").addClass("hd");
								$("#map-china-wrap").removeClass("hd");
			                }
			            }
	                }
	            },
			  	visualMap: {
			  		top:'top',
					type: 'continuous', // 连续型
					min: 0,       		// 值域最小值，必须参数
					max: maxworld,			// 值域最大值，必须参数
					calculable: true,	// 是否启用值域漫游
					inRange: {
			             	color: ['#EDDE5D','#F09819']
			                             // 指定数值从低到高时的颜色变化
			        },
			        textStyle: {
						color: '#fe6816'	// 值域控件的文本颜色
					}
			    },
			  	backgroundColor: '#ececec',
				series: [
					{
						name: '世界', // series名称
						type: 'map',
			            mapType: 'world',
						symbolSize: 6,
						data: worldData // series数据内容
					}
				]
			}
	
			worldChart.setOption(option);
			worldChart.on('click', function (params) {//点击事件
				if(params.name == '中国'){
					$("#map-world-wrap").addClass("hd");
					$("#map-china-wrap").removeClass("hd");
				}
			});
        }

		function chinaMap(){
			var chinaChart = echarts.init(document.getElementById('map-china-wrap'));
			var option = {
			  	tooltip: {
			        trigger: 'item',
			        hideDelay: 1000,
			        alwaysShowContent: true,
			        enterable:true,
			        formatter: function (params) {
			            if (params.name != '') {
				        	for (var i in travel) {
				        		if (params.name == travel[i].name){
					        		var values = travel[i].value;
					        		var newbox = '<div class="newTrace">';
						        	newbox += '<div class="trangle"></div>';
						        	newbox += '<h3>最新足迹:</h3>';
						        	newbox +=  '<ul class="newprintlist">';
						        	var j = 0;
						        	for (j in values) {
										if(j < 5){
								        	newbox += '<li>'
								            newbox += '<span class="location"><img src="${baseimg}/imgs/default/location.png" alt="位置" title="位置">'+values[j].city+'</span>';
								        	newbox += '<a href="${base}/article/'+values[j].cid+'/'+values[j].oid+'">'+values[j].title.substring(1,10)+'</a>'
								        	newbox += '</li>'
										} else {
											break;
										}					        	
						        	}
						        	newbox += '</ul>'
						        	if (j >= 5) { 
						        		newbox += '<input type="hidden" name="province" id="province" value='+travel[i].name+' />';
						        		newbox += '<a href="#" onclick="area(this,3);" class="more">更多</a>';
						        	}
						        	newbox += '</div>';
						        	return newbox;
				        		}
				        	}
			        	}
			        }
			    },
			    toolbox: {
	                show: true,
	                left:'left',
	                top:'bottom',
	                itemSize:120,
	                feature: {
	                    myTool: {
			                show: true,
			                title: '国际地图',
			                icon: 'image://${baseimg}/imgs/default/world.png',
			                onclick: function (){}
			            }
	                }
	            },
			  	visualMap: {
			  		top:'top',
					type: 'continuous', // 连续型
					min: 0,       		// 值域最小值，必须参数
					max: maxchina,			// 值域最大值，必须参数
					calculable: true,	// 是否启用值域漫游
					inRange: {
			             	color: ['#EDDE5D','#F09819']
			                             // 指定数值从低到高时的颜色变化
			        },
			        textStyle: {
						color: '#fe6816'	// 值域控件的文本颜色
					}
			    },
			  	backgroundColor: '#ececec',
				series: [
					{
						name: '中国', // series名称
						type: 'map', // series图表类型
            			mapType: 'china',
            			label: {
			                normal: {
			                    show: true
			                },
			                emphasis: {
			                    show: true
			                }
			            },
						data: chinaData // series数据内容
					}
				]
			}
			chinaChart.setOption(option);
			chinaChart.on('click', function (params) {//点击事件
				canback = true;
			});
		}
		
		function back(){
			$("#map-china-wrap").on("click",function(params){
				if (canback == true) {
					$("#map-world-wrap").removeClass("hd");
					$("#map-china-wrap").addClass("hd");
					if ($("#map-world-wrap").html() == '') {
						worldmap();
					}
				} else{
					canback = true;
				}
			})
		}
		
		$(function(){
			$.fn.scrollHandle({
				obj: window,
				fun: loadmore
			})
			$("#lastest").click();
			chinaMap();
			back();
		})
		
		var pagenum = 1;
		var prePage = 1;
		var more = 0;
		// 加载更多话题 
		function loadmore() {
		var provinces = '';
			if(pagenum == 0){
				return false;
			} 
			var json = {};
			json.cid='${cid?c}';
			json.pagenum=pagenum;
			var url = "";
			if (more == 1) {
				url = "${base}/ajax/article/hottravel";
			} else if(more >= 2 && more <= 4) {
				url = "${base}/ajax/article/area";
				if (more == 2 ) {
					provinces = $("#province").val();
				} else if (more == 3) {
					provinces = $("#searchcity").val();
				} else if (more == 4) {
					provinces = $("#s_province").val();
					var citys = $("#s_city").val();
					if(citys.length > 2) {
						citys = citys.substring(0,citys.length - 1);
					}
					json.city = citys;
				}
				if(provinces != void(0) && provinces.length > 2) {
					provinces = provinces.substring(0,provinces.length - 1);
				}
				json.province = provinces;
			} else {
				url = "${base}/ajax/otherpage/catalogue/${cid?c}";
			}
			if(prePage != json.pagenum) {
			    prePage = json.pagenum;
				$.get(url, json, function(data){
					if(data.trim() == ""){// 没有更多内容
						pagenum=0;
						return false;
					}
					$(".tabBox").append(data);
					pagenum+=1;
				});
			}
		}
		
		
		//获得最热话题
		function hotArticles(obj){
			$(".airechose").addClass("hd");
			more = 1;
			pagenum = 1;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/ajax/article/hottravel";
			var json = {};
			json.cid='${cid?c}';
			json.pagenum=pagenum;
			$.get(url, json,function(data){
				if(data != ''){
					pagenum +=1;
					$(".tabBox").html(data);
				}
			});
		}
		
		//获得最新话题
		function lastArticles(obj){
			$(".airechose").addClass("hd");
			more = 0;
			pagenum = 1;
			prePage=pagenum;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/ajax/otherpage/catalogue/${cid?c}";
			$.get(url, {pagenum:pagenum},function(data){
				if(data != ''){
					pagenum +=1;
					$(".tabBox").html(data);
				}
			});
		}
		
		//根据时区获取
		function area(obj,type){
			$(".airechose").removeClass("hd");
			_init_area();
			more = 2;
			pagenum = 1;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/ajax/article/area";
			var provinces = "";
			var citys = "";
			
			if (type == 2) {
				provinces = $("#province").val();
			} else if (type == 3) {
				provinces = $("#searchcity").val();
			} else if (type == 4) {
				provinces = $("#s_province").val();
				citys = $("#s_city").val();
				if(citys != void(0) && citys.length > 2) {
					citys = citys.substring(0,citys.length - 1);
					
				}
			} else if (type == 5) {
				provinces = $(obj).text();
			} 
			if(provinces != void(0) && provinces.length > 2) {
				provinces = provinces.substring(0,provinces.length - 1);
			}
			var json = {};
			json.cid='${cid?c}';
			json.pagenum=pagenum;
			json.province = provinces;
			json.city = citys;
			$.get(url, json,function(data){
				if(data != ''){
					pagenum +=1;
					$(".tabBox").html(data);
				} else {
					$(".tabBox").html('<div class="nodata">暂无足迹</div>');
				}
			});
		}
	</script>
</body>
</html>