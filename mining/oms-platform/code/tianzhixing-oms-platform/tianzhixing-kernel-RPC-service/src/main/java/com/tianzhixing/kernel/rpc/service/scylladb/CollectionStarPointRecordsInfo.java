package com.tianzhixing.kernel.rpc.service.scylladb;

import java.io.Serializable;
import java.util.Date;

/**
 * 采集的星点记录
 * Created by routine.k on 2018/6/21.
 */
public class CollectionStarPointRecordsInfo implements Serializable {

    /**
     * 创建时间
     */
    private Date createTime;

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
     * 记录Token，唯一
     */
    private String recordToken;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getRecordToken() {
        return recordToken;
    }

    public void setRecordToken(String recordToken) {
        this.recordToken = recordToken;
    }
}
