package com.wenjiaxi.oa.admin.leave.dao;

import java.util.List;

import com.wenjiaxi.oa.admin.leave.entity.LeaveAudit;
import com.wenjiaxi.oa.core.dao.BaseDao;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:17:20
 * @version 1.0
 */

public interface LeaveAuditDao extends BaseDao {

	/**
	 * 查询审批结果
	 * @param id
	 * @return
	 */
	List<LeaveAudit> getLeaveAuditByLeaveItemId(Long id);

}
