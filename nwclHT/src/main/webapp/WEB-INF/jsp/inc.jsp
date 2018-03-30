<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
request.setAttribute("basePath", basePath);
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<script type="text/javascript" src="${basePath}/js/extBrowser.js" charset="utf-8"></script>

<!-- 引入my97日期时间控件 -->
<script type="text/javascript" src="${basePath}/js/My97DatePicker4.8b3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<!-- 引入kindEditor插件 -->
<link rel="stylesheet" href="${basePath}/js/kindeditor-4.1.7/themes/default/default.css">
<script type="text/javascript" src="${basePath}/js/kindeditor-4.1.7/kindeditor-all-min.js" charset="utf-8"></script>

<!-- 引入jQuery -->
<script src="${basePath}/js/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>

<!-- 引入bootstrap样式 -->
<link href="${basePath}/js/bootstrap-2.3.1/css/bootstrap.min.css" rel="stylesheet" media="screen">
<!-- <script charset="utf-8" src="${basePath}/js/bootstrap-2.3.1/js/bootstrap.min.js" charset="utf-8"></script> -->

<!-- 引入EasyUI -->
<link id="easyuiTheme" rel="stylesheet" href="${basePath}/js/jquery-easyui-1.3.3/themes/<c:out value="${cookie.easyuiThemeName.value}" default="bootstrap"/>/easyui.css" type="text/css">
<!-- <link rel="stylesheet" href="${basePath}/js/jquery-easyui-1.3.3/themes/icon.css" type="text/css"> -->
<script type="text/javascript" src="${basePath}/js/jquery-easyui-1.3.3/jquery.easyui.min.js" charset="utf-8"></script>

<script type="text/javascript" src="${basePath}/js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<!-- 修复EasyUI1.3.3中layout组件的BUG -->
<script type="text/javascript" src="${basePath}/js/jquery-easyui-1.3.3/plugins/jquery.layout.js" charset="utf-8"></script>

<!-- 引入EasyUI Portal插件 -->
<link rel="stylesheet" href="${basePath}/js/jquery-easyui-portal/portal.css" type="text/css">
<script type="text/javascript" src="${basePath}/js/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>

<!-- 扩展EasyUI -->
<script type="text/javascript" src="${basePath}/js/extEasyUI.js?v=20130516" charset="utf-8"></script>

<!-- 扩展EasyUI Icon -->
<link rel="stylesheet" href="${basePath}/style/extEasyUIIcon.css?v=20130510" type="text/css">


<!-- 扩展jQuery -->
<script type="text/javascript" src="${basePath}/js/extJquery.js?v=20130506" charset="utf-8"></script>

<script type="text/javascript" src="${basePath}/js/ajaxfileupload.js" ></script>
