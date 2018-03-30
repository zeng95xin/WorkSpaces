<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${basePath}/js/ajaxfileupload.js" charset="utf-8"></script>
<!-- 引入kindEditor插件 -->
<link rel="stylesheet" href="${basePath}/js/kindeditor-4.1.7/themes/default/default.css">
<script type="text/javascript" src="${basePath}/js/kindeditor-4.1.7/kindeditor-all-min.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		window.setTimeout(function() {
			editor = KindEditor.create('#Content', {
				width : '95%',
				height : '400px',
// 				items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak', 'anchor', 'link', 'unlink' ],
				items : [  'source','|','undo', 'redo', '|', 'preview', 'print', 'template', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'table', 'hr', 'emoticons', 'baidumap', 'link', 'unlink' ],
				uploadJson : '${basePath}/fileController/upload',
				fileManagerJson : '${basePath}/fileController/fileManage',
				afterChange : function() {
                   }  ,
				allowFileManager : true
				
			});									
			parent.$.messager.progress('close');
		}, 1);
		
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${basePath}/web/announcementNotice/edit',			
			onSubmit : function() {
				editor.sync();
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
				if (result.code == "SUCCESS") {
					parent.$.messager.alert('提示', result.message, 'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为shopClass.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.message, 'error');
				}
			}
		});
	});
	
	
	//上传照片按钮
	$("#doUp").bind("click", function() {
		$("#imgs").trigger("click");
	});

	/**
	 * 上传酒店图片 选择显示预览，以及前端判断
	 */
	function setImagePreview() {
		var docObj = document.getElementById("imgs");
		var localImag = document.getElementById("localImag");
		//localImag.innerHTML = "";
		var fileList = docObj.files;
		/* fileUploadFun(fileList); */
		for (var i = 0; i < fileList.length; i++) {
			//localImag.innerHTML += "<li style='float:left;margin-right:15px' > <img id='preview" + i + "'  /> </li>";
			$("#localImag").prepend("<li style='float:left;margin-right:15px' > <img id='preview" + i + "'  /> </li>");
			var imgObjPreview = document.getElementById("preview" + i);

			//判断是否正确的图片格式，以及判断是不是其它文件改了一个后缀冒充图片的
			var url = window.URL.createObjectURL(docObj.files[i]);
			var img = new Image();
			img.src = url;
			img.onerror = function() {
				alert("图片格式不对或图片损坏，仅支持 jpg|jpeg|gif|png");
				document.getElementById("imgs").value = "";
				return false;
			};
			//判断是否为正确的图片格式END
			//判断图片的大小是否超过2M
			var imageSize = docObj.files[i].size;
			if (imageSize > 20 * 1024 * 1024) {
				alert("上传图片太大,不得超过20M");
				document.getElementById("imgs").value = "";
				document.getElementById("preview" + i).src = "";
				return false;
			}
			/* var imgObjPreview=document.getElementById("preview"); */
			if (docObj.files && docObj.files[i]) {
				//火狐下，直接设img属性
				imgObjPreview.style.display = 'block';
				imgObjPreview.style.width = '97px';
				imgObjPreview.style.height = '116px';
				//imgObjPreview.style.margin = '10px';
				//imgObjPreview.src = docObj.files[0].getAsDataURL();
				//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式  
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
			} else {
				//IE下，使用滤镜
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById("localImag");
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
		// 图片上传
		fileUploadFun(this);
		return true;
	};
	
	// 选择发送人
	function getNames(){
		var cnodes = $('#ctt').tree('getChecked');
		var ps = 0;
		var cs = '';
		for (var i = 0; i < cnodes.length; i++) {
			if (undefined != cnodes[i].id){
				if (cnodes[i].iconCls == "icon-building") {
					if (cs != '') {
						cs += ',';
						cs += cnodes[i].id;
					}else{
						cs += cnodes[i].id;
					}
				ps+=1;
				}
			}
		}
		$("#recipient").val(cs);
		$('<div id="add"class="quick-alert">已选择 '+ps+' 楼栋</div>').insertAfter($("#example")).animate({opacity: 1.0}, 2000).fadeOut("slow",function(){ 
			//隐藏时把元素删除
			$(this).remove();
			});
	}



	function fileUploadFun(file) {
		var id = "imgs";
		$.ajaxFileUpload({
            url:'${basePath}/web/announcementNotice/fileUpload',
            secureuri:false,
            fileElementId:id,
            dataType: 'text',
            success: function (data){  
            	//var data1 = JSON.parse(data);
            	//alert(data1);
            	var end = data.length - 6;
				data = data.substring(59, end);
				data = $.parseJSON(data);
            	if(data.code =="SUCCESS"){
                  //alert(data.message);
                 // alert(data.result.length);
					//var images = $("#pictures").val();
					var paths = $("#pictures").val();
                 	for (var i = 0; i < data.result.length; i++) {
                 		if(paths != ""){
                 			paths =paths + "," + data.result[i];
                 		}else{
                 			paths = paths + data.result[i];
                 		}
					}
                 	$("#pictures").val(paths);
             	}else{
             		//alert('error');
             	};
            },
        }) ;
	}
	
	function deleteImg(id){
		parent.$.messager.confirm('询问', '您是否要删除当前图片？', function(b) {
			if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${basePath}/web/announcementNotice/deleteImg', {
						id : id
					}, function(result) {
						if (result.code=="SUCCESS") {
							$('.item'+id).remove();
							parent.$.messager.alert('提示', result.message, 'info');
						}
						parent.$.messager.progress('close');
					}, 'JSON');
			}
		});
	}
	
	function ChooseBuidingFun(){
		return;
		if(${loginUser.roleId == 1 }){
			var id = $("#coms").val();
			if(id == "" || id == null){
				$("#chooseDiv").css("display","none");
				alert("请选择一个小区");
				$("#chooseDiv").css("display","none");
			}else{
				$("#ctt").tree({
					 url: "${basePath }/web/announcementNotice/getBuildingTree?communityId="+id,  
				        loadFilter: function(data){     
				            return data;        
				        }   
				});
			$("#chooseDiv").css("display","block");
			}
		}
		if(${loginUser.roleId == 2 }){
			$("#chooseDiv").css("display","block");
		}
	}
