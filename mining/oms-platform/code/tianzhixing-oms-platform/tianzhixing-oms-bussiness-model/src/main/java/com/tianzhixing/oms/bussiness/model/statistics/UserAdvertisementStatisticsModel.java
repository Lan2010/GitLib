package com.tianzhixing.oms.bussiness.model.statistics;

import com.tianzhixing.oms.bussiness.model.annotation.Column;
import com.tianzhixing.oms.bussiness.model.annotation.PrimaryKey;
import com.tianzhixing.oms.bussiness.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户访问/点击广告统计
 * Created by routine.k on 2018/7/4.
 */
@Table(name = "user_advertisement_statistics")
public class UserAdvertisementStatisticsModel implements Serializable {

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
     * 广告ID
     */
    @Column(name = "advertisement_id")
    private String advettisementId;

    /**
     * 广告名字
     */
    @Column(name = "advertisement_name")
    private String advettisementName;

    /**
     * 访问量
     */
    @Column(name = "access_count")
    private Integer accessCount;

    /**
     * 访问量(独立IP)
     */
    @Column(name = "access_diffip_count")
    private Integer accessDiffIPCount;

    /**
     * 点击量
     */
    @Column(name = "click_count")
    private Integer clickCount;

    /**
     * 点击量(独立IP)
     */
    @Column(name = "click_diffip_count")
    private Integer clickDiffIPCount;

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
