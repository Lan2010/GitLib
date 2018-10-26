package com.tianzhixing.oms.bussiness.model.statistics;

import com.tianzhixing.oms.bussiness.model.annotation.Column;
import com.tianzhixing.oms.bussiness.model.annotation.PrimaryKey;
import com.tianzhixing.oms.bussiness.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录登出统计
 * Created by routine.k on 2018/7/4.
 */
@Table(name = "user_login_status_statistics")
public class UserLoginStatusStatisticsModel implements Serializable {

    /**
     * id
     */
    @PrimaryKey
    @Column(name = "id")
    private Long id;

    /**
     * 版本号
     */
    @Column(name = "version")
    private Integer version;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 统计时间-小时
     */
    @Column(name = "statistics_hour")
    private Integer statisticsHour;

    /**
     * 统计时间-天
     */
    @Column(name = "statistics_day")
    private Integer statisticsDay;

    /**
     * 统计时间-月
     */
    @Column(name = "statistics_month")
    private Integer statisticsMonth;

    /**
     * 统计时间-年
     */
    @Column(name = "statistics_year")
    private Integer statisticsYear;

    /**
     * 客户端类型
     */
    @Column(name = "client_platform_type")
    private String clientPlatformType;

    /**
     * 请求平台来源
     */
    @Column(name = "platform_from")
    private String platformFrom;

    /**
     * 用户登录数
     */
    @Column(name = "user_login_count")
    private Integer userLoginCount;

    /**
     * 用户登录数（独立账号）
     */
    @Column(name = "user_login_diff_account_count")
    private Integer userLoginDiffAccountCount;

    /**
     * 用户登数
     */
    @Column(name = "user_logout_count")
    private Integer userLogoutCount;

    /**
     * 用户登出数（独立账号）
     */
    @Column(name = "user_logout_diff_account_count")
    private Integer userLogoutDiffAccountCount;

    /**
     * 用户在线数
     */
    @Column(name = "user_online_count")
    private Integer userOnlineCount;

    /**
     * 平台名称
     */
    @Column(name = "platform_name")
    private String platformName;

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
