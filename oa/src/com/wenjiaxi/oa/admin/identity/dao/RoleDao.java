package com.wenjiaxi.oa.admin.identity.dao;

import java.util.List;

import com.wenjiaxi.oa.admin.identity.entity.Role;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.dao.BaseDao;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:25:58
 * @version 1.0
 */

public interface RoleDao extends BaseDao{

	/**
	 * 分页查询role
	 * @param role
	 * @param pageModel
	 * @return
	 */
	List<Role> getRoleByPage(Role role, PageModel pageModel);

	/**
	 * 删除role
	 * @param ids
	 */
	void deleteRole(String[] ids);

}
