package com.tianzhixing.oms.bussiness.model.statistics;

import com.tianzhixing.oms.bussiness.model.annotation.Column;
import com.tianzhixing.oms.bussiness.model.annotation.PrimaryKey;
import com.tianzhixing.oms.bussiness.model.annotation.Table;
import com.tianzhixing.oms.commons.em.ClientPlatformType;

import java.io.Serializable;
import java.util.Date;

/**
 * 程序操作统计（启动-关闭）
 * Created by routine.k on 2018/6/20.
 */
@Table(name = "application_operation_statistics")
public class ApplicationOperationStatisticsModel implements Serializable {

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
     * 平台名称
     */
    @Column(name = "platform_name")
    private String platformName;

    /**
     * APP启动数
     */
    @Column(name = "app_start_total_count")
    private Integer appStartTotalCount;

    /**
     * APP关闭数
     */
    @Column(name = "app_down_total_count")
    private Integer appDownTotalCount;

    /**
     * APP启动数(IP独立)
     */
    @Column(name = "app_start_diffip_total_count")
    private Integer appStartDiffIPTotalCount;

    /**
     * APP关闭数(IP独立)
     */
    @Column(name = "app_down_diffip_total_count")
    private Integer appDownDiffIPTotalCount;

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
