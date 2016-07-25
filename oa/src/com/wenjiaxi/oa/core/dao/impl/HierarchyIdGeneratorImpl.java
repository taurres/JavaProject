package com.wenjiaxi.oa.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.wenjiaxi.oa.core.dao.HierarchyIdGenerator;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月25日 下午10:58:12
 * @version 1.0
 */


public class HierarchyIdGeneratorImpl extends BaseDaoImpl implements HierarchyIdGenerator {

	/**
	 * 层级ID生成器
	 * @param clazz 持久化类
	 * @param field 主键字段名
	 * @param parentCode 父节点:"0001"
	 * @param codeLength 每个层级code的长度
	 * @return id格式 0001---00010001,00010002;
	 */
	public <T> String generateId(Class<T> clazz, String field, String parentCode, int codeLength){
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		//生成的临时id值
		String curCode = "";
		//判断parentCode是否为空，是则返回空字符串
		parentCode = StringUtils.isEmpty(parentCode) ? "" : parentCode;
		hql.append("select max(" + field + ") from " + clazz.getSimpleName() + " where length("+ field +") = ?");
		params.add(parentCode.length() + codeLength);
		hql.append(" and " + field + " like ?");
		params.add(parentCode + "%");
		//找到当前层级下最大的code
		String maxCode = this.findUniqueEntity(hql.toString(), params.toArray());
		//判断maxcode是否为空,不为空说明该层级已经有id
		if (!StringUtils.isEmpty(maxCode)) {
			//maxCode截去父节点 00010002 --> 0002
			maxCode = maxCode.substring(maxCode.length() - codeLength);
			//将字符串编程整数+1再转换为字符串，结果为 0002 --> 3
			curCode = String.valueOf((Integer.valueOf(maxCode) + 1));
			//最终拼成 00010003
			//如果curCode位数超过预计的最大位数，则抛出异常
			if (curCode.length() > codeLength) {
				throw new RuntimeException("创建的Id位数超出最大值");
			}
			return parentCode + maxCode.substring(0, maxCode.length() - curCode.length()) + curCode;

		}else{
				//如果maxcode为空说明不存在id，新建id
				for (int i = 0; i < codeLength - 1; i++) {
					curCode += "0";
				}
				//最终拼成 [父节点]0001,或者父节点为空则为0001
				return parentCode + curCode + "1";
		}
	}

}
