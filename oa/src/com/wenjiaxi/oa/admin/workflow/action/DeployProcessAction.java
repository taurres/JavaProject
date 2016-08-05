package com.wenjiaxi.oa.admin.workflow.action;

import java.io.File;
import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;


/**
* 
* @author WEN JIAXI
* @date 2016年8月5日 上午9:53:27
* @version 1.0
*/

public class DeployProcessAction extends WorkflowAction {

	private static final long serialVersionUID = 4631114710154319217L;
	
	private String bpmnFileName;
	private String name;
	private File bpmn;
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
