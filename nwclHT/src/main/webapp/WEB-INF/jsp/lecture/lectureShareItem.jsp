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
<html>
<head>
<title>什么是业主讲堂</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
  <style type="text/css">
    body{width:100%; height:100%; background:#000; background-size:100% 100%; font-size:13px;}
 	body,div,ul,li,p,input,img,dl,dd,dt,h5{margin:0; padding:0;}   
    table{ border-collapse:collapse; border:solid 1px Black; width:700px}      
  </style>
</head>
<body>
<img style="width:100%; height:100%;"  src="http://img.nwcl.wauwo.net/upload/images/lectureShare.jpg"/>
<img  src="/images/lectureShare.jpg"/>
<div>
 <h2>什么是业主讲堂？</h2>
 <p>业主自荐平台为新世界社区业主提供了移动端很好的社区文化交流平台，<br>
    人们将新世界业主日常生活中各种有意义的生活主题、讨论主题汇集起<br>来、积少成多、
    形成了新世界社区自己的社区文化力量。
 </p>
  <h2><font color="green">赢得社区良好声誉，融洽社区社会关系。</font></h2>
  <h2>新世界希望分享的主题？</h2>
</div>
<div>
	<table border="1" cellpadding="3" cellspacing="1" width="600px">
	 	    	 
	    <%
	    List<LectureShareItem> list= (List) request.getAttribute("itemList"); 
	  for(int i=0;i<3;i++)
	   {
	  %>
	  <tr>
	   <td style="vertical-align:middle; text-align:center;"><%=list.get(i).getItemName()%></td>  
	   <td style="vertical-align:middle; text-align:center;"><%=list.get(i+3).getItemName()%></td>  
	   <td style="vertical-align:middle; text-align:center;"><%=list.get(i+6).getItemName()%></td>     
	  </tr>
	  <%
	   }
	  %>
	   <tr>
	     	
	</table>
 </div>
</body>
</html>