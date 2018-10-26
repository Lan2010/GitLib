package com.tianzhixing.oms.bussiness.backend.web.vo;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/6/24.
 */
public class CityVo implements Serializable{

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
