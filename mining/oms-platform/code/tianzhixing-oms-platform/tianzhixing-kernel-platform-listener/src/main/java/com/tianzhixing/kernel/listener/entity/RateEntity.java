package com.tianzhixing.kernel.listener.entity;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/6/26.
 */
public class RateEntity implements Serializable {

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 汇率
     */
    private String rate;

    /**
     * 失效时间 - 小时
     */
    private Long timeoutHours;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务关键字
     */
    private String taskKeyWord;

    /**
     * 任务地点名称
     */
    private String taskLocationName;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Long getTimeoutHours() {
        return timeoutHours;
    }

    public void setTimeoutHours(Long timeoutHours) {
        this.timeoutHours = timeoutHours;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskKeyWord() {
        return taskKeyWord;
    }

    public void setTaskKeyWord(String taskKeyWord) {
        this.taskKeyWord = taskKeyWord;
    }

    public String getTaskLocationName() {
        return taskLocationName;
    }

    public void setTaskLocationName(String taskLocationName) {
        this.taskLocationName = taskLocationName;
    }
}
