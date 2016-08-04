package com.wenjiaxi.oa.admin.leave.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.wenjiaxi.oa.admin.leave.service.LeaveService;
import com.wenjiaxi.oa.core.common.web.PageModel;

/**
* 
* @author WEN JIAXI
* @date 2016年8月4日 下午7:26:24
* @version 1.0
*/

public class LeaveAction extends ActionSupport{
	
	private static final long serialVersionUID = -1098053731942621758L;

	@Resource
	protected LeaveService leaveService;
	
	protected PageModel pageModel = new PageModel();
	protected String msg;
	
	
	//getter setter
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
