package com.wenjiaxi.oa.admin.leave.service;

import java.util.List;
import java.util.Map;

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



}
