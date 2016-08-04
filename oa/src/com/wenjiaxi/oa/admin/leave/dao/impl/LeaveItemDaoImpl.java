package com.wenjiaxi.oa.admin.leave.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.wenjiaxi.oa.admin.AdminConstant;
import com.wenjiaxi.oa.admin.leave.dao.LeaveItemDao;
import com.wenjiaxi.oa.admin.leave.entity.LeaveItem;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.dao.impl.BaseDaoImpl;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:18:02
 * @version 1.0
 */
@Repository("leaveItemDao")
public class LeaveItemDaoImpl extends BaseDaoImpl implements LeaveItemDao {

	/**
	 * 按条件分页查询指定用户的休假
	 * @param pageModel
	 * @param leaveItem
	 * @return
	 */
	public List<LeaveItem> getLeaveItemByPageAndUser(PageModel pageModel, LeaveItem leaveItem){
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		hql.append("select l from LeaveItem l where l.creater.userId = ?");
		params.add(AdminConstant.getSessionUser().getUserId());
		if (leaveItem != null && leaveItem.getLeaveType() != null && !StringUtils.isEmpty(leaveItem.getLeaveType().getCode())) {
			hql.append(" and l.leaveType.code = ?");
			params.add(leaveItem.getLeaveType().getCode());
		}
		hql.append(" order by l.createDate");
		return this.findByPage(hql.toString(), pageModel, params);
	}
}
