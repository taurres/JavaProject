<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>办公管理系统-添加休假</title>
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
		<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			$(function(){
				// 异步加载假期类型与流程定义 [[{leaveType},{},{}...], [{process},{},{}...]]
				$.ajax({
				url: "${path}/admin/leave/loadLeaveTypeAndProcess",
				type: "post",
				dataType: "json",
				success: function(data){
					//加载LeaveType
					$.each(data[0],function(){
						$("<option/>").val(this.code)
									  .text(this.name)
									  .appendTo("#leaveType");						
					});
					//加载process
					$.each(data[1],function(){
						$("<option/>").val(this.id)
									  .text(this.name)
									  .appendTo("#process");						
					});
				},
				error: function(){
					alert("加载失败");
				}
			});
			
				// 为表单绑定onsubmit事件
				$("#addLeaveItemForm").submit(function(){
					var beginDate = $("#beginDate");
					var endDate = $("#endDate");
					var leaveHour = $("#leaveHour");
					var leaveCase = $("#leaveCase");
					var msg = "";
					if ($.trim(beginDate.val()) == ""){
						msg = "开始日期不能为空！";
						beginDate.focus();
					}else if ($.trim(endDate.val()) == ""){
						msg = "结束日期不能为空！";
						endDate.focus();
					}else if ($.trim(beginDate.val()) > $.trim(endDate.val())){
						msg = "开始日期不能大于结束日期！";
					}else if ($.trim(leaveHour.val()) == ""){
						msg = "请假小时不能为空！";
						leaveHour.focus();
					}else if (!/(^0\.[1-9]\d*$)|(^[1-9]\d*(\.\d)?$)/.test($.trim(leaveHour.val()))){
						msg = "请假小时格式不正确！";
						leaveHour.focus();
					}else if ($.trim(leaveCase.val()) == ""){
						msg = "请假原因不能为空！";
						leaveCase.focus();
					}
					if (msg != ""){
						alert(msg);
						return false;
					}else{
						return true;
					}
				});
			});
		</script>
	</head>
<body>
	<!-- 输出防表单重复提交的提示信息 -->
	<s:actionerror cssStyle="font-size:12px;color:red;"/>
	<!-- 输出后台校验失败的提示信息 -->
	<s:fielderror cssStyle="font-size:12px;color:red;"/>
	<form id="addLeaveItemForm" action="${path}/admin/leave/addLeaveItem" method="post">
		<!-- 防表单重复提交需要传的token -->
		<s:token></s:token>
		<table align="center">
			<tr><td colspan="4"></td></tr>
			<tr>
				<td>假期类型：</td>
				<td>
					<select id="leaveType" name="leaveItem.leaveType.code"></select>
				</td>
			</tr>
			<tr>
				<td>开始日期：</td>
				<td>
					<input type="text" name="leaveItem.beginDate" id="beginDate"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						class="Wdate"/>
				</td>
			</tr>
			<tr>
				<td>结束日期：</td>
				<td>
					<input type="text" name="leaveItem.endDate" id="endDate"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						class="Wdate"/>
				</td>
			</tr>
			<tr>
				<td>请假小时：</td>
				<td>
					<input type="text" name="leaveItem.leaveHour" id="leaveHour"/>
				</td>
			</tr>
			<tr>
				<td>选择流程：</td>
				<td>
					<select id="process" name="leaveItem.procInstanceId"></select>
				</td>
			</tr>
			<tr>
				<td>请假原因：</td>
				<td>
					<textarea name="leaveItem.leaveCase"  cols="40" rows="5" id="leaveCase"></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input value="提 交" type="submit"/>
					&nbsp;
					<input value="重 置" type="reset" />&nbsp;<font color="red">${msg}</font>
				</td>
			</tr>
		
		</table>
	</form>
</body>
</html>	