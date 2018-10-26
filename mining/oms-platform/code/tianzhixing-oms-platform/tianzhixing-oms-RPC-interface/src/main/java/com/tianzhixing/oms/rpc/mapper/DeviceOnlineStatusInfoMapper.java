package com.tianzhixing.oms.rpc.mapper;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备上线/下线信息表
 * Created by routine.k on 2018/6/12.
 */
public class DeviceOnlineStatusInfoMapper implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 请求平台来源
     */
    private String platformFrom;

    /**
     * 客户端类型
     */
    private String clientPlatformType;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 设备mac地址
     */
    private String deviceMac;

    /**
     * 设备ip地址
     */
    private String deviceIp;

    /**
     * 设备型号
     */
    private String deviceModel;

    /**
     * 设备操作类型
     */
    private String deviceOperType;

    /**
     * 上线时间
     */
    private Long operationTime;

    /**
     * 操作类型(0=下线，1=上线)
     */
    private Integer operationType;

    /**
     * 经度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 是否已绑定(0=未绑定，1=已绑定)
     */
    private Integer bindStatus;

    public DeviceOnlineStatusInfoMapper() {
    }

    public DeviceOnlineStatusInfoMapper(String id, Date createTime, String platformFrom, String clientPlatformType, String mobile, String deviceId, String deviceType, String deviceMac, String deviceIp, String deviceModel, String deviceOperType, Long operationTime, Integer operationType, String lng, String lat, Integer bindStatus) {
        this.id = id;
        this.createTime = createTime;
        this.platformFrom = platformFrom;
        this.clientPlatformType = clientPlatformType;
        this.mobile = mobile;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.deviceMac = deviceMac;
        this.deviceIp = deviceIp;
        this.deviceModel = deviceModel;
        this.deviceOperType = deviceOperType;
        this.operationTime = operationTime;
        this.operationType = operationType;
        this.lng = lng;
        this.lat = lat;
        this.bindStatus = bindStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPlatformFrom() {
        return platformFrom;
    }

    public void setPlatformFrom(String platformFrom) {
        this.platformFrom = platformFrom;
    }

    public String getClientPlatformType() {
        return clientPlatformType;
    }

    public void setClientPlatformType(String clientPlatformType) {
        this.clientPlatformType = clientPlatformType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceOperType() {
        return deviceOperType;
    }

    public void setDeviceOperType(String deviceOperType) {
        this.deviceOperType = deviceOperType;
    }

    public Long getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Long operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
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

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }
}
