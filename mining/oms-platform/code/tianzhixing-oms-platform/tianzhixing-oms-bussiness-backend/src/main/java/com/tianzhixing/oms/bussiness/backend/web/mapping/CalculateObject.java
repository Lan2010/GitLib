package com.tianzhixing.oms.bussiness.backend.web.mapping;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/7/16.
 */
public class CalculateObject implements Serializable {

    private Integer value = 0;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
