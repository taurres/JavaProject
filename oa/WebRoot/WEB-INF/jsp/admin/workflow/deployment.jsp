<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>OA办公管理系统-流程管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${path}/logo.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${path}/css/common/admin.css" type="text/css" rel="stylesheet"/>
	<link href="${path}/css/common/pager.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
			
			/** 为下面所有的tr绑定onmouseover与onmouseout */
			$("tr[id^='tr_']").hover(
				function(){ // onmouseover
					$(this).css({"background-color":"#FFFFBF", "cursor":"pointer"});
				},
				function(){ // onmouseout
					/** 获取当前行中的checkbox */
					var box = $(this).find("input[id^='box_']");
					if (!box.attr("checked")){
						$(this).css("background-color", "#FFFFFF");
					}
				}
			);
			/** 为全选绑定点击事件 */
			$("#checkAll").click(function(){
				/** 获取到下面的所有的checkbox */
				$("input[id^='box_']").attr("checked", this.checked);
				/** 获取下面所有的tr */
				$("tr[id^='tr_']").trigger(this.checked ? "mouseover" : "mouseout");
			});
			
			/** 让全选选中 */
			/** 获取到下面的所有的checkbox */
			var boxs = $("input[id^='box_']");
			boxs.click(function(){
				/** 把选中的checkbox过滤出来 */
				/** 如果选中的个数与总的个数一样 */
				$("#checkAll").attr("checked", boxs.length == boxs.filter(":checked").length);
			});
			
			
			/** 为删除按钮绑定点击事件 */
			$("#deleteDeployment").click(function(){
				/** 获取下面选中的checkbox */
				var boxs = $("input[id^='box_']").filter(":checked");
				if (boxs.length == 0){
					alert("请选择要删除的流程部署！");
				}else{
					if (confirm("您确定要删除吗？")){
						/** 获取checkbox的value */
						var ids = boxs.map(function(){
							return this.value;
						});
						window.location.href = "${path}/admin/workflow/deleteDeployment?pageModel.pageIndex=${pageModel.pageIndex}&ids=" + ids.toArray();
					}
				}
			});

			
		});
	</script>
</head>
<body>
	<!-- 工具按钮区 -->
	<s:form action="/admin/workflow/selectDeployment" method="post" theme="simple">
		<table>
			<tr>
				<td>部署名称：<s:textfield name="name"/></td>
				<td><input type="submit" value="查询"/></td>
				<td><input type="button" value="删除" id="deleteDeployment"/>
					&nbsp;<font color="red">${msg}</font></td>
			</tr>
		</table>
	</s:form>

		
	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th width="5%"><input type="checkbox" id="checkAll"/>全部</th>
			<th>部署编号</th>
			<th>部署名称</th>
			<th>部署时间</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="deployments" status="stat">
				<tr id="tr_${stat.index}" class="listTr">
					<td><input type="checkbox" id="box_${stat.index}" value="${id}"/>${stat.count}</td>
					<td><s:property value="id"/></td>
					<td><s:property value="name"/></td>
					<td><s:date name="deploymentTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	
	<!-- 分页标签区 -->
	<page:pager pageIndex="${pageModel.pageIndex}" 
			  pageSize="${pageModel.pageSize}" 
			  recordCount="${pageModel.recordCount}" 
			  submitUrl="${path}/admin/workflow/selectDeployment?pageModel.pageIndex={0}&name=${name}"/>
	
	
	
</body>
</html>