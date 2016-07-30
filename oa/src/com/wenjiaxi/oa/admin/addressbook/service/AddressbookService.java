package com.wenjiaxi.oa.admin.addressbook.service;

import java.util.List;

import com.wenjiaxi.oa.admin.addressbook.entity.Contact;
import com.wenjiaxi.oa.admin.addressbook.entity.ContactGroup;
import com.wenjiaxi.oa.core.common.web.PageModel;



/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:16:15
 * @version 1.0
 */

public interface AddressbookService {

	/** TODO ########################### contactGroup ########################## */

	/**
	 * 分页查询ContactGroup
	 * @param contactGroup
	 * @param pageModel
	 * @return
	 */
	public List<ContactGroup> getContactGroupByPage(PageModel pageModel);
	
	/**
	 * 添加contactGroup
	 */
	public void addContactGroup(ContactGroup contactGroup);

	/**
	 * 根据id查询contactGroup
	 * @param id
	 * @return
	 */
	public ContactGroup getContactGroup(Long id);

	/**
	 * 更新contactGroup
	 * @param contactGroup
	 */
	public void updateContactGroup(ContactGroup contactGroup);

	/**
	 * 删除contactGroup
	 * @param ids
	 */
	public void deleteContactGroup(String[] ids);

	/**
	 * 异步加载联系组dtree
	 * @return
	 */
	public List<List<Object>> loadContactTree();
	
	/** TODO ########################### contact ########################## */

	/**
	 * 分页查询contact
	 * @param contact
	 * @param pageModel
	 * @return
	 */
	public List<Contact> getContactByPage(long contactGroup, PageModel pageModel);

	/**
	 * 添加contact
	 * @param contact
	 */
	public void addContact(Contact contact, long contactGroup);

	/**
	 * 通过code查询contact
	 * @param code
	 * @return
	 */
	public Contact getContact(long id);

	/**
	 * 更新contact
	 * @param contact
	 */
	public void updateContact(Contact contact, long contactGroup);

	/**
	 * 批量删除contact
	 * @param ids
	 */
	public void deleteContact(String[] ids);
}
