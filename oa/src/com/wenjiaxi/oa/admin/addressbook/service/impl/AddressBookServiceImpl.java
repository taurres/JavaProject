package com.wenjiaxi.oa.admin.addressbook.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wenjiaxi.oa.admin.AdminConstant;
import com.wenjiaxi.oa.admin.addressbook.dao.ContactDao;
import com.wenjiaxi.oa.admin.addressbook.dao.ContactGroupDao;
import com.wenjiaxi.oa.admin.addressbook.service.AddressbookService;
import com.wenjiaxi.oa.admin.addressbook.entity.Contact;
import com.wenjiaxi.oa.admin.addressbook.entity.ContactGroup;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.exception.OAException;


/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:58:13
 * @version 1.0
 */
@Service("addressBookService")
public class AddressbookServiceImpl implements AddressbookService {

	@Resource
	private ContactDao contactDao;
	@Resource
	private ContactGroupDao contactGroupDao;
	
	/** TODO ########################### contactGroup ########################## */

	/**
	 * 分页查询ContactGroup
	 * @param contactGroup
	 * @param pageModel
	 * @return
	 */
	public List<ContactGroup> getContactGroupByPage(PageModel pageModel){
		try {
			List<ContactGroup> contactGroups = contactGroupDao.getContactGroupByPage(pageModel);
			return contactGroups;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("分页查询联系组时出错",e);
		}
	}
	
	/**
	 * 添加contactGroup
	 */
	public void addContactGroup(ContactGroup contactGroup){
		try {
			contactGroupDao.save(contactGroup);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("添加联系组时出错",e);
		}		
	}
	
	/**
	 * 根据id查询contactGroup
	 * @param id
	 * @return
	 */
	public ContactGroup getContactGroup(Long id){
		return contactGroupDao.get(ContactGroup.class, id);
	}
	
	/**
	 * 更新contactGroup
	 * @param contactGroup
	 */
	public void updateContactGroup(ContactGroup contactGroup){
		try {
			ContactGroup cg = new ContactGroup();
			cg = getContactGroup(contactGroup.getId());
			cg.setName(contactGroup.getName());
			cg.setRemark(contactGroup.getRemark());
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("更新联系组时出错",e);
		}
	}
	
	/**
	 * 删除contactGroup
	 * @param ids
	 */
	public void deleteContactGroup(String[] ids){
		try {
			contactGroupDao.deleteContactGroup(ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("删除联系组时出错",e);
		}
	}
	
	/**
	 * 异步加载联系组dtree
	 * @return
	 */
	public List<List<Object>> loadContactTree(){
		return contactGroupDao.getContactGroupsByIdAndName();
	}
	/** TODO ########################### contact ########################## */
	
	/**
	 * 分页查询contact
	 * @param contact
	 * @param pageModel
	 * @return
	 */
	public List<Contact> getContactByPage(String code, PageModel pageModel){
		List<Contact> contacts;
		try {
			contacts = contactDao.getContactByPage(code, pageModel, AdminConstant.MODULE_CODE_LENGTH);
			for (Contact contact : contacts) {
				if (contacts != null) {

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("分页查询contact时出错",e);
		}
		return contacts;
	}
	

	/**
	 * 添加contact
	 * @param contact
	 */
	public void addContact(Contact contact){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("添加contact时出错",e);
		}		
	}
	
	/**
	 * 通过code查询contact
	 * @param code
	 * @return
	 */
	public Contact getContact(String code){
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("根据code查询contact时出错",e);
		}
	}
	
	/**
	 * 更新contact
	 * @param contact
	 */
	public void updateContact(Contact contact){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("更新contact时出错",e);
		}
	}
	
	/**
	 * 批量删除contact
	 * @param ids
	 */
	public void deleteContact(String[] codes){
		contactDao.deleteContact(codes);
	}
	
}
