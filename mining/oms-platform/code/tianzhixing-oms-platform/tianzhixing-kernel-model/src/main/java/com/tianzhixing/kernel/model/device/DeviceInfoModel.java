package com.tianzhixing.kernel.model.device;

import com.tianzhixing.kernel.commons.em.DeviceStatus;
import com.tianzhixing.kernel.model.annotation.Column;
import com.tianzhixing.kernel.model.annotation.PrimaryKey;
import com.tianzhixing.kernel.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备信息表
 * Created by routine.k on 2018/6/12.
 */
@Table(name = "device_info")
public class DeviceInfoModel implements Serializable {

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
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 设备型号
     */
    @Column(name = "device_model")
    private String deviceModel;

    /**
     * 绑定时间
     */
    @Column(name = "operation_time")
    private Date operationTime;

    /**
     * 设备状态
     */
    @Column(name = "device_status")
    private DeviceStatus deviceStatus;

    /**
     * 机构
     */
    @Column(name = "org")
    private String org;

    /**
     * account id
     */
    @Column(name = "account_id")
    private Long accountId;

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

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(DeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
