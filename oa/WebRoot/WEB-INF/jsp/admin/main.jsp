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
</head>
	<frameset rows="82,*" cols="*" id="tstMain" frameborder="no" border="0" framespacing="0">
		<frame src="${path}/admin/head" name="topframe"  id="topframe" />
		<frameset id="fstMain" border="0" cols="148,*" rows="*" frameborder="no">
    		<frame src="${path}/admin/left" name="leftframe" scrolling="no" id="leftframe"/>
	        <frameset border="0" rows="*,28" cols="*" frameborder="no">
	            <frame src="${path}/admin/tab" name="mainframe" id="mainframe"/>
	            <frame src="${path}/admin/button">
	        </frameset>
	    </frameset>
	</frameset>
	<noframes>
		<body style="margin:0;">
		</body>
	</noframes>
</html>