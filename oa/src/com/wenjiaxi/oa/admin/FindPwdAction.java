package com.wenjiaxi.oa.admin;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.wenjiaxi.oa.admin.identity.service.IdentityService;

public class FindPwdAction extends ActionSupport {

	@Resource
	private IdentityService identityService;
	
	private String userId;
	private int question;
	private String answer;
	private String msg;
	
	@Override
	public String execute() {
		try{
			msg = identityService.findPwd(userId, question, answer);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getQuestion() {
		return question;
	}

	public void setQuestion(int question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
