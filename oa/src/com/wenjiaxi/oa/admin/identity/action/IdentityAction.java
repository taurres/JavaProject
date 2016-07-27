package com.wenjiaxi.oa.admin.identity.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.wenjiaxi.oa.admin.identity.service.IdentityService;
import com.wenjiaxi.oa.core.common.web.PageModel;

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
	
	protected PageModel pageModel = new PageModel();
	
	//操作结果信息
	protected String msg;

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	
	
}
