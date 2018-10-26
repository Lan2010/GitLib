package com.tianzhixing.kernel.rpc.mapper.task;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
public class TaskOperationMapper implements Serializable {

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 接受时间
     */
    private Date acceptTime;

    public TaskOperationMapper(String taskId, Date acceptTime) {
        this.taskId = taskId;
        this.acceptTime = acceptTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }
}
