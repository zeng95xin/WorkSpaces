<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="initial-scale=1.0, user-scalable=no, width=device-width">
<title>高德地图</title>
<link rel="stylesheet"
	href="http://cache.amap.com/lbs/static/main1119.css" />
<link rel="stylesheet" href="https://cache.amap.com/lbs/static/main.css" />
<script type="text/javascript" charset="utf-8"
	src="${basePath}js/jquery-1.11.0.min.js"></script>
<style type="text/css">
body {
	font-size: 12px;
}

#tip {
	background-color: #ddf;
	color: #333;
	border: 1px solid silver;
	box-shadow: 3px 4px 3px 0px silver;
	position: absolute;
	top: 10px;
	right: 10px;
	border-radius: 5px;
	overflow: hidden;
	line-height: 20px;
}

#tip input[type="text"] {
	height: 25px;
	border: 0;
	padding-left: 5px;
	width: 280px;
	border-radius: 3px;
	outline: none;
}

.info-title {
	color: white;
	font-size: 14px;
	background-color: blue;
	line-height: 26px;
	padding: 0px 0 0 6px;
	font-weight: lighter;
	letter-spacing: 1px
}

.info-content {
	padding: 4px;
	color: #666666;
	line-height: 23px;
	font: 12px Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑',
		Arial;
}

.info-content img {
	float: left;
	margin: 3px;
}
</style>
<script
	src="http://webapi.amap.com/maps?v=1.4.0&key=a4f524fb26861e4b35dfba8c88f12ad8&plugin=AMap.PlaceSearch,AMap.AdvancedInfoWindow,AMap.CitySearch"></script>
<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>

</head>
<body>
	<div id="mapContainer"></div>
	<div id="tip">
		关键字：<input type="text" id="keyword" name="keyword"
			value="请输入关键字：(选定后搜索)" onfocus='this.value=""' /><br> 范　围：<input
			type="text" id="distance" name="distance" value="请输入有效距离：(选定后搜索)"
			onfocus='this.value=""' /><br> 经纬度：<input type="text"
			readonly="true" id="lnglat"> <input type="submit"
			name="submit" />
	</div>


	<script type="text/javascript">
		$(":submit").on("click", function() {
			form();
		})
		function form() {
			var keyword = $("#keyword").val();
			var value = $("#distance").val();
			var lis = $("#lnglat").val();
			window.opener.setValue(value);
			window.opener.setValuel(keyword);
			window.opener.setValuelis(lis);
			window.close();
		}
		function setRadius(n) {
			var circle = new AMap.Circle({
				radius : n,
				center : map.getCenter(),
				strokeColor : '#FD6D2C',
				strokeWeight : 1,
				fillColor : 'rgba(0,0,0,0.0)',
				fillOpacity : 10,
				map : map
			})
		}

		var windowsArr = [];
		var map = new AMap.Map("mapContainer", {
			enableHighAccuracy : true,//是否使用高精度定位，默认:true
			resizeEnable : true,
			center : [ 106.4705657959, 29.6213330342 ],//å°å¾ä¸­å¿ç¹
			zoom : 17,//å°å¾æ¾ç¤ºçç¼©æ¾çº§å«
			keyboardEnable : false,
			isHotspot : true
		});
		var marker = new AMap.Marker({
			position : [ 106.4705657959, 29.6213330342 ]
		});
		marker.setMap(map);
		$("body").bind('keydown', function(e) {
			var key = e.which;
			if (key == 13) {
				var n = $("#distance").val();
				if (n == "") {
					n = 0;
				}
				setRadius(n);
			}
		});
		AMap.plugin([ 'AMap.Autocomplete', 'AMap.PlaceSearch' ], function() {
			var autoOptions = {
				city : "åäº¬", //åå¸ï¼é»è®¤å¨å½
				input : "keyword"//ä½¿ç¨èæ³è¾å¥çinputçid
			};
			autocomplete = new AMap.Autocomplete(autoOptions);
			var placeSearch = new AMap.PlaceSearch({
				city : 'åäº¬',
				map : map
			})
			AMap.event.addListener(autocomplete, "select", function(e) {
				//TODO éå¯¹éä¸­çpoiå®ç°èªå·±çåè½
				placeSearch.setCity(e.poi.adcode);
				placeSearch.search(e.poi.name)
			});
		});
		//ä¸ºå°å¾æ³¨åclickäºä»¶è·åé¼ æ ç¹å»åºçç»çº¬åº¦åæ 
		var clickEventListener = map.on('click', function(e) {
			document.getElementById("lnglat").value = e.lnglat.getLng() + ','
					+ e.lnglat.getLat()
		});
		function select(e) {
			if (e.poi && e.poi.location) {
				map.setZoom(15);
				map.setCenter(e.poi.location);
			}
		}
	</script>

	<script>
		var placeSearch = new AMap.PlaceSearch(); //构造地点查询类
		var infoWindow = new AMap.AdvancedInfoWindow({});
		map.on('hotspotclick', function(result) {
			placeSearch.getDetails(result.id, function(status, result) {
				if (status === 'complete' && result.info === 'OK') {
					placeSearch_CallBack(result);
				}
			});
		});
		//回调函数
		function placeSearch_CallBack(data) { //infoWindow.open(map, result.lnglat);
			var poiArr = data.poiList.pois;
			var location = poiArr[0].location;
			infoWindow.setContent(createContent(poiArr[0]));
			infoWindow.open(map, location);
		}
		function createContent(poi) { //信息窗体内容
			var s = [];
			s.push('<div class="info-title">' + poi.name
					+ '</div><div class="info-content">' + "地址：" + poi.address);
			s.push("电话：" + poi.tel);
			s.push("类型：" + poi.type);
			s.push('<div>');
			return s.join("<br>");
		}
	</script>
<script type="text/javascript">
    //获取用户所在城市信息
    $(function() {
        //实例化城市查询类
        var citysearch = new AMap.CitySearch();
        //自动获取用户IP，返回当前城市
        citysearch.getLocalCity(function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                if (result && result.city && result.bounds) {
                    var cityinfo = result.city;
                    var citybounds = result.bounds;
                    //地图显示当前城市
                    map.setBounds(citybounds);
                }
            } else {
                document.getElementById('tip').innerHTML = result.info;
            }
        });
    });
</script>
	<script type="text/javascript"
		src="https://webapi.amap.com/demos/js/liteToolbar.js"></script>
</body>
</html>