package com.wenjiaxi.oa.admin;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.wenjiaxi.oa.admin.identity.service.IdentityService;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月29日 下午8:27:04
 * @version 1.0
 */

public class UpdatePwdAction extends ActionSupport {

	private static final long serialVersionUID = -5614123932158060792L;

	@Resource
	private IdentityService identityService;
	
	private String oldPwd;
	private String newPwd;
	private String checkPwd;
	private String msg;
	@Override
	public String execute() throws Exception {
		try {
			boolean success = identityService.updatePwd(oldPwd, newPwd, checkPwd);
			if (success) {
				return SUCCESS;
			}
			setMsg("密码修改失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getCheckPwd() {
		return checkPwd;
	}
	public void setCheckPwd(String checkPwd) {
		this.checkPwd = checkPwd;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
