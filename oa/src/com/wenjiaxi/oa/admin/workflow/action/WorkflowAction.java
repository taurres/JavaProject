package com.wenjiaxi.oa.admin.workflow.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

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

	private static final long serialVersionUID = 1796909007907283118L;

	@Resource
	private WorkflowService workflowService;
	
	private String bpmnFileName;
	private String name;
	private File bpmn;
	private String msg;
	private PageModel pageModel = new PageModel();
	private List<Deployment> deployments;
	private String ids;
	
	/**
	 * 部署流程
	 * @return
	 */
	public String deployProcess(){
		try {
			workflowService.deployProcess(bpmn, bpmnFileName, name);
			setMsg("部署成功！");
		} catch (Exception e) {
			e.printStackTrace();
			setMsg("部署失败！");
		}
		return SUCCESS;
	}
	
	/**
	 * 按条件分页查询流程部署
	 * @return
	 */
	public String selectDeployment(){
		try {
			if (!StringUtils.isEmpty(name) && ServletActionContext.getRequest().getMethod().equalsIgnoreCase("get")) {
				name = new String(name.getBytes("iso8895-1"), "utf-8");
			}
			deployments = workflowService.getDeploymentByPage(name, pageModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** 批量删除流程部署 */
	public String deleteDeployment(){
		try{
			workflowService.deleteDeployment(ids.split(","));
			setMsg("删除成功！");
		}catch(Exception ex){
			setMsg("删除失败！");
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	//getter setter
	public String getBpmnFileName() {
		return bpmnFileName;
	}
	public void setBpmnFileName(String bpmnFileName) {
		this.bpmnFileName = bpmnFileName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public File getBpmn() {
		return bpmn;
	}
	public void setBpmn(File bpmn) {
		this.bpmn = bpmn;
	}
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
	public List<Deployment> getDeployments() {
		return deployments;
	}
	public void setDeployments(List<Deployment> deployments) {
		this.deployments = deployments;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}

	
	
}
