<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>办公管理系统-流程部署</title>
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
				
				// 表单提交
				$("#deploy_btn").click(function(){
					var name = $("#name");
					var bpmn = $("#bpmn");
					var msg = "";
					if ($.trim(name.val()) == ""){
						msg = "流程定义文件名称不能为空！";
						name.focus();
					}else if (bpmn.val() == ""){
						msg = "请选择需要上传的流程定义文件！";
					}
					if (msg != ""){
						alert(msg);
					}else{
						$("#deployForm").submit();
					}
				});
			});
		</script>
	</head>
<body>
	<br/>
	<s:actionerror cssStyle="color:red;font-size:12px;"/>
	<table align="center" class="editTable" cellpadding="8" cellspacing="1">
		<tbody style="background-color: #FFFFFF;">
			<s:form id="deployForm" action="/admin/workflow/deployProcess" method="post"
			 enctype="multipart/form-data" theme="simple">
				<s:token></s:token>
				<tr>
					<td width="16%">流程定义文件名称：</td>
					<td>
						<s:textfield id="name" name="name"  size="35"/>
					</td>
				</tr>
				<tr>
					<td>
						请选择需要上传的流程定义文件：
					</td>
					<td>
						<s:file id="bpmn" name="bpmn" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="部署" id="deploy_btn"/>&nbsp;
						<input type="reset" value="重置"/>&nbsp;<font color="red">${msg}</font>
					</td>
				</tr>		
			</s:form>
		</tbody>
	</table>
</body>
</html>

	
	