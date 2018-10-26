package com.tianzhixing.oms.bussiness.rpc.mapper.task;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/4/27.
 */
public class TaskAllotMapper implements Serializable {

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
     * 执行时间
     */
    private Date executeTime;

    /**
     * 执行日期
     */
    private String executeDay;

    /**
     * 任务类型 0=分配任务 1=细节任务
     */
    private Integer taskType;

    /**
     * 任务状态 0=未执行 1=已完成
     */
    private Integer taskStatus;

    /**
     * 机器号
     */
    private String machineNum;

    /**
     * 任务名称
     */
    private String taskName;

    public TaskAllotMapper() {
    }

    public TaskAllotMapper(Long id, Integer version, Date createTime, Date updateTime, Date executeTime, String executeDay, Integer taskType, Integer taskStatus, String machineNum, String taskName) {
        this.id = id;
        this.version = version;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.executeTime = executeTime;
        this.executeDay = executeDay;
        this.taskType = taskType;
        this.taskStatus = taskStatus;
        this.machineNum = machineNum;
        this.taskName = taskName;
    }

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

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public String getExecuteDay() {
        return executeDay;
    }

    public void setExecuteDay(String executeDay) {
        this.executeDay = executeDay;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getMachineNum() {
        return machineNum;
    }

    public void setMachineNum(String machineNum) {
        this.machineNum = machineNum;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
