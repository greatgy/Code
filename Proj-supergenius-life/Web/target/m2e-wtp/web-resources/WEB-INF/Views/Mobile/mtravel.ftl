[#ftl]
[#import "/WEB-INF/FtlLib/Macro/Public.ftl" as p]
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title!'天才人生——行万里路'}</title>
	<link rel="stylesheet" href="${basecss}/css/mobile/default/index.css">
	<script src="${basejs}/js/libs3/echarts.min.js"></script>
	<script src="${basejs}/js/pages/world.js"></script>
	<script src="${basejs}/js/pages/china.js"></script>
	<script type="text/javascript" src="${basejs}/js/pages/scroll.js"></script>
	<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${basejs}/js/weixinshare.js"></script>
	<style type="text/css">
		.descripe{
			margin: 0 auto 30px;
		    color: #888;
		    font-size: .9rem;
		    line-height: 22px;
		    max-height: 68px;
		    overflow: hidden;
		}
		.footbox li{
			height: auto;
			position: relative;
		    margin-bottom: 20px;
		    border-bottom: 1px solid #e3e3e3;
		    padding-bottom: 20px;
		}
	
	</style>
</head>
<body>
	<div id="map-world-wrap" class="map hd"></div>
	<div id="map-china-wrap" class="map"></div>
	<div class="footprint">
		<ul class="judgement">
			<li onclick="lastArticles(this);" id="lastest" class="curjudge">最新</li>
			<li onclick="hotArticles(this);">最热</li>
		</ul>
		[#if me??]
			<a class="publishBtn" href="${base}/addproblem/${cid?c}">添加足迹</a>
		[#else]
			<a class="publishBtn" href="${base}/m/login?cid=${cid?c}&pcid=${pcid}" >添加足迹</a>
		[/#if]
		<ul class="tabBox footbox" id="newList">
		</ul>
	</div>
	<script>
		var maxworld = ${maxWorld};
		var maxchina = '${maxChina}';
		var worldData = ${world};
        var chinaData = ${china};
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
	                itemSize:60,
	                feature: {
	                    myTool: {
			                show: true,
			                title: '中国地图',
			                icon: 'image://${baseimg}/imgs/default/china.png',
			                onclick: function (){
			                }
			            }
	                }
	            },
			  	visualMap: {
			  		top:'top',
					type: 'continuous', // 连续型
					min: 0,       		// 值域最小值，必须参数
					max: maxworld,			// 值域最大值，必须参数
					itemWidth:8,                           //图形的宽度，即长条的宽度。
        			itemHeight:50, 
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
			var option1 = {
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
	                itemSize:60,
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
					itemWidth:8,                           //图形的宽度，即长条的宽度。
        			itemHeight:50, 
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
			                emphasis: {
			                    show: true
			                }
			            },
						data: chinaData // series数据内容
					}
				]
			}
			chinaChart.setOption(option1);
			chinaChart.on('click', function (params) {//点击事件
				canback = false;
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
			$("#map-world-wrap").on("click",function(params){
				if (canback == true) {
					$("#map-china-wrap").removeClass("hd");
					$("#map-world-wrap").addClass("hd");
					if ($("#map-china-wrap").html() == '') {
						chinamap();
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
		var ishot = false;
		var prePage = 1;
		// 加载更多话题 
		function loadmore() {
			if(pagenum == 0){
				return false;
			}
			var json = {};
			json.cid='${cid?c}';
			json.pagenum=pagenum;
			var url = "";
			if(ishot == true){
				url = "${base}/m/ajax/article/hottravel";
			} else{
				url = "${base}/m/ajax/otherpage/catalogue/${cid?c}";
			}
			if(prePage != json.pagenum) {
			    prePage = json.pagenum;
				$.get(url, json, function(data){
					if(data.trim() == ""){// 没有更多内容
						pagenum=0;
						return false;
					}
					$(".footbox").append(data);
					pagenum+=1;
				});
			}
		}
		
		
		//获得最热话题
		function hotArticles(obj){
			ishot = true;
			pagenum = 1;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/m/ajax/article/hottravel";
			var json = {};
			json.cid='${cid?c}';
			json.pagenum=pagenum;
			$.get(url, json,function(data){
				if(data != ''){
					pagenum +=1;
					$(".footbox").html(data);
				}
			});
		}
		
		//获得最新话题
		function lastArticles(obj){
			ishot = false;
			pagenum = 1;
			prePage=pagenum;
			$(obj).addClass("curjudge").siblings().removeClass("curjudge");
			var url = "${base}/m/ajax/otherpage/catalogue/${cid?c}";
			$.get(url,{pagenum:pagenum},function(data){
				if(data != ''){
					pagenum +=1;
					$(".footbox").html(data);
				}
			});
		}
	</script>
</body>
</html>