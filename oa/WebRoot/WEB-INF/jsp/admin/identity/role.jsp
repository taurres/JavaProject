<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-用户管理</title>
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
			$("#addRole").click(function(){
				$("#divDialog").dialog({
					title: "添加角色",
					width: 400,
					height: 220,
					collapsible : true,
					maximizable : true,
					modal: true,
					onClose : function(){
						window.location.href = "${path}/admin/identity/selectRole?pageModel.pageIndex=${pageModel.pageIndex}";
					}
				});
				$("#iframe").attr("src", "${path}/admin/identity/showAddRole").fadeIn(200);
			});
			
			// 点击按钮修改用户
			$("#updateRole").click(function(){
				// 获取选中的checkbox 
				var boxes = $("input[id^='box_']:checked");
				if (boxes.length == 0){
					alert("请选择要修改的角色！");
				}else if (boxes.length == 1){
					$("#divDialog").dialog({    
						title: "修改角色",   // 标题  
						width: 400,   // 宽度
						height: 220,   // 高度
						modal: true, // 模态窗口.
						collapsible : true, // 可伸缩
						minimizable : false, // 最小化
						maximizable : true, // 最大化
						onClose : function(){
							window.location.href = "${path}/admin/identity/selectRole?pageModel.pageIndex=${pageModel.pageIndex}";
							location.reload();
						}
					});
					$("#iframe").attr("src", "${path}/admin/identity/showUpdateRole?role.id=" + boxes.val()).fadeIn(200);
				}else{
					alert("修改角色时，只能选择一个！");
				}
			});
			
			//点击按钮删除用户
			$("#deleteRole").click(function(){
				var boxes = $("input[id^='box_']:checked");
				if(boxes.length == 0){
					alert("请选择要删除的角色");
				}else{
					if (confirm("确定要删除该角色吗?")){
						var ids = boxes.map(function(){
							return this.value;
						});
						window.location.href = "${path}/admin/identity/deleteRole?pageModel.pageIndex=${pageModel.pageIndex}&ids=" + ids.get();
						alert("删除成功！");
					};
				}
			});
			
			
		});
	</script>
</head>
<body>
	<!-- 工具按钮区 -->
		<table>
			<tr>
				<td><input type="button" value="添加" id="addRole"/></td>
				<td><input type="button" value="修改" id="updateRole"/></td>
				<td><input type="button" value="删除" id="deleteRole"/></td>
			</tr>
		</table>
	
	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th><input type="checkbox" id="checkAll"/>全部</th>
			<th>名称</th>
			<th>备注</th>
			<th>操作</th>
			<th>创建时间</th>
			<th>创建人</th>
			<th>修改时间</th>
			<th>修改人</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="roles" status="stat" >
				<tr id="tr_${stat.index}" class="listTr">
					<td><input type="checkbox" id="box_${stat.index}" value="${id}"/>${stat.count}</td>
					<td><s:property value="name"/></td>
					<td><s:property value="remark"/></td>
					<td><a href="#">绑定用户</a>&nbsp;<a href="#">绑定操作</a></td>
					<td><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:property value="creater.name"/></td>
					<td><s:date name="modifyDate" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:property value="modifier.name"/></td>
				</tr>
			</s:iterator>
			
		</tbody>
	</table>
	<!-- 分页标签区 -->
		<page:pager pageIndex="${pageModel.pageIndex}" 
		pageSize="${pageModel.pageSize}" 
		recordCount="${pageModel.recordCount}" 
		submitUrl="${path}/admin/identity/selectRole?pageModel.pageIndex={0}"/>
	<!-- div作为弹出窗口 -->
    <div id="divDialog" style="overflow: hidden;">
		<iframe id="iframe" frameborder="0" width="100%" height="100%" style="display:none;"></iframe>
	</div>
	
</body>
</html>