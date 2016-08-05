<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>办公管理系统-审批</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
		<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
		<meta name="Description" content="网页信息的描述" />
		<meta name="Author" content="fkjava.org" />
		<meta name="Copyright" content="All Rights Reserved." />
		<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
		<link href="${path}/css/common/admin.css" type="text/css" rel="stylesheet"/>
		<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
		<script type="text/javascript">
			$(function(){
				
				// 输出提示信息 
				if ("${msg}" != ""){
					alert("${msg}");
					/** 让父窗口重新加载 */
					parent.location.reload();
				}
				
				// 为同意按钮绑定事件 
				$("#btn_1").click(function(){
					$("#status").val(1);
					formSubmitFn();
				});
				// 为不同意按钮绑定事件 
				$("#btn_2").click(function(){
					$("#status").val(2);
					formSubmitFn();
				});
				
				var formSubmitFn = function(){
					var remark = $("#remark");
					if ($.trim(remark.val()) == ""){
						alert("审批说明不能为空！");
					}else{
						$("#auditLeaveForm").submit();
					}
				};
			});
		</script>
	</head>
<body>
	<table align="center">
		<!-- 输出防表单重复提交的提示信息 -->
		<s:actionerror cssStyle="font-size:12px;color:red;"/>
		<!-- 输出后台校验失败的提示信息 -->
		<s:fielderror cssStyle="font-size:12px;color:red;"/>
		
		<s:form id="auditLeaveForm" action="/admin/leave/audit" method="post" theme="simple">
			<!-- 防表单重复提交需要传的token -->
			<s:token></s:token>
			<input type="hidden" value="${leaveItem.id}" name="leaveAudit.leaveItem.id"/>
			<input type="hidden" value="${leaveItem.taskId}" name="taskId"/>
			<input type="hidden" name="leaveAudit.status" id="status"/>
			<tr><td colspan="4"></td></tr>
			<tr>
				<td>请假人：</td>
				<td>
					<font color="red">${leaveItem.creater.name}</font>
				</td>
			</tr>
			<tr>
				<td>请假原因：</td>
				<td>
					${leaveItem.leaveCase }
				</td>
			</tr>
			<tr>
				<td>审批说明：</td>
				<td>
					<s:textarea name="leaveAudit.remark"  cols="33" rows="5" id="remark"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input value="同意" type="button" id="btn_1" />
					&nbsp;
					<input value="不同意" type="button" id="btn_2"/>
				</td>
			</tr>
		</s:form>
	</table>
</body>
</html>	