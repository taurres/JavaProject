package com.wenjiaxi.oa.admin.identity.action;

import java.util.List;

import org.springframework.util.StringUtils;

import com.wenjiaxi.oa.admin.identity.entity.Module;
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
	private List<User> users;
	private String ids;
	private String moduleCode;
	private List<Module> ops;
	private String codes;

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
	
	/**
	 * 查询指定modules下的操作权限，同时返回选中的role所绑定的权限
	 * @return
	 */
	public String selectPopedom(){
		try {	
			//返回该module下的所有操作
			ops = identityService.getOps(moduleCode);
			//返回当前操作的角色role信息给页面
			role = identityService.getRole(role.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 绑定popedom
	 * @return
	 */
	public String bindPopedom(){
		try {
			System.out.println("action"+ !StringUtils.isEmpty(codes.split(",")));
			identityService.bindPopedom(moduleCode, role, codes);
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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public List<Module> getOps() {
		return ops;
	}
	public void setOps(List<Module> ops) {
		this.ops = ops;
	}
	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
	}

}
