package com.wenjiaxi.oa.admin.identity.action;

import java.util.List;

import com.wenjiaxi.oa.admin.identity.entity.Role;
import com.wenjiaxi.oa.admin.identity.entity.User;
import com.wenjiaxi.oa.core.common.web.PageModel;

/**
 * 用户绑定角色权限action
 * @author WEN JIAXI
 * @date 2016年7月23日 下午5:58:23
 * @version 1.0
 */

public class RoleBindAction extends IdentityAction {

	private static final long serialVersionUID = -2219677877282646338L;
	
	private Role role;
	private List<Role> roles;
	private User user;
	private List<User> users;
	private String ids;

	/**
	 * 分页查询已绑定指定role的user
	 * @return
	 */
	public String showBindedUser(){
		try {	
			users = identityService.getBindedUser(pageModel, role.getId());	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 分页查询可以绑定的user
	 * @return
	 */
	public String showBindableUser(){
		try {	
			System.out.println("action:"+role.getId());
			pageModel.setPageSize(5);
			users = identityService.getBindableUser(pageModel, role.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 给用户绑定角色
	 * @return
	 */
	public String bindUser(){
		try {
			identityService.bindUser(role.getId(), ids.split(","));
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 解除绑定用户
	 * @return
	 */
	public String unbindUser(){
		try {
			identityService.unbindUser(role.getId(), ids.split(","));
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	// getter setter
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	

}
