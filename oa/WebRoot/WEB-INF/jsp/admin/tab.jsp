<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	<link rel="stylesheet" type="text/css" href="${path}/js/jqeasyui/themes/bootstrap/easyui.css"/>
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${path}/js/jqeasyui/jquery.easyui.min.js"></script>
  	<style type="text/css">
		html, body {
		width : 100%;
		height : 100%;
		padding : 0;
		margin : 0;
		overflow : hidden;
	</style>
	<script type="text/javascript">
		$(function(){
		
			//设置tab大小为适应frame
			$("#tab").tabs({
				fit: true,
				onSelect: function(title,index){
				 	var tab = $("#tab").tabs("getSelected");
				 	tab.panel("refresh");
				 }	
			});
			
			//创建用户信息tab，该tab不可关闭
			$("#tab").tabs("add", {
				 title : "用户信息",  // 标题
				 content : "用户信息",  // 内容
				 closable : false // 是否可以关闭
			});
			

		});
		
		//创建新tab的方法
		var addTab = function(title, url){
			//获取存在状态
			var isExisted = $("#tab").tabs("exists",title);
			//判断是否已经存在tab
			if(isExisted){
				//存在则选中已有的tab
				$("#tab").tabs("select",title);
			}else{
				//不存在则根据传入数据创建新tab
				$("#tab").tabs("add",{
					title:title,
					content:"<iframe width='100%' height='100%' src='${path}"+ url +"' frameborder='0'></iframe>",
					closable:true
				});
			}
		};
		//显示更改密码窗口的函数
		var showPwdDialog = function(){
			$("#divDialog").dialog({    
				title: "修改密码",   // 标题  
				width: 370,   // 宽度
				height: 225,   // 高度
				modal: true, // 模态窗口.
				collapsible : true // 可伸缩
			});
			$("#iframe").attr("src", "${path}/admin/password.jspx").fadeIn(200);
			};
		
	</script>
</head>
<body>
<div id="tab"></div>
 <div id="divDialog" style="overflow: hidden;">
		<iframe id="iframe" frameborder="0" width="100%" height="100%" style="display:none;"></iframe>
</div>
</body>
</html>