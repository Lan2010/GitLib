package com.tianzhixing.oms.rpc.mapper;

import com.tianzhixing.oms.commons.em.ClientPlatformType;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户创建/分享明信片信息表
 * Created by routine.k on 2018/6/12.
 */
public class UserPostCardInfoMapper implements Serializable {

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
     * 明信片ID
     */
    private String postCardId;

    /**
     * 明信片信息
     */
    private String postCardInfo;

    /**
     * 明信片链接
     */
    private String postCardLink;

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

    public UserPostCardInfoMapper() {
    }

    public UserPostCardInfoMapper(String id, Date createTime, String platformFrom, String clientPlatformType, String mobile, String postCardId, String postCardInfo, String postCardLink, String nickName, String wxID, Long operationTime, Integer operationType, String shareFromPlatform, String shareToPlatform) {
        this.id = id;
        this.createTime = createTime;
        this.platformFrom = platformFrom;
        this.clientPlatformType = clientPlatformType;
        this.mobile = mobile;
        this.postCardId = postCardId;
        this.postCardInfo = postCardInfo;
        this.postCardLink = postCardLink;
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

    public String getPostCardId() {
        return postCardId;
    }

    public void setPostCardId(String postCardId) {
        this.postCardId = postCardId;
    }

    public String getPostCardInfo() {
        return postCardInfo;
    }

    public void setPostCardInfo(String postCardInfo) {
        this.postCardInfo = postCardInfo;
    }

    public String getPostCardLink() {
        return postCardLink;
    }

    public void setPostCardLink(String postCardLink) {
        this.postCardLink = postCardLink;
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
