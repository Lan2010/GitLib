package com.tianzhixing.kernel.rpc.mapper.account;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/6/30.
 */
public class AccountStarPointWithDayRecordsMapper implements Serializable {

    private String date;

    private Double starPoint;

    public AccountStarPointWithDayRecordsMapper() {
    }

    public AccountStarPointWithDayRecordsMapper(String date, Double starPoint) {
        this.date = date;
        this.starPoint = starPoint;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getStarPoint() {
        return starPoint;
    }

    public void setStarPoint(Double starPoint) {
        this.starPoint = starPoint;
    }
}
