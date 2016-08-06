package com.wenjiaxi.oa.admin.leave.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wenjiaxi.oa.admin.leave.dao.LeaveAuditDao;
import com.wenjiaxi.oa.core.dao.impl.BaseDaoImpl;
import com.wenjiaxi.oa.admin.leave.entity.LeaveAudit;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:18:02
 * @version 1.0
 */
@Repository("leaveAuditDao")
public class LeaveAuditDaoImpl extends BaseDaoImpl implements LeaveAuditDao {

	/**
	 * 查询审批结果
	 * @param id
	 * @return
	 */
	public List<LeaveAudit> getLeaveAuditByLeaveItemId(Long id){
		String hql = "select la from LeaveAudit la where la.leaveItem.id = ? order by id asc";
		return this.find(hql, new Object[]{id});
	}
}
