package com.wenjiaxi.oa.admin.leave.action;

import java.util.List;

import com.wenjiaxi.oa.admin.leave.entity.LeaveAudit;
import com.wenjiaxi.oa.admin.leave.entity.LeaveItem;

/**
* 
* @author WEN JIAXI
* @date 2016年8月5日 下午2:13:29
* @version 1.0
*/

public class LeaveAuditAction extends LeaveAction {

	private static final long serialVersionUID = 6261764680923909981L;
	private List<LeaveItem> leaveItems;
	private LeaveItem leaveItem;
	private String taskId;
	private LeaveAudit leaveAudit;
	private List<LeaveAudit> leaveAudits;
	
	/**
	 * 查询需要审批的休假
	 * @return
	 */
	public String selectAuditLeave(){
		try {
			leaveItems = leaveService.fetchLeaveItemByUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到审批休假页面
	 * @return
	 */
	public String showAudit(){
		try{
			// 缓存一下任务ID 
			taskId = leaveItem.getTaskId();
			leaveItem = leaveService.getLeaveItem(leaveItem.getId());
			leaveItem.setTaskId(taskId);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 审批休假
	 * @return
	 */
	public String audit(){
		try {
			leaveService.audit(leaveAudit, taskId);
			setMsg("审批成功");
		} catch (Exception e) {
			setMsg("审批失败");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** 查询审批结果 */
	public String selectAuditResult(){
		try{
			leaveAudits = leaveService.getLeaveAuditByLeaveItemId(leaveAudit.getLeaveItem().getId());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	//getter setter
	public List<LeaveItem> getLeaveItems() {
		return leaveItems;
	}
	public void setLeaveItems(List<LeaveItem> leaveItems) {
		this.leaveItems = leaveItems;
	}
	public LeaveItem getLeaveItem() {
		return leaveItem;
	}
	public void setLeaveItem(LeaveItem leaveItem) {
		this.leaveItem = leaveItem;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public LeaveAudit getLeaveAudit() {
		return leaveAudit;
	}
	public void setLeaveAudit(LeaveAudit leaveAudit) {
		this.leaveAudit = leaveAudit;
	}
	public List<LeaveAudit> getLeaveAudits() {
		return leaveAudits;
	}
	public void setLeaveAudits(List<LeaveAudit> leaveAudits) {
		this.leaveAudits = leaveAudits;
	}


	
	
}
