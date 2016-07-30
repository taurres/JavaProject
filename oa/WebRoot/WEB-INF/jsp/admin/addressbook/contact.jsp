<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-模块管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${path}/css/common/admin.css" type="text/css" rel="stylesheet"/>
	<link href="${path}/css/common/pager.css" type="text/css" rel="stylesheet"/>
	<link href="${path}/js/jqeasyui/themes/bootstrap/easyui.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${path}/js/jqeasyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		$(function(){
			
			//鼠标经过变色
			$("tr[id^='tr_']").hover(function(){
				$(this).css("background-color","#FFFACD");
			}, function(){
				//如果不是选中的行就触发mouseout事件
				var box = $(this).find("input[id^='box_']");
				if(! box.attr("checked")){
					$(this).css("background-color","white");
				}
				
			});
			
			//全选按钮 全选后全部行颜色改变
			$("#checkAll").click(function(){
				$("input[id^='box_']").attr("checked", this.checked);
				$("tr[id^='tr_']").trigger(this.checked? "mouseover" : "mouseout");
			});
			
			//选中按钮全部选中后，自动选中全选按钮
			var boxes = $("input[id^='box_']");
			boxes.click(function(){
				$("#checkAll").attr("checked", boxes.filter(":checked").length == boxes.length);
			});
			
			//点击添加按钮弹出添加角色窗口
			$("#addContact").click(function(){
				$("#divDialog").dialog({    
					title: "添加联系人",   // 标题  
					width: 500,   // 宽度
					height: 420,   // 高度
					modal: true, // 模态窗口
					collapsible : true, // 可伸缩
					minimizable : false, // 最小化
					maximizable : true, // 最大化
					onClose : function(){
						// 刷新左边的
						parent.contactLeftFrame.location.reload();
						window.location.href = "${path}/admin/addressbook/selectContact?pageModel.pageIndex=${pageModel.pageIndex}&contactGroup=${contactGroup}";
					}
				});
				$("#iframe").attr("src", "${path}/admin/addressbook/showAddContact?contactGroup=${contactGroup}").fadeIn(200);
			});
			
			// 点击按钮修改用户
			$("#updateContact").click(function(){
				var boxs = $("input[type='checkbox'][id^='box_']:checked");
				if (boxs.length == 0){
					alert("请选择要修改的操作！");
				}else if (boxs.length == 1){
					$("#divDialog").dialog({    
						title: "修改操作",   // 标题  
						width: 500,   // 宽度
						height: 420,   // 高度
						modal: true, // 模态窗口.
						collapsible : true, // 可伸缩
						minimizable : false, // 最小化
						maximizable : true, // 最大化
						onClose : function(){
							// 刷新左边的树 
							parent.contactLeftFrame.location.reload();
							window.location.href = "${path}/admin/addressbook/selectContact?pageModel.pageIndex=${pageModel.pageIndex}&contactGroup=${contactGroup}";
						}
					});
					$("#iframe").attr("src", "${path}/admin/addressbook/showUpdateContact?contact.id=" + boxs.val()).fadeIn(200);
				}else{
					alert("修改操作时，只能选择一个！");
				}
			});
			
			//点击按钮删除用户
			$("#deleteContact").click(function(){
				var boxs = $("input[type='checkbox'][id^='box_']:checked");
				if (boxs.length == 0){
					alert("请选择要删除的操作！");
				}else{
					if (confirm("您确定要删除吗？")){
						var ids = boxs.map(function(){
							return this.value;
						});
						window.location.href = "${path}/admin/addressbook/deleteContact?pageModel.pageIndex=${pageModel.pageIndex}&contactGroup=${contactGroup}&ids=" + ids.get();
					}
				}
			});
			
			if ("${msg}" != ""){
				parent.contactLeftFrame.location.reload();
			}
			
			
		});
	</script>
</head>
<body>
	<!-- 工具按钮区 -->
		<table>
			<tr>
				<td><input type="button" value="添加" id="addContact"/></td>
				<td><input type="button" value="修改" id="updateContact"/></td>
				<td><input type="button" value="删除" id="deleteContact"/></td>
			</tr>
		</table>
	
	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th><input type="checkbox" id="checkAll"/>全部</th>
			<th>姓名</th>
			<th>性别</th>
			<th>手机号码</th>
			<th>邮箱</th>
			<th>QQ号码</th>
			<th>生日</th>
			<th>组名</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="contacts" status="stat">
				<tr id="tr_${stat.index}" class="listTr">
					<td><input type="checkbox" id="box_${stat.index}" value="${id}"/>${stat.count}</td>
					<td><s:property value="name"/></td>
					<td>${sex == 1 ? '男' : '女' }</td>
					<td><s:property value="phone"/></td>
					<td><s:property value="email"/></td>
					<td><s:property value="qqNum"/></td>
					<td><s:date name="birthday" format="yyyy-MM-dd"/></td>
					<td><s:property value="contactGroup.name"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<!-- 分页标签区 -->
		<page:pager pageIndex="${pageModel.pageIndex}" 
		pageSize="${pageModel.pageSize}" 
		recordCount="${pageModel.recordCount}" 
		submitUrl="${path}/admin/addressbook/selectContact?pageModel.pageIndex={0}&contactGroup=${contactGroup}"/>
	<!-- div作为弹出窗口 -->
    <div id="divDialog" style="overflow: hidden;">
		<iframe id="iframe" frameborder="0" width="100%" height="100%" style="display:none;"></iframe>
	</div>
</body>
</html>