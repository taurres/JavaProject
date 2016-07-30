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
				/** 为表单绑定onsubmit事件 */
				$("#addModuleForm").submit(function(){
					var name = $("#name");
					var url = $("#url");
					var remark = $("#remark");
					var msg = "";
					if ($.trim(name.val()) == ""){
						msg = "名称不能为空！";
						name.focus();
					}else if ($.trim(url.val()) == ""){
						msg = "链接不能为空！";
						url.focus();
					}else if ($.trim(remark.val()) == ""){
						msg = "备注不能为空！";
						remark.focus();
					}
					if (msg != ""){
						alert(msg);
						return false;
					}else{
						return true;
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
		<!-- 输出防表单重复提交的提示信息 -->
		<s:actionerror cssStyle="font-size:12px;color:red;"/>
		<s:form id="addModuleForm" action="/admin/identity/addModule" method="post" theme="simple">
			<s:hidden name="parentCode"/>
			<!-- 防表单重复提交需要传的token -->
			<s:token></s:token>
			<tr><td colspan="4"></td></tr>
			<tr>
				<td>名称：</td>
				<td>
					<s:textfield name="module.name" size="38" id="name"/>
				</td>
			</tr>
			<tr>
				<td>链接：</td>
				<td>
					<s:textfield name="module.url" size="38" id="url"/>
				</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td>
					<s:textarea name="module.remark"  cols="36" rows="5" id="remark"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input value="提 交" type="submit"/>
					&nbsp;
					<input value="重 置" type="reset" />&nbsp;<font color="red"></font>
				</td>
			</tr>
		</s:form>
	</table>
</body>
</html>	