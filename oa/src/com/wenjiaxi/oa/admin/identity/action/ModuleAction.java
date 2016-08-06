package com.wenjiaxi.oa.admin.identity.action;

import java.util.List;

import org.springframework.util.StringUtils;

import com.wenjiaxi.oa.admin.identity.entity.Module;
import com.wenjiaxi.oa.core.common.web.PageModel;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月20日 上午12:19:29
 * @version 1.0
 */

public class ModuleAction extends IdentityAction {


	private static final long serialVersionUID = -6303505451863805413L;
	private Module module;
	private List<Module> modules;
	private String parentCode;
	private String codes;
	

	/**
	 * 分页查询module
	 * @return 
	 */
	public String selectModule(){
		
		try {
			if (pageModel == null) {
				pageModel = new PageModel();
			}
			modules = identityService.getModuleByPage(parentCode, pageModel);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加module
	 * @return
	 */
	public String addModule(){
		try {
			//将parentCode封装到module对象中传输给service层
			module.setCode(parentCode);
			identityService.addModule(module);
			setMsg("success");
		} catch (Exception e) {
			e.printStackTrace();
			setMsg("failed");
		}
		return SUCCESS;
	}

	/**
	 * 显示更新module页面
	 * @return
	 */
	public String showUpdateModule(){
		try {
			module = identityService.getModule(module.getCode());
			//将module名字中的---去掉再显示给用户
			module.setName(module.getName().replace("-", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 更新module
	 * @return
	 */
	public String updateModule(){
		try {
			identityService.updateModule(module);
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 批量删除用户
	 * @return
	 */
	public String deleteModule(){
		try {
			identityService.deleteModule(codes.split(","));
			setMsg("success");
		} catch (Exception e) {
			setMsg("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	// getter setter
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

	
		
}
