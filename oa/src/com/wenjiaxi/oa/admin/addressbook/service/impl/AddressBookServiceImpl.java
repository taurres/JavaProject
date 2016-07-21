package com.wenjiaxi.oa.admin.addressbook.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wenjiaxi.oa.admin.addressbook.dao.ContactDao;
import com.wenjiaxi.oa.admin.addressbook.dao.ContactGroupDao;
import com.wenjiaxi.oa.admin.addressbook.service.AddressbookService;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:58:13
 * @version 1.0
 */
@Service("addressBookService")
public class AddressBookServiceImpl implements AddressbookService {

	@Resource
	private ContactDao contactDao;
	@Resource
	private ContactGroupDao contactGroupDao;
}
