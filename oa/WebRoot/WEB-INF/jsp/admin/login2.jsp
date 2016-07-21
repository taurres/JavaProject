<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>办公管理系统-登录页面</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
	<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
	<meta name="Description" content="网页信息的描述" />
	<meta name="Author" content="gdcct.gov.cn" />
	<meta name="Copyright" content="All Rights Reserved." />
	<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<style type="text/css">
		body{ 
			background-repeat: repeat; 
			background-positon: 100%, 100%; 
		} 
		li{
			margin-left:20px;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			//看不清楚按钮绑定点击事件
			$("#unclear").click(function(){
				$("#img").attr("src", "${path}/verifyCode?random=" + Math.random());
			});
			
			//点击验证码图片更换验证码
			$("#img").on("click", function(){
				$("#unclear").trigger("click");
			}).on("mouseover",function(){
				$(this).css("cursor", "pointer");
			});
			
			//按回车提交表单
			$(document).keyup(function(event){
				if(event.keyCode == 13){
					$("#vcode").blur();
					$(login_btn).trigger("click");
				}
			});
			
			//验证userId
			var usermsg = "";
			$("#userId").on("blur",function(){
				if($(this).val()=="") {
					//判断用户名是否为空
					usermsg = "请输入用户名";
					$(this).attr("value",usermsg);
					$(this).css({"background-color":"#FFDEDE"});
				}else if(!/^\w{5,20}$/.test($(this).val())){
					//判断用户名格式
					usermsg = "请输入5-20个字符";
					$(this).attr("value",usermsg);
					$(this).css({"background-color":"#FFDEDE"});
				}
			}).on("focus",function(){
				if($(this).css("background-color") == "rgb(255, 222, 222)"){
					$(this).attr("value",null);
					$(this).css({"background-color":"WHITE"});
					usermsg="";
				}
			});
			
			//验证password
			var pwmsg = "";
			$("#password").on("blur",function(){
				if($(this).val()=="") {
					//判断密码是否为空
					pwmsg = "请输入密码";
					$(this).attr("type","text");
					$(this).attr("value",pwmsg);
					$(this).css({"background-color":"#FFDEDE"});
				}else if(!/^[A-Za-z0-9]{6,20}$/.test($(this).val())){
					//判断密码格式
					pwmsg = "请输入6-20个字符";
					$(this).attr("type","text");
					$(this).attr("value",pwmsg);
					$(this).css({"background-color":"#FFDEDE"});
				}
			}).on("focus",function(){
				if($(this).css("background-color") == "rgb(255, 222, 222)"){
					$(this).attr("type","password");
					$(this).attr("value","");
					$(this).css({"background-color":"WHITE"});
					pwmsg="";
				}
			});
			
			//检验验证码
			var vcmsg = "";
			$("#vcode").on("blur",function(){
				if($(this).val()=="") {
					//判断是否为空
					vcmsg = "不能为空";
					$(this).attr("value",vcmsg);
					$(this).css({"background-color":"#FFDEDE"});
				}else if(!/^[A-Za-z0-9]{4}$/.test($(this).val())){
					//判断格式
					vcmsg = "长度4位";
					$(this).attr("value",vcmsg);
					$(this).css({"background-color":"#FFDEDE"});					
				}
			}).on("focus",function(){
				if($(this).css("background-color") == "rgb(255, 222, 222)"){
					$(this).attr("value","");
					$(this).css({"background-color":"WHITE"});
					vcmsg="";
				}
			});
			
			//表单提交验证
			$("#login_btn").click(function(){
				$("#unclear").trigger("click");
				//验证没有发生错误时发送异步请求
				if(usermsg=="" && pwmsg=="" && vcmsg=="") {
					//序列化表格数据
					var formData = $("#login_form").serialize();
					//进行异步请求
					$.ajax({
						url: "${path}/admin/loginAjax",
						type: "post",
						data: formData,
						dataType: "json",
						success:function(data){
							if(data.status == 0) {
								//成功则跳转到main页面
								window.location.href = "${path}/admin/main";
							}else if(data.status == 1){
								//提示验证码错误
								$("#vcode").attr("value","请重新输入");
								$("#vcode").css({"background-color":"#FFDEDE"});
								//1.5秒后聚焦到验证码输入框
								window.setTimeout(function() {
									$("#vcode").focus();
								}, 1500);
								
							}else if(data.status == 2){
								//提示用户名或密码错误
								$("#userId").attr("value","请重新输入");
								$("#userId").css({"background-color":"#FFDEDE"});
								$("#password").attr("type","text");
								$("#password").attr("value","请重新输入");
								$("#password").css({"background-color":"#FFDEDE"});
								//清空验证码输入框
								$("#vcode").attr("value","");
							}	
						},
						error:function(){
							alert("登录失败，请联系管理员");
						}
					});
				}
			});
		});
	</script>
</head>
<body background="${path}/images/login/9.png">
	<div align="center" style="height:100%">
		<form action="#" method="post" id="login_form">
			<table border="0" cellpadding="0" cellspacing="0" style="margin-top:120px;">
				<tr>
					<td colspan="2" width="447" height="104" style="background-image: url('${path}/images/login/1_01.jpg');"></td>
				</tr>
				<tr>
					<td width="92" height="200" style="background-image: url('${path}/images/login/1_02.gif');">&nbsp;</td>
					<td width="355" height="200" style="background-image: url('${path}/images/login/1_03.gif');">
						<table style="font-size:13px;margin:0 0 0 30px;" >
							<tr align="left">
								<td>用户名：</td>
								<td>
									<input type="text" name="userId" size="15" id="userId"/>
								</td>
								<td><span id="userIdMsg"></span></td>
								
							</tr>
							<tr align="left">
								<td>密&nbsp;码：</td>
								<td>
									<input type="password" name="password" size="15" id="password"/>
								</td>
								<td>
									<a href="javascript:void(0)" id="findpwd" style="font-size:12px;color:white;">忘了密码?</a>
								</td>
							</tr>
						   <tr align="left">
								<td>验证码：</td>
								<td>
									<div style="float:left;">
										<input type="text" name="vcode" size="7" maxlength="7" id="vcode"/>
									</div>
									<div style="float:left;padding:0 0 0 5px;">
										<img src="${path}/verifyCode" width="60" height="22" title="验证码" id="img"/>
									</div>
								</td>
								<td align="left">
									<a href="javascript:void(0);" id="unclear" style="font-size:12px;color:white;">看不清楚</a>
								</td>
						   </tr>
						   <tr align="left">
						   		<td>&nbsp;</td>
								<td colspan="2"><input type="checkbox" name="key" value="1" id="key"/>记住用户</td>
						   </tr>
						 
						  <tr align="center">
							<td colspan="3">
								<input type="button" value="登 录" width="67" height="22" id="login_btn"/>&nbsp;
								<input type="reset" value="重 置"/>
							</td>
						  </tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" width="447" height="34" style="background-image: url('${path}/images/login/1_04.gif');"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>