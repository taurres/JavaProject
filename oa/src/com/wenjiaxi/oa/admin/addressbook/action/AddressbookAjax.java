package com.wenjiaxi.oa.admin.addressbook.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月26日 下午2:59:47
 * @version 1.0
 */

public class AddressbookAjax extends AddressbookAction {

	private static final long serialVersionUID = 4822962227297665903L;
	
	private List<List<Object>> responseList;
	
	/**
	 * 异步加载模块dtree
	 * @return 数据格式: [[id,name],[],...]
	 */
	public String loadContactTree(){
		try {
			responseList = addressbookService.loadContactTree();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public List<List<Object>> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<List<Object>> responseList) {
		this.responseList = responseList;
	}
	
	
}
