package com.wenjiaxi.oa.admin.leave.action;

import java.util.List;
import java.util.Map;

/**
* 
* @author WEN JIAXI
* @date 2016年8月4日 下午10:25:29
* @version 1.0
*/

public class LeaveAjax extends LeaveAction {

	private List<Map<String, Object>> responseMap;
	private List<List<Map<String, Object>>> responseList;
	/**
	 * 异步加载休假状态
	 * @return [{code:0, name:"新建"},{},{}]
	 */
	public String loadLeaveType(){
		try {
			responseMap = leaveService.getLeaveType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 异步加载休假状态和流程定义
	 * @return  [[{leaveType},{},{}...], [{process},{},{}...]]
	 */
	public String loadLeaveTypeAndProcess(){
		try {
			responseList = leaveService.getLeaveTypeAndProcess();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//getter setter
	public List<Map<String, Object>> getResponseMap() {
		return responseMap;
	}
	public void setResponseMap(List<Map<String, Object>> responseMap) {
		this.responseMap = responseMap;
	}
	public List<List<Map<String, Object>>> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<List<Map<String, Object>>> responseList) {
		this.responseList = responseList;
	}
	
}
