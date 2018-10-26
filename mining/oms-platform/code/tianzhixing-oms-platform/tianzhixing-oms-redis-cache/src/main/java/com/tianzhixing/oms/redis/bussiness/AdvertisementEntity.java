package com.tianzhixing.oms.redis.bussiness;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/25.
 */
public class AdvertisementEntity implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 单次访问奖励星点
     */
    private String onceAccessStarPoint;

    /**
     * 单次点击奖励星点
     */
    private String onceClickStarPoint;

    /**
     * 是否有效
     */
    private Boolean enable;

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

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOnceAccessStarPoint() {
        return onceAccessStarPoint;
    }

    public void setOnceAccessStarPoint(String onceAccessStarPoint) {
        this.onceAccessStarPoint = onceAccessStarPoint;
    }

    public String getOnceClickStarPoint() {
        return onceClickStarPoint;
    }

    public void setOnceClickStarPoint(String onceClickStarPoint) {
        this.onceClickStarPoint = onceClickStarPoint;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

}
