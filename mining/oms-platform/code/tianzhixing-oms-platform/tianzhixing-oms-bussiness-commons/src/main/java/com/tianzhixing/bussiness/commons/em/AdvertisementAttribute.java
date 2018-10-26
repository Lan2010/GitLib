package com.tianzhixing.bussiness.commons.em;

/**
 * Created by routine.k on 2018/6/22.
 */
public enum AdvertisementAttribute {

    DEFAULT(0, "默认"),
    ADVERTISER(1, "广告商");

    /**
     * 标识码
     */
    private int code;

    /**
     * 资源值
     */
    private String value;

    AdvertisementAttribute(int code, String value) {
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
