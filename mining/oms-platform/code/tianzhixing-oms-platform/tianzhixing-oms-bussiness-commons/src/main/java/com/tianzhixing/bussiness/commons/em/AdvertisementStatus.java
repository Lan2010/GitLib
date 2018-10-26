package com.tianzhixing.bussiness.commons.em;

/**
 * Created by routine.k on 2018/6/22.
 */
public enum AdvertisementStatus {

    ENABLE(0,"可用"),
    DISABLE(1,"不可用");

    /**
     * 标识码
     */
    private int code;

    /**
     * 资源值
     */
    private String value;

    AdvertisementStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
