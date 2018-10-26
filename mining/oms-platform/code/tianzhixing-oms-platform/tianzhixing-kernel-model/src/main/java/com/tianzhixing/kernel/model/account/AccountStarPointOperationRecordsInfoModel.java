package com.tianzhixing.kernel.model.account;

import com.tianzhixing.kernel.model.annotation.Column;
import com.tianzhixing.kernel.model.annotation.PrimaryKey;
import com.tianzhixing.kernel.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户星点操作记录
 * Created by routine.k on 2018/6/21.
 */
@Table(name = "account_starpoint_operation_records")
public class AccountStarPointOperationRecordsInfoModel implements Serializable {

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
     * 账户ID
     */
    @Column(name = "account_id")
    private Long accountId;

    /**
     * 可用星点
     */
    @Column(name = "available_starpoint")
    private Double availableStarPoint;

    /**
     * 冻结星点
     */
    @Column(name = "frozen_starpoint")
    private Double frozenStarPoint;

    /**
     * 操作星点数
     */
    @Column(name = "oper_starpoint")
    private Double operStarPoint;

    /**
     * 操作类型(com.tianzhixing.kernel.commons.em.StarPointOperationType)
     */
    @Column(name = "operation_type")
    private Integer operationType;

    /**
     * 记录类型(com.tianzhixing.kernel.commons.em.StarPointRecordsType)
     */
    @Column(name = "records_type")
    private Integer recordsType;

    /**
     * 任务ID
     */
    @Column(name = "task_id")
    private String taskId;

    /**
     * 广告ID
     */
    @Column(name = "advertisement_id")
    private String advertisementId;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

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

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
