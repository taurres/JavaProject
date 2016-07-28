package com.wenjiaxi.oa.admin.identity.service;

import java.util.List;
import java.util.Map;

import com.wenjiaxi.oa.admin.identity.entity.Module;
import com.wenjiaxi.oa.admin.identity.entity.Role;
import com.wenjiaxi.oa.admin.identity.entity.User;
import com.wenjiaxi.oa.core.common.web.PageModel;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 上午12:12:33
 * @version 1.0
 */

public interface IdentityService {

	/** TODO ########################### user ########################## */
	/**
	 * 登录方法
	 * @param userId
	 * @param password
	 * @param vcode
	 * @param key
	 * @return 登录数据
	 */
	public Map<String, Object> login(String userId, String password,
			String vcode, Integer key);
	
	/**
	 * 根据id查询User，根据是否进行MD5加密调用不同dao方法
	 * @param userId
	 * @param isMD5
	 * @return
	 */
	public User getUser(String userId, boolean isMD5);

	/**
	 * 分页查询user
	 * @param user
	 * @param pageModel
	 * @return List<User> user集合
	 */
	public List<User> getUserByPage(User user, PageModel pageModel);

	/**
	 * 查询所有部门的id name
	 * @return
	 */
	public List<Map<String, Object>> loadDepts();

	/**
	 * 异步请求部门和职位菜单
	 * @return
	 */
	public Map<String, List<Map<String, Object>>> loadDeptsJobs();
	
	/**
	 * 查询所有职业的code name
	 * @return
	 */
	public List<Map<String, Object>> loadJobs();

	/**
	 * 登出
	 */
	public void logout();

	/**
	 * 用关键词搜索user的名字
	 * @param q
	 * @return
	 */
	public List<Map<String, String>> loadUserName(String username);

	/**
	 * 添加user
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * 确认用户名是否重复
	 * @param userId
	 * @return
	 */
	public boolean confirmUserId(String userId);

	/**
	 * 更新user
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 删除user
	 * @param userIds
	 */
	public void deleteUser(String[] userIds);

	/**
	 * 批量审批user
	 * @param userIds
	 * @param status
	 */
	public void checkUser(String[] userIds, Short status);

	/** TODO ########################### role ########################## */

	/**
	 * 分页查询Role
	 * @param role
	 * @param pageModel
	 * @return
	 */
	public List<Role> getRoleByPage(PageModel pageModel);
	
	/**
	 * 添加role
	 */
	public void addRole(Role role);

	/**
	 * 根据id查询role
	 * @param id
	 * @return
	 */
	public Role getRole(Long id);

	/**
	 * 更新role
	 * @param role
	 */
	public void updateRole(Role role);

	/**
	 * 删除role
	 * @param split
	 */
	public void deleteRole(String[] split);

	/** TODO ########################### module ########################## */
	/**
	 * 异步加载模块dtree
	 * @return
	 */
	public List<Map<String,Object>> loadModuleTree();

	/**
	 * 分页查询module
	 * @param module
	 * @param pageModel
	 * @return
	 */
	public List<Module> getModuleByPage(String code, PageModel pageModel);

	/**
	 * 添加module
	 * @param module
	 */
	public void addModule(Module module);

	/**
	 * 通过code查询module
	 * @param code
	 * @return
	 */
	public Module getModule(String code);

	/**
	 * 更新module
	 * @param module
	 */
	public void updateModule(Module module);

	/**
	 * 批量删除module
	 * @param ids
	 */
	public void deleteModule(String[] codes);

	/**
	 * 分页查询已绑定指定role的user
	 * @param pageModel
	 * @param id
	 * @return
	 */
	public List<User> getBindedUser(PageModel pageModel, Long id);

	/**
	 * 分页查询可以绑定的user
	 * @param pageModel
	 * @param id
	 * @return
	 */
	public List<User> getBindableUser(PageModel pageModel, Long id);

	/**
	 * 给用户绑定角色
	 * @param roleId
	 * @param userIds
	 */
	public void bindUser(Long roleId, String[] userIds);

	/**
	 * 解绑用户
	 * @param id
	 * @param split
	 */
	public void unbindUser(Long roleId, String[] userIds);

	/**
	 * 查询指定module下的所有操作权限
	 * @param moduleCode
	 * @return
	 */
	public List<Module> getOps(String moduleCode);

	/**
	 * 异步加载权限树
	 * @return
	 */
	public List<Map<String, Object>> loadPopedomTree();

	/**
	 * 绑定popedom
	 * @param moduleCode
	 * @param role
	 * @param split
	 */
	public void bindPopedom(String moduleCode, Role role, String[] codes);

	/**
	 * 异步查询指定模块指定角色下已经绑定的popedom
	 * @param id
	 * @param code
	 * @return
	 */
	public List<String> getBindedPopedom(String code, Long id);





}
