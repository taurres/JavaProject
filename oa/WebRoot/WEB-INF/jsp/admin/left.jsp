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
	
			//根据权限生成菜单树 数据格式[{id : '', pid : '', name : '', url : ''},{}]
			window.d = new dTree("d","${path}/js/dtree/");
			d.add(0, -1, "办公管理系统");
			$.getJSON("${path}/admin/identity/loadMenuTree",function(data){
				$.each(data,function(){
					d.add(this.id, this.pid, this.name, "javascript:parent.mainframe.addTab('" + this.name + "','"+ this.url+"')", this.name);
				});
			$("#shumenu").html(d.toString());
			d.openAll();
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
					
				    //d.add(21, 2, "用户管理", "javascript:parent.mainframe.addTab('用户管理','/admin/identity/selectUser')", "用户管理");
				</script>
			</div>
	    </div>
    </div>
  </body>
</html>
