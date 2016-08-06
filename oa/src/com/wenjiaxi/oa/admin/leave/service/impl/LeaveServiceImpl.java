package com.wenjiaxi.oa.admin.leave.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import com.wenjiaxi.oa.admin.AdminConstant;
import com.wenjiaxi.oa.admin.identity.entity.User;
import com.wenjiaxi.oa.admin.identity.service.IdentityService;
import com.wenjiaxi.oa.admin.leave.dao.LeaveAuditDao;
import com.wenjiaxi.oa.admin.leave.dao.LeaveItemDao;
import com.wenjiaxi.oa.admin.leave.dao.LeaveTypeDao;
import com.wenjiaxi.oa.admin.leave.entity.LeaveAudit;
import com.wenjiaxi.oa.admin.leave.entity.LeaveItem;
import com.wenjiaxi.oa.admin.leave.service.LeaveService;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.exception.OAException;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:56:25
 * @version 1.0
 */
@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {

	@Resource
	private LeaveAuditDao leaveAuditDao;
	@Resource
	private LeaveItemDao leaveItemDao;
	@Resource
	private LeaveTypeDao leaveTypeDao;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService; 
	@Resource
	private HistoryService historyService; 
	@Resource
	private IdentityService identityService;
	
	/**
	 * 按条件分页查询指定用户的休假
	 * @param pageModel
	 * @param leaveItem
	 * @return
	 */
	public List<LeaveItem> getLeaveItemByPageAndUser(PageModel pageModel, LeaveItem leaveItem){
		try {
			List<LeaveItem> leaveItems = leaveItemDao.getLeaveItemByPageAndUser(pageModel, leaveItem);
			//加载延迟属性
			for (LeaveItem l : leaveItems) {
				if (l.getLeaveType() != null) l.getLeaveType().getCode();
				if (l.getCreater() != null) l.getCreater().getName();
			}
			return leaveItems;
		} catch (Exception e) {

			throw new OAException("按条件分页查询指定用户的休假时出现异常", e);
		}
	}
	
	/**
	 * 为指定用户添加休假
	 * @param leaveItem
	 */
	public void addLeaveItem(LeaveItem leaveItem){
		try {
			//获取流程定义id
			String pdId = leaveItem.getProcInstanceId();
			//获取添加休假的用户
			User user = identityService.getUser(AdminConstant.getSessionUser().getUserId(), false);
			//放置流程实例参数
			Map<String, Object> variables = new HashMap<>();
			//jobCode用于确定由哪个职位进行审批
			variables.put("jobCode",  Integer.valueOf(user.getJob().getCode()));
			//deptId用于确定交给哪个部门的主管进行审批
			variables.put("deptId", user.getDept().getId());
			//根据流程定义id开启流程实例
			ProcessInstance processInstance = runtimeService.startProcessInstanceById(pdId, variables);
			
			leaveItem.setProcInstanceId(processInstance.getId());
			leaveItem.setCreateDate(new Date());
			leaveItem.setCreater(user);
			leaveItemDao.save(leaveItem);
			
			//将新添加的leaveItem与对应的流程实例关联(将leaveItemId传给流程实例)
			runtimeService.setVariable(processInstance.getId(), "leaveItemId", leaveItem.getId());
		} catch (Exception e) {
			
			throw new OAException("为指定用户添加休假时出现异常", e);
		}
	}
	
	/**
	 * 异步加载leavetype
	 * @return
	 */
	public List<Map<String, Object>> getLeaveType(){
		try {
			return leaveTypeDao.getLeaveType();
		} catch (Exception e) {
			
			throw new OAException("异步加载leavetype时出现异常", e);
		}
	}
	
	/**
	 * 获取所有ProcessDefinition
	 * @return [{id:"", name:""}, {}, {}]
	 */
	private List<Map<String, Object>> getProcessDefinition(){
		try {
			//获取所有ProcessDefinition
			List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
																		  .latestVersion()
																		  .list();
			List<Map<String, Object>> responseList = new ArrayList<>();
			for (ProcessDefinition processDefinition : processDefinitions) {
				Map<String, Object> map = new HashMap<>();
				//存入id和name
				map.put("id", processDefinition.getId());
				map.put("name", processDefinition.getName());
				responseList.add(map);
			}
			return responseList;
		} catch (Exception e) {
			
			throw new OAException("获取所有ProcessDefinition时出现异常", e);
		}	
	}
	
	/**
	 * 异步加载leavetype和process
	 * @return
	 */
	public List<List<Map<String, Object>>> getLeaveTypeAndProcess(){
		try {
			List<List<Map<String, Object>>> responseList = new ArrayList<>();
			//往list中加入leaveType数据
			responseList.add(getLeaveType());
			//往list中加入process数据
			responseList.add(getProcessDefinition());
			return responseList;
		} catch (Exception e) {
			
			throw new OAException("异步加载leavetype和process时出现异常", e);
		}
	}
	
	/**
	 * 查询指定用户要审批的休假
	 * @return
	 */
	public List<LeaveItem> fetchLeaveItemByUser(){
		try {
			User user = identityService.getUser(AdminConstant.getSessionUser().getUserId(), false);
			System.out.println("code " + user.getJob().getCode());
			//根据当前用户所在的候选组查找任务
			List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(user.getJob().getCode()).list();
			System.out.println("task: " + tasks);
			//领取属于自己部门的任务
			for (Task task : tasks) {
				long deptId = taskService.getVariable(task.getId(), "deptId", Long.class);
				if (deptId == user.getDept().getId() || "0009".equals(user.getJob().getCode())) {
					taskService.claim(task.getId(), user.getUserId());
				}
			}
			//查询已经领取的任务
			List<Task> claimedTasks = taskService.createTaskQuery().taskAssignee(user.getUserId()).list();
			System.out.println("claim: " + claimedTasks);
			//根据任务查询leaveItem
			List<LeaveItem> leaveItems = new ArrayList<>();
			for (Task task : claimedTasks) {
				Long leaveItemId = taskService.getVariable(task.getId(), "leaveItemId", Long.class);
				System.out.println("leaveId: " + leaveItemId);
				LeaveItem leaveItem = leaveItemDao.get(LeaveItem.class, leaveItemId);
				System.out.println("leaveItem: " + leaveItem);
				//加载延迟属性
				if (leaveItem.getCreater() != null) leaveItem.getCreater().getName();
				if (leaveItem.getLeaveType() != null) leaveItem.getLeaveType().getName();
				//审批任务时需要用到taskId，先将taskId存放起来
				leaveItem.setTaskId(task.getId());
				leaveItems.add(leaveItem);				
			}
			return leaveItems;
		} catch (Exception e) {
			
			throw new OAException("查询指定用户需要审批的休假时出现异常", e);
		}
	}
	
	/**
	 * 根据主键id查询请假单信息
	 * @param id
	 * @return 
	 */
	public LeaveItem getLeaveItem(Long id){
		try{
			LeaveItem leaveItem = leaveItemDao.get(LeaveItem.class, id);
			leaveItem.getCreater().getName();
			return leaveItem;
		}catch(Exception ex){
			throw new OAException("根据主键id查询请假单时出现异常！", ex); 
		}
	}
	
	/**
	 * 审批休假
	 * @param leaveAudit
	 * @param taskId
	 */
	public void audit(LeaveAudit leaveAudit, String taskId){
		try{
			// 用Map集合封装流程变量 
			Map<String, Object> variables = new HashMap<>();
			variables.put("status", leaveAudit.getStatus());
			// 完成任务 
			taskService.complete(taskId, variables);
			
			//保存leaveAudit
			leaveAudit.setCheckDate(new Date());
			leaveAudit.setChecker(AdminConstant.getSessionUser());
			leaveAuditDao.save(leaveAudit);
			//根据审批的id获取对应的leaveItem
			LeaveItem leaveItem = leaveItemDao.get(LeaveItem.class, leaveAudit.getLeaveItem().getId());
			// 根据流程实例Id查询历史流程实例，判断流程是否结束
			HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
																		    .processInstanceId(leaveItem.getProcInstanceId())
																		    .singleResult();
			// 判断流程实例有没有结束时间，有则说明已经完成整个流程，流程完成则修改leaveItem的status
			if (historicProcessInstance.getEndTime() != null){
				// 修改leaveItem的status
				leaveItem.setStatus(leaveAudit.getStatus());
			}
		}catch(Exception ex){
			throw new OAException("假期审批时出现异常！", ex); 
		}
	}
	
	/**
	 * 查询休假审批结果
	 * @param id
	 * @return
	 */
	public List<LeaveAudit> getLeaveAuditByLeaveItemId(Long id){
		try{
			List<LeaveAudit> leaveAudits = leaveAuditDao.getLeaveAuditByLeaveItemId(id);
			for (LeaveAudit leaveAudit : leaveAudits){
				if (leaveAudit.getChecker() != null) {
					leaveAudit.getChecker().getName();
					leaveAudit.getChecker().getJob().getName();
				}
			}
			return leaveAudits;
		}catch(Exception ex){
			throw new OAException("查询审批结果时出现异常！", ex); 
		}
	}
}
