package com.wenjiaxi.oa.admin.identity.dao.impl;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wenjiaxi.oa.admin.identity.dao.JobDao;
import com.wenjiaxi.oa.core.dao.impl.BaseDaoImpl;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:27:01
 * @version 1.0
 */
@Repository("jobDao")
public class JobDaoImpl extends BaseDaoImpl implements JobDao {


	/**
	 * 查询所有职业的code name
	 * @return
	 */
	public List<Map<String, Object>> getJobsByCodeAndName(){
		return this.find("select new map(code as code, name as name) from Job j order by j.code asc");	
	}
}
