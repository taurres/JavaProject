package com.wenjiaxi.oa.admin.addressbook.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wenjiaxi.oa.admin.addressbook.dao.ContactGroupDao;
import com.wenjiaxi.oa.admin.addressbook.entity.ContactGroup;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.dao.impl.BaseDaoImpl;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:14:49
 * @version 1.0
 */
@Repository("contactGroupDao")
public class ContactGroupDaoImpl extends BaseDaoImpl implements ContactGroupDao {

	/**
	 * 分页查询contactGroup
	 * @param contactGroup
	 * @param pageModel
	 * @return
	 */
	public List<ContactGroup> getContactGroupByPage(PageModel pageModel){
		return findByPage("from ContactGroup order by id", pageModel, null);		
	}
	
	/**
	 * 删除contactGroup
	 * @param ids
	 */
	public void deleteContactGroup(String[] ids){
		Long[] idsLong = new Long[ids.length];
		StringBuilder hql = new StringBuilder();
		hql.append("delete from ContactGroup where id in (");
		for (int i = 0; i < ids.length; i++) {
			hql.append(i == 0 ? "?" : ",?");
			idsLong[i] = Long.valueOf(ids[i]);
		}
		hql.append(")");
		this.bulkUpdate(hql.toString(), idsLong);
	}
	
	/**
	 * 获取所有联系组的id和name
	 * @return 数据格式: [[id,name],[],...]
	 */
	public List<List<Object>> getContactGroupsByIdAndName(){
		return this.find("select new list(id as id, name as name) from ContactGroup order by id");
}
}
