package com.tianzhixing.oms.rpc.mapper;

import com.tianzhixing.oms.commons.em.ClientPlatformType;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户创建/分享贺卡信息表
 * Created by routine.k on 2018/6/12.
 */
public class UserGreeterCardInfoMapper implements Serializable {

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
     * 贺卡ID
     */
    private String greeterCardId;

    /**
     * 贺卡信息
     */
    private String greeterCardInfo;

    /**
     * 贺卡链接
     */
    private String greeterCardLink;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 微信ID
     */
    private String wxID;

    /**
     * 操作时间
     */
    private Long operationTime;

    /**
     * 操作类型(0=分享，1=创建)
     */
    private Integer operationType;

    /**
     * 来源平台
     */
    private String shareFromPlatform;

    /**
     * 分享至的平台
     */
    private String shareToPlatform;

    public UserGreeterCardInfoMapper() {
    }

    public UserGreeterCardInfoMapper(String id, Date createTime, String platformFrom, String clientPlatformType, String mobile, String greeterCardId, String greeterCardInfo, String greeterCardLink, String nickName, String wxID, Long operationTime, Integer operationType, String shareFromPlatform, String shareToPlatform) {
        this.id = id;
        this.createTime = createTime;
        this.platformFrom = platformFrom;
        this.clientPlatformType = clientPlatformType;
        this.mobile = mobile;
        this.greeterCardId = greeterCardId;
        this.greeterCardInfo = greeterCardInfo;
        this.greeterCardLink = greeterCardLink;
        this.nickName = nickName;
        this.wxID = wxID;
        this.operationTime = operationTime;
        this.operationType = operationType;
        this.shareFromPlatform = shareFromPlatform;
        this.shareToPlatform = shareToPlatform;
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

    public String getGreeterCardId() {
        return greeterCardId;
    }

    public void setGreeterCardId(String greeterCardId) {
        this.greeterCardId = greeterCardId;
    }

    public String getGreeterCardInfo() {
        return greeterCardInfo;
    }

    public void setGreeterCardInfo(String greeterCardInfo) {
        this.greeterCardInfo = greeterCardInfo;
    }

    public String getGreeterCardLink() {
        return greeterCardLink;
    }

    public void setGreeterCardLink(String greeterCardLink) {
        this.greeterCardLink = greeterCardLink;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWxID() {
        return wxID;
    }

    public void setWxID(String wxID) {
        this.wxID = wxID;
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

    public String getShareFromPlatform() {
        return shareFromPlatform;
    }

    public void setShareFromPlatform(String shareFromPlatform) {
        this.shareFromPlatform = shareFromPlatform;
    }

    public String getShareToPlatform() {
        return shareToPlatform;
    }

    public void setShareToPlatform(String shareToPlatform) {
        this.shareToPlatform = shareToPlatform;
    }
}
