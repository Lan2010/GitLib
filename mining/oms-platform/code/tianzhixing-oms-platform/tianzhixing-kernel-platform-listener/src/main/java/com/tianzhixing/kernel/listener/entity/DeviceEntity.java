package com.tianzhixing.kernel.listener.entity;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/6/26.
 */
public class DeviceEntity implements Serializable {


////{
//    "devid":"TZX1_AABBCC22E77",
//            "devwifi":"00:E0:4C:3B:7D:2F",
//            "devbt":"11:E0:4C:3B:7D:2F",
//            "time":"2018-06-25 10:01:10",
//            "gps":"114.121212;89.232323",
//            "count":11,
//}

    /**
     * 设备id
     */
    private String devid;

    /**
     * 设备wifi
     */
    private String devwifi;

    /**
     * 蓝牙地址
     */
    private String devbt;

    /**
     * 时间
     */
    private String time;

    /**
     * gps经纬度
     */
    private String gps;

    /**
     * 采集到的数量
     */
    private int count;

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public String getDevwifi() {
        return devwifi;
    }

    public void setDevwifi(String devwifi) {
        this.devwifi = devwifi;
    }

    public String getDevbt() {
        return devbt;
    }

    public void setDevbt(String devbt) {
        this.devbt = devbt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
