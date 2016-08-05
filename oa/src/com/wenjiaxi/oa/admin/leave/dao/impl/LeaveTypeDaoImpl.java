package com.wenjiaxi.oa.admin.leave.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wenjiaxi.oa.admin.leave.dao.LeaveTypeDao;
import com.wenjiaxi.oa.core.dao.impl.BaseDaoImpl;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:18:02
 * @version 1.0
 */
@Repository("leaveTypeDao")
public class LeaveTypeDaoImpl extends BaseDaoImpl implements LeaveTypeDao {

	/**
	 * 获取leavetype
	 * @return
	 */
	public List<Map<String, Object>> getLeaveType() {
		return this.find("select new map(code as code, name as name) from LeaveType order by code");
	}

}
