package com.tianzhixing.oms.rpc.mapper;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户访问/点击广告信息表
 * Created by routine.k on 2018/6/16.
 */
public class UserAdvertisementInfoMapper implements Serializable {

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
     * 广告ID
     */
    private String advertId;

    /**
     * 广告链接
     */
    private String advertLink;

    /**
     * 广告类型
     */
    private String advertType;

    /**
     * 广告名字
     */
    private String advertName;

    /**
     * 广告信息
     */
    private String advertInfo;

    /**
     * 操作时间
     */
    private Long operationTime;

    /**
     * 操作类型(0=访问，1=点击)
     */
    private Integer operationType;

    /**
     * ip
     */
    private String ip;

    public UserAdvertisementInfoMapper() {
    }

    public UserAdvertisementInfoMapper(String id, Date createTime, String platformFrom, String clientPlatformType, String mobile, String advertId, String advertLink, String advertType, String advertName, String advertInfo, Long operationTime, Integer operationType, String ip) {
        this.id = id;
        this.createTime = createTime;
        this.platformFrom = platformFrom;
        this.clientPlatformType = clientPlatformType;
        this.mobile = mobile;
        this.advertId = advertId;
        this.advertLink = advertLink;
        this.advertType = advertType;
        this.advertName = advertName;
        this.advertInfo = advertInfo;
        this.operationTime = operationTime;
        this.operationType = operationType;
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

    public String getAdvertId() {
        return advertId;
    }

    public void setAdvertId(String advertId) {
        this.advertId = advertId;
    }

    public String getAdvertLink() {
        return advertLink;
    }

    public void setAdvertLink(String advertLink) {
        this.advertLink = advertLink;
    }

    public String getAdvertType() {
        return advertType;
    }

    public void setAdvertType(String advertType) {
        this.advertType = advertType;
    }

    public String getAdvertName() {
        return advertName;
    }

    public void setAdvertName(String advertName) {
        this.advertName = advertName;
    }

    public String getAdvertInfo() {
        return advertInfo;
    }

    public void setAdvertInfo(String advertInfo) {
        this.advertInfo = advertInfo;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
