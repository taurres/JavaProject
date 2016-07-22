package com.wenjiaxi.oa.admin.identity.action;

import java.util.List;
import java.util.Map;


/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月20日 上午11:31:58
 * @version 1.0
 */

public class IdentityAjax extends IdentityAction {

	private List<Map<String, Object>> responseMap;
	private Map<String, List<Map<String, Object>>> responseMaps;
	private List<Map<String, String>> usernameList;
	//easyui combobox传入的文本框输入内容
	private String q;
	private String userId;

	//检查用户名是否重复的结果
	private boolean isExisted;

	/**
	 * 异步请求部门菜单
	 * @return 数据格式：[{id:1,name:xx},{}] List<Map<String,Object> 
	 */
	public String loadDepts(){
		try {
			responseMap = identityService.loadDepts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


	/**
	 * 异步请求部门和职位菜单
	 * @return 数据格式：{"depts":[{id:1,name:xx},{}],"jobs":[{},{}]} Map<String,List<Map<String,Object>>>
	 */
	public String loadDeptsJobs(){
		try {
			responseMaps = identityService.loadDeptsJobs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 用关键词搜索user的名字
	 * @return
	 */
	public String loadUserName(){
		try {
			usernameList = identityService.loadUserName(q);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 确认用户名是否重复
	 * @return
	 */
	public String confirmUserId(){
		isExisted = identityService.confirmUserId(userId);
		return SUCCESS;
	}
	
	
	//getter setter
	public List<Map<String, Object>> getResponseMap() {
		return responseMap;
	}
	public void setResponseMap(List<Map<String, Object>> responseMap) {
		this.responseMap = responseMap;
	}


	public Map<String, List<Map<String, Object>>> getResponseMaps() {
		return responseMaps;
	}


	public void setResponseMaps(Map<String, List<Map<String, Object>>> responseMaps) {
		this.responseMaps = responseMaps;
	}

	
	public List<Map<String, String>> getUsernameList() {
		return usernameList;
	}


	public void setUsernameList(List<Map<String, String>> usernameList) {
		this.usernameList = usernameList;
	}


	public String getQ() {
		return q;
	}


	public void setQ(String q) {
		this.q = q;
	}

	public boolean getIsExisted() {
		return isExisted;
	}


	public void setIsExisted(boolean isExisted) {
		this.isExisted = isExisted;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
