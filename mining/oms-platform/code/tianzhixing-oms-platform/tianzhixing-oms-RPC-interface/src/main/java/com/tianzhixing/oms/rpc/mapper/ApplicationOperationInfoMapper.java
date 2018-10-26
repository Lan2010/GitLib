package com.tianzhixing.oms.rpc.mapper;

import java.io.Serializable;
import java.util.Date;

/**
 * 程序开关机信息
 * Created by routine.k on 2018/6/12.
 */
public class ApplicationOperationInfoMapper implements Serializable {

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
     * app 操作时间
     */
    private Long appOperationTime;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 微信ID
     */
    private String wxID;

    /**
     * ip
     */
    private String ip;

    /**
     * 操作类型(0=关机，1=开机)
     */
    private Integer operationType;

    public ApplicationOperationInfoMapper() {
    }

    public ApplicationOperationInfoMapper(String id, Date createTime, String platformFrom, String clientPlatformType, Long appOperationTime, String mobile, String wxID, String ip, Integer operationType) {
        this.id = id;
        this.createTime = createTime;
        this.platformFrom = platformFrom;
        this.clientPlatformType = clientPlatformType;
        this.appOperationTime = appOperationTime;
        this.mobile = mobile;
        this.wxID = wxID;
        this.ip = ip;
        this.operationType = operationType;
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

    public Long getAppOperationTime() {
        return appOperationTime;
    }

    public void setAppOperationTime(Long appOperationTime) {
        this.appOperationTime = appOperationTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWxID() {
        return wxID;
    }

    public void setWxID(String wxID) {
        this.wxID = wxID;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }
}
