package com.kgc.util;

public class Page {
	// 总页数
	private int totalPageCount = 0;
	// 页面大小，每页显示条数
	private int pageSize = 5;
	// 总记录条数
	private int totalCount;
	// 当前页码
	private int currPageNo = 1;

	public int getTotalPageCount() {
		return totalPageCount;
	}

	/**
	 * 设置总页数
	 * 
	 * @param totalPageCount
	 */
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页显示条数
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总条数，从数据库查询出的总条数直接设置进来
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
		}
		// 计算总页数
		totalPageCount = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize
				: (this.totalCount / this.pageSize) + 1;
	}

	public int getCurrPageNo() {
		return currPageNo;
	}

	/**
	 * 设置当前页码
	 * 
	 * @param currPageNo
	 */
	public void setCurrPageNo(int currPageNo) {
		if (currPageNo > 0) {
			this.currPageNo = currPageNo;
		}

	}

}
