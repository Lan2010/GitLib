package com.tianzhixing.common.auth.commons.em;

/**
 * Created by routine.k on 2018/6/6.
 */
public enum CodeOrgType {

    YUNPIAN(0, "云片网");

    /**
     * 标识码
     */
    private int code;

    /**
     * 资源值
     */
    private String value;

    CodeOrgType(int code, String value) {
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
