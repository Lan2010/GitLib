package com.tianzhixing.kernel.rpc.mapper.account;

import com.tianzhixing.kernel.commons.em.StarPointOperationType;
import com.tianzhixing.kernel.commons.em.StarPointRecordsType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/21.
 */
public class AccountStarPointRecordsInfoMapper implements Serializable {

    /**
     * id
     */
    private Long id;

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
     * 可用星点
     */
    private Double availableStarPoint;

    /**
     * 冻结星点
     */
    private Double frozenStarPoint;

    /**
     * 操作星点数
     */
    private Double operStarPoint;

    /**
     * 操作类型(com.tianzhixing.kernel.commons.em.StarPointOperationType)
     */
    private StarPointOperationType operationType;

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
     * 备注
     */
    private String remark;

    public AccountStarPointRecordsInfoMapper() {
    }

    public AccountStarPointRecordsInfoMapper(Long id, Date createTime, Date updateTime, Long accountId, Double availableStarPoint, Double frozenStarPoint, Double operStarPoint, StarPointOperationType operationType, StarPointRecordsType recordsType, String taskId, String advertisementId, String remark) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.accountId = accountId;
        this.availableStarPoint = availableStarPoint;
        this.frozenStarPoint = frozenStarPoint;
        this.operStarPoint = operStarPoint;
        this.operationType = operationType;
        this.recordsType = recordsType;
        this.taskId = taskId;
        this.advertisementId = advertisementId;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getAvailableStarPoint() {
        return availableStarPoint;
    }

    public void setAvailableStarPoint(Double availableStarPoint) {
        this.availableStarPoint = availableStarPoint;
    }

    public Double getFrozenStarPoint() {
        return frozenStarPoint;
    }

    public void setFrozenStarPoint(Double frozenStarPoint) {
        this.frozenStarPoint = frozenStarPoint;
    }

    public Double getOperStarPoint() {
        return operStarPoint;
    }

    public void setOperStarPoint(Double operStarPoint) {
        this.operStarPoint = operStarPoint;
    }

    public StarPointOperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(StarPointOperationType operationType) {
        this.operationType = operationType;
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
}
