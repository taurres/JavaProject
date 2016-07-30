package com.wenjiaxi.oa.admin.addressbook.dao;

import java.util.List;
import java.util.Map;

import com.wenjiaxi.oa.admin.addressbook.entity.Contact;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.dao.BaseDao;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:14:07
 * @version 1.0
 */

public interface ContactDao extends BaseDao {

	/**
	 * 获取所有模块的code和name
	 * @return 包含code和name的集合
	 */
	List<Map<String,Object>> getContactsByCodeAndName();

	/**
	 * 分页查询contact
	 * @param contact
	 * @param pageModel
	 * @return 
	 */
	List<Contact> getContactByPage(String code, PageModel pageModel, int codeLength);

	/**
	 * 批量删除contact
	 * @param codes
	 */
	void deleteContact(String[] ids);

	/**
	 * 分页查询contact
	 * @param contactGroup
	 * @param pageModel
	 * @return
	 */
	List<Contact> getContactByPage(long contactGroup, PageModel pageModel);
}
