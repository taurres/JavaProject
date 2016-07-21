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

	private User user;
	private PageModel pageModel;
	private List<User> users;
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
		identityService.addUser(user);
		return SUCCESS;
	}
	
	
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
		
}
