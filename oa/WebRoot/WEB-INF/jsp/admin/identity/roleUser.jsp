<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-绑定用户角色管理</title>
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
			
			//点击按钮选择要绑定的用户
			$("#bindBtn").click(function(){
				$("#divDialog").dialog({
					title: "绑定用户",
					width: 800,
					height: 420,
					collapsible : true,
					maximizable : true,
					modal: true,
					onClose : function(){
						window.location.href = "${path}/admin/identity/showBindedUser?pageModel.pageIndex=${pageModel.pageIndex}&role.id=${role.id}&role.name=${role.name}";
					}
				});
				$("#iframe").attr("src", "${path}/admin/identity/showBindableUser?role.id=${role.id}").fadeIn(200);
			});
			
			// 点击按钮解除用户
			$("#unbindBtn").click(function(){
				// 获取选中的checkbox 
				var boxes = $("input[id^='box_']:checked");
				if(boxes.length == 0){
					alert("请选择要解除绑定的用户");
				}else{
					if (confirm("确定要解除绑定该角色吗?")){
						var ids = boxes.map(function(){
							return this.value;
						});
						window.location.href = "${path}/admin/identity/unbindUser?role.id=${role.id}&role.name=${role.name}&pageModel.pageIndex=${pageModel.pageIndex}&ids=" + ids.get();
						alert("解除绑定成功！");
					};
				}
			});
			
			//点击按钮返回角色页面
			$("#backBtn").click(function(){
				window.location.href = "${path}/admin/identity/selectRole";
			});
			
			
		});
	</script>
</head>
<body>
	<!-- 工具按钮区 -->
		<table>
			<tr>
				<td><input type="button" value="绑定用户" id="bindBtn"/></td>
				<td><input type="button" value="解除用户" id="unbindBtn"/></td>
				<td><input type="button" value="返回" id="backBtn"/><span>&nbsp;&nbsp;正在给<font color="red">【${role.name}】</font>角色绑定用户</span></td>
				
			</tr>
		</table>
	
	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th><input type="checkbox" id="checkAll"/>全部</th>
			<th>编号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>部门</th>
			<th>职位</th>
			<th>手机号码</th>
			<th>邮箱</th>
			<th>状态</th>
			<th>创建日期</th>
			<th>创建人</th>
			<th>审核日期</th>
			<th>审核人</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="users" status="stat">
				<tr id="tr_${stat.index}" class="listTr">
					<td><input type="checkbox" id="box_${stat.index}" value="${userId}"/>${stat.count}</td>
					<td><s:property value="userId"/></td>
					<td><s:property value="name"/></td>
					<td>${sex == 1 ? '男' : '女'}</td>
					<td><s:property value="dept.name"/></td>
					<td><s:property value="job.name"/></td>
					<td><s:property value="phone"/></td>
					<td><s:property value="email"/></td>
					<td>
						<s:if test="status == 0">
							<font color="blue">新建</font>
						</s:if>
						<s:elseif test="status == 1">
							<font color="green">已审核</font>
						</s:elseif>
						<s:elseif test="status == 2">
							<font color="red">不通过</font>
						</s:elseif>
						<s:else><font color="HotPink">冻结</font></s:else>
					</td>
					<td><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:property value="creater.name"/></td>
					<td><s:date name="checkDate" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:property value="checker.name"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<!-- 分页标签区 -->
		<page:pager pageIndex="${pageModel.pageIndex}" 
		pageSize="${pageModel.pageSize}" 
		recordCount="${pageModel.recordCount}" 
		submitUrl="${path}/admin/identity/showBindedUser?pageModel.pageIndex={0}&role.id=${role.id}"/>
	<!-- div作为弹出窗口 -->
    <div id="divDialog" style="overflow: hidden;">
		<iframe id="iframe" frameborder="0" width="100%" height="100%" style="display:none;"></iframe>
	</div>
</body>
</html>