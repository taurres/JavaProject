package com.wenjiaxi.oa.admin.identity.dao;

import java.util.List;
import java.util.Map;

import com.wenjiaxi.oa.core.dao.BaseDao;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:25:58
 * @version 1.0
 */

public interface DeptDao extends BaseDao{

	/**
	 * 查询所有部门的id name
	 * @return
	 */
	public List<Map<String, Object>> getDeptsByIdAndName(); 
}
