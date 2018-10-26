package com.tianzhixing.oms.bussiness.model.task;

import com.tianzhixing.oms.bussiness.model.annotation.Column;
import com.tianzhixing.oms.bussiness.model.annotation.PrimaryKey;
import com.tianzhixing.oms.bussiness.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务分配信息
 * Created by routine.k on 2018/6/12.
 */
@Table(name = "task_allot")
public class TaskAllotModel implements Serializable {

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
     * 执行时间
     */
    @Column(name = "execute_time")
    private Date executeTime;

    /**
     * 执行日期
     */
    @Column(name = "execute_day")
    private String executeDay;

    /**
     * 任务类型 0=分配任务 1=细节任务
     */
    @Column(name = "task_type")
    private Integer taskType;

    /**
     * 任务状态 0=未执行 1=已完成
     */
    @Column(name = "task_status")
    private Integer taskStatus;

    /**
     * 机器号
     */
    @Column(name = "machine_num")
    private String machineNum;

    /**
     * 任务名称
     */
    @Column(name = "task_name")
    private String taskName;


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

    public void setUpdateTime(Date update_time) {
        this.updateTime = update_time;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
