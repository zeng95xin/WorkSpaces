<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${basePath}"></c:set>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${ctx}/web/indexImg/edit',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				console.trace(result);
				result = $.parseJSON(result);
				if (result.code=="SUCCESS") {
					parent.$.messager.alert('提示',result.message,'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为shopClass.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				}else {
					parent.$.messager.alert('错误', result.message, 'error');
				}
			}
		});
	});
	
	function btn(n){
		$("#file"+n).click();
	}
	
	function show(n) {
		
	}
	
	function setImagePreview(n) {
		var docObj = document.getElementById("file"+n);
		var localImag = document.getElementById("pic"+n);
		var fileList = docObj.files;
		for (var i = 0; i < fileList.length; i++) {
			var imgObjPreview = document.getElementById("pic" + n);

			//判断是否正确的图片格式，以及判断是不是其它文件改了一个后缀冒充图片的
			var url = window.URL.createObjectURL(docObj.files[i]);
			var img = new Image();
			img.src = url;
			img.onerror = function() {
				alert("图片格式不对或图片损坏，仅支持 jpg|jpeg|gif|png");
				document.getElementById("file"+n).value = "";
				return false;
			};
			/* var imgObjPreview=document.getElementById("preview"); */
			if (docObj.files && docObj.files[i]) {
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
			} else {
				//IE下，使用滤镜
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById("pic"+n);
				//必须设置初始大小
				localImagId.style.width = "150px";
				localImagId.style.height = "150px";
				localImagId.style.margin = "10px";
				//图片异常的捕捉，防止用户修改后缀来伪造图片
				try {
					localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters
							.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
				} catch (e) {
					alert("您上传的图片格式不正确，请重新选择!");
					return false;
				}
				imgObjPreview.style.display = 'none';
				document.selection.empty();
			}
		}
		return true;
	};
	
	function GetUrl(){
		var type = $("#chooseType").val();
		if (type == 1) {
			$("#typeZone").html("<th style='text-align: right; width: 50px;'>连接地址</th> <td>"
				+"<textarea name='url'  style='width: 200px;height:60px;' placeholder='http://'></textarea></td>");
		}
		if (type == 2) {
			str="<th style='text-align: right; width: 50px;'>App内容</th> <td id='zone1'>"
				+"<select id='typeId' onchange='GetUrlInfo()' class='span2' style='width: 200px;'><option> --- 请选择 ---</option>"
				+"<option value='1'>业主讲堂</option>"
				+"<option value='2'>社区文化</option>"
				+"<option value='3'>自荐列表</option>"
				+"<option value='4'>尊享</option></select></td><td id='resultZone'></td>"
				+"";
			$("#typeZone").html(str);
			$("#urlDiv").html("<input type='hidden' id='setUrl' name='url'>");
		}
	}
	
	function GetUrlInfo(){
		var id = $("#typeId").val();
		var communityId = $("#comId").val();
		if(communityId == ''){
			alert('请选择小区！');
		}
		$.ajax({
			type : 'get',
			url : '${basePath}/web/indexImg/getUrl',
			data : {'id' : id, 'communityId' : communityId},
			dataType : 'json',
			error : function(){
				alert('发生错误');
			},
			success : function(data){
				if(data.code == "SUCCESS"){
					if(data.result != null){
						var sss= "<option> --- 请选择 ---</option>";
						for (var i = 0; i < data.result.length; i++) {
							sss += "<option value="+data.result[i].id+">"+data.result[i].title+"</option>";
						}
						var str = "<td><select id='result' onchange='setUrlAPPID();' style='width:200px;' >"+sss+"</select></td>";
						$("#resultZone").html(str);
						$("#urlDiv").html("<input type='hidden' id='setUrl' name='url'>");
					}
				}
			}
		});
	}
	function setUrlAPPID(){
		var aid = $("#typeId").val();
		var rid = $("#result").val();
		var text = "app:"+aid+":"+rid;
		$("#setUrl").val(text);
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post"  encType="multipart/form-data" action="${basePath}/web/thing/add">
			<table class="table table-hover table-condensed">
			  <tr>				
					<td><input name="id" type="hidden" value="${model.id}" ></td>
				</tr>	
				<tr>
					<th style="text-align: right; width: 50px;">图片类型</th>
					<td>
						<select name="type" class="span2" style="width: 200px;">
							<option value="4" <c:if test="${model.type==4 }">selected</c:if> >首页Banner</option>
							<option value="1" <c:if test="${model.type==1 }">selected</c:if> >服务+Banner</option>
							<option value="2" <c:if test="${model.type==2 }">selected</c:if> >尊享Banner</option>
							<option value="3" <c:if test="${model.type==3 }">selected</c:if> >邻里圈Banner</option>		
						</select>
					</td>	
					<td>
						<select name="communityId"  id="comId" class="span2" style="width: 200px;" onchange="GetUrl()">
							<c:forEach items="${Communities }" var="c">
								<option value="${c.id }" <c:if test="${model.communityId == c.id }">selected</c:if> >${c.name }</option>		
							</c:forEach>
						</select>
					</td>				
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">首页图片</th>
					<td>
						<img alt="" src="${model.imgPath }" style="height: 116px";width="97px" id="pic1"><br/>
						<input type="button" value="更换图片" style="margin-left: 80px;" onclick="btn(1)">
						<input type="file" name="img1" id="file1" class="file" style="width: 200px;display: none" onchange="setImagePreview(1)"/>
						<input type="file" name="img2" id="file2" class="file" style="width: 200px;display: none" hidden="hidden"/>
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">跳转对象</th>
					<td>
						<select name="urlType" class="span2" style="width: 200px;" id="chooseType" onchange="GetUrl()">
								<option value="1" <c:if test="${model.urlType eq 1 }">selected </c:if>>链接地址</option>
								<option value="2" <c:if test="${model.urlType eq 2 }">selected </c:if>>App</option>
						</select>
					</td>
				</tr>
				<tr id="typeZone">
					<c:if test="${model.urlType eq 1 }">
							<th style="text-align: right; width: 50px;">链接地址</th>
							<td>	<textarea name="url" style="width:200px;height:60px;">${model.url }</textarea></td>
					</c:if>
					<c:if test="${model.urlType eq 2 }">
							<th style="text-align: right; width: 50px;">App内容</th>
							<td>	<select style="width:200px;" id='typeId' onchange='GetUrlInfo()'>								
									<option value='1' <c:if test="${model.appType eq 1 }"> selected = selected </c:if>>业主讲堂</option>
									<option value='2' <c:if test="${model.appType eq 2 }"> selected = selected </c:if>>社区文化</option>
									<option value='3' <c:if test="${model.appType eq 3 }"> selected = selected </c:if>>自荐列表</option>
									<option value='4' <c:if test="${model.appType eq 4 }"> selected = selected </c:if>>尊享</option>								
							</select></td>
							<td id='resultZone'>
								<select style="width:200px;" id='result'>
									<option value="${model.appId }">${model.appTitle }</option>
								</select>
								<input type="hidden" name="url" value="${model.url }">
							</td>
					</c:if>
				</tr>
			</table>
			<div id="urlDiv"></div>
		</form>
	</div>
</div>


