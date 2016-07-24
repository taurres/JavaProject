package com.wenjiaxi.oa.admin.identity.action;

import java.util.List;

import com.wenjiaxi.oa.admin.identity.entity.Role;
import com.wenjiaxi.oa.core.common.web.PageModel;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月23日 下午5:58:23
 * @version 1.0
 */

public class RoleAction extends IdentityAction {

	private static final long serialVersionUID = -5411041136062662346L;
	
	private Role role;
	private List<Role> roles;
	private String ids;
	
	/**
	 * 分页查询role
	 * @return
	 */
	public String selectRole(){
		try {
			if (pageModel == null) {
				pageModel = new PageModel();
			}
			
			roles = identityService.getRoleByPage(pageModel);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加role
	 * @return
	 */
	public String addRole(){
		try {
			identityService.addRole(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 返回更新role页面
	 * @return
	 */
	public String showUpdateRole(){
		try {
			role = identityService.getRole(role.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 更新role
	 * @return
	 */
	public String updateRole(){
		try {
			identityService.updateRole(role);
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除role
	 * @return
	 */
	public String deleteRole(){
		try {
			identityService.deleteRole(ids.split(","));
		} catch (Exception e) {
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

	
	
	
}
