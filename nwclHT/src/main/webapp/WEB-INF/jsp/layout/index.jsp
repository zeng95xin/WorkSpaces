<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
var NotifyNode;
var OpinionNode;
var releaseNode;
var maintenanceNode;
var housekeeperNode;
var hangNode;
var intoduceNode;
var sessionInfo_userId = '${sessionInfo.id}';
$(function(){
	if (sessionInfo_userId) {/*目的是，如果已经登陆过了，那么刷新页面*/
	$.post('${basePath}/web/indexPage/getInfo', {}, function(result){
		if (result.code=="SUCCESS"){
			var data = result.result;
			NotifyNode = data.notifyNode;
			OpinionNode = data.opinionNode;
			releaseNode = data.releaseNode;
			maintenanceNode = data.maintenanceNode;
			housekeeperNode = data.housekeeperNode;
			hangNode = data.hangNode;
			intoduceNode = data.intoduceNode;
			if(data.notifies.length > 0){
				var ans = data.notifies;
				var str = "";
				for (var i = 0; i < ans.length; i++) {
					str+="<p><span style='margin-left:10px;'>"+ans[i].title+"</span><span style='float:right;margin-right:10px;'>"+ans[i].rowAddTime+"</span></p>";
				}
				$("#p1").html(str);
			}
			if(data.opinions.length > 0){
				var ans = data.opinions;
				var str = "";
				for (var i = 0; i < ans.length; i++) {
					var content = ans[i].content;
					if(content.length > 20 ){
						content = content.substring(0,20)+"...";
					}
					str+="<p><span style='margin-left:10px;'>"+content+"</span><span style='float:right;margin-right:10px;'>"+ans[i].rowAddTime+"</span></p>";
				}
				$("#p2").html(str);
			}
			
			if(data.releases.length > 0){
				var ans = data.releases;
				var str = "";
				for (var i = 0; i < ans.length; i++) {
					var content = ans[i].thing;
					if(content.length > 20 ){
						content = content.substring(0,20)+"...";
					}
					str+="<p><span style='margin-left:10px;'>"+content+"</span><span style='float:right;margin-right:10px;'>"+ans[i].rowAddTime+"</span></p>";
				}
				$("#p4").html(str);
			}
			if(data.maintenances.length > 0){
				var ans = data.maintenances;
				var str = "";
				for (var i = 0; i < ans.length; i++) {
					var content = ans[i].description;
					if(content.length > 20 ){
						content = content.substring(0,20)+"...";
					}
					str+="<p><span style='margin-left:10px;'>"+content+"</span><span style='float:right;margin-right:10px;'>"+ans[i].rowAddTime+"</span></p>";
				}
				$("#p5").html(str);
			}
			if(data.hMessages.length > 0){
				var ans = data.hMessages;
				var str = "";
				for (var i = 0; i < ans.length; i++) {
					var content = ans[i].content;
					if(content.length > 20 ){
						content = content.substring(0,20)+"...";
					}
					str+="<p><span style='margin-left:10px;'>"+content+"</span><span style='float:right;margin-right:10px;'>"+ans[i].rowAddTime+"</span></p>";
				}
				$("#p6").html(str);
			}
			if(data.hangPictures.length > 0){
				var ans = data.hangPictures;
				var str = "";
				for (var i = 0; i < ans.length; i++) {
					var content = ans[i].description;
					if(content.length > 20 ){
						content = content.substring(0,20)+"...";
					}
					str+="<p><span style='margin-left:10px;'>"+content+"</span><span style='float:right;margin-right:10px;'>"+ans[i].rowAddTime+"</span></p>";
				}
				$("#p7").html(str);
			}
			if(data.introducings.length > 0){
				var ans = data.introducings;
				var str = "";
				for (var i = 0; i < ans.length; i++) {
					var content = ans[i].title;
					if(content.length > 20 ){
						content = content.substring(0,20)+"...";
					}
					str+="<p><span style='margin-left:10px;'>"+content+"</span><span style='float:right;margin-right:10px;'>"+ans[i].rowAddTime+"</span></p>";
				}
				$("#p8").html(str);
			}
			
			
			
			if(data.housekeepers.length > 0){
				var ans = data.housekeepers;
				var str = "<p><b>当前值班管家</b></p>";
				for (var i = 0; i < ans.length; i++) {
					str+="<p><span style='margin-left:10px;'>"+ans[i].suerName+"</span><span style='float:right;margin-right:10px;'>"+ans[i].suerPhone+"</span></p>";
				}
				$("#p3").html(str);
			}
			if(data.repairmen.length > 0){
				var ans = data.repairmen;
				var str = "<p><b>当前值班维修人员</b></p>";
				for (var i = 0; i < ans.length; i++) {
					str+="<p><span style='margin-left:10px;'>"+ans[i].suerName+"</span><span style='float:right;margin-right:10px;'>"+ans[i].suerPhone+"</span></p>";
				}
				$("#p3").append(str);
			}
			if(data.security.length > 0){
				var ans = data.security;
				var str = "<p><b>当前值班保安</b></p>";
				for (var i = 0; i < ans.length; i++) {
					str+="<p><span style='margin-left:10px;'>"+ans[i].suerName+"</span><span style='float:right;margin-right:10px;'>"+ans[i].suerPhone+"</span></p>";
				}
				$("#p3").append(str);
			};
		}
		parent.$.messager.progress('close');
	}, 'JSON');
	
	}
});

