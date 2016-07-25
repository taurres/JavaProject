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
			$("#addModule").click(function(){
				$("#divDialog").dialog({    
					title: "添加操作",   // 标题  
					width: 370,   // 宽度
					height: 245,   // 高度
					modal: true, // 模态窗口
					collapsible : true, // 可伸缩
					minimizable : false, // 最小化
					maximizable : true, // 最大化
					onClose : function(){
						/** 刷新左边的树 */
						parent.leftFrame.location.reload();
						/** 刷新操作的页面 */
						window.location.href = "${path}/admin/identity/selectModule?pageModel.pageIndex=${pageModel.pageIndex}&parentCode=${parentCode}";
					}
				});
				$("#iframe").attr("src", "${path}/admin/identity/showAddModule?parentCode=${parentCode}").fadeIn(200);
			});
			
			// 点击按钮修改用户
			$("#updateModule").click(function(){
				/** 获取下面选中的checkbox */
				var boxs = $("input[type='checkbox'][id^='box_']:checked");
				if (boxs.length == 0){
					alert("请选择要修改的操作！");
				}else if (boxs.length == 1){
					$("#divDialog").dialog({    
						title: "修改操作",   // 标题  
						width: 370,   // 宽度
						height: 245,   // 高度
						modal: true, // 模态窗口.
						collapsible : true, // 可伸缩
						minimizable : false, // 最小化
						maximizable : true, // 最大化
						onClose : function(){
							/** 刷新左边的树 */
							parent.leftFrame.location.reload();
							/** 刷新操作的页面 */
							window.location.href = "${path}/admin/identity/selectModule?pageModel.pageIndex=${pageModel.pageIndex}&parentCode=${parentCode}";
						}
					});
					$("#iframe").attr("src", "${path}/admin/identity/showUpdateModule?module.code=" + boxs.val()).fadeIn(200);
				}else{
					alert("修改操作时，只能选择一个！");
				}
			});
			
			//点击按钮删除用户
			$("#deleteModule").click(function(){
				/** 获取下面选中的checkbox */
				var boxs = $("input[type='checkbox'][id^='box_']:checked");
				if (boxs.length == 0){
					alert("请选择要删除的操作！");
				}else{
					if (confirm("您确定要删除吗？")){
						/** 获取删除的code, map可以改变jQuery对象里面存放的是什么 , 是map方法中回调函数的返回值*/
						var codes = boxs.map(function(){
							return this.value;
						});
						window.location.href = "${path}/admin/identity/deleteModule?pageModel.pageIndex=${pageModel.pageIndex}&parentCode=${parentCode}&codes=" + codes.get();
					}
				}
			});
			
			/** 删除完成后 */
			if ("${tip}" != ""){
				/** 刷新左边的树 */
				parent.leftFrame.location.reload();
			}
			
			
		});
	</script>
</head>
<body>
	<!-- 工具按钮区 -->
		<table>
			<tr>
				<td><input type="button" value="添加" id="addModule"/></td>
				<td><input type="button" value="修改" id="updateModule"/></td>
				<td><input type="button" value="删除" id="deleteModule"/></td>
			</tr>
		</table>
	
	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th><input type="checkbox" id="checkAll"/>全部</th>
			<th>编号</th>
			<th>名称</th>
			<th>备注</th>
			<th>链接</th>
			<th>操作</th>
			<th>创建日期</th>
			<th>创建人</th>
			<th>修改日期</th>
			<th>修改人</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="modules" status="stat" >
				<tr id="tr_${stat.index}" class="listTr">
					<td><input type="checkbox" id="box_${stat.index}" value="${code}"/>${stat.count}</td>
					<td><s:property value="code"/></td>
					<td><s:property value="name"/></td>
					<td><s:property value="remark"/></td>
					<td><s:property value="url"/></td>
					<td><a href="${path}/admin/identity/selectModule?parentCode=${code}">查看下级</a></td>
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
		submitUrl="${path}/admin/identity/selectModule?pageModel.pageIndex={0}&parentCode=${parentCode}"/>
	<!-- div作为弹出窗口 -->
    <div id="divDialog" style="overflow: hidden;">
		<iframe id="iframe" frameborder="0" width="100%" height="100%" style="display:none;"></iframe>
	</div>
</body>
</html>