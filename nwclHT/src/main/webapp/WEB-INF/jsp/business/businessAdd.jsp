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
<script type="text/javascript">
	$(function() {
		window.setTimeout(function() {
			editor = KindEditor.create('#Content', {
				width : '95%',
				height : '300px',
				items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak', 'anchor', 'link', 'unlink' ],
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
			url : '${basePath}/web/business/add',
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
				//console.trace(result);
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

		$("#doUpPic").click(function() {
			$("#Pic").trigger("click");
		});

		//上传照片按钮
		$("#doUp").bind("click", function() {
			$("#imgs").trigger("click");
		});

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

	/**
	 * 上传头像 选择头像显示预览，以及前端判断
	 */
	function setPicPreview() {
		var docObj = document.getElementById("Pic");
		var localImag = document.getElementById("localPic");
		localImag.innerHTML = "";
		var fileList = docObj.files;
		for (var i = 0; i < fileList.length; i++) {
			localImag.innerHTML += "<li style='float:left;margin:15px' > <img id='previews" + i + "'  /> </li>";
			var imgObjPreview = document.getElementById("previews" + i);
			
			//判断是否正确的图片格式，以及判断是不是其它文件改了一个后缀冒充图片的
			var url = window.URL.createObjectURL(docObj.files[i]);
			var img = new Image();
			img.src = url;
			img.onerror = function() {
				alert("图片格式不对或图片损坏，仅支持 jpg|jpeg|gif|png");
				document.getElementById("Pic").value = "";
				return false;
			};
			//判断是否为正确的图片格式END
			//判断图片的大小是否超过2M
			var imageSize = docObj.files[i].size;
			if (imageSize > 20 * 1024 * 1024) {
				alert("上传图片太大,不得超过20M");
				document.getElementById("Pic").value = "";
				document.getElementById("previews" + i).src = "";
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
				var localImagId = document.getElementById("localPic");
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
	
	function fileUploadFun(file) {
		var id = "imgs";
		$.ajaxFileUpload({
            url:'${basePath}/web/business/fileUpload',
            secureuri:false,
            fileElementId:id,
            dataType: 'text',
            success: function (data){  
            	//var data1 = JSON.parse(data);
            	//alert(data1);
            	var end = data.length - 6;
				data = data.substring(59, end);
				data = $.parseJSON(data);
				console.log(data);
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
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="form" method="post" encType="multipart/form-data" >
			<table class="table table-hover table-condensed">
				<tr>
					<th style="text-align: right; width: 70px;">名称</th>
					<td><input name="title" type="text" style="width: 300px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true"></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 70px;">所属版块</th>
					<td>
						<select name="type" style="width: 300px;" class="easyui-validatebox span2" data-options="required:true">
							<option> 请选择 </option>
							<option value="3"> 商圈特惠 </option>
							<option value="1"> 酒店公寓 </option>
							<option value="2"> 温泉养生 </option>
						</select>
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 70px;">地址</th>
					<td><input name="address" type="text" style="width: 300px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true"></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 70px;">联系电话</th>
					<td><input name="phone" type="text" style="width: 300px;"
						placeholder="" class="easyui-validatebox span2"
						data-options="required:true"></td>
				</tr>
				
				<tr>
					<th style="text-align: right; width: 70px;">形象缩略图</th>
					<td>
						<div>
							<input type="file" autocomplete="off" name="Pic" id="Pic"
								style="display:none;" onchange="javascript:setPicPreview();"
								accept="image/*" />
							<ul class="page_imgs clearfix" id="localPic"
								style="list-style-type: none">
								<li><img alt="" src="${basePath}/images/up_img.png" id="doUpPic"></li>
							</ul>
						</div>
					</td>
				</tr>
				<tr>
					<th style="text-align: right; width: 70px;">商家简介</th>
					<td><textarea name="summary" style="width: 300px;height:100px;" id="summary"
							class="easyui-validatebox span2"></textarea></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 70px;">商家介绍</th>
					<td><textarea name="content" style="width: 300px;height:100px;" id="Content"
							class="easyui-validatebox span2"></textarea></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 50px;">&nbsp;</th>
					<td><span style="color: red;">图片最佳大小为5:1的比例,比如750：150</span></td>
				</tr>
				<tr>
					<th style="text-align: right; width: 70px;">广告图片</th>
					<td>
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
			</table>
		</form>
	</div>
</div>


