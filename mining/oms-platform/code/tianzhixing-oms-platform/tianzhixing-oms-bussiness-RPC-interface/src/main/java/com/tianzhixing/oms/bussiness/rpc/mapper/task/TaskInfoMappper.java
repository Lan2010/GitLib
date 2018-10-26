package com.tianzhixing.oms.bussiness.rpc.mapper.task;


import com.tianzhixing.bussiness.commons.em.TaskStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
public class TaskInfoMappper implements Serializable {

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
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 汇率(mac:star 一对一关系, 一个mac换多少星点)
     */
    private Double rate;

    /**
     * 任务状态
     */
    private TaskStatus taskStatus;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 是否已经推送
     */
    private Boolean isSend;

    /**
     * 任务半径
     */
    private Integer taskRadius;

    /**
     * 任务图标
     */
    private String taskIcon;

    /**
     * 任务奖励
     */
    private Double taskAward;

    /**
     * 任务等级(0-1, 数字越高，等级越高)
     */
    private Integer taskLevel;

    /**
     * 任务描述
     */
    private String taskRemark;

    public TaskInfoMappper(Long id, Date createTime, Date updateTime, String createUser, String updateUser, String taskName, String city, String area, String keyword, Double rate, TaskStatus taskStatus, Date beginTime, Date endTime, Boolean isSend, Integer taskRadius, String taskIcon, Double taskAward, Integer taskLevel, String taskRemark) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.taskName = taskName;
        this.city = city;
        this.area = area;
        this.keyword = keyword;
        this.rate = rate;
        this.taskStatus = taskStatus;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.isSend = isSend;
        this.taskRadius = taskRadius;
        this.taskIcon = taskIcon;
        this.taskAward = taskAward;
        this.taskLevel = taskLevel;
        this.taskRemark = taskRemark;
    }

    public TaskInfoMappper() {
    }

    public Double getTaskAward() {
        return taskAward;
    }

    public void setTaskAward(Double taskAward) {
        this.taskAward = taskAward;
    }

    public Integer getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(Integer taskLevel) {
        this.taskLevel = taskLevel;
    }

    public String getTaskRemark() {
        return taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
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

    public Boolean getIsSend() {
        return isSend;
    }

    public void setIsSend(Boolean isSend) {
        this.isSend = isSend;
    }

    public Integer getTaskRadius() {
        return taskRadius;
    }

    public void setTaskRadius(Integer taskRadius) {
        this.taskRadius = taskRadius;
    }

    public String getTaskIcon() {
        return taskIcon;
    }

    public void setTaskIcon(String taskIcon) {
        this.taskIcon = taskIcon;
    }


}
