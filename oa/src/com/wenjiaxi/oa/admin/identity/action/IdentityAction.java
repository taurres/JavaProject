package com.wenjiaxi.oa.admin.identity.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.wenjiaxi.oa.admin.identity.service.IdentityService;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月19日 下午10:07:29
 * @version 1.0
 */

public class IdentityAction extends ActionSupport {

	private static final long serialVersionUID = -3107654296605675426L;
	
	@Resource
	protected IdentityService identityService;
}
