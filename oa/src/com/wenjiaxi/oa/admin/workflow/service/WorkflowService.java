package com.wenjiaxi.oa.admin.workflow.service;

import java.io.File;
import java.util.List;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;

import com.wenjiaxi.oa.core.common.web.PageModel;

/**
* 
* @author WEN JIAXI
* @date 2016年8月3日 下午5:20:58
* @version 1.0
*/

public interface WorkflowService {

	/**
	 * 部署流程
	 * @param bpmnFile
	 * @param bpmnFileName
	 * @param processDefinitionName
	 */
	void deployProcess(File bpmn, String bpmnFileName, String name);

	/**
	 * 按条件分页查询流程部署
	 * @param name
	 * @param pageModel
	 */
	List<Deployment> getDeploymentByPage(String name, PageModel pageModel);

	/**
	 * 批量删除流程部署
	 * @param ids
	 */
	void deleteDeployment(String[] ids);

	/**
	 * 查询历史任务
	 * @param processInstanceId
	 * @return
	 */
	List<HistoricTaskInstance> getHistoricTaskInstance(String processInstanceId);
}
