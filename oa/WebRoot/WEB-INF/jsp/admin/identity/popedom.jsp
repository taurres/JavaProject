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
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
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
			
			//定义成员变量来装载返回的json数据，表示已经绑定的权限的code
			window.binded = "";
			//自动选中已经绑定的操作的复选框，全选则自动选中全选复选框
			$.ajax({
				url: "${path}/admin/identity/showBindedPopedom", 
				type: "post",
				data: {"moduleCode":"${moduleCode}", "role.id":"${role.id}"},
				dataType: "json",
				success: function(data){
					binded = data;
					boxes.each(function(){
						if(data.toString().indexOf(this.value) != -1){
							//触发复选框的click和mouseover事件
							$(this).trigger("click").trigger("mouseover");
						}
					});	
				}
			}); 
			
			//点击绑定按钮实现绑定和解绑
			$("#bindPopedom").click(function(){
				//获取选中的checkbox的code
				var boxes = $("input[id^='box_']:checked");
				var codes = boxes.map(function() {
					return this.value;
				})
				//如果绑定前后选中的个数相等，但是内容有不一样，则提交绑定请求，如果选中个数相等且内容一样，则不提交请求
				if(binded.length == boxes.length){
					boxes.each(function(){
						//遍历比较选中的与已经绑定的，有不同(index = -1)则提交请求
						if(binded.toString().indexOf(this.value) == -1){
							window.location.href = "${path}/admin/identity/bindPopedom?moduleCode=${moduleCode}&role.id=${role.id}&codes="+codes.get();
						}
					});
				}else{
					//前后选中个数不相等，则一定提交请求
					window.location.href = "${path}/admin/identity/bindPopedom?moduleCode=${moduleCode}&role.id=${role.id}&codes="+codes.get();
				}
			});
			
			//点击按钮返回角色页面
			$("#backBtn").click(function(){
				parent.window.location.href = "${path}/admin/identity/selectRole";
			});
			
		});
	</script>
</head>
<body>
	<!-- 工具按钮区 -->
		<table>
			<tr>
				<td><input type="button" value="绑定" id="bindPopedom"/>
				<td><input type="button" value="返回" id="backBtn"/><span>&nbsp;&nbsp;正在给<font color="red">【${role.name}】</font>角色绑定操作权限</span></td>
			</tr>
		</table>
	
	<!-- 数据展示区 -->
	<table width="100%" class="listTable" cellpadding="8" cellspacing="1">
		<tr class="listHeaderTr">
			<th><input type="checkbox" id="checkAll"/>全部</th>
			<th>编号</th>
			<th>名称</th>
			<th>链接</th>
			<th>备注</th>
		</tr>
		<tbody style="background-color: #FFFFFF;">
			<s:iterator value="ops" status="stat">
				<tr id="tr_${stat.index}" class="listTr">
					<td><input type="checkbox" id="box_${stat.index}" value="${code}"/>${stat.count}</td>
					<td><s:property value="code"/></td>
					<td><s:property value="name"/></td>
					<td><s:property value="url"/></td>
					<td><s:property value="remark"/></td>
				</tr>
			</s:iterator>
			
		</tbody>
	</table>
</body>
</html>