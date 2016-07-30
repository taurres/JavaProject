package com.wenjiaxi.oa.admin.addressbook.action;

import java.util.List;

import com.wenjiaxi.oa.admin.addressbook.entity.ContactGroup;
import com.wenjiaxi.oa.core.common.web.PageModel;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月26日 下午2:14:41
 * @version 1.0
 */

public class ContactGroupAction extends AddressbookAction {

	private static final long serialVersionUID = -3870015800646844644L;

	private ContactGroup contactGroup;
	private List<ContactGroup> contactGroups;
	private String ids;
	private String id;
	
	/**
	 * 分页查询contactGroup
	 * @return
	 */
	public String selectContactGroup(){
		try {
			if (pageModel == null) {
				pageModel = new PageModel();
			}
			
			contactGroups = addressbookService.getContactGroupByPage(pageModel);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加contactGroup
	 * @return
	 */
	public String addContactGroup(){
		try {
			addressbookService.addContactGroup(contactGroup);
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 返回更新contactGroup页面
	 * @return
	 */
	public String showUpdateContactGroup(){
		try {
			contactGroup = addressbookService.getContactGroup(contactGroup.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 更新contactGroup
	 * @return
	 */
	public String updateContactGroup(){
		try {
			addressbookService.updateContactGroup(contactGroup);
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除contactGroup
	 * @return
	 */
	public String deleteContactGroup(){
		try {
			addressbookService.deleteContactGroup(ids.split(","));
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	// getter setter
	public ContactGroup getContactGroup() {
		return contactGroup;
	}

	public void setContactGroup(ContactGroup contactGroup) {
		this.contactGroup = contactGroup;
	}

	public List<ContactGroup> getContactGroups() {
		return contactGroups;
	}

	public void setContactGroups(List<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