</script>
<style>

.quick-alert{
 width: 130px; 
 margin: 1em 0; 
 padding: .5em; 
 background: #ffa; 
 border: 1px solid #a00; 
 color: #a00; 
 font-weight: bold; 
}
.span_div{position:relative}
.span_div>img{cursor:default}
.close_span,.close_span1{position:absolute;top:2px;right:2px;color:#fff;font-weight:bold;font-size:20px;cursor: pointer}
#div_span{width:100%;display: inline-block;}
.dimg{width:25px;height:25px;}
</style>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post" encType="multipart/form-data" >
			<input type="hidden" name="id" value="${model.id }">
			<input type="hidden" name="communityId" value="${model.communityId }">
			<table class="table table-hover table-condensed">

				<tr>
					<th style="text-align: right; width: 50px;">标题</th>
					<td><input name="title" type="text" style="width: 95%;" 
						placeholder="标题" class="easyui-validatebox span2"
						data-options="required:true" value="${model.title }"></td>

					<%-- <td rowspan="7" style="width: 250px;">
						<div style="width:90%;float:right;display:none" id="chooseDiv">
							<span>选择接受人</span>
							<ul id="ctt" class="easyui-tree ss"
								data-options=" url:'${basePath }/web/announcementNotice/getBuildingTree',animate:true,checkbox:true,">
							</ul>
							<br/><br/>
							<input type="button" value="确认选择" onclick="getNames()">
							<br><div id="example"class="quick-alert77"> </div>
						</div>
					</td> --%>
				</tr>
				<tr>
					 
					<th style="text-align: right; width: 50px;">简介</th>
					<td>
						<input name="summary" type="text" style="width: 95%;"
						placeholder="简介" class="easyui-validatebox span2" value="${model.summary }"
						data-options="required:true">
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">&nbsp;</th>
					<td><span style="color: red;">图片最佳大小为5:1的比例,比如750：150</span></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">公告图</th>
					<td>
						<div>
							<ul style="list-style-type: none">
								<c:forEach items="${model.images }" var="list">
									<li style='float:left;margin:10px' class="item${list.id } span_div">
										<img class="hp" alt="" src="${list.imgPath }" style="height: 97px";width="116px">
										<span onclick="deleteImg(${list.id })" class="close_span"><img class="dimg" alt=""  src="/images/delete.png"></span>
									</li>
								</c:forEach>
							</ul>
						</div>
					</td>
				</tr> 
				<tr>
					<th style="text-align: right; width: 70px;">添加公告图</th>
					<td>
						<input type="hidden" id="recipient" name="recipient">
						<input type="hidden" name="pictures" id="pictures">
						<div>
							<input type="file" autocomplete="off" name="imgs" id="imgs"
								style="display:none;" multiple="multiple"
								onchange="javascript:setImagePreview();" accept="image/*" />
							<ul class="page_imgs clearfix" id="localImag"
								style="list-style-type: none">
								<li><img alt="" src="${basePath}/images/up_img.png" id="doUp"></li>
							</ul>
						</div>
					</td>
				</tr> 
				<tr>
					<th style="text-align: right; width: 50px;">内容</th>
					<td><textarea name="detail"  style="width:100px;height: 190px;" id="Content"
							class="easyui-validatebox span2">${model.detail }</textarea></td>
				</tr>

			</table>
		</form>
	</div>
</div>
