package com.wenjiaxi.oa.admin.leave.service;

import java.util.List;
import java.util.Map;

import com.wenjiaxi.oa.admin.leave.entity.LeaveAudit;
import com.wenjiaxi.oa.admin.leave.entity.LeaveItem;
import com.wenjiaxi.oa.core.common.web.PageModel;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:55:34
 * @version 1.0
 */

public interface LeaveService {

	/**
	 * 按条件分页查询指定用户的休假
	 * @param pageModel
	 * @param leaveItem
	 * @return
	 */
	List<LeaveItem> getLeaveItemByPageAndUser(PageModel pageModel, LeaveItem leaveItem);

	/**
	 * 为指定用户添加休假
	 * @param leaveItem
	 */
	void addLeaveItem(LeaveItem leaveItem);

	/**
	 * 异步加载leavetype
	 * @return
	 */
	List<Map<String, Object>> getLeaveType();

	/**
	 * 异步加载leavetype和process
	 * @return
	 */
	List<List<Map<String, Object>>> getLeaveTypeAndProcess();
	
	/**
	 * 查询指定用户要审批的休假
	 * @return
	 */
	List<LeaveItem> fetchLeaveItemByUser();
	
	/**
	 * 根据主键id查询请假单
	 * @param id
	 * @return 请假单
	 */
	public LeaveItem getLeaveItem(Long id);

	/**
	 * 审批休假
	 * @param leaveAudit
	 * @param taskId
	 */
	void audit(LeaveAudit leaveAudit, String taskId);

	/**
	 * 查询休假审批结果
	 * @param id
	 * @return
	 */
	List<LeaveAudit> getLeaveAuditByLeaveItemId(Long id);



}
