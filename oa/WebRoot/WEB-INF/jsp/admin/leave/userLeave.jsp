<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-休假管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${path}/css/common/admin.css" type="text/css" rel="stylesheet"/>
	<link href="${path}/css/common/pager.css" type="text/css" rel="stylesheet"/>
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
				var box = $(this).find("input[id^='box_']");
				if(! box.attr("checked")){
					$(this).css("background-color","white");
				}
				
			});
			
			//异步加载审核状态 [{code:0, name:"新建"},{},{}]
			$.ajax({
				url: "${path}/admin/leave/loadLeaveType",
				type: "post",
				dataType: "json",
				success: function(data){
					$.each(data,function(){
						$("<option/>").val(this.code)
									  .text(this.name)
									  .attr("selected", this.code == "${leaveItem.leaveType.code}")
									  .appendTo("#leaveType");
					});
				},
				error: function(){
					alert("加载失败");
				}
			});
			
			//点击添加按钮弹出添加角色窗口
			$("#addLeaveItem").click(function(){
				$("#divDialog").dialog({
					title: "添加休假",
					width: 500,
					height: 320,
					collapsible : true,
					maximizable : true,
					modal: true,
					onClose : function(){
						window.location.href = "${path}/admin/leave/selectUserLeave?msg=${msg}&pageModel.pageIndex=${pageModel.pageIndex}";
					}
				});
				$("#iframe").attr("src", "${path}/admin/leave/showAddLeaveItem").fadeIn(200);
			});
				
		});
		
		//点击链接查看流程图
		var showProcessDiagram = function(processInstanceId){
			$("#divDialog").dialog({    
				title: "请假-流程图",   // 标题  
				width: 850,   // 宽度
				height: 500,   // 高度
				modal: true, // 模态窗口
				collapsible : true, // 可伸缩
				minimizable : false, // 最小化
				maximizable : true // 最大化
			});
			$("#iframe").attr("src", "${path}/admin/workflow/showProcessDiagram?processInstanceId=" + processInstanceId).fadeIn(200);
		};
	</script>
</head>
<body>
	<!-- 工具按钮区 -->
	<form action="${path}/admin/leave/selectUserLeave" method="post" >
		<table>
			<tr>
				<td><input type="button" value="添加" id="addLeaveItem"/></td>
				<td>假期类型:<select id="leaveType"
				name="leaveItem.leaveType.code"><option>请选择</option></select></td>
				<td><input type="submit" value="查询"/></td>
				<td><span>${msg}</span><td>
			</tr>
		</table>
	</form>
	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th>请假类型</th>
			<th>请假人</th>
			<th>请假原因</th>
			<th>开始日期</th>
			<th>结束日期</th>
			<th>请假小时数</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="leaveItems" status="stat">
			<tr id="tr_${stat.index}" class="listTr">
				<td><s:property value="leaveType.name"/></td>
				<td><s:property value="creater.name"/></td>
				<td><s:property value="leaveCase"/></td>
				<td><s:date name="beginDate" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:date name="endDate" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:property value="leaveHour"/></td>
				<td>
					<s:if test="status == 0">
						<font>新建</font>
					</s:if>
					<s:elseif test="status == 1">
						<font color="green">审核通过</font>
					</s:elseif>
					<s:else>
						<font color="red">不通过</font>
					</s:else>
				</td>
				<td>
					<s:if test="status == 0">
						<a href="javascript:void(0)" onclick="showProcessDiagram(${procInstanceId})">查看流程图</a>
					</s:if>
					<s:else>
						<a href="javascript:void(0)">查看历史任务</a>
					</s:else>
					<a href="javascript:void(0)">| 审批意见</a>
				</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
	<!-- 分页标签区 -->
		<page:pager pageIndex="${pageModel.pageIndex}" 
		pageSize="${pageModel.pageSize}" 
		recordCount="${pageModel.recordCount}" 
		submitUrl="${path}/admin/leave/selectUserLeave?pageModel.pageIndex={0}&leaveItem.leaveType.code=${leaveItem.leaveType.code}"/>
	<!-- div作为弹出窗口 -->
    <div id="divDialog" style="overflow: hidden;">
		<iframe id="iframe" frameborder="0" width="100%" height="100%" style="display:none;"></iframe>
	</div>
</body>
</html>