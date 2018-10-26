package com.tianzhixing.oms.rpc.mapper;

import com.tianzhixing.oms.commons.em.AuthStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户认证信息表
 * Created by routine.k on 2018/6/12.
 */
public class UserAuthInfoMapper implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 请求平台来源
     */
    private String platformFrom;

    /**
     * 客户端类型
     */
    private String clientPlatformType;

    /**
     * 认证类型
     */
    private String authType;

    /**
     * 认证状态(0=失败，1=成功)
     */
    private Integer authStatus;

    /**
     * 认证时间
     */
    private Long authTime;

    public UserAuthInfoMapper() {
    }

    public UserAuthInfoMapper(String id, Date createTime, String platformFrom, String clientPlatformType, String authType, Integer authStatus, Long authTime) {
        this.id = id;
        this.createTime = createTime;
        this.platformFrom = platformFrom;
        this.clientPlatformType = clientPlatformType;
        this.authType = authType;
        this.authStatus = authStatus;
        this.authTime = authTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPlatformFrom() {
        return platformFrom;
    }

    public void setPlatformFrom(String platformFrom) {
        this.platformFrom = platformFrom;
    }

    public String getClientPlatformType() {
        return clientPlatformType;
    }

    public void setClientPlatformType(String clientPlatformType) {
        this.clientPlatformType = clientPlatformType;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public Long getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Long authTime) {
        this.authTime = authTime;
    }
}
