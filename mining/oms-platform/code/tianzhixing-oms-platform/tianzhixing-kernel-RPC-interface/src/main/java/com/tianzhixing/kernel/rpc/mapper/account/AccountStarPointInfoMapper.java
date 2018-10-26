package com.tianzhixing.kernel.rpc.mapper.account;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户星点
 * Created by routine.k on 2018/6/21.
 */
public class AccountStarPointInfoMapper implements Serializable {

    /**
     * id
     */
    private Long id;

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

    public AccountStarPointInfoMapper() {
    }

    public AccountStarPointInfoMapper(Long id, Date updateTime, Long accountId, Double availableStarPoint, Double frozenStarPoint) {
        this.id = id;
        this.updateTime = updateTime;
        this.accountId = accountId;
        this.availableStarPoint = availableStarPoint;
        this.frozenStarPoint = frozenStarPoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
