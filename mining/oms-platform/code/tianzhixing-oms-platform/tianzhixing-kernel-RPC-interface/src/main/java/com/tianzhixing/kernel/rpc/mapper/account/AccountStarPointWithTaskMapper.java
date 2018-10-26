package com.tianzhixing.kernel.rpc.mapper.account;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/6/30.
 */
public class AccountStarPointWithTaskMapper implements Serializable {

    private String task;

    private Double starPoint;

    public AccountStarPointWithTaskMapper() {
    }

    public AccountStarPointWithTaskMapper(String task, Double starPoint) {
        this.task = task;
        this.starPoint = starPoint;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Double getStarPoint() {
        return starPoint;
    }

    public void setStarPoint(Double starPoint) {
        this.starPoint = starPoint;
    }
}
