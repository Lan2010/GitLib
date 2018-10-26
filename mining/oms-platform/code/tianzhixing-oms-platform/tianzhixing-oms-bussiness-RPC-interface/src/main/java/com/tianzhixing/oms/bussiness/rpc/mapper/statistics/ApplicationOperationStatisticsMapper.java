package com.tianzhixing.oms.bussiness.rpc.mapper.statistics;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/7/5.
 */
public class ApplicationOperationStatisticsMapper implements Serializable {
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
     * 平台名称
     */
    private String platformName;

    /**
     * 请求平台来源
     */
    private String platformFrom;

    /**
     * APP启动数
     */
    private Integer appStartTotalCount;

    /**
     * APP关闭数
     */
    private Integer appDownTotalCount;

    /**
     * APP启动数(IP独立)
     */
    private Integer appStartDiffIPTotalCount;

    /**
     * APP关闭数(IP独立)
     */
    private Integer appDownDiffIPTotalCount;

    public ApplicationOperationStatisticsMapper() {
    }

    public ApplicationOperationStatisticsMapper(Long id, Integer version, Date createTime, Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformName, String platformFrom, Integer appStartTotalCount, Integer appDownTotalCount, Integer appStartDiffIPTotalCount, Integer appDownDiffIPTotalCount) {
        this.id = id;
        this.version = version;
        this.createTime = createTime;
        this.statisticsHour = statisticsHour;
        this.statisticsDay = statisticsDay;
        this.statisticsMonth = statisticsMonth;
        this.statisticsYear = statisticsYear;
        this.clientPlatformType = clientPlatformType;
        this.platformName = platformName;
        this.platformFrom = platformFrom;
        this.appStartTotalCount = appStartTotalCount;
        this.appDownTotalCount = appDownTotalCount;
        this.appStartDiffIPTotalCount = appStartDiffIPTotalCount;
        this.appDownDiffIPTotalCount = appDownDiffIPTotalCount;
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

    public Integer getAppStartTotalCount() {
        return appStartTotalCount;
    }

    public void setAppStartTotalCount(Integer appStartTotalCount) {
        this.appStartTotalCount = appStartTotalCount;
    }

    public Integer getAppDownTotalCount() {
        return appDownTotalCount;
    }

    public void setAppDownTotalCount(Integer appDownTotalCount) {
        this.appDownTotalCount = appDownTotalCount;
    }

    public Integer getAppStartDiffIPTotalCount() {
        return appStartDiffIPTotalCount;
    }

    public void setAppStartDiffIPTotalCount(Integer appStartDiffIPTotalCount) {
        this.appStartDiffIPTotalCount = appStartDiffIPTotalCount;
    }

    public Integer getAppDownDiffIPTotalCount() {
        return appDownDiffIPTotalCount;
    }

    public void setAppDownDiffIPTotalCount(Integer appDownDiffIPTotalCount) {
        this.appDownDiffIPTotalCount = appDownDiffIPTotalCount;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
}
