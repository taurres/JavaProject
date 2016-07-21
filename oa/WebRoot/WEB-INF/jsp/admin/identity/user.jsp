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
	<link href="${path}/js/jqeasyui/themes/default/easyui.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="${path}/js/jqeasyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		$(function(){
			//加载页面时获取部门菜单
			$.get("${path}/admin/identity/loadDepts",function(data, status){
				if(status == "success"){
					//获取上次查询时选择的deptId
					var selectedId = "${user.dept.id}";
					//成功则遍历json中的对象，将部门名添加到部门菜单，同时自动选中上次查询所选的部门
					$.each(data,function(){
						$("<option/>").val(this.id)
							.text(this.name)
							.attr("selected", this.id == selectedId)
							.appendTo("#deptSelect");
					});
				}else{
					alert("加载部门失败");
				}		
			},"json");
			
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
			
			//点击添加按钮弹出添加用户窗口
			$("#addUser").click(function(){
				$("#divDialog").dialog({
					title: "addUser",
					width: 500,
					height: 270,
					collapsible : true,
					maximizable : true,
					modal: true,
					onClose : function(){
						window.location.reload();
					}
				});
				$("#iframe").attr("src", "${path}/admin/identity/showAddUser").fadeIn(200);
			});
			
			//联想输入框  返回所有user的用户，数据格式 [{"name":"名字"},{}]
  			$("#user_name").combobox({
  				url: "${path}/admin/identity/loadUserName", //访问的ajax
  				mode: "remote", //远程模式获取数据
  				editable: true, //使输入框可以编辑
				hasDownArrow: false, //取消下拉箭头
				textField: "name", //显示在菜单上的值，即key=name对应的value
				valueField: "name",// 对应的后台值
				panelHeight: "auto", //自动高度
				onBeforeLoad: function(param){
					//如果没有输入或只输入空格，则不加载数据，返回false
					if(param == null || param.q == null || $.trim(param.q) == ""){
						//当没有输入操作但输入框里有内容时(查询后数据回写时)，返回true不阻止载入，保证数据能够回写
						if($(this).combobox("getValue")){
							return true;
						}
						return false;
					}
				}
			});   
			
		});
	</script>
</head>
<body>
	<!-- 工具按钮区 -->
	<s:form id="selectUser" action="/admin/identity/selectUser" method="post" theme="simple">
		<table>
			<tr>
				<td><input type="button" value="添加" id="addUser"/></td>
				<td><input type="button" value="修改" id="updateUser"/></td>
				<td><input type="button" value="删除" id="deleteUser"/></td>
				<td id="td_checkUser">状态：
				<!-- "1"审核 "2"不通过 "3" 冻结-->
					<s:select name="" id="status" list="#{1:'审核',2:'不通过',3:'冻结'}"/>
				</td>
				<td><input type="button" value="审核" id="checkUser"/></td>
				<td>姓名：<s:textfield name="user.name" size="12" id="user_name" /></td>
				<td>手机号码：<s:textfield name="user.phone" size="12" id="user_phone"/></td>
				<td>部门：<select name="user.dept.id" id="deptSelect">
						<option value="0">==请选择==</option>
						</select>
				</td>
				<td><input type="submit" value="查询"/>&nbsp;<font color="red"></font></td>
			</tr>
		</table>
	</s:form>
	
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
			<s:iterator value="users" status="stat" >
				<tr id="tr_${stat.index}" class="listTr">
					<td><input type="checkbox" id="box_${stat.index}" value="${stat.index}"/>${stat.count}</td>
					<td><s:property value="userId"/></td>
					<td><s:property value="name"/></td>
					<td>${sex==1?'男':'女'}</td>
					<td><s:property value="dept.name"/></td>
					<td><s:property value="job.name"/></td>
					<td><s:property value="phone"/></td>
					<td><s:property value="email"/></td>
					<td>
						<s:if test="status==0">
							<font color="green">新建</font>
						</s:if>
						<s:elseif test="status==1">
							<font color="green">已审核</font>
						</s:elseif>
						<s:elseif test="status==2">
							<font color="red">不通过</font>
						</s:elseif>
						<s:elseif test="status==3">
							<font color="green">冻结</font>
						</s:elseif>
						<s:else>
							<font>无</font>
						</s:else>		
					</td>
					<td><s:date name="createDate" format="yyyy-MM-dd HH-mm-ss"/></td>
					<td><s:property value="creater.name"/></td>
					<td><s:date name="checkDate" format="yyyy-MM-dd HH-mm-ss"/></td>
					<td><s:property value="checker.name"/></td>
				</tr>
			</s:iterator>
			
		</tbody>
	</table>
	<!-- 分页标签区 -->
		<page:pager pageIndex="${pageModel.pageIndex}" 
		pageSize="${pageModel.pageSize}" 
		recordCount="${pageModel.recordCount}" 
		submitUrl="${path}/admin/identity/selectUser?pageModel.pageIndex={0}&user.name=${user.name}&user.phone=${user.phone}&user.dept.id=${user.dept.id}"/>
	<!-- div作为弹出窗口 -->
    <div id="divDialog" style="overflow: hidden;">
		<iframe id="iframe" frameborder="0" width="100%" height="100%" style="display:none;"></iframe>
	</div>
</body>
</html>