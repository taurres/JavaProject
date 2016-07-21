package com.wenjiaxi.oa.admin.identity.dao;

import java.util.List;
import java.util.Map;

import com.wenjiaxi.oa.admin.identity.entity.User;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.dao.BaseDao;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:25:58
 * @version 1.0
 */

public interface UserDao extends BaseDao{

	/**
	 * 根据userId查询用户，其中userId经过MD5加密
	 * @param userId
	 * @return
	 */
	public User getUser(String userId);

	/**
	 * 分页查询user
	 * @param user
	 * @param pageModel
	 * @return
	 */
	public List<User> getUserByPage(User user, PageModel pageModel);

	/**
	 * 获取符合关键词的user的名字
	 * @param username
	 * @return 符合条件的user名字
	 */
	public List<Map<String, String>> getUserName(String username);



}
