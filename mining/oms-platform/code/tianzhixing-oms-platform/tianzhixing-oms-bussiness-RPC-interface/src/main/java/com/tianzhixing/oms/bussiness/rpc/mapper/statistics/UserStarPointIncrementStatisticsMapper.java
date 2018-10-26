package com.tianzhixing.oms.bussiness.rpc.mapper.statistics;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户星点增长统计
 * Created by routine.k on 2018/7/4.
 */
public class UserStarPointIncrementStatisticsMapper implements Serializable {

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
     * 增长数量
     */
    private Double incrementCount;

    /**
     * 增长原因
     */
    private String incrementCause;

    /**
     * 增长类型(0=掉落，1=任务，2=广告)
     */
    private Integer incrementType;

    /**
     * 消费原因名称
     */
    private String incrementCauseName;

    /**
     * 平台名称
     */
    private String platformName;

    public UserStarPointIncrementStatisticsMapper(Long id, Integer version, Date createTime, Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, Double incrementCount, String incrementCause, Integer incrementType, String incrementCauseName, String platformName) {
		this.id = id;
		this.version = version;
		this.createTime = createTime;
		this.statisticsHour = statisticsHour;
		this.statisticsDay = statisticsDay;
		this.statisticsMonth = statisticsMonth;
		this.statisticsYear = statisticsYear;
		this.clientPlatformType = clientPlatformType;
		this.platformFrom = platformFrom;
		this.incrementCount = incrementCount;
		this.incrementCause = incrementCause;
		this.incrementType = incrementType;
		this.incrementCauseName = incrementCauseName;
		this.platformName = platformName;
	}
    
    public UserStarPointIncrementStatisticsMapper() {}

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

    public Double getIncrementCount() {
        return incrementCount;
    }

    public void setIncrementCount(Double incrementCount) {
        this.incrementCount = incrementCount;
    }

    public String getIncrementCause() {
        return incrementCause;
    }

    public void setIncrementCause(String incrementCause) {
        this.incrementCause = incrementCause;
    }

    public String getIncrementCauseName() {
        return incrementCauseName;
    }

    public void setIncrementCauseName(String incrementCauseName) {
        this.incrementCauseName = incrementCauseName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Integer getIncrementType() {
        return incrementType;
    }

    public void setIncrementType(Integer incrementType) {
        this.incrementType = incrementType;
    }
}
