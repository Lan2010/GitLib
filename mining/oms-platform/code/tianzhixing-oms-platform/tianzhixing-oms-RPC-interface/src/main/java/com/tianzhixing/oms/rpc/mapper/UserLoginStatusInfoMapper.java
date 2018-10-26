package com.tianzhixing.oms.rpc.mapper;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登入/登出信息表
 * Created by routine.k on 2018/6/12.
 */
public class UserLoginStatusInfoMapper implements Serializable {

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
     * 手机号
     */
    private String mobile;

    /**
     * 操作时间
     */
    private Long operationTime;

    /**
     * 操作类型(0=登出，1=登录)
     */
    private Integer operationType;

    /**
     * 微信ID
     */
    private String wxID;

    /**
     * QQID
     */
    private String qqID;

    /**
     * 新浪微博ID
     */
    private String sinaWeiBoID;

    /**
     * ip
     */
    private String ip;

    public UserLoginStatusInfoMapper() {
    }

    public UserLoginStatusInfoMapper(String id, Date createTime, String platformFrom, String clientPlatformType, String mobile, Long operationTime, Integer operationType, String wxID, String qqID, String sinaWeiBoID, String ip) {
        this.id = id;
        this.createTime = createTime;
        this.platformFrom = platformFrom;
        this.clientPlatformType = clientPlatformType;
        this.mobile = mobile;
        this.operationTime = operationTime;
        this.operationType = operationType;
        this.wxID = wxID;
        this.qqID = qqID;
        this.sinaWeiBoID = sinaWeiBoID;
        this.ip = ip;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Long operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getWxID() {
        return wxID;
    }

    public void setWxID(String wxID) {
        this.wxID = wxID;
    }

    public String getQqID() {
        return qqID;
    }

    public void setQqID(String qqID) {
        this.qqID = qqID;
    }

    public String getSinaWeiBoID() {
        return sinaWeiBoID;
    }

    public void setSinaWeiBoID(String sinaWeiBoID) {
        this.sinaWeiBoID = sinaWeiBoID;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
