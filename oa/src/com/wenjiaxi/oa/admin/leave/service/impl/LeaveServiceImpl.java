package com.wenjiaxi.oa.admin.leave.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wenjiaxi.oa.admin.leave.dao.LeaveAuditDao;
import com.wenjiaxi.oa.admin.leave.dao.LeaveItemDao;
import com.wenjiaxi.oa.admin.leave.dao.LeaveTypeDao;
import com.wenjiaxi.oa.admin.leave.service.LeaveService;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:56:25
 * @version 1.0
 */
@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {

	@Resource
	private LeaveAuditDao leaveAuditDao;
	@Resource
	private LeaveItemDao leaveItemDao;
	@Resource
	private LeaveTypeDao leaveTypeDao;
}
