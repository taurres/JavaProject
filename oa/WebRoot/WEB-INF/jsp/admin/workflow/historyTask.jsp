<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-模块管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${path}/css/common/admin.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
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
			
			//点击添加按钮弹出添加角色窗口
			$("#addModule").click(function(){
				$("#divDialog").dialog({    
					title: "添加操作",   // 标题  
					width: 370,   // 宽度
					height: 245,   // 高度
					modal: true, // 模态窗口
					collapsible : true, // 可伸缩
					minimizable : false, // 最小化
					maximizable : true, // 最大化
					onClose : function(){
						// 刷新左边的
						parent.moduleLeftFrame.location.reload();
						window.location.href = "${path}/admin/identity/selectModule?pageModel.pageIndex=${pageModel.pageIndex}&parentCode=${parentCode}";
					}
				});
				$("#iframe").attr("src", "${path}/admin/identity/showAddModule?parentCode=${parentCode}").fadeIn(200);
			});
			
			// 点击按钮修改用户
			$("#updateModule").click(function(){
				var boxs = $("input[type='checkbox'][id^='box_']:checked");
				if (boxs.length == 0){
					alert("请选择要修改的操作！");
				}else if (boxs.length == 1){
					$("#divDialog").dialog({    
						title: "修改操作",   // 标题  
						width: 370,   // 宽度
						height: 245,   // 高度
						modal: true, // 模态窗口.
						collapsible : true, // 可伸缩
						minimizable : false, // 最小化
						maximizable : true, // 最大化
						onClose : function(){
							// 刷新左边的树 
							parent.moduleLeftFrame.location.reload();
							window.location.href = "${path}/admin/identity/selectModule?pageModel.pageIndex=${pageModel.pageIndex}&parentCode=${parentCode}";
						}
					});
					$("#iframe").attr("src", "${path}/admin/identity/showUpdateModule?module.code=" + boxs.val()).fadeIn(200);
				}else{
					alert("修改操作时，只能选择一个！");
				}
			});
			
			//点击按钮删除用户
			$("#deleteModule").click(function(){
				var boxs = $("input[type='checkbox'][id^='box_']:checked");
				if (boxs.length == 0){
					alert("请选择要删除的操作！");
				}else{
					if (confirm("您确定要删除吗？")){
						var codes = boxs.map(function(){
							return this.value;
						});
						window.location.href = "${path}/admin/identity/deleteModule?pageModel.pageIndex=${pageModel.pageIndex}&parentCode=${parentCode}&codes=" + codes.get();
					}
				}
			});
			
			if ("${msg}" != ""){
				parent.moduleLeftFrame.location.reload();
			}
			
			//返回按钮
			$("#back_btn").click(function(){
				window.location.href = "${path}/admin/leave/selectUserLeave?pageModel.pageIndex=${pageModel.pageIndex}"
			});
			
		});
	</script>
</head>
<body>
	<!-- 工具按钮区 -->
		<table>
			<tr>
				<td><input type="button" value="返回" id="back_btn"/></td>
			</tr>
		</table>
	
	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th>任务编号</th>
			<th>任务名称</th>
			<th>任务处理人</th>
			<th>任务开始日期</th>
			<th>任务结束日期</th>
			<th>任务处理时间</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="historicTaskInstances" status="stat" >
				<tr id="tr_${stat.index}" class="listTr">
					<td><s:property value="id"/></td>
					<td><s:property value="name"/></td>
					<td><s:property value="assignee"/></td>
					<td><s:date name="startTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:property value="workTimeInMillis"/></td>
				</tr>
			</s:iterator>
			
		</tbody>
	</table>
</body>
</html>