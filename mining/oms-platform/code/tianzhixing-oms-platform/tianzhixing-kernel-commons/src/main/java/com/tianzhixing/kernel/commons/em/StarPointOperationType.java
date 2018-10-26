package com.tianzhixing.kernel.commons.em;

/**
 * 星点操作类型
 * Created by routine.k on 2018/6/11.
 */
public enum StarPointOperationType {
    CONSUME(0, "CONSUME"),
    INCREMENT(1, "INCREMENT"),
    FROZEN(2, "FROZEN");

    /**
     * 标识码
     */
    private int code;

    /**
     * 资源值
     */
    private String value;

    StarPointOperationType(int code, String value) {
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

    public static StarPointOperationType byCode(int code) {
        for (StarPointOperationType starPointOperationType : StarPointOperationType.values()) {
            if (code == starPointOperationType.getCode()) {
                return starPointOperationType;
            }
        }
        return null;
    }
}
