package com.wenjiaxi.oa.admin.addressbook.action;

import java.util.List;

import com.wenjiaxi.oa.admin.addressbook.entity.Contact;
import com.wenjiaxi.oa.core.common.web.PageModel;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月26日 下午3:47:52
 * @version 1.0
 */

public class ContactAction extends AddressbookAction {

	private static final long serialVersionUID = 7980602076249858829L;

	private Contact contact;
	private List<Contact> contacts;
	private long contactGroup;
	private String ids;	

	/**
	 * 分页查询contact
	 * @return 
	 */
	public String selectContact(){
		
		try {
			if (pageModel == null) {
				pageModel = new PageModel();
			}
			contacts = addressbookService.getContactByPage(contactGroup, pageModel);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加contact
	 * @return
	 */
	public String addContact(){
		try {
			addressbookService.addContact(contact, contactGroup);
			setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
			setMsg("failed");
		}
		return SUCCESS;
	}

	/**
	 * 显示更新contact页面
	 * @return
	 */
	public String showUpdateContact(){
		try {
			contact = addressbookService.getContact(contact.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 更新contact
	 * @return
	 */
	public String updateContact(){
		try {
			addressbookService.updateContact(contact, contactGroup);
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 批量删除用户
	 * @return
	 */
	public String deleteContact(){
		try {
			addressbookService.deleteContact(ids.split(","));
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	// getter setter
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public long getContactGroup() {
		return contactGroup;
	}

	public void setContactGroup(long contactGroup) {
		this.contactGroup = contactGroup;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}




}
