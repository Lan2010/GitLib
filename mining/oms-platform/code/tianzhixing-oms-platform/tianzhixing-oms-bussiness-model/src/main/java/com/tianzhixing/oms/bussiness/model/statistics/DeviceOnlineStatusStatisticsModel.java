package com.tianzhixing.oms.bussiness.model.statistics;

import com.tianzhixing.oms.bussiness.model.annotation.Column;
import com.tianzhixing.oms.bussiness.model.annotation.PrimaryKey;
import com.tianzhixing.oms.bussiness.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备上线/下线统计
 * Created by routine.k on 2018/6/20.
 */
@Table(name = "device_online_status_statistics")
public class DeviceOnlineStatusStatisticsModel implements Serializable {

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
     * 设备类型
     */
    @Column(name = "device_type")
    private String deviceType;

    /**
     * 已绑定设备上线数
     */
    @Column(name = "bind_device_online_count")
    private Integer bindDeviceOnlineCount;

    /**
     * 已绑定设备上线数（独立device）
     */
    @Column(name = "bind_device_diffid_online_count")
    private Integer bindDeviceDiffIDOnlineCount;

    /**
     * 未绑定设备上线数
     */
    @Column(name = "unbind_device_online_count")
    private Integer unBindDeviceOnlineCount;

    /**
     * 未绑定设备上线数（独立device）
     */
    @Column(name = "unbind_device_diffid_online_count")
    private Integer unBindDeviceDiffIDOnlineCount;

    /**
     * 已绑定设备下线数
     */
    @Column(name = "bind_device_offline_count")
    private Integer bindDeviceOfflineCount;

    /**
     * 已绑定设备下线数（独立device）
     */
    @Column(name = "bind_device_diffid_offline_count")
    private Integer bindDeviceDiffIDOfflineCount;

    /**
     * 未绑定设备下线数
     */
    @Column(name = "unbind_device_offline_count")
    private Integer unBindDeviceOfflineCount;

    /**
     * 未绑定设备下线数（独立device）
     */
    @Column(name = "unbind_device_diffid_offline_count")
    private Integer unBindDeviceDiffIDOfflineCount;

    /**
     * 当前未绑定设备在线数（独立device）
     */
    @Column(name = "unbind_device_current_online_count")
    private Integer unBindDeviceCurrentOnlineCount;

    /**
     * 当前已绑定设备在线数（独立device）
     */
    @Column(name = "bind_device_current_online_count")
    private Integer bindDeviceCurrentOnlineCount;

    /**
     * 设备类型名称
     */
    @Column(name = "device_type_name")
    private String deviceTypeName;

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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getBindDeviceOnlineCount() {
        return bindDeviceOnlineCount;
    }

    public void setBindDeviceOnlineCount(Integer bindDeviceOnlineCount) {
        this.bindDeviceOnlineCount = bindDeviceOnlineCount;
    }

    public Integer getBindDeviceDiffIDOnlineCount() {
        return bindDeviceDiffIDOnlineCount;
    }

    public void setBindDeviceDiffIDOnlineCount(Integer bindDeviceDiffIDOnlineCount) {
        this.bindDeviceDiffIDOnlineCount = bindDeviceDiffIDOnlineCount;
    }

    public Integer getUnBindDeviceOnlineCount() {
        return unBindDeviceOnlineCount;
    }

    public void setUnBindDeviceOnlineCount(Integer unBindDeviceOnlineCount) {
        this.unBindDeviceOnlineCount = unBindDeviceOnlineCount;
    }

    public Integer getUnBindDeviceDiffIDOnlineCount() {
        return unBindDeviceDiffIDOnlineCount;
    }

    public void setUnBindDeviceDiffIDOnlineCount(Integer unBindDeviceDiffIDOnlineCount) {
        this.unBindDeviceDiffIDOnlineCount = unBindDeviceDiffIDOnlineCount;
    }

    public Integer getBindDeviceOfflineCount() {
        return bindDeviceOfflineCount;
    }

    public void setBindDeviceOfflineCount(Integer bindDeviceOfflineCount) {
        this.bindDeviceOfflineCount = bindDeviceOfflineCount;
    }

    public Integer getBindDeviceDiffIDOfflineCount() {
        return bindDeviceDiffIDOfflineCount;
    }

    public void setBindDeviceDiffIDOfflineCount(Integer bindDeviceDiffIDOfflineCount) {
        this.bindDeviceDiffIDOfflineCount = bindDeviceDiffIDOfflineCount;
    }

    public Integer getUnBindDeviceOfflineCount() {
        return unBindDeviceOfflineCount;
    }

    public void setUnBindDeviceOfflineCount(Integer unBindDeviceOfflineCount) {
        this.unBindDeviceOfflineCount = unBindDeviceOfflineCount;
    }

    public Integer getUnBindDeviceDiffIDOfflineCount() {
        return unBindDeviceDiffIDOfflineCount;
    }

    public void setUnBindDeviceDiffIDOfflineCount(Integer unBindDeviceDiffIDOfflineCount) {
        this.unBindDeviceDiffIDOfflineCount = unBindDeviceDiffIDOfflineCount;
    }

    public Integer getUnBindDeviceCurrentOnlineCount() {
        return unBindDeviceCurrentOnlineCount;
    }

    public void setUnBindDeviceCurrentOnlineCount(Integer unBindDeviceCurrentOnlineCount) {
        this.unBindDeviceCurrentOnlineCount = unBindDeviceCurrentOnlineCount;
    }

    public Integer getBindDeviceCurrentOnlineCount() {
        return bindDeviceCurrentOnlineCount;
    }

    public void setBindDeviceCurrentOnlineCount(Integer bindDeviceCurrentOnlineCount) {
        this.bindDeviceCurrentOnlineCount = bindDeviceCurrentOnlineCount;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
}
