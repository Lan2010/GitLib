package com.tianzhixing.oms.bussiness.backend.web.mapping;

import java.io.Serializable;

/**
 * 分页组件
 * 
 * @author jinkai.xie
 * 
 * @date 下午8:51:49
 * 
 */
public class PagerMapping implements Serializable {

	private int page;

	private int pageSize;

	@SuppressWarnings("unused")
    private int from;

	public PagerMapping(int page, int pageSize) {
		setPage(page);
		setPageSize(pageSize);
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the from
	 */
	public int getFrom() {
		int index = (page - 1) * pageSize;
		return index < 0 ? 0 : index;
	}

}
