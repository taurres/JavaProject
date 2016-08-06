package com.wenjiaxi.oa.admin.workflow.action;

import java.util.List;

import org.activiti.engine.history.HistoricTaskInstance;

/**
* 
* @author WEN JIAXI
* @date 2016年8月6日 上午9:08:21
* @version 1.0
*/

public class HistoryAction extends WorkflowAction {

	private static final long serialVersionUID = -1310822986876572336L;
	
	private String processInstanceId;
	private List<HistoricTaskInstance> historicTaskInstances;
	
	/**
	 * 查询历史任务
	 * @return
	 */
	public String selectHistoryTask(){
		try {
			System.out.println("id: " + processInstanceId);
			historicTaskInstances = workflowService.getHistoricTaskInstance(processInstanceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	


	//getter setter
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public List<HistoricTaskInstance> getHistoricTaskInstances() {
		return historicTaskInstances;
	}
	public void setHistoricTaskInstances(List<HistoricTaskInstance> historicTaskInstances) {
		this.historicTaskInstances = historicTaskInstances;
	}
	
	
}
