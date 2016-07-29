package com.wenjiaxi.oa.admin.interceptor;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wenjiaxi.oa.admin.AdminConstant;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月29日 下午2:34:31
 * @version 1.0
 */

public class AuthInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -984408155687217854L;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getProxy().getActionName();
		String nameSpace = invocation.getProxy().getNamespace();
		//获取该用户请求操作的url
		String requestURL = nameSpace + "/" + actionName;
		//获取该用户所有权限对应的url
		Map<String, List<String>> map = (Map<String, List<String>>) invocation.getInvocationContext().getSession().get(AdminConstant.SESSION_USER_POPEDOM);
		//遍历权限url，与请求操作的url作对比，有权限则放行
		for(Map.Entry<String, List<String>> entry : map.entrySet()){
			for (String url : entry.getValue()) {
				//url中含有后缀jspx，要截取
				if (url.substring(0, url.lastIndexOf(".")).equals(requestURL)) {
					//将权限对应的url放入session，用于之后对页面按钮的权限控制
					invocation.getInvocationContext().getSession().put(AdminConstant.SESSION_POPEDOM, entry.getValue());
					return invocation.invoke();
				}
			}
		}
		invocation.getInvocationContext().put("msg", "您的权限正在审核中，请联系系统管理员。");
		return Action.ERROR;
	}

}
