package com.tianzhixing.oms.bussiness.rpc.mapper.system;

import com.tianzhixing.bussiness.commons.em.SystemParamType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/25.
 */
public class SystemParamMapper implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 值
     */
    private String systemValue;

    /**
     * 系统参数类型
     */
    private SystemParamType systemParamType;

    /**
     * 是否需要推送
     */
    private Boolean needPush;

    public SystemParamMapper() {
    }

    public Boolean getNeedPush() {
        return needPush;
    }

    public void setNeedPush(Boolean needPush) {
        this.needPush = needPush;
    }

    public SystemParamMapper(Long id, Date createTime, Date updateTime, String createUser, String updateUser, String systemValue, SystemParamType systemParamType, Boolean needPush) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.systemValue = systemValue;
        this.systemParamType = systemParamType;
        this.needPush = needPush;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getSystemValue() {
        return systemValue;
    }

    public void setSystemValue(String systemValue) {
        this.systemValue = systemValue;
    }

    public SystemParamType getSystemParamType() {
        return systemParamType;
    }

    public void setSystemParamType(SystemParamType systemParamType) {
        this.systemParamType = systemParamType;
    }
}
