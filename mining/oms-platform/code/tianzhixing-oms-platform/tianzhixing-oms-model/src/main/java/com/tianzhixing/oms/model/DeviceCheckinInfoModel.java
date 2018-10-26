package com.tianzhixing.oms.model;

import com.tianzhixing.oms.model.annotation.Column;
import com.tianzhixing.oms.model.annotation.PrimaryKey;
import com.tianzhixing.oms.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备登记信息表
 * Created by routine.k on 2018/6/12.
 */
@Table(name = "device_checkin_info")
public class DeviceCheckinInfoModel implements Serializable {

    /**
     * id
     */
    @PrimaryKey
    @Column(name = "id")
    private String id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 请求平台来源
     */
    @Column(name = "platform_from")
    private String platformFrom;

    /**
     * 客户端类型
     */
    @Column(name = "client_platform_type")
    private String clientPlatformType;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 设备ID
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 设备类型
     */
    @Column(name = "device_type")
    private String deviceType;

    /**
     * 设备mac地址
     */
    @Column(name = "device_mac")
    private String deviceMac;

    /**
     * 设备ip地址
     */
    @Column(name = "device_ip")
    private String deviceIp;

    /**
     * 设备型号
     */
    @Column(name = "device_model")
    private String deviceModel;

    /**
     * 设备操作类型
     */
    @Column(name = "device_oper_type")
    private String deviceOperType;

    /**
     * 登记时间
     */
    @Column(name = "checkin_time")
    private Long checkinTime;

    /**
     * 是否已绑定(0=未绑定，1=已绑定)
     */
    @Column(name = "bind_status")
    private Integer bindStatus;

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

    public Long getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Long checkinTime) {
        this.checkinTime = checkinTime;
    }

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }
}
