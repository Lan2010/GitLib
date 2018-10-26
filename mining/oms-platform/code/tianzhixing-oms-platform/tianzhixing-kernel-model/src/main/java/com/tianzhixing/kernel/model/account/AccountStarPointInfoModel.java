package com.tianzhixing.kernel.model.account;

import com.tianzhixing.kernel.model.annotation.Column;
import com.tianzhixing.kernel.model.annotation.PrimaryKey;
import com.tianzhixing.kernel.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户星点
 * Created by routine.k on 2018/6/21.
 */
@Table(name = "account_starpoint_info")
public class AccountStarPointInfoModel implements Serializable{

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
}
