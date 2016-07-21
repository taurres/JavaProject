package com.wenjiaxi.oa.admin.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wenjiaxi.oa.admin.AdminConstant;
import com.wenjiaxi.oa.admin.identity.entity.User;
import com.wenjiaxi.oa.admin.identity.service.IdentityService;
import com.wenjiaxi.oa.core.common.web.CookieUtil;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月19日 下午4:46:58
 * @version 1.0
 */

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -1993542651328154688L;
	
	@Resource
	private IdentityService identityService;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//获取action名
		String actionName = invocation.getProxy().getActionName();
		//在session中查找user
		User user = (User) invocation.getInvocationContext().getSession().get(AdminConstant.SESSION_USER);
		//判断session中是否有user，有user则放行
		if (user != null) {
			//如果访问的是login页面，直接跳转到main页面
			if (actionName.equals("login")) {
				return "main";
			}
			return invocation.invoke();
		}
		//如果user为空，则查询cookie
		if (user == null) {	
			Cookie cookie = CookieUtil.getCookie(AdminConstant.COOKIE_LOGIN);
			//如果cookie存在，则根据cookie保存的userid查询user
			if (cookie != null) {
				String userId = cookie.getValue();
				user = identityService.getUser(userId,true);
				//若查询到user，则把userId存入session,并放行
				if (user != null) {
					invocation.getInvocationContext().getSession().put(AdminConstant.SESSION_USER, user);
					//如果访问的是login页面，直接跳转到main页面
					if (actionName.equals("login")) {
						return "main";
					}
					return invocation.invoke();
				}
			}
		}
		//其他情况均返回到login页面
		return Action.LOGIN;
	}

}
