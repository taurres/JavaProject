package com.wenjiaxi.oa.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.wenjiaxi.oa.admin.identity.service.IdentityService;

/**
 * 登录action
 * @author WEN JIAXI
 * @date 2016年7月19日 上午8:37:31
 * @version 1.0
 */

public class LoginAjax extends ActionSupport {
	
	private static final long serialVersionUID = -8383829456942024927L;
	@Resource
	private IdentityService identityService;
	
	private String userId;
	private String password;
	private String vcode;
	private Integer key;
	private String phone;
	private String smsCode;
	//以Map数据形式返回登录信息msg和登录状态status给页面
	private Map<String, Object> responseData = new HashMap<String, Object>();
	@Override
	public String execute() throws Exception {
		responseData = identityService.login(userId, password, vcode, key);
		System.out.println(responseData);
		return SUCCESS;
	}
	
	//不设置多余的get方法，避免返回未封装的数据
	public Map<String, Object> getResponseData() {
		return responseData;
	}
	public void setResponseData(Map<String, Object> responseData) {
		this.responseData = responseData;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	
	
	
	

}
