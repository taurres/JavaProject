package com.wenjiaxi.oa.admin.leave.action;

import java.util.List;

import com.wenjiaxi.oa.admin.leave.entity.LeaveItem;

/**
* 
* @author WEN JIAXI
* @date 2016年8月4日 下午7:28:57
* @version 1.0
*/

public class LeaveItemAction extends LeaveAction {

	private static final long serialVersionUID = 1488504126945011486L;
	private LeaveItem leaveItem;
	private List<LeaveItem> leaveItems;

	/**
	 * 按条件分页查询指定用户的休假
	 * @return
	 */
	public String selectUserLeave(){
		try {
			leaveItems = leaveService.getLeaveItemByPageAndUser(pageModel, leaveItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 为指定用户添加休假
	 * @return
	 */
	public String addLeaveItem(){
		try {
			leaveService.addLeaveItem(leaveItem);
			setMsg("添加成功");
		} catch (Exception e) {
			setMsg("添加失败");
			e.printStackTrace();
		}
		return SUCCESS;
	}

	//getter setter
	public LeaveItem getLeaveItem() {
		return leaveItem;
	}
	public void setLeaveItem(LeaveItem leaveItem) {
		this.leaveItem = leaveItem;
	}
	public List<LeaveItem> getLeaveItems() {
		return leaveItems;
	}
	public void setLeaveItems(List<LeaveItem> leaveItems) {
		this.leaveItems = leaveItems;
	}
}
