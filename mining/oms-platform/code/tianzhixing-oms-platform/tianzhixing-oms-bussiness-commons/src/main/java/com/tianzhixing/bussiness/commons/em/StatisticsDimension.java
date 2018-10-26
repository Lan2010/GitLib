package com.tianzhixing.bussiness.commons.em;

/**
 * Created by routine.k on 2018/7/5.
 */
public enum StatisticsDimension {

    PLATFORM(0, "平台"),
    CLIENT(1, "客户端");

    /**
     * 标识码
     */
    private int code;

    /**
     * 资源值
     */
    private String value;

    StatisticsDimension(int code, String value) {
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
