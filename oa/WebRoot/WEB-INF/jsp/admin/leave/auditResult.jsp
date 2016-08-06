<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-审批意见</title>
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
			
			// 返回按钮 
			$("#back_btn").click(function(){
				window.location.href = "${path}/admin/leave/selectUserLeave.jspx?pageModel.pageIndex=${pageModel.pageIndex}";
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
			<th>编号</th>
			<th>审批人</th>
			<th>职位</th>
			<th>是否通过</th>
			<th>审批意见</th>
			<th>审批时间</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="leaveAudits" status="stat">
				<tr id="tr_${stat.index}" class="listTr">
					<td><s:property value="id"/></td>
					<td><s:property value="checker.name"/></td>
					<td><s:property value="checker.job.name"/></td>
					<td>${status == 1 ? '同意' : '不同意'}</td>
					<td><s:property value="remark"/></td>
					<td><s:date name="checkDate" format="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	
</body>
</html>