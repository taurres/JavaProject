package com.wenjiaxi.oa.admin;

import org.apache.struts2.ServletActionContext;

import com.wenjiaxi.oa.admin.identity.entity.User;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月19日 上午11:08:15
 * @version 1.0
 */

public final class AdminConstant {
	/** 定义存放在Session中的用户的名称 */
	public static final String SESSION_USER = "session_user";
	
	// 定义存放登录信息cookie的名称
	public static final String COOKIE_LOGIN = "cookie_login";

	// 定义存放登录信息cookie的期限：一周
	public static final int COOKIE_LOGIN_AGE = 86400 * 7;
	
	//返回session里面user的名字
	public static User getSessionUser(){
		return (User) ServletActionContext.getContext().getSession().get(SESSION_USER);
	}
}
