<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title></title>

<link rel="stylesheet" type="text/css" href="${basePath}/js/jquery.tzineClock/jquery.tzineClock.css" />
<style>
#fancyClock {
	border: none;
	width: 620px;
	zoom:0.3;
	margin-left: 40px;
}
</style>
<script type="text/javascript" src="${basePath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.tzineClock/jquery.tzineClock.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#fancyClock').tzineClock();
});
</script>
</head>
<body>
	<div id="fancyClock"></div>
</body>
</html>