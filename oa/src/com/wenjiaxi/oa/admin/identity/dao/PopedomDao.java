package com.wenjiaxi.oa.admin.identity.dao;

import java.util.List;
import java.util.Map;

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
	 * 根据role和module查询权限的OpCode
	 * @param moduleCode
	 * @param roleId
	 * @return
	 */
	List<String> getOpCodes(String moduleCode, Long roleId);

	/**
	 * 查询指定用户的权限的moduleCode
	 * @param userId
	 * @return 
	 */
	List<String> getModuleCodes(String userId);

	/**
	 * 根据用户id获取其所有权限的opCode
	 * @param userId
	 */
	List<String> getOpCodes(String userId);


}
