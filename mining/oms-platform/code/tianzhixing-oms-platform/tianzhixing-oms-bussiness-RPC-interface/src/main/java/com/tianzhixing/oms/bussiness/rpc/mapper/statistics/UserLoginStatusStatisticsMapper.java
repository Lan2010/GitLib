package com.tianzhixing.oms.bussiness.rpc.mapper.statistics;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录登出统计
 * Created by routine.k on 2018/7/4.
 */
public class UserLoginStatusStatisticsMapper implements Serializable {

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
     * 用户登录数
     */
    private Integer userLoginCount;

    /**
     * 用户登录数（独立账号）
     */
    private Integer userLoginDiffAccountCount;

    /**
     * 用户登出数
     */
    private Integer userLogoutCount;

    /**
     * 用户登出数（独立账号）
     */
    private Integer userLogoutDiffAccountCount;

    /**
     * 用户在线数
     */
    private Integer userOnlineCount;

    /**
     * 平台名称
     */
    private String platformName;
    
    

    public UserLoginStatusStatisticsMapper() {
	}

	public UserLoginStatusStatisticsMapper(Long id, Integer version, Date createTime, Integer statisticsHour,
			Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType,
			String platformFrom, Integer userLoginCount, Integer userLoginDiffAccountCount, Integer userLogoutCount,
			Integer userLogoutDiffAccountCount, Integer userOnlineCount, String platformName) {
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
		this.userLoginCount = userLoginCount;
		this.userLoginDiffAccountCount = userLoginDiffAccountCount;
		this.userLogoutCount = userLogoutCount;
		this.userLogoutDiffAccountCount = userLogoutDiffAccountCount;
		this.userOnlineCount = userOnlineCount;
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

    public Integer getUserLoginCount() {
        return userLoginCount;
    }

    public void setUserLoginCount(Integer userLoginCount) {
        this.userLoginCount = userLoginCount;
    }

    public Integer getUserLoginDiffAccountCount() {
        return userLoginDiffAccountCount;
    }

    public void setUserLoginDiffAccountCount(Integer userLoginDiffAccountCount) {
        this.userLoginDiffAccountCount = userLoginDiffAccountCount;
    }

    public Integer getUserLogoutCount() {
        return userLogoutCount;
    }

    public void setUserLogoutCount(Integer userLogoutCount) {
        this.userLogoutCount = userLogoutCount;
    }

    public Integer getUserLogoutDiffAccountCount() {
        return userLogoutDiffAccountCount;
    }

    public void setUserLogoutDiffAccountCount(Integer userLogoutDiffAccountCount) {
        this.userLogoutDiffAccountCount = userLogoutDiffAccountCount;
    }

    public Integer getUserOnlineCount() {
        return userOnlineCount;
    }

    public void setUserOnlineCount(Integer userOnlineCount) {
        this.userOnlineCount = userOnlineCount;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
}
