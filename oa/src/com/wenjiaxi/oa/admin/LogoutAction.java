package com.wenjiaxi.oa.admin;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.wenjiaxi.oa.admin.identity.service.IdentityService;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月21日 上午12:55:54
 * @version 1.0
 */

public class LogoutAction extends ActionSupport {

	@Resource
	private IdentityService identityService;
	
	@Override
	public String execute() throws Exception {
		identityService.logout();
		return LOGIN;
	}

	
}
