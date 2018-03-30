<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
   import="java.util.*,com.bola.nwcl.dal.mybatis.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<!-- 维修价格 -->
<html>
<head>
<title>维修价格</title>
<meta name="viewport" content="width=device-width, initial-scale=1" /> 
  <style type="text/css">
  	body{width:100%; height:100%; background:#000; background-size:100% 100%; font-size:13px;}
 	body,div,ul,li,p,input,img,dl,dd,dt,h5{margin:0; padding:0;}   
    table{ border-collapse:collapse; border:solid 1px Black; width:700px}     
  </style>
</head>
<body>
	<img style="width:100%; height:100%;" src="http://img.nwcl.wauwo.net/upload/images/price.jpg"/>
	<%-- <table border="1"  >
	  <tr>
	    <th rowspan="2">序号</th>
	    <th rowspan="2">服务项目</th>
	    <th rowspan="2">规格/单位</th>
	    <th colspan="2">服务费</th>
	  </tr>
	  <tr>
	    <th >普通楼盘中心标准</th>
	    <th >高端楼盘中心标准</th>	   
	  </tr>
	  <tr>
	    <th style="text-align:left;" colspan="5">一、电气类</th>	   
	  </tr>
	    <%
	    List<MaintenancePrice> list= (List) request.getAttribute("priceList"); 
	  for(int i=0;i<list.size();i++)
	   {
	  %>
	  <tr>
	   <td><%=list.get(i).getSerial()%></td>
	   <td><%=list.get(i).getServiceProject()%></td>
	   <td><%=list.get(i).getUnit()%></td>
	   <td><%=list.get(i).getNormalServicePrice()%></td>
	   <td><%=list.get(i).getSeniorServicePrice()%></td>
	   
	  </tr>
	  <%
	   }
	  %>
	   <tr>
	    <th style="text-align:left;" colspan="5">二、给排水类</th>	   
	  </tr>
	   <%
	    List<MaintenancePrice> list2= (List) request.getAttribute("priceList2"); 
	     for(int i=0;i<list2.size();i++)
	   {
	  %>
	  <tr>
	   <td><%=list2.get(i).getSerial()%></td>
	   <td><%=list2.get(i).getServiceProject()%></td>
	   <td><%=list2.get(i).getUnit()%></td>
	   <td><%=list2.get(i).getNormalServicePrice()%></td>
	   <td><%=list2.get(i).getSeniorServicePrice()%></td>
	   
	  </tr>
	  <%
	   }
	  %>
	</table> --%>
</body>
</html>