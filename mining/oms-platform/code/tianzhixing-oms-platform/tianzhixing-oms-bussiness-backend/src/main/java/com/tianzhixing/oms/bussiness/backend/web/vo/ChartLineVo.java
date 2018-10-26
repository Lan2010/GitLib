package com.tianzhixing.oms.bussiness.backend.web.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by routine.k on 2018/7/14.
 */
public class ChartLineVo implements Serializable {

    private String label;
    private Boolean fill;
    private List data;

    public ChartLineVo() {
    }

    public ChartLineVo(String label, Boolean fill, List data) {
        this.label = label;
        this.fill = fill;
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getFill() {
        return fill;
    }

    public void setFill(Boolean fill) {
        this.fill = fill;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
