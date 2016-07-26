package com.wenjiaxi.oa.admin.identity.dao;

import java.util.List;
import java.util.Map;

import com.wenjiaxi.oa.admin.identity.entity.Module;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.dao.BaseDao;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:25:58
 * @version 1.0
 */

public interface ModuleDao extends BaseDao{

	/**
	 * 获取所有模块的code和name
	 * @return 包含code和name的集合
	 */
	List<Map<String,Object>> getModulesByCodeAndName();

	/**
	 * 分页查询module
	 * @param module
	 * @param pageModel
	 * @return 
	 */
	List<Module> getModuleByPage(String code, PageModel pageModel, int codeLength);

	/**
	 * 批量删除module
	 * @param codes
	 */
	void deleteModule(String[] codes);

}
