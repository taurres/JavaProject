package com.wenjiaxi.oa.admin.identity.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.wenjiaxi.oa.admin.AdminConstant;
import com.wenjiaxi.oa.admin.identity.dao.DeptDao;
import com.wenjiaxi.oa.admin.identity.dao.JobDao;
import com.wenjiaxi.oa.admin.identity.dao.ModuleDao;
import com.wenjiaxi.oa.admin.identity.dao.PopedomDao;
import com.wenjiaxi.oa.admin.identity.dao.RoleDao;
import com.wenjiaxi.oa.admin.identity.dao.UserDao;
import com.wenjiaxi.oa.admin.identity.entity.Module;
import com.wenjiaxi.oa.admin.identity.entity.Role;
import com.wenjiaxi.oa.admin.identity.entity.User;
import com.wenjiaxi.oa.admin.identity.service.IdentityService;
import com.wenjiaxi.oa.core.action.VerifyAction;
import com.wenjiaxi.oa.core.common.security.MD5;
import com.wenjiaxi.oa.core.common.web.CookieUtil;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.dao.HierarchyIdGenerator;
import com.wenjiaxi.oa.core.exception.OAException;


/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月16日 上午12:14:24
 * @version 1.0
 */
@Service("identityService")
public class IdentityServiceImpl implements IdentityService {
	@Resource
	private HierarchyIdGenerator hierarchyIdGenerator;
	@Resource
	private DeptDao deptDao;
	@Resource
	private JobDao jobDao;
	@Resource
	private ModuleDao moduleDao;
	@Resource
	private PopedomDao popedomDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private UserDao userDao;


	
	/** TODO################### 用户业务 ##################### */
	/**
	 * 登录方法
	 * @param userId
	 * @param password
	 * @param vcode
	 * @param key
	 * @return 登录数据 status:0登陆成功 1验证码错误 2用户名或密码不正确
	 */
	@Override
	public Map<String, Object> login(String userId, String password,
			String vcode, Integer key) {
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("msg", "验证码错误");
			data.put("status", 1);
			//获取存放在session中的验证码
			String code = (String) ServletActionContext.getRequest().getSession().getAttribute(VerifyAction.VERIFY_CODE);
			//判断验证码是否正确
			if (!StringUtils.isEmpty(code) && code.equalsIgnoreCase(vcode)) {
				//验证码正确则查找用户
				User user = userDao.get(User.class, userId);
				if (user != null && user.getPassWord().equals(MD5.getMD5(password))) {
					//如果查找到用户且密码正确,将user放入session，登录状态为0
					ActionContext.getContext().getSession().put(AdminConstant.SESSION_USER, user);
					data.put("msg", "登录成功");
					data.put("status", 0);
					//如果用户选择记录用户，则将userId放入cookie
					if (key != null && key.equals(1)) {
						CookieUtil.addCookie(AdminConstant.COOKIE_LOGIN, MD5.getMD5(user.getUserId()), AdminConstant.COOKIE_LOGIN_AGE);
					}
				}else {
					//登录失败
					data.put("msg", "用户名或密码不正确");
					data.put("status", 2);
				}
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("登录方法异常",e);
		}
	}
	
	/**
	 * 根据id查询User，根据是否进行MD5加密调用不同dao方法
	 * @param userId
	 * @param isMD5
	 * @return
	 */
	public User getUser(String userId, boolean isMD5){
		try{
			User user = null;
			if (isMD5) {
				user =  userDao.getUser(userId);
			}else{
				user =  userDao.get(User.class, userId);
			}
			//获取延迟加载数据
			if (user != null) {
				if (user.getDept() != null) user.getDept().getId();
				if (user.getJob() != null) user.getJob().getCode();
			}
			return user;
		}catch(Exception e){
			e.printStackTrace();
			throw new OAException("查询User出现异常",e);
		}
	}

	/**
	 * 分页查询user
	 * @param user
	 * @param pageModel
	 * @return List<User> user集合
	 */
	public List<User> getUserByPage(User user, PageModel pageModel){
		try {
			List<User> users = userDao.getUserByPage(user, pageModel);
			//加载延迟加载的属性
			for (User u : users){
				if (u.getDept() != null) u.getDept().getId();
				if (u.getJob() != null) u.getJob().getName(); 
				if (u.getCreater() != null) u.getCreater().getName();
				if (u.getChecker() != null) u.getChecker().getName();
			}

			return users;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("分页查询出现异常",e);
		}
	}
	
