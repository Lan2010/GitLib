package com.tianzhixing.oms.bussiness.backend.web.vo;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/7/17.
 */
public class ScatterLineVo implements Serializable {

    private String x;

    private String y;

    public ScatterLineVo(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
