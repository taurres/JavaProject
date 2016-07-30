package com.wenjiaxi.oa.admin.identity.dao.impl;




import java.util.List;

import org.springframework.stereotype.Repository;

import com.wenjiaxi.oa.admin.identity.dao.RoleDao;
import com.wenjiaxi.oa.admin.identity.entity.Role;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.dao.impl.BaseDaoImpl;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:27:01
 * @version 1.0
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

	/**
	 * 分页查询role
	 * @param role
	 * @param pageModel
	 * @return
	 */
	public List<Role> getRoleByPage(PageModel pageModel){
		return findByPage("from Role order by createDate", pageModel, null);		
	}
	
	/**
	 * 删除role
	 * @param ids
	 */
	public void deleteRole(String[] ids){
		Long[] idsLong = new Long[ids.length];
		StringBuilder hql = new StringBuilder();
		hql.append("delete from Role where id in (");
		for (int i = 0; i < ids.length; i++) {
			hql.append(i == 0 ? "?" : ",?");
			idsLong[i] = Long.valueOf(ids[i]);
		}
		hql.append(")");
		this.bulkUpdate(hql.toString(), idsLong);
	}
}
