<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<title>办公管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
	<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
	<meta name="Description" content="网页信息的描述" />
	<meta name="Author" content="gdcct.gov.cn" />
	<meta name="Copyright" content="All Rights Reserved." />
	<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${path}/css/common/global.css"/>
	<link href="${path}/js/jqeasyui/themes/bootstrap/easyui.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-clock-1.0.js"></script>
	<script type="text/javascript" src="${path}/js/jqeasyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		$(function(){
			//clock动态时钟
			$("#time").getClock();
			//隐藏菜单
			//获取父页面中的Frame元素
			var headFrame = $("#tstMain", parent.document);
			$("#showNav").toggle(
				function(){
					headFrame.attr("rows","10,*");
					$("#headtitle").fadeOut(0);
					$(this).attr("src", "${path}/images/system/top_xs.gif");
					$("#nav_title").attr("title", "显示菜单栏");
				}, 
				function(){
					headFrame.attr("rows","82,*");
					$("#headtitle").fadeIn(100);
					$(this).attr("scr", "${path}/images/system/top_yc.gif");
					$("#nav_title").attr("title", "隐藏菜单栏");
			});
			
			//更改密码按钮
			$("#changepwd").click(function(){
				parent.mainframe.showPwdDialog();
			});
			//安全退出按钮退出登录
			$("#logout").click(function(){
				window.parent.location.href = "${path}/admin/logout";
			});
		});
	</script>
  </head>

 <body class="headbody">
	<div class="headtitle" id="headtitle">
    	<div class="headlogo"><img src="${path}/images/system/logo.gif" />
    	</div>
        <div class="headmenutop">
        	
        	<a class="headtopout" id="logout" title="退出系统" href="javascript:void(0);" >安全退出</a>
        	<a class="headtopout" id="changepwd" title="密码修改" href="javascript:void(0);" >密码修改</a>
			<div class="titlexx">
				<span style="font-size:14px;">${session_user.name}</span><font style="font-size:14px;">&nbsp;您好，欢迎使用本系统&nbsp;&nbsp;</font>
				<span id="time" style="color:#fff;font-size:14px;"></span>
				
			</div>
        </div>
    </div>
    <div class="yctopdiv">
      	<a href="javascript:void(0);" class="t_link" title="隐藏菜单栏" id="nav_title">
		<img src="${path}/images/system/top_yc.gif" id="showNav"/><!--隐藏时反显示的图片<img src="images/top_xs.gif" />--></a>
    </div>
       
  </body>
</html>