package com.tianzhixing.common.auth.commons.em;

/**
 * Created by routine.k on 16/8/12.
 */
public enum RequestFromType {

    WAKUANG(0, "挖矿APP"), WXMPAPP(1, "微信公众号"), WXMINIAPP(2, "微信小程序"), OMS(3, "运营平台"), ACCOUNT(4, "中心账户平台");

    /**
     * 标识码
     */
    private int code;

    /**
     * 资源值
     */
    private String value;

    RequestFromType(int code, String value) {
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
