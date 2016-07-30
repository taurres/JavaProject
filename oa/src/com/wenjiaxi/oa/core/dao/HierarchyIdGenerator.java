package com.wenjiaxi.oa.core.dao;


/**
 * 层级ID生成器
 * @author WEN JIAXI
 * @date 2016年7月25日 下午10:55:51
 * @version 1.0
 */


public interface HierarchyIdGenerator extends BaseDao{

	/**
	 * 层级ID生成器
	 * @param clazz 持久化类
	 * @param field 主键字段名
	 * @param parentCode 父节点:"0001"
	 * @param codeLength 每个层级code的长度
	 * @return id格式 0001---00010001,00010002;
	 */
	public <T> String generateId(Class<T> clazz, String field, String parentCode, int codeLength);
}
