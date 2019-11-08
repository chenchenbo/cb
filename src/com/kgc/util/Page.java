package com.kgc.util;

public class Page {
	// ��ҳ��
	private int totalPageCount = 0;
	// ҳ���С��ÿҳ��ʾ����
	private int pageSize = 5;
	// �ܼ�¼����
	private int totalCount;
	// ��ǰҳ��
	private int currPageNo = 1;

	public int getTotalPageCount() {
		return totalPageCount;
	}

	/**
	 * ������ҳ��
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
	 * ����ÿҳ��ʾ����
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
	 * �����������������ݿ��ѯ����������ֱ�����ý���
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
		}
		// ������ҳ��
		totalPageCount = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize
				: (this.totalCount / this.pageSize) + 1;
	}

	public int getCurrPageNo() {
		return currPageNo;
	}

	/**
	 * ���õ�ǰҳ��
	 * 
	 * @param currPageNo
	 */
	public void setCurrPageNo(int currPageNo) {
		if (currPageNo > 0) {
			this.currPageNo = currPageNo;
		}

	}

}
