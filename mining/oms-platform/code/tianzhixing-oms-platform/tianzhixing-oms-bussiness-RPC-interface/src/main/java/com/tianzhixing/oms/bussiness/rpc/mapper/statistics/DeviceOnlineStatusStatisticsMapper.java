package com.tianzhixing.oms.bussiness.rpc.mapper.statistics;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备上线/下线统计
 * Created by routine.k on 2018/6/20.
 */
public class DeviceOnlineStatusStatisticsMapper implements Serializable {

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
     * 设备类型
     */
    private String deviceType;

    /**
     * 已绑定设备上线数
     */
    private Integer bindDeviceOnlineCount;

    /**
     * 已绑定设备上线数（独立device）
     */
    private Integer bindDeviceDiffIDOnlineCount;

    /**
     * 未绑定设备上线数
     */
    private Integer unBindDeviceOnlineCount;

    /**
     * 未绑定设备上线数（独立device）
     */
    private Integer unBindDeviceDiffIDOnlineCount;

    /**
     * 已绑定设备下线数
     */
    private Integer bindDeviceOfflineCount;

    /**
     * 已绑定设备下线数（独立device）
     */
    private Integer bindDeviceDiffIDOfflineCount;

    /**
     * 未绑定设备下线数
     */
    private Integer unBindDeviceOfflineCount;

    /**
     * 未绑定设备下线数（独立device）
     */
    private Integer unBindDeviceDiffIDOfflineCount;

    /**
     * 当前未绑定设备在线数（独立device）
     */
    private Integer unBindDeviceCurrentOnlineCount;

    /**
     * 当前已绑定设备在线数（独立device）
     */
    private Integer bindDeviceCurrentOnlineCount;

    /**
     * 平台名称
     */
    private String platformName;

    /**
     * 设备类型名称
     */
    private String deviceTypeName;

    public DeviceOnlineStatusStatisticsMapper() {
    }

    public DeviceOnlineStatusStatisticsMapper(Long id, Integer version, Date createTime, Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String deviceType, Integer bindDeviceOnlineCount, Integer bindDeviceDiffIDOnlineCount, Integer unBindDeviceOnlineCount, Integer unBindDeviceDiffIDOnlineCount, Integer bindDeviceOfflineCount, Integer bindDeviceDiffIDOfflineCount, Integer unBindDeviceOfflineCount, Integer unBindDeviceDiffIDOfflineCount, Integer unBindDeviceCurrentOnlineCount, Integer bindDeviceCurrentOnlineCount, String platformName, String deviceTypeName) {
		this.id = id;
		this.version = version;
		this.createTime = createTime;
		this.statisticsHour = statisticsHour;
		this.statisticsDay = statisticsDay;
		this.statisticsMonth = statisticsMonth;
		this.statisticsYear = statisticsYear;
		this.clientPlatformType = clientPlatformType;
		this.platformFrom = platformFrom;
		this.deviceType = deviceType;
		this.bindDeviceOnlineCount = bindDeviceOnlineCount;
		this.bindDeviceDiffIDOnlineCount = bindDeviceDiffIDOnlineCount;
		this.unBindDeviceOnlineCount = unBindDeviceOnlineCount;
		this.unBindDeviceDiffIDOnlineCount = unBindDeviceDiffIDOnlineCount;
		this.bindDeviceOfflineCount = bindDeviceOfflineCount;
		this.bindDeviceDiffIDOfflineCount = bindDeviceDiffIDOfflineCount;
		this.unBindDeviceOfflineCount = unBindDeviceOfflineCount;
		this.unBindDeviceDiffIDOfflineCount = unBindDeviceDiffIDOfflineCount;
		this.unBindDeviceCurrentOnlineCount = unBindDeviceCurrentOnlineCount;
		this.bindDeviceCurrentOnlineCount = bindDeviceCurrentOnlineCount;
		this.platformName = platformName;
		this.deviceTypeName = deviceTypeName;
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

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }
}
