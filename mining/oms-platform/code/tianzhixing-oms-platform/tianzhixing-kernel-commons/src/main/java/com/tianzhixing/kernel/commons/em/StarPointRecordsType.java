package com.tianzhixing.kernel.commons.em;

/**
 * 星点记录类型
 * Created by routine.k on 2018/6/11.
 */
public enum StarPointRecordsType {
    TASK(0, "TASK"),
    ADVERTISEMENT(1, "ADVERTISEMENT"),
    BASIC(2, "BASIC"),
    RANDOM(3, "RANDOM"),
    CONSUME(4, "CONSUME"),
    AWARD(5, "AWARD");


    /**
     * 标识码
     */
    private int code;

    /**
     * 资源值
     */
    private String value;

    StarPointRecordsType(int code, String value) {
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

    public static StarPointRecordsType byCode(int code) {
        for (StarPointRecordsType starPointRecordsType : StarPointRecordsType.values()) {
            if (code == starPointRecordsType.getCode()) {
                return starPointRecordsType;
            }
        }
        return null;
    }
}
