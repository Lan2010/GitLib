package com.tianzhixing.oms.bussiness.rpc.mapper.statistics;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/7/4.
 */
public class UserBasicStatisticsMapper implements Serializable {

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
     * 用户数
     */
    private Integer userCount;

    /**
     * 用户渠道
     */
    private String userFromType;

    /**
     * 用户渠道名称
     */
    private String userFromTypeName;

    /**
     * 平台名称
     */
    private String platformName;

    public UserBasicStatisticsMapper() {
    }

    public UserBasicStatisticsMapper(Long id, Integer version, Date createTime, Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, Integer userCount, String userFromType, String userFromTypeName, String platformName) {
        this.id = id;
        this.version = version;
        this.createTime = createTime;
        this.statisticsHour = statisticsHour;
        this.statisticsDay = statisticsDay;
        this.statisticsMonth = statisticsMonth;
        this.statisticsYear = statisticsYear;
        this.clientPlatformType = clientPlatformType;
        this.platformFrom = platformFrom;
        this.userCount = userCount;
        this.userFromType = userFromType;
        this.userFromTypeName = userFromTypeName;
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

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public String getUserFromType() {
        return userFromType;
    }

    public void setUserFromType(String userFromType) {
        this.userFromType = userFromType;
    }

    public String getUserFromTypeName() {
        return userFromTypeName;
    }

    public void setUserFromTypeName(String userFromTypeName) {
        this.userFromTypeName = userFromTypeName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
}
