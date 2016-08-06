package com.wenjiaxi.oa.admin.workflow.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wenjiaxi.oa.admin.workflow.service.WorkflowService;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.exception.OAException;

/**
* 
* @author WEN JIAXI
* @date 2016年8月3日 下午5:21:14
* @version 1.0
*/
@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService{

	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	
	/**
	 * 部署流程
	 * @param bpmnFile
	 * @param bpmnFileName
	 * @param processDefinitionName
	 */
	public void deployProcess(File bpmn, String bpmnFileName, String name){
		try {
			repositoryService.createDeployment()
							 .name(name)
							 .category(name)
							 .addInputStream(bpmnFileName, new FileInputStream(bpmn))
							 .deploy();
		} catch (Exception e) {
			throw new OAException("部署流程时出现异常", e);
		}
	}
	
	/**
	 * 按条件分页查询流程部署
	 * @param name
	 * @param pageModel
	 */
	public List<Deployment> getDeploymentByPage(String name, PageModel pageModel){
		try {
			/** 创建部署查询对象 */
			DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
			/** 添加查询条件 */
			if (!StringUtils.isEmpty(name)){
				deploymentQuery.deploymentNameLike("%" + name + "%");
			}
			/** 统计查询 */
			long recordCount = deploymentQuery.count();
			if (recordCount == 0){
				return null;
			}
			/** 分页查询 */
			pageModel.setRecordCount((int)recordCount);
			return deploymentQuery.orderByDeploymenTime()
								  .asc()
								  .listPage(pageModel.getStartRow(), pageModel.getPageSize());
		} catch (Exception e) {
			throw new OAException("查询流程部署时出现异常", e);
		}
	}
	
	/**
	 * 批量删除流程部署
	 * @param ids
	 */
	public void deleteDeployment(String[] ids){
		try{
			for (String id : ids){
				repositoryService.deleteDeployment(id, true);
			}
		}catch(Exception ex){
			throw new OAException("批量删除流程部署时出现了异常！", ex);
		}
	}
	
	/**
	 * 查询历史任务
	 * @param processInstanceId
	 * @return
	 */
	public List<HistoricTaskInstance> getHistoricTaskInstance(String processInstanceId){
		try{
			return historyService.createHistoricTaskInstanceQuery()
								 .processInstanceId(processInstanceId)
								 .finished()
								 .list();
		}catch(Exception ex){
			throw new OAException("查询历史任务时出现了异常！", ex);
		}
	}
}
