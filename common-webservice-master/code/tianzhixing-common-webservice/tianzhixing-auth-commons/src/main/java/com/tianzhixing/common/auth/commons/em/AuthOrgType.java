package com.tianzhixing.common.auth.commons.em;

/**
 * Created by routine.k on 2018/6/6.
 */
public enum AuthOrgType {

    YUANJIAN(0, "远鉴科技"),
    //不进行验证前的存储
    DATA(0, "数据存储");

    /**
     * 标识码
     */
    private int code;

    /**
     * 资源值
     */
    private String value;

    AuthOrgType(int code, String value) {
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
