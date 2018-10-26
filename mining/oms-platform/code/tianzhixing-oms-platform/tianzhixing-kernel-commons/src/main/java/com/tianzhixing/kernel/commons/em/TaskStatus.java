package com.tianzhixing.kernel.commons.em;

/**
 * Created by routine.k on 2018/6/22.
 */
public enum TaskStatus {

    ACCEPT(0, "ACCEPT"),
    CANCEL(1, "CANCEL");

    /**
     * 标识码
     */
    private int code;

    /**
     * 资源值
     */
    private String value;

    TaskStatus(int code, String value) {
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
