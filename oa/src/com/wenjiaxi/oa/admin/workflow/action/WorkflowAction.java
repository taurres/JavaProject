package com.wenjiaxi.oa.admin.workflow.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.wenjiaxi.oa.admin.workflow.service.WorkflowService;
import com.wenjiaxi.oa.core.common.web.PageModel;

/**
* 
* @author WEN JIAXI
* @date 2016年8月3日 下午5:20:32
* @version 1.0
*/

public class WorkflowAction extends ActionSupport{

	protected static final long serialVersionUID = 1796909007907283118L;

	@Resource
	protected WorkflowService workflowService;
	@Resource
	protected RepositoryService repositoryService;
	@Resource
	protected RuntimeService runtimeService;
	@Resource
	protected TaskService taskService;
	@Resource
	protected HistoryService historyService;
	
	protected String msg;
	protected PageModel pageModel = new PageModel();
	
	//getter setter
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	
}
