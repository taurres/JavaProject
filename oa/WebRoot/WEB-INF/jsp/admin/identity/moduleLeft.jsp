<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OA办公管理系统-模块管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
	<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
	<meta name="Description" content="网页信息的描述" />
	<meta name="Author" content="gdcct.gov.cn" />
	<meta name="Copyright" content="All Rights Reserved." />
	<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${path}/js/dtree/dtree.css"/>
	<script type="text/javascript" src="${path}/js/dtree/dtree.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			window.d = new dTree("d", "${path}/js/dtree/");
			//添加根节点
			d.add(-2, -1, "操作管理");
			d.add(0, -2, "全部","${path}/admin/identity/selectModule","全部操作","moduleRightFrame");
			//异步请求根据数据库内容加载节点
			//返回数据格式:[{id: ,pid: ,name: },{},{}]
			$.getJSON("${path}/admin/identity/loadModuleTree","post",function(data,status){
				if(status=="success"){
					$.each(data,function(){
						d.add(this.id,this.pid,this.name,"${path}/admin/identity/selectModule?parentCode="+this.id,this.name,"moduleRightFrame");
					});
				$(document.body).html(d.toString());
				}else{
					alert("加载失败");
				}
			});
			

		});
		
	</script>
</head>
<body>
	
</body>
</html>