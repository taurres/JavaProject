package com.wenjiaxi.oa.admin.addressbook.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.wenjiaxi.oa.admin.addressbook.service.AddressbookService;
import com.wenjiaxi.oa.core.common.web.PageModel;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月26日 下午2:13:02
 * @version 1.0
 */

public class AddressbookAction extends ActionSupport{

	private static final long serialVersionUID = 3730143146976216105L;
	
	@Resource
	protected AddressbookService addressbookService;
	
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
