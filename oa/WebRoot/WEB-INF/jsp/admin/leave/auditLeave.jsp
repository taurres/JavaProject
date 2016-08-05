<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-用户假期管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${path}/css/common/admin.css" type="text/css" rel="stylesheet"/>
	<link href="${path}/js/jqeasyui/themes/bootstrap/easyui.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${path}/js/jqeasyui/jquery.easyui.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
			
			//鼠标经过变色
			$("tr[id^='tr_']").hover(function(){
				$(this).css("background-color","#FFFACD");
			}, function(){
				//如果不是选中的行就触发mouseout事件
				var radio = $(this).find("input[id^='radio_']");
				if(! radio.attr("checked")){
					$(this).css("background-color","white");
				}
				
			});
			
			
			// 为审批按钮绑定点击事件 
			$("#auditLeave").click(function(){
				// 获取选中的radio 
				var boxs = $("input[id^='radio']:checked");
				if (boxs.length == 1){
					$("#divDialog").dialog({    
						title: "审核休假",   // 标题  
						width: 365,   // 宽度
						height: 245,   // 高度
						modal: true, // 模态窗口
						collapsible : true, // 可伸缩
						minimizable : false, // 最小化
						maximizable : true, // 最大化
						onClose : function(){
							// 刷新页面 
							window.location.href = "${path}/admin/leave/selectAuditLeave";
						}
					});
					// 获取radio中的value 
					var arr = boxs.val().split(",");
					$("#iframe").attr("src", "${path}/admin/leave/showAudit?leaveItem.id=" + arr[0] + "&leaveItem.taskId=" + arr[1]).fadeIn(200);
				}else{
					alert("请选择要审批请假单！");
				}
			});
		});
	
	</script>
</head>
<body>
	
	<!-- 工具按钮区 -->
	<table>
		<tr>
			<td><input type="button" value="审批" id="auditLeave"/>
				&nbsp;<font color="red">${tip}</font></td>
		</tr>
	</table>

		
	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th>选择</th>
			<th>请假类型</th>
			<th>请假原因</th>
			<th>开始日期</th>
			<th>结束日期</th>
			<th>请假小时数</th>
			<th>请假人</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="leaveItems" status="stat">
				<tr id="tr_${stat.index}" class="listTr">
					<td><input type="radio" name="radio" id="radio_${stat.index}" 
						value="${id},${taskId}"/>${stat.count}</td>
					<td><s:property value="leaveType.name"/></td>
					<td><s:property value="leaveCase"/></td>
					<td><s:date name="beginDate" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:date name="endDate" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:property value="leaveHour"/></td>
					<td><s:property value="creater.name"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	
	<!-- 用div作为弹出窗体 -->
	<div id="divDialog" style="overflow: hidden;">
		<iframe width="100%" height="100%" id="iframe" frameborder="0" style="display:none;"></iframe>
	</div>
</body>
</html>