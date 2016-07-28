package com.wenjiaxi.oa.admin.identity.dao.impl;




import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sun.org.apache.bcel.internal.generic.Select;
import com.wenjiaxi.oa.admin.identity.dao.PopedomDao;
import com.wenjiaxi.oa.admin.identity.entity.Module;
import com.wenjiaxi.oa.admin.identity.entity.Popedom;
import com.wenjiaxi.oa.core.dao.impl.BaseDaoImpl;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:27:01
 * @version 1.0
 */
@Repository("popedomDao")
public class PopedomDaoImpl extends BaseDaoImpl implements PopedomDao {

	/**
	 * 删除指定角色下指定模块的权限
	 * @param moduleCode
	 * @param id
	 */
	public void deletePopedom(String moduleCode, Long id){
		List<Object> params = new ArrayList<Object>();
		params.add(moduleCode);
		params.add(id);
		this.bulkUpdate("delete from Popedom where module.code = ? and role.id = ?", params.toArray());
	}

	/**
	 * 根据role和module查询popedom的code
	 * @param moduleCode
	 * @param roleId
	 * @return
	 */
	public List<String> getCodes(String moduleCode, Long roleId){
		List<Object> params = new ArrayList<Object>();
		params.add(moduleCode);
		params.add(roleId);
		return this.find("select opera.code from Popedom where module.code = ? and role.id = ?", params.toArray());
	}
}
