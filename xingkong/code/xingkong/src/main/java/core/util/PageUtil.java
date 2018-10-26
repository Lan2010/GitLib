package core.util;

public class PageUtil {
	private Integer page;//当前页数 
	private Integer pageSize;//每页显示的数据行数
	private Integer start;//SQL查询起始页数
	private static final int DEFAULT_PAGESIZE = 20;
	
	public PageUtil() {
		//空构造函数
	}
	/**
	 * 构造器
	 * @param page  当前页
	 * @param pageSize  每页显示的行数
	 */
	public PageUtil(String page, String pageSize) {
		if(page==null || page.isEmpty() || Integer.valueOf(page)<=0) {
			page="1";
		}
		if(pageSize==null||pageSize.isEmpty()|| Integer.valueOf(pageSize)<=0){
			pageSize=String.valueOf(DEFAULT_PAGESIZE);
		}
		this.page = Integer.valueOf(page);
		this.pageSize = Integer.valueOf(pageSize);
	}
	
	public PageUtil(Integer page, Integer pageSize) {
		if(page==null || page<=0) {
			page=1;
		}
		if(pageSize==null|| pageSize<=0){
			pageSize=DEFAULT_PAGESIZE;
		}
		this.page = page;
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		start = (getCurrentPage() - 1) * getPageSize();
		return start;
	}

	public Integer getCurrentPage() {
		if (page == null || page < 1) {
			page = 1;
		}
		return page;
	}

	public void setCurrentPage(Integer currentPage) {
		this.page = currentPage;
	}

	public Integer getPageSize() {
		if(pageSize==null) {
			pageSize=DEFAULT_PAGESIZE;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setStart(Integer start) {
		this.start = start;
	}
}
