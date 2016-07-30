package com.wenjiaxi.oa.admin.identity.dao.impl;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.wenjiaxi.oa.admin.AdminConstant;
import com.wenjiaxi.oa.admin.identity.dao.ModuleDao;
import com.wenjiaxi.oa.admin.identity.entity.Module;
import com.wenjiaxi.oa.core.common.web.PageModel;
import com.wenjiaxi.oa.core.dao.impl.BaseDaoImpl;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:27:01
 * @version 1.0
 */
@Repository("moduleDao")
public class ModuleDaoImpl extends BaseDaoImpl implements ModuleDao {

	/**
	 * 获取所有模块的code和name
	 * @return 包含code和name的集合
	 */
	public List<Map<String,Object>> getModulesByCodeAndName(){
		return this.find("select new map(code as id,name as name) from Module order by code");
	}
	
	/**
	 * 分页查询module
	 * @param parentCode 当前选中的节点的code
	 * @param module
	 * @param pageModel
	 */
	public List<Module> getModuleByPage(String parentCode, PageModel pageModel, int codeLength){
		//如果code为空或者等于根节点0，则当做空字符串
		if(StringUtils.isEmpty(parentCode) || parentCode.equals("0")){
			parentCode = "";
		}
		List<Object> params = new ArrayList<Object>();
		//要查找的节点长度等于选中节点的长度加每一个级别code的长度
		codeLength = parentCode.length() + codeLength;
		params.add(codeLength);
		//查询以选中节点开头的节点
		params.add(parentCode + "%");
		return this.findByPage("from Module where length(code) = ? and code like ? order by code", pageModel, params);
	}
	
	/**
	 * 批量删除module
	 * @param codes
	 */
	public void deleteModule(String[] codes){
		List<Object> params = new ArrayList<Object>();
		for (int i = 0; i < codes.length; i++) {
			String code = codes[i];
			params.add(code + "%");
			this.bulkUpdate("delete from Module where code like ? ", params.toArray());
		}
	}
	
	/**
	 * 查询指定module下的所有操作(12位module)
	 * @param moduleCode
	 * @param codeLength
	 * @return
	 */
	public List<Module> getOps(String moduleCode, int codeLength){
		
		List<Object> params = new ArrayList<Object>();
		params.add(codeLength + moduleCode.length());
		params.add(moduleCode + "%");
		return this.find("select m from Module m where length(m.code) = ? and m.code like ? order by m.code asc", params.toArray());
	}
	
	/**
	 * 根据code长度查询module，获取小于指定长度的module
	 * @param codeLength
	 * @return [{id: ,name: },{},{}]
	 */
	public List<Map<String, Object>> getPopedomByCodeLength(int codeLength){
		String hql = "select new map(m.code as id, m.name as name) from Module m where length(m.code) <= ? order by m.code asc";
		return this.find(hql, new Object[]{codeLength});
	}

}
