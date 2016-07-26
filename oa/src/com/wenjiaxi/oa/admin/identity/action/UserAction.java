package com.wenjiaxi.oa.admin.identity.action;

import java.util.List;

import org.springframework.util.StringUtils;

import com.wenjiaxi.oa.admin.identity.entity.User;
import com.wenjiaxi.oa.core.common.web.PageModel;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月20日 上午12:19:29
 * @version 1.0
 */

public class UserAction extends IdentityAction {

	private static final long serialVersionUID = 2909904499857787452L;
	
	private User user;
	private List<User> users;
	private String userId;
	private String userIds;
	

	/**
	 * 分页查询user
	 * @return 
	 */
	public String selectUser(){
		
		try {
			if (pageModel == null) {
				pageModel = new PageModel();
			}
			
			users = identityService.getUserByPage(user, pageModel);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加user
	 * @return
	 */
	public String addUser(){
		try {
			identityService.addUser(user);
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 显示更新user页面
	 * @return
	 */
	public String showUpdateUser(){
		try {
			user = identityService.getUser(user.getUserId(), false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 更新user
	 * @return
	 */
	public String updateUser(){
		try {
			identityService.updateUser(user);
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 批量删除用户
	 * @return
	 */
	public String deleteUser(){
		try {
			identityService.deleteUser(userIds.split(","));
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 批量审批用户
	 * @return
	 */
	public String checkUser(){
		try {
			identityService.checkUser(userIds.split(","),user.getStatus());
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	

	
	
	
	// getter setter
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<User> getUsers() {
		return users;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}



	
	
	
		
}