function getNotifyPage(){
	var t = parent.$('#index_tabs');
	var iframe = '<iframe src="' + NotifyNode.memuUrl + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
	var opts = {
			title : NotifyNode.memuName,
			closable : true,
			iconCls : NotifyNode.icon,
			content : iframe,
			border : false,
			fit : true
		};
	
		if (t.tabs('exists', opts.title)){
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		}
}
function getOpinionPage(){
	var t = parent.$('#index_tabs');
	var iframe = '<iframe src="' + OpinionNode.memuUrl + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
	var opts = {
			title : OpinionNode.memuName,
			closable : true,
			iconCls : OpinionNode.icon,
			content : iframe,
			border : false,
			fit : true
		};
	
		if (t.tabs('exists', opts.title)){
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		};
}
function getPage(str){
	var data = null;
	if (str == 4) {
		data = releaseNode;
	}else if(str == 5){
		data = maintenanceNode;
	}else if(str == 6){
		data = housekeeperNode;
	}else if(str == 7){
		data = hangNode;
	}else if(str == 8){
		data = intoduceNode;
	}
	
	var t = parent.$('#index_tabs');
	var iframe = '<iframe src="' + data.memuUrl + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
	var opts = {
			title : data.memuName,
			closable : true,
			iconCls : data.icon,
			content : iframe,
			border : false,
			fit : true
		};
	
		if (t.tabs('exists', opts.title)){
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		};
}


</script>
<div class="" style="margin-top:5%;" id="divBody">
	<div style="margin-left:20%;float: left">
		<div>
			<div id="p1" class="easyui-panel" title="&nbsp;消息通知"  
				style="width:500px;height:140px;padding:10px;background:#fafafa;"
				data-options="iconCls:'bell',closable:false,
   			collapsible:false,minimizable:false,maximizable:false,tools:'#tt1'">
			</div>
			
			<br />
			<div id="p2" class="easyui-panel" title="&nbsp;意见反馈"
				style="width:500px;height:140px;padding:10px;background:#fafafa;"
				data-options="iconCls:'comment',closable:false,tools:'#tt2',
    		collapsible:false,minimizable:false,maximizable:false">
			</div>
			
			<br />
			<div id="p4" class="easyui-panel" title="&nbsp;放行条"
				style="width:500px;height:140px;padding:10px;background:#fafafa;"
				data-options="iconCls:'text_padding_bottom',closable:false,tools:'#tt4',
    		collapsible:false,minimizable:false,maximizable:false">
			</div>			
		</div>
	</div>
	<div style="margin-left:20px;float: left">
		<div id="p3" class="easyui-panel" title="&nbsp;事务提醒"
			style="width:300px;height:460px;padding:10px;background:#fafafa;"
			data-options="iconCls:'attach',closable:false,
   				collapsible:false,minimizable:false,maximizable:false">
		</div>
	</div>
	
	<!-- 下面框 -->
	<br />
	<div style="margin-left:20%;margin-top:20px; float: left">
		<div >
			<div id="p5" class="easyui-panel" title="&nbsp;维修单"
				style="width:400px;height:140px;padding:10px;background:#fafafa;float: left"
				data-options="iconCls:'layers',closable:false,tools:'#tt5',
	   		collapsible:false,minimizable:false,maximizable:false">
			</div>
			<div style="margin-top:20px"></div>
			<div id="p6" class="easyui-panel" title="&nbsp;管家留言"
				style="width:400px;height:140px;padding:10px;background:#fafafa;float: right;"
				data-options="iconCls:'comments',closable:false,tools:'#tt6',
	   		collapsible:false,minimizable:false,maximizable:false">
			</div>
		</div>
	</div>
		
	<br />
	<div style="margin-left:20px;margin-top:20px; float: left;">
		<div id="p7" class="easyui-panel" title="&nbsp;挂画管理"
			style="width:400px;height:140px;padding:10px;background:#fafafa;"
			data-options="iconCls:'color_wheel',closable:false,tools:'#tt7',
   		collapsible:false,minimizable:false,maximizable:false">
		</div>
		
		<div style="margin-top:20px"></div>
		<div id="p8" class="easyui-panel" title="&nbsp;业主自荐"
			style="width:400px;height:140px;padding:10px;background:#fafafa;"
			data-options="iconCls:'emoticon_smile',closable:false,tools:'#tt8',
   		collapsible:false,minimizable:false,maximizable:false">
		</div>
	</div>
</div>



















<div id="tt1">
		<a href="javascript:void(0)" class="icon-check"   title="查看更多"  onclick="javascript:getNotifyPage()"></a>
</div>
<div id="tt2">
		<a href="javascript:void(0)" class="icon-check"   title="查看更多"  onclick="javascript:getOpinionPage()"></a>
</div>
<div id="tt4">
		<a href="javascript:void(0)" class="icon-check"   title="查看更多"  onclick="javascript:getPage(4)"></a>
</div>
<div id="tt5">
		<a href="javascript:void(0)" class="icon-check"   title="查看更多"  onclick="javascript:getPage(5)"></a>
</div>
<div id="tt6">
		<a href="javascript:void(0)" class="icon-check"   title="查看更多"  onclick="javascript:getPage(6)"></a>
</div>
<div id="tt7">
		<a href="javascript:void(0)" class="icon-check"   title="查看更多"  onclick="javascript:getPage(7)"></a>
</div>
<div id="tt8">
		<a href="javascript:void(0)" class="icon-check"  title="查看更多"  onclick="javascript:getPage(8)"></a>
</div>