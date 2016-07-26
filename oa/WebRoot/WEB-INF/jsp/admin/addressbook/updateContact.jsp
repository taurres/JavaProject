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
		<link href="${path}/css/common/bootstrap.min.css" type="text/css" rel="stylesheet"/>
		<link href="${path}/css/datetimepicker/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet"/>
		<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
		<script type="text/javascript" src="${path}/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${path}/js/datetimepicker/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="${path}/js/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
		<script type="text/javascript">
			$(function(){
			
				//创建日期选择器
				$("#birthday").datetimepicker({
    				format: "yyyy-mm-dd", //格式
    				weekStart: "1",	//每周开始日
    				startDate: "1900-01-01", //可选择的最远日期
    				endDate: "2017-01-01", //可选择的最近日期
    				autoclose: true, //选择完成后自动关闭选择框
    				minView: "2",	//最小可以选择的时间单位(日)
    				language: "zh-CN" //使用语言
				});
				
				//加载contactGroup列表
				$.getJSON("${path}/admin/addressbook/loadContactTree","post",function(data){
					$.each(data, function(){
						$("<option/>").val(this[0]).text(this[1]).appendTo("#contactGroup")
						.attr("selected", this[0] == "${contact.contactGroup.id}"); //复选框选中正在查看的联系组
					});
					
				});

				
				// 为表单绑定onsubmit事件 
				$("#btn_submit").click(function(){
					var contactGroup = $("#contactGroup");
					var name = $("#name");
					var phone = $("#phone");
					var email = $("#email");
					var qqNum = $("#qqNum");
					var birthday = $("#birthday");
					var msg = "";
					if(contactGroup.val() == 0){
						msg = "请选择分组";
					}else if ($.trim(name.val()) == ""){
						msg = "姓名不能为空！";
						name.focus();
					}else if ($.trim(phone.val()) == ""){
						msg = "手机号码不能为为空！";
						url.focus();
					}else if ($.trim(email.val()) == ""){
						msg = "邮箱不能为空！";
						email.focus();
					}else if ($.trim(qqNum.val()) == ""){
						msg = "QQ号码不能为空！";
						qqNum.focus();
					}else if ($.trim(birthday.val()) == ""){
						msg = "生日不能为空！";
						birthday.focus();
					}
					
					if (msg != ""){
						alert(msg);
					}else{
						$("#addContactForm").submit();
					}
				});
				
				if("${msg}" == "success") {
					parent.$("#divDialog").window("close");
					alert("修改成功");
				}
				
			});
		</script>
	</head>
<body>
	<table align="center">
		<s:actionerror cssStyle="font-size:12px;color:red;"/>
		<!-- 输入表单 -->
		<s:form id="updateContactForm" action="/admin/addressbook/updateContact" method="post" theme="simple">
			<!-- 防表单重复提交需要传的token -->
			<s:token></s:token>
			<s:hidden name="contact.id"/>
			<tr><td colspan="4"></td></tr>
			<tr>
				<td>分组：</td>
				<td>
					<select name="contactGroup" id="contactGroup" >
						<option value="0">请选择分组</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td>
					<s:textfield class="form-control"  name="contact.name" size="38" id="name"/>
				</td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					<s:select class="form-control" list="#{1:'男',2 :'女'}" cssStyle="width:100px;" name="contact.sex"></s:select>
				</td>
			</tr>
			<tr>
				<td>手机号码：</td>
				<td>
					<s:textfield class="form-control"  name="contact.phone" size="38" id="phone"/>
				</td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td>
					<s:textfield class="form-control"  name="contact.email" size="38" id="email"/>
				</td>
			</tr>
			<tr>
				<td>QQ号码：</td>
				<td>
					<s:textfield class="form-control"  name="contact.qqNum" size="38" id="qqNum"/>
				</td>
			</tr>
			<tr>
				<td>生日：</td>
				<td>
					<s:textfield name="contact.birthday" size="38" id="birthday" class="form-control" readonly="true">
					<!-- 配置时间格式 -->
						<s:param name="value"><s:date name="contact.birthday" format="yyyy-MM-dd"/></s:param>
					</s:textfield>
				</td>
			</tr>		
			<tr>
				<td colspan="2" align="center">
					<input value="提 交" type="submit" />
					&nbsp;
					<input value="重 置" type="reset" />&nbsp;<font color="red">${msg}</font>
				</td>
			</tr>
		</s:form>
	</table>
</body>
</html>	