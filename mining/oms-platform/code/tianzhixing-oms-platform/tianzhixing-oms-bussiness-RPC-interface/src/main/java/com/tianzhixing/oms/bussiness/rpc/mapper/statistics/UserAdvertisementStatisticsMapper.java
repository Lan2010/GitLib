package com.tianzhixing.oms.bussiness.rpc.mapper.statistics;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户访问/点击广告统计
 * Created by routine.k on 2018/7/4.
 */
public class UserAdvertisementStatisticsMapper implements Serializable {

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
     * 广告ID
     */
    private String advettisementId;

    /**
     * 广告名字
     */
    private String advettisementName;

    /**
     * 访问量
     */
    private Integer accessCount;

    /**
     * 访问量(独立IP)
     */
    private Integer accessDiffIPCount;

    /**
     * 点击量
     */
    private Integer clickCount;

    /**
     * 点击量(独立IP)
     */
    private Integer clickDiffIPCount;

    /**
     * 平台名称
     */
    private String platformName;

    public UserAdvertisementStatisticsMapper() {
    }

    public UserAdvertisementStatisticsMapper(Long id, Integer version, Date createTime, Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String advettisementId, String advettisementName, Integer accessCount, Integer accessDiffIPCount, Integer clickCount, Integer clickDiffIPCount, String platformName) {
        this.id = id;
        this.version = version;
        this.createTime = createTime;
        this.statisticsHour = statisticsHour;
        this.statisticsDay = statisticsDay;
        this.statisticsMonth = statisticsMonth;
        this.statisticsYear = statisticsYear;
        this.clientPlatformType = clientPlatformType;
        this.platformFrom = platformFrom;
        this.advettisementId = advettisementId;
        this.advettisementName = advettisementName;
        this.accessCount = accessCount;
        this.accessDiffIPCount = accessDiffIPCount;
        this.clickCount = clickCount;
        this.clickDiffIPCount = clickDiffIPCount;
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

    public String getAdvettisementId() {
        return advettisementId;
    }

    public void setAdvettisementId(String advettisementId) {
        this.advettisementId = advettisementId;
    }

    public String getAdvettisementName() {
        return advettisementName;
    }

    public void setAdvettisementName(String advettisementName) {
        this.advettisementName = advettisementName;
    }

    public Integer getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Integer accessCount) {
        this.accessCount = accessCount;
    }

    public Integer getAccessDiffIPCount() {
        return accessDiffIPCount;
    }

    public void setAccessDiffIPCount(Integer accessDiffIPCount) {
        this.accessDiffIPCount = accessDiffIPCount;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Integer getClickDiffIPCount() {
        return clickDiffIPCount;
    }

    public void setClickDiffIPCount(Integer clickDiffIPCount) {
        this.clickDiffIPCount = clickDiffIPCount;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
}
