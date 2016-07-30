package com.wenjiaxi.oa.admin.addressbook.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.wenjiaxi.oa.admin.addressbook.dao.ContactDao;
import com.wenjiaxi.oa.admin.addressbook.entity.Contact;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.dao.impl.BaseDaoImpl;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:14:49
 * @version 1.0
 */
@Repository("contactDao")
public class ContactDaoImpl extends BaseDaoImpl implements ContactDao {

	/**
	 * 分页查询contact
	 * @param contactGroup
	 * @param pageModel
	 * @return
	 */
	public List<Contact> getContactByPage(long contactGroup, PageModel pageModel){
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		if (contactGroup != 0L) {
			hql.append("from Contact where contactGroup.id = ? ");
			params.add(contactGroup);
			return this.findByPage(hql.toString(), pageModel, params);
		}else {
			return this.findByPage("from Contact", pageModel, null);
		}
		
		
	}
	
	/**
	 * 获取所有模块的code和name
	 * @return 包含code和name的集合
	 */
	public List<Map<String,Object>> getContactsByCodeAndName(){
		return this.find("select new map(code as id,name as name) from Contact order by code");
	}
	
	/**
	 * 分页查询contact
	 * @param parentCode 当前选中的节点的code
	 * @param contact
	 * @param pageModel
	 */
	public List<Contact> getContactByPage(String parentCode, PageModel pageModel, int codeLength){
		//如果code为空或者等于根节点0，则当做空字符串
		if(StringUtils.isEmpty(parentCode) || parentCode.equals("0")){
			parentCode = "";
		}
		List<Object> params = new ArrayList<Object>();
		//要查找的节点长度等于选中节点的长度加每一个级别code的长度
		codeLength = parentCode.length() + codeLength;
		params.add(codeLength);
		//查询以选中节点开头的节点
		params.add(parentCode + "%");
		return this.findByPage("from Contact where length(code) = ? and code like ? order by code", pageModel, params);
	}
	
	/**
	 * 批量删除contact
	 * @param codes
	 */
	public void deleteContact(String[] ids){
		StringBuilder hql = new StringBuilder();
		Long[] params = new Long[ids.length];
		hql.append("delete from Contact where id in (");
		for (int i = 0; i < ids.length; i++) {
			hql.append(i == 0 ? "?" : ",?");
			params[i] = Long.valueOf(ids[i]);
		}
		hql.append(")");
		this.bulkUpdate(hql.toString(), params);
	}
}
