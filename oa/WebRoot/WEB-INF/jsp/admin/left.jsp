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
    <link rel="stylesheet" type="text/css" href="${path}/js/dtree/dtree.css"/>
    <script type="text/javascript" src="${path}/js/dtree/dtree.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			//隐藏菜单栏
			var leftFrame = $("#fstMain", parent.document);
			$("#showNav").toggle(
				function(){
					leftFrame.attr("cols","20,*");
					$("#shumenu").fadeOut(100);
					$(this).attr("src", "${path}/images/system/left_xs.gif");
					$("#shumenu").attr("title", "显示菜单栏");
				}, 
				function(){
					leftFrame.attr("cols","148,*");
					$("#shumenu").fadeIn(100);
					$(this).attr("scr", "${path}/images/system/left_yc.gif");
					$("#shumenu").attr("title", "隐藏菜单栏");
			});
		});
	</script>
	<style type="text/css">
		html,body{ height:100%;}
		a{color:#6a6f71; text-decoration:none;}
	</style>	
	
  </head>
 <body class="leftmenubody">
 	<div class="topdivyc">
    	<a href="javascript:void(0);" class="a_link" title="隐藏菜单栏" id="nav_title"><img src="${path}/images/system/left_yc.gif" id="showNav" /><!--隐藏时反显示的图片<img src="images/left_xs.gif" />--></a>
    </div>
    <div class="bodytextmenu" id="shumenu">
	    <div class="shumenu" >
	    	<div >
				<script type="text/javascript">
					var d = new dTree("d","${path}/js/dtree/");
					//使用js代码调用mainFrame中的addTab方法添加tab
					d.add(1, -1, "办公管理系统");
				    d.add(2, 1, "系统管理");
				    d.add(21, 2, "用户管理", "javascript:parent.mainframe.addTab('用户管理','/admin/identity/selectUser')", "用户管理");
				    d.add(22, 2, "角色管理", "javascript:parent.mainframe.addTab('角色管理','/admin/login')", "角色管理");
				    d.add(23, 2, "操作管理", "javascript:parent.mainframe.addTab('操作管理','/admin/login')", "操作管理");
				    d.add(24, 2, "部门管理");
				    d.add(25, 2, "职位管理");
				     
				    d.add(3, 1, "假期管理");
				    d.add(31, 3, "假期类型");
				    d.add(32, 3, "假期明细");
				    d.add(33, 3, "用户请假");
				    d.add(34, 3, "假期审批");
				    
				    d.add(4, 1, "流程管理");
				    d.add(41, 4, "流程部署");
				    d.add(42, 4, "总署管理");
				    
				    d.add(5, 1, "通讯录管理");
				    d.add(51, 5, "联系组管理");
				    d.add(52, 5, "联系人管理");
				    					
					document.write(d);
					d.openAll();
				</script>
			</div>
	    </div>
    </div>
  </body>
</html>
