package com.wenjiaxi.oa.admin.identity.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wenjiaxi.oa.admin.identity.dao.DeptDao;
import com.wenjiaxi.oa.admin.identity.entity.Dept;
import com.wenjiaxi.oa.core.dao.impl.BaseDaoImpl;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:27:01
 * @version 1.0
 */
@Repository("deptDao")
public class DeptDaoImpl extends BaseDaoImpl implements DeptDao {

	/**
	 * 查询所有部门的id name
	 * @return
	 */
	public List<Map<String, Object>> getDeptsByIdAndName(){
		return this.find("select new map(id as id,name as name) from Dept d order by d.id asc"); 
	}

}
