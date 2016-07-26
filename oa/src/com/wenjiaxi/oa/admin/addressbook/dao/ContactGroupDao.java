package com.wenjiaxi.oa.admin.addressbook.dao;

import java.util.List;

import com.wenjiaxi.oa.admin.addressbook.entity.ContactGroup;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.dao.BaseDao;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:14:07
 * @version 1.0
 */

public interface ContactGroupDao extends BaseDao {

	/**
	 * 分页查询contactGroup
	 * @param contactGroup
	 * @param pageModel
	 * @return
	 */
	List<ContactGroup> getContactGroupByPage(PageModel pageModel);

	/**
	 * 删除contactGroup
	 * @param ids
	 */
	void deleteContactGroup(String[] ids);

	/**
	 * 获取所有联系组的id和name
	 * @return 数据格式: [[id,name],[],...]
	 */
	List<List<Object>> getContactGroupsByIdAndName();
}
