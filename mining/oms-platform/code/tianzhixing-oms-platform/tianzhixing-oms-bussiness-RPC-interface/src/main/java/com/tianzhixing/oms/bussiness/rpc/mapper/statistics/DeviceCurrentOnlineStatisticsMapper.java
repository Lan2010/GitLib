package com.tianzhixing.oms.bussiness.rpc.mapper.statistics;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备当前在线清单
 * Created by routine.k on 2018/7/5.
 */
public class DeviceCurrentOnlineStatisticsMapper implements Serializable {

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
     * 更新时间
     */
    private Date updateTime;

    /**
     * 客户端类型
     */
    private String clientPlatformType;

    /**
     * 请求平台来源
     */
    private String platformFrom;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 状态(0=离线，1=在线)
     */
    private Integer status;

    /**
     * 经度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 设备类型
     */
    private String deviceType;

    public DeviceCurrentOnlineStatisticsMapper() {
    }

    public DeviceCurrentOnlineStatisticsMapper(Long id, Integer version, Date createTime, Date updateTime, String clientPlatformType, String platformFrom, String deviceId, Integer status, String lng, String lat, String deviceType) {
        this.id = id;
        this.version = version;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.clientPlatformType = clientPlatformType;
        this.platformFrom = platformFrom;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.status = status;
        this.lng = lng;
        this.lat = lat;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
