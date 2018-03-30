<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>500</title>
<script type="text/javascript" src="${basePath}js/jquery-1.11.0.min.js"></script>
<style>
 *{ margin:0; padding:0;}
 body{ font-family:"宋体"; font-size:12px; color:#666;}
 a:link ,a:visited{ color:#f60; text-decoration:none;}
 a:hover{ text-decoration:underline;}
 .ml20{ margin-left:20px;}
 .b{ font-weight:bold;}
 .wrap{ margin-left:auto; margin-right:auto; min-width:1002px; min-height:600px; 
  width:1002px; height:600px; background:url("${basePath}images/500_bg.jpg")  center top no-repeat;}
 	.wrap p{ padding-top:310px; padding-left:400px;}
</style>
</head>

<body>
<div class="wrap">
<div style="height: 150px"></div>
<p>您可以选择: <a href="javascript:history.back(1)" class="b">返回上一页</a><a href="${basePath}main" class="ml20 b">返回首页</a></p>
</div>
</body>
</html>