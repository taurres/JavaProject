package com.wenjiaxi.oa.core.common.web;

/**
 * 分页实体类
 * @author WEN JIAXI
 * @date 2016年7月18日 下午7:48:40
 * @version 1.0
 */

public class PageModel {
	//每页记录数常量
	private static final int PAGE_SIZE = 3;
	// 当前页码 
	private int pageIndex;
	// 每页显示的记录条数
	private int pageSize;
	// 总记录条数 
	private int recordCount;
	
	
	public int getPageIndex() {
		//当前页码不能小于1
		this.pageIndex = pageIndex <= 0 ? 1 : pageIndex;
		//当前页面不能大于总页数
		int totalPage = (this.recordCount - 1) / this.getPageSize() + 1;
		return pageIndex > totalPage ? totalPage : pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize <= 0 ? PAGE_SIZE : pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	//返回limit的第一个参数
	public int getStartRow(){
		return (getPageIndex() - 1) * getPageSize();
	}
}
