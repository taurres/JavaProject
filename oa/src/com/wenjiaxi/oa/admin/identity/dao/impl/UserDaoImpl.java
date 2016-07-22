package com.wenjiaxi.oa.admin.identity.dao.impl;




import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.Select;
import com.wenjiaxi.oa.admin.AdminConstant;
import com.wenjiaxi.oa.admin.identity.dao.UserDao;
import com.wenjiaxi.oa.admin.identity.entity.User;
import com.wenjiaxi.oa.core.common.security.MD5;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.dao.impl.BaseDaoImpl;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:27:01
 * @version 1.0
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	/**
	 * 根据userId查询用户，其中userId经过MD5加密
	 * @param userId
	 * @return
	 */
	public User getUser(String userId){
		return this.findUniqueEntity("from User u where MD5(u.userId) = ?", userId);
	}
	
	/**
	 * 分页查询user
	 * @param user
	 * @param pageModel
	 * @return
	 */
	public List<User> getUserByPage(User user, PageModel pageModel){
		StringBuilder hql = new StringBuilder();
		//参数集合
		List<Object> params = new ArrayList<Object>();
		hql.append("from User u where 1=1");
		if (user != null) {
			//姓名查询
			if (!StringUtils.isEmpty(user.getName())) {
				hql.append(" and u.name like ?");
				params.add("%"+user.getName()+"%");
			}
			//电话查询
			if (!StringUtils.isEmpty(user.getPhone())) {
				hql.append(" and u.phone like ?");
				params.add("%"+user.getPhone()+"%");
			}
			//部门id查询
			if (user.getDept() != null && user.getDept().getId() != null && user.getDept().getId()>0) {
				hql.append(" and u.dept.id = ?");
				params.add(user.getDept().getId());
			}
		}
		hql.append("order by createDate");
		return this.findByPage(hql.toString(), pageModel, params);
	}
	
	/**
	 * 用关键词搜索user的名字
	 * @param username
	 * @return 符合条件的user名字
	 */
	public List<Map<String, String>> getUserName(String username){
		if (!StringUtils.isEmpty(username)) {
			List<Object> params = new ArrayList<Object>();
			params.add("%"+username+"%");
			return this.find("select new map(name as name) from User u where u.name like ?",params.toArray());
		}else {
			return new ArrayList<Map<String,String>>();
		}	
	}
	
	/**
	 * 批量删除user
	 * @param userIds
	 */
	public void deleteUser(String[] userIds){
		StringBuilder hql = new StringBuilder();
		hql.append("delete from User where userId in (");
		for (int i = 0; i < userIds.length; i++) {
			hql.append(i == 0? "?" : ",?");
		}
		hql.append(")");
		this.bulkUpdate(hql.toString(), userIds);
	}
	
	/**
	 * 批量审批用户
	 * @param userIds
	 * @param status
	 */
	public void checkUser(String[] userIds, Short status){
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		hql.append("update User set checkDate = ?, checker = ?, status = ? where userId in (");
		params.add(new Date());
		params.add(AdminConstant.getSessionUser());
		params.add(status);
		for (int i = 0; i < userIds.length; i++) {
			hql.append(i == 0 ? "?" : ",?");
			params.add(userIds[i]);
		}
		hql.append(")");
		this.bulkUpdate(hql.toString(), params.toArray());
		
	}
}
