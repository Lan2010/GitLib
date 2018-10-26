package com.tianzhixing.oms.redis.bussiness;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by routine.k on 2018/6/26.
 */
public class TaskEntity implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 汇率(mac:star 一对一关系, 一个mac换多少星点)
     */
    private String rate;

    /**
     * 是否有效
     */
    private Boolean isEnable;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 任务半径
     */
    private Integer taskRadius;

    /**
     * 位置集合信息
     */
    private Set<Long> locationIdSet;

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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
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

    public Integer getTaskRadius() {
        return taskRadius;
    }

    public void setTaskRadius(Integer taskRadius) {
        this.taskRadius = taskRadius;
    }

    public Set<Long> getLocationIdSet() {
        return locationIdSet;
    }

    public void setLocationIdSet(Set<Long> locationIdSet) {
        this.locationIdSet = locationIdSet;
    }
}
