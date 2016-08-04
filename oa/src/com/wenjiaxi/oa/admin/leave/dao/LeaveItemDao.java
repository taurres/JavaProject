package com.wenjiaxi.oa.admin.leave.dao;

import java.util.List;

import com.wenjiaxi.oa.admin.leave.entity.LeaveItem;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.dao.BaseDao;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 下午7:17:20
 * @version 1.0
 */

public interface LeaveItemDao extends BaseDao {

	/**
	 * 按条件分页查询指定用户的休假
	 * @param pageModel
	 * @param leaveItem
	 * @return
	 */
	List<LeaveItem> getLeaveItemByPageAndUser(PageModel pageModel, LeaveItem leaveItem);

}
