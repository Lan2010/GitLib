package com.tianzhixing.oms.redis.starpoint;

import java.io.Serializable;
import java.util.Date;

/**
 * Mac 产生星点的记录
 * Created by routine.k on 2018/6/21.
 */
public class DeviceMacCollectionStarPointRecords implements Serializable {

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
     * 账户ID
     */
    private String accountId;

    /**
     * 操作星点数
     */
    private String operStarPoint;

    /**
     * 记录类型(com.tianzhixing.kernel.commons.em.StarPointRecordsType)
     */
    private Integer recordsType;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 广告ID
     */
    private String advertisementId;

    /**
     * 经纬度信息
     */
    private String longitudeAndLatitude;

    /**
     * 记录Token，唯一
     */
    private String recordToken;

    /**
     * 超时时间
     */
    private Integer timeoutHour;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务关键字
     */
    private String taskKeyWord;

    /**
     * 任务地点名称
     */
    private String taskLocationName;

    /**
     * 设备id
     */
    private String devid;

    /**
     * 设备wifi
     */
    private String devwifi;

    /**
     * 蓝牙地址
     */
    private String devbt;

    /**
     * 时间
     */
    private String time;

    /**
     * gps经纬度
     */
    private String gps;

    /**
     * 采集到的数量
     */
    private int count;


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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOperStarPoint() {
        return operStarPoint;
    }

    public void setOperStarPoint(String operStarPoint) {
        this.operStarPoint = operStarPoint;
    }

    public Integer getRecordsType() {
        return recordsType;
    }

    public void setRecordsType(Integer recordsType) {
        this.recordsType = recordsType;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(String advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getLongitudeAndLatitude() {
        return longitudeAndLatitude;
    }

    public void setLongitudeAndLatitude(String longitudeAndLatitude) {
        this.longitudeAndLatitude = longitudeAndLatitude;
    }

    public String getRecordToken() {
        return recordToken;
    }

    public void setRecordToken(String recordToken) {
        this.recordToken = recordToken;
    }

    public Integer getTimeoutHour() {
        return timeoutHour;
    }

    public void setTimeoutHour(Integer timeoutHour) {
        this.timeoutHour = timeoutHour;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskKeyWord() {
        return taskKeyWord;
    }

    public void setTaskKeyWord(String taskKeyWord) {
        this.taskKeyWord = taskKeyWord;
    }

    public String getTaskLocationName() {
        return taskLocationName;
    }

    public void setTaskLocationName(String taskLocationName) {
        this.taskLocationName = taskLocationName;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public String getDevwifi() {
        return devwifi;
    }

    public void setDevwifi(String devwifi) {
        this.devwifi = devwifi;
    }

    public String getDevbt() {
        return devbt;
    }

    public void setDevbt(String devbt) {
        this.devbt = devbt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
