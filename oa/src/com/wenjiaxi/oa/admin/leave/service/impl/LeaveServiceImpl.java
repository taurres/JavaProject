package com.wenjiaxi.oa.admin.leave.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import com.wenjiaxi.oa.admin.AdminConstant;
import com.wenjiaxi.oa.admin.identity.entity.User;
import com.wenjiaxi.oa.admin.leave.dao.LeaveAuditDao;
import com.wenjiaxi.oa.admin.leave.dao.LeaveItemDao;
import com.wenjiaxi.oa.admin.leave.dao.LeaveTypeDao;
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
			e.printStackTrace();
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
			User user = AdminConstant.getSessionUser();
			//放置流程实例参数
			Map<String, Object> variables = new HashMap<>();
			//jobCode用于确定由哪个职位进行审批
			variables.put("jobCode", user.getJob().getCode());
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
			throw new OAException("异步加载leavetype和process时出现异常", e);
		}
	}
}
