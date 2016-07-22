package com.wenjiaxi.oa.admin.identity.service;

import java.util.List;
import java.util.Map;

import com.wenjiaxi.oa.admin.identity.entity.Dept;
import com.wenjiaxi.oa.admin.identity.entity.User;
import com.wenjiaxi.oa.core.common.web.PageModel;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 上午12:12:33
 * @version 1.0
 */

public interface IdentityService {

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

}
