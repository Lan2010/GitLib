package com.tianzhixing.oms.commons.em;

/**
 * Created by routine.k on 2018/6/12.
 */
public enum AuthStatus {
    SUC(0, "SUC"), FAILED(1, "FAILED");

    /**
     * 标识码
     */
    private int code;

    /**
     * 资源值
     */
    private String value;

    AuthStatus(int code, String value) {
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
