package com.tianzhixing.common.auth.verification.yuanjian;

/**
 * Created by routine.k on 2018/5/22.
 */
public enum YuanJianResultDataCode {
    ZERO(0, "0", "亲，认证成功(收费)"),
    ONE(1, "1", "亲，认证信息不一致(收费)"),
    TWO(2, "2", "亲，认证信息不存在( 收费)"),
    NINE(3, "9", "亲，其他异常(不收费)");

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

    YuanJianResultDataCode(int code, String key, String value) {
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
