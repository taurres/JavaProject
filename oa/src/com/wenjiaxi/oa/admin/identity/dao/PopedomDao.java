package com.wenjiaxi.oa.admin.identity.dao;

import java.util.List;

import com.wenjiaxi.oa.admin.identity.entity.Module;
import com.wenjiaxi.oa.core.dao.BaseDao;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:25:58
 * @version 1.0
 */

public interface PopedomDao extends BaseDao{

	/**
	 * 删除指定用户下指定模块的权限
	 * @param moduleCode
	 * @param roleId
	 */
	void deletePopedom(String moduleCode, Long roleId);

	/**
	 * 根据role和module查询popedom的code
	 * @param moduleCode
	 * @param roleId
	 * @return
	 */
	List<String> getCodes(String moduleCode, Long roleId);


}
