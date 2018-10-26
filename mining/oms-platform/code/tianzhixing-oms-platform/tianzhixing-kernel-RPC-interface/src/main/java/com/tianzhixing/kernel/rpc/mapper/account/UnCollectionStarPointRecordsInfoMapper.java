package com.tianzhixing.kernel.rpc.mapper.account;

import com.tianzhixing.kernel.commons.em.StarPointRecordsType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/21.
 */
public class UnCollectionStarPointRecordsInfoMapper implements Serializable {

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
    private Long accountId;

    /**
     * 操作星点数
     */
    private Double operStarPoint;

    /**
     * 记录类型(com.tianzhixing.kernel.commons.em.StarPointRecordsType)
     */
    private StarPointRecordsType recordsType;

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
     * 备注
     */
    private String remark;

    /**
     * 超时时间
     */
    private Integer timeoutHour;

    public UnCollectionStarPointRecordsInfoMapper() {
    }

    public UnCollectionStarPointRecordsInfoMapper(Date createTime, Date updateTime, Long accountId, Double operStarPoint, StarPointRecordsType recordsType, String taskId, String advertisementId, String longitudeAndLatitude, String recordToken, String remark, Integer timeoutHour) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.accountId = accountId;
        this.operStarPoint = operStarPoint;
        this.recordsType = recordsType;
        this.taskId = taskId;
        this.advertisementId = advertisementId;
        this.longitudeAndLatitude = longitudeAndLatitude;
        this.recordToken = recordToken;
        this.remark = remark;
        this.timeoutHour = timeoutHour;
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getOperStarPoint() {
        return operStarPoint;
    }

    public void setOperStarPoint(Double operStarPoint) {
        this.operStarPoint = operStarPoint;
    }

    public StarPointRecordsType getRecordsType() {
        return recordsType;
    }

    public void setRecordsType(StarPointRecordsType recordsType) {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
