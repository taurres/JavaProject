<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>办公管理系统-添加用户</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
		<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
		<meta name="Description" content="网页信息的描述" />
		<meta name="Author" content="fkjava.org" />
		<meta name="Copyright" content="All Rights Reserved." />
		<link href="${path}/css/common/admin.css" type="text/css" rel="stylesheet"/>
		<link href="${path}/js/jqeasyui/themes/bootstrap/easyui.css" type="text/css" rel="stylesheet"/>
		<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
		<script type="text/javascript" src="${path}/js/jqeasyui/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			$(function(){
			
				//  提交表单
				$("#btn_submit").click(function(){
					// 校验表单数据
					var name = $("#name");
					var remark = $("#remark");
					var msg = "";
					if ($.trim(name.val()) == ""){
						msg = "角色名称不能为空！";
						name.focus();
					}else if ($.trim(remark.val()) == ""){
						msg = "备注不能为空！";
						remark.focus();
					}
					// 直接提交
					if (msg != ""){
						alert(msg);
					}else{
						// 提交表单 
						$("#addContactGroupForm").submit();
					}
				});
				
				if("${msg}" == "success") {
					parent.$("#divDialog").window("close");
					alert("添加成功");
				}
			});
		</script>
	</head>
<body>
	<table align="center">
		<!-- 输入表单 -->
		<s:form id="addContactGroupForm" action="/admin/addressbook/addContactGroup" method="post" theme="simple">
			<!-- 防表单重复提交需要传的token -->
			<s:token></s:token>
			<tr><td colspan="4"></td></tr>
			<tr>
				<td>角色名称：</td>
				<td>
					<s:textfield name="contactGroup.name" size="33" id="name"/>
				</td>
			</tr>
			<tr>
				<td>角色备注：</td>
				<td>
					<s:textarea name="contactGroup.remark"  cols="33" rows="5" id="remark"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input value="提 交" type="button" id="btn_submit" />
					&nbsp;
					<input value="重 置" type="reset" />&nbsp;<font color="red">${tip}</font>
				</td>
			</tr>
		</s:form>
	</table>
</body>
</html>	