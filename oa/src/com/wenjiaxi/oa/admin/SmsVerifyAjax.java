package com.wenjiaxi.oa.admin;

import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.wenjiaxi.oa.admin.identity.service.IdentityService;

/**
* 
* @author WEN JIAXI
* @date 2016年8月1日 下午2:19:58
* @version 1.0
*/

public class SmsVerifyAjax extends ActionSupport {
	
	private static final long serialVersionUID = 6362922926767548304L;

	@Resource
	private IdentityService identityService;
	
	private String phone;
	private String smsCodeSent;
	private String smsCodeInput;
	private Map<String, Object> responseData;

	/**
	 * 发送短信验证码
	 * @return
	 * @throws Exception
	 */
	public String sendSms(){
		try {
			System.out.println("action:" + phone);
			responseData = identityService.sendSms(phone);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String smsLogin(){
		try {
			System.out.println("smsCodeinput " + smsCodeInput);
			System.out.println("smsCodeSent " + smsCodeSent);
			System.out.println("phone " + phone);
			responseData = identityService.smsLogin(phone, smsCodeInput, smsCodeSent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	//getter setter
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Map<String, Object> getResponseData() {
		return responseData;
	}
	public void setResponseData(Map<String, Object> responseData) {
		this.responseData = responseData;
	}
	public String getSmsCodeSent() {
		return smsCodeSent;
	}
	public void setSmsCodeSent(String smsCodeSent) {
		this.smsCodeSent = smsCodeSent;
	}
	public String getSmsCodeInput() {
		return smsCodeInput;
	}
	public void setSmsCodeInput(String smsCodeInput) {
		this.smsCodeInput = smsCodeInput;
	}


	
}
