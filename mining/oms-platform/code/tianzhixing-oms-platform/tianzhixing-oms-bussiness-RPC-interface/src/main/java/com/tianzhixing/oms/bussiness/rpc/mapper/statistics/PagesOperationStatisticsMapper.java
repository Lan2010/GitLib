package com.tianzhixing.oms.bussiness.rpc.mapper.statistics;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面操作统计
 * Created by routine.k on 2018/7/4.
 */
public class PagesOperationStatisticsMapper implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 统计时间-小时
     */
    private Integer statisticsHour;

    /**
     * 统计时间-天
     */
    private Integer statisticsDay;

    /**
     * 统计时间-月
     */
    private Integer statisticsMonth;

    /**
     * 统计时间-年
     */
    private Integer statisticsYear;

    /**
     * 客户端类型
     */
    private String clientPlatformType;

    /**
     * 请求平台来源
     */
    private String platformFrom;

    /**
     * 页面地址
     */
    private String pagesURL;

    /**
     * 操作页面数
     */
    private Integer pagesOperationCount;

    /**
     * 操作页面数（独立IP）
     */
    private Integer pagesOperationDiffIPCount;

    /**
     * 页面名称
     */
    private String pageName;

    /**
     * 平台名称
     */
    private String platformName;

    public PagesOperationStatisticsMapper() {
    }

    public PagesOperationStatisticsMapper(Long id, Integer version, Date createTime, Integer statisticsHour,
			Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType,
			String platformFrom, String pagesURL, Integer pagesOperationCount, Integer pagesOperationDiffIPCount,
			String pageName, String platformName) {
		super();
		this.id = id;
		this.version = version;
		this.createTime = createTime;
		this.statisticsHour = statisticsHour;
		this.statisticsDay = statisticsDay;
		this.statisticsMonth = statisticsMonth;
		this.statisticsYear = statisticsYear;
		this.clientPlatformType = clientPlatformType;
		this.platformFrom = platformFrom;
		this.pagesURL = pagesURL;
		this.pagesOperationCount = pagesOperationCount;
		this.pagesOperationDiffIPCount = pagesOperationDiffIPCount;
		this.pageName = pageName;
		this.platformName = platformName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatisticsHour() {
        return statisticsHour;
    }

    public void setStatisticsHour(Integer statisticsHour) {
        this.statisticsHour = statisticsHour;
    }

    public Integer getStatisticsDay() {
        return statisticsDay;
    }

    public void setStatisticsDay(Integer statisticsDay) {
        this.statisticsDay = statisticsDay;
    }

    public Integer getStatisticsMonth() {
        return statisticsMonth;
    }

    public void setStatisticsMonth(Integer statisticsMonth) {
        this.statisticsMonth = statisticsMonth;
    }

    public Integer getStatisticsYear() {
        return statisticsYear;
    }

    public void setStatisticsYear(Integer statisticsYear) {
        this.statisticsYear = statisticsYear;
    }

    public String getClientPlatformType() {
        return clientPlatformType;
    }

    public void setClientPlatformType(String clientPlatformType) {
        this.clientPlatformType = clientPlatformType;
    }

    public String getPlatformFrom() {
        return platformFrom;
    }

    public void setPlatformFrom(String platformFrom) {
        this.platformFrom = platformFrom;
    }

    public String getPagesURL() {
        return pagesURL;
    }

    public void setPagesURL(String pagesURL) {
        this.pagesURL = pagesURL;
    }

    public Integer getPagesOperationCount() {
        return pagesOperationCount;
    }

    public void setPagesOperationCount(Integer pagesOperationCount) {
        this.pagesOperationCount = pagesOperationCount;
    }

    public Integer getPagesOperationDiffIPCount() {
        return pagesOperationDiffIPCount;
    }

    public void setPagesOperationDiffIPCount(Integer pagesOperationDiffIPCount) {
        this.pagesOperationDiffIPCount = pagesOperationDiffIPCount;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
}
