package com.tianzhixing.oms.redis.bussiness;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/6/25.
 */
public class SystemParamEntity implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 值
     */
    private String systemValue;

    /**
     * 系统参数类型
     */
    private String systemParamType;

    /**
     * 是否有效
     */
    private Boolean enable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getSystemValue() {
        return systemValue;
    }

    public void setSystemValue(String systemValue) {
        this.systemValue = systemValue;
    }

    public String getSystemParamType() {
        return systemParamType;
    }

    public void setSystemParamType(String systemParamType) {
        this.systemParamType = systemParamType;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
