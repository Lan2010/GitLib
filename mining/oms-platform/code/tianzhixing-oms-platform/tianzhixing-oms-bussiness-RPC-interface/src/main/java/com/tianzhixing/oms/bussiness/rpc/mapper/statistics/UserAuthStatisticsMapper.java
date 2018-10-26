package com.tianzhixing.oms.bussiness.rpc.mapper.statistics;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户认证统计
 * Created by routine.k on 2018/7/4.
 */
public class UserAuthStatisticsMapper implements Serializable {

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
     * 认证失败数量
     */
    private Integer failedAuthCount;

    /**
     * 成功认证的数量
     */
    private Integer sucAuthCount;

    /**
     * 认证总数量
     */
    private Integer authCount;

    /**
     * 认证类型名称
     */
    private String authTypeName;

    /**
     * 认证类型
     */
    private String authType;

    /**
     * 平台名称
     */
    private String platformName;

    public UserAuthStatisticsMapper() {
    }

    public UserAuthStatisticsMapper(Long id, Integer version, Date createTime, Integer statisticsHour,
			Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType,
			String platformFrom, Integer failedAuthCount, Integer sucAuthCount, Integer authCount, String authTypeName,
			String authType, String platformName) {
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
		this.failedAuthCount = failedAuthCount;
		this.sucAuthCount = sucAuthCount;
		this.authCount = authCount;
		this.authTypeName = authTypeName;
		this.authType = authType;
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

    public Integer getFailedAuthCount() {
        return failedAuthCount;
    }

    public void setFailedAuthCount(Integer failedAuthCount) {
        this.failedAuthCount = failedAuthCount;
    }

    public Integer getSucAuthCount() {
        return sucAuthCount;
    }

    public void setSucAuthCount(Integer sucAuthCount) {
        this.sucAuthCount = sucAuthCount;
    }

    public Integer getAuthCount() {
        return authCount;
    }

    public void setAuthCount(Integer authCount) {
        this.authCount = authCount;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getAuthTypeName() {
        return authTypeName;
    }

    public void setAuthTypeName(String authTypeName) {
        this.authTypeName = authTypeName;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }
}
