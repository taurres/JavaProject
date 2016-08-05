package com.wenjiaxi.oa.admin.leave.dao;

import java.util.List;
import java.util.Map;

import com.wenjiaxi.oa.core.dao.BaseDao;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:17:20
 * @version 1.0
 */

public interface LeaveTypeDao extends BaseDao {

	/**
	 * 获取leavetype
	 * @return
	 */
	List<Map<String, Object>> getLeaveType();

}
