package com.tianzhixing.common.auth.verification.yuanjian;

/**
 * Created by routine.k on 2018/5/22.
 */
public enum YuanJianFeeStatus {

    Y(0, "Y", "收费"), N(1, "N", "不收费");

    /**
     * 标识码
     */
    private int code;

    /**
     * key
     */
    private String key;

    /**
     * 资源值
     */
    private String value;

    YuanJianFeeStatus(int code, String key, String value) {
        this.code = code;
        this.value = value;
        this.key = key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
