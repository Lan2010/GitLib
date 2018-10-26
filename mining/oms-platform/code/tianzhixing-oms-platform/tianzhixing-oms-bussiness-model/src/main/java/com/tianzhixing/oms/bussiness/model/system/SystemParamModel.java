package com.tianzhixing.oms.bussiness.model.system;

import com.tianzhixing.bussiness.commons.em.SystemParamType;
import com.tianzhixing.oms.bussiness.model.annotation.Column;
import com.tianzhixing.oms.bussiness.model.annotation.PrimaryKey;
import com.tianzhixing.oms.bussiness.model.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/25.
 */
@Table(name = "system_param")
public class SystemParamModel implements Serializable {

    /**
     * id
     */
    @PrimaryKey
    @Column(name = "id")
    private Long id;

    /**
     * 版本号
     */
    @Column(name = "version")
    private Integer version;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 更新人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 值
     */
    @Column(name = "system_value")
    private String systemValue;

    /**
     * 是否需要推送
     */
    @Column(name = "need_push")
    private Boolean needPush;

    /**
     * 系统参数类型
     */
    @Column(name = "system_param_type")
    private SystemParamType systemParamType;

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

    public Boolean getNeedPush() {
        return needPush;
    }

    public void setNeedPush(Boolean needPush) {
        this.needPush = needPush;
    }
}