	/**
	 * 查询所有部门的id name
	 * @return
	 */
	public List<Map<String, Object>> loadDepts(){
		try {
			return deptDao.getDeptsByIdAndName();
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("异步请求部门菜单时出错",e);
		}
	}
	
	/**
	 * 查询所有职业的code name
	 * @return
	 */
	public List<Map<String, Object>> loadJobs(){
		try {
			return jobDao.getJobsByCodeAndName();
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("异步请求部门菜单时出错",e);
		}
	}
	
	/**
	 * 异步请求部门和职位菜单
	 * @return
	 */
	public Map<String, List<Map<String, Object>>> loadDeptsJobs(){
		try {
			List<Map<String, Object>> depts = loadDepts();
			List<Map<String, Object>> jobs = loadJobs();
			Map<String, List<Map<String, Object>>> deptsJobs = new HashMap<>();
			deptsJobs.put("depts", depts);
			deptsJobs.put("jobs", jobs);
			return deptsJobs;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("异步请求部门和职位菜单时出错",e);
		}
	}
	
	/**
	 * 登出
	 */
	public void logout(){
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			//session不为空，则从session中除去user
			if (session != null) {
				session.invalidate();
			}
			//删除登录cookie
			CookieUtil.removeCookie(AdminConstant.COOKIE_LOGIN);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("登出时出错",e);
		}
	}
	
	/**
	 * 用关键词搜索user的名字
	 * @param 名字查询关键词
	 * @return 符合条件的user的名字
	 */
	public List<Map<String, String>> loadUserName(String username){
		try {
			return userDao.getUserName(username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("搜索user名字时出错",e);
		}
	}
	
	/**
	 * 添加user
	 * @param user
	 */
	public void addUser(User user){
		try {
			user.setPassWord(MD5.getMD5(user.getPassWord()));
			user.setCreateDate(new Date());
			user.setCreater(AdminConstant.getSessionUser());
			userDao.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("添加用户时出错",e);
		}
		
	}
	/**
	 * 确认用户名是否重复
	 * @param userId
	 * @return
	 */
	public boolean confirmUserId(String userId){
		try {
			return getUser(userId, false) != null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("确认用户名是否重复时出错",e);
		}
	}
	
	/**
	 * 更新user
	 * @param user
	 */
	public void updateUser(User user){
		try {
			User u = getUser(user.getUserId(), false);
			u.setAnswer(user.getAnswer());
			u.setDept(user.getDept());
			u.setEmail(user.getEmail());
			u.setJob(user.getJob());
			u.setModifier(AdminConstant.getSessionUser());
			u.setModifyDate(new Date());
			u.setName(user.getName());
			u.setPhone(user.getPhone());
			u.setQqNum(user.getQqNum());
			u.setQuestion(user.getQuestion());
			u.setSex(user.getSex());
			u.setTel(user.getTel());
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("更新用户时出错",e);
		}
	}

	/**
	 * 批量删除user
	 * @param userIds
	 */
	public void deleteUser(String[] userIds){
		try {
			userDao.deleteUser(userIds);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("删除用户时出错",e);
		}
	}
	
	/**
	 * 批量审批user
	 * @param userIds
	 * @param status
	 */
	public void checkUser(String[] userIds, Short status){
		try {
			userDao.checkUser(userIds, status);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("审批用户时出错",e);
		}
	}
	
	/** TODO################### 角色业务 ##################### */

	/**
	 * 分页查询Role
	 * @param role
	 * @param pageModel
	 * @return
	 */
	public List<Role> getRoleByPage(PageModel pageModel){
		try {
			List<Role> roles = roleDao.getRoleByPage(pageModel);
			for (Role r : roles) {
				if(r.getModifier() != null) r.getModifier().getName();
				if(r.getCreater() != null) r.getCreater().getName();
			}
			return roles;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("分页查询角色时出错",e);
		}
	}
	
	/**
	 * 添加role
	 */
	public void addRole(Role role){
		try {
			role.setCreateDate(new Date());
			role.setCreater(AdminConstant.getSessionUser());
			roleDao.save(role);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("添加角色时出错",e);
		}		
	}
	
	/**
	 * 根据id查询role
	 * @param id
	 * @return
	 */
	public Role getRole(Long id){
		return roleDao.get(Role.class, id);
	}
	
	/**
	 * 更新role
	 * @param role
	 */
	public void updateRole(Role role){
		try {
			Role r = new Role();
			r = getRole(role.getId());
			r.setName(role.getName());
			r.setRemark(role.getRemark());
			r.setModifyDate(new Date());
			r.setModifier(AdminConstant.getSessionUser());
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("更新角色时出错",e);
		}
	}
	
	/**
	 * 删除role
	 * @param split
	 */
	public void deleteRole(String[] ids){
		try {
			roleDao.deleteRole(ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("删除角色时出错",e);
		}
	}
	
	/** TODO################### 模块业务 ##################### */
	
	/**
	 * 异步加载模块dtree
	 * @return [{id: ,pid: ,name: },{},{}]
	 */
	public List<Map<String,Object>> loadModuleTree(){
		try {
			List<Map<String, Object>> maps = moduleDao.getModulesByCodeAndName();
			for (Map<String, Object> map : maps) {
				String code = (String) map.get("id");
				//判断code长度，code大于4则截取code前面的作为parentCode，code等于4则parentCode为"0"
				String pid = code.length() == 4 ? "0" : code.substring(0, code.length() - AdminConstant.MODULE_CODE_LENGTH);
				//获取名字，同时把名字中的"-"去掉
				String name = ((String) map.get("name")).replace("-", "");
				//将数组格式设置为[{id: ,pid: ,name: },{},{}]
				map.put("pid", pid);
				map.put("name", name);
			}
			return maps;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("加载模块dtree时出错",e);
		}
	}
	
	/**
	 * 分页查询module
	 * @param module
	 * @param pageModel
	 * @return
	 */
	public List<Module> getModuleByPage(String code, PageModel pageModel){
		List<Module> modules;
		try {
			modules = moduleDao.getModuleByPage(code, pageModel, AdminConstant.MODULE_CODE_LENGTH);
			for (Module module : modules) {
				if (modules != null) {
					if (module.getCreater() != null) module.getCreater().getName();
					if (module.getModifier() != null) module.getModifier().getName();
				}
			}
			return modules;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("分页查询module时出错",e);
		}
	}
	

	/**
	 * 添加module
	 * @param module
	 */
	public void addModule(Module module){
		try {
			String parentCode = module.getCode();
			//生成有层次的code
			String code = hierarchyIdGenerator.generateId(Module.class, "code", parentCode, AdminConstant.MODULE_CODE_LENGTH);
			module.setCode(code);
			//在name前添加对应层次的"-"
			module.setName(parentCode.replaceAll(".", "-") + module.getName());
			module.setCreateDate(new Date());
			module.setCreater(AdminConstant.getSessionUser());
			moduleDao.save(module);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("添加module时出错",e);
		}		
	}
	
	/**
	 * 通过code查询module
	 * @param code
	 * @return
	 */
	public Module getModule(String code){
		try {
			Module module = moduleDao.get(Module.class, code);
			//将module名字中的---去掉再显示给用户
			module.setName(module.getName().replace("-", ""));
			return module;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("根据code查询module时出错",e);
		}
	}
	
	/**
	 * 更新module
	 * @param module
	 */
	public void updateModule(Module module){
		try {
			Module m = getModule(module.getCode());
			String code = module.getCode();
			//给module名字添加跟父节点长度一致的"-"前缀
			String prefix = code.substring(0, code.length() - AdminConstant.MODULE_CODE_LENGTH).replaceAll(".", "-");
			m.setName(prefix + module.getName());
			m.setUrl(module.getUrl());
			m.setRemark(module.getRemark());
			m.setModifyDate(new Date());
			m.setModifier(AdminConstant.getSessionUser());
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAException("更新module时出错",e);
		}
	}
	
	/**
	 * 批量删除module
	 * @param ids
	 */
	public void deleteModule(String[] codes){
		moduleDao.deleteModule(codes);
	}
}
